package caojun.com.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import caojun.com.myapplication.R;
import caojun.com.myapplication.gobal.App;
import caojun.com.myapplication.util.ShareUtil;
import caojun.com.myapplication.view.fragment.MainFragment;

import static caojun.com.myapplication.gobal.App.names;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.activity_main)
    CoordinatorLayout mActivityMain;


    private List<Fragment> fragments = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initEvent();
    }



    private void init() {
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);

        fragments = new ArrayList<>();
        for (String title  :  App.names) {
            fragments.add(MainFragment.getInstance(title));
        }


        mViewpager.setOffscreenPageLimit(4);
        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return names[position];
            }
        });

        mTab.setupWithViewPager(mViewpager);
    }

    private void initEvent() {

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mViewpager , "小飞机~~", Snackbar.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main , menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share_app:
                ShareUtil.shareText(this , "给你推荐一个超帮的App!\n https://github.com/caojunJackson/MyGankGirlPro");
                break;
            case R.id.action_about_app:
                startActivity(new Intent(MainActivity.this , AboutApp.class));
                break;
            case R.id.action_about_me:
                startActivity(new Intent(MainActivity.this , AboutMe.class));

                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
