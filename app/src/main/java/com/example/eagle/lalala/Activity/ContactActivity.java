package com.example.eagle.lalala.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.eagle.lalala.Fragment.ContactFragment;
import com.example.eagle.lalala.Fragment.LoginFragment;
import com.example.eagle.lalala.R;

public class ContactActivity extends SingleFragmentActivity{

    @Override
    protected Fragment creatFragment() {
        return new ContactFragment();
    }

}
