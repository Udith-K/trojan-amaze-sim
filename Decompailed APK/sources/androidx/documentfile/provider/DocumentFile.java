package androidx.documentfile.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;

/* JADX INFO: loaded from: classes.dex */
public abstract class DocumentFile {
    private final DocumentFile mParent;

    public abstract String getName();

    public abstract Uri getUri();

    public abstract boolean isDirectory();

    public abstract long length();

    public abstract DocumentFile[] listFiles();

    DocumentFile(DocumentFile documentFile) {
        this.mParent = documentFile;
    }

    public static DocumentFile fromSingleUri(Context context, Uri uri) {
        return new SingleDocumentFile(null, context, uri);
    }

    public static DocumentFile fromTreeUri(Context context, Uri uri) {
        return new TreeDocumentFile(null, context, DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri)));
    }

    public DocumentFile getParentFile() {
        return this.mParent;
    }
}
