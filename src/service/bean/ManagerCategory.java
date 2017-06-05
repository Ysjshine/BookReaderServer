package service.bean;

import DAO.UserCategoryDAO;
import bean.UserCategory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by dream on 17-5-1.
 */
public class ManagerCategory {

    @NotNull
    static private SQLUtils sqlUtils = SQLUtils.getInstance();

    @Nullable
    private static PreparedStatement delUserCategory = sqlUtils.getPtmt(
            "delete from UserCategories where UserID = ? and CategoryID = ?");

    public static boolean addCategory(int UserID, String CategoryName) {
        UserCategoryDAO userCategoryDAO = UserCategoryDAO.getInstance();
        UserCategory userCategory = new UserCategory();
        userCategory.UserID = UserID;
        userCategory.CategoryID = userCategoryDAO.getMaxCount(UserID) + 1;
        userCategory.CategoryName = CategoryName;
        return userCategoryDAO.insertBeanToDB(userCategory);
    }

    public static boolean delCategory(int uid, int cid) {
        try {
            delUserCategory.setObject(1, uid);
            delUserCategory.setObject(2, cid);
            return delUserCategory.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}