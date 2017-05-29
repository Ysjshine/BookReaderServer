package servlet.File;

import service.file.ServiceUploadBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by dream on 17-5-1.
 */
@WebServlet(name = "ServletUploadBook", urlPatterns = "/UploadBook")
public class ServletUploadBook extends HttpServlet {
    private static String resourceRoot;

    @Override
    public void init() throws ServletException {
        super.init();
        resourceRoot = getServletContext().getInitParameter("resourceRoot");
        File books = new File(resourceRoot, "books");
        File images = new File(resourceRoot, "images");
        if (!books.exists()) {
            books.mkdir();
        }
        if (!images.exists()) {
            images.mkdir();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        boolean ans = ServiceUploadBook.uploadBook(request, resourceRoot);
        request.getSession().setAttribute("UPLOAD", ans);
        response.sendRedirect("Upload.jsp");
    }
}