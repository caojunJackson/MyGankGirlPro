package caojun.com.myapplication.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import caojun.com.myapplication.R;
import caojun.com.myapplication.model.GanHuo;
import caojun.com.myapplication.retrofit.GankRetrofit;
import caojun.com.myapplication.retrofit.GankServer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tiger on 2017/3/6.
 */

public class AboutApp extends BaseActivity {

    @BindView(R.id.image_about)
    ImageView mImageAbout;
    @BindView(R.id.aboutapp_toolbar)
    Toolbar mAboutappToolbar;
    @BindView(R.id.aboutapp_appbar)
    AppBarLayout mAboutappAppbar;
    @BindView(R.id.tv_appdesc)
    TextView mTvAppdesc;
    @BindView(R.id.aboutapp_fab)
    FloatingActionButton mAboutappFab;
    @BindView(R.id.aboutapp_collapsing)
    CollapsingToolbarLayout mAboutappCollapsing;

    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aboutapp);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        setImageBar();

        mAboutappCollapsing.setTitle("About App");
        setSupportActionBar(mAboutappToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAboutappAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

                if(percentage > 0.9f){
                    page++;
                    setImageBar();
                }
            }
        });

        mTvAppdesc.setText("MyGankGrilPro");
    }

    private void setImageBar() {
        GankRetrofit.getRetrofit()
                .create(GankServer.class)
                .getGanHuo("福利", 1, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GanHuo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Glide.with(getApplicationContext())
                                .load(R.drawable.wall_picture)
                                .into(mImageAbout);
                    }

                    @Override
                    public void onNext(GanHuo ganHuo) {

                        Glide.with(getApplicationContext())
                                .load(ganHuo.getResults().get(0).getUrl())
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .centerCrop()
                                .into(mImageAbout);
                    }
                });

    }

}
