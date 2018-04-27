package github.didikee.updatechecker;

import android.os.AsyncTask;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import github.didikee.updatechecker.checker.CoolapkChecker;
import github.didikee.updatechecker.checker.GoogleChecker;
import github.didikee.updatechecker.checker.TencentChecker;

/**
 * Created by didikee on 2018/3/1.
 */

public class UpdateChecker {

    public void checkUpdate(String packageName, final UpdateProgressListener updateProgressListener) {
        new MyTask(updateProgressListener).execute(packageName);
    }

    private static class MyTask extends AsyncTask<String, Void, List<ApkInfo>> {
        UpdateProgressListener updateProgressListener;

        private MyTask(UpdateProgressListener updateProgressListener) {
            this.updateProgressListener = updateProgressListener;
        }

        @Override
        protected List<ApkInfo> doInBackground(String... strings) {
            List<ApkInfo> apkInfos = new ArrayList<>();
            String packageName = strings[0];
            if (!TextUtils.isEmpty(packageName)) {
                apkInfos.add(new GoogleChecker().check(packageName));
                apkInfos.add(new TencentChecker().check(packageName));
                apkInfos.add(new CoolapkChecker().check(packageName));
            }
            return apkInfos;
        }

        @Override
        protected void onPostExecute(List<ApkInfo> apkInfos) {
            super.onPostExecute(apkInfos);
            if (updateProgressListener != null) {
                updateProgressListener.onSuccess(apkInfos);
            }
        }
    }

}
