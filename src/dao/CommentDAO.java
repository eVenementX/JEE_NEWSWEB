package dao;

import model.Comment;

public interface CommentDAO extends GenericDAO<Comment, Long> {
    public Comment getCommentByNewsID(long newsId);
}
