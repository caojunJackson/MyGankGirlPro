package caojun.com.myapplication.gobal;

import android.app.Application;

/**
 * Created by tiger on 2017/3/6.
 */

public class App extends Application {
    public static  String[] names = {"福利" ,"Android" , "iOS" , "休息视频"};

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
