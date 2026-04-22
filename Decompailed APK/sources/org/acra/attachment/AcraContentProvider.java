package org.acra.attachment;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.acra.ACRA;
import org.acra.file.Directory;

/* JADX INFO: compiled from: AcraContentProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0001 B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016JK\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001a\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J/\u0010\u0018\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\rH\u0016¢\u0006\u0002\u0010\u001aJ9\u0010\u001b\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\rH\u0016¢\u0006\u0002\u0010\u001cJ\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u0005H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/acra/attachment/AcraContentProvider;", "Landroid/content/ContentProvider;", "<init>", "()V", "authority", "", "onCreate", "", "query", "Landroid/database/Cursor;", "uri", "Landroid/net/Uri;", "projection", "", "selection", "selectionArgs", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "getFileForUri", "Ljava/io/File;", "getType", "insert", "values", "Landroid/content/ContentValues;", "delete", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "openFile", "Landroid/os/ParcelFileDescriptor;", "mode", "Companion", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AcraContentProvider extends ContentProvider {
    private String authority;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] COLUMNS = {"_display_name", "_size"};

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Query: " + uri);
        }
        File fileForUri = getFileForUri(uri);
        if (fileForUri == null) {
            return null;
        }
        if (projection == null) {
            projection = COLUMNS;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = ArrayIteratorKt.iterator(projection);
        while (it.hasNext()) {
            String str = (String) it.next();
            if (Intrinsics.areEqual(str, "_display_name")) {
                linkedHashMap.put("_display_name", fileForUri.getName());
            } else if (Intrinsics.areEqual(str, "_size")) {
                linkedHashMap.put("_size", Long.valueOf(fileForUri.length()));
            }
        }
        MatrixCursor matrixCursor = new MatrixCursor((String[]) linkedHashMap.keySet().toArray(new String[0]), 1);
        matrixCursor.addRow(linkedHashMap.values());
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Companion companion = INSTANCE;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String authority = companion.getAuthority(context);
        this.authority = authority;
        if (!ACRA.DEV_LOGGING) {
            return true;
        }
        ACRA.log.d(ACRA.LOG_TAG, "Registered content provider for authority " + authority);
        return true;
    }

    private final File getFileForUri(Uri uri) {
        if (!Intrinsics.areEqual("content", uri.getScheme()) || !Intrinsics.areEqual(this.authority, uri.getAuthority())) {
            return null;
        }
        List<String> pathSegments = uri.getPathSegments();
        Intrinsics.checkNotNullExpressionValue(pathSegments, "getPathSegments(...)");
        List mutableList = CollectionsKt.toMutableList((Collection) pathSegments);
        if (mutableList.size() < 2) {
            return null;
        }
        Object objRemove = mutableList.remove(0);
        Intrinsics.checkNotNullExpressionValue(objRemove, "removeAt(...)");
        String upperCase = ((String) objRemove).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        try {
            Directory directoryValueOf = Directory.valueOf(upperCase);
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            String strJoin = TextUtils.join(File.separator, mutableList);
            Intrinsics.checkNotNullExpressionValue(strJoin, "join(...)");
            return directoryValueOf.getFile(context, strJoin);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return INSTANCE.guessMimeType(uri);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        throw new UnsupportedOperationException("No insert supported");
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        throw new UnsupportedOperationException("No delete supported");
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        throw new UnsupportedOperationException("No update supported");
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(mode, "mode");
        File fileForUri = getFileForUri(uri);
        if (fileForUri != null) {
            if (!fileForUri.exists()) {
                fileForUri = null;
            }
            if (fileForUri != null) {
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, getCallingPackage() + " opened " + fileForUri.getPath());
                }
                ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(fileForUri, 268435456);
                Intrinsics.checkNotNullExpressionValue(parcelFileDescriptorOpen, "open(...)");
                return parcelFileDescriptorOpen;
            }
        }
        throw new FileNotFoundException("File represented by uri " + uri + " could not be found");
    }

    /* JADX INFO: compiled from: AcraContentProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getAuthority(Context context) {
            return context.getPackageName() + ".acra";
        }

        public final Uri getUriForFile(Context context, File file) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(file, "file");
            Directory directory = Directory.ROOT;
            String path = file.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            return getUriForFile(context, directory, path);
        }

        public final Uri getUriForFile(Context context, Directory directory, String relativePath) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(directory, "directory");
            Intrinsics.checkNotNullParameter(relativePath, "relativePath");
            Uri.Builder builderAuthority = new Uri.Builder().scheme("content").authority(getAuthority(context));
            String lowerCase = directory.name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            Uri.Builder builderAppendPath = builderAuthority.appendPath(lowerCase);
            List listSplit$default = StringsKt.split$default((CharSequence) relativePath, new String[]{Pattern.quote(File.separator)}, false, 0, 6, (Object) null);
            for (String str : (String[]) listSplit$default.toArray(new String[0])) {
                if (str.length() > 0) {
                    builderAppendPath.appendPath(str);
                }
            }
            Uri uriBuild = builderAppendPath.build();
            Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
            return uriBuild;
        }

        public final String guessMimeType(Uri uri) {
            String mimeTypeFromExtension;
            Intrinsics.checkNotNullParameter(uri, "uri");
            String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            if (fileExtensionFromUrl != null) {
                MimeTypeMap singleton = MimeTypeMap.getSingleton();
                String lowerCase = fileExtensionFromUrl.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                mimeTypeFromExtension = singleton.getMimeTypeFromExtension(lowerCase);
                if (mimeTypeFromExtension == null && Intrinsics.areEqual("json", fileExtensionFromUrl)) {
                    mimeTypeFromExtension = "application/json";
                }
            } else {
                mimeTypeFromExtension = null;
            }
            return mimeTypeFromExtension == null ? "application/octet-stream" : mimeTypeFromExtension;
        }
    }
}
