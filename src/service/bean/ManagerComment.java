package service.bean;

import DAO.CommentDAO;
import bean.CommentBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dream on 17-5-20.
 */
public class ManagerComment {

    @NotNull
    private static SQLUtils sqlUtils = SQLUtils.getInstance();

    @NotNull
    private static CommentDAO commentDAO = CommentDAO.getInstance();

    @Nullable
    private static PreparedStatement delCommentSQL = sqlUtils.getPtmt(
            "delete from Comments where CommentID = ? and UserID = ?");
    @Nullable
    private static PreparedStatement queryCommentSQL = sqlUtils.getPtmt(
            "select nickname,temp.* from " +
                    "(select * from Comments where BookID = ?) as temp join Users on temp.UserID = Users.UserID order by CommentID desc");


    public static boolean addComment(int bid, int uid, String contents) {
        CommentBean commentBean = new CommentBean();
        commentBean.BookID = bid;
        commentBean.UserID = uid;
        commentBean.contents = contents;
        return commentDAO.insertBeanToDB(commentBean);
    }

    public static boolean delComment(int coid, int uid) {
        try {
            delCommentSQL.setObject(1, coid);
            delCommentSQL.setObject(2, uid);
            return delCommentSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Nullable
    public static List<CommentBean> queryComment(int bid) {
        try {
            queryCommentSQL.setObject(1, bid);
            return sqlUtils.getBeansFromSQL(queryCommentSQL, CommentBean.class);
        } catch (@NotNull SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

}
