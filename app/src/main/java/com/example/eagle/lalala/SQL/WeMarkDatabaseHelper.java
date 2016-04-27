package com.example.eagle.lalala.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by NeilHY on 2016/4/24.
 */
public class WeMarkDatabaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE="User";
    public static final String USER_ID="userId";
    public static final String EMAIL="email";
    public static final String STATUS="status";
    public static final String USER_NAME="userName";
    public static final String PASSWORD="password";
    public static final String SIGNATURE="signature";
    public static final String ICON="icon";
    public static final String BACKGROUND="background";

    public static final String FRIENDS_TABLE = "Friends";
    public static final String FRIEND_ID = "friendId";
    public static final String FRIEND_NAME = "friendName";
    public static final String FRIEND_ICON = "friendIcon";

    public static final String MARKS_TABLE = "Marks";
    public static final String MARK_ID = "markId";
    public static final String MARK_CONTENT = "content";
    public static final String MARK_PHOTO = "photo";

    public static final String AUTHORITY_TABLE="Authority";
    public static final String AUTHORITY_ID="authorityId";
    public static final String AUTHORITY_NAME="name";
    public static final String AUTHORITY_USER_ID="userId";

    private Context mContext;

    public static final String CREATE_USER_TABLE="create table "+USER_TABLE+" ("
            +USER_ID+" integer(8) primary key, "
            +EMAIL+" varchar(255), "
            +STATUS+" tinyint(2), "
            +USER_NAME+" varchar(255), "
            +PASSWORD+" varchar(255), "
            +SIGNATURE+" text, "
            +ICON+" blob, "
            +BACKGROUND+" blob)";

    public static final String CREATE_FRIEND_TABLE="create table "+FRIENDS_TABLE+" ("
            +FRIEND_ID+" integer(8) primary key, "
            +FRIEND_NAME+" varchar(255), "
            +FRIEND_ICON+" blob)";

    public static final String CREATE_MARKS_TABLE="create table "+MARKS_TABLE+" ("
            +MARK_ID+" integer(8) primary key, "
            +MARK_CONTENT+" text,"
            +MARK_PHOTO+" blob)";

    public static final String CREATE_AUTHORITY_TABLE="create table "+AUTHORITY_TABLE+" ("
            +AUTHORITY_ID+" integer(8) primary key, "
            +AUTHORITY_NAME+" varchar(255), "
            +AUTHORITY_USER_ID+" integer(8))";

    public WeMarkDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FRIEND_TABLE);
        db.execSQL(CREATE_MARKS_TABLE);
        db.execSQL(CREATE_AUTHORITY_TABLE);
        Toast.makeText(mContext,"ok",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
