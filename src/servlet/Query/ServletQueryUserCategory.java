package servlet.Query;

import bean.UserCategory;
import service.BeanService.ServiceUserCategory;
import service.TransferService.TransferCommonBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-17.
 */
@WebServlet(name = "ServletQueryUserCategory", urlPatterns = "/QueryUC")
public class ServletQueryUserCategory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        try {
            TransferCommonBean.Transfer(response, ServiceUserCategory.queryUserCategory(username), UserCategory.class);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
