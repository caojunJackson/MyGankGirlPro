package caojun.com.myapplication.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import caojun.com.myapplication.R;

/**
 * Created by tiger on 2017/3/6.
 */

public class SplashActivity extends AppCompatActivity {

    private ImageView iv_splash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {
        iv_splash = (ImageView) findViewById(R.id.iv_splash);

        //从网络拿到每日最新图片


    }
}
