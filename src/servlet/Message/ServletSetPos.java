package servlet.Message;

import service.message.CommonCommunication;
import service.other.ManagerPos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-28.
 */
@WebServlet(name = "ServletSetPos", urlPatterns = "/UpdatePos")
public class ServletSetPos extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int uid = Integer.parseInt(request.getParameter("uid"));
        int bid = Integer.parseInt(request.getParameter("bid"));
        int chapter = Integer.parseInt(request.getParameter("chapter"));
        int page = Integer.parseInt(request.getParameter("page"));
        boolean ans = ManagerPos.updatePos(uid, bid, chapter, page);
        CommonCommunication.sendMessage(response, ans);
    }
}
