package servlet.File;

import service.file.ServiceUploadImage;
import service.message.CommonCommunication;
import service.utils.InfoNewsImage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-29.
 */
@WebServlet(name = "ServletUploadNewsImage", urlPatterns = "/UploadNewsImage")
public class ServletUploadNewsImage extends HttpServlet {
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
        boolean ans = ServiceUploadImage.uploadImage(request, resourceRoot, InfoNewsImage.template, InfoNewsImage.values);
        CommonCommunication.sendMessage(response, ans);
    }
}
