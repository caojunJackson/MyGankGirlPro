package caojun.com.myapplication.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import caojun.com.myapplication.R;
import caojun.com.myapplication.adapter.GanHuoAdapter;
import caojun.com.myapplication.adapter.MeiZhiAdapter;
import caojun.com.myapplication.model.GanHuo;
import caojun.com.myapplication.retrofit.GankRetrofit;
import caojun.com.myapplication.retrofit.GankServer;
import caojun.com.myapplication.util.LogUtils;
import caojun.com.myapplication.view.activity.GanHuoActivity;
import caojun.com.myapplication.view.activity.MeizhiActivity;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static caojun.com.myapplication.gobal.App.names;

/**
 * Created by tiger on 2017/3/6.
 */

public class MainFragment extends Fragment implements RecyclerArrayAdapter.OnLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.no_wifi)
    LinearLayout mNoWifi;
    @BindView(R.id.easyRecyclerView)
    EasyRecyclerView mEasyRecyclerView;
    private String mTitle;
    private MeiZhiAdapter mMeiZhiAdapter;

    private int page = 1;
    private GanHuoAdapter mGanHuoAdapter;


    public static MainFragment getInstance(String title) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        mTitle = arguments.getString("title", "福利");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, inflate);
        init();
        initEvent();
        return inflate;
    }

    private void initEvent() {
        mNoWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });


    }

    private void init() {
        if (names[0].equals(mTitle)) {
            mEasyRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL));
            mMeiZhiAdapter = new MeiZhiAdapter(getContext());
            dealAdapter(mMeiZhiAdapter);
        } else {

            mEasyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mGanHuoAdapter = new GanHuoAdapter(getContext());
            dealAdapter(mGanHuoAdapter);
        }
        mEasyRecyclerView.addItemDecoration(new SpaceDecoration(5));

        mEasyRecyclerView.setRefreshListener(this);
        onRefresh();
    }


    private void dealAdapter(final RecyclerArrayAdapter adapter) {
        //        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
        //            @Override
        //            public View onCreateView(ViewGroup parent) {
        //
        //
        //                return null;
        //            }
        //
        //            @Override
        //            public void onBindView(View headerView) {
        //
        //            }
        //        });


        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        mEasyRecyclerView.setAdapterWithProgress(adapter);

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(names[0].equals(mTitle)){
                    startActivity(new Intent(getContext() , MeizhiActivity.class));
                }else{
                    startActivity(new Intent(getContext() , GanHuoActivity.class));
                }
            }
        });
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (names[0].equals(mTitle)) {
                    mMeiZhiAdapter.clear();
                    getData(20, 1);
                } else {
                    mGanHuoAdapter.clear();
                    getData(20, 1);

                }
                page = 2;
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(20, page);
                page++;
            }
        }, 2000);
    }

    public void getData(int count, int page) {
        GankRetrofit.getRetrofit()
                .create(GankServer.class)
                .getGanHuo(mTitle, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GanHuo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(this, e.getCause().toString());
                        mNoWifi.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(GanHuo ganHuo) {
                        LogUtils.e(this, ganHuo.getResults().toString());
                        if (names[0].equals(mTitle)) {
                            mMeiZhiAdapter.addAll(ganHuo.getResults());
                        } else {
                            mGanHuoAdapter.addAll(ganHuo.getResults());
                        }
                    }
                });

    }
}
