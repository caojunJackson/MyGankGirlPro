/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package caojun.com.myapplication.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Created by gc on 2016/11/6.
 */
public class NetworkUtils {

    private NetworkUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static final int ACTION_WIFI_SETTINGS = 0;
    public static final int ACTION_DATA_ROAMING_SETTINGS = 1;

    /**
     * 判断网络是否可用，需要加上访问网络状态的权限
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvaiable(Context context) {
        ConnectivityManager connectivity = getConnectivityManager(context);
        if (connectivity == null) {
            return false;
        }
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return false;
        }
        return true;
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivity = getConnectivityManager(context);
        if (null != connectivity) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是WiFi网络
     *
     * @param context
     * @return
     */
    public static boolean isWifiConn(Context context) {
        boolean isCommected = isConnected(context);
        if (isCommected) {
            ConnectivityManager connectivity = getConnectivityManager(context);
            if (connectivity == null)
                return false;
            return connectivity.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    /**
     * 获取网络连接管理
     *
     * @param context
     * @return
     */
    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * 打开网络设置界面 整体
     *
     * @param activity
     */
    public static void openSetting(Activity activity) {
        //整体
        activity.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
    }

    /**
     * 打开网络设置界面 WIFI/流量
     *
     * @param activity
     * @param i 0:WIFI/1:流量
     */
    public static void openSetting(Activity activity, int i) {
        if (i == ACTION_WIFI_SETTINGS) {
            //WIFI
            activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        } else if (i == ACTION_DATA_ROAMING_SETTINGS) {
            //流量
            activity.startActivity(new Intent(
                    Settings.ACTION_DATA_ROAMING_SETTINGS));
        }
    }

}
