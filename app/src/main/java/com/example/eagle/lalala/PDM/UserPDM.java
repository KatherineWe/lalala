package com.example.eagle.lalala.PDM;

import com.example.eagle.lalala.NetWork.HttpUtil;

/**
 * Created by NeilHY on 2016/4/24.
 */
public class UserPDM {

    private String email;
    private String password;

    private String user_name;
    private String icon;
    private String background;
    private String signature;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = HttpUtil.toUTFString(user_name);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = HttpUtil.toUTFString(signature);
    }
}
