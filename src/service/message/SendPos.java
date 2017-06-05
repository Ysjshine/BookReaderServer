package service.message;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import service.utils.PosBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by dream on 17-5-28.
 */
public class SendPos {
    public static void send(@NotNull HttpServletResponse response, @Nullable List<PosBean> beans, Integer size) throws IOException {
        response.setContentType("application/octet-stream");
        OutputStream outputStream = response.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        if (beans == null || beans.size() == 0) {
            oos.writeObject(2);
            oos.writeObject(1);
        } else {
            oos.writeObject(beans.get(0).chapter);
            oos.writeObject(beans.get(0).page);
        }
        oos.writeObject(size);
        oos.writeObject(null);
        outputStream.close();
    }
}
