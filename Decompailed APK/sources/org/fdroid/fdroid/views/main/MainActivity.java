package org.fdroid.fdroid.views.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts$RequestPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.nearby.SDCardScannerService;
import org.fdroid.fdroid.nearby.SwapService;
import org.fdroid.fdroid.nearby.TreeUriScannerIntentService;
import org.fdroid.fdroid.nearby.WifiStateChangeService;
import org.fdroid.fdroid.views.AppDetailsActivity;
import org.fdroid.fdroid.views.apps.AppListActivity;

/* JADX INFO: loaded from: classes2.dex */
public class MainActivity extends AppCompatActivity {
    public static final String ACTION_REQUEST_SWAP = "requestSwap";
    private static final String EXTRA_VIEW_CATEGORIES = "org.fdroid.fdroid.views.main.MainActivity.VIEW_CATEGORIES";
    public static final String EXTRA_VIEW_LATEST = "org.fdroid.fdroid.views.main.MainActivity.VIEW_LATEST";
    private static final String EXTRA_VIEW_NEARBY = "org.fdroid.fdroid.views.main.MainActivity.VIEW_NEARBY";
    public static final String EXTRA_VIEW_SETTINGS = "org.fdroid.fdroid.views.main.MainActivity.VIEW_SETTINGS";
    public static final String EXTRA_VIEW_UPDATES = "org.fdroid.fdroid.views.main.MainActivity.VIEW_UPDATES";
    static final int REQUEST_LOCATION_PERMISSIONS = 61199;
    static final int REQUEST_STORAGE_ACCESS = 16613;
    static final int REQUEST_STORAGE_PERMISSIONS = 45060;
    private static final String TAG = "MainActivity";
    private MainViewAdapter adapter;
    private BottomNavigationView bottomNavigation;
    private RecyclerView pager;
    private final ActivityResultLauncher permissionLauncher = registerForActivityResult(new ActivityResultContracts$RequestPermission(), new ActivityResultCallback() { // from class: org.fdroid.fdroid.views.main.MainActivity$$ExternalSyntheticLambda3
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            MainActivity.lambda$new$0((Boolean) obj);
        }
    });
    private BadgeDrawable updatesBadge;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(Boolean bool) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        FDroidApp fDroidApp = (FDroidApp) getApplication();
        fDroidApp.setSecureWindow(this);
        fDroidApp.applyPureBlackBackgroundInDarkTheme(this);
        EdgeToEdge.enable(this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        if ((getResources().getConfiguration().uiMode & 48) != 32) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
        }
        this.adapter = new MainViewAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_view_pager);
        this.pager = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.pager.setLayoutManager(new NonScrollingHorizontalLayoutManager(this));
        this.pager.setAdapter(this.adapter);
        ViewCompat.setOnApplyWindowInsetsListener(this.pager, new OnApplyWindowInsetsListener() { // from class: org.fdroid.fdroid.views.main.MainActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return MainActivity.lambda$onCreate$1(view, windowInsetsCompat);
            }
        });
        this.bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        setSelectedMenuInNav(Preferences.get().getBottomNavigationViewName());
        this.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { // from class: org.fdroid.fdroid.views.main.MainActivity$$ExternalSyntheticLambda1
            @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
            public final boolean onNavigationItemSelected(MenuItem menuItem) {
                return this.f$0.lambda$onCreate$2(menuItem);
            }
        });
        BadgeDrawable orCreateBadge = this.bottomNavigation.getOrCreateBadge(R.id.updates);
        this.updatesBadge = orCreateBadge;
        orCreateBadge.setVisible(false);
        AppUpdateStatusManager.getInstance(this).getNumUpdatableApps().observe(this, new Observer() { // from class: org.fdroid.fdroid.views.main.MainActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.refreshUpdatesBadge(((Integer) obj).intValue());
            }
        });
        Intent intent = getIntent();
        if (handleMainViewSelectIntent(intent)) {
            return;
        }
        handleSearchOrAppViewIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ WindowInsetsCompat lambda$onCreate$1(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
        view.setPadding(insets.left, insets.top, insets.right, 0);
        return WindowInsetsCompat.CONSUMED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onCreate$2(MenuItem menuItem) {
        this.pager.scrollToPosition(menuItem.getOrder());
        if (menuItem.getItemId() == R.id.latest) {
            Preferences.get().setBottomNavigationViewName(EXTRA_VIEW_LATEST);
            return true;
        }
        if (menuItem.getItemId() == R.id.categories) {
            Preferences.get().setBottomNavigationViewName(EXTRA_VIEW_CATEGORIES);
            return true;
        }
        if (menuItem.getItemId() == R.id.nearby) {
            Preferences.get().setBottomNavigationViewName(EXTRA_VIEW_NEARBY);
            NearbyViewBinder.updateUsbOtg(this);
            return true;
        }
        if (menuItem.getItemId() == R.id.updates) {
            Preferences.get().setBottomNavigationViewName(EXTRA_VIEW_UPDATES);
            return true;
        }
        if (menuItem.getItemId() != R.id.settings) {
            return true;
        }
        Preferences.get().setBottomNavigationViewName(EXTRA_VIEW_SETTINGS);
        return true;
    }

    private void setSelectedMenuInNav(int i) {
        int iAdapterPositionFromItemId = this.adapter.adapterPositionFromItemId(i);
        if (iAdapterPositionFromItemId < 0) {
            Log.e(TAG, "Invalid menu position: " + iAdapterPositionFromItemId);
            return;
        }
        this.pager.scrollToPosition(iAdapterPositionFromItemId);
        this.bottomNavigation.getMenu().getItem(iAdapterPositionFromItemId).setChecked(true);
    }

    private void setSelectedMenuInNav(String str) {
        if (EXTRA_VIEW_LATEST.equals(str)) {
            setSelectedMenuInNav(R.id.latest);
            return;
        }
        if (EXTRA_VIEW_CATEGORIES.equals(str)) {
            setSelectedMenuInNav(R.id.categories);
            return;
        }
        if (EXTRA_VIEW_NEARBY.equals(str)) {
            setSelectedMenuInNav(R.id.nearby);
        } else if (EXTRA_VIEW_UPDATES.equals(str)) {
            setSelectedMenuInNav(R.id.updates);
        } else if (EXTRA_VIEW_SETTINGS.equals(str)) {
            setSelectedMenuInNav(R.id.settings);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        FDroidApp.checkStartTor(this, Preferences.get());
        NearbyViewBinder.updateExternalStorageViews(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT < 33 || ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == 0) {
            return;
        }
        this.permissionLauncher.launch("android.permission.POST_NOTIFICATIONS");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (handleMainViewSelectIntent(intent)) {
            return;
        }
        handleSearchOrAppViewIntent(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == REQUEST_STORAGE_ACCESS) {
            TreeUriScannerIntentService.onActivityResult(this, intent);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == REQUEST_LOCATION_PERMISSIONS) {
            WifiStateChangeService.start(this, null);
            ContextCompat.startForegroundService(this, new Intent(this, (Class<?>) SwapService.class));
        } else if (i == REQUEST_STORAGE_PERMISSIONS) {
            Toast.makeText(this, getString(R.string.scan_removable_storage_toast, ""), 0).show();
            SDCardScannerService.scan(this);
        }
    }

    private boolean handleMainViewSelectIntent(Intent intent) {
        if (intent.hasExtra(EXTRA_VIEW_NEARBY)) {
            setSelectedMenuInNav(R.id.nearby);
            return true;
        }
        if (intent.hasExtra(EXTRA_VIEW_UPDATES)) {
            setSelectedMenuInNav(R.id.updates);
            return true;
        }
        if (!intent.hasExtra(EXTRA_VIEW_SETTINGS)) {
            return false;
        }
        setSelectedMenuInNav(R.id.settings);
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void handleSearchOrAppViewIntent(Intent intent) {
        String schemeSpecificPart;
        String schemeSpecificPart2;
        if ("android.intent.action.SEARCH".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("query");
            if (stringExtra != null) {
                performSearch(stringExtra);
                return;
            }
            return;
        }
        Uri data = intent.getData();
        if (data == null) {
            return;
        }
        String scheme = data.getScheme();
        String path = data.getPath();
        String queryParameter = null;
        if (data.isHierarchical()) {
            String host = data.getHost();
            if (host != null) {
                switch (host) {
                    case "search":
                        schemeSpecificPart = data.getQueryParameter("q");
                        String str = queryParameter;
                        queryParameter = schemeSpecificPart;
                        schemeSpecificPart2 = str;
                        break;
                    case "apps":
                    case "amazon.com":
                    case "www.amazon.com":
                        queryParameter = data.getQueryParameter("p");
                        schemeSpecificPart = data.getQueryParameter("s");
                        String str2 = queryParameter;
                        queryParameter = schemeSpecificPart;
                        schemeSpecificPart2 = str2;
                        break;
                    case "play.google.com":
                        if (path.startsWith("/store/apps/details")) {
                            schemeSpecificPart2 = data.getQueryParameter("id");
                            break;
                        } else {
                            if (path.startsWith("/store/search")) {
                                schemeSpecificPart = data.getQueryParameter("q");
                                String str22 = queryParameter;
                                queryParameter = schemeSpecificPart;
                                schemeSpecificPart2 = str22;
                                break;
                            }
                            schemeSpecificPart2 = null;
                            break;
                        }
                        break;
                    case "f-droid.org":
                    case "www.f-droid.org":
                    case "staging.f-droid.org":
                        if (path.startsWith("/app/") || path.startsWith("/packages/") || path.matches("^/[a-z][a-z][a-zA-Z_-]*/packages/.*")) {
                            schemeSpecificPart2 = data.getLastPathSegment();
                            break;
                        } else {
                            if (path.startsWith("/repository/browse")) {
                                queryParameter = data.getQueryParameter("fdfilter");
                                schemeSpecificPart2 = data.getQueryParameter("fdid");
                            } else {
                                if (!"/app".equals(data.getPath())) {
                                    "/packages".equals(data.getPath());
                                }
                                schemeSpecificPart2 = null;
                            }
                            break;
                        }
                        break;
                    case "details":
                        schemeSpecificPart2 = data.getQueryParameter("id");
                        break;
                    default:
                        schemeSpecificPart2 = null;
                        break;
                }
            } else {
                return;
            }
        } else if ("fdroid.app".equals(scheme)) {
            schemeSpecificPart2 = data.getSchemeSpecificPart();
        } else {
            if ("fdroid.search".equals(scheme)) {
                schemeSpecificPart = data.getSchemeSpecificPart();
                String str222 = queryParameter;
                queryParameter = schemeSpecificPart;
                schemeSpecificPart2 = str222;
            }
            schemeSpecificPart2 = null;
        }
        if (!TextUtils.isEmpty(queryParameter)) {
            if (queryParameter.startsWith("pname:")) {
                schemeSpecificPart2 = queryParameter.split(":")[1];
            }
            if (queryParameter.contains(":")) {
                queryParameter = queryParameter.split(":")[1];
            }
        }
        if (!TextUtils.isEmpty(schemeSpecificPart2)) {
            String strReplaceAll = schemeSpecificPart2.replaceAll("[^A-Za-z\\d_.]", "");
            Utils.debugLog(TAG, "FDroid launched via app link for '" + strReplaceAll + "'");
            Intent intent2 = new Intent(this, (Class<?>) AppDetailsActivity.class);
            intent2.putExtra(AppDetailsActivity.EXTRA_APPID, strReplaceAll);
            startActivity(intent2);
            finish();
            return;
        }
        if (TextUtils.isEmpty(queryParameter)) {
            return;
        }
        Utils.debugLog(TAG, "FDroid launched via search link for '" + queryParameter + "'");
        performSearch(queryParameter);
    }

    static String sanitizeSearchTerms(String str) {
        return str.replaceAll("[^\\p{L}\\d_ -]", " ");
    }

    private void performSearch(String str) {
        Intent intent = new Intent(this, (Class<?>) AppListActivity.class);
        intent.putExtra(AppListActivity.EXTRA_SEARCH_TERMS, sanitizeSearchTerms(str));
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUpdatesBadge(int i) {
        if (i <= 0) {
            this.updatesBadge.setVisible(false);
            this.updatesBadge.clearNumber();
        } else {
            this.updatesBadge.setNumber(i);
            this.updatesBadge.setVisible(true);
        }
    }

    private static class NonScrollingHorizontalLayoutManager extends LinearLayoutManager {
        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollHorizontally() {
            return false;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollVertically() {
            return false;
        }

        NonScrollingHorizontalLayoutManager(Context context) {
            super(context, 0, false);
        }
    }
}
