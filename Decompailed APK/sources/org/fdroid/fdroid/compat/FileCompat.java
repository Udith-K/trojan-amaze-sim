package org.fdroid.fdroid.compat;

import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import java.io.IOException;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.SanitizedFile;

/* JADX INFO: loaded from: classes2.dex */
public class FileCompat {
    private static final String TAG = "FileCompat";

    public static boolean symlink(SanitizedFile sanitizedFile, SanitizedFile sanitizedFile2) {
        symlinkOs(sanitizedFile, sanitizedFile2);
        return sanitizedFile2.exists();
    }

    private static class Symlink21 {
        private Symlink21() {
        }

        void symlink(SanitizedFile sanitizedFile, SanitizedFile sanitizedFile2) {
            try {
                Os.symlink(sanitizedFile.getAbsolutePath(), sanitizedFile2.getAbsolutePath());
            } catch (ErrnoException unused) {
            }
        }
    }

    static void symlinkOs(SanitizedFile sanitizedFile, SanitizedFile sanitizedFile2) {
        new Symlink21().symlink(sanitizedFile, sanitizedFile2);
    }

    static void symlinkRuntime(SanitizedFile sanitizedFile, SanitizedFile sanitizedFile2) {
        String[] strArr = {FDroidApp.SYSTEM_DIR_NAME + "/bin/ln", "-s", sanitizedFile.getAbsolutePath(), sanitizedFile2.getAbsolutePath()};
        try {
            Utils.debugLog(TAG, "Executing command: " + strArr[0] + " " + strArr[1] + " " + strArr[2] + " " + strArr[3]);
            Process processExec = Runtime.getRuntime().exec(strArr);
            Utils.consumeStream(processExec.getInputStream());
            Utils.consumeStream(processExec.getErrorStream());
        } catch (IOException unused) {
        }
    }

    static void symlinkLibcore(SanitizedFile sanitizedFile, SanitizedFile sanitizedFile2) {
        try {
            Object obj = Class.forName("libcore.io.Libcore").getField("os").get(null);
            obj.getClass().getMethod("symlink", String.class, String.class).invoke(obj, sanitizedFile.getAbsolutePath(), sanitizedFile2.getAbsolutePath());
        } catch (Exception e) {
            Log.e(TAG, "Could not symlink " + sanitizedFile.getAbsolutePath() + " to " + sanitizedFile2.getAbsolutePath(), e);
        }
    }
}
