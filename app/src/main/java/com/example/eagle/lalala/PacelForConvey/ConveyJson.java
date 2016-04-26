package com.example.eagle.lalala.PacelForConvey;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by NeilHY on 2016/4/24.
 */
public class ConveyJson implements Parcelable {

    public JSONObject object;

    public static final Creator<ConveyJson> CREATOR = new Creator<ConveyJson>() {
        @Override
        public ConveyJson createFromParcel(Parcel in) {
            String str=in.readString();
            ConveyJson conveyJson = new ConveyJson(str);
            return conveyJson;
        }

        @Override
        public ConveyJson[] newArray(int size) {
            return new ConveyJson[size];
        }
    };

    public ConveyJson(String str) {
        try {
            object = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(object.toString());
    }
}
