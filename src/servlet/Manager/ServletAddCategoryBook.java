package servlet.Manager;

import service.beanService.ServiceCategoryBook;
import service.transferService.CommonTransfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-17.
 */
@WebServlet(name = "ServletAddCategoryBook", urlPatterns = "/CollectBook")
public class ServletAddCategoryBook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        int cid = Integer.parseInt(request.getParameter("cid"));
        int bid = Integer.parseInt(request.getParameter("bid"));
        boolean ans = ServiceCategoryBook.addCategoryBook(uid, cid, bid);
        CommonTransfer.sendMessage(response, ans);
    }
}
