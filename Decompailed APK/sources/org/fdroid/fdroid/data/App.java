package org.fdroid.fdroid.data;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.pm.PackageInfoCompat;
import androidx.core.os.LocaleListCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.fdroid.IndexFile;
import org.fdroid.database.AppListItem;
import org.fdroid.database.AppPrefs;
import org.fdroid.database.Repository;
import org.fdroid.database.UpdatableApp;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: loaded from: classes2.dex */
public class App implements Comparable<App>, Parcelable {
    public static final Parcelable.Creator<App> CREATOR = new Parcelable.Creator<App>() { // from class: org.fdroid.fdroid.data.App.1
        @Override // android.os.Parcelable.Creator
        public App createFromParcel(Parcel parcel) {
            return new App(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public App[] newArray(int i) {
            return new App[i];
        }
    };
    private static final String TAG = "App";
    public static LocaleListCompat systemLocaleList;
    public Date added;
    public Map<String, String> antiFeatureReasons;
    public String[] antiFeatures;
    public String authorEmail;
    public String authorName;
    public long autoInstallVersionCode;
    private String autoInstallVersionName;
    public String bitcoin;
    public String[] categories;
    public String changelog;
    public boolean compatible;
    public String description;
    public String donate;
    public FileV2 featureGraphic;
    private String flattrID;
    public FileV2 iconFile;
    public Apk installedApk;
    public String installedSigner;
    public long installedVersionCode;
    public String installedVersionName;
    public boolean isApk;
    public String issueTracker;
    public Date lastUpdated;
    public String liberapay;
    public String license;
    public String litecoin;
    public String name;
    private String openCollective;
    public String packageName;
    public List<FileV2> phoneScreenshots;
    public String preferredSigner;
    public AppPrefs prefs;
    private FileV2 promoGraphic;
    public long repoId;
    private List<FileV2> sevenInchScreenshots;
    public String sourceCode;

    @Deprecated
    public int suggestedVersionCode;

    @Deprecated
    public String suggestedVersionName;
    public String summary;
    private List<FileV2> tenInchScreenshots;
    public String translation;
    private FileV2 tvBanner;
    private List<FileV2> tvScreenshots;
    public String video;
    private List<FileV2> wearScreenshots;
    public String webSite;
    public String whatsNew;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static LocaleListCompat getLocales() {
        LocaleListCompat localeListCompat = systemLocaleList;
        if (localeListCompat != null) {
            return localeListCompat;
        }
        LocaleListCompat localeListCompat2 = LocaleListCompat.getDefault();
        systemLocaleList = localeListCompat2;
        return localeListCompat2;
    }

    @Override // java.lang.Comparable
    public int compareTo(App app) {
        return this.name.compareToIgnoreCase(app.name);
    }

    public App() {
        this.packageName = "unknown";
        this.name = "Unknown";
        this.summary = "Unknown application";
        this.phoneScreenshots = Collections.emptyList();
        this.sevenInchScreenshots = Collections.emptyList();
        this.tenInchScreenshots = Collections.emptyList();
        this.tvScreenshots = Collections.emptyList();
        this.wearScreenshots = Collections.emptyList();
        this.suggestedVersionCode = Integer.MIN_VALUE;
        this.antiFeatureReasons = new HashMap();
    }

    public App(UpdatableApp updatableApp) {
        this.packageName = "unknown";
        this.name = "Unknown";
        this.summary = "Unknown application";
        this.phoneScreenshots = Collections.emptyList();
        this.sevenInchScreenshots = Collections.emptyList();
        this.tenInchScreenshots = Collections.emptyList();
        this.tvScreenshots = Collections.emptyList();
        this.wearScreenshots = Collections.emptyList();
        this.suggestedVersionCode = Integer.MIN_VALUE;
        this.antiFeatureReasons = new HashMap();
        this.repoId = updatableApp.getUpdate().getRepoId();
        setPackageName(updatableApp.getPackageName());
        this.name = updatableApp.getName() == null ? "" : updatableApp.getName();
        this.summary = updatableApp.getSummary() != null ? updatableApp.getSummary() : "";
        this.installedVersionCode = updatableApp.getInstalledVersionCode();
        this.autoInstallVersionCode = updatableApp.getUpdate().getManifest().getVersionCode();
        this.iconFile = updatableApp.getIcon(getLocales());
    }

    public App(org.fdroid.database.App app, PackageInfo packageInfo) {
        this.packageName = "unknown";
        this.name = "Unknown";
        this.summary = "Unknown application";
        this.phoneScreenshots = Collections.emptyList();
        this.sevenInchScreenshots = Collections.emptyList();
        this.tenInchScreenshots = Collections.emptyList();
        this.tvScreenshots = Collections.emptyList();
        this.wearScreenshots = Collections.emptyList();
        this.suggestedVersionCode = Integer.MIN_VALUE;
        this.antiFeatureReasons = new HashMap();
        this.repoId = app.getRepoId();
        this.compatible = app.getMetadata().isCompatible();
        setPackageName(app.getPackageName());
        this.name = app.getName() == null ? "" : app.getName();
        this.summary = app.getSummary() == null ? "" : app.getSummary();
        String description = app.getDescription(getLocales());
        setDescription(description != null ? description : "");
        this.license = app.getMetadata().getLicense();
        this.authorName = app.getMetadata().getAuthorName();
        this.authorEmail = app.getMetadata().getAuthorEmail();
        this.webSite = app.getMetadata().getWebSite();
        this.issueTracker = app.getMetadata().getIssueTracker();
        this.sourceCode = app.getMetadata().getSourceCode();
        this.translation = app.getMetadata().getTranslation();
        this.video = app.getVideo(getLocales());
        this.changelog = app.getMetadata().getChangelog();
        List<String> donate = app.getMetadata().getDonate();
        if (donate != null && !donate.isEmpty()) {
            this.donate = donate.get(0);
        }
        this.bitcoin = app.getMetadata().getBitcoin();
        this.litecoin = app.getMetadata().getLitecoin();
        this.flattrID = app.getMetadata().getFlattrID();
        this.liberapay = app.getMetadata().getLiberapay();
        this.openCollective = app.getMetadata().getOpenCollective();
        this.preferredSigner = app.getMetadata().getPreferredSigner();
        this.added = new Date(app.getMetadata().getAdded());
        this.lastUpdated = new Date(app.getMetadata().getLastUpdated());
        this.iconFile = app.getIcon(getLocales());
        this.featureGraphic = app.getFeatureGraphic(getLocales());
        this.promoGraphic = app.getPromoGraphic(getLocales());
        this.tvBanner = app.getTvBanner(getLocales());
        this.phoneScreenshots = app.getPhoneScreenshots(getLocales());
        this.sevenInchScreenshots = app.getSevenInchScreenshots(getLocales());
        this.tenInchScreenshots = app.getTenInchScreenshots(getLocales());
        this.tvScreenshots = app.getTvScreenshots(getLocales());
        this.wearScreenshots = app.getWearScreenshots(getLocales());
        setInstalled(packageInfo);
    }

    public App(AppListItem appListItem) {
        this.packageName = "unknown";
        this.name = "Unknown";
        this.summary = "Unknown application";
        this.phoneScreenshots = Collections.emptyList();
        this.sevenInchScreenshots = Collections.emptyList();
        this.tenInchScreenshots = Collections.emptyList();
        this.tvScreenshots = Collections.emptyList();
        this.wearScreenshots = Collections.emptyList();
        this.suggestedVersionCode = Integer.MIN_VALUE;
        this.antiFeatureReasons = new HashMap();
        this.repoId = appListItem.getRepoId();
        setPackageName(appListItem.getPackageName());
        this.name = appListItem.getName() == null ? "" : appListItem.getName();
        this.summary = appListItem.getSummary() != null ? appListItem.getSummary() : "";
        this.iconFile = appListItem.getIcon(getLocales());
        this.installedVersionCode = appListItem.getInstalledVersionCode() == null ? 0L : appListItem.getInstalledVersionCode().longValue();
        this.installedVersionName = appListItem.getInstalledVersionName();
        this.antiFeatures = (String[]) appListItem.getAntiFeatureKeys().toArray(new String[0]);
        this.compatible = appListItem.isCompatible();
        this.preferredSigner = appListItem.getPreferredSigner();
    }

    public void setInstalled(PackageInfo packageInfo) {
        this.installedVersionCode = packageInfo == null ? 0L : PackageInfoCompat.getLongVersionCode(packageInfo);
        this.installedVersionName = packageInfo == null ? null : packageInfo.versionName;
        this.installedSigner = packageInfo != null ? Utils.getPackageSigner(packageInfo) : null;
    }

    public void update(Context context, List<Apk> list, AppPrefs appPrefs) {
        this.prefs = appPrefs;
        for (Apk apk : list) {
            if ((apk.versionCode == this.installedVersionCode && TextUtils.equals(apk.signer, this.installedSigner)) || (!apk.isApk() && apk.isMediaInstalled(context))) {
                this.installedApk = apk;
                this.installedVersionCode = apk.versionCode;
                this.installedVersionName = apk.versionName;
                break;
            }
        }
        Apk apkFindSuggestedApk = findSuggestedApk(list, appPrefs);
        if (apkFindSuggestedApk == null) {
            return;
        }
        if (this.autoInstallVersionCode <= 0) {
            long j = this.installedVersionCode;
            long j2 = apkFindSuggestedApk.versionCode;
            if (j < j2) {
                this.autoInstallVersionCode = j2;
                this.autoInstallVersionName = apkFindSuggestedApk.versionName;
            }
        }
        this.antiFeatures = apkFindSuggestedApk.antiFeatures;
        this.antiFeatureReasons = apkFindSuggestedApk.antiFeatureReasons;
        this.whatsNew = apkFindSuggestedApk.whatsNew;
        this.isApk = apkFindSuggestedApk.isApk();
    }

    private void setDescription(String str) {
        this.description = formatDescription(str);
    }

    private void setPackageName(String str) {
        if (Utils.isSafePackageName(str)) {
            this.packageName = str;
            return;
        }
        throw new IllegalArgumentException("Repo index app entry includes unsafe packageName: '" + str + "'");
    }

    private static String formatDescription(String str) {
        return str.replace("\n", "<br>");
    }

    public Uri getShareUri(Context context) {
        Repository repository = FDroidApp.getRepoManager(context).getRepository(this.repoId);
        if (repository == null || repository.getWebBaseUrl() == null) {
            return null;
        }
        return Uri.parse(repository.getWebBaseUrl()).buildUpon().appendPath(this.packageName).build();
    }

    public RequestBuilder loadWithGlide(Context context, IndexFile indexFile) {
        return loadWithGlide(context, this.repoId, indexFile);
    }

    public static RequestBuilder loadWithGlide(Context context, long j, IndexFile indexFile) {
        Repository repository = FDroidApp.getRepoManager(context).getRepository(j);
        if (repository == null || indexFile == null) {
            return Glide.with(context).load(Integer.valueOf(R.drawable.ic_repo_app_default));
        }
        return Glide.with(context).load(Utils.getGlideModel(repository, indexFile));
    }

    public static RequestBuilder loadBitmapWithGlide(Context context, long j, IndexFile indexFile) {
        Repository repository = FDroidApp.getRepoManager(context).getRepository(j);
        if (repository == null) {
            Log.e(TAG, "Repo not found: " + j);
            return Glide.with(context).asBitmap().load(Integer.valueOf(R.drawable.ic_repo_app_default));
        }
        return Glide.with(context).asBitmap().load(Utils.getGlideModel(repository, indexFile));
    }

    public static String getPath(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Collections.addAll(arrayList, str.split("/"));
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append("/");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public List<FileV2> getAllScreenshots() {
        ArrayList arrayList = new ArrayList(this.phoneScreenshots);
        arrayList.addAll(this.sevenInchScreenshots);
        arrayList.addAll(this.tenInchScreenshots);
        arrayList.addAll(this.tvScreenshots);
        arrayList.addAll(this.wearScreenshots);
        return arrayList;
    }

    static File getObbDir(String str) {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/obb/" + str);
    }

    public Apk getInstalledApk(Context context, List<Apk> list) {
        Apk next;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.packageName, 0);
            Iterator<Apk> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (next.versionCode == PackageInfoCompat.getLongVersionCode(packageInfo)) {
                    break;
                }
            }
            return next == null ? new Apk(packageInfo) : next;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public boolean isInstalled(Context context) {
        return this.installedVersionCode > 0 || (!isApk() && isMediaInstalled(context));
    }

    private boolean isApk() {
        return this.isApk;
    }

    private boolean isMediaInstalled(Context context) {
        return getMediaApkifInstalled(context) != null;
    }

    public Apk getMediaApkifInstalled(Context context) {
        Apk apk = this.installedApk;
        if (apk == null || apk.isApk() || !this.installedApk.isMediaInstalled(context)) {
            return null;
        }
        return this.installedApk;
    }

    @Deprecated
    public boolean hasUpdates() {
        long j = this.autoInstallVersionCode;
        if (j <= 0) {
            return false;
        }
        long j2 = this.installedVersionCode;
        return j2 > 0 && j2 < j;
    }

    public boolean hasUpdates(List<Apk> list, AppPrefs appPrefs) {
        Apk apkFindSuggestedApk = findSuggestedApk(list, appPrefs);
        if (apkFindSuggestedApk == null) {
            return false;
        }
        long j = this.installedVersionCode;
        return j > 0 && j < apkFindSuggestedApk.versionCode;
    }

    public Apk findSuggestedApk(List<Apk> list, AppPrefs appPrefs) {
        return findSuggestedApk(list, appPrefs.getReleaseChannels().contains("Beta") ? "Beta" : Preferences.get().getReleaseChannel());
    }

    public Apk findSuggestedApk(List<Apk> list, String str) {
        Apk next;
        String mostAppropriateSigner = getMostAppropriateSigner();
        Iterator<Apk> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next.compatible && (mostAppropriateSigner == null || mostAppropriateSigner.equals(next.signer))) {
                if (Apk.RELEASE_CHANNEL_STABLE.equals(str) ? next.releaseChannels.contains(Apk.RELEASE_CHANNEL_STABLE) : next.releaseChannels.contains(Apk.RELEASE_CHANNEL_STABLE) || next.releaseChannels.contains(str)) {
                    break;
                }
            }
        }
        return (next != null || list.size() <= 0) ? next : list.get(0);
    }

