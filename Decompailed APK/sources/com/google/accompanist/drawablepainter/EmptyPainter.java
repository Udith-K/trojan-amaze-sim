package com.google.accompanist.drawablepainter;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DrawablePainter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class EmptyPainter extends Painter {
    public static final EmptyPainter INSTANCE = new EmptyPainter();

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected void onDraw(DrawScope drawScope) {
        Intrinsics.checkNotNullParameter(drawScope, "<this>");
    }

    private EmptyPainter() {
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public long mo1548getIntrinsicSizeNHjbRc() {
        return Size.Companion.m1201getUnspecifiedNHjbRc();
    }
}
