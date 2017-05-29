package servlet.Message;

import service.message.ServiceTransPos;
import service.other.ManagerPos;
import service.other.ServiceEpub;
import service.utils.PosBean;
import service.utils.UtilsBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by dream on 17-5-28.
 */
@WebServlet(name = "ServletGetPos", urlPatterns = "/QueryPos")
public class ServletGetPos extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int uid = Integer.parseInt(request.getParameter("uid"));
        int bid = Integer.parseInt(request.getParameter("bid"));
        List<PosBean> posBeans = ManagerPos.queryPos(uid, bid);
        File file = new File(getServletContext().getInitParameter("resourceRoot"), UtilsBook.fillFileSrc(bid)[0]);
        ServiceTransPos.Transfer(response, posBeans, ServiceEpub.getChapter(file));
    }
}
