package dao;

public class MysqlDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public NewsDAO getNewsDAO() {
        return new NewsDAOImpl();
    }

    @Override
    public VoteDAO getVoteDAO() {
        return new VoteDAOImpl();
    }

    @Override
    public CommentDAO getCommentDAO() {
        return new CommentDAOImpl();
    }
}
