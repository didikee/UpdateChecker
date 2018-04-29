package github.didikee.updatechecker.checker;

import android.text.TextUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import github.didikee.updatechecker.ApkInfo;
import github.didikee.updatechecker.AppMarkets;
import github.didikee.updatechecker.Constant;

/**
 * Created by didikee on 2018/4/21.
 */

public class TencentChecker implements IChecker {

    @Override
    public ApkInfo check(String packageName) {
        ApkInfo apkInfo = new ApkInfo(AppMarkets.TENCENT);
        try {

            Document document = Jsoup.connect(AppMarkets.getTencentLink(packageName))
                    .timeout(Constant.TIME_OUT)
                    .userAgent(Constant.GOOGLE_CHROME)
                    .get();
            if (document != null) {
                Elements infoDataElements = document.getElementsByClass("det-othinfo-data");
                if (infoDataElements != null && infoDataElements.size() > 0) {
                    for (Element infoDataElement : infoDataElements) {
                        String text = infoDataElement.text();
                        if (!TextUtils.isEmpty(text)) {
                            String maybeVersionText = text.toLowerCase();
                            if (maybeVersionText.startsWith("v")) {
                                String versionName = maybeVersionText.replace("v", "");
                                apkInfo.versionName = versionName;
                                return apkInfo;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apkInfo;
    }
}
