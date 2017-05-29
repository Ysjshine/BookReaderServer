package service.bean;

import DAO.CategoryBookDAO;
import bean.BookBean;
import bean.CategoryBook;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dream on 17-5-25.
 */
public class ManagerCategoryBook {
    static private SQLUtils sqlUtils = SQLUtils.getInstance();

    private static PreparedStatement delCategoryBookSQL = sqlUtils.getPtmt(
            "delete from CategoryBooks where UserID = ? and CategoryID = ? and BookID = ?");

    private static PreparedStatement queryCategoryBookSQL = sqlUtils.getPtmt(
            "select Books.* " +
                    "from Books join (select BookID from CategoryBooks where UserID = ? and CategoryID = ?) as tempBooks " +
                    "on Books.BookID = tempBooks.BookID");

    public static boolean addCategoryBook(int uid, int cid, int bid) {
        CategoryBook categoryBook = new CategoryBook();
        categoryBook.UserID = uid;
        categoryBook.CategoryID = cid;
        categoryBook.BookID = bid;
        return CategoryBookDAO.getInstance().insertBeanToDB(categoryBook);
    }

    public static boolean delCategoryBook(int uid, int cid, int bid) {
        boolean ans = false;
        try {
            delCategoryBookSQL.setObject(1, uid);
            delCategoryBookSQL.setObject(2, cid);
            delCategoryBookSQL.setObject(3, bid);
            ans = delCategoryBookSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static List<BookBean> queryCategoryBook(int uid, int cid) {
        ResultSet rs = null;
        List<BookBean> bookBeans = null;
        try {
            queryCategoryBookSQL.setObject(1, uid);
            queryCategoryBookSQL.setObject(2, cid);
            rs = queryCategoryBookSQL.executeQuery();
            bookBeans = sqlUtils.getBeansFromResultSet(rs, BookBean.class);
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
        return bookBeans;
    }
}
