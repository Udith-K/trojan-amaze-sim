package org.fdroid.fdroid.nearby;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import org.apache.commons.io.FileUtils;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.data.SanitizedFile;
import org.fdroid.fdroid.nearby.LocalRepoKeyStore;
import org.fdroid.index.v1.AppV1;
import org.fdroid.index.v1.IndexV1;
import org.fdroid.index.v1.IndexV1Creator;
import org.fdroid.index.v1.IndexV1UpdaterKt;
import org.fdroid.index.v1.IndexV1VerifierKt;
import org.fdroid.index.v1.PackageV1;
import org.fdroid.index.v1.RepoV1;

/* JADX INFO: loaded from: classes2.dex */
public final class LocalRepoManager {
    private static final String TAG = "LocalRepoManager";
    public static final String[] WEB_ROOT_ASSET_FILES = {"swap-icon.png", "swap-tick-done.png", "swap-tick-not-done.png"};
    private static LocalRepoManager localRepoManager;
    private final List<App> apps = new ArrayList();
    private final AssetManager assetManager;
    private final Context context;
    private final SanitizedFile fdroidDir;
    private final SanitizedFile fdroidDirCaps;
    private final String fdroidPackageName;
    private final SanitizedFile indexJar;
    private final SanitizedFile indexJarUnsigned;
    private final PackageManager pm;
    private final SanitizedFile repoDir;
    private final SanitizedFile repoDirCaps;
    private final SanitizedFile webRoot;

    public static LocalRepoManager get(Context context) {
        if (localRepoManager == null) {
            localRepoManager = new LocalRepoManager(context);
        }
        return localRepoManager;
    }

    private LocalRepoManager(Context context) {
        this.context = context.getApplicationContext();
        this.pm = context.getPackageManager();
        this.assetManager = context.getAssets();
        this.fdroidPackageName = context.getPackageName();
        SanitizedFile sanitizedFileKnownSanitized = SanitizedFile.knownSanitized(context.getFilesDir());
        this.webRoot = sanitizedFileKnownSanitized;
        SanitizedFile sanitizedFile = new SanitizedFile(sanitizedFileKnownSanitized, "fdroid");
        this.fdroidDir = sanitizedFile;
        SanitizedFile sanitizedFile2 = new SanitizedFile(sanitizedFileKnownSanitized, "FDROID");
        this.fdroidDirCaps = sanitizedFile2;
        SanitizedFile sanitizedFile3 = new SanitizedFile(sanitizedFile, "repo");
        this.repoDir = sanitizedFile3;
        this.repoDirCaps = new SanitizedFile(sanitizedFile2, "REPO");
        this.indexJar = new SanitizedFile(sanitizedFile3, IndexV1UpdaterKt.SIGNED_FILE_NAME);
        this.indexJarUnsigned = new SanitizedFile(sanitizedFile3, "index-v1.unsigned.jar");
        if (!sanitizedFile.exists() && !sanitizedFile.mkdir()) {
            Log.e(TAG, "Unable to create empty base: " + sanitizedFile);
        }
        if (!sanitizedFile3.exists() && !sanitizedFile3.mkdir()) {
            Log.e(TAG, "Unable to create empty repo: " + sanitizedFile3);
        }
        SanitizedFile sanitizedFile4 = new SanitizedFile(sanitizedFile3, "icons");
        if (sanitizedFile4.exists() || sanitizedFile4.mkdir()) {
            return;
        }
        Log.e(TAG, "Unable to create icons folder: " + sanitizedFile4);
    }

    private String writeFdroidApkToWebroot() {
        try {
            SanitizedFile sanitizedFileKnownSanitized = SanitizedFile.knownSanitized(this.pm.getApplicationInfo(this.fdroidPackageName, 128).publicSourceDir);
            SanitizedFile sanitizedFile = new SanitizedFile(this.fdroidDir, "F-Droid.apk");
            attemptToDelete(sanitizedFile);
            if (!Utils.symlinkOrCopyFileQuietly(sanitizedFileKnownSanitized, sanitizedFile)) {
                return "https://f-droid.org/F-Droid.apk";
            }
            return "/" + this.fdroidDir.getName() + "/" + sanitizedFile.getName();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Could not set up F-Droid apk in the webroot", e);
            return "https://f-droid.org/F-Droid.apk";
        }
    }

