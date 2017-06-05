package DAO;

import bean.CommentBean;
import org.jetbrains.annotations.NotNull;

/**
 * Created by dream on 17-5-1.
 */
public class CommentDAO extends CommonDAO<CommentBean> {

    private static final String tableName = "Comments";

    private static final CommentDAO commentDAO = new CommentDAO();

    private CommentDAO() {
    }

    @NotNull
    static public CommentDAO getInstance() {
        return commentDAO;
    }

    @NotNull
    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void createTable(CommentBean bean) {

    }
}
