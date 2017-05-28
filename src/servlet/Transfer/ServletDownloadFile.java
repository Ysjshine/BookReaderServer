package servlet.Transfer;

import service.OtherService.UtilsBook;
import service.TransferService.ServiceDownload;

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
    private File resRoot;

    @Override
    public void init() {
        resRoot = new File(getServletContext().getInitParameter("resourceRoot"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        File fileSrc = new File(resRoot, UtilsBook.fillFileSrc(id)[0]);
        String contentType = getServletContext().getMimeType(fileSrc.getAbsolutePath());
        ServiceDownload.downloadFile(response, fileSrc, contentType);
    }
}
