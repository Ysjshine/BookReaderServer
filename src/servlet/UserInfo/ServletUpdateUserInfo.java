package servlet.UserInfo;

import service.UserService.ServiceUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-16.
 */
@WebServlet(name = "ServletUpdateUserInfo", urlPatterns = "/UserInfo")
public class ServletUpdateUserInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Integer userID = null;
        String newNickName = null;
        String newPassword = null;
        Integer newGender = null;
        try {
            userID = Integer.valueOf(request.getParameter("uid"));
        } catch (NumberFormatException ignored) {
        }
        try {
            newNickName = request.getParameter("newNickname");
        } catch (StringIndexOutOfBoundsException ignored) {
        }
        try {
            newPassword = request.getParameter("newPassword");
        } catch (StringIndexOutOfBoundsException ignored) {
        }
        try {
            newGender = Integer.valueOf(request.getParameter("newGender"));
        } catch (NumberFormatException ignored) {
        }
        if (userID == null) {
            return;
        }
        ServiceUser.updateUserInfo(userID, newNickName, newPassword, newGender);
    }
}
