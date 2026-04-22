package org.fdroid.fdroid.views;

import android.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.preference.PreferenceManager;
import ch.qos.logback.core.joran.action.Action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.fdroid.database.Repository;
import org.fdroid.download.Mirror;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.RepoUpdateManager;
import org.fdroid.fdroid.net.BluetoothDownloader;
import org.fdroid.fdroid.net.ConnectivityMonitorService;

/* JADX INFO: loaded from: classes2.dex */
public class StatusBanner extends AppCompatTextView {
    private final SharedPreferences.OnSharedPreferenceChangeListener dataWifiChangeListener;
    private boolean isUpdatingRepos;
    private int networkState;
    private final BroadcastReceiver onNetworkStateChanged;
    private final Observer onRepoUpdateChanged;
    private int overDataState;
    private int overWiFiState;
    private final SharedPreferences preferences;
    private final RepoUpdateManager repoUpdateManager;

    public StatusBanner(Context context) {
        this(context, null);
    }

    public StatusBanner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public StatusBanner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.networkState = 2;
        this.onRepoUpdateChanged = new Observer() { // from class: org.fdroid.fdroid.views.StatusBanner.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                StatusBanner.this.isUpdatingRepos = bool.booleanValue();
                StatusBanner.this.setBannerTextAndVisibility();
            }
        };
        this.onNetworkStateChanged = new BroadcastReceiver() { // from class: org.fdroid.fdroid.views.StatusBanner.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                StatusBanner.this.networkState = ConnectivityMonitorService.getNetworkState(context2);
                StatusBanner.this.setBannerTextAndVisibility();
            }
        };
        this.dataWifiChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: org.fdroid.fdroid.views.StatusBanner.3
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                if (str.equals(Preferences.PREF_OVER_DATA) || str.equals(Preferences.PREF_OVER_WIFI)) {
                    StatusBanner.this.overDataState = Preferences.get().getOverData();
                    StatusBanner.this.overWiFiState = Preferences.get().getOverWifi();
                    StatusBanner.this.setBannerTextAndVisibility();
                }
            }
        };
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        RepoUpdateManager repoUpdateManager = FDroidApp.getRepoUpdateManager(context);
        this.repoUpdateManager = repoUpdateManager;
        this.isUpdatingRepos = ((Boolean) repoUpdateManager.getIsUpdating().getValue()).booleanValue();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Context context = getContext();
        this.networkState = ConnectivityMonitorService.getNetworkState(context);
        context.registerReceiver(this.onNetworkStateChanged, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.isUpdatingRepos = ((Boolean) this.repoUpdateManager.getIsUpdating().getValue()).booleanValue();
        this.repoUpdateManager.getIsUpdatingLiveData().observeForever(this.onRepoUpdateChanged);
        this.overDataState = Preferences.get().getOverData();
        this.overWiFiState = Preferences.get().getOverWifi();
        this.preferences.registerOnSharedPreferenceChangeListener(this.dataWifiChangeListener);
        setBannerTextAndVisibility();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Context context = getContext();
        this.repoUpdateManager.getIsUpdatingLiveData().removeObserver(this.onRepoUpdateChanged);
        context.unregisterReceiver(this.onNetworkStateChanged);
        this.preferences.unregisterOnSharedPreferenceChangeListener(this.dataWifiChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBannerTextAndVisibility() {
        if (isInEditMode() || this.isUpdatingRepos) {
            setText(org.fdroid.fdroid.R.string.banner_updating_repositories);
            setVisibility(0);
            return;
        }
        int i = this.networkState;
        if (i == 0 || i == 3) {
            setText(org.fdroid.fdroid.R.string.banner_no_internet);
            setVisibility(0);
            return;
        }
        if (this.overDataState == 0 && this.overWiFiState == 0) {
            List<Repository> localRepos = getLocalRepos(FDroidApp.getRepoManager(getContext()).getRepositories());
            List listAsList = Arrays.asList("odm", "oem", "product", "system", "vendor");
            Iterator<Repository> it = localRepos.iterator();
            boolean z = true;
            while (it.hasNext()) {
                Iterator<String> it2 = Uri.parse(it.next().getAddress()).getPathSegments().iterator();
                if (it2.hasNext() && listAsList.contains(it2.next())) {
                    z = false;
                }
            }
            if (localRepos.isEmpty() || !z) {
                setText(org.fdroid.fdroid.R.string.banner_no_data_or_wifi);
                setVisibility(0);
                return;
            } else {
                setVisibility(8);
                return;
            }
        }
        setVisibility(8);
    }

    public static List<Repository> getLocalRepos(List<Repository> list) {
        ArrayList arrayList = new ArrayList();
        for (Repository repository : list) {
            if (isLocalRepoAddress(repository.getAddress())) {
                arrayList.add(repository);
            } else {
                Iterator<Mirror> it = repository.getMirrors().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (!it.next().isHttp()) {
                        arrayList.add(repository);
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

    private static boolean isLocalRepoAddress(String str) {
        return str != null && (str.startsWith(BluetoothDownloader.SCHEME) || str.startsWith("content") || str.startsWith(Action.FILE_ATTRIBUTE));
    }
}
