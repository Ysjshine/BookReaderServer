package servlet.Query;

import bean.BookBean;
import service.beanService.ServiceCategoryBook;
import service.transferService.CommonTransfer;
import service.utils.UtilsBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dream on 17-5-17.
 */
@WebServlet(name = "ServletQueryCategoryBook", urlPatterns = "/QueryCB")
public class ServletQueryCategoryBook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        int cid = Integer.parseInt(request.getParameter("cid"));
        List<BookBean> bookBeans = ServiceCategoryBook.queryCategoryBook(uid, cid);
        UtilsBook.changeDownloadUri(bookBeans, getServletContext().getInitParameter("serverRoot"));
        CommonTransfer.Transfer(response, bookBeans, BookBean.class);
    }
}
