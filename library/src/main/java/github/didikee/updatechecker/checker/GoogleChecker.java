package github.didikee.updatechecker.checker;

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

public class GoogleChecker implements IChecker {

    @Override
    public ApkInfo check(String packageName) {
        ApkInfo apkInfo = new ApkInfo(AppMarkets.GOOGLE_PLAY);
        try {
            Document document2 = Jsoup.connect(AppMarkets.getGooglePlayLink(packageName))
                    .timeout(Constant.TIME_OUT)
                    .userAgent(Constant.GOOGLE_CHROME)
                    .get();
            Elements metaInfoElements = document2.getElementsByClass("meta-info");
            if (metaInfoElements != null && metaInfoElements.size() > 0) {
                for (Element metaInfoElement : metaInfoElements) {
                    Elements contentElements = metaInfoElement.getElementsByClass("content");
                    if (contentElements != null && contentElements.size() > 0) {
                        for (Element contentElement : contentElements) {
                            if (contentElement.hasAttr("itemprop")) {
                                String itemprop = contentElement.attr("itemprop");
                                if ("softwareVersion".equalsIgnoreCase(itemprop)) {
                                    apkInfo.versionName = contentElement.text().trim();
                                    return apkInfo;
                                }
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