    public boolean canAndWantToUpdate(Apk apk) {
        if (apk == null || this.installedVersionCode >= apk.versionCode) {
            return false;
        }
        AppPrefs appPrefs = this.prefs;
        return appPrefs == null || !appPrefs.shouldIgnoreUpdate(this.autoInstallVersionCode);
    }

    public boolean isDisabledByAntiFeatures(Context context) {
        if (this.antiFeatures == null) {
            return false;
        }
        List listAsList = Arrays.asList(context.getResources().getStringArray(R.array.antifeaturesValues));
        Set<String> setShowAppsWithAntiFeatures = Preferences.get().showAppsWithAntiFeatures();
        for (String str : this.antiFeatures) {
            if (listAsList.contains(str)) {
                if (!setShowAppsWithAntiFeatures.contains(str)) {
                    return true;
                }
            } else if (!setShowAppsWithAntiFeatures.contains(context.getResources().getString(R.string.antiothers_key))) {
                return true;
            }
        }
        return false;
    }

    public String getBitcoinUri() {
        if (TextUtils.isEmpty(this.bitcoin)) {
            return null;
        }
        return "bitcoin:" + this.bitcoin;
    }

    public String getLitecoinUri() {
        if (TextUtils.isEmpty(this.litecoin)) {
            return null;
        }
        return "litecoin:" + this.litecoin;
    }

