package caojun.com.myapplication.util;

import android.util.Log;

/**
 * Created by tiger on 2017/3/6.
 */

public class LogUtils {
    public static int  VERBOSE = 1;
    public static int  DEBUG = 2;
    public static int  INFO = 3;
    public static int  WARN = 4;
    public static int  ERROR = 5;

    public static int NONE= 6;

    private static int level = DEBUG;

    public static void v(Object obj , String msg){
        if(level <= VERBOSE){
            Log.v(obj.getClass().getSimpleName() , msg);
        }
    }

    public static void v(String tag , String msg){
        if(level <= VERBOSE){
            Log.v(tag, msg);
        }
    }

    public static void d(Object obj , String msg){
        if(level <= DEBUG){
            Log.d(obj.getClass().getSimpleName() , msg);
        }
    }

    public static void d(String tag , String msg){
        if(level <= DEBUG){
            Log.d(tag, msg);
        }
    }

    public static void i(Object obj , String msg){
        if(level <= INFO){
            Log.i(obj.getClass().getSimpleName() , msg);
        }
    }

    public static void i(String tag , String msg){
        if(level <= INFO){
            Log.i(tag, msg);
        }
    }


    public static void w(Object obj , String msg){
        if(level <= WARN){
            Log.w(obj.getClass().getSimpleName() , msg);
        }
    }

    public static void w(String tag , String msg){
        if(level <= WARN){
            Log.w(tag, msg);
        }
    }

    public static void e(Object obj , String msg){
        if(level <= ERROR){
            Log.e(obj.getClass().getSimpleName() , msg);
        }
    }

    public static void e(String tag , String msg){
        if(level <= ERROR){
            Log.e(tag, msg);
        }
    }
}
