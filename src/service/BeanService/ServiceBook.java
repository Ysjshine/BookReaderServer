package service.BeanService;

import bean.BookBean;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dream on 17-5-1.
 */
public class ServiceBook {

    private static SQLUtils sqlUtils = SQLUtils.getInstance();

    private static PreparedStatement queryBookByNameSQL = sqlUtils.getPtmt(
            "select * from Books where title like ?");

    private static PreparedStatement queryBookByTopSQL = sqlUtils.getPtmt(
            "SELECT * FROM Books ORDER BY collectTimes DESC LIMIT 0,3");

    private static PreparedStatement queryBookByTypeSQL = sqlUtils.getPtmt(
            "select * from Books where type = ?");

    public static List<BookBean> queryBookByName(String bookName) {
        ResultSet rs = null;
        List<BookBean> bookBeans = null;
        try {
            queryBookByNameSQL.setObject(1, "%" + bookName + "%");
            rs = queryBookByNameSQL.executeQuery();
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

    public static List<BookBean> queryBookByTop() {
        List<BookBean> books = null;
        ResultSet rs = null;
        try {
            rs = queryBookByTopSQL.executeQuery();
            books = sqlUtils.getBeansFromResultSet(rs, BookBean.class);
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
        return books;
    }

    public static List<BookBean> queryBookByType(int type) {
        List<BookBean> bookBeans = null;
        ResultSet rs = null;
        try {
            queryBookByTypeSQL.setObject(1, type);
            rs = queryBookByTypeSQL.executeQuery();
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
