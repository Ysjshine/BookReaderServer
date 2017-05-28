package service.BeanService;

import DAO.CommentDAO;
import bean.CommentBean;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dream on 17-5-20.
 */
public class ServiceComment {

    private static SQLUtils sqlUtils = SQLUtils.getInstance();

    private static CommentDAO commentDAO = CommentDAO.getInstance();

    private static PreparedStatement delCommentSQL = sqlUtils.getPtmt(
            "delete from Comments where CommentID = ? and UserID = ?");

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
        boolean ans = false;
        try {
            delCommentSQL.setObject(1, coid);
            delCommentSQL.setObject(2, uid);
            ans = delCommentSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static List<CommentBean> queryComment(int bid) {
        ResultSet rs = null;
        List<CommentBean> beans = null;
        try {
            queryCommentSQL.setObject(1, bid);
            rs = queryCommentSQL.executeQuery();
            beans = sqlUtils.getBeansFromResultSet(rs, CommentBean.class);
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
        return beans;
    }

}
