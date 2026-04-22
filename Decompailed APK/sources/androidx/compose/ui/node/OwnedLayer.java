package androidx.compose.ui.node;

import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: OwnedLayer.kt */
/* JADX INFO: loaded from: classes.dex */
public interface OwnedLayer {
    void destroy();

    void drawLayer(Canvas canvas, GraphicsLayer graphicsLayer);

    void invalidate();

    /* JADX INFO: renamed from: inverseTransform-58bKbWc, reason: not valid java name */
    void mo1897inverseTransform58bKbWc(float[] fArr);

    /* JADX INFO: renamed from: isInLayer-k-4lQ0M, reason: not valid java name */
    boolean mo1898isInLayerk4lQ0M(long j);

    void mapBounds(MutableRect mutableRect, boolean z);

    /* JADX INFO: renamed from: mapOffset-8S9VItk, reason: not valid java name */
    long mo1899mapOffset8S9VItk(long j, boolean z);

    /* JADX INFO: renamed from: move--gyyYBs, reason: not valid java name */
    void mo1900movegyyYBs(long j);

    /* JADX INFO: renamed from: resize-ozmzZPI, reason: not valid java name */
    void mo1901resizeozmzZPI(long j);

    void reuseLayer(Function2 function2, Function0 function0);

    /* JADX INFO: renamed from: transform-58bKbWc, reason: not valid java name */
    void mo1902transform58bKbWc(float[] fArr);

    void updateDisplayList();

    void updateLayerProperties(ReusableGraphicsLayerScope reusableGraphicsLayerScope);
}
