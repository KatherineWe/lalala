package com.example.eagle.lalala.Activity;

import android.support.v4.app.Fragment;

import com.example.eagle.lalala.InfoFragment;

/**
 * Created by eagle on 2016/4/13.
 */
public class InfoActivity extends SingleFragmentActivity {

    @Override
    protected Fragment creatFragment() {
        return new InfoFragment();
    }

}
