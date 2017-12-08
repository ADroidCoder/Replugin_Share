package com.happy.shareplugin.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.share.IShare;
import com.share.ShareHelper;
import com.share.listener.ShareEvent;
import com.share.listener.ShareListener;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hapi on 2017/12/7.
 */

public class TencentQQShare implements IShare {


    private ShareListener mListener;
    protected String mToken;
    protected String mOpenID;
    protected String NAME = "QQ";

    public final static String APP_NAME = "测试";
    public final static String QQ_APPID = "222222";

    private Tencent mTencent;
    private Context mContext;

    private ShareOrLoginListener mAllListener;


    public TencentQQShare(Context context) {
        this.mContext = context;
        mTencent = Tencent.createInstance(QQ_APPID, mContext);
        mAllListener = new ShareOrLoginListener();

    }

    @Override
    public void setListener(ShareListener shareListener) {
        mListener = shareListener;
    }

    @Override
    public void login() {

    }

    @Override
    public void share(String model) {
        mListener.onShareEvent(new ShareEvent(100, new Object[]{NAME, ShareHelper.TYPE_LOGIN, model}));
        if (TextUtils.isEmpty(model)) {
            return;
        }
        if (null == mTencent || mTencent.getOpenId() != null) {
            if (mContext != null) {
                mTencent = Tencent.createInstance(QQ_APPID, mContext);
            } else {
                //Log.d("");
                if (mListener != null) {
                    mListener.onShareEvent(new ShareEvent(-1, new Object[]{NAME, ShareHelper.TYPE_LOGIN, "context为null"}));
                }
                return;
            }
        }
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "测试分享");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "今天天气不错");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,"https://github.com/Qihoo360/RePlugin");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://download.easyicon.net/png/1095593/256/");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, APP_NAME);
        if (mAllListener == null)
            mAllListener = new ShareOrLoginListener();
        mAllListener.setType(ShareHelper.SHARE);
        mTencent.shareToQQ((Activity) mContext, params, mAllListener);
    }

    @Override
    public String getToken() {
        return mToken;
    }

    @Override
    public String getOpenID() {
        return mOpenID;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_QQ_SHARE || requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mAllListener);
        }
    }


    private class ShareOrLoginListener implements IUiListener {

        public int type = ShareHelper.SHARE;

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public void onComplete(Object response) {
            if (type == ShareHelper.SHARE) {
                if (mListener != null)
                    mListener.onShareEvent(new ShareEvent(0, new Object[]{NAME, type, "suc"}));
            } else if (type == ShareHelper.TYPE_LOGIN) {
                try {
                    String openID = ((JSONObject) response).getString("openid");
                    String access_token = ((JSONObject) response).getString("access_token");
                    String expires = ((JSONObject) response).getString("expires_in");
                    mToken = (access_token);
                    mOpenID = (openID);
                    mTencent.setAccessToken(access_token, expires);
                    mTencent.setOpenId(openID);
                    openID = null;
                    access_token = null;

                    if (mListener != null)
                        mListener.onShareEvent(new ShareEvent(0, new Object[]{NAME, type, "suc"}));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onError(UiError uiError) {
            if (mListener != null)
                mListener.onShareEvent(new ShareEvent(-1, new Object[]{NAME, type, uiError.errorMessage}));
        }

        @Override
        public void onCancel() {
            if (mListener != null)
                mListener.onShareEvent(new ShareEvent(-2, new Object[]{NAME, type, "cancel"}));
        }
    }


}
