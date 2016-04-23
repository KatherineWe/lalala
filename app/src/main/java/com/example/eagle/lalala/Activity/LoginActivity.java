package com.example.eagle.lalala.Activity;

import android.support.v4.app.Fragment;

import com.example.eagle.lalala.LoginFragment;

/**
 * Created by eagle on 2016/4/13.
 */
public class LoginActivity extends SingleFragmentActivity{

    @Override
    protected Fragment creatFragment() {
        return new LoginFragment();
    }

}
