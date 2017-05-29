package servlet.Message;

import service.message.CommonCommunication;
import service.utils.UtilsTopImg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-25.
 */
@WebServlet(name = "ServletGetTopImageSrc", urlPatterns = "/GetTopBookSrc")
public class ServletGetTopImageSrc extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String[] files = UtilsTopImg.changeDownloadSrc(getServletContext().getInitParameter("serverRoot"));
        CommonCommunication.sendMessage(response, files);
    }
}
