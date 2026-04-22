package org.fdroid.index;

import java.io.File;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: compiled from: IndexUpdater.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lorg/fdroid/index/TempFileProvider;", "", "createTempFile", "Ljava/io/File;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface TempFileProvider {
    File createTempFile() throws IOException;
}
