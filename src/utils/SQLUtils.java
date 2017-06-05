package utils;

import bean.CommonBean;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.sql.PooledConnection;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by dream on 17-3-15.
 */
public class SQLUtils {
    private static final String dbms = "mysql";
    private static final String serverName = "localhost";
    private static final String dbName = "BookReaderServer";
    private static final String userName = "root";
    private static final String password = "000";
    private static final String autoReconnect = "true";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final int portNumber = 3306;
    @NotNull
    private static SQLUtils sqlUtils = new SQLUtils();

    @NotNull
    private Properties prop = new Properties();

    @Nullable
    private Connection ownConnection;

    private SQLUtils() {
        prop.put("user", userName);
        prop.put("password", password);
        prop.put("autoReconnect", autoReconnect);
        ownConnection = getConnectionByDriverManager();
    }

    @Nullable
    private Connection getConnectionByDriverManager() {
        Connection conn = null;
        try {
            Class.forName(driver); //jdbc4.0之后不需要
            conn = DriverManager.getConnection("jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/" + dbName, prop);
            System.out.println("Connected to database");
        } catch (@NotNull SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @NotNull
    public static SQLUtils getInstance() {
        return sqlUtils;
    }

    public void createTable(String tableName, String tableAttributes) throws SQLException {
        String sql = "CREATE TABLE " + tableName + tableAttributes;
        try (PreparedStatement stmt = ownConnection.prepareStatement(sql)) {
            stmt.executeUpdate(sql);
        }
    }

    @Nullable
    public Connection getOwnConnection() {
        return ownConnection;
    }

    @Nullable
    public PreparedStatement getPtmt(String sql) {
        try {
            return ownConnection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertBeanToDB(String sql, @NotNull CommonBean bean, @NotNull List<Field> insert) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ownConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for (Field field : insert) {
                if (field.equals(bean.getAutoIncreField())) {
                    continue;
                }
                // if (field.getType() == String.class || field.getType() == Timestamp.class)
                // values.append("\"").append(field.get(bean)).append("\"").append(",");
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                stmt.setObject(i, field.get(bean));
                field.setAccessible(flag);
                i++;
            }
            stmt.executeUpdate();
            if (bean.getAutoIncreField() != null) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    bean.getAutoIncreField().set(bean, rs.getInt(1));
                } else {
                    bean.getAutoIncreField().set(bean, -1);
                }
            }
            return true;
        } catch (@NotNull SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateBeanInDB(String sql, @NotNull CommonBean bean, @NotNull List<Field> update) {
        try (PreparedStatement stmt = ownConnection.prepareStatement(sql)) {
            int i = 1;
            for (Field field : update) {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                stmt.setObject(i, field.get(bean));
                field.setAccessible(flag);
                i++;
            }
            stmt.setObject(i, bean.getPrimaryKeyField().get(bean));
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean queryExists(@NotNull PreparedStatement stmt) {
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void populateDBbyBean(@NotNull ResultSet rs, @NotNull Object bean) throws SQLException, IllegalAccessException, InstantiationException {
        rs.moveToInsertRow();
        Class beanClass = bean.getClass();
        Field[] fields = beanClass.getFields();
        for (Field field : fields) {
            rs.updateObject(field.getName(), field.get(bean));
        }
        rs.insertRow();
        rs.beforeFirst();
    }

    @NotNull
    public <T> List<T> getBeansFromDB(String tableName, @NotNull Class<T> beanClass) throws SQLException, InstantiationException, IllegalAccessException {
        PreparedStatement stmt = null;
        try {
            stmt = ownConnection.prepareStatement("SELECT * FROM " + tableName);
            return getBeansFromSQL(stmt, beanClass);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @NotNull
    public <T> List<T> getBeansFromSQL(@NotNull PreparedStatement sql, @NotNull Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException {
//            List<Field> temp = new ArrayList<>();
//            Class<?> clazz = clazz;
//            while (clazz != Object.class) {
//                temp.addAll(Arrays.asList(clazz.getFields()));
//                clazz = clazz.getSuperclass();
//            }
//            Field[] fields = new Field[temp.size()];
//            temp.toArray(fields);
        try (ResultSet rs = sql.executeQuery()) {
            List<T> list = new ArrayList<>();
            Field[] fields = clazz.getFields();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            rs.beforeFirst();
            while (rs.next()) {
                T bean = clazz.newInstance();
                fill(rs, bean, fields, rsmd, cols);
                list.add(bean);
            }
            return list;
        }
    }

    //    boolean flag = field.isAccessible();
    //    field.setAccessible(true);
    //    field.set(bean, rs.getObject(i));
    //    field.setAccessible(flag);
    private <T> void fill(@NotNull ResultSet rs, T bean, @NotNull Field[] fields, @NotNull ResultSetMetaData rsmd, int cols) throws SQLException, IllegalAccessException {
        for (int i = 1; i <= cols; i++) {
            for (Field field : fields) {
                if (field.getName().equalsIgnoreCase(rsmd.getColumnName(i))) {
                    field.set(bean, rs.getObject(i));
                }
            }
        }
    }

    private PooledConnection getConnectionByDataSource() throws SQLException {
        MysqlConnectionPoolDataSource cpds = new MysqlConnectionPoolDataSource();
        cpds.setServerName(serverName);
        cpds.setPort(portNumber);
        cpds.setDatabaseName(dbName);
        cpds.setUser(userName);
        cpds.setPassword(password);
        return cpds.getPooledConnection();
    }
}
