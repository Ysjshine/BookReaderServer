package service.bean;

import bean.UserBean;
import bean.UserCategory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import service.user.ServiceUser;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dream on 17-5-25.
 */
public class ManagerUserCategory {
    @NotNull
    static private SQLUtils sqlUtils = SQLUtils.getInstance();
    @Nullable
    private static PreparedStatement queryUserCategorySQL = sqlUtils.getPtmt(
            "select * from UserCategories where UserID = ?");

    @Nullable
    public static List<UserCategory> queryUserCategory(String username) {
        UserBean userBean = ServiceUser.getInfo(username);
        if (userBean == null) {
            return null;
        }
        try {
            queryUserCategorySQL.setObject(1, userBean.UserID);
            return sqlUtils.getBeansFromSQL(queryUserCategorySQL, UserCategory.class);
        } catch (@NotNull SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
