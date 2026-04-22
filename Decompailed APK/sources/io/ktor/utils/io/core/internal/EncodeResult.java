package io.ktor.utils.io.core.internal;

import kotlin.UShort;

/* JADX INFO: compiled from: EncodeResult.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class EncodeResult {
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2627constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2628constructorimpl(short s, short s2) {
        return m2627constructorimpl(((s & 65535) << 16) | (s2 & 65535));
    }

    /* JADX INFO: renamed from: getCharacters-Mh2AYeg, reason: not valid java name */
    public static final short m2630getCharactersMh2AYeg(int i) {
        return UShort.m2716constructorimpl((short) (i >>> 16));
    }

    /* JADX INFO: renamed from: getBytes-Mh2AYeg, reason: not valid java name */
    public static final short m2629getBytesMh2AYeg(int i) {
        return UShort.m2716constructorimpl((short) (i & 65535));
    }

    /* JADX INFO: renamed from: component1-Mh2AYeg, reason: not valid java name */
    public static final short m2625component1Mh2AYeg(int i) {
        return m2630getCharactersMh2AYeg(i);
    }

    /* JADX INFO: renamed from: component2-Mh2AYeg, reason: not valid java name */
    public static final short m2626component2Mh2AYeg(int i) {
        return m2629getBytesMh2AYeg(i);
    }
}
