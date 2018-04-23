package github.didikee.updatechecker;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import github.didikee.updatechecker.checker.CoolapkChecker;
import github.didikee.updatechecker.checker.GoogleChecker;
import github.didikee.updatechecker.checker.TencentChecker;

/**
 * Created by didikee on 2018/3/1.
 */

public class UpdateVersionChecker {
    private static final String PACKAGE_NAME = "com.didikee.gifparser";

    public void checkUpdate(final UpdateProgressListener updateProgressListener) {
        new MyTask(updateProgressListener).execute();
    }

    private static class MyTask extends AsyncTask<Void, Void, List<ApkInfo>> {
        UpdateProgressListener updateProgressListener;

        private MyTask(UpdateProgressListener updateProgressListener) {
            this.updateProgressListener = updateProgressListener;
        }

        @Override
        protected List<ApkInfo> doInBackground(Void... voids) {
            List<ApkInfo> apkInfos = new ArrayList<>();
            apkInfos.add(new GoogleChecker().check(PACKAGE_NAME));
            apkInfos.add(new TencentChecker().check(PACKAGE_NAME));
            apkInfos.add(new CoolapkChecker().check(PACKAGE_NAME));
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
