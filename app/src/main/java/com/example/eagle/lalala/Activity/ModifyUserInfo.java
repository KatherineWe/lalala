package com.example.eagle.lalala.Activity;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eagle.lalala.PictureWork.HandlePicture;
import com.example.eagle.lalala.R;
import com.example.eagle.lalala.SQL.WeMarkDatabaseHelper;
import com.example.eagle.lalala.Service.WorkWithDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NeilHY on 2016/4/26.
 */
public class ModifyUserInfo extends AppCompatActivity implements View.OnClickListener,DialogEditUserInfo.EditInputListener {

    private LinearLayout iconLayout;
    private LinearLayout nickNameLayout;
    private LinearLayout emailLayout;
    private LinearLayout signatureLayout;
    private ImageView backgroundIv;
    private ImageView iconIv;
    private TextView nickNameTv;
    private TextView emailTv;
    private TextView signatureTv;

    private WorkWithDatabase.AccessDatabaseBinder accessDatabaseBinder;//对后台的绑定
    private ServiceConnection connection;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyuserinfo);
        initView();
        getUserInfo();
    }

    private void initView() {
        iconLayout = (LinearLayout) findViewById(R.id.modify_icon_layout);
        nickNameLayout = (LinearLayout) findViewById(R.id.modify_nickname_layout);
        signatureLayout = (LinearLayout) findViewById(R.id.modify_signature_layout);
        backgroundIv = (ImageView) findViewById(R.id.modify_background_iv);
        iconIv = (ImageView) findViewById(R.id.modify_icon_iv);
        nickNameTv = (TextView) findViewById(R.id.modify_nickname_tv);
        emailTv = (TextView) findViewById(R.id.modify_email_tv);
        signatureTv = (TextView) findViewById(R.id.modify_signature_tv);

        backgroundIv.setOnClickListener(this);
        iconLayout.setOnClickListener(this);
        nickNameLayout.setOnClickListener(this);
        signatureLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_background_iv:
                Toast.makeText(ModifyUserInfo.this,"background",Toast.LENGTH_SHORT).show();

                break;
            case R.id.modify_icon_layout:
                Toast.makeText(ModifyUserInfo.this,"icon",Toast.LENGTH_SHORT).show();

                break;
            case R.id.modify_nickname_layout:
                showEditStringDialog("昵称");
                break;
            case R.id.modify_signature_layout:
                showEditStringDialog("签名");

                break;
        }
    }

    public void showEditStringDialog(String title)
    {
        Bundle bundle=new Bundle();
        bundle.putString("title",title);
        DialogEditUserInfo dialog = new DialogEditUserInfo();
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(),title);

    }

    @Override
    public void onEditInputComplete(String info) {
        if (info != null) {
            Toast.makeText(ModifyUserInfo.this, info, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ModifyUserInfo.this, "请输入内容…", Toast.LENGTH_SHORT).show();
        }
    }

    public void getUserInfo() {
        WeMarkDatabaseHelper databaseHelper = new WeMarkDatabaseHelper(ModifyUserInfo.this, "WeMark.db", null, 1);
        final SQLiteDatabase db=databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(WeMarkDatabaseHelper.USER_TABLE, null, "userId=" + MainActivity.userId, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex("email")) != null) {
                    emailTv.setText(cursor.getString(cursor.getColumnIndex("email")));
                }
                if (cursor.getString(cursor.getColumnIndex("userName")) != null) {
                    nickNameTv.setText(cursor.getString(cursor.getColumnIndex("userName")));
                }
                if (cursor.getString(cursor.getColumnIndex("signature")) != null) {
                    signatureTv.setText(cursor.getString(cursor.getColumnIndex("signature")));
                }
                if (cursor.getBlob(cursor.getColumnIndex("icon")) != null) {

                    iconIv.setImageBitmap(HandlePicture.byteToBitmap(cursor.getBlob(cursor.getColumnIndex("icon"))));
                }
                if (cursor.getBlob(cursor.getColumnIndex("background")) != null) {

                   backgroundIv.setImageBitmap(HandlePicture.byteToBitmap(cursor.getBlob(cursor.getColumnIndex("background"))));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

}
