package github.didikee.updatechecker.checker;

import github.didikee.updatechecker.ApkInfo;

/**
 * Created by didikee on 2018/4/21.
 */

public interface IChecker {
    ApkInfo check(String packageName);
}
