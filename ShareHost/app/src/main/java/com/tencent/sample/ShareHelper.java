package com.tencent.sample;


import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.component.dummy.DummyActivity;

import java.lang.reflect.Method;

/**
 * Created by hapi on 2017/12/7.
 */

public class ShareHelper {


    public static <T> T  getShareFac(Class<T> cls) {
        ClassLoader share = RePlugin.fetchClassLoader("share");
        try {
            Class<?> shareImpl = share.loadClass("com.happy.shareplugin.impl.IShareImpl");
            Method getInstance = shareImpl.getDeclaredMethod("getInstance");
            Object invoke = getInstance.invoke(null, new Object[0]);
            return cls.cast(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void registerHookClass(){
        // 辅助找到 在插件里的类
        RePlugin.registerHookingClass("com.tencent.tauth.AuthActivity",
                RePlugin.createComponentName("share","com.tencent.tauth.AuthActivity"),
                DummyActivity.class);

        RePlugin.registerHookingClass(
                "com.tencent.connect.common.AssistActivity",
                RePlugin.createComponentName("share",
                        "com.tencent.connect.common.AssistActivity"),
                DummyActivity.class);
    }

}
