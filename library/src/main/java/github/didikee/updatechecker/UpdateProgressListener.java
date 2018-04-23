package github.didikee.updatechecker;

import java.util.List;

/**
 * Created by didikee on 2018/3/1.
 */

public interface UpdateProgressListener {
    /**
     * 会尽可能多的获取信息
     */
    void onSuccess(List<ApkInfo> result);
}
