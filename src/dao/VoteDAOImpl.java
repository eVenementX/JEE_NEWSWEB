package dao;

import model.Vote;
import model.VoteType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import util.ConnectionProvider;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteDAOImpl implements VoteDAO {



    private static final String CREATE_VOTE = "INSERT INTO vote (news_id, user_id,date, type) VALUES(:news_id, :user_id,:date,:type);";
    private static final String READ_VOTE ="SELECT vote_id, news_id, user_id, date, type FROM vote WHERE vote_id = :vote_id;";
    private static final String READ_VOTE_BY_DISCOVERY_USE_IDS = "SELECT vote_id, news_id, user_id, date, type FROM vote WHERE user_id = :user_id AND news_id = :news_id;";
    private static final String  UPDATE_VOTE = "UPDATE vote SET date=:date,type=:type WHERE vote_id = :vote_id;";

    NamedParameterJdbcTemplate template;

    public VoteDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());

    }

    @Override
    public Vote getVoteByUserIdNewsId(long userId, long newsId) {
        Vote resultVote = null;
        Map<String,Object> paramMap = new HashMap <>();
        paramMap.put("user_id",userId);
        paramMap.put("news_id",newsId);
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        try {
            resultVote = template.queryForObject(READ_VOTE_BY_DISCOVERY_USE_IDS, paramSource, new VoteRowMapper());
        } catch(EmptyResultDataAccessException e) {

        }
        return resultVote;
    }

    @Override
    public Vote create(Vote vote) {
        Vote resultVote = vote;
        Map<String,Object> voteMap = new HashMap <>();
        voteMap.put("news_id",resultVote.getNewsId());
        voteMap.put("user_id",resultVote.getUserId());
        voteMap.put("date",resultVote.getTimestamp());
        voteMap.put("type", resultVote.getVoteType().toString());
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new MapSqlParameterSource(voteMap);
        int rowsAffected = template.update(CREATE_VOTE,paramSource,holder);
        if (rowsAffected >0)
        {
            resultVote.setId((Long) holder.getKey());
        }
        return resultVote;
    }

    @Override
    public Vote read(Long primaryKey) {
        Vote resultVote = new Vote();
        SqlParameterSource paramSource = new MapSqlParameterSource("vote_id",primaryKey);
        resultVote = template.queryForObject(READ_VOTE,paramSource,new VoteRowMapper());
        return resultVote;
    }


    @Override
    public boolean update(Vote vote) {
        Map<String,Object>paramMap = new HashMap <>();
        boolean result = false;
        paramMap.put("date",vote.getTimestamp());
        paramMap.put("type",vote.getVoteType().toString());
        paramMap.put("vote_id",vote.getId());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);

        int rowsAffected = template.update(UPDATE_VOTE,paramSource);
        if (rowsAffected > 0)
        {
             result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List <Vote> getAll() {
        return null;
    }



    class VoteRowMapper implements RowMapper<Vote>
    {

        @Override
        public Vote mapRow(ResultSet resultSet, int i) throws SQLException {
            Vote resultVote = new Vote();
            resultVote.setId(resultSet.getLong("vote_id"));
            resultVote.setNewsId(resultSet.getLong("news_id"));
            resultVote.setUserId(resultSet.getLong("user_id"));
            resultVote.setTimestamp(resultSet.getTimestamp("date"));
            resultVote.setVoteType(VoteType.valueOf(resultSet.getString("type")));
            return resultVote;
        }
    }
}



