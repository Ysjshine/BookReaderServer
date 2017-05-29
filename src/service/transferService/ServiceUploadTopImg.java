package service.transferService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.utils.UtilsTopImg;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by dream on 17-5-25.
 */
public class ServiceUploadTopImg {
    public static boolean uploadTopImg(HttpServletRequest request, String resRoot) {
        boolean ans = false;
        String[] names = new String[]{"a", "b", "c"};
        try {
            FileItem[] fileItems = getItems(request, resRoot);
            for (int i = 0; i <= 2; i++) {
                if (fileItems[i] != null) {
                    File temp = new File(resRoot, UtilsTopImg.fillTopTemplate(names[i]));
                    if (temp.exists()) temp.delete();
                    fileItems[i].write(temp);
                }
            }
            ans = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    private static FileItem[] getItems(HttpServletRequest request, String resRoot) throws UnsupportedEncodingException, FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory(20 * 1024 * 1024, new File(resRoot, "temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);

        FileItem[] ans = new FileItem[3];
        for (FileItem item : items) {
            if (!item.isFormField()) {
                if (item.getFieldName().equals("a")) {
                    ans[0] = item;
                    continue;
                }
                if (item.getFieldName().equals("b")) {
                    ans[1] = item;
                    continue;
                }
                if (item.getFieldName().equals("c")) {
                    ans[2] = item;
                    continue;
                }
            }
        }
        return ans;
    }
}
