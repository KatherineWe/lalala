package com.example.eagle.lalala.mvp.view;


import com.example.eagle.lalala.bean.CommentConfig;
import com.example.eagle.lalala.bean.CommentItem;
import com.example.eagle.lalala.bean.FavortItem;

/**
 * @author yiw
 * @ClassName: ICircleViewUpdateListener
 * @Description: view, 服务器响应后更新界面
 * @date 2015-12-28 下午4:13:04
 */
public interface ICircleView {

    public void update2DeleteCircle(String circleId);

    public void update2AddFavorite(int circlePosition, FavortItem addItem);

    public void update2DeleteFavort(int circlePosition, String favortId);

    public void update2AddComment(int circlePosition, CommentItem addItem);

    public void update2DeleteComment(int circlePosition, String commentId);

    public void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig);

}
