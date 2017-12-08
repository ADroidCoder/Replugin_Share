package com.happy.shareplugin.impl;

import android.content.Context;
import android.text.TextUtils;

import com.share.IShare;
import com.share.IShareFactory;

/**
 * Created by hapi on 2017/12/7.
 */

public class IShareImpl implements IShareFactory {

    private static IShareFactory iShareFactory = new IShareImpl();

    public static IShareFactory getInstance() {
        return iShareFactory;
    }

    @Override
    public IShare getShare(String name, Context context) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        IShare share = null;
        if ("QQ".equals(name)) {
            share = new TencentQQShare(context);
        } else if ("WX".equals(name)) {
            share = new WeixinShare(context);
        } else if ("WB".equals(name)) {
            share = new SinaWeiboShare(context);
        }
        return share;
    }
}
