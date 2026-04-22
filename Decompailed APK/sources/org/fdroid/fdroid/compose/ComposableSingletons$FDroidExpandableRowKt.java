package org.fdroid.fdroid.compose;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.LockKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: FDroidExpandableRow.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$FDroidExpandableRowKt {
    public static final ComposableSingletons$FDroidExpandableRowKt INSTANCE = new ComposableSingletons$FDroidExpandableRowKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2 f35lambda1 = ComposableLambdaKt.composableLambdaInstance(-909024616, false, new Function2() { // from class: org.fdroid.fdroid.compose.ComposableSingletons$FDroidExpandableRowKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-909024616, i, -1, "org.fdroid.fdroid.compose.ComposableSingletons$FDroidExpandableRowKt.lambda-1.<anonymous> (FDroidExpandableRow.kt:93)");
            }
            TextKt.m772Text4IGK_g("Some content here", null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2 f36lambda2 = ComposableLambdaKt.composableLambdaInstance(822494646, false, new Function2() { // from class: org.fdroid.fdroid.compose.ComposableSingletons$FDroidExpandableRowKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(822494646, i, -1, "org.fdroid.fdroid.compose.ComposableSingletons$FDroidExpandableRowKt.lambda-2.<anonymous> (FDroidExpandableRow.kt:88)");
                }
                FDroidExpandableRowKt.FDroidExpandableRow("Permissions", LockKt.getLock(Icons.INSTANCE.getDefault()), true, ComposableSingletons$FDroidExpandableRowKt.INSTANCE.m2962getLambda1$app_fullRelease(), composer, 3462, 0);
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
    public final Function2 m2962getLambda1$app_fullRelease() {
        return f35lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_fullRelease, reason: not valid java name */
    public final Function2 m2963getLambda2$app_fullRelease() {
        return f36lambda2;
    }
}
