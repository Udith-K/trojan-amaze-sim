package org.acra.file;

import android.content.Context;
import android.os.Environment;
import ch.qos.logback.core.CoreConstants;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: Directory.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H&j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\u0012"}, d2 = {"Lorg/acra/file/Directory;", "", "<init>", "(Ljava/lang/String;I)V", "FILES_LEGACY", "FILES", "EXTERNAL_FILES", "CACHE", "EXTERNAL_CACHE", "NO_BACKUP_FILES", "EXTERNAL_STORAGE", "ROOT", "getFile", "Ljava/io/File;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "fileName", "", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class Directory {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ Directory[] $VALUES;
    public static final Directory FILES_LEGACY = new Directory("FILES_LEGACY", 0) { // from class: org.acra.file.Directory.FILES_LEGACY
        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // org.acra.file.Directory
        public File getFile(Context context, String fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            return (StringsKt.startsWith$default(fileName, "/", false, 2, (Object) null) ? Directory.ROOT : Directory.FILES).getFile(context, fileName);
        }
    };
    public static final Directory FILES = new Directory("FILES", 1) { // from class: org.acra.file.Directory.FILES
        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // org.acra.file.Directory
        public File getFile(Context context, String fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            return new File(context.getFilesDir(), fileName);
        }
    };
    public static final Directory EXTERNAL_FILES = new Directory("EXTERNAL_FILES", 2) { // from class: org.acra.file.Directory.EXTERNAL_FILES
        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // org.acra.file.Directory
        public File getFile(Context context, String fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            return new File(context.getExternalFilesDir(null), fileName);
        }
    };
    public static final Directory CACHE = new Directory("CACHE", 3) { // from class: org.acra.file.Directory.CACHE
        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // org.acra.file.Directory
        public File getFile(Context context, String fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            return new File(context.getCacheDir(), fileName);
        }
    };
    public static final Directory EXTERNAL_CACHE = new Directory("EXTERNAL_CACHE", 4) { // from class: org.acra.file.Directory.EXTERNAL_CACHE
        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // org.acra.file.Directory
        public File getFile(Context context, String fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            return new File(context.getExternalCacheDir(), fileName);
        }
    };
    public static final Directory NO_BACKUP_FILES = new Directory("NO_BACKUP_FILES", 5) { // from class: org.acra.file.Directory.NO_BACKUP_FILES
        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // org.acra.file.Directory
        public File getFile(Context context, String fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            File noBackupFilesDir = context.getNoBackupFilesDir();
            Intrinsics.checkNotNull(noBackupFilesDir);
            return new File(noBackupFilesDir, fileName);
        }
    };
    public static final Directory EXTERNAL_STORAGE = new Directory("EXTERNAL_STORAGE", 6) { // from class: org.acra.file.Directory.EXTERNAL_STORAGE
        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // org.acra.file.Directory
        public File getFile(Context context, String fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            return new File(Environment.getExternalStorageDirectory(), fileName);
        }
    };
    public static final Directory ROOT = new Directory("ROOT", 7) { // from class: org.acra.file.Directory.ROOT
        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // org.acra.file.Directory
        public File getFile(Context context, String fileName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            List listSplit$default = StringsKt.split$default((CharSequence) fileName, new String[]{File.separator}, false, 2, 2, (Object) null);
            if (listSplit$default.size() == 1) {
                return new File(fileName);
            }
            File[] fileArrListRoots = File.listRoots();
            Iterator it = ArrayIteratorKt.iterator(fileArrListRoots);
            while (it.hasNext()) {
                File file = (File) it.next();
                Object obj = listSplit$default.get(0);
                String path = file.getPath();
                Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
                String separator = File.separator;
                Intrinsics.checkNotNullExpressionValue(separator, "separator");
                if (Intrinsics.areEqual(obj, StringsKt.replace$default(path, separator, "", false, 4, (Object) null))) {
                    return new File(file, (String) listSplit$default.get(1));
                }
            }
            return new File(fileArrListRoots[0], fileName);
        }
    };

    private static final /* synthetic */ Directory[] $values() {
        return new Directory[]{FILES_LEGACY, FILES, EXTERNAL_FILES, CACHE, EXTERNAL_CACHE, NO_BACKUP_FILES, EXTERNAL_STORAGE, ROOT};
    }

    public /* synthetic */ Directory(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public abstract File getFile(Context context, String fileName);

    private Directory(String str, int i) {
    }

    static {
        Directory[] directoryArr$values = $values();
        $VALUES = directoryArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(directoryArr$values);
    }

    public static Directory valueOf(String str) {
        return (Directory) Enum.valueOf(Directory.class, str);
    }

    public static Directory[] values() {
        return (Directory[]) $VALUES.clone();
    }
}
