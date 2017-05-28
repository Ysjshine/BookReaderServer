package bean;

import java.lang.reflect.Field;

/**
 * Created by dream on 17-5-1.
 */
public class UserCategory extends CommonBean {
    public Integer UCID;

    public Integer UserID;
    public Integer CategoryID;
    public String CategoryName;

    @Override
    public Field getPrimaryKeyField() {
        try {
            return getClass().getField("UCID");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
