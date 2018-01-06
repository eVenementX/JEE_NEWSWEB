package model;

import java.sql.Timestamp;
import java.util.Objects;

public class News {
    private long id;
    private String name;
    private String description;
    private String url;
    private User user;
    private Timestamp timestamp;
    private int upVote;
    private int downVote;

    public News() {
    }

    public News(long id, String name, String description, String url, User user, Timestamp timestamp, int upVote, int downVote) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.user = user;
        this.timestamp = timestamp;
        this.upVote = upVote;
        this.downVote = downVote;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return getId() == news.getId() &&
                getUpVote() == news.getUpVote() &&
                getDownVote() == news.getDownVote() &&
                Objects.equals(getName(), news.getName()) &&
                Objects.equals(getDescription(), news.getDescription()) &&
                Objects.equals(getUrl(), news.getUrl()) &&
                Objects.equals(getUser(), news.getUser()) &&
                Objects.equals(getTimestamp(), news.getTimestamp());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getDescription(), getUrl(), getUser(), getTimestamp(), getUpVote(), getDownVote());
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", user=" + user +
                ", timestamp=" + timestamp +
                ", upVote=" + upVote +
                ", downVote=" + downVote +
                '}';
    }
}
