package servlet.Manager;

import service.bean.ManagerComment;
import service.message.CommonCommunication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-21.
 */
@WebServlet(name = "ServletDelComment", urlPatterns = "/DelComment")
public class ServletDelComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int coid = Integer.parseInt(request.getParameter("coid"));
        int uid = Integer.parseInt(request.getParameter("uid"));
        boolean ans = ManagerComment.delComment(coid, uid);
        CommonCommunication.sendMessage(response, ans);
    }
}
