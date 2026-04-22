package org.fdroid.fdroid.privileged.views;

import android.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.core.util.Supplier;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.request.BaseRequestOptions;
import org.fdroid.database.App;
import org.fdroid.database.AppDao;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.installer.Installer;

/* JADX INFO: loaded from: classes2.dex */
public class InstallConfirmActivity extends AppCompatActivity implements DialogInterface.OnCancelListener, View.OnClickListener {
    private static final String TAB_ID_ALL = "all";
    private static final String TAB_ID_NEW = "new";
    private AppDiff appDiff;
    private Button cancelButton;
    private View installConfirm;
    private Intent intent;
    private Button okButton;
    private boolean okCanInstall;
    private CaffeinatedScrollView scrollView;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startInstallConfirm$0(String str) {
    }

    private void startInstallConfirm() {
        int i;
        boolean z;
        int i2;
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        TabsAdapter tabsAdapter = new TabsAdapter(this, tabHost, (ViewPager) findViewById(org.fdroid.fdroid.R.id.pager));
        tabsAdapter.setOnTabChangedListener(new TabHost.OnTabChangeListener() { // from class: org.fdroid.fdroid.privileged.views.InstallConfirmActivity$$ExternalSyntheticLambda2
            @Override // android.widget.TabHost.OnTabChangeListener
            public final void onTabChanged(String str) {
                InstallConfirmActivity.lambda$startInstallConfirm$0(str);
            }
        });
        this.scrollView = null;
        this.okCanInstall = false;
        AppSecurityPermissions appSecurityPermissions = new AppSecurityPermissions(this, this.appDiff.apkPackageInfo);
        ApplicationInfo applicationInfo = this.appDiff.installedApplicationInfo;
        if (applicationInfo != null) {
            if ((applicationInfo.flags & 1) != 0) {
                i = org.fdroid.fdroid.R.string.install_confirm_update_system;
            } else {
                i = org.fdroid.fdroid.R.string.install_confirm_update;
            }
            CaffeinatedScrollView caffeinatedScrollView = new CaffeinatedScrollView(this);
            this.scrollView = caffeinatedScrollView;
            caffeinatedScrollView.setFillViewport(true);
            if (appSecurityPermissions.getPermissionCount(4) > 0) {
                this.scrollView.addView(appSecurityPermissions.getPermissionsView(4));
                tabsAdapter.addTab(tabHost.newTabSpec(TAB_ID_NEW).setIndicator(getText(org.fdroid.fdroid.R.string.newPerms)), this.scrollView);
                z = true;
            } else {
                throw new RuntimeException("This should not happen. No new permissions were found but InstallConfirmActivity has been started!");
            }
        } else {
            findViewById(org.fdroid.fdroid.R.id.tabscontainer).setVisibility(8);
            findViewById(org.fdroid.fdroid.R.id.divider).setVisibility(0);
            i = 0;
            z = false;
        }
        if (appSecurityPermissions.getPermissionCount(65535) > 0) {
            View viewInflate = ((LayoutInflater) ContextCompat.getSystemService(this, LayoutInflater.class)).inflate(org.fdroid.fdroid.R.layout.permissions_list, (ViewGroup) null);
            if (this.scrollView == null) {
                this.scrollView = (CaffeinatedScrollView) viewInflate.findViewById(org.fdroid.fdroid.R.id.scrollview);
            }
            ((ViewGroup) viewInflate.findViewById(org.fdroid.fdroid.R.id.permission_list)).addView(appSecurityPermissions.getPermissionsView(65535));
            tabsAdapter.addTab(tabHost.newTabSpec(TAB_ID_ALL).setIndicator(getText(org.fdroid.fdroid.R.string.allPerms)), viewInflate);
            z = true;
        }
        if (!z) {
            ApplicationInfo applicationInfo2 = this.appDiff.installedApplicationInfo;
            if (applicationInfo2 != null) {
                if ((applicationInfo2.flags & 1) != 0) {
                    i2 = org.fdroid.fdroid.R.string.install_confirm_update_system_no_perms;
                } else {
                    i2 = org.fdroid.fdroid.R.string.install_confirm_update_no_perms;
                }
                i = i2;
                tabHost.setVisibility(8);
                findViewById(org.fdroid.fdroid.R.id.filler).setVisibility(0);
                findViewById(org.fdroid.fdroid.R.id.divider).setVisibility(8);
                this.scrollView = null;
            } else {
                throw new RuntimeException("no permissions requested. This screen should not appear!");
            }
        }
        if (i != 0) {
            ((TextView) findViewById(org.fdroid.fdroid.R.id.install_confirm)).setText(i);
        }
        this.installConfirm.setVisibility(0);
        this.okButton = (Button) findViewById(org.fdroid.fdroid.R.id.ok_button);
        this.cancelButton = (Button) findViewById(org.fdroid.fdroid.R.id.cancel_button);
        this.okButton.setOnClickListener(this);
        this.cancelButton.setOnClickListener(this);
        CaffeinatedScrollView caffeinatedScrollView2 = this.scrollView;
        if (caffeinatedScrollView2 == null) {
            this.okButton.setText(org.fdroid.fdroid.R.string.menu_install);
            this.okCanInstall = true;
        } else {
            caffeinatedScrollView2.setFullScrollAction(new Runnable() { // from class: org.fdroid.fdroid.privileged.views.InstallConfirmActivity$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$startInstallConfirm$1();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startInstallConfirm$1() {
        this.okButton.setText(org.fdroid.fdroid.R.string.menu_install);
        this.okCanInstall = true;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        FDroidApp fDroidApp = (FDroidApp) getApplication();
        fDroidApp.setSecureWindow(this);
        fDroidApp.applyPureBlackBackgroundInDarkTheme(this);
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.intent = intent;
        final Apk apk = (Apk) intent.getParcelableExtra(Installer.EXTRA_APK);
        final AppDao appDao = DBHelper.getDb(this).getAppDao();
        Utils.runOffUiThread(new Supplier() { // from class: org.fdroid.fdroid.privileged.views.InstallConfirmActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.util.Supplier
            public final Object get() {
                return InstallConfirmActivity.lambda$onCreate$2(appDao, apk);
            }
        }, new Consumer() { // from class: org.fdroid.fdroid.privileged.views.InstallConfirmActivity$$ExternalSyntheticLambda1
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                this.f$0.onAppLoaded((App) obj);
            }
        });
        this.appDiff = new AppDiff(this, apk);
        setContentView(org.fdroid.fdroid.R.layout.install_start);
        getWindow().setLayout(-1, -2);
        View viewFindViewById = findViewById(org.fdroid.fdroid.R.id.install_confirm_panel);
        this.installConfirm = viewFindViewById;
        viewFindViewById.setVisibility(4);
        startInstallConfirm();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ App lambda$onCreate$2(AppDao appDao, Apk apk) {
        return appDao.getApp(apk.repoId, apk.packageName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAppLoaded(App app) {
        org.fdroid.fdroid.data.App app2 = new org.fdroid.fdroid.data.App(app, null);
        View viewFindViewById = findViewById(org.fdroid.fdroid.R.id.app_snippet);
        ((TextView) viewFindViewById.findViewById(org.fdroid.fdroid.R.id.app_name)).setText(app2.name);
        app2.loadWithGlide(this, app2.iconFile).apply((BaseRequestOptions) Utils.getAlwaysShowIconRequestOptions()).into((ImageView) viewFindViewById.findViewById(org.fdroid.fdroid.R.id.app_icon));
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        CaffeinatedScrollView caffeinatedScrollView;
        if (view == this.okButton) {
            if (this.okCanInstall || (caffeinatedScrollView = this.scrollView) == null) {
                setResult(-1, this.intent);
                finish();
                return;
            } else {
                caffeinatedScrollView.pageScroll(130);
                return;
            }
        }
        if (view == this.cancelButton) {
            setResult(0, this.intent);
            finish();
        }
    }
}
