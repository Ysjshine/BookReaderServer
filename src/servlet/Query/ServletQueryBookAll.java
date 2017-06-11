package servlet.Query;

import bean.BookBean;
import service.bean.ManagerBook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dream on 17-6-11.
 */
@WebServlet(name = "ServletQueryBookAll", urlPatterns = "/QueryBookAll")
public class ServletQueryBookAll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<BookBean> bookBeans = ManagerBook.queryBookAll();
        request.setAttribute("bookBeans", bookBeans);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("showBooks.jsp");
        requestDispatcher.forward(request, response);
    }
}
