package com.happy.shareplugin.impl;

import android.content.Context;
import android.content.Intent;

import com.share.IShare;
import com.share.listener.ShareListener;

/**
 * Created by hapi on 2017/12/7.
 */

public class SinaWeiboShare implements IShare {


    private ShareListener listener;

    protected String token;
    protected String openID;
    protected String name;

    private Context context;

    public SinaWeiboShare(Context context) {
        this.context = context;
    }

    @Override
    public void setListener(ShareListener shareListener) {
        listener = shareListener;
    }

    @Override
    public void login() {

    }

    @Override
    public void share(String s) {

    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getOpenID() {
        return openID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
