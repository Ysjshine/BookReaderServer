package servlet.File;

import service.file.ServiceDownload;
import service.utils.UtilsBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by dream on 17-5-20.
 */
@WebServlet(name = "ServletDownloadFile", urlPatterns = "/File")
public class ServletDownloadFile extends HttpServlet {
    private static String resourceRoot;

    @Override
    public void init() throws ServletException {
        super.init();
        resourceRoot = getServletContext().getInitParameter("resourceRoot");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        File fileSrc = UtilsBook.getRealSrc(resourceRoot, id, 0);
        String contentType = getServletContext().getMimeType(fileSrc.getAbsolutePath());
        ServiceDownload.downloadFile(response, fileSrc, contentType);
    }
}
