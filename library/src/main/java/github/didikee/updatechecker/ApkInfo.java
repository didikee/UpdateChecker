package github.didikee.updatechecker;

/**
 * Created by didikee on 2018/4/21.
 */

public class ApkInfo {
    public ApkInfo(String market) {
        this.market = market;
    }

    public String market;
    /**
     * version name
     */
    public String versionName = "";
    /**
     * version code
     */
    public int versionCode = -1;
}
