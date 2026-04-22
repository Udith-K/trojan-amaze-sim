package com.google.zxing.oned.rss.expanded.decoders;

/* JADX INFO: loaded from: classes.dex */
final class DecodedChar extends DecodedObject {
    private final char value;

    DecodedChar(int i, char c) {
        super(i);
        this.value = c;
    }

    char getValue() {
        return this.value;
    }

    boolean isFNC1() {
        return this.value == '$';
    }
}
