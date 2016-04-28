package com.example.eagle.lalala.Service;

import android.app.Service;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.eagle.lalala.Activity.MainActivity;
import com.example.eagle.lalala.PictureWork.HandlePicture;
import com.example.eagle.lalala.SQL.WeMarkDatabaseHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NeilHY on 2016/4/24.
 */
public class WorkWithDatabase extends Service {

    private WeMarkDatabaseHelper databaseHelper;
    private AccessDatabaseBinder mBinder=new AccessDatabaseBinder();

    public class AccessDatabaseBinder extends Binder{

        public void saveUserInfo(Context context, final HashMap<String, Object> userInfo){
            databaseHelper = new WeMarkDatabaseHelper(context, "WeMark.db", null, 1);
            final SQLiteDatabase db=databaseHelper.getWritableDatabase();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.beginTransaction();
                        try {
                            db.delete(WeMarkDatabaseHelper.USER_TABLE, null, null);//删除表的数据
                            ContentValues values = new ContentValues();
                            boolean flag = false;
                            if ((long) userInfo.get("userId") != 0) {
                                values.put(WeMarkDatabaseHelper.USER_ID, (long) userInfo.get("userId"));
                                flag = true;//标志有userId，不为空才可以插入
                            }
                            if (userInfo.get("email") != null && !userInfo.get("email").toString().equals("")) {
                                values.put(WeMarkDatabaseHelper.EMAIL, userInfo.get("email").toString());
                            }
                            if (userInfo.get("userName") != null && !userInfo.get("userName").toString().equals("")) {
                                values.put(WeMarkDatabaseHelper.USER_NAME, userInfo.get("userName").toString());
                            }
                            if (userInfo.get("password") != null && !userInfo.get("password").toString().equals("")) {
                                values.put(WeMarkDatabaseHelper.PASSWORD, userInfo.get("password").toString());
                            }
                            if (userInfo.get("icon") != null && !userInfo.get("icon").toString().equals("")) {
//                                Bitmap bitmap = HandlePicture.StringToBitmap(userInfo.get("icon").toString());
//                                values.put(WeMarkDatabaseHelper.ICON, HandlePicture.bitmapTobyte(bitmap));
                                values.put(WeMarkDatabaseHelper.ICON,userInfo.get("icon").toString());
                            }
                            if (userInfo.get("background") != null && !userInfo.get("background").toString().equals("")) {
//                                Bitmap bitmap = HandlePicture.StringToBitmap(userInfo.get("background").toString());
//                                values.put(WeMarkDatabaseHelper.BACKGROUND, HandlePicture.bitmapTobyte(bitmap));
                                values.put(WeMarkDatabaseHelper.BACKGROUND,userInfo.get("background").toString());
                            }
                            if (userInfo.get("signature") != null) {
                                values.put(WeMarkDatabaseHelper.SIGNATURE, userInfo.get("signature").toString());
                            }
                            if (flag) {
                                db.insert(WeMarkDatabaseHelper.USER_TABLE, null, values);
                            }
                            db.setTransactionSuccessful();
                        } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        db.endTransaction();
                    }

                    }
                }).start();

        }

        public void upDateUserInfo(Context context, final HashMap<String, Object> userInfo) {
            databaseHelper = new WeMarkDatabaseHelper(context, "WeMark.db", null, 1);
            final SQLiteDatabase db=databaseHelper.getWritableDatabase();
            db.beginTransaction();
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ContentValues values = new ContentValues();
                        boolean flag = false;
                        if ((long) userInfo.get("userId") != 0) {
                            values.put(WeMarkDatabaseHelper.USER_ID, (long) userInfo.get("userId"));
                            flag = true;//标志有userId，不为空才可以插入
                        }
                        if (userInfo.get("email") != null && !userInfo.get("email").toString().equals("")) {
                            values.put(WeMarkDatabaseHelper.EMAIL, userInfo.get("email").toString());
                        }
                        if (userInfo.get("userName") != null && !userInfo.get("userName").toString().equals("")) {
                            values.put(WeMarkDatabaseHelper.USER_NAME, userInfo.get("userName").toString());
                        }
                        if (userInfo.get("password") != null && !userInfo.get("password").toString().equals("")) {
                            values.put(WeMarkDatabaseHelper.PASSWORD, userInfo.get("password").toString());
                        }
                        if (userInfo.get("icon") != null && !userInfo.get("icon").toString().equals("")) {
//                            Bitmap bitmap = HandlePicture.StringToBitmap(userInfo.get("icon").toString());
//                            values.put(WeMarkDatabaseHelper.ICON, HandlePicture.bitmapTobyte(bitmap));
                            values.put(WeMarkDatabaseHelper.ICON,userInfo.get("icon").toString());
                        }
                        if (userInfo.get("background") != null && !userInfo.get("background").toString().equals("")) {
//                            Bitmap bitmap = HandlePicture.StringToBitmap(userInfo.get("background").toString());
//                            values.put(WeMarkDatabaseHelper.BACKGROUND, HandlePicture.bitmapTobyte(bitmap));
                            values.put(WeMarkDatabaseHelper.BACKGROUND,userInfo.get("background").toString());
                        }
                        if (userInfo.get("signature") != null) {
                            values.put(WeMarkDatabaseHelper.SIGNATURE, userInfo.get("signature").toString());
                        }
                        if (flag) {
                            db.update(WeMarkDatabaseHelper.USER_TABLE, values,"userId="+userInfo.get("userId"),null);
                        }
                        db.setTransactionSuccessful();
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                db.endTransaction();
            }
        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
