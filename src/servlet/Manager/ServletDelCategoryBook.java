package servlet.Manager;

import service.bean.ManagerCategoryBook;
import service.message.CommonCommunication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-20.
 */
@WebServlet(name = "ServletDelCategoryBook", urlPatterns = "/UnCollectBook")
public class ServletDelCategoryBook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int uid = Integer.parseInt(request.getParameter("uid"));
        int cid = Integer.parseInt(request.getParameter("cid"));
        int bid = Integer.parseInt(request.getParameter("bid"));
        boolean ans = ManagerCategoryBook.delCategoryBook(uid, cid, bid);
        CommonCommunication.sendMessage(response, ans);
    }
}
