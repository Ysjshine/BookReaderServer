package bean;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by dream on 17-3-18.
 */
public abstract class CommonBean implements Serializable {

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Class<?> clazz = this.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            try {
                str.append(field.getName()).append(" = ").append(field.get(this)).append(" ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return str.toString();
    }

    public Field getAutoIncreField() {
        return getPrimaryKeyField();
    }

    abstract public Field getPrimaryKeyField();
}
