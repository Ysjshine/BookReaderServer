package service.otherService;

import org.jetbrains.annotations.Nullable;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dream on 17-5-28.
 */
public class ServicePos {
    private static SQLUtils sqlUtils = SQLUtils.getInstance();

    private static PreparedStatement updateSQL = sqlUtils.getPtmt(
            "insert into ReadPositions(UserID, BookID, chapter, page) values(?,?,?,?) " +
                    "ON DUPLICATE KEY UPDATE chapter = ?,page = ?");

    private static PreparedStatement delPosSQL = sqlUtils.getPtmt(
            "delete from ReadPositions where UserID = ? and BookID = ?");

    private static PreparedStatement queryPosSQL = sqlUtils.getPtmt(
            "select * from ReadPositions where UserID = ? and BookID = ?");

    public static boolean updatePos(int uid, int bid, int chapter, int page) {
        boolean ans = false;
        try {
            updateSQL.setObject(1, uid);
            updateSQL.setObject(2, bid);
            updateSQL.setObject(3, chapter);
            updateSQL.setObject(4, page);
            updateSQL.setObject(5, chapter);
            updateSQL.setObject(6, page);
            ans = updateSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static boolean delPos(int uid, int bid) {
        boolean ans = false;
        try {
            delPosSQL.setObject(1, uid);
            delPosSQL.setObject(2, bid);
            ans = delPosSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    @Nullable
    public static List<PosBean> queryPos(int uid, int bid) {
        ResultSet rs = null;
        List<PosBean> posBeans = null;
        try {
            queryPosSQL.setObject(1, uid);
            queryPosSQL.setObject(2, bid);
            rs = queryPosSQL.executeQuery();
            posBeans = sqlUtils.getBeansFromResultSet(rs, PosBean.class);
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
        return posBeans;
    }
}
