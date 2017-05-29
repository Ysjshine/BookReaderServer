package servlet.Query;

import bean.BookBean;
import service.bean.ManagerBook;
import service.message.CommonCommunication;
import service.utils.UtilsBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dream on 17-5-25.
 */
@WebServlet(name = "ServletQueryBookByType", urlPatterns = "/QueryTypeBook")
public class ServletQueryBookByType extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int type = Integer.parseInt(request.getParameter("type"));
        List<BookBean> books = ManagerBook.queryBookByType(type);
        UtilsBook.changeDownloadUri(books, getServletContext().getInitParameter("serverRoot"));
        CommonCommunication.Transfer(response, books, BookBean.class);
    }
}
