package com.example.eagle.lalala.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eagle.lalala.R;
import com.example.eagle.lalala.adapter.ContactAdapter;
import com.example.eagle.lalala.utils.DatasUtil;
import com.example.eagle.lalala.widgets.SideBar;

/**
 * Created by eagle on 2016/4/26.好友列表界面
 */
public class ContactFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private View layout;
    private ListView lvContact;
    private SideBar indexBar;
    private TextView mDialogText;
    private WindowManager mWindowManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layout == null) {
            layout = getActivity().getLayoutInflater().inflate(R.layout.frag_contacts,
                    null);
            mWindowManager = (WindowManager) getActivity()
                    .getSystemService(Context.WINDOW_SERVICE);
            initViews();
            initData();
            setOnListener();
        } else {
            ViewGroup parent = (ViewGroup) layout.getParent();
            if (parent != null) {
                parent.removeView(layout);
            }
        }
        return layout;
    }

    private void initViews() {
        lvContact = (ListView) layout.findViewById(R.id.lvContact);

        mDialogText = (TextView) LayoutInflater.from(getActivity()).inflate(
                R.layout.list_position, null);
        mDialogText.setVisibility(View.INVISIBLE);

        indexBar = (SideBar) layout.findViewById(R.id.sideBar);
        indexBar.setListView(lvContact);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        mWindowManager.addView(mDialogText, lp);
        indexBar.setTextView(mDialogText);

    }

    @Override
    public void onDestroy() {
        mWindowManager.removeView(mDialogText);
        super.onDestroy();
    }

    /**
     * 刷新页面
     */
    public void refresh() {
        initData();
    }

    private void initData() {
//        if (GloableParams.UserInfos != null) {
                lvContact.setAdapter(new ContactAdapter(getActivity(),
                        DatasUtil.getUsers()));
//            } else {
//                FinalDb db = FinalDb
//                        .create(getActivity(), Constants.DB_NAME, false);
//                GloableParams.UserInfos = db.findAllByWhere(User.class, "type='N'");
//                lvContact.setAdapter(new ContactAdapter(getActivity(),
//                        GloableParams.UserInfos));
//                for (User user : GloableParams.UserInfos) {
//                    GloableParams.Users.put(user.getTelephone(), user);
//                }
            // Intent intent = new Intent(getActivity(), UpdateService.class);
            // getActivity().startService(intent);
     //   }
    }

    private void setOnListener() {
        lvContact.setOnItemClickListener(this);
//        layout_head.findViewById(R.id.layout_addfriend)
//                .setOnClickListener(this);
//        layout_head.findViewById(R.id.layout_search).setOnClickListener(this);
//        layout_head.findViewById(R.id.layout_group).setOnClickListener(this);
//        layout_head.findViewById(R.id.layout_public).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//        User user = GloableParams.UserInfos.get(arg2 - 1);
//        if (user != null) {
//            Intent intent = new Intent(getActivity(), FriendMsgActivity.class);
//            intent.putExtra(Constants.NAME, user.getUserName());
//            intent.putExtra(Constants.TYPE, ChatActivity.CHATTYPE_SINGLE);
//            intent.putExtra(Constants.User_ID, user.getTelephone());
//            getActivity().startActivity(intent);
//            getActivity().overridePendingTransition(R.anim.push_left_in,
//                    R.anim.push_left_out);
//        }

    }

}

