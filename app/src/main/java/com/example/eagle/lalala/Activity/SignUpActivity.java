package com.example.eagle.lalala.Activity;

import android.support.v4.app.Fragment;

import com.example.eagle.lalala.Fragment.SignUpFragment;

/**
 * Created by eagle on 2016/4/13.
 */
public class SignUpActivity extends SingleFragmentActivity{

    @Override
    protected Fragment creatFragment() {
        return new SignUpFragment();
    }

}
