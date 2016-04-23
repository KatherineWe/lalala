package com.example.eagle.lalala.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eagle.lalala.Edit_marks_aty;
import com.example.eagle.lalala.MapFragment;
import com.example.eagle.lalala.PictureWork.HandlePicture;
import com.example.eagle.lalala.PictureWork.TakePicture;
import com.example.eagle.lalala.R;
import com.example.eagle.lalala.SharedFragment;
import com.example.neilhy.floatingbutton_library.FloatingActionButton;
import com.example.neilhy.floatingbutton_library.FloatingActionMenu;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends SingleFragmentActivity implements View.OnClickListener {

    private static final int TAKE_PHOTO=1;

    @Bind(R.id.textView_title_map)
    TextView mTextViewTitleMap;
    @Bind(R.id.textView_title_list)
    TextView mTextViewTitleList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

    }


    private void init() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawer(GravityCompat.START);

                switch (item.getItemId()) {
                    case R.id.menu_message:

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
        },500);//让这个按钮500毫秒之后显示出来

        TakePicture.createCustomAnimation(menuButton);//设置点击按钮后的动画，星星变叉
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
                changeFrag(new MapFragment());
                break;
            case R.id.textView_title_list:
                changeFrag(new SharedFragment());
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case TAKE_PHOTO:
                    Edit_marks_aty.actionStart(MainActivity.this,imageFile.getAbsolutePath());
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

}
