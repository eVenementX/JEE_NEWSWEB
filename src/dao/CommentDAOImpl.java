package dao;

import model.Comment;

import java.io.Serializable;
import java.util.List;


public class CommentDAOImpl implements CommentDAO {

    @Override
    public Comment getCommentByNewsID(long newsId) {
        return null;
    }

    @Override
    public Comment create(Comment newObject) {
        return null;
    }

    @Override
    public Comment read(Long primaryKey) {
        return null;
    }

    @Override
    public boolean update(Comment updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List <Comment> getAll() {
        return null;
    }
}
