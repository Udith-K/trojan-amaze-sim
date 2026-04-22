package org.fdroid.fdroid.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: FDroidSwitchRow.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$FDroidSwitchRowKt {
    public static final ComposableSingletons$FDroidSwitchRowKt INSTANCE = new ComposableSingletons$FDroidSwitchRowKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2 f37lambda1 = ComposableLambdaKt.composableLambdaInstance(236050358, false, new Function2() { // from class: org.fdroid.fdroid.compose.ComposableSingletons$FDroidSwitchRowKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(236050358, i, -1, "org.fdroid.fdroid.compose.ComposableSingletons$FDroidSwitchRowKt.lambda-1.<anonymous> (FDroidSwitchRow.kt:61)");
                }
                FDroidSwitchRowKt.m2972FDroidSwitchRowHYR8e34("Important setting", null, true, null, false, 0.0f, composer, 390, 58);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_fullRelease, reason: not valid java name */
    public final Function2 m2964getLambda1$app_fullRelease() {
        return f37lambda1;
    }
}
