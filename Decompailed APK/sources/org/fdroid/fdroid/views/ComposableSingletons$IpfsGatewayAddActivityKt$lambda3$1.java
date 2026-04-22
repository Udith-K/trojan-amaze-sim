package org.fdroid.fdroid.views;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewayAddActivityKt$lambda-3$1, reason: invalid class name */
/* JADX INFO: compiled from: IpfsGatewayAddActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class ComposableSingletons$IpfsGatewayAddActivityKt$lambda3$1 implements Function2 {
    public static final ComposableSingletons$IpfsGatewayAddActivityKt$lambda3$1 INSTANCE = new ComposableSingletons$IpfsGatewayAddActivityKt$lambda3$1();

    ComposableSingletons$IpfsGatewayAddActivityKt$lambda3$1() {
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if ((i & 3) != 2 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-879463895, i, -1, "org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewayAddActivityKt.lambda-3.<anonymous> (IpfsGatewayAddActivity.kt:181)");
            }
            composer.startReplaceGroup(560810414);
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewayAddActivityKt$lambda-3$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function0 function0 = (Function0) objRememberedValue;
            composer.endReplaceGroup();
            composer.startReplaceGroup(560811534);
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewayAddActivityKt$lambda-3$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ComposableSingletons$IpfsGatewayAddActivityKt$lambda3$1.invoke$lambda$3$lambda$2((String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceGroup();
            IpfsGatewayAddActivityKt.IpfsGatewayAddScreen(function0, (Function1) objRememberedValue2, composer, 54);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$3$lambda$2(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }
}
