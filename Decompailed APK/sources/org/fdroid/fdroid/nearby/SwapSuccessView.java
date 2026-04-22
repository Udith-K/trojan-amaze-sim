package org.fdroid.fdroid.nearby;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.pm.PackageInfoCompat;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.CompatibilityChecker;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.installer.InstallManagerService;
import org.fdroid.fdroid.installer.Installer;
import org.fdroid.fdroid.net.DownloaderService;
import org.fdroid.index.v1.AppV1;
import org.fdroid.index.v1.IndexV1;
import org.fdroid.index.v1.PackageV1;
import org.fdroid.index.v1.PermissionV1;
import org.fdroid.index.v2.FileV1;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: loaded from: classes2.dex */
public class SwapSuccessView extends SwapView {
    private static final String TAG = "SwapSuccessView";
    private AppListAdapter adapter;
    private Repository repo;

    public SwapSuccessView(Context context) {
        super(context);
    }

    public SwapSuccessView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SwapSuccessView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SwapSuccessView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.repo = getActivity().getSwapService().getPeerRepo();
        this.adapter = new AppListAdapter();
        ((RecyclerView) findViewById(R.id.list)).setAdapter(this.adapter);
        getActivity().getSwapService().getIndex().observe(getActivity(), new Observer() { // from class: org.fdroid.fdroid.nearby.SwapSuccessView$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.onIndexReceived((IndexV1) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onIndexReceived(IndexV1 indexV1) {
        ArrayList arrayList = new ArrayList(indexV1.getApps().size());
        HashMap map = new HashMap(indexV1.getApps().size());
        CompatibilityChecker compatibilityChecker = new CompatibilityChecker(getContext());
        for (AppV1 appV1 : indexV1.getApps()) {
            App app = new App();
            app.name = appV1.getName();
            app.packageName = appV1.getPackageName();
            app.iconFile = FileV2.fromPath("icons/" + appV1.getIcon());
            try {
                app.installedVersionCode = PackageInfoCompat.getLongVersionCode(getContext().getPackageManager().getPackageInfo(app.packageName, 0));
            } catch (PackageManager.NameNotFoundException unused) {
            }
            Apk apk = new Apk();
            List<PackageV1> list = indexV1.getPackages().get(app.packageName);
            if (list != null && list.get(0) != null) {
                PackageV1 packageV1 = list.get(0);
                if (packageV1.getVersionCode() != null) {
                    app.autoInstallVersionCode = packageV1.getVersionCode().intValue();
                }
                if (packageV1.getVersionCode() != null) {
                    apk.versionCode = packageV1.getVersionCode().longValue();
                }
                apk.versionName = packageV1.getVersionName();
                apk.apkFile = new FileV1("/" + packageV1.getApkName(), packageV1.getHash(), Long.valueOf(packageV1.getSize()), null);
                ArrayList arrayList2 = new ArrayList(packageV1.getUsesPermission().size());
                Iterator<PermissionV1> it = packageV1.getUsesPermission().iterator();
                while (it.hasNext()) {
                    arrayList2.add(it.next().getName());
                }
                String[] strArr = (String[]) arrayList2.toArray(new String[0]);
                apk.requestedPermissions = strArr;
                if (strArr.length == 0) {
                    apk.requestedPermissions = null;
                }
            }
            apk.repoId = Preferences.UPDATE_INTERVAL_DISABLED;
            apk.packageName = app.packageName;
            apk.repoAddress = this.repo.getAddress();
            apk.canonicalRepoAddress = this.repo.getAddress();
            apk.setCompatibility(compatibilityChecker);
            app.compatible = apk.compatible;
            arrayList.add(app);
            map.put(app.packageName, apk);
        }
        this.adapter.setApps(arrayList, map);
    }

    private class AppListAdapter extends RecyclerView.Adapter {
        private final Map<String, Apk> apks;
        private final List<App> apps;

        private AppListAdapter() {
            this.apps = new ArrayList();
            this.apks = new HashMap();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            private Apk apk;
            private App app;
            Button btnInstall;
            private final View.OnClickListener cancelListener;
            ImageView iconView;
            private final View.OnClickListener installListener;
            private final LocalBroadcastManager localBroadcastManager;
            TextView nameView;
            LinearProgressIndicator progressView;
            TextView statusIncompatible;
            TextView statusInstalled;

            private class DownloadReceiver extends BroadcastReceiver {
                private DownloadReceiver() {
                }

                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    action.hashCode();
                    switch (action) {
                        case "org.fdroid.fdroid.net.Downloader.action.STARTED":
                            ViewHolder.this.resetView();
                            return;
                        case "org.fdroid.fdroid.net.Downloader.action.CONNECTION_FAILED":
                        case "org.fdroid.fdroid.net.Downloader.action.INTERRUPTED":
                            ViewHolder.this.localBroadcastManager.unregisterReceiver(this);
                            if (intent.hasExtra(DownloaderService.EXTRA_ERROR_MESSAGE)) {
                                String str = intent.getStringExtra(DownloaderService.EXTRA_ERROR_MESSAGE) + " " + intent.getDataString();
                                Toast.makeText(context, R.string.download_error, 0).show();
                                Toast.makeText(context, str, 1).show();
                            } else {
                                Toast.makeText(context, R.string.details_notinstalled, 1).show();
                            }
                            ViewHolder.this.resetView();
                            return;
                        case "org.fdroid.fdroid.net.Downloader.action.PROGRESS":
                            if (ViewHolder.this.progressView.getVisibility() != 0) {
                                ViewHolder.this.showProgress();
                            }
                            long longExtra = intent.getLongExtra(DownloaderService.EXTRA_BYTES_READ, 0L);
                            long longExtra2 = intent.getLongExtra(DownloaderService.EXTRA_TOTAL_BYTES, 0L);
                            if (longExtra2 > 0) {
                                ViewHolder.this.progressView.setProgressCompat(Utils.getPercent(longExtra, longExtra2), true);
                            } else if (!ViewHolder.this.progressView.isIndeterminate()) {
                                ViewHolder.this.progressView.hide();
                                ViewHolder.this.progressView.setIndeterminate(true);
                            }
                            ViewHolder.this.progressView.show();
                            return;
                        case "org.fdroid.fdroid.net.Downloader.action.COMPLETE":
                            ViewHolder.this.localBroadcastManager.unregisterReceiver(this);
                            ViewHolder.this.resetView();
                            ViewHolder.this.statusInstalled.setText(R.string.installing);
                            ViewHolder.this.statusInstalled.setVisibility(0);
                            ViewHolder.this.btnInstall.setVisibility(8);
                            return;
                        default:
                            throw new RuntimeException("intent action not handled!");
                    }
                }
            }

            ViewHolder(View view) {
                super(view);
                this.cancelListener = new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapSuccessView.AppListAdapter.ViewHolder.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        if (ViewHolder.this.apk != null) {
                            InstallManagerService.cancel(SwapSuccessView.this.getContext(), ViewHolder.this.apk.getCanonicalUrl());
                        }
                    }
                };
                this.installListener = new View.OnClickListener() { // from class: org.fdroid.fdroid.nearby.SwapSuccessView.AppListAdapter.ViewHolder.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        if (ViewHolder.this.apk != null) {
                            if (ViewHolder.this.app.hasUpdates() || ViewHolder.this.app.compatible) {
                                ViewHolder.this.showProgress();
                                InstallManagerService.queue(SwapSuccessView.this.getContext(), ViewHolder.this.app, ViewHolder.this.apk);
                            }
                        }
                    }
                };
                this.localBroadcastManager = LocalBroadcastManager.getInstance(SwapSuccessView.this.getContext());
                this.progressView = (LinearProgressIndicator) view.findViewById(R.id.progress);
                this.nameView = (TextView) view.findViewById(R.id.name);
                this.iconView = (ImageView) view.findViewById(android.R.id.icon);
                this.btnInstall = (Button) view.findViewById(R.id.btn_install);
                this.statusInstalled = (TextView) view.findViewById(R.id.status_installed);
                this.statusIncompatible = (TextView) view.findViewById(R.id.status_incompatible);
            }

            public void setApp(App app) {
                App app2 = this.app;
                if (app2 == null || !app2.packageName.equals(app.packageName)) {
                    this.app = app;
                    Apk apk = (Apk) AppListAdapter.this.apks.get(this.app.packageName);
                    this.apk = apk;
                    if (apk != null) {
                        this.localBroadcastManager.registerReceiver(new DownloadReceiver(), DownloaderService.getIntentFilter(this.apk.getCanonicalUrl()));
                        this.localBroadcastManager.registerReceiver(new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.SwapSuccessView.AppListAdapter.ViewHolder.1
                            @Override // android.content.BroadcastReceiver
                            public void onReceive(Context context, Intent intent) {
                                String action = intent.getAction();
                                action.hashCode();
                                switch (action) {
                                    case "org.fdroid.fdroid.installer.Installer.action.INSTALL_INTERRUPTED":
                                        ViewHolder.this.localBroadcastManager.unregisterReceiver(this);
                                        ViewHolder.this.statusInstalled.setVisibility(8);
                                        ViewHolder.this.btnInstall.setVisibility(0);
                                        ViewHolder.this.progressView.hide();
                                        String stringExtra = intent.getStringExtra(Installer.EXTRA_ERROR_MESSAGE);
                                        if (stringExtra != null) {
                                            Toast.makeText(SwapSuccessView.this.getContext(), stringExtra, 1).show();
                                            break;
                                        }
                                        break;
                                    case "org.fdroid.fdroid.installer.Installer.action.INSTALL_USER_INTERACTION":
                                        try {
                                            ((PendingIntent) intent.getParcelableExtra(Installer.EXTRA_USER_INTERACTION_PI)).send();
                                            break;
                                        } catch (PendingIntent.CanceledException e) {
                                            Log.e(SwapSuccessView.TAG, "PI canceled", e);
                                            return;
                                        }
                                        break;
                                    case "org.fdroid.fdroid.installer.Installer.action.INSTALL_STARTED":
                                        ViewHolder.this.statusInstalled.setText(R.string.installing);
                                        ViewHolder.this.statusInstalled.setVisibility(0);
                                        ViewHolder.this.btnInstall.setVisibility(8);
                                        if (!ViewHolder.this.progressView.isIndeterminate()) {
                                            ViewHolder.this.progressView.hide();
                                            ViewHolder.this.progressView.setIndeterminate(true);
                                        }
                                        ViewHolder.this.progressView.show();
                                        break;
                                    case "org.fdroid.fdroid.installer.Installer.action.INSTALL_COMPLETE":
                                        ViewHolder.this.localBroadcastManager.unregisterReceiver(this);
                                        ViewHolder.this.statusInstalled.setText(R.string.app_installed);
                                        ViewHolder.this.statusInstalled.setVisibility(0);
                                        ViewHolder.this.btnInstall.setVisibility(8);
                                        ViewHolder.this.progressView.hide();
                                        break;
                                }
                            }
                        }, Installer.getInstallIntentFilter(this.apk.getCanonicalUrl()));
                    }
                }
                resetView();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void resetView() {
                if (this.app == null) {
                    return;
                }
                if (!this.progressView.isIndeterminate()) {
                    this.progressView.hide();
                    this.progressView.setIndeterminate(true);
                }
                this.progressView.show();
                String str = this.app.name;
                if (str != null) {
                    this.nameView.setText(str);
                }
                Glide.with(this.iconView.getContext()).m3060load(Utils.getGlideModel(SwapSuccessView.this.repo, this.app.iconFile)).apply((BaseRequestOptions) Utils.getAlwaysShowIconRequestOptions()).into(this.iconView);
                if (this.app.hasUpdates()) {
                    this.btnInstall.setText(R.string.menu_upgrade);
                    this.btnInstall.setVisibility(0);
                    this.btnInstall.setOnClickListener(this.installListener);
                    this.statusIncompatible.setVisibility(8);
                    this.statusInstalled.setVisibility(8);
                    return;
                }
                if (this.app.isInstalled(SwapSuccessView.this.getContext())) {
                    this.btnInstall.setVisibility(8);
                    this.statusIncompatible.setVisibility(8);
                    this.statusInstalled.setVisibility(0);
                    this.statusInstalled.setText(R.string.app_installed);
                    return;
                }
                if (!this.app.compatible) {
                    this.btnInstall.setVisibility(8);
                    this.statusIncompatible.setVisibility(0);
                    this.statusInstalled.setVisibility(8);
                } else {
                    if (this.progressView.getVisibility() == 0) {
                        this.btnInstall.setText(R.string.cancel);
                        this.btnInstall.setVisibility(0);
                        this.btnInstall.setOnClickListener(this.cancelListener);
                        this.statusIncompatible.setVisibility(8);
                        this.statusInstalled.setVisibility(8);
                        return;
                    }
                    this.btnInstall.setText(R.string.menu_install);
                    this.btnInstall.setVisibility(0);
                    this.btnInstall.setOnClickListener(this.installListener);
                    this.statusIncompatible.setVisibility(8);
                    this.statusInstalled.setVisibility(8);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void showProgress() {
                this.btnInstall.setText(R.string.cancel);
                this.btnInstall.setVisibility(0);
                this.btnInstall.setOnClickListener(this.cancelListener);
                this.progressView.show();
                this.statusInstalled.setVisibility(8);
                this.statusIncompatible.setVisibility(8);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.swap_app_list_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.setApp(this.apps.get(i));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.apps.size();
        }

        void setApps(List<App> list, Map<String, Apk> map) {
            this.apps.clear();
            this.apps.addAll(list);
            this.apks.clear();
            this.apks.putAll(map);
            notifyDataSetChanged();
        }
    }
}
