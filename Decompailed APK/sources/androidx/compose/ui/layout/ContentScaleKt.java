package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Size;

/* JADX INFO: compiled from: ContentScale.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ContentScaleKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: computeFillMaxDimension-iLBOSCw, reason: not valid java name */
    public static final float m1740computeFillMaxDimensioniLBOSCw(long j, long j2) {
        return Math.max(m1742computeFillWidthiLBOSCw(j, j2), m1739computeFillHeightiLBOSCw(j, j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: computeFillMinDimension-iLBOSCw, reason: not valid java name */
    public static final float m1741computeFillMinDimensioniLBOSCw(long j, long j2) {
        return Math.min(m1742computeFillWidthiLBOSCw(j, j2), m1739computeFillHeightiLBOSCw(j, j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: computeFillWidth-iLBOSCw, reason: not valid java name */
    public static final float m1742computeFillWidthiLBOSCw(long j, long j2) {
        return Size.m1196getWidthimpl(j2) / Size.m1196getWidthimpl(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: computeFillHeight-iLBOSCw, reason: not valid java name */
    public static final float m1739computeFillHeightiLBOSCw(long j, long j2) {
        return Size.m1194getHeightimpl(j2) / Size.m1194getHeightimpl(j);
    }
}
