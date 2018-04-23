package github.didikee.updatechecker;

/**
 * Created by didikee on 2018/3/1.
 * 支持的应用市场
 */

public class AppMarkets {
    public static final String GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=";
    public static final String TENCENT = "http://android.myapp.com/myapp/detail.htm?apkName=";
    public static final String COOLAPK = "https://www.coolapk.com/apk/";

    public static String getGooglePlayLink(String packageName) {
        return GOOGLE_PLAY + packageName;
    }

    public static String getTencentLink(String packageName) {
        return TENCENT + packageName;
    }

    public static String getCoolapkLink(String packageName) {
        return COOLAPK + packageName;
    }
}
