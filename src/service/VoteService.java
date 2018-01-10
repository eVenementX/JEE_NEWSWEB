package service;

import dao.DAOFactory;
import dao.VoteDAO;
import model.Vote;
import model.VoteType;

import java.sql.Timestamp;
import java.util.Date;

public class VoteService {

    public Vote addVote(long newsId,long user_id, VoteType voteType)
    {
        Vote vote = new Vote();
        vote.setNewsId(newsId);
        vote.setUserId(user_id);
        vote.setVoteType(voteType);
        vote.setTimestamp(new Timestamp(new Date().getTime()));
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = daoFactory.getVoteDAO();
        voteDAO.create(vote);
        return vote;
    }
    public Vote updateVote(long newsId, long userId,VoteType voteType)
    {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = daoFactory.getVoteDAO();
        Vote voteToUpdate = getVoteByUserIdNewsId(userId,newsId);
        if (voteToUpdate != null)
        {
            voteToUpdate.setVoteType(voteType);
            voteDAO.update(voteToUpdate);
        }
        return voteToUpdate;
    }
    public Vote addOrUpdateVote(long newsId,long userId, VoteType voteType)
    {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = daoFactory.getVoteDAO();
       Vote voteToCheck =voteDAO.getVoteByUserIdNewsId(userId,newsId);
       Vote resultVote;
       if (voteToCheck == null)
       {
          resultVote =  addVote(newsId, userId, voteType);
       }else
       {
         resultVote =  updateVote(newsId, userId, voteType);
       }
       return  resultVote;
    }
    public Vote getVoteByUserIdNewsId(long userId,long newsId)
    {
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = daoFactory.getVoteDAO();
        Vote resultVote = voteDAO.getVoteByUserIdNewsId(userId,newsId);
        return resultVote;
    }
}
