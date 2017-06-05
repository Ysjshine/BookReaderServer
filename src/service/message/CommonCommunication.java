package service.message;

import bean.CommonBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by dream on 17-5-12.
 */
public class CommonCommunication {
    public static void Transfer(@NotNull HttpServletResponse response, @Nullable List<? extends CommonBean> beans, @NotNull Class clazz) throws IOException {
        response.setContentType("application/octet-stream");
        OutputStream outputStream = response.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        if (beans != null) {
            for (Object b : beans) {
                oos.writeObject(clazz.cast(b));
            }
        }
        oos.writeObject(null);
        outputStream.close();
    }

    public static void Transfer(@NotNull HttpServletResponse response, CommonBean bean) throws IOException {
        response.setContentType("application/octet-stream");
        OutputStream outputStream = response.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(bean);
        oos.writeObject(null);
        outputStream.close();
    }

    public static void sendMessage(@NotNull HttpServletResponse response, boolean ans) throws IOException {
        response.setContentType("application/octet-stream");
        OutputStream outputStream = response.getOutputStream();
        String s;
        if (ans) {
            s = "1";
        } else {
            s = "0";
        }
        outputStream.write(s.getBytes());
        outputStream.close();
    }

    public static void sendMessage(@NotNull HttpServletResponse response, @NotNull String str) throws IOException {
//        response.setContentType("application/octet-stream");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(str.getBytes());
        outputStream.close();
    }

    public static void sendMessage(@NotNull HttpServletResponse response, @NotNull String[] strings) throws IOException {
        response.setContentType("application/octet-stream");
        OutputStream outputStream = response.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        for (String s : strings) {
            objectOutputStream.writeObject(s);
        }
        objectOutputStream.writeObject(null);
        objectOutputStream.close();
    }
}
