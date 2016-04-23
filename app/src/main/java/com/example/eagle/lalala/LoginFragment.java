package com.example.eagle.lalala;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eagle.lalala.Activity.MainActivity;
import com.example.eagle.lalala.Activity.SignUpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eagle on 2016/4/9.
 */
public class LoginFragment extends Fragment {

    @Bind(R.id.input_login_email)
    EditText mInputEmail;
    @Bind(R.id.input_login_password)
    EditText mInputPassword;
    @Bind(R.id.btn_login)
    AppCompatButton mBtnLogin;
    @Bind(R.id.link_signup)
    TextView mLinkSignup;
    private static final String TAG = "LoginFragment";
    private static final int REQUEST_SIGNUP = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_login, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_login, R.id.link_signup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.link_signup:
                startActivityForResult(new Intent(getActivity(),SignUpActivity.class),REQUEST_SIGNUP);
                break;
        }
    }

    public void onLoginSuccess() {
        mBtnLogin.setEnabled(true);
        startActivity(new Intent(getActivity(),MainActivity.class));
    }

    public void onLoginFailed() {
        Toast.makeText(getActivity(), "登陆失败", Toast.LENGTH_SHORT).show();
        mBtnLogin.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String email = mInputEmail.getText().toString();
        String password = mInputPassword.getText().toString();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mInputEmail.setError("请输入有效邮件");
            valid = false;
        } else {
            mInputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 10) {
            mInputPassword.setError("请输入6到10位长的密码");
            valid = false;
        } else {
            mInputPassword.setError(null);
        }

        return valid;
    }

    private void login() {
        Log.d(TAG, "登陆");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        mBtnLogin.setEnabled(false);
        startActivity(new Intent(getActivity(),MainActivity.class));

        //此部分后边再补充
//        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Authenticating...");
//        progressDialog.show();
//
//        String email = mInputEmail.getText().toString();
//        String password = mInputPassword.getText().toString();
//
//        // TODO: Implement your own authentication logic here.
//
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onLoginSuccess or onLoginFailed
//                        onLoginSuccess();
//                        // onLoginFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) {
            return;//或者需要其他提示信息？
        }

        if(requestCode == REQUEST_SIGNUP){
            Toast.makeText(getActivity(), "请登陆注册邮箱验证完成注册", Toast.LENGTH_SHORT).show();
        }
    }
}
