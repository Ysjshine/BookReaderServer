package bean;

import java.lang.reflect.Field;

/**
 * Created by dream on 17-5-1.
 */
public class BookBean extends CommonBean {
    public Integer BookID;
    public String title;
    public String author;
    public Float price;
    public Integer collectTimes;
    public Integer readTimes;
    public String fileSource;
    public String imgSource;
    public Integer type;

    @Override
    public Field getPrimaryKeyField() {
        try {
            return getClass().getField("BookID");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
