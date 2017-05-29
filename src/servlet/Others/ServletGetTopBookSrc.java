package servlet.Others;

import service.transferService.CommonTransfer;
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
@WebServlet(name = "ServletGetTopBookSrc", urlPatterns = "/GetTopBookSrc")
public class ServletGetTopBookSrc extends HttpServlet {
    private static final String[] tops = {"a", "b", "c"};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String[] files = UtilsTopImg.changeDownloadSrc(tops, getServletContext().getInitParameter("serverRoot"));
        CommonTransfer.sendMessage(response, files);
    }
}
