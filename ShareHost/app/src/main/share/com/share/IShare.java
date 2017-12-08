package com.share;

import android.content.Intent;

import com.share.listener.ShareListener;

/**
 * Created by hapi on 2017/12/7.
 */

public interface IShare {


    void setListener(ShareListener listener);

    void login();

    void share(String jsonObj);

    String getToken();

    String getOpenID();

    String getName();

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
