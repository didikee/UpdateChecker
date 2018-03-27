package github.didikee.updatechecker;

import android.os.AsyncTask;
import android.text.TextUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by didikee on 2018/3/1.
 */

public class UpdateVersionChecker {


    public void checkUpdate(final UpdateProgressListener updateProgressListener) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {

                    Document document = Jsoup.connect("http://android.myapp.com/myapp/detail.htm?apkName=com.didikee.gifparser")
                            .get();
                    if (document != null) {
                        Elements infoDataElements = document.getElementsByClass("det-othinfo-data");
                        if (infoDataElements != null && infoDataElements.size() > 0) {
                            for (Element infoDataElement : infoDataElements) {
                                String text = infoDataElement.text();
                                if (!TextUtils.isEmpty(text)) {
                                    String maybeVersionText = text.toLowerCase();
                                    if (maybeVersionText.startsWith("v")) {
                                        String version = maybeVersionText.replace("v", "");
                                        if (updateProgressListener != null) {
                                            updateProgressListener.onSuccess(version.trim(), -1);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    Document document2 = Jsoup.connect("https://play.google.com/store/apps/details?id=com.didikee.gifparser")
                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
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
                                            String version = contentElement.text().trim();
                                            if (updateProgressListener != null) {
                                                updateProgressListener.onSuccess(version, -1);
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();
    }

}
