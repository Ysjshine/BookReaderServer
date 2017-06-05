package DAO;

import bean.BookBean;
import org.jetbrains.annotations.NotNull;

/**
 * Created by dream on 17-5-1.
 */
public class BookDAO extends CommonDAO<BookBean> {

    private static final String tableName = "Books";

    private static final BookDAO bookDAO = new BookDAO();

    private BookDAO() {
    }

    @NotNull
    static public BookDAO getInstance() {
        return bookDAO;
    }

    @NotNull
    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void createTable(BookBean bean) {

    }
}
