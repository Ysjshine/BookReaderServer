package DAO;

import bean.UserBean;

/**
 * Created by dream on 17-5-1.
 */
public class UserDAO extends CommonDAO<UserBean> {

    private static final String tableName = "Users";

    private static final UserDAO userDAO = new UserDAO();

    private UserDAO() {
    }

    static public UserDAO getInstance() {
        return userDAO;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void createTable(UserBean bean) {

    }
}
