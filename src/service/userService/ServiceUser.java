package service.userService;

import DAO.UserCategoryDAO;
import DAO.UserDAO;
import bean.UserBean;
import bean.UserCategory;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dream on 17-5-1.
 */
public class ServiceUser {

    private static SQLUtils sqlUtils = SQLUtils.getInstance();

    private static UserDAO userDAO = UserDAO.getInstance();

    private static UserCategoryDAO userCategoryDAO = UserCategoryDAO.getInstance();

    private static PreparedStatement loginSQL = sqlUtils.getPtmt("select * from Users where account = ? and password = ?");

    private static PreparedStatement userExistsSQL = sqlUtils.getPtmt("select * from Users where account = ?");

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

    public static boolean register(String account, String password, String gender) {
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
            userCategory.CategoryName = "默认";
            userCategoryDAO.insertBeanToDB(userCategory);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static UserBean getInfo(String username) {
        ResultSet rs = null;
        UserBean userBean = null;
        try {
            getInfoSQL.setObject(1, username);
            rs = getInfoSQL.executeQuery();
            userBean = sqlUtils.getBeansFromResultSet(rs, UserBean.class).get(0);
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
        return userBean;
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