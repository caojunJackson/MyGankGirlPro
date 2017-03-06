package caojun.com.myapplication.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tiger on 2017/3/6.
 */

public class GankRetrofit {

    private static Retrofit mInstance = null;
    public static String BASEURL = "http://gank.io/";

    public static Retrofit getRetrofit(){
        if(mInstance == null){
            synchronized (GankRetrofit.class){
                if(mInstance == null){
                    mInstance = new Retrofit.Builder()
                            .baseUrl(BASEURL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }

        return mInstance;

    }

}
