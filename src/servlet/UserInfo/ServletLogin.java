package servlet.UserInfo;

import service.TransferService.TransferCommonBean;
import service.UserService.ServiceUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-1.
 */
@WebServlet(name = "ServletLogin", urlPatterns = "/Login")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String account = request.getParameter("username");
        String password = request.getParameter("password");
        boolean ans = ServiceUser.login(account, password);
        TransferCommonBean.sendMessage(response, ans);
    }
}
