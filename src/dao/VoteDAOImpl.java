package dao;

import model.Vote;

import java.io.Serializable;
import java.util.List;

public class VoteDAOImpl implements VoteDAO {
    @Override
    public Vote getVoteByUserIdNewsId(long userId, long newsId) {
        return null;
    }

    @Override
    public Vote create(Vote newObject) {
        return null;
    }

    @Override
    public Vote read(Long primaryKey) {
        return null;
    }

    @Override
    public boolean update(Vote updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List <Vote> getAll() {
        return null;
    }
}



