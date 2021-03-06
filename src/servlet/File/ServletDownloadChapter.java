package servlet.File;

import service.message.CommonCommunication;
import service.other.ServiceEpub;
import service.utils.UtilsBook;

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
@WebServlet(name = "ServletDownloadChapter", urlPatterns = "/Chapter")
public class ServletDownloadChapter extends HttpServlet {
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
        int bid = Integer.parseInt(request.getParameter("bid"));
        int chapter = Integer.parseInt(request.getParameter("chapter"));
        File fileSrc = UtilsBook.getRealSrc(resourceRoot, bid, 0);
        String contents = ServiceEpub.getContents(fileSrc, chapter);
        CommonCommunication.sendMessage(response, contents);
    }
}
