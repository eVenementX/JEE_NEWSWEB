package servlet;

import model.User;
import service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddNews")
public class AddNewsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("inputName");
        String url = request.getParameter("inputUrl");
        String description = request.getParameter("inputDescription");
        User authenticatedUser = (User) request.getSession().getAttribute("user");
        if (request.getUserPrincipal() != null)
        {
            NewsService newsService = new NewsService();
            newsService.addNews(name,description,url,authenticatedUser);
            response.sendRedirect(request.getContextPath()+ "/");
        }else
            response.sendError(403);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if (request.getUserPrincipal()!= null)
            {
                request.getRequestDispatcher("newsAdd.jsp").forward(request,response);
            }else
                response.sendError(403);
    }
}
