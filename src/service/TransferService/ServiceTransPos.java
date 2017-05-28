package service.TransferService;

import org.jetbrains.annotations.Nullable;
import service.OtherService.PosBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by dream on 17-5-28.
 */
public class ServiceTransPos {
    public static void Transfer(HttpServletResponse response, @Nullable List<PosBean> beans) throws IOException {
        response.setContentType("application/octet-stream");
        OutputStream outputStream = response.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        if (beans == null || beans.size() == 0) {
            oos.writeObject(0);
            oos.writeObject(0);
        } else {
            oos.writeObject(beans.get(0).chapter);
            oos.writeObject(beans.get(0).page);
        }
        oos.writeObject(null);
        outputStream.close();
    }
}
