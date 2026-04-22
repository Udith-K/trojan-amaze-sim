package org.fdroid.fdroid.nearby;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class PublicSourceDirProvider extends ContentProvider {
    public static final String SHARE_APK_MIME_TYPE = "application/zip";
    public static final String TAG = "PublicSourceDirProvider";
    private static PackageManager pm;

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public static Uri getUri(Context context, String str) {
        return Uri.parse(String.format(Locale.ENGLISH, "content://%s.nearby.%s/%s.apk", context.getPackageName(), TAG, str));
    }

    public static Intent getApkShareIntent(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        Uri uri = getUri(context, str);
        intent.setType(SHARE_APK_MIME_TYPE);
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setFlags(1);
        return intent;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"_display_name", "mime_type", "_data", "_size"});
        try {
            ApplicationInfo applicationInfo = getApplicationInfo(uri);
            File file = new File(applicationInfo.publicSourceDir);
            matrixCursor.addRow(new Object[]{pm.getApplicationLabel(applicationInfo).toString().replace(" ", "") + ".apk", SHARE_APK_MIME_TYPE, Uri.parse("file://" + file.getCanonicalPath()), Long.valueOf(file.length())});
        } catch (PackageManager.NameNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return SHARE_APK_MIME_TYPE;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new IllegalStateException("unimplemented");
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        try {
            return ParcelFileDescriptor.open(new File(getApplicationInfo(uri).publicSourceDir), 268435456);
        } catch (PackageManager.NameNotFoundException | IOException e) {
            throw new FileNotFoundException(e.getLocalizedMessage());
        }
    }

    private ApplicationInfo getApplicationInfo(Uri uri) throws PackageManager.NameNotFoundException {
        if (pm == null) {
            pm = getContext().getPackageManager();
        }
        return pm.getApplicationInfo(uri.getLastPathSegment().substring(0, r3.length() - 4), 0);
    }
}
