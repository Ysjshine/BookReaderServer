package servlet.UserInfo;

import service.message.CommonCommunication;
import service.user.ServiceUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-16.
 */
@WebServlet(name = "ServletGetUserInfo", urlPatterns = "/SendUserInfo")
public class ServletGetUserInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        CommonCommunication.Transfer(response, ServiceUser.getInfo(username));
    }
}
