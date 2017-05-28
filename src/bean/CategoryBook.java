package bean;

import java.lang.reflect.Field;

/**
 * Created by dream on 17-5-1.
 */
public class CategoryBook extends CommonBean {
    public Integer CBID;
    public Integer UserID;
    public Integer CategoryID;
    public Integer BookID;

    @Override
    public Field getPrimaryKeyField() {
        try {
            return getClass().getField("CBID");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
