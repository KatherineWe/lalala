package com.example.eagle.lalala.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eagle.lalala.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eagle on 2016/4/9.
 */
public class SignUpFragment extends Fragment {

    @Bind(R.id.input_nickname)
    EditText mInputNickname;
    @Bind(R.id.input_signup_email)
    EditText mInputSignupEmail;
    @Bind(R.id.input_signup_password)
    EditText mInputSignupPassword;
    @Bind(R.id.input_signup_repeatPassword)
    EditText mInputSignupRepeatPassword;
    @Bind(R.id.btn_signup)
    AppCompatButton mBtnSignup;
    @Bind(R.id.link_login)
    TextView mLinkLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_signup, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_signup, R.id.link_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                if(validate())
                    getActivity().setResult(Activity.RESULT_OK);
                break;
            case R.id.link_login:
                    getActivity().setResult(Activity.RESULT_CANCELED);
                break;
        }
    }


    public boolean validate() {
        boolean valid = true;

        String name = mInputNickname.getText().toString();
        String email = mInputSignupEmail.getText().toString();
        String password = mInputSignupPassword.getText().toString();
        String repeatPassword = mInputSignupRepeatPassword.getText().toString();

        if (name.isEmpty() || name.length() < 3 || name.length() > 10) {
            mInputNickname.setError("请输入长度为3到10的昵称");
            valid = false;
        } else {
            mInputNickname.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mInputSignupEmail.setError("请输入有效邮件");
            valid = false;
        } else {
            mInputSignupEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 10) {
            mInputSignupPassword.setError("请输入6到10位长的密码");
            valid = false;
        } else {
            mInputSignupPassword.setError(null);
        }

        if(!password.equals(repeatPassword)){
            valid = false;
            Toast.makeText(getActivity(),"两次输入密码不一致",Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

}
