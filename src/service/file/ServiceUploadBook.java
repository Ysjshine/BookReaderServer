package service.file;

import DAO.BookDAO;
import bean.BookBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.utils.UtilsBook;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by dream on 17-5-25.
 */
public class ServiceUploadBook {

    public static boolean uploadBook(HttpServletRequest request, String resRoot) {
        boolean ans = false;
        try {
            BookBean book = new BookBean();
            FileItem[] bookFileItems = setInfo(request, resRoot, book);
            if (bookFileItems[0] != null && bookFileItems[1] != null) {
                BookDAO.getInstance().insertBeanToDB(book);
                String[] trueSources = UtilsBook.fillFileSrc(book.BookID);
                bookFileItems[0].write(new File(resRoot, trueSources[0]));
                bookFileItems[1].write(new File(resRoot, trueSources[1]));
                ans = true;
            }
        } catch (UnsupportedEncodingException | FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    private static FileItem[] setInfo(HttpServletRequest request, String resRoot, BookBean book) throws UnsupportedEncodingException, FileUploadException {
        //request.setCharacterEncoding("UTF-8");在本例中不设置可以成功上传中文文件名的文件，
        //倘若之后发现不行可以尝试
        //至于表单域，需要request getString联合设置
        //fileSizeThreshold 当数据量大于该值时（单位字节），内容将被临时写入到临时目录中repository，但是等到上传完毕后，总会被删除。
        DiskFileItemFactory factory = new DiskFileItemFactory(20 * 1024 * 1024, new File(resRoot, "temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);

        FileItem[] ans = new FileItem[2];
        for (FileItem item : items) {
            if (item.isFormField()) {
                // String name = item.getFieldName();
                // String value = item.getString();
                if (item.getFieldName().equals("Author")) {
                    book.author = item.getString("UTF-8");
                    continue;
                }
                if (item.getFieldName().equals("Title")) {
                    book.title = item.getString("UTF-8");
                    continue;
                }
                if (item.getFieldName().equals("Price")) {
                    book.price = Float.parseFloat(item.getString("UTF-8"));
                    continue;
                }
                if (item.getFieldName().equals("Type")) {
                    book.type = Integer.parseInt(item.getString("UTF-8"));
                    continue;
                }
            } else {
//                String fieldName = item.getFieldName();
//                String fileName = item.getName();
//                String contentType = item.getContentType();//mime
//                boolean isInMemory = item.isInMemory();
//                long sizeInBytes = item.getSize();
//                String fileName = bookFileItem.getName();
//                String bookType = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (item.getFieldName().equals("File")) {
                    ans[0] = item;
                    continue;
                }
                if (item.getFieldName().equals("Image")) {
                    ans[1] = item;
                    continue;
                }
            }
        }
        return ans;
    }
}
