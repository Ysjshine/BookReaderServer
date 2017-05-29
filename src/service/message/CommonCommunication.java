package service.message;

import bean.CommonBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by dream on 17-5-12.
 */
public class CommonCommunication {
    public static void Transfer(HttpServletResponse response, List<? extends CommonBean> beans, Class clazz) throws IOException {
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

    public static void Transfer(HttpServletResponse response, CommonBean bean) throws IOException {
        response.setContentType("application/octet-stream");
        OutputStream outputStream = response.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(bean);
        oos.writeObject(null);
        outputStream.close();
    }

    public static void sendMessage(HttpServletResponse response, boolean ans) throws IOException {
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

    public static void sendMessage(HttpServletResponse response, String str) throws IOException {
        response.setContentType("application/octet-stream");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(str.getBytes());
        outputStream.close();
    }

    public static void sendMessage(HttpServletResponse response, String[] strings) throws IOException {
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
