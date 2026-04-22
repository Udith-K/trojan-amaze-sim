package androidx.compose.foundation.layout;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Intrinsic.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class IntrinsicKt {
    public static final Modifier width(Modifier modifier, final IntrinsicSize intrinsicSize) {
        return modifier.then(new IntrinsicWidthElement(intrinsicSize, true, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1() { // from class: androidx.compose.foundation.layout.IntrinsicKt$width$$inlined$debugInspectorInfo$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                invoke((InspectorInfo) null);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                throw null;
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }
}
