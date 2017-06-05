package service.user;

import DAO.UserCategoryDAO;
import DAO.UserDAO;
import bean.UserBean;
import bean.UserCategory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by dream on 17-5-1.
 */
public class ServiceUser {

    @NotNull
    private static SQLUtils sqlUtils = SQLUtils.getInstance();

    @NotNull
    private static UserDAO userDAO = UserDAO.getInstance();

    @NotNull
    private static UserCategoryDAO userCategoryDAO = UserCategoryDAO.getInstance();

    @Nullable
    private static PreparedStatement loginSQL = sqlUtils.getPtmt("select * from Users where account = ? and password = ?");

    @Nullable
    private static PreparedStatement userExistsSQL = sqlUtils.getPtmt("select * from Users where account = ?");

    @Nullable
    private static PreparedStatement getInfoSQL = sqlUtils.getPtmt("select * from Users where account = ?");

    public static boolean login(String account, String password) {
        try {
            loginSQL.setObject(1, account);
            loginSQL.setObject(2, password);
            return sqlUtils.queryExists(loginSQL);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean register(String account, String password, @NotNull String gender) {
        try {
            userExistsSQL.setObject(1, account);
            if (sqlUtils.queryExists(userExistsSQL)) {
                return false;
            }
            UserBean userBean = new UserBean();
            userBean.account = account;
            userBean.password = password;
            userBean.nickname = account;
            userBean.gender = Integer.parseInt(gender);
            userDAO.insertBeanToDB(userBean);
            UserCategory userCategory = new UserCategory();
            userCategory.UserID = userBean.UserID;
            userCategory.CategoryID = 1;
            userCategory.CategoryName = "我最喜欢的书籍";
            userCategoryDAO.insertBeanToDB(userCategory);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Nullable
    public static UserBean getInfo(String username) {
        try {
            getInfoSQL.setObject(1, username);
            return sqlUtils.getBeansFromSQL(getInfoSQL, UserBean.class).get(0);
        } catch (@NotNull SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateUserInfo(Integer userID, String newNickName, String newPassword, Integer newGender) {
        UserBean userBean = new UserBean();
        userBean.UserID = userID;
        userBean.password = newPassword;
        userBean.nickname = newNickName;
        userBean.gender = newGender;
        System.out.println(userBean);
        UserDAO.getInstance().updateBeanInDB(userBean);
    }

}