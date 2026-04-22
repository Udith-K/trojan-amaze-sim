package androidx.documentfile.provider;

import android.content.Context;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
class SingleDocumentFile extends DocumentFile {
    private Context mContext;
    private Uri mUri;

    SingleDocumentFile(DocumentFile documentFile, Context context, Uri uri) {
        super(documentFile);
        this.mContext = context;
        this.mUri = uri;
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public Uri getUri() {
        return this.mUri;
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public String getName() {
        return DocumentsContractApi19.getName(this.mContext, this.mUri);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean isDirectory() {
        return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public long length() {
        return DocumentsContractApi19.length(this.mContext, this.mUri);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public DocumentFile[] listFiles() {
        throw new UnsupportedOperationException();
    }
}
