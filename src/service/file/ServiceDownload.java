package service.file;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by dream on 17-5-25.
 */
public class ServiceDownload {
    public static void downloadFile(HttpServletResponse response, File f, String contentType) {
        if (f.exists()) {
            if (contentType == null)
                contentType = "application/octet-stream";
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=" + f.getName());
            BufferedInputStream is = null;
            BufferedOutputStream os = null;
            try {
                is = new BufferedInputStream(new FileInputStream(f.getAbsolutePath()));
                os = new BufferedOutputStream(response.getOutputStream());
                byte[] buffer = new byte[4 * 1024]; //4k Buffer
                int read;
                while ((read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
