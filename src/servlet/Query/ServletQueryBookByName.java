package servlet.Query;

import bean.BookBean;
import service.BeanService.ServiceBook;
import service.OtherService.UtilsBook;
import service.TransferService.TransferCommonBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dream on 17-5-12.
 */
@WebServlet(name = "ServletQueryBookByName", urlPatterns = "/QueryBook")
public class ServletQueryBookByName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String bookName = request.getParameter("bookName");
        List<BookBean> bookBeans = ServiceBook.queryBookByName(bookName);
        UtilsBook.changeDownloadUri(bookBeans, getServletContext().getInitParameter("serverRoot"));
        TransferCommonBean.Transfer(response, bookBeans, BookBean.class);
    }
}
