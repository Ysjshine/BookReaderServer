package servlet.Manager;

import service.bean.ManagerCategory;
import service.message.CommonCommunication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dream on 17-5-20.
 */
@WebServlet(name = "ServletDelCategory", urlPatterns = "/DeleteCategory")
public class ServletDelCategory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int uid = Integer.parseInt(request.getParameter("uid"));
        int cid = Integer.parseInt(request.getParameter("cid"));
        boolean ans = ManagerCategory.delCategory(uid, cid);
        CommonCommunication.sendMessage(response, ans);
    }
}
