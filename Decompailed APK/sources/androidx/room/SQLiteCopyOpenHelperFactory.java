package androidx.room;

import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;
import java.util.concurrent.Callable;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SQLiteCopyOpenHelperFactory.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SQLiteCopyOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    private final String mCopyFromAssetPath;
    private final File mCopyFromFile;
    private final Callable mCopyFromInputStream;
    private final SupportSQLiteOpenHelper.Factory mDelegate;

    public SQLiteCopyOpenHelperFactory(String str, File file, Callable callable, SupportSQLiteOpenHelper.Factory mDelegate) {
        Intrinsics.checkNotNullParameter(mDelegate, "mDelegate");
        this.mCopyFromAssetPath = str;
        this.mCopyFromFile = file;
        this.mCopyFromInputStream = callable;
        this.mDelegate = mDelegate;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Factory
    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new SQLiteCopyOpenHelper(configuration.context, this.mCopyFromAssetPath, this.mCopyFromFile, this.mCopyFromInputStream, configuration.callback.version, this.mDelegate.create(configuration));
    }
}
