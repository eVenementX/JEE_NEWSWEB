package servlet;

import model.News;
import model.User;
import model.Vote;
import model.VoteType;
import service.NewsService;
import service.VoteService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vote")
public class VoteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("user");
        if(loggedUser != null) {
            VoteType voteType = VoteType.valueOf(request.getParameter("vote"));
            long userId  = loggedUser.getId();
            long newsId = Long.parseLong(request.getParameter("news_id"));
            updateVote(userId, newsId, voteType);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }

    private void updateVote(long userId, long newsId, VoteType voteType) {
        VoteService voteService = new VoteService();
        Vote existingVote = voteService.getVoteByUserIdNewsId(userId, newsId);
        Vote updatedVote = voteService.addOrUpdateVote(newsId, userId, voteType);
        if(existingVote != updatedVote || !updatedVote.equals(existingVote )) {
            updateNews(newsId, existingVote, updatedVote);
        }
    }

    private void updateNews(long newsId, Vote oldVote, Vote newVote) {
        NewsService newsService = new NewsService();
        News newsById = newsService.getNewsById(newsId);
        News updatedNews = null;
        if(oldVote == null && newVote != null) {
            updatedNews = addNewsVote(newsById, newVote.getVoteType());
        } else if(oldVote != null && newVote != null) {
            updatedNews = removeNewsVote(newsById, oldVote.getVoteType());
            updatedNews = addNewsVote(updatedNews, newVote.getVoteType());
        }
        newsService.updateNews(updatedNews);
    }

    private News addNewsVote(News news, VoteType voteType) {
        News newsCopy = news;
        if(voteType == VoteType.VOTE_UP) {
            newsCopy.setUpVote(newsCopy.getUpVote() + 1);
        } else if(voteType == VoteType.VOTE_DOWN) {
            newsCopy.setDownVote(newsCopy.getDownVote() + 1);
        }
        return newsCopy;
    }

    private News removeNewsVote(News news, VoteType voteType) {
        News newsCopy = news;
        if(voteType == VoteType.VOTE_UP) {
            newsCopy.setUpVote(newsCopy.getUpVote() - 1);
        } else if(voteType == VoteType.VOTE_DOWN) {
            newsCopy.setDownVote(newsCopy.getDownVote() - 1);
        }
        return newsCopy;
    }
}
