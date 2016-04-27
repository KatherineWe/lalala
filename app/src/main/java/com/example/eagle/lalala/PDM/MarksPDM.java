package com.example.eagle.lalala.PDM;

import android.graphics.Bitmap;

import com.example.eagle.lalala.PDM.BasicEnum.Authorities;
import com.example.eagle.lalala.PictureWork.HandlePicture;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by NeilHY on 2016/4/26.
 */
public class MarksPDM {
    private long userId;
    private long markId;
    private String lbsName;
    private Timestamp createTime;
    private String content;
    private Bitmap photo;
    private Authorities authority;
    private List<commentsPDM> comments;
    private List<likesPDM> likes;

    public MarksPDM() {
    }

    public MarksPDM(long userId, long markId, String lbsName, Timestamp createTime, String content, String photo, Authorities authority, List<commentsPDM> comments, List<likesPDM> likes) {
        this.userId = userId;
        this.markId = markId;
        this.lbsName = lbsName;
        this.createTime = createTime;
        this.content = content;
        this.photo = HandlePicture.StringToBitmap(photo);
        this.authority = authority;
        this.comments = comments;
        this.likes = likes;
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

    public String getLbsName() {
        return lbsName;
    }

    public void setLbsName(String lbsName) {
        this.lbsName = lbsName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = HandlePicture.StringToBitmap(photo);
    }

    public Authorities getAuthority() {
        return authority;
    }

    public void setAuthority(Authorities authority) {
        this.authority = authority;
    }

    public List<commentsPDM> getComments() {
        return comments;
    }

    public void setComments(List<commentsPDM> comments) {
        this.comments = comments;
    }

    public List<likesPDM> getLikes() {
        return likes;
    }

    public void setLikes(List<likesPDM> likes) {
        this.likes = likes;
    }
}
