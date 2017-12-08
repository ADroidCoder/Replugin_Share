package com.share;

import android.content.Context;

/**
 * Created by hapi on 2017/12/7.
 */

public interface IShareFactory {

    IShare getShare(String name, Context context);

}
