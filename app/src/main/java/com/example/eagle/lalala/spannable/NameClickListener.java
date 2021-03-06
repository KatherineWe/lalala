package com.example.eagle.lalala.spannable;

import android.text.SpannableString;
import android.widget.Toast;

import com.example.eagle.lalala.MyApplication;


/**
 * @author yiw
 * @ClassName: NameClickListener
 * @Description: 点赞和评论中人名的点击事件
 * @date 2015-01-02 下午3:42:21
 */
public class NameClickListener implements ISpanClick {
    private SpannableString userName;
    private long userId;

    public NameClickListener(SpannableString name, long userid) {
        this.userName = name;
        this.userId = userid;
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(MyApplication.getContext(), userName + " &id = " + userId, Toast.LENGTH_SHORT).show();
    }
}
