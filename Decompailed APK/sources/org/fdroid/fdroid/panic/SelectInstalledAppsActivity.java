package org.fdroid.fdroid.panic;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.List;
import org.fdroid.database.AppListItem;
import org.fdroid.database.AppPrefs;
import org.fdroid.database.AppPrefsDao;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.views.installed.InstalledAppListAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class SelectInstalledAppsActivity extends AppCompatActivity {
    private InstalledAppListAdapter adapter;
    private RecyclerView appList;
    private int checkId;
    private FDroidDatabase db;
    private TextView emptyState;
    private Preferences prefs;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        FDroidApp fDroidApp = (FDroidApp) getApplication();
        fDroidApp.setSecureWindow(this);
        fDroidApp.applyPureBlackBackgroundInDarkTheme(this);
        super.onCreate(bundle);
        setContentView(R.layout.installed_apps_layout);
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.toolbar);
        materialToolbar.setTitle(getString(R.string.panic_add_apps_to_uninstall));
        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.adapter = new SelectInstalledAppListAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.app_list);
        this.appList = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.appList.setLayoutManager(new LinearLayoutManager(this));
        this.appList.setAdapter(this.adapter);
        this.emptyState = (TextView) findViewById(R.id.empty_state);
        FDroidDatabase db = DBHelper.getDb(this);
        this.db = db;
        db.getAppDao().getInstalledAppListItems(getPackageManager()).observe(this, new Observer() { // from class: org.fdroid.fdroid.panic.SelectInstalledAppsActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.onLoadFinished((List) obj);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.prefs = Preferences.get();
    }

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
            Utils.observeOnce(appPrefsDao.getAppPrefs(appListItem.getPackageName()), this, new Consumer() { // from class: org.fdroid.fdroid.panic.SelectInstalledAppsActivity$$ExternalSyntheticLambda1
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
        MenuItem menuItemAdd = menu.add(R.string.menu_select_for_wipe);
        menuItemAdd.setShowAsAction(2);
        this.checkId = menuItemAdd.getItemId();
        menuItemAdd.setIcon(R.drawable.check);
        return true;
    }

    @Override // android.app.Activity
    @SuppressLint({"ApplySharedPref"})
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        finish();
        if (menuItem.getItemId() == this.checkId) {
            Preferences preferences = this.prefs;
            preferences.setPanicWipeSet(preferences.getPanicTmpSelectedSet());
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
