package com.example.eagle.lalala.Activity;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eagle.lalala.Edit_marks_aty;
import com.example.eagle.lalala.Fragment.LoginFragment;
import com.example.eagle.lalala.Fragment.MapFragment;
import com.example.eagle.lalala.PacelForConvey.ConveyJson;
import com.example.eagle.lalala.PictureWork.HandlePicture;
import com.example.eagle.lalala.PictureWork.TakePicture;
import com.example.eagle.lalala.R;
import com.example.eagle.lalala.Fragment.SharedFragment;
import com.example.eagle.lalala.Service.WorkWithDatabase;
import com.example.neilhy.floatingbutton_library.FloatingActionButton;
import com.example.neilhy.floatingbutton_library.FloatingActionMenu;

import org.json.JSONException;

import java.io.File;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int TAKE_PHOTO=1;
    public static long userId=0;//用户的id

    @Bind(R.id.textView_title_map)
    TextView mTextViewTitleMap;
    @Bind(R.id.textView_title_list)
    TextView mTextViewTitleList;
    TextView mUserName;
    ImageView mUserIcon;
    @Bind(R.id.btn_info_in_MainActivity)
    ImageButton mBtnInfoInMainActivity;
    @Bind(R.id.btn_search_in_MainActivity)
    ImageButton mBtnSearchInMainActivity;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.single_frag_container)
    FrameLayout mSingleFragContainer;
    @Bind(R.id.fab_add)
    FloatingActionButton fab_add;
    @Bind(R.id.fab_camera)
    FloatingActionButton fab_camera;
    @Bind(R.id.menu_button)
    FloatingActionMenu menuButton;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    private Handler mUiHandler=new Handler();
    private File imageFile;

    private Fragment mMapFrgment;
    private Fragment mListFragment;

    private WorkWithDatabase.AccessDatabaseBinder accessDatabaseBinder;//对后台的绑定
    private ServiceConnection connection;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        saveUserInfo();//启用后台获取数据库中用户的数据
    }


    private void init() {
        mMapFrgment = new MapFragment();
        mListFragment = new SharedFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.single_frag_container,mListFragment,"list_frag")
                .add(R.id.single_frag_container,mMapFrgment,"map_frag")
                .hide(mListFragment)
                .commit();

        RelativeLayout drawerHeaderLayout= (RelativeLayout) mNavigationView.getHeaderView(0);
        mUserIcon= (ImageView) drawerHeaderLayout.getChildAt(0);//一定要有这种形式得到header的布局元素，不然会报错nullpoint
        mUserName = (TextView) drawerHeaderLayout.getChildAt(1);
//        mUserName = (TextView) findViewById(R.id.drawer_header_name);//报错
//        mUserIcon = (ImageView) findViewById(R.id.drawer_header_icon);
        mUserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ModifyUserInfo.class));
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawer(GravityCompat.START);

                switch (item.getItemId()) {
                    case R.id.menu_message:
                        Toast.makeText(MainActivity.this,"message",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_favorite:

                        break;
                    case R.id.menu_likes:

                        break;
                    case R.id.menu_posted:

                        break;
                    case R.id.menu_setting:

                        break;
                    default:

                        break;
                }


                return true;
            }
        });
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        fab_add.setOnClickListener(this);
        fab_camera.setOnClickListener(this);

        menuButton.setClosedOnTouchOutside(true);//点击外部可以关闭选项
        menuButton.hideMenuButton(false);

        mUiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                menuButton.showMenuButton(true);
            }
        },800);//让这个按钮800毫秒之后显示出来

        TakePicture.createCustomAnimation(menuButton);//设置点击按钮后的动画，星星变叉
    }

//    @Override
//    protected Fragment creatFragment() {
//        return mMapFrgment;
//    }
    private void setUserIconAndName(String username,String icon){
        if (icon != null) {
            Bitmap userIcon=HandlePicture.StringToBitmap(icon);
            mUserIcon.setImageBitmap(userIcon);
        }
        mUserName.setText(username);
    }

    @Override
    protected Fragment creatFragment() {
        return new MapFragment();
    }

    private void takePhoto(){
        imageFile= HandlePicture.createFileForPhoto();//创建图片路径
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        startActivityForResult(intent,TAKE_PHOTO);//启动相机程序
    }

    @OnClick({R.id.textView_title_map, R.id.textView_title_list, R.id.btn_info_in_MainActivity, R.id.btn_search_in_MainActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView_title_map:
                changeFrag(mListFragment,mMapFrgment);
                //changeFrag(getSupportFragmentManager().findFragmentByTag("list_frag"),getSupportFragmentManager().findFragmentByTag("map_frag"));
                break;
            case R.id.textView_title_list:
                changeFrag(mMapFrgment,mListFragment);
                //changeFrag(getSupportFragmentManager().findFragmentByTag("map_frag"),getSupportFragmentManager().findFragmentByTag("list_frag"));
                break;
            case R.id.btn_info_in_MainActivity:
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
                break;
            case R.id.btn_search_in_MainActivity:
                break;
            case R.id.fab_add:
                Intent intent = new Intent(MainActivity.this, Edit_marks_aty.class);
                startActivity(intent);
                break;
            case R.id.fab_camera:
                takePhoto();
                break;
        }
        menuButton.close(true);
    }

    private void changeFrag(Fragment from,Fragment to) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(!to.isAdded())
            transaction.hide(from).add(R.id.single_frag_container, to).commit();
        else
            transaction.hide(from).show(to).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case TAKE_PHOTO:
                    Edit_marks_aty.actionStart(MainActivity.this,imageFile.getAbsolutePath(),userId);
                    break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void saveUserInfo(){
        ConveyJson userJson=getIntent().getParcelableExtra("userInfo");
        final HashMap<String,Object> userInfo=new HashMap<>();
        try {
            String userName=userJson.object.getString("userName");
            String icon=userJson.object.getString("icon");
            userId = userJson.object.getLong("userId");
            setUserIconAndName(userName,icon);//设置navigation上的头像和名称

            userInfo.put("userId",userId);
            userInfo.put("email", userJson.object.getString("email"));
            userInfo.put("userName", userName);
            userInfo.put("password", userJson.object.getString("password"));
            userInfo.put("icon", icon);
            userInfo.put("background", userJson.object.getString("background"));
            userInfo.put("signature", userJson.object.getString("signature"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                accessDatabaseBinder= (WorkWithDatabase.AccessDatabaseBinder) service;
//                accessDatabaseBinder.saveUserInfo(MainActivity.this,userInfo);
                accessDatabaseBinder.saveUserInfo(MainActivity.this);

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        new saveUserInfos().execute(connection);
    }

    private class saveUserInfos extends AsyncTask<ServiceConnection, Void, String> {
        @Override
        protected String doInBackground(ServiceConnection... params) {
            Intent bindIntent = new Intent(MainActivity.this, WorkWithDatabase.class);
            bindService(bindIntent, params[0], BIND_AUTO_CREATE);//绑定服务
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("正在初始化,请稍候...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String str) {
            progressDialog.dismiss();
            unbindService(connection);
            Toast.makeText(MainActivity.this, "初始化成功", Toast.LENGTH_LONG).show();
        }
    }

}