    public String getOpenCollectiveUri() {
        if (TextUtils.isEmpty(this.openCollective)) {
            return null;
        }
        return "https://opencollective.com/" + this.openCollective + "/donate";
    }

    public String getFlattrUri() {
        if (TextUtils.isEmpty(this.flattrID)) {
            return null;
        }
        return "https://flattr.com/thing/" + this.flattrID;
    }

    public String getLiberapayUri() {
        if (TextUtils.isEmpty(this.liberapay)) {
            return null;
        }
        return "https://liberapay.com/" + this.liberapay + "/donate";
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
    
        r6 = 0;
        r2 = 127;
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
    
        if (r0 >= r5.getAttributeCount()) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003a, code lost:
    
        if (r5.getAttributeName(r0).equals("minSdkVersion") == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003c, code lost:
    
        r6 = java.lang.Integer.parseInt(r5.getAttributeValue(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
    
        r5 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0046, code lost:
    
        r0 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0048, code lost:
    
        r5 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004a, code lost:
    
        r5 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
    
        r5 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0058, code lost:
    
        if (r5.getAttributeName(r0).equals("targetSdkVersion") == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005a, code lost:
    
        r1 = java.lang.Integer.parseInt(r5.getAttributeValue(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006d, code lost:
    
        if (r5.getAttributeName(r0).equals("maxSdkVersion") == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006f, code lost:
    
        r2 = java.lang.Integer.parseInt(r5.getAttributeValue(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0077, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007a, code lost:
    
        r0 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x008e, code lost:
    
        android.util.Log.e(org.fdroid.fdroid.data.App.TAG, "Could not get min/max sdk version", r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int[] getMinTargetMaxSdkVersions(android.content.Context r5, java.lang.String r6) {
        /*
            r0 = 0
            r1 = 127(0x7f, float:1.78E-43)
            android.content.Context r5 = r5.createPackageContext(r6, r0)     // Catch: java.lang.NumberFormatException -> L7c org.xmlpull.v1.XmlPullParserException -> L80 java.io.IOException -> L82 android.content.pm.PackageManager.NameNotFoundException -> L84
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch: java.lang.NumberFormatException -> L7c org.xmlpull.v1.XmlPullParserException -> L80 java.io.IOException -> L82 android.content.pm.PackageManager.NameNotFoundException -> L84
            java.lang.String r6 = "AndroidManifest.xml"
            android.content.res.XmlResourceParser r5 = r5.openXmlResourceParser(r6)     // Catch: java.lang.NumberFormatException -> L7c org.xmlpull.v1.XmlPullParserException -> L80 java.io.IOException -> L82 android.content.pm.PackageManager.NameNotFoundException -> L84
            int r6 = r5.getEventType()     // Catch: java.lang.NumberFormatException -> L7c org.xmlpull.v1.XmlPullParserException -> L80 java.io.IOException -> L82 android.content.pm.PackageManager.NameNotFoundException -> L84
        L15:
            r2 = 1
            if (r6 == r2) goto L8b
            r2 = 2
            if (r6 != r2) goto L86
            java.lang.String r6 = "uses-sdk"
            java.lang.String r2 = r5.getName()     // Catch: java.lang.NumberFormatException -> L7c org.xmlpull.v1.XmlPullParserException -> L80 java.io.IOException -> L82 android.content.pm.PackageManager.NameNotFoundException -> L84
            boolean r6 = r6.equals(r2)     // Catch: java.lang.NumberFormatException -> L7c org.xmlpull.v1.XmlPullParserException -> L80 java.io.IOException -> L82 android.content.pm.PackageManager.NameNotFoundException -> L84
            if (r6 == 0) goto L86
            r6 = r0
            r2 = r1
            r1 = r6
        L2a:
            int r3 = r5.getAttributeCount()     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            if (r0 >= r3) goto L7a
            java.lang.String r3 = r5.getAttributeName(r0)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            java.lang.String r4 = "minSdkVersion"
            boolean r3 = r3.equals(r4)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            if (r3 == 0) goto L4e
            java.lang.String r3 = r5.getAttributeValue(r0)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            int r6 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            goto L77
        L45:
            r5 = move-exception
        L46:
            r0 = r6
            goto L8e
        L48:
            r5 = move-exception
            goto L46
        L4a:
            r5 = move-exception
            goto L46
        L4c:
            r5 = move-exception
            goto L46
        L4e:
            java.lang.String r3 = r5.getAttributeName(r0)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            java.lang.String r4 = "targetSdkVersion"
            boolean r3 = r3.equals(r4)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            if (r3 == 0) goto L63
            java.lang.String r3 = r5.getAttributeValue(r0)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            int r1 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            goto L77
        L63:
            java.lang.String r3 = r5.getAttributeName(r0)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            java.lang.String r4 = "maxSdkVersion"
            boolean r3 = r3.equals(r4)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            if (r3 == 0) goto L77
            java.lang.String r3 = r5.getAttributeValue(r0)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
            int r2 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L45 org.xmlpull.v1.XmlPullParserException -> L48 java.io.IOException -> L4a android.content.pm.PackageManager.NameNotFoundException -> L4c
        L77:
            int r0 = r0 + 1
            goto L2a
        L7a:
            r0 = r6
            goto L95
        L7c:
            r5 = move-exception
        L7d:
            r2 = r1
            r1 = r0
            goto L8e
        L80:
            r5 = move-exception
            goto L7d
        L82:
            r5 = move-exception
            goto L7d
        L84:
            r5 = move-exception
            goto L7d
        L86:
            int r6 = r5.nextToken()     // Catch: java.lang.NumberFormatException -> L7c org.xmlpull.v1.XmlPullParserException -> L80 java.io.IOException -> L82 android.content.pm.PackageManager.NameNotFoundException -> L84
            goto L15
        L8b:
            r2 = r1
            r1 = r0
            goto L95
        L8e:
            java.lang.String r6 = "App"
            java.lang.String r3 = "Could not get min/max sdk version"
            android.util.Log.e(r6, r3, r5)
        L95:
            if (r1 >= r0) goto L98
            r1 = r0
        L98:
            int[] r5 = new int[]{r0, r1, r2}
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.data.App.getMinTargetMaxSdkVersions(android.content.Context, java.lang.String):int[]");
    }

    public boolean isUninstallable(Context context) {
        if (isMediaInstalled(context)) {
            return true;
        }
        if (isInstalled(context)) {
            try {
                return (context.getPackageManager().getApplicationInfo(this.packageName, 8192).flags & 1) == 0 && isInstalled(context);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.compatible ? (byte) 1 : (byte) 0);
        parcel.writeString(this.packageName);
        parcel.writeString(this.name);
        parcel.writeLong(this.repoId);
        parcel.writeString(this.summary);
        FileV2 fileV2 = this.iconFile;
        parcel.writeString(fileV2 == null ? null : fileV2.serialize());
        parcel.writeString(this.description);
        parcel.writeString(this.whatsNew);
        parcel.writeString(this.license);
        parcel.writeString(this.authorName);
        parcel.writeString(this.authorEmail);
        parcel.writeString(this.webSite);
        parcel.writeString(this.issueTracker);
        parcel.writeString(this.sourceCode);
        parcel.writeString(this.translation);
        parcel.writeString(this.video);
        parcel.writeString(this.changelog);
        parcel.writeString(this.donate);
        parcel.writeString(this.bitcoin);
        parcel.writeString(this.litecoin);
        parcel.writeString(this.flattrID);
        parcel.writeString(this.liberapay);
        parcel.writeString(this.openCollective);
        parcel.writeString(this.preferredSigner);
        parcel.writeString(this.suggestedVersionName);
        parcel.writeInt(this.suggestedVersionCode);
        parcel.writeString(this.autoInstallVersionName);
        parcel.writeLong(this.autoInstallVersionCode);
        Date date = this.added;
        parcel.writeLong(date != null ? date.getTime() : -1L);
        Date date2 = this.lastUpdated;
        parcel.writeLong(date2 != null ? date2.getTime() : -1L);
        parcel.writeStringArray(this.categories);
        parcel.writeStringArray(this.antiFeatures);
        FileV2 fileV22 = this.featureGraphic;
        parcel.writeString(fileV22 == null ? null : fileV22.serialize());
        FileV2 fileV23 = this.promoGraphic;
        parcel.writeString(fileV23 == null ? null : fileV23.serialize());
        FileV2 fileV24 = this.tvBanner;
        parcel.writeString(fileV24 != null ? fileV24.serialize() : null);
        parcel.writeStringList(Utils.toString(this.phoneScreenshots));
        parcel.writeStringList(Utils.toString(this.sevenInchScreenshots));
        parcel.writeStringList(Utils.toString(this.tenInchScreenshots));
        parcel.writeStringList(Utils.toString(this.tvScreenshots));
        parcel.writeStringList(Utils.toString(this.wearScreenshots));
        parcel.writeByte(this.isApk ? (byte) 1 : (byte) 0);
        parcel.writeString(this.installedVersionName);
        parcel.writeLong(this.installedVersionCode);
        parcel.writeParcelable(this.installedApk, i);
        parcel.writeString(this.installedSigner);
    }

    protected App(Parcel parcel) {
        this.packageName = "unknown";
        this.name = "Unknown";
        this.summary = "Unknown application";
        this.phoneScreenshots = Collections.emptyList();
        this.sevenInchScreenshots = Collections.emptyList();
        this.tenInchScreenshots = Collections.emptyList();
        this.tvScreenshots = Collections.emptyList();
        this.wearScreenshots = Collections.emptyList();
        this.suggestedVersionCode = Integer.MIN_VALUE;
        this.antiFeatureReasons = new HashMap();
        this.compatible = parcel.readByte() != 0;
        this.packageName = parcel.readString();
        this.name = parcel.readString();
        this.repoId = parcel.readLong();
        this.summary = parcel.readString();
        this.iconFile = FileV2.deserialize(parcel.readString());
        this.description = parcel.readString();
        this.whatsNew = parcel.readString();
        this.license = parcel.readString();
        this.authorName = parcel.readString();
        this.authorEmail = parcel.readString();
        this.webSite = parcel.readString();
        this.issueTracker = parcel.readString();
        this.sourceCode = parcel.readString();
        this.translation = parcel.readString();
        this.video = parcel.readString();
        this.changelog = parcel.readString();
        this.donate = parcel.readString();
        this.bitcoin = parcel.readString();
        this.litecoin = parcel.readString();
        this.flattrID = parcel.readString();
        this.liberapay = parcel.readString();
        this.openCollective = parcel.readString();
        this.preferredSigner = parcel.readString();
        this.suggestedVersionName = parcel.readString();
        this.suggestedVersionCode = parcel.readInt();
        this.autoInstallVersionName = parcel.readString();
        this.autoInstallVersionCode = parcel.readLong();
        long j = parcel.readLong();
        this.added = j == -1 ? null : new Date(j);
        long j2 = parcel.readLong();
        this.lastUpdated = j2 != -1 ? new Date(j2) : null;
        this.categories = parcel.createStringArray();
        this.antiFeatures = parcel.createStringArray();
        this.featureGraphic = FileV2.deserialize(parcel.readString());
        this.promoGraphic = FileV2.deserialize(parcel.readString());
        this.tvBanner = FileV2.deserialize(parcel.readString());
        this.phoneScreenshots = Utils.fileV2FromStrings(parcel.createStringArrayList());
        this.sevenInchScreenshots = Utils.fileV2FromStrings(parcel.createStringArrayList());
        this.tenInchScreenshots = Utils.fileV2FromStrings(parcel.createStringArrayList());
        this.tvScreenshots = Utils.fileV2FromStrings(parcel.createStringArrayList());
        this.wearScreenshots = Utils.fileV2FromStrings(parcel.createStringArrayList());
        this.isApk = parcel.readByte() != 0;
        this.installedVersionName = parcel.readString();
        this.installedVersionCode = parcel.readLong();
        this.installedApk = (Apk) parcel.readParcelable(Apk.class.getClassLoader());
        this.installedSigner = parcel.readString();
    }

    private String getMostAppropriateSigner() {
        if (!TextUtils.isEmpty(this.installedSigner)) {
            return this.installedSigner;
        }
        if (TextUtils.isEmpty(this.preferredSigner)) {
            return null;
        }
        return this.preferredSigner;
    }
}
