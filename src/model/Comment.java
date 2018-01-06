package model;

import java.util.Objects;

public class Comment {
    private long id;
    private String description;
    private User user;
    private News news;

    public Comment() {
    }

    public Comment(long id, String description, User user, News news) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.news = news;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getId() == comment.getId() &&
                Objects.equals(getDescription(), comment.getDescription()) &&
                Objects.equals(getUser(), comment.getUser()) &&
                Objects.equals(getNews(), comment.getNews());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDescription(), getUser(), getNews());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", news=" + news +
                '}';
    }
}
