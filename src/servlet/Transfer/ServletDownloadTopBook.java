package servlet.Transfer;

import service.OtherService.UtilsTopImg;
import service.TransferService.ServiceDownload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by dream on 17-5-25.
 */
@WebServlet(name = "ServletDownloadTopBook", urlPatterns = "/TopBook")
public class ServletDownloadTopBook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        File src = new File(getServletContext().getInitParameter("resourceRoot"), UtilsTopImg.fillTopTemplate(id));
        String contentType = getServletContext().getMimeType(src.getAbsolutePath());
        ServiceDownload.downloadFile(response, src, contentType);
    }
}
