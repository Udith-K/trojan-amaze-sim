package org.fdroid.fdroid.installer;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.net.DownloaderService;

/* JADX INFO: loaded from: classes2.dex */
public class ObfInstallerService extends IntentService {
    private static final String ACTION_INSTALL_OBF = "org.fdroid.fdroid.installer.action.INSTALL_OBF";
    private static final String EXTRA_OBF_PATH = "org.fdroid.fdroid.installer.extra.OBF_PATH";
    private static final String TAG = "ObfInstallerService";

    public ObfInstallerService() {
        super(TAG);
    }

    public static void install(Context context, Uri uri, App app, Apk apk, File file) {
        Intent intent = new Intent(context, (Class<?>) ObfInstallerService.class);
        intent.setAction(ACTION_INSTALL_OBF);
        intent.putExtra(DownloaderService.EXTRA_CANONICAL_URL, uri.toString());
        intent.putExtra(Installer.EXTRA_APP, app);
        intent.putExtra(Installer.EXTRA_APK, apk);
        intent.putExtra(EXTRA_OBF_PATH, file.getAbsolutePath());
        context.startService(intent);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null || !ACTION_INSTALL_OBF.equals(intent.getAction())) {
            Log.e(TAG, "received invalid intent: " + intent);
            return;
        }
        Uri uri = Uri.parse(intent.getStringExtra(DownloaderService.EXTRA_CANONICAL_URL));
        App app = (App) intent.getParcelableExtra(Installer.EXTRA_APP);
        Apk apk = (Apk) intent.getParcelableExtra(Installer.EXTRA_APK);
        String stringExtra = intent.getStringExtra(EXTRA_OBF_PATH);
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(stringExtra);
        if ("obf".equals(fileExtensionFromUrl)) {
            sendPostInstallAndCompleteIntents(uri, app, apk, new File(stringExtra));
            return;
        }
        if (!"zip".equals(fileExtensionFromUrl)) {
            sendBroadcastInstall(Installer.ACTION_INSTALL_INTERRUPTED, uri, app, apk, "Only .obf and .zip files are supported: " + stringExtra);
            return;
        }
        try {
            File file = new File(stringExtra);
            ZipFile zipFile = new ZipFile(file);
            if (zipFile.size() < 1) {
                sendBroadcastInstall(Installer.ACTION_INSTALL_INTERRUPTED, uri, app, apk, "Corrupt or empty ZIP file!");
            }
            ZipEntry zipEntryNextElement = zipFile.entries().nextElement();
            File file2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), zipEntryNextElement.getName());
            FileUtils.copyInputStreamToFile(zipFile.getInputStream(zipEntryNextElement), file2);
            file.delete();
            sendPostInstallAndCompleteIntents(uri, app, apk, file2);
        } catch (IOException e) {
            e.printStackTrace();
            sendBroadcastInstall(Installer.ACTION_INSTALL_INTERRUPTED, uri, app, apk, e.getMessage());
        }
    }

    private void sendBroadcastInstall(String str, Uri uri, App app, Apk apk, String str2) {
        Installer.sendBroadcastInstall(this, uri, str, app, apk, null, str2);
    }

    void sendPostInstallAndCompleteIntents(Uri uri, App app, Apk apk, File file) {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                StrictMode.class.getMethod("disableDeathOnFileUriExposure", null).invoke(null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(335544320);
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension("obf");
        if (TextUtils.isEmpty(mimeTypeFromExtension)) {
            mimeTypeFromExtension = "application/octet-stream";
        }
        if (Build.VERSION.SDK_INT < 24) {
            intent.setDataAndType(Uri.fromFile(file), mimeTypeFromExtension);
        } else {
            intent.setDataAndType(FileProvider.getUriForFile(this, Installer.AUTHORITY, file), mimeTypeFromExtension);
        }
        intent.setFlags(65);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            sendBroadcastInstall(Installer.ACTION_INSTALL_COMPLETE, uri, app, apk, null);
            return;
        }
        Log.i(TAG, "No AppCompatActivity available to handle " + intent);
        sendBroadcastInstall(Installer.ACTION_INSTALL_INTERRUPTED, uri, app, apk, null);
    }
}
