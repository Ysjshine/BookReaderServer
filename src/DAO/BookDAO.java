package DAO;

import bean.BookBean;

/**
 * Created by dream on 17-5-1.
 */
public class BookDAO extends CommonDAO<BookBean> {

    private static final String tableName = "Books";

    private static final BookDAO bookDAO = new BookDAO();

    private BookDAO() {
    }

    static public BookDAO getInstance() {
        return bookDAO;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void createTable(BookBean bean) {

    }
}
