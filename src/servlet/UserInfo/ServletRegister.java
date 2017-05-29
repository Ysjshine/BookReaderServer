package servlet.UserInfo;

import service.transferService.CommonTransfer;
import service.userService.ServiceUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-1.
 */
@WebServlet(name = "ServletRegister", urlPatterns = "/Register")
public class ServletRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String account = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        boolean ans = ServiceUser.register(account, password, gender);
        CommonTransfer.sendMessage(response, ans);
    }
}
