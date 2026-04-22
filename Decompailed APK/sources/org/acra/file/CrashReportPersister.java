package org.acra.file;

import java.io.File;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.acra.data.CrashReportData;

/* JADX INFO: compiled from: CrashReportPersister.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CrashReportPersister {
    public final CrashReportData load(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return new CrashReportData(FilesKt.readText$default(file, null, 1, null));
    }

    public final void store(CrashReportData crashData, File file) {
        Intrinsics.checkNotNullParameter(crashData, "crashData");
        Intrinsics.checkNotNullParameter(file, "file");
        FilesKt.writeText$default(file, crashData.toJSON(), null, 2, null);
    }
}
