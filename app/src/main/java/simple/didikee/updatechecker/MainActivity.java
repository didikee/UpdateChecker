package simple.didikee.updatechecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import github.didikee.updatechecker.UpdateProgressListener;
import github.didikee.updatechecker.UpdateVersionChecker;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DemoChecker";
    private static final String SAMPLE_PACKAGE_NAME = "com.didikee.gifparser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        UpdateVersionChecker updateVersionChecker = new UpdateVersionChecker();
        updateVersionChecker.checkUpdate(new UpdateProgressListener() {
            @Override
            public void onSuccess(String version, int versionCode) {
                Log.d(TAG, "version: " + version);
            }
        });

    }


}
