package org.acra.util;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;

/* JADX INFO: compiled from: Installation.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class Installation {
    public static final Installation INSTANCE = new Installation();

    private Installation() {
    }

    public static final synchronized String id(Context context) {
        String text$default;
        try {
            Intrinsics.checkNotNullParameter(context, "context");
            File file = new File(context.getFilesDir(), "ACRA-INSTALLATION");
            try {
                if (!file.exists()) {
                    String string = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                    FilesKt.writeText$default(file, string, null, 2, null);
                }
                text$default = FilesKt.readText$default(file, null, 1, null);
            } catch (IOException e) {
                ACRA.log.w(ACRA.LOG_TAG, "Couldn't retrieve InstallationId for " + context.getPackageName(), e);
                text$default = "Couldn't retrieve InstallationId";
            } catch (RuntimeException e2) {
                ACRA.log.w(ACRA.LOG_TAG, "Couldn't retrieve InstallationId for " + context.getPackageName(), e2);
                text$default = "Couldn't retrieve InstallationId";
            }
        } catch (Throwable th) {
            throw th;
        }
        return text$default;
    }
}
