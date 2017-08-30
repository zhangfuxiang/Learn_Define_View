package com.example.fuxiangzhang.learn_define_view.common;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fuxiangzhang.learn_define_view.R;

/**
 * Created by Fuxiang.Zhang on 2017/8/30.
 */

public class SimplePaddingDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private Paint dividerPaint;


    public SimplePaddingDecoration(Context mContext) {
        dividerPaint=new Paint();
        dividerPaint.setColor(mContext.getResources().getColor(R.color.colorAccent));
        dividerHeight=mContext.getResources().getDimensionPixelSize(R.dimen.divider_height);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom=dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount=parent.getChildCount();
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();

        for (int mI = 0; mI < childCount-1; mI++) {
            View mView=parent.getChildAt(mI);
            float top=mView.getBottom();
            float bottom=mView.getBottom()+dividerHeight;
            c.drawRect(left,top,right,bottom,dividerPaint);


        }
    }
}
