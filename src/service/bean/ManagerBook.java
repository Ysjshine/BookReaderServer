package service.bean;

import DAO.BookDAO;
import bean.BookBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dream on 17-5-1.
 */
public class ManagerBook {

    @NotNull
    private static SQLUtils sqlUtils = SQLUtils.getInstance();

    @Nullable
    private static PreparedStatement queryBookByNameSQL = sqlUtils.getPtmt(
            "select * from Books where title like ?");

    @Nullable
    private static PreparedStatement queryBookByTopSQL = sqlUtils.getPtmt(
            "SELECT * FROM Books ORDER BY collectTimes DESC LIMIT 0,3");

    @Nullable
    private static PreparedStatement queryBookByTypeSQL = sqlUtils.getPtmt(
            "select * from Books where type = ?");

    @Nullable
    private static PreparedStatement delBookSQL = sqlUtils.getPtmt(
            "delete from Books where BookID = ?");

    @Nullable
    public static List<BookBean> queryBookByName(String bookName) {
        try {
            queryBookByNameSQL.setObject(1, "%" + bookName + "%");
            return sqlUtils.getBeansFromSQL(queryBookByNameSQL, BookBean.class);
        } catch (@NotNull SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static List<BookBean> queryBookByTop() {
        try {
            return sqlUtils.getBeansFromSQL(queryBookByTopSQL, BookBean.class);
        } catch (@NotNull SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static List<BookBean> queryBookByType(int type) {
        try {
            queryBookByTypeSQL.setObject(1, type);
            return sqlUtils.getBeansFromSQL(queryBookByTypeSQL, BookBean.class);
        } catch (@NotNull SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static List<BookBean> queryBookAll() {
        try {
            List<BookBean> beansFromDB = BookDAO.getInstance().getBeansFromDB(new BookBean());
            beansFromDB.remove(0);
            return beansFromDB;
        } catch (IllegalAccessException | SQLException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean delBook(int bid) {
        try {
            delBookSQL.setObject(1, bid);
            return delBookSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
