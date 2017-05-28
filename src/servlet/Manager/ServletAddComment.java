package servlet.Manager;

import service.BeanService.ServiceComment;
import service.TransferService.TransferCommonBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-20.
 */
@WebServlet(name = "ServletAddComment", urlPatterns = "/AddComment")
public class ServletAddComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int uid = Integer.parseInt(request.getParameter("uid"));
        int bid = Integer.parseInt(request.getParameter("bid"));
        String contents = request.getParameter("contents");
        boolean ans = ServiceComment.addComment(bid, uid, contents);
        TransferCommonBean.sendMessage(response, ans);
    }
}
