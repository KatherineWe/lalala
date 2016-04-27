package com.example.eagle.lalala.NetWork;

import org.json.JSONObject;

/**
 * Created by NeilHY on 2016/4/22.
 */
public interface HttpCallbackListener {
    void onFinishGetJson(JSONObject jsonObject);

    void onFinishGetString(String response);

    void onError(Exception e);
}
