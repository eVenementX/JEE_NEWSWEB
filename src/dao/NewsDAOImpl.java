package dao;

import model.News;
import model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import util.ConnectionProvider;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class NewsDAOImpl implements NewsDAO {

private static final String CREATE_NEWS = "INSERT INTO news(name, description, url, user_id, date, up_vote, down_vote) VALUES(:name, :description, :url, :user_id, :date,:up_vote,:down_vote);";
private static final String READ_ALL_NEWS ="SELECT user.user_id, username,email, is_active,password, news_id,name,description,url, date, up_vote,down_vote FROM news LEFT JOIN user ON news.user_id = user.user_id ;";
    private static final String READ_NEWS ="SELECT user.user_id, username,email, is_active,password, news_id,name,description,url, date, up_vote,down_vote FROM news LEFT JOIN user ON news.user_id = user.user_id WHERE news_id=:news_id ;";
    private static final String UPDATE_NEWS = "UPDATE news SET name=:name, description=:description, url=:url, user_id=:user_id, date=:date, up_vote=:up_vote, down_vote=:down_vote WHERE news_id=:news_id;";
private NamedParameterJdbcTemplate template;

    public NewsDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
    }

    @Override
    public News create(News news) {
        News resultNews = news;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String,Object> paramMap = new HashMap <>();
        paramMap.put("name",news.getName());
        paramMap.put("description",news.getDescription());
        paramMap.put("url",news.getUrl());
        paramMap.put("user_id",news.getUser().getId());
        paramMap.put("date",news.getTimestamp());
        paramMap.put("up_vote",news.getUpVote());
        paramMap.put("down_vote",news.getDownVote());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_NEWS,paramSource,keyHolder);
        if (update>0)
        {
             resultNews.setId((Long) keyHolder.getKey());
        }
        return null;
    }

    @Override
    public News read(Long primaryKey) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("news_id",primaryKey);
        News resultNews = template.queryForObject(READ_NEWS,parameterSource,new NewsRowMapper());
        return resultNews;
    }

    @Override
    public boolean update(News news) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("news_id", news.getId());
        paramMap.put("name", news.getName());
        paramMap.put("description", news.getDescription());
        paramMap.put("url", news.getUrl());
        paramMap.put("user_id", news.getUser().getId());
        paramMap.put("date", news.getTimestamp());
        paramMap.put("up_vote", news.getUpVote());
        paramMap.put("down_vote", news.getDownVote());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_NEWS, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List <News> getAll() {
        List<News> newsList = template.query(READ_ALL_NEWS, new NewsRowMapper());
        return newsList;
    }

    private class NewsRowMapper implements RowMapper<News>
    {
        @Override
        public News mapRow(ResultSet resultSet, int i) throws SQLException {

            News news = new News();
            news.setId(resultSet.getLong("news_id"));
            news.setName(resultSet.getString("name"));
            news.setDescription(resultSet.getString("description"));
            news.setUrl(resultSet.getString("url"));
            news.setTimestamp(new Timestamp(new Date().getTime()));
            news.setDownVote(resultSet.getInt("down_vote"));
            news.setUpVote(resultSet.getInt("up_vote"));
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            news.setUser(user);

            return news;

        }
    }
}
