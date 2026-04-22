package androidx.compose.ui.input.key;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KeyEvent.kt */
/* JADX INFO: loaded from: classes.dex */
public final class KeyEvent {
    private final android.view.KeyEvent nativeKeyEvent;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ KeyEvent m1615boximpl(android.view.KeyEvent keyEvent) {
        return new KeyEvent(keyEvent);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static android.view.KeyEvent m1616constructorimpl(android.view.KeyEvent keyEvent) {
        return keyEvent;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1617equalsimpl(android.view.KeyEvent keyEvent, Object obj) {
        return (obj instanceof KeyEvent) && Intrinsics.areEqual(keyEvent, ((KeyEvent) obj).m1620unboximpl());
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1618hashCodeimpl(android.view.KeyEvent keyEvent) {
        return keyEvent.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1619toStringimpl(android.view.KeyEvent keyEvent) {
        return "KeyEvent(nativeKeyEvent=" + keyEvent + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public boolean equals(Object obj) {
        return m1617equalsimpl(this.nativeKeyEvent, obj);
    }

    public int hashCode() {
        return m1618hashCodeimpl(this.nativeKeyEvent);
    }

    public String toString() {
        return m1619toStringimpl(this.nativeKeyEvent);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ android.view.KeyEvent m1620unboximpl() {
        return this.nativeKeyEvent;
    }

    private /* synthetic */ KeyEvent(android.view.KeyEvent keyEvent) {
        this.nativeKeyEvent = keyEvent;
    }
}
