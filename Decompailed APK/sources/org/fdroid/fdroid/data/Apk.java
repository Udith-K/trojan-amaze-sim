package org.fdroid.fdroid.data;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.core.os.LocaleListCompat;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipFile;
import org.fdroid.database.AppManifest;
import org.fdroid.database.AppVersion;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.CompatibilityChecker;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.installer.ApkCache;
import org.fdroid.index.v2.FileV1;
import org.fdroid.index.v2.PermissionV2;
import org.fdroid.index.v2.SignerV2;

/* JADX INFO: loaded from: classes2.dex */
public class Apk implements Comparable<Apk>, Parcelable {
    public static final Parcelable.Creator<Apk> CREATOR = new Parcelable.Creator<Apk>() { // from class: org.fdroid.fdroid.data.Apk.1
        @Override // android.os.Parcelable.Creator
        public Apk createFromParcel(Parcel parcel) {
            return new Apk(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Apk[] newArray(int i) {
            return new Apk[i];
        }
    };
    public static final String RELEASE_CHANNEL_BETA = "Beta";
    public static final String RELEASE_CHANNEL_STABLE = "Stable";
    public static final int SDK_VERSION_MAX_VALUE = 127;
    public static final int SDK_VERSION_MIN_VALUE = 0;
    public Date added;
    Map<String, String> antiFeatureReasons;
    String[] antiFeatures;
    public FileV1 apkFile;
    public String canonicalRepoAddress;
    public boolean compatible;
    public String[] features;
    public String[] incompatibleReasons;
    private SanitizedFile installedFile;
    public int maxSdkVersion;
    public int minSdkVersion;
    public String[] nativecode;
    private String obbMainFile;
    public String obbMainFileSha256;
    private String obbPatchFile;
    public String obbPatchFileSha256;
    public String packageName;
    public List<String> releaseChannels;
    public String repoAddress;
    public long repoId;
    public String[] requestedPermissions;
    public String signer;
    public long size;
    private String srcname;
    public int targetSdkVersion;
    public long versionCode;
    public String versionName;
    String whatsNew;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Apk() {
        this.minSdkVersion = 0;
        this.targetSdkVersion = 0;
        this.maxSdkVersion = 127;
        this.antiFeatureReasons = new HashMap();
    }

    public Apk(PackageInfo packageInfo) {
        this.minSdkVersion = 0;
        this.targetSdkVersion = 0;
        this.maxSdkVersion = 127;
        this.antiFeatureReasons = new HashMap();
        this.packageName = packageInfo.packageName;
        this.versionName = packageInfo.versionName;
        this.versionCode = packageInfo.versionCode;
        this.targetSdkVersion = packageInfo.applicationInfo.targetSdkVersion;
        this.releaseChannels = Collections.emptyList();
        this.size = 0L;
        this.installedFile = null;
        this.repoId = 0L;
    }

    public Apk(AppVersion appVersion, Repository repository) {
        this.minSdkVersion = 0;
        this.targetSdkVersion = 0;
        this.maxSdkVersion = 127;
        this.antiFeatureReasons = new HashMap();
        if (appVersion.getRepoId() != repository.getRepoId()) {
            throw new IllegalArgumentException();
        }
        this.repoAddress = Utils.getRepoAddress(repository);
        this.canonicalRepoAddress = repository.getAddress();
        this.added = new Date(appVersion.getAdded());
        this.features = (String[]) appVersion.getFeatureNames().toArray(new String[0]);
        setPackageName(appVersion.getPackageName());
        this.compatible = appVersion.isCompatible();
        AppManifest manifest = appVersion.getManifest();
        this.minSdkVersion = manifest.getUsesSdk() == null ? 0 : manifest.getUsesSdk().getMinSdkVersion();
        this.targetSdkVersion = manifest.getUsesSdk() == null ? this.minSdkVersion : manifest.getUsesSdk().getTargetSdkVersion();
        this.maxSdkVersion = manifest.getMaxSdkVersion() != null ? manifest.getMaxSdkVersion().intValue() : 127;
        List<String> releaseChannels = appVersion.getReleaseChannels();
        if (releaseChannels.isEmpty()) {
            this.releaseChannels = Collections.singletonList(RELEASE_CHANNEL_STABLE);
        } else {
            this.releaseChannels = releaseChannels;
        }
        this.apkFile = appVersion.getFile();
        setRequestedPermissions(appVersion.getUsesPermission(), 0);
        setRequestedPermissions(appVersion.getUsesPermissionSdk23(), 23);
        this.nativecode = (String[]) appVersion.getNativeCode().toArray(new String[0]);
        this.repoId = appVersion.getRepoId();
        SignerV2 signer = appVersion.getManifest().getSigner();
        this.signer = signer == null ? null : signer.getSha256().get(0);
        this.size = appVersion.getFile().getSize() == null ? 0L : appVersion.getFile().getSize().longValue();
        this.srcname = appVersion.getSrc() != null ? appVersion.getSrc().getName() : null;
        this.versionName = manifest.getVersionName();
        this.versionCode = manifest.getVersionCode();
        this.antiFeatures = (String[]) appVersion.getAntiFeatureKeys().toArray(new String[0]);
        LocaleListCompat localeListCompat = LocaleListCompat.getDefault();
        this.antiFeatureReasons.clear();
        for (String str : this.antiFeatures) {
            this.antiFeatureReasons.put(str, appVersion.getAntiFeatureReason(str, localeListCompat));
        }
        this.whatsNew = appVersion.getWhatsNew(App.getLocales());
    }

    public void setCompatibility(CompatibilityChecker compatibilityChecker) {
        List<String> incompatibleReasons = compatibilityChecker.getIncompatibleReasons(this);
        if (incompatibleReasons.isEmpty()) {
            this.compatible = true;
            this.incompatibleReasons = null;
        } else {
            this.compatible = false;
            this.incompatibleReasons = (String[]) incompatibleReasons.toArray(new String[0]);
        }
    }

    private void checkRepoAddress() {
        if (this.repoAddress == null || this.apkFile == null) {
            throw new IllegalStateException("Apk needs to have both Schema.ApkTable.Cols.REPO_ADDRESS and Schema.ApkTable.Cols.NAME set in order to calculate URL [package: " + this.packageName + ", versionCode: " + this.versionCode + ", apkName: " + getApkPath() + ", repoAddress: " + this.repoAddress + ", repoId: " + this.repoId + "]");
        }
    }

    public String getApkPath() {
        FileV1 fileV1 = this.apkFile;
        return fileV1 == null ? "" : fileV1.getName();
    }

    public String getCanonicalUrl() {
        checkRepoAddress();
        return Utils.getUri(this.canonicalRepoAddress, getApkPath().split("/")).toString();
    }

    public String getDownloadUrl() {
        checkRepoAddress();
        return Utils.getUri(this.repoAddress, getApkPath().split("/")).toString();
    }

    public String getMainObbUrl() {
        if (this.repoAddress == null || this.obbMainFile == null) {
            return null;
        }
        checkRepoAddress();
        return this.repoAddress + "/" + this.obbMainFile;
    }

    public String getPatchObbUrl() {
        if (this.repoAddress == null || this.obbPatchFile == null) {
            return null;
        }
        checkRepoAddress();
        return this.repoAddress + "/" + this.obbPatchFile;
    }

    public File getMainObbFile() {
        if (this.obbMainFile == null) {
            return null;
        }
        return new File(App.getObbDir(this.packageName), this.obbMainFile);
    }

    public File getPatchObbFile() {
        if (this.obbPatchFile == null) {
            return null;
        }
        return new File(App.getObbDir(this.packageName), this.obbPatchFile);
    }

    @Override // java.lang.Comparable
    public int compareTo(Apk apk) {
        return Long.compare(this.versionCode, apk.versionCode);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.packageName);
        parcel.writeString(this.versionName);
        parcel.writeLong(this.versionCode);
        parcel.writeLong(this.size);
        parcel.writeLong(this.repoId);
        parcel.writeInt(this.minSdkVersion);
        parcel.writeInt(this.targetSdkVersion);
        parcel.writeInt(this.maxSdkVersion);
        parcel.writeString(this.obbMainFile);
        parcel.writeString(this.obbMainFileSha256);
        parcel.writeString(this.obbPatchFile);
        parcel.writeString(this.obbPatchFileSha256);
        Date date = this.added;
        parcel.writeLong(date != null ? date.getTime() : -1L);
        parcel.writeStringArray(this.requestedPermissions);
        parcel.writeStringArray(this.features);
        parcel.writeStringArray(this.nativecode);
        parcel.writeString(this.signer);
        parcel.writeByte(this.compatible ? (byte) 1 : (byte) 0);
        FileV1 fileV1 = this.apkFile;
        parcel.writeString(fileV1 != null ? fileV1.serialize() : null);
        parcel.writeSerializable(this.installedFile);
        parcel.writeString(this.srcname);
        parcel.writeString(this.repoAddress);
        parcel.writeString(this.canonicalRepoAddress);
        parcel.writeStringArray(this.incompatibleReasons);
        parcel.writeStringArray(this.antiFeatures);
    }

    protected Apk(Parcel parcel) {
        this.minSdkVersion = 0;
        this.targetSdkVersion = 0;
        this.maxSdkVersion = 127;
        this.antiFeatureReasons = new HashMap();
        this.packageName = parcel.readString();
        this.versionName = parcel.readString();
        this.versionCode = parcel.readLong();
        this.size = parcel.readLong();
        this.repoId = parcel.readLong();
        this.minSdkVersion = parcel.readInt();
        this.targetSdkVersion = parcel.readInt();
        this.maxSdkVersion = parcel.readInt();
        this.obbMainFile = parcel.readString();
        this.obbMainFileSha256 = parcel.readString();
        this.obbPatchFile = parcel.readString();
        this.obbPatchFileSha256 = parcel.readString();
        long j = parcel.readLong();
        this.added = j == -1 ? null : new Date(j);
        this.requestedPermissions = parcel.createStringArray();
        this.features = parcel.createStringArray();
        this.nativecode = parcel.createStringArray();
        this.signer = parcel.readString();
        this.compatible = parcel.readByte() != 0;
        this.apkFile = FileV1.deserialize(parcel.readString());
        this.installedFile = (SanitizedFile) parcel.readSerializable();
        this.srcname = parcel.readString();
        this.repoAddress = parcel.readString();
        this.canonicalRepoAddress = parcel.readString();
        this.incompatibleReasons = parcel.createStringArray();
        this.antiFeatures = parcel.createStringArray();
    }

    private void setPackageName(String str) {
        if (Utils.isSafePackageName(str)) {
            this.packageName = str;
            return;
        }
        throw new IllegalArgumentException("Repo index package entry includes unsafe packageName: '" + str + "'");
    }

    public void setRequestedPermissions(List<PermissionV2> list, int i) {
        HashSet hashSet = new HashSet();
        String[] strArr = this.requestedPermissions;
        if (strArr != null) {
            Collections.addAll(hashSet, strArr);
        }
        for (PermissionV2 permissionV2 : list) {
            int iIntValue = permissionV2.getMaxSdkVersion() != null ? permissionV2.getMaxSdkVersion().intValue() : Integer.MAX_VALUE;
            int i2 = Build.VERSION.SDK_INT;
            if (i <= i2 && i2 <= iIntValue) {
                hashSet.add(permissionV2.getName());
            }
        }
        if (hashSet.contains("android.permission.WRITE_EXTERNAL_STORAGE")) {
            hashSet.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            if (hashSet.contains("android.permission.ACCESS_FINE_LOCATION")) {
                hashSet.add("android.permission.ACCESS_COARSE_LOCATION");
            }
            if (this.targetSdkVersion < 29) {
                if (hashSet.contains("android.permission.ACCESS_FINE_LOCATION")) {
                    hashSet.add("android.permission.ACCESS_BACKGROUND_LOCATION");
                }
                if (hashSet.contains("android.permission.ACCESS_COARSE_LOCATION")) {
                    hashSet.add("android.permission.ACCESS_BACKGROUND_LOCATION");
                }
                if (hashSet.contains("android.permission.READ_EXTERNAL_STORAGE")) {
                    hashSet.add("android.permission.ACCESS_MEDIA_LOCATION");
                }
            }
        }
        if (i3 >= 31 && this.targetSdkVersion < 31 && (hashSet.contains("android.permission.BLUETOOTH") || hashSet.contains("android.permission.BLUETOOTH_ADMIN"))) {
            hashSet.add("android.permission.BLUETOOTH_SCAN");
            hashSet.add("android.permission.BLUETOOTH_CONNECT");
            hashSet.add("android.permission.BLUETOOTH_ADVERTISE");
        }
        if (i3 >= 33 && this.targetSdkVersion < 33) {
            if (hashSet.contains("android.permission.BODY_SENSORS")) {
                hashSet.add("android.permission.BODY_SENSORS_BACKGROUND");
            }
            if (hashSet.contains("android.permission.READ_EXTERNAL_STORAGE") || hashSet.contains("android.permission.WRITE_EXTERNAL_STORAGE")) {
                hashSet.add("android.permission.READ_MEDIA_AUDIO");
                hashSet.add("android.permission.READ_MEDIA_VIDEO");
                hashSet.add("android.permission.READ_MEDIA_IMAGES");
            }
            hashSet.add("android.permission.POST_NOTIFICATIONS");
        }
        if (i3 >= 34 && (hashSet.contains("android.permission.READ_MEDIA_IMAGES") || hashSet.contains("android.permission.READ_MEDIA_VIDEO") || hashSet.contains("android.permission.ACCESS_MEDIA_LOCATION"))) {
            hashSet.add("android.permission.READ_MEDIA_VISUAL_USER_SELECTED");
        }
        String[] strArr2 = (String[]) hashSet.toArray(new String[0]);
        if (strArr2.length == 0) {
            strArr2 = null;
        }
        this.requestedPermissions = strArr2;
    }

    public File getMediaInstallPath(Context context) {
        String str;
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(getCanonicalUrl());
        if (TextUtils.isEmpty(fileExtensionFromUrl)) {
            return externalStoragePublicDirectory;
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        if (TextUtils.isEmpty(mimeTypeFromExtension)) {
            str = null;
        } else {
            String[] strArrSplit = mimeTypeFromExtension.split("/");
            if (strArrSplit.length == 0) {
                str = "";
            } else {
                str = strArrSplit[0];
            }
        }
        if ("audio".equals(str)) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        }
        if ("image".equals(str)) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        }
        if ("video".equals(str)) {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        }
        if ("zip".equals(fileExtensionFromUrl)) {
            try {
                ZipFile zipFile = new ZipFile(ApkCache.getApkDownloadPath(context, getCanonicalUrl()));
                try {
                    if (zipFile.size() == 1) {
                        String name = zipFile.entries().nextElement().getName();
                        if (name != null && name.endsWith(".obf")) {
                            File cacheDir = context.getCacheDir();
                            zipFile.close();
                            return cacheDir;
                        }
                    } else if (zipFile.getEntry("META-INF/com/google/android/update-binary") != null) {
                        File file = new File(context.getApplicationInfo().dataDir + "/ota");
                        zipFile.close();
                        return file;
                    }
                    zipFile.close();
                } finally {
                }
            } catch (IOException unused) {
            }
            return externalStoragePublicDirectory;
        }
        if ("apk".equals(fileExtensionFromUrl)) {
            throw new IllegalStateException("APKs should not be handled in the media install path!");
        }
        return externalStoragePublicDirectory;
    }

    public File getInstalledMediaFile(Context context) {
        return new File(getMediaInstallPath(context), SanitizedFile.sanitizeFileName(getApkPath()));
    }

    public boolean isMediaInstalled(Context context) {
        return getInstalledMediaFile(context).isFile();
    }

    public boolean isApk() {
        FileV1 fileV1 = this.apkFile;
        return fileV1 == null || fileV1.getName().substring(this.apkFile.getName().length() + (-4)).toLowerCase(Locale.ENGLISH).endsWith(".apk");
    }
}
