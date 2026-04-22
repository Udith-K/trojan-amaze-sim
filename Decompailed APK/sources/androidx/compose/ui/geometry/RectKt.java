package androidx.compose.ui.geometry;

/* JADX INFO: compiled from: Rect.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RectKt {
    /* JADX INFO: renamed from: Rect-tz77jQw, reason: not valid java name */
    public static final Rect m1183Recttz77jQw(long j, long j2) {
        return new Rect(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j), Offset.m1159getXimpl(j) + Size.m1196getWidthimpl(j2), Offset.m1160getYimpl(j) + Size.m1194getHeightimpl(j2));
    }

    /* JADX INFO: renamed from: Rect-0a9Yr6o, reason: not valid java name */
    public static final Rect m1182Rect0a9Yr6o(long j, long j2) {
        return new Rect(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j), Offset.m1159getXimpl(j2), Offset.m1160getYimpl(j2));
    }
}
