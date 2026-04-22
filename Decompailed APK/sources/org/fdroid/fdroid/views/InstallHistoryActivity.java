package org.fdroid.fdroid.views;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import com.google.android.material.appbar.MaterialToolbar;
import fi.iki.elonen.NanoHTTPD;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.installer.InstallHistoryService;
import org.fdroid.fdroid.work.FDroidMetricsWorker;

/* JADX INFO: loaded from: classes2.dex */
public class InstallHistoryActivity extends AppCompatActivity {
    static final String EXTRA_SHOW_FDROID_METRICS = "showFDroidMetrics";
    public static final String TAG = "InstallHistoryActivity";
    private String appName;
    private MenuItem showMenuItem;
    private boolean showingInstallHistory;
    private TextView textView;
    private MaterialToolbar toolbar;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        FDroidApp fDroidApp = (FDroidApp) getApplication();
        fDroidApp.setSecureWindow(this);
        fDroidApp.applyPureBlackBackgroundInDarkTheme(this);
        EdgeToEdge.enable(this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_install_history);
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        this.toolbar = materialToolbar;
        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.textView = (TextView) findViewById(R.id.text);
        this.appName = getString(R.string.app_name);
        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra(EXTRA_SHOW_FDROID_METRICS, false)) {
            showFDroidMetricsReport();
        } else {
            showInstallHistory();
        }
    }

    private void showInstallHistory() {
        String string;
        try {
            ContentResolver contentResolver = getContentResolver();
            Uri uri = InstallHistoryService.LOG_URI;
            Cursor cursorQuery = contentResolver.query(uri, null, null, null, null);
            if (cursorQuery != null) {
                cursorQuery.moveToFirst();
                cursorQuery.close();
            }
            string = IOUtils.toString(new FileInputStream(contentResolver.openFileDescriptor(uri, "r").getFileDescriptor()), Charset.defaultCharset());
        } catch (IOException e) {
            e = e;
            e.printStackTrace();
            string = "";
        } catch (IllegalStateException e2) {
            e = e2;
            e.printStackTrace();
            string = "";
        } catch (SecurityException e3) {
            e = e3;
            e.printStackTrace();
            string = "";
        }
        this.toolbar.setTitle(getString(R.string.install_history));
        this.textView.setText(string);
        this.showingInstallHistory = true;
        MenuItem menuItem = this.showMenuItem;
        if (menuItem != null) {
            menuItem.setVisible(Preferences.get().isSendingToFDroidMetrics());
            this.showMenuItem.setTitle(R.string.menu_show_fdroid_metrics_report);
        }
    }

    private void showFDroidMetricsReport() {
        this.toolbar.setTitle(getString(R.string.fdroid_metrics_report, this.appName));
        this.textView.setText(FDroidMetricsWorker.generateReport(this));
        this.showingInstallHistory = false;
        MenuItem menuItem = this.showMenuItem;
        if (menuItem != null) {
            menuItem.setVisible(Preferences.get().isSendingToFDroidMetrics());
            this.showMenuItem.setTitle(R.string.menu_show_install_history);
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.install_history, menu);
        MenuItem menuItemFindItem = menu.findItem(R.id.menu_show);
        this.showMenuItem = menuItemFindItem;
        menuItemFindItem.setVisible(Preferences.get().isSendingToFDroidMetrics());
        if (this.showingInstallHistory) {
            this.showMenuItem.setTitle(R.string.menu_show_fdroid_metrics_report);
        } else {
            this.showMenuItem.setTitle(R.string.menu_show_install_history);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String string;
        int itemId = menuItem.getItemId();
        if (itemId == R.id.menu_share) {
            ShareCompat.IntentBuilder intentBuilderFrom = ShareCompat.IntentBuilder.from(this);
            if (this.showingInstallHistory) {
                StringBuilder sb = new StringBuilder();
                sb.append("Repos:\n");
                for (Repository repository : FDroidApp.getRepoManager(this).getRepositories()) {
                    if (repository.getEnabled()) {
                        sb.append("* ");
                        sb.append(repository.getAddress());
                        sb.append('\n');
                    }
                }
                string = getString(R.string.send_install_history);
                intentBuilderFrom.setText(sb.toString()).setStream(InstallHistoryService.LOG_URI).setType(NanoHTTPD.MIME_PLAINTEXT).setSubject(getString(R.string.send_history_csv, this.appName)).setChooserTitle(string);
            } else {
                string = getString(R.string.send_fdroid_metrics_report, this.appName);
                intentBuilderFrom.setText(this.textView.getText()).setType("application/json").setSubject(getString(R.string.send_fdroid_metrics_json, this.appName)).setChooserTitle(string);
            }
            Intent intent = intentBuilderFrom.getIntent();
            intent.addFlags(1);
            try {
                startActivity(Intent.createChooser(intent, string));
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(this, R.string.no_handler_app_generic, 1).show();
            }
        } else if (itemId == R.id.menu_delete) {
            if (this.showingInstallHistory) {
                getContentResolver().delete(InstallHistoryService.LOG_URI, null, null);
            }
            this.textView.setText("");
        } else if (itemId == R.id.menu_show) {
            if (this.showingInstallHistory) {
                showFDroidMetricsReport();
            } else {
                showInstallHistory();
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
