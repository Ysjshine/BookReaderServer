package service.file;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.UploadUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by dream on 17-5-29.
 */
public class ServiceUploadImage {

    public static boolean uploadImage(HttpServletRequest request, String resRoot, String template, String[] values) {
        boolean ans = false;
        try {
            List<FileItem> fileItems = getItems(request, resRoot, values);
            for (FileItem fileItem : fileItems) {
                if (fileItem != null) {
                    File temp = UploadUtils.getRealSrc(resRoot, template, fileItem.getFieldName());
                    System.out.println(temp.toString());
                    if (temp.exists()) temp.delete();
                    fileItem.write(temp);
                }
            }
            ans = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    private static List<FileItem> getItems(HttpServletRequest request, String resRoot, String[] values) throws UnsupportedEncodingException, FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory(20 * 1024 * 1024, new File(resRoot, "temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        return upload.parseRequest(request);
    }
}
