package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Scaffold.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ComposableSingletons$ScaffoldKt {
    public static final ComposableSingletons$ScaffoldKt INSTANCE = new ComposableSingletons$ScaffoldKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2 f23lambda1 = ComposableLambdaKt.composableLambdaInstance(1519764251, false, new Function2() { // from class: androidx.compose.material3.ComposableSingletons$ScaffoldKt$lambda-1$1
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
                ComposerKt.traceEventStart(1519764251, i, -1, "androidx.compose.material3.ComposableSingletons$ScaffoldKt.lambda-1.<anonymous> (Scaffold.kt:85)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2 f24lambda2 = ComposableLambdaKt.composableLambdaInstance(836907051, false, new Function2() { // from class: androidx.compose.material3.ComposableSingletons$ScaffoldKt$lambda-2$1
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
                ComposerKt.traceEventStart(836907051, i, -1, "androidx.compose.material3.ComposableSingletons$ScaffoldKt.lambda-2.<anonymous> (Scaffold.kt:86)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function2 f25lambda3 = ComposableLambdaKt.composableLambdaInstance(-297736342, false, new Function2() { // from class: androidx.compose.material3.ComposableSingletons$ScaffoldKt$lambda-3$1
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
                ComposerKt.traceEventStart(-297736342, i, -1, "androidx.compose.material3.ComposableSingletons$ScaffoldKt.lambda-3.<anonymous> (Scaffold.kt:87)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function2 f26lambda4 = ComposableLambdaKt.composableLambdaInstance(1545895435, false, new Function2() { // from class: androidx.compose.material3.ComposableSingletons$ScaffoldKt$lambda-4$1
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
                ComposerKt.traceEventStart(1545895435, i, -1, "androidx.compose.material3.ComposableSingletons$ScaffoldKt.lambda-4.<anonymous> (Scaffold.kt:88)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$material3_release, reason: not valid java name */
    public final Function2 m665getLambda1$material3_release() {
        return f23lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$material3_release, reason: not valid java name */
    public final Function2 m666getLambda2$material3_release() {
        return f24lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$material3_release, reason: not valid java name */
    public final Function2 m667getLambda3$material3_release() {
        return f25lambda3;
    }

    /* JADX INFO: renamed from: getLambda-4$material3_release, reason: not valid java name */
    public final Function2 m668getLambda4$material3_release() {
        return f26lambda4;
    }
}
