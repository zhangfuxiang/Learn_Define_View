package com.example.fuxiangzhang.learn_define_view.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

import com.example.fuxiangzhang.learn_define_view.R;

import static android.content.ContentValues.TAG;

/**
 * Created by Fuxiang.Zhang on 2017/8/30.
 */

public class SectionDecoration extends RecyclerView.ItemDecoration {

    public interface DecorationCallback{
        long getGroupId(int position);

        String getGroupFirstLine(int position);
    }

    private DecorationCallback mDecorationCallback;
    private Paint mPaint;
    private int topGap;
    private TextPaint mTextPaint;
    private Paint.FontMetrics mFontMetrics;

    public SectionDecoration(Context mContext,DecorationCallback mDecorationCallback) {
        Resources mResources=mContext.getResources();
        this.mDecorationCallback=mDecorationCallback;

        mTextPaint=new TextPaint();
        mPaint.setColor(mResources.getColor(R.color.colorAccent));

        mTextPaint=new TextPaint();
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(80);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.getFontMetrics(mFontMetrics);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mFontMetrics = new Paint.FontMetrics();
        topGap = mResources.getDimensionPixelSize(R.dimen.sectioned_top);//32dp

    }
    private boolean isFirstInGroup(int pos) {
        if (pos == 0) {
            return true;
        } else {
            long prevGroupId = mDecorationCallback.getGroupId(pos - 1);
            long groupId = mDecorationCallback.getGroupId(pos);
            return prevGroupId != groupId;
        }
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos=parent.getChildPosition(view);
        Log.i(TAG, "getItemOffsets: "+pos);
        long groupId=mDecorationCallback.getGroupId(pos);
        if (groupId<0) return;
        if (pos == 0 || isFirstInGroup(pos)) {//同组的第一个才添加padding
            outRect.top = topGap;
        } else {
            outRect.top = 0;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            long groupId = mDecorationCallback.getGroupId(position);
            if (groupId < 0) return;
            String textLine = mDecorationCallback.getGroupFirstLine(position).toUpperCase();
            if (position == 0 || isFirstInGroup(position)) {
                float top = view.getTop() - topGap;
                float bottom = view.getTop();
                c.drawRect(left, top, right, bottom, mPaint);//绘制红色矩形
                c.drawText(textLine, left, bottom, mTextPaint);//绘制文本
            }
        }
    }
}
