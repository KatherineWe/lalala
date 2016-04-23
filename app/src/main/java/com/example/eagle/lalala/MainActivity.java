package com.example.eagle.lalala;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import butterknife.OnClick;


public class MainActivity extends SingleFragmentActivity implements View.OnClickListener{


//    @Bind(R.id.textView_title_map)
    TextView mTextViewTitleMap;
//    @Bind(R.id.textView_title_list)
    TextView mTextViewTitleList;
//    @Bind(R.id.btn_info_in_MainActivity)
    ImageButton mBtnInfoInMainActivity;
//    @Bind(R.id.btn_search_in_MainActivity)
    ImageButton mBtnSearchInMainActivity;
//    @Bind(R.id.toolbar)
    Toolbar mToolbar;
//    @Bind(R.id.single_frag_container)
//    FrameLayout mSingleFragContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag0_single_with_titelbar);
       // ButterKnife.bind(this);
        init();
        setSupportActionBar(mToolbar);
    }


    private void init() {
        mTextViewTitleMap = (TextView)findViewById(R.id.textView_title_map);
        mBtnSearchInMainActivity = (ImageButton)findViewById(R.id.btn_search_in_MainActivity);
        mBtnInfoInMainActivity = (ImageButton)findViewById(R.id.btn_info_in_MainActivity);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mTextViewTitleList = (TextView)findViewById(R.id.textView_title_map);
        mTextViewTitleList.setOnClickListener(this);
        mTextViewTitleMap.setOnClickListener(this);
        mBtnInfoInMainActivity.setOnClickListener(this);
        mBtnSearchInMainActivity.setOnClickListener(this);
    }

    @Override
    protected Fragment creatFragment() {
        return new MapFragment();
    }


   // @OnClick({R.id.textView_title_map, R.id.textView_title_list, R.id.btn_info_in_MainActivity, R.id.btn_search_in_MainActivity})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView_title_map:
                mTextViewTitleMap.setTextColor(0xffffffff);
                mTextViewTitleList.setTextColor(0xb0ffffff);
                changeFrag(new MapFragment());
                break;
            case R.id.textView_title_list:
                mTextViewTitleList.setTextColor(0xffffffff);
                mTextViewTitleMap.setTextColor(0xb0ffffff);
                changeFrag(new SharedFragment());
                break;
            case R.id.btn_info_in_MainActivity:
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
                break;
            case R.id.btn_search_in_MainActivity:
                break;
        }
    }


}
