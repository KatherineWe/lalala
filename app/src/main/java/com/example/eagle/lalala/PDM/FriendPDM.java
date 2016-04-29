package com.example.eagle.lalala.PDM;

import android.graphics.Bitmap;

import com.example.eagle.lalala.PictureWork.HandlePicture;

/**
 * Created by NeilHY on 2016/4/29.
 */
public class FriendPDM {
    private long userID;
    private String userName;
    private Bitmap icon;
    private String signature;
    private String emailAddr;

    public FriendPDM() {
    }

    public FriendPDM(long userID, String userName, String icon, String signature, String emailAddr) {
        this.userID = userID;
        this.userName = userName;
        this.icon = HandlePicture.StringToBitmap(icon);
        this.signature = signature;
        this.emailAddr = emailAddr;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = HandlePicture.StringToBitmap(icon);
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }
}
