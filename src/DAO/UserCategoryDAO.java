package DAO;

import bean.UserCategory;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dream on 17-5-1.
 */
public class UserCategoryDAO extends CommonDAO<UserCategory> {

    private static final String tableName = "UserCategories";

    private static final UserCategoryDAO userCategoryDAO = new UserCategoryDAO();

    private UserCategoryDAO() {
    }

    @NotNull
    static public UserCategoryDAO getInstance() {
        return userCategoryDAO;
    }

    @NotNull
    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void createTable(UserCategory bean) {

    }

    public int getMaxCount(int UserID) {
        PreparedStatement stmt = sqlUtils.getPtmt("select max(CategoryID) from " + tableName + " where UserID = ?");
        ResultSet resultSet = null;
        try {
            stmt.setObject(1, UserID);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
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
}
