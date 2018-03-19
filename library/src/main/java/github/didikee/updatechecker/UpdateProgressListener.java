package github.didikee.updatechecker;

/**
 * Created by didikee on 2018/3/1.
 */

public interface UpdateProgressListener {
    /**
     * 会尽可能多的获取信息
     * @param version 这个一般都会有
     * @param versionCode 很可能没有，如果没有获取到那么返回-1
     */
    void onSuccess(String version, int versionCode);
}
