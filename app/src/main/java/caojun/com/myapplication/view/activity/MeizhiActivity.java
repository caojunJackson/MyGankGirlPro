package caojun.com.myapplication.view.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import caojun.com.myapplication.R;
import caojun.com.myapplication.util.ImageUtil;
import caojun.com.myapplication.util.ShareUtil;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by tiger on 2017/3/6.
 */

public class MeizhiActivity extends BaseActivity {

    @BindView(R.id.meizhi_toolbar)
    Toolbar mMeizhiToolbar;
    @BindView(R.id.meizhi)
    ImageView mMeizhi;
    private String mDesc;
    private String mUrl;
    private PhotoViewAttacher mAttacher;
    private Bitmap mBitmap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meizhi);
        ButterKnife.bind(this);
        getData();
        init();

    }

    private void getData() {
        mDesc = getIntent().getStringExtra("desc");
        mUrl = getIntent().getStringExtra("url");

    }

    private void init() {
        mMeizhiToolbar.setTitle(mDesc);
        setSupportActionBar(mMeizhiToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_send_black_24dp);

        mAttacher = new PhotoViewAttacher(mMeizhi);
        Glide.with(getApplicationContext())
                .load(mUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mMeizhi.setImageBitmap(resource);
                        mAttacher.update();
                        mBitmap = resource;
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meizhi , menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_meizhi:
                BaseActivity.requestPermission(new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE ,Manifest.permission.READ_EXTERNAL_STORAGE} , new BaseActivity.onRequestPermission(){

                    @Override
                    public void onGrant() {
                        ImageUtil.saveImage(MeizhiActivity.this , mUrl , mBitmap , mMeizhi , "save");
                    }

                    @Override
                    public void onDeny(List<String> denyedPermissions) {
                        Snackbar.make(mMeizhi , "你拒绝了写入sd卡的权限  ", Snackbar.LENGTH_LONG).setAction("Check", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                BaseActivity.getAppDetailSettingIntent();
                            }
                        });
                    }
                });


                break;
            case R.id.action_share_meizhi:
                BaseActivity.requestPermission(new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE ,Manifest.permission.READ_EXTERNAL_STORAGE} , new BaseActivity.onRequestPermission(){

                    @Override
                    public void onGrant() {
                        ShareUtil.shareImage(MeizhiActivity.this ,  ImageUtil.saveImage(MeizhiActivity.this , mUrl , mBitmap , mMeizhi , "share"));
                    }

                    @Override
                    public void onDeny(List<String> denyedPermissions) {
                        Snackbar.make(mMeizhi , "你拒绝了写入sd卡的权限  ", Snackbar.LENGTH_LONG).setAction("Check", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                BaseActivity.getAppDetailSettingIntent();
                            }
                        });
                    }
                });
                break;

            case R.id.action_click_meizhi:
                Snackbar.make(mMeizhi , "click me ! 如花似玉 ", Snackbar.LENGTH_LONG).show();
                break;

            case  android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        
        
        return super.onOptionsItemSelected(item);
    }
}
