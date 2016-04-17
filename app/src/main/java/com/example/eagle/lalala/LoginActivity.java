package com.example.eagle.lalala;

import android.support.v4.app.Fragment;

/**
 * Created by eagle on 2016/4/13.
 */
public class LoginActivity extends SingleFragmentActivity{

    @Override
    protected Fragment creatFragment() {
        return new LoginFragment();
    }

}
