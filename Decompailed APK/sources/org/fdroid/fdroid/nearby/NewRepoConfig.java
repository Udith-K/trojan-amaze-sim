package org.fdroid.fdroid.nearby;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import ch.qos.logback.core.joran.action.Action;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.fdroid.nearby.peers.WifiPeer;
import org.fdroid.fdroid.views.repos.ManageReposActivity;

/* JADX INFO: loaded from: classes2.dex */
public class NewRepoConfig {
    private static final List<String> FORCE_HTTPS_DOMAINS = Arrays.asList("amazonaws.com", "github.com", "githubusercontent.com", "github.io", "gitlab.com", "gitlab.io");
    private static final String TAG = "NewRepoConfig";
    private String bssid;
    private String errorMessage;
    private String fingerprint;
    private boolean fromSwap;
    private String host;
    private boolean isValidRepo;
    private String password;
    private boolean preventFurtherSwaps;
    private String ssid;
    private String uriString;
    private String username;

    public NewRepoConfig(Context context, String str) {
        init(context, str != null ? Uri.parse(str) : null);
    }

    public NewRepoConfig(Context context, Intent intent) {
        init(context, intent.getData());
        this.preventFurtherSwaps = intent.getBooleanExtra(SwapWorkflowActivity.EXTRA_PREVENT_FURTHER_SWAP_REQUESTS, false);
    }

    private void init(Context context, Uri uri) {
        if (uri == null) {
            this.isValidRepo = false;
            return;
        }
        if (hasDisallowInstallUnknownSources(context)) {
            this.errorMessage = ManageReposActivity.getDisallowInstallUnknownSourcesErrorMessage(context);
            this.isValidRepo = false;
            return;
        }
        Utils.debugLog(TAG, "Parsing incoming intent looking for repo: " + uri);
        String scheme = uri.getScheme();
        this.host = uri.getHost();
        if (TextUtils.isEmpty(scheme) || (TextUtils.isEmpty(this.host) && !Action.FILE_ATTRIBUTE.equals(scheme))) {
            String str = String.format(context.getString(R.string.malformed_repo_uri), uri);
            this.errorMessage = str;
            Log.i(TAG, str);
            this.isValidRepo = false;
            return;
        }
        if (Arrays.asList("FDROIDREPO", "FDROIDREPOS").contains(scheme) || uri.getPath().endsWith("/FDROID/REPO")) {
            uri = Uri.parse(uri.toString().toLowerCase(Locale.ENGLISH));
        }
        Locale locale = Locale.ENGLISH;
        String lowerCase = scheme.toLowerCase(locale);
        this.host = this.host.toLowerCase(locale);
        if (uri.getPath() == null || !Arrays.asList("https", "http", "fdroidrepos", "fdroidrepo", "content", Action.FILE_ATTRIBUTE).contains(lowerCase)) {
            this.isValidRepo = false;
            return;
        }
        String userInfo = uri.getUserInfo();
        if (userInfo != null) {
            String[] strArrSplit = userInfo.split(":");
            if (strArrSplit.length >= 2) {
                this.username = strArrSplit[0];
                this.password = strArrSplit[1];
                for (int i = 2; i < strArrSplit.length; i++) {
                    this.password += ":" + strArrSplit[i];
                }
            }
        }
        this.fingerprint = uri.getQueryParameter(BonjourPeer.FINGERPRINT);
        this.bssid = uri.getQueryParameter("bssid");
        this.ssid = uri.getQueryParameter("ssid");
        this.fromSwap = uri.getQueryParameter("swap") != null;
        this.uriString = sanitizeRepoUri(uri);
        this.isValidRepo = true;
    }

    public String getBssid() {
        return this.bssid;
    }

    public String getSsid() {
        return this.ssid;
    }

    public String getRepoUriString() {
        return this.uriString;
    }

    public Uri getRepoUri() {
        String str = this.uriString;
        if (str == null) {
            return null;
        }
        return Uri.parse(str);
    }

    public String getHost() {
        return this.host;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }

    public boolean isValidRepo() {
        return this.isValidRepo;
    }

    public boolean isFromSwap() {
        return this.fromSwap;
    }

    public boolean preventFurtherSwaps() {
        return this.preventFurtherSwaps;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public static String sanitizeRepoUri(Uri uri) {
        String scheme = uri.getScheme();
        Locale locale = Locale.ENGLISH;
        String lowerCase = scheme.toLowerCase(locale);
        String host = uri.getHost();
        String lowerCase2 = host.toLowerCase(locale);
        String userInfo = uri.getUserInfo();
        if ("http".equals(lowerCase)) {
            Iterator<String> it = FORCE_HTTPS_DOMAINS.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (lowerCase2.endsWith(it.next())) {
                    scheme = "https";
                    break;
                }
            }
        }
        return uri.toString().replaceAll("\\?.*$", "").replaceAll("/*$", "").replace(userInfo + "@", "").replaceFirst(host, lowerCase2).replaceFirst(scheme, lowerCase).replace("fdroidrepo", "http").replace("/FDROID/REPO", "/fdroid/repo");
    }

    public WifiPeer toPeer() {
        return new WifiPeer(this);
    }

    public static boolean hasDisallowInstallUnknownSources(Context context) {
        UserManager userManager = (UserManager) context.getSystemService("user");
        if (Build.VERSION.SDK_INT < 29) {
            return userManager.hasUserRestriction("no_install_unknown_sources");
        }
        return userManager.hasUserRestriction("no_install_unknown_sources") || userManager.hasUserRestriction("no_install_unknown_sources_globally");
    }
}
