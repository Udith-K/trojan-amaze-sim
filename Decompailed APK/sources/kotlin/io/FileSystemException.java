package kotlin.io;

import java.io.File;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Exceptions.kt */
/* JADX INFO: loaded from: classes.dex */
public class FileSystemException extends IOException {
    private final File file;
    private final File other;
    private final String reason;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileSystemException(File file, File file2, String str) {
        super(ExceptionsKt.constructMessage(file, file2, str));
        Intrinsics.checkNotNullParameter(file, "file");
        this.file = file;
        this.other = file2;
        this.reason = str;
    }
}
