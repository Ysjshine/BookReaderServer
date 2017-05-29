package servlet.Query;

import bean.CommentBean;
import service.bean.ManagerComment;
import service.message.CommonCommunication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dream on 17-5-21.
 */
@WebServlet(name = "ServletQueryComment", urlPatterns = "/QueryComment")
public class ServletQueryComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bid = Integer.parseInt(request.getParameter("bid"));
        List<CommentBean> beans = ManagerComment.queryComment(bid);
        CommonCommunication.Transfer(response, beans, CommentBean.class);
    }
}
