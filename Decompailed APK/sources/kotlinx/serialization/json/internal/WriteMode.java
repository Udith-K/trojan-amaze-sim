package kotlinx.serialization.json.internal;

import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: WriteMode.kt */
/* JADX INFO: loaded from: classes2.dex */
public enum WriteMode {
    OBJ(CoreConstants.CURLY_LEFT, CoreConstants.CURLY_RIGHT),
    LIST('[', ']'),
    MAP(CoreConstants.CURLY_LEFT, CoreConstants.CURLY_RIGHT),
    POLY_OBJ('[', ']');

    public final char begin;
    public final char end;

    WriteMode(char c, char c2) {
        this.begin = c;
        this.end = c2;
    }
}
