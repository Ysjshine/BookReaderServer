package servlet.Transfer;

import service.otherService.ConvertService;
import service.transferService.CommonTransfer;
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
@WebServlet(name = "ServletDownloadChapter", urlPatterns = "/DownloadChapter")
public class ServletDownloadChapter extends HttpServlet {
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
        int bid = Integer.parseInt(request.getParameter("bid"));
        int chapter = Integer.parseInt(request.getParameter("chapter"));
        File fileSrc = new File(resRoot, UtilsBook.fillFileSrc(bid)[0]);
        String contents = ConvertService.getContents(fileSrc, chapter);
        CommonTransfer.sendMessage(response, contents);
    }
}
