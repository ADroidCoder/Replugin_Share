package com.tencent.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.share.IShare;
import com.share.IShareFactory;
import com.share.listener.ShareEvent;
import com.share.listener.ShareListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 通过反射获取到插件里的分享工厂类
        boolean share = RePlugin.isPluginInstalled("share");
        Log.d("MainActivity", "share:" + share);

    }

    IShare qq;

    public void ClickQQ(View view) {
        IShareFactory shareFac = ShareHelper.getShareFac(IShareFactory.class);
        qq = shareFac.getShare("QQ", this);
        qq.setListener(new ShareListener() {
            @Override
            public void onShareEvent(ShareEvent shareEvent) {
                switch (shareEvent.getType()) {
                    case 0:
                        Toast.makeText(MainActivity.this, "suc", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        break;
                    case -2:
                        Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
        qq.share("share");

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        qq.onActivityResult(requestCode, resultCode, data);
    }
}
