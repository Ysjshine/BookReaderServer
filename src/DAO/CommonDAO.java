package DAO;

import bean.CommonBean;
import org.jetbrains.annotations.NotNull;
import utils.SQLUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dream on 17-3-18.
 */
public abstract class CommonDAO<T extends CommonBean> {
    protected static SQLUtils sqlUtils;

    protected static Connection conn;

    CommonDAO() {
        sqlUtils = SQLUtils.getInstance();
        conn = sqlUtils.getOwnConnection();
    }

    @NotNull
    public final List<T> getBeansFromDB(@NotNull T leader) throws IllegalAccessException, SQLException, InstantiationException {
        List<T> list = sqlUtils.getBeansFromDB(getTableName(), (Class<T>) leader.getClass());
        list.add(0, leader);
        return list;
    }

    abstract public String getTableName();

    abstract public void createTable(T bean);

    public boolean insertBeanToDB(@NotNull T bean) {
        ArrayList<Field> insertFields = getFields(bean);
        String sql = getInsertSql(bean, insertFields);
        System.out.println(sql);
        return sqlUtils.insertBeanToDB(sql, bean, insertFields);
    }

    @NotNull
    private ArrayList<Field> getFields(@NotNull T bean) {
        Field[] fields = bean.getClass().getDeclaredFields();
        ArrayList<Field> updateFields = new ArrayList<>();
        try {
            for (Field f : fields) {
                boolean flag = f.isAccessible();
                f.setAccessible(true);
                if (f.get(bean) != null) {
                    updateFields.add(f);
                }
                f.setAccessible(flag);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return updateFields;
    }

    private String getInsertSql(@NotNull T bean, @NotNull List<Field> insertFields) {
        StringBuilder sql = new StringBuilder("INSERT INTO ").append(getTableName());
        StringBuilder attributes = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (Field field : insertFields) {
            if (field.equals(bean.getAutoIncreField())) {
                continue;
            }
            attributes.append(field.getName()).append(",");
            values.append("?").append(",");
        }
        attributes.deleteCharAt(attributes.lastIndexOf(","));
        values.deleteCharAt(values.lastIndexOf(","));
        sql.append("(").append(attributes).append(") VALUES(").append(values).append(")");
        return sql.toString();
    }

    public boolean updateBeanInDB(@NotNull T bean) {
        ArrayList<Field> updateFields = getFields(bean);
        String sql = getUpdateSql(bean, updateFields);
        System.out.println(sql);
        return sqlUtils.updateBeanInDB(sql, bean, updateFields);
    }

    private String getUpdateSql(@NotNull T bean, @NotNull List<Field> updateFields) {
        StringBuilder sql = new StringBuilder("UPDATE ").append(getTableName()).append(" SET ");
        for (Field field : updateFields) {
            sql.append(field.getName()).append(" = ?, ");
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        sql.append(" WHERE ")
                .append(bean.getPrimaryKeyField().getName())
                .append(" = ? ");
        return sql.toString();
    }
}
