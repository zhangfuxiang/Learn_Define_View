package com.example.fuxiangzhang.learn_define_view.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fuxiangzhang.learn_define_view.R;

/**
 * Created by Fuxiang.Zhang on 2017/8/30.
 */

public class LeftAndRightTagDecoration extends RecyclerView.ItemDecoration {
    private int tagWidth;
    private Paint leftPaint;
    private Paint rightPaint;

    public LeftAndRightTagDecoration(Context mContext) {
        leftPaint=new Paint();
        leftPaint.setColor(mContext.getResources().getColor(R.color.colorAccent));
        rightPaint=new Paint();
        rightPaint.setColor(mContext.getResources().getColor(R.color.colorPrimary));
        tagWidth=mContext.getResources().getDimensionPixelSize(R.dimen.divider_height);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        float left,right,top,bottom;
        super.onDrawOver(c, parent, state);
        int childCount=parent.getChildCount();
        for (int mI = 0; mI < childCount; mI++) {
            View child=parent.getChildAt(mI);
            int position=parent.getChildAdapterPosition(child);
            boolean isleft=position%2==0;
            if (isleft) {
                left=child.getLeft();
                right=left+tagWidth;
                top=child.getTop();
                bottom=child.getBottom();
                c.drawRect(left,top,right,bottom,leftPaint);
            }else{
                right=child.getRight();
                left=right-tagWidth;
                top=child.getTop();
                bottom=child.getBottom();
                c.drawRect(left,top,right,bottom,rightPaint);
            }
        }
    }
}
