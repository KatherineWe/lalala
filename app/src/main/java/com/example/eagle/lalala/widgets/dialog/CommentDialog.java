package com.example.eagle.lalala.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.eagle.lalala.Activity.MainActivity;
import com.example.eagle.lalala.PDM.commentsPDM;
import com.example.eagle.lalala.R;
import com.example.eagle.lalala.bean.CommentItem;
import com.example.eagle.lalala.mvp.presenter.CirclePresenter;
import com.example.eagle.lalala.utils.DatasUtil;


/**
 * @author yiw
 * @ClassName: CommentDialog
 * @Description: 评论长按对话框，进行复制和删除
 * @date 2015-12-28 下午3:36:39
 */
public class CommentDialog extends Dialog implements
        View.OnClickListener {

    private Context mContext;
    private CirclePresenter mPresenter;
    private commentsPDM mCommentItem;
    private int mCirclePosition;

    public CommentDialog(Context context, CirclePresenter presenter,
                         commentsPDM commentItem, int circlePosition) {
        super(context, R.style.comment_dialog);
        mContext = context;
        this.mPresenter = presenter;
        this.mCommentItem = commentItem;
        this.mCirclePosition = circlePosition;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment);
        initWindowParams();
        initView();
    }

    private void initWindowParams() {
        Window dialogWindow = getWindow();
        // 获取屏幕宽、高用
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (display.getWidth() * 0.65); // 宽度设置为屏幕的0.65

        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        TextView copyTv = (TextView) findViewById(R.id.copyTv);
        copyTv.setOnClickListener(this);
        TextView deleteTv = (TextView) findViewById(R.id.deleteTv);
        if (mCommentItem != null
                && MainActivity.userId ==
                mCommentItem.getFriendId()) {
            deleteTv.setVisibility(View.VISIBLE);
        } else {
            deleteTv.setVisibility(View.GONE);
        }
        deleteTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.copyTv:
                if (mCommentItem != null) {
                    ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(mCommentItem.getContent());
                }
                dismiss();
                break;
            case R.id.deleteTv:
                if (mPresenter != null && mCommentItem != null) {
                    mPresenter.deleteComment(mCirclePosition, mCommentItem.getCommentId());
                }
                dismiss();
                break;
            default:
                break;
        }
    }

}
