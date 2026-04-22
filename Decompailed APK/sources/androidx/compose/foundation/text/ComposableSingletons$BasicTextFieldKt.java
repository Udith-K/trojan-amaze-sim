package androidx.compose.foundation.text;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: BasicTextField.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ComposableSingletons$BasicTextFieldKt {
    public static final ComposableSingletons$BasicTextFieldKt INSTANCE = new ComposableSingletons$BasicTextFieldKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function3 f0lambda1 = ComposableLambdaKt.composableLambdaInstance(997835932, false, new Function3() { // from class: androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((Function2) obj, (Composer) obj2, ((Number) obj3).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Function2 function2, Composer composer, int i) {
            if ((i & 6) == 0) {
                i |= composer.changedInstance(function2) ? 4 : 2;
            }
            if ((i & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(997835932, i, -1, "androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt.lambda-1.<anonymous> (BasicTextField.kt:609)");
            }
            function2.invoke(composer, Integer.valueOf(i & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function3 f1lambda2 = ComposableLambdaKt.composableLambdaInstance(2105616367, false, new Function3() { // from class: androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((Function2) obj, (Composer) obj2, ((Number) obj3).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Function2 function2, Composer composer, int i) {
            if ((i & 6) == 0) {
                i |= composer.changedInstance(function2) ? 4 : 2;
            }
            if ((i & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2105616367, i, -1, "androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt.lambda-2.<anonymous> (BasicTextField.kt:761)");
            }
            function2.invoke(composer, Integer.valueOf(i & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function3 f2lambda3 = ComposableLambdaKt.composableLambdaInstance(434140383, false, new Function3() { // from class: androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((Function2) obj, (Composer) obj2, ((Number) obj3).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Function2 function2, Composer composer, int i) {
            if ((i & 6) == 0) {
                i |= composer.changedInstance(function2) ? 4 : 2;
            }
            if ((i & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(434140383, i, -1, "androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt.lambda-3.<anonymous> (BasicTextField.kt:805)");
            }
            function2.invoke(composer, Integer.valueOf(i & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function3 f3lambda4 = ComposableLambdaKt.composableLambdaInstance(-34833998, false, new Function3() { // from class: androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((Function2) obj, (Composer) obj2, ((Number) obj3).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Function2 function2, Composer composer, int i) {
            if ((i & 6) == 0) {
                i |= composer.changedInstance(function2) ? 4 : 2;
            }
            if ((i & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-34833998, i, -1, "androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt.lambda-4.<anonymous> (BasicTextField.kt:845)");
            }
            function2.invoke(composer, Integer.valueOf(i & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-2$foundation_release, reason: not valid java name */
    public final Function3 m376getLambda2$foundation_release() {
        return f1lambda2;
    }
}
