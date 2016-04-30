package com.example.eagle.lalala.mvp.view;


import com.example.eagle.lalala.PDM.commentsPDM;
import com.example.eagle.lalala.PDM.likesPDM;
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

    public void update2DeleteCircle(long markId);

    public void update2AddFavorite(int circlePosition, likesPDM addItem);

    public void update2DeleteFavort(int circlePosition, long favortId);

    public void update2AddComment(int circlePosition, commentsPDM addItem);

    public void update2DeleteComment(int circlePosition, long commentId);

    public void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig);

}
