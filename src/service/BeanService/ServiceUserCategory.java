package service.BeanService;

import bean.UserBean;
import bean.UserCategory;
import service.UserService.ServiceUser;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dream on 17-5-25.
 */
public class ServiceUserCategory {
    static private SQLUtils sqlUtils = SQLUtils.getInstance();
    private static PreparedStatement queryUserCategorySQL = sqlUtils.getPtmt(
            "select * from UserCategories where UserID = ?");

    public static List<UserCategory> queryUserCategory(String username) {
        UserBean userBean = ServiceUser.getInfo(username);
        if (userBean == null) {
            return null;
        }
        ResultSet rs = null;
        List<UserCategory> categories = null;
        try {
            queryUserCategorySQL.setObject(1, userBean.UserID);
            rs = queryUserCategorySQL.executeQuery();
            categories = sqlUtils.getBeansFromResultSet(rs, UserCategory.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return categories;
    }
}
