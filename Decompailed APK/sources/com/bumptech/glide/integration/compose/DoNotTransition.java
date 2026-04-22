package com.bumptech.glide.integration.compose;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import com.bumptech.glide.integration.compose.Transition;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Transition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DoNotTransition implements Transition {
    public static final DoNotTransition INSTANCE = new DoNotTransition();
    private static final Function5 drawPlaceholder = new Function5() { // from class: com.bumptech.glide.integration.compose.DoNotTransition$drawPlaceholder$1
        /* JADX INFO: renamed from: invoke-QfoU1oo, reason: not valid java name */
        public final void m2575invokeQfoU1oo(DrawScope drawScope, Painter painter, long j, float f, ColorFilter colorFilter) {
            Intrinsics.checkNotNullParameter(drawScope, "$this$null");
            Intrinsics.checkNotNullParameter(painter, "<anonymous parameter 0>");
        }

        @Override // kotlin.jvm.functions.Function5
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            m2575invokeQfoU1oo((DrawScope) obj, (Painter) obj2, ((Size) obj3).m1200unboximpl(), ((Number) obj4).floatValue(), (ColorFilter) obj5);
            return Unit.INSTANCE;
        }
    };
    private static final Function5 drawCurrent = new Function5() { // from class: com.bumptech.glide.integration.compose.DoNotTransition$drawCurrent$1
        @Override // kotlin.jvm.functions.Function5
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            m2574invokeQfoU1oo((DrawScope) obj, (Painter) obj2, ((Size) obj3).m1200unboximpl(), ((Number) obj4).floatValue(), (ColorFilter) obj5);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke-QfoU1oo, reason: not valid java name */
        public final void m2574invokeQfoU1oo(DrawScope drawScope, Painter painter, long j, float f, ColorFilter colorFilter) {
            Intrinsics.checkNotNullParameter(drawScope, "$this$null");
            Intrinsics.checkNotNullParameter(painter, "painter");
            painter.m1549drawx_KDEd0(drawScope, j, f, colorFilter);
        }
    };

    /* JADX INFO: compiled from: Transition.kt */
    public static final class Factory implements Transition.Factory {
        public static final Factory INSTANCE = new Factory();

        private Factory() {
        }

        @Override // com.bumptech.glide.integration.compose.Transition.Factory
        public DoNotTransition build() {
            return DoNotTransition.INSTANCE;
        }
    }

    private DoNotTransition() {
    }

    @Override // com.bumptech.glide.integration.compose.Transition
    public Object transition(Function0 function0, Continuation continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.bumptech.glide.integration.compose.Transition
    public Object stop(Continuation continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.bumptech.glide.integration.compose.Transition
    public Function5 getDrawPlaceholder() {
        return drawPlaceholder;
    }

    @Override // com.bumptech.glide.integration.compose.Transition
    public Function5 getDrawCurrent() {
        return drawCurrent;
    }
}
