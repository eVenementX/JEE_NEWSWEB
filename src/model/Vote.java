package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Vote {
    private long id;
    private long userId;
    private long newsId;
    private Timestamp timestamp;
    private VoteType voteType;

    public Vote() {
    }

    public Vote(long id, long userId, long newsId, Timestamp timestamp, VoteType voteType) {
        this.id = id;
        this.userId = userId;
        this.newsId = newsId;
        this.timestamp = timestamp;
        this.voteType = voteType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;
        Vote vote = (Vote) o;
        return getId() == vote.getId() &&
                getUserId() == vote.getUserId() &&
                getNewsId() == vote.getNewsId() &&
                Objects.equals(getTimestamp(), vote.getTimestamp()) &&
                Objects.equals(getVoteType(), vote.getVoteType());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUserId(), getNewsId(), getTimestamp(), getVoteType());
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", userId=" + userId +
                ", newsId=" + newsId +
                ", timestamp=" + timestamp +
                ", voteType=" + voteType +
                '}';
    }
}
