package service;

import dao.DAOFactory;
import dao.NewsDAO;
import model.News;
import model.User;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class NewsService {


    public void addNews (String name, String description, String url, User user)
    {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        NewsDAO newsDAO = daoFactory.getNewsDAO();
        News newsObject = createNewsObject(name,description,url,user);
        newsDAO.create(newsObject);
    }

    private News createNewsObject(String name, String description, String url, User user)
    {
        News resultNews = new News();
        User userCopy = user;
        resultNews.setName(name);
        resultNews.setDescription(description);
        resultNews.setUrl(url);
        resultNews.setUser(userCopy);
        resultNews.setTimestamp(new Timestamp(new Date().getTime()));
        return resultNews;
    }
    public boolean updateNews(News news)
    {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        NewsDAO newsDAO =daoFactory.getNewsDAO();
        boolean result = newsDAO.update(news);

        return  result;
    }

    public News getNewsById(long id)
    {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        NewsDAO newsDAO = daoFactory.getNewsDAO();
        News news = newsDAO.read(id);
        return news;
    }
    public List<News> getAllNews()
    {
        return getAllNews(null);
    }

    public List<News> getAllNews(Comparator<News> comparator)
    {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        NewsDAO newsDAO =daoFactory.getNewsDAO();
        List<News> newsList =newsDAO.getAll();
        if (comparator != null && newsList != null)
        {
            newsList.sort(comparator);
        }
        return newsList;
    }
}
