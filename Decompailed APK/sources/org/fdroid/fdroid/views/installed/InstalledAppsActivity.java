package org.fdroid.fdroid.views.installed;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.util.Consumer;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.qos.logback.core.CoreConstants;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.List;
import org.fdroid.database.AppListItem;
import org.fdroid.database.AppPrefs;
import org.fdroid.database.AppPrefsDao;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.UiUtils;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.data.DBHelper;

/* JADX INFO: loaded from: classes2.dex */
public class InstalledAppsActivity extends AppCompatActivity {
    private InstalledAppListAdapter adapter;
    private RecyclerView appList;
    private FDroidDatabase db;
    private TextView emptyState;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        FDroidApp fDroidApp = (FDroidApp) getApplication();
        fDroidApp.setSecureWindow(this);
        fDroidApp.applyPureBlackBackgroundInDarkTheme(this);
        EdgeToEdge.enable(this);
        super.onCreate(bundle);
        setContentView(R.layout.installed_apps_layout);
        setSupportActionBar((MaterialToolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.adapter = new InstalledAppListAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.app_list);
        this.appList = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.appList.setLayoutManager(new LinearLayoutManager(this));
        this.appList.setAdapter(this.adapter);
        this.emptyState = (TextView) findViewById(R.id.empty_state);
        FDroidDatabase db = DBHelper.getDb(this);
        this.db = db;
        db.getAppDao().getInstalledAppListItems(getPackageManager()).observe(this, new Observer() { // from class: org.fdroid.fdroid.views.installed.InstalledAppsActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.onLoadFinished((List) obj);
            }
        });
        UiUtils.setupEdgeToEdge(this.appList, false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLoadFinished(List<AppListItem> list) {
        this.adapter.setApps(list);
        if (this.adapter.getItemCount() == 0) {
            this.appList.setVisibility(8);
            this.emptyState.setVisibility(0);
        } else {
            this.appList.setVisibility(0);
            this.emptyState.setVisibility(8);
        }
        AppPrefsDao appPrefsDao = this.db.getAppPrefsDao();
        for (final AppListItem appListItem : list) {
            Utils.observeOnce(appPrefsDao.getAppPrefs(appListItem.getPackageName()), this, new Consumer() { // from class: org.fdroid.fdroid.views.installed.InstalledAppsActivity$$ExternalSyntheticLambda0
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$onLoadFinished$0(appListItem, (AppPrefs) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onLoadFinished$0(AppListItem appListItem, AppPrefs appPrefs) {
        if (appPrefs.getIgnoreVersionCodeUpdate() > 0) {
            this.adapter.updateItem(appListItem, appPrefs);
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.installed_apps, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_share) {
            StringBuilder sb = new StringBuilder();
            sb.append("packageName,versionCode,versionName\n");
            for (int i = 0; i < this.adapter.getItemCount(); i++) {
                App item = this.adapter.getItem(i);
                if (item != null) {
                    sb.append(item.packageName);
                    sb.append(CoreConstants.COMMA_CHAR);
                    sb.append(item.installedVersionCode);
                    sb.append(CoreConstants.COMMA_CHAR);
                    sb.append(item.installedVersionName);
                    sb.append('\n');
                }
            }
            String string = getString(R.string.send_installed_apps);
            try {
                startActivity(Intent.createChooser(new ShareCompat.IntentBuilder(this).setSubject(string).setChooserTitle(string).setText(sb.toString()).setType("text/csv").getIntent(), string));
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(this, R.string.no_handler_app_generic, 1).show();
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
