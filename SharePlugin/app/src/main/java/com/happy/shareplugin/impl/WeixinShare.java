package com.happy.shareplugin.impl;

import android.content.Context;
import android.content.Intent;

import com.share.IShare;
import com.share.listener.ShareListener;

/**
 * Created by hapi on 2017/12/7.
 */

public class WeixinShare implements IShare {


    private Context context;

    public WeixinShare(Context context) {
        this.context = context;
    }

    @Override
    public void setListener(ShareListener shareListener) {

    }

    @Override
    public void login() {

    }

    @Override
    public void share(String s) {

    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public String getOpenID() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
