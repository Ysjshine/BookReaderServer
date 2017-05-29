package servlet.File;

import service.file.ServiceDownload;
import service.utils.InfoTopImage;
import utils.UploadUtils;

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
@WebServlet(name = "ServletDownloadTopImage", urlPatterns = "/TopImage")
public class ServletDownloadTopImage extends HttpServlet {
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
        String id = request.getParameter("id");
        File file = UploadUtils.getRealSrc(resourceRoot, InfoTopImage.template, id);
        String contentType = getServletContext().getMimeType(file.getAbsolutePath());
        ServiceDownload.downloadFile(response, file, contentType);
    }
}
