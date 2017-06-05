package service.bean;

import DAO.CategoryBookDAO;
import bean.BookBean;
import bean.CategoryBook;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dream on 17-5-25.
 */
public class ManagerCategoryBook {
    @NotNull
    static private SQLUtils sqlUtils = SQLUtils.getInstance();

    @Nullable
    private static PreparedStatement delCategoryBookSQL = sqlUtils.getPtmt(
            "delete from CategoryBooks where UserID = ? and CategoryID = ? and BookID = ?");

    @Nullable
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
        try {
            delCategoryBookSQL.setObject(1, uid);
            delCategoryBookSQL.setObject(2, cid);
            delCategoryBookSQL.setObject(3, bid);
            return delCategoryBookSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Nullable
    public static List<BookBean> queryCategoryBook(int uid, int cid) {
        try {
            queryCategoryBookSQL.setObject(1, uid);
            queryCategoryBookSQL.setObject(2, cid);
            return sqlUtils.getBeansFromSQL(queryCategoryBookSQL, BookBean.class);
        } catch (@NotNull SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
