package service.OtherService;

import utils.SQLUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by dream on 17-5-25.
 */
public class ServiceReadTimes {
    private static SQLUtils sqlUtils = SQLUtils.getInstance();
    private static PreparedStatement increaseReadTimes = sqlUtils.getPtmt(
            "update Books set readTimes = readTimes + 1 where BookID = ?");

    public boolean IncreaseReadTimes(int bookID) {
        boolean ans = false;
        try {
            increaseReadTimes.setObject(1, bookID);
            ans = increaseReadTimes.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
