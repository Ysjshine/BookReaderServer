package servlet.Manager;

import service.beanService.ServiceCategory;
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
@WebServlet(name = "ServletAddCategory", urlPatterns = "/AddCategory")
public class ServletAddCategory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String categoryName = request.getParameter("categoryName");
        int uid = Integer.parseInt(request.getParameter("uid"));
        boolean ans = ServiceCategory.addCategory(uid, categoryName);
        CommonTransfer.sendMessage(response, ans);
    }
}
