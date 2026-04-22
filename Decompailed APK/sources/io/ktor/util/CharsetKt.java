package io.ktor.util;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Charset.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CharsetKt {
    public static final boolean isLowerCase(char c) {
        return Character.toLowerCase(c) == c;
    }

    public static final char[] toCharArray(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = str.charAt(i);
        }
        return cArr;
    }
}
