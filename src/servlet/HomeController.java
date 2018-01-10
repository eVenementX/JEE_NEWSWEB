package servlet;

import model.News;
import service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

@WebServlet("")
public class HomeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        saveNewsAsRequestAtribute(request);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
    private void saveNewsAsRequestAtribute(HttpServletRequest request)
    {
        NewsService newsService = new NewsService();
       List<News> newsList = newsService.getAllNews(((o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp())));
       request.setAttribute("newsList",newsList);
    }
}
