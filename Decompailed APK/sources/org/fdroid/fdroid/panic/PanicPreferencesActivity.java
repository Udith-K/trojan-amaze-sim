package org.fdroid.fdroid.panic;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
public class PanicPreferencesActivity extends AppCompatActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        FDroidApp fDroidApp = (FDroidApp) getApplication();
        fDroidApp.setSecureWindow(this);
        fDroidApp.applyPureBlackBackgroundInDarkTheme(this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_panic_settings);
        setSupportActionBar((MaterialToolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