    void writeIndexPage(String str) {
        String strWriteFdroidApkToWebroot = writeFdroidApkToWebroot();
        try {
            File file = new File(this.webRoot, "index.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.assetManager.open("index.template.html"), "UTF-8"));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            StringBuilder sb = new StringBuilder();
            for (App app : this.apps) {
                sb.append("<li><a href=\"/fdroid/repo/");
                sb.append(app.packageName);
                sb.append("_");
                sb.append(app.installedApk.versionCode);
                sb.append(".apk\">");
                sb.append("<img width=\"32\" height=\"32\" src=\"/fdroid/repo/icons/");
                sb.append(app.packageName);
                sb.append("_");
                sb.append(app.installedApk.versionCode);
                sb.append(".png\">");
                sb.append(app.name);
                sb.append("</a></li>\n");
            }
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                } else {
                    bufferedWriter.write(line.replaceAll("\\{\\{REPO_URL\\}\\}", str).replaceAll("\\{\\{CLIENT_URL\\}\\}", strWriteFdroidApkToWebroot).replaceAll("\\{\\{APP_LIST\\}\\}", sb.toString()));
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            for (String str2 : WEB_ROOT_ASSET_FILES) {
                InputStream inputStreamOpen = this.assetManager.open(str2);
                FileOutputStream fileOutputStream = new FileOutputStream(new File(this.webRoot, str2));
                Utils.copy(inputStreamOpen, fileOutputStream);
                inputStreamOpen.close();
                fileOutputStream.close();
            }
            symlinkEntireWebRootElsewhere("../", this.fdroidDir);
            symlinkEntireWebRootElsewhere("../../", this.repoDir);
            attemptToMkdir(this.fdroidDirCaps);
            attemptToMkdir(this.repoDirCaps);
            symlinkEntireWebRootElsewhere("../", this.fdroidDirCaps);
            symlinkEntireWebRootElsewhere("../../", this.repoDirCaps);
        } catch (IOException e) {
            Log.e(TAG, "Error writing local repo index", e);
        }
    }

    private static void attemptToMkdir(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                return;
            }
            throw new IOException("Can't make directory " + file + " - it is already a file.");
        }
        if (file.mkdir()) {
            return;
        }
        throw new IOException("An error occurred trying to create directory " + file);
    }

    private static void attemptToDelete(File file) {
        if (file.delete()) {
            return;
        }
        Log.i(TAG, "Could not delete \"" + file.getAbsolutePath() + "\".");
    }

    private void symlinkEntireWebRootElsewhere(String str, File file) {
        symlinkFileElsewhere("index.html", str, file);
        for (String str2 : WEB_ROOT_ASSET_FILES) {
            symlinkFileElsewhere(str2, str, file);
        }
    }

    private void symlinkFileElsewhere(String str, String str2, File file) {
        SanitizedFile sanitizedFile = new SanitizedFile(file, str);
        attemptToDelete(sanitizedFile);
        Utils.symlinkOrCopyFileQuietly(new SanitizedFile(new File(file, str2), str), sanitizedFile);
    }

    private void deleteContents(File file) {
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    deleteContents(file2);
                } else {
                    attemptToDelete(file2);
                }
            }
        }
    }

    public File getIndexJar() {
        return this.indexJar;
    }

    public File getWebRoot() {
        return this.webRoot;
    }

    public void deleteRepo() {
        deleteContents(this.repoDir);
    }

    void generateIndex(String str, String str2, String[] strArr) throws IOException {
        cacheApps(new IndexV1Creator(this.context.getPackageManager(), this.repoDir, new HashSet(Arrays.asList(strArr)), new RepoV1(System.currentTimeMillis(), 20001, 7, Preferences.get().getLocalRepoName() + " on " + FDroidApp.ipAddressString, "swap-icon.png", str2, "A local FDroid repo generated from apps installed on " + Preferences.get().getLocalRepoName(), Collections.emptyList())).createRepo());
        writeIndexPage(str);
        writeIndexJar(new SanitizedFile(this.repoDir, IndexV1VerifierKt.DATA_FILE_NAME));
    }

    private void cacheApps(IndexV1 indexV1) {
        Long versionCode;
        this.apps.clear();
        for (AppV1 appV1 : indexV1.getApps()) {
            App app = new App();
            app.packageName = appV1.getPackageName();
            app.name = appV1.getName();
            app.installedApk = new Apk();
            List<PackageV1> list = indexV1.getPackages().get(appV1.getPackageName());
            if (list != null && list.size() > 0 && (versionCode = list.get(0).getVersionCode()) != null) {
                app.installedApk.versionCode = versionCode.longValue();
            }
            this.apps.add(app);
        }
    }

    private void writeIndexJar(SanitizedFile sanitizedFile) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.indexJarUnsigned));
        JarOutputStream jarOutputStream = new JarOutputStream(bufferedOutputStream);
        jarOutputStream.putNextEntry(new JarEntry(sanitizedFile.getName()));
        FileUtils.copyFile(sanitizedFile, jarOutputStream);
        jarOutputStream.close();
        bufferedOutputStream.close();
        try {
            try {
                LocalRepoKeyStore.get(this.context).signZip(this.indexJarUnsigned, this.indexJar);
            } catch (LocalRepoKeyStore.InitException unused) {
                throw new IOException("Could not sign index - keystore failed to initialize");
            }
        } finally {
            attemptToDelete(this.indexJarUnsigned);
        }
    }
}
