package servlet.Message;

import service.message.CommonCommunication;
import service.utils.InfoTopImage;
import utils.UploadUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-25.
 */
@WebServlet(name = "ServletGetTopImageSrc", urlPatterns = "/GetTopImageSrc")
public class ServletGetTopImageSrc extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String[] files = UploadUtils.getDownloadURI(getServletContext().getInitParameter("serverRoot"), InfoTopImage.URIPattern, InfoTopImage.values);
        CommonCommunication.sendMessage(response, files);
    }
}
