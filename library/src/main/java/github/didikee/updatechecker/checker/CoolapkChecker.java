package github.didikee.updatechecker.checker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import github.didikee.updatechecker.ApkInfo;
import github.didikee.updatechecker.AppMarkets;

/**
 * Created by didikee on 2018/4/21.
 */

public class CoolapkChecker implements IChecker {
    @Override
    public ApkInfo check(String packageName) {
        ApkInfo apkInfo = new ApkInfo(AppMarkets.COOLAPK);
        try {
            Document document = Jsoup.connect(AppMarkets.getCoolapkLink(packageName))
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
                    .get();
            Elements list_app_info = document.getElementsByClass("list_app_info");
            if (list_app_info != null && list_app_info.size() > 0) {
                String trim = list_app_info.get(0).text().trim();
                apkInfo.versionName = trim;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apkInfo;
    }
}
