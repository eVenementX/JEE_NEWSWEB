package dao;

import model.Vote;



public interface VoteDAO extends GenericDAO <Vote, Long> {

    public Vote getVoteByUserIdNewsId(long userId, long newsId);
}
