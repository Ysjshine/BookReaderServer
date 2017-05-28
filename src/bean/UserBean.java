package bean;

import java.lang.reflect.Field;

/**
 * Created by dream on 17-5-1.
 */
public class UserBean extends CommonBean {

    public Integer UserID;
    public String account;

    public String password;

    public String nickname;

    public Integer gender;

    @Override
    public Field getPrimaryKeyField() {
        try {
            return getClass().getField("UserID");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
