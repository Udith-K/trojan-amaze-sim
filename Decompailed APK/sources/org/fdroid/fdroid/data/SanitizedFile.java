package org.fdroid.fdroid.data;

import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class SanitizedFile extends File {
    public static String sanitizeFileName(String str) {
        return str.replaceAll("[^A-Za-z0-9-._ ]", "");
    }

    public SanitizedFile(File file, String str) {
        super(file, sanitizeFileName(str));
    }

    private SanitizedFile(File file) {
        super(file.getAbsolutePath());
    }

    public static SanitizedFile knownSanitized(String str) {
        return new SanitizedFile(new File(str));
    }

    public static SanitizedFile knownSanitized(File file) {
        return new SanitizedFile(file);
    }
}
