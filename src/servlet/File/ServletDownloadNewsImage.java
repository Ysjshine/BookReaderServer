package servlet.File;

import service.file.ServiceDownload;
import service.utils.UtilsNewsImg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by dream on 17-5-29.
 */
@WebServlet(name = "ServletDownloadNewsImage", urlPatterns = "/NewsImage")
public class ServletDownloadNewsImage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        File file = new File(getServletContext().getInitParameter("resourceRoot"), UtilsNewsImg.fillNewsTemplate(id));
        String contentType = getServletContext().getMimeType(file.getAbsolutePath());
        ServiceDownload.downloadFile(response, file, contentType);
    }
}
