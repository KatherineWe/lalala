package com.example.eagle.lalala.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.eagle.lalala.Fragment.SharedFragment;
import com.example.eagle.lalala.R;

/**
 * @author yiw
 * @ClassName: CommonUtils
 * @Description:
 * @date 2015-12-28 下午4:16:01
 */
public class CommonUtils {

    public static void showSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    public static boolean isShowSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //获取状态信息
        return imm.isActive();//true 打开
    }

    public static void changeFrag(FragmentActivity context, String toTag) {
        if(toTag == DatasUtil.mCurrentFragment)
            return;
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        Fragment from = context.getSupportFragmentManager().findFragmentByTag(DatasUtil.mCurrentFragment);
        Fragment to = context.getSupportFragmentManager().findFragmentByTag(toTag);
        if(to == null) {
            switch (toTag)
            {
                case "posted_frag":
                    transaction.hide(from).add(R.id.single_frag_container,new SharedFragment(),toTag).commit();
                    break;
                case "focused_frag":
                    transaction.hide(from).add(R.id.single_frag_container,new SharedFragment(),toTag).commit();
                    break;
                default:
                    break;
            }

        }
        else
            transaction.hide(from).show(to).commit();
        DatasUtil.mCurrentFragment = toTag;
    }
}
