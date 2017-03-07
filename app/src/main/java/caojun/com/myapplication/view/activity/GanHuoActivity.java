package caojun.com.myapplication.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import caojun.com.myapplication.R;
import caojun.com.myapplication.util.ShareUtil;

/**
 * Created by tiger on 2017/3/6.
 */

public class GanHuoActivity extends AppCompatActivity {

    @BindView(R.id.ganhuo_toolbar)
    Toolbar mGanhuoToolbar;
    @BindView(R.id.loading)
    FrameLayout mLoading;
    @BindView(R.id.webView)
    WebView mWebView;
    private String mDesc;
    private String mUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganhuo);
        ButterKnife.bind(this);
        getData();
        init();
    }
    public void getData() {

        mDesc = getIntent().getStringExtra("desc");
        mUrl = getIntent().getStringExtra("url");
    }
    private void init() {
        mGanhuoToolbar.setTitle(mDesc);
        setSupportActionBar(mGanhuoToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_send_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                mWebView.loadUrl(mUrl);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mLoading.setVisibility(View.VISIBLE);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress == 100){
                    mLoading.setVisibility(View.GONE);
                }
            }
        });

        mWebView.loadUrl(mUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.ganhuo , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share_ganhuo:
                ShareUtil.shareText(this, mDesc+"\n"+mUrl);
                break;
            case R.id.action_copy_url:
                ShareUtil.copyToClipboard(this , mUrl , mWebView);
                break;
            case R.id.action_refresh:
                mWebView.reload();
                break;

            case R.id.action_open_browser:
                startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(mUrl)));
                break;
            default:
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mWebView!=null){
            mWebView.onResume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(mWebView!= null){
            mWebView.onPause();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mWebView != null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }

    }
}
