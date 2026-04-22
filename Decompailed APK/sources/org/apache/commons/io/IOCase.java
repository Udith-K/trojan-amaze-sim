package org.apache.commons.io;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public enum IOCase implements Serializable {
    SENSITIVE("Sensitive", true),
    INSENSITIVE("Insensitive", false),
    SYSTEM("System", !FilenameUtils.isSystemWindows());

    private final String name;
    private final transient boolean sensitive;

    IOCase(String str, boolean z) {
        this.name = str;
        this.sensitive = z;
    }

    public int checkIndexOf(String str, int i, String str2) {
        int length = str.length() - str2.length();
        if (length < i) {
            return -1;
        }
        while (i <= length) {
            if (checkRegionMatches(str, i, str2)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean checkRegionMatches(String str, int i, String str2) {
        return str.regionMatches(!this.sensitive, i, str2, 0, str2.length());
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
