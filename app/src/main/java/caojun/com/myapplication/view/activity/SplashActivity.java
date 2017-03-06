package caojun.com.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import caojun.com.myapplication.R;
import caojun.com.myapplication.model.GanHuo;
import caojun.com.myapplication.retrofit.GankRetrofit;
import caojun.com.myapplication.retrofit.GankServer;
import caojun.com.myapplication.util.LogUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tiger on 2017/3/6.
 */

public class SplashActivity extends AppCompatActivity {

    private ImageView iv_splash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager
                .LayoutParams.FLAG_KEEP_SCREEN_ON);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//            Transition explorer = TransitionInflater.from(this).inflateTransition(android.R
//                    .transition.move);
//            getWindow().setEnterTransition(explorer);
////            getWindow().setExitTransition(explorer);
//        }

        setContentView(R.layout.activity_splash);
        init();

    }

    private void init() {
        iv_splash = (ImageView) findViewById(R.id.iv_splash);

        //从网络拿到每日最新图片
        GankRetrofit.getRetrofit()
                .create(GankServer.class)
                .getGanHuo("福利", 1, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GanHuo>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.e(this, " onCompleted()");
                        animaImage();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(this, " onError() --> " + e.getCause());
                        Glide.with(getApplicationContext())
                                .load(R.drawable.wall_picture)
                                .into(iv_splash);
                        animaImage();
                    }

                    @Override
                    public void onNext(GanHuo ganHuo) {
                        LogUtils.e(this, " onNext()");
                        LogUtils.e("GanhHuo result---> ", ganHuo.getResults().get(0).toString());

                        Glide.with(getApplicationContext())
                                .load(ganHuo.getResults().get(0).getUrl())
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .error(R.drawable.wall_picture)
                                .centerCrop()
                                .into(iv_splash);
                    }
                });
    }


    private void animaImage() {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation
                .RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        iv_splash.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
