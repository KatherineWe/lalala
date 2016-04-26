package com.example.eagle.lalala.PDM;

/**
 * Created by NeilHY on 2016/4/26.
 */
public class likesPDM {
    private long likeId;
    private long userId;
    private long markId;
    private String userName;

    public likesPDM() {
    }

    public likesPDM(long likeId, long userId, long markId, String userName) {
        this.likeId = likeId;
        this.userId = userId;
        this.markId = markId;
        this.userName = userName;
    }

    public long getLikeId() {
        return likeId;
    }

    public void setLikeId(long likeId) {
        this.likeId = likeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMarkId() {
        return markId;
    }

    public void setMarkId(long markId) {
        this.markId = markId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
