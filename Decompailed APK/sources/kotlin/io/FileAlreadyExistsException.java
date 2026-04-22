package kotlin.io;

import java.io.File;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Exceptions.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FileAlreadyExistsException extends FileSystemException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileAlreadyExistsException(File file, File file2, String str) {
        super(file, file2, str);
        Intrinsics.checkNotNullParameter(file, "file");
    }
}
