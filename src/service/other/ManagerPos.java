package service.other;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import service.utils.PosBean;
import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dream on 17-5-28.
 */
public class ManagerPos {
    @NotNull
    private static SQLUtils sqlUtils = SQLUtils.getInstance();

    @Nullable
    private static PreparedStatement updateSQL = sqlUtils.getPtmt(
            "insert into ReadPositions(UserID, BookID, chapter, page) values(?,?,?,?) " +
                    "ON DUPLICATE KEY UPDATE chapter = ?,page = ?");

    @Nullable
    private static PreparedStatement delPosSQL = sqlUtils.getPtmt(
            "delete from ReadPositions where UserID = ? and BookID = ?");

    @Nullable
    private static PreparedStatement queryPosSQL = sqlUtils.getPtmt(
            "select * from ReadPositions where UserID = ? and BookID = ?");

    public static boolean updatePos(int uid, int bid, int chapter, int page) {
        try {
            updateSQL.setObject(1, uid);
            updateSQL.setObject(2, bid);
            updateSQL.setObject(3, chapter);
            updateSQL.setObject(4, page);
            updateSQL.setObject(5, chapter);
            updateSQL.setObject(6, page);
            return updateSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delPos(int uid, int bid) {
        try {
            delPosSQL.setObject(1, uid);
            delPosSQL.setObject(2, bid);
            return delPosSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Nullable
    public static List<PosBean> queryPos(int uid, int bid) {
        try {
            queryPosSQL.setObject(1, uid);
            queryPosSQL.setObject(2, bid);
            return sqlUtils.getBeansFromSQL(queryPosSQL, PosBean.class);
        } catch (@NotNull SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
