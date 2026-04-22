package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter;
import androidx.compose.ui.graphics.Matrix;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LegacyPlatformTextInputServiceAdapter.android.kt */
/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$request$1 extends FunctionReferenceImpl implements Function1 {
    final /* synthetic */ LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode $node;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$request$1(LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode legacyPlatformTextInputNode) {
        super(1, Intrinsics.Kotlin.class, "localToScreen", "startInput$localToScreen(Landroidx/compose/foundation/text/input/internal/LegacyPlatformTextInputServiceAdapter$LegacyPlatformTextInputNode;[F)V", 0);
        this.$node = legacyPlatformTextInputNode;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m473invoke58bKbWc(((Matrix) obj).m1371unboximpl());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke-58bKbWc, reason: not valid java name */
    public final void m473invoke58bKbWc(float[] fArr) {
        AndroidLegacyPlatformTextInputServiceAdapter.startInput$localToScreen(this.$node, fArr);
    }
}
