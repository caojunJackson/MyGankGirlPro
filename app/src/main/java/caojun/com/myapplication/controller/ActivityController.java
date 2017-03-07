package caojun.com.myapplication.controller;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiger on 2017/3/7.
 */

public class ActivityController {

    public static List<Activity> mActivities = new ArrayList<>();

    public static void addActivity(Activity activity){
        if(activity != null){
            mActivities.add(activity);
        }
    }

    public static void removeActivity(Activity activity){
        if(activity == null){
            return;
        }

        if(mActivities.isEmpty()){
            return;
        }

        mActivities.remove(activity);
    }


    public static Activity getTopActivity(){
        if(mActivities.isEmpty()){
            return null;
        }

        return mActivities.get(mActivities.size()-1);
    }
}
