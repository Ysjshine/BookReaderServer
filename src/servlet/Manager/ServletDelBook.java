package servlet.Manager;

import service.bean.ManagerBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-6-11.
 */
@WebServlet(name = "ServletDelBook", urlPatterns = "/DelBook")
public class ServletDelBook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int bid = Integer.parseInt(request.getParameter("bid"));
        boolean ans = ManagerBook.delBook(bid);
        response.sendRedirect("/QueryBookAll");
    }
}
