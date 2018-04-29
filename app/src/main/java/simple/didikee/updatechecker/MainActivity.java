package simple.didikee.updatechecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import github.didikee.updatechecker.ApkInfo;
import github.didikee.updatechecker.UpdateProgressListener;
import github.didikee.updatechecker.UpdateChecker;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DemoChecker";
    private static final String SAMPLE_PACKAGE_NAME = "com.didikee.gifparser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        UpdateChecker updateChecker = new UpdateChecker();
        updateChecker.checkUpdate(SAMPLE_PACKAGE_NAME,new UpdateProgressListener() {
            @Override
            public void onSuccess(List<ApkInfo> result) {
                for (ApkInfo apkInfo : result) {
                    Log.d(TAG, "apkInfo market: " + apkInfo.market + " versionName: " + apkInfo.versionName);
                }
            }
        });

    }


}
