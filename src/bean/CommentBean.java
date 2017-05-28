package bean;

import java.lang.reflect.Field;

/**
 * Created by dream on 17-5-1.
 */
public class CommentBean extends CommonBean {
    public Integer CommentID;
    public Integer BookID;
    public Integer UserID;
    public String nickname;
    public String contents;

    @Override
    public Field getPrimaryKeyField() {
        try {
            return getClass().getField("CommentID");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
