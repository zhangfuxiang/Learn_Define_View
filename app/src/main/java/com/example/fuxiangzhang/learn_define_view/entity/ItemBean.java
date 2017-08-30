package com.example.fuxiangzhang.learn_define_view.entity;

/**
 * Created by Fuxiang.Zhang on 2017/8/29.
 */

public class ItemBean {

    private int app_code;
    private String content;

    public ItemBean(int mApp_code, String mContent) {
        app_code = mApp_code;
        content = mContent;
    }

    public ItemBean() {

    }

    public int getApp_code() {
        return app_code;
    }

    public void setApp_code(int mApp_code) {
        app_code = mApp_code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String mContent) {
        content = mContent;
    }
}
