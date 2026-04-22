package org.fdroid.fdroid.views;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.Icons$AutoMirrored$Filled;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material.icons.filled.AddKt;
import androidx.compose.material.icons.filled.DeleteForeverKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.res.StringResources_androidKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.fdroid.fdroid.R;

/* JADX INFO: compiled from: IpfsGatewaySettingsActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$IpfsGatewaySettingsActivityKt {
    public static final ComposableSingletons$IpfsGatewaySettingsActivityKt INSTANCE = new ComposableSingletons$IpfsGatewaySettingsActivityKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2 f41lambda1 = ComposableLambdaKt.composableLambdaInstance(-902731222, false, new Function2() { // from class: org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewaySettingsActivityKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-902731222, i, -1, "org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewaySettingsActivityKt.lambda-1.<anonymous> (IpfsGatewaySettingsActivity.kt:85)");
                }
                TextKt.m772Text4IGK_g(StringResources_androidKt.stringResource(R.string.ipfsgw_title, composer, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2 f42lambda2 = ComposableLambdaKt.composableLambdaInstance(1015829803, false, new Function2() { // from class: org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewaySettingsActivityKt$lambda-2$1
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
                ComposerKt.traceEventStart(1015829803, i, -1, "org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewaySettingsActivityKt.lambda-2.<anonymous> (IpfsGatewaySettingsActivity.kt:81)");
            }
            IconKt.m691Iconww6aTOc(ArrowBackKt.getArrowBack(Icons$AutoMirrored$Filled.INSTANCE), StringResources_androidKt.stringResource(R.string.back, composer, 0), (Modifier) null, 0L, composer, 0, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function2 f43lambda3 = ComposableLambdaKt.composableLambdaInstance(695004174, false, new Function2() { // from class: org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewaySettingsActivityKt$lambda-3$1
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
                ComposerKt.traceEventStart(695004174, i, -1, "org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewaySettingsActivityKt.lambda-3.<anonymous> (IpfsGatewaySettingsActivity.kt:99)");
            }
            IconKt.m691Iconww6aTOc(AddKt.getAdd(Icons.Filled.INSTANCE), StringResources_androidKt.stringResource(R.string.ipfsgw_add_add, composer, 0), (Modifier) null, 0L, composer, 0, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function2 f44lambda4 = ComposableLambdaKt.composableLambdaInstance(319925507, false, new Function2() { // from class: org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewaySettingsActivityKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(319925507, i, -1, "org.fdroid.fdroid.views.ComposableSingletons$IpfsGatewaySettingsActivityKt.lambda-4.<anonymous> (IpfsGatewaySettingsActivity.kt:225)");
                }
                IconKt.m691Iconww6aTOc(DeleteForeverKt.getDeleteForever(Icons.INSTANCE.getDefault()), "Localized description", (Modifier) null, 0L, composer, 48, 12);
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
    public final Function2 m3115getLambda1$app_fullRelease() {
        return f41lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_fullRelease, reason: not valid java name */
    public final Function2 m3116getLambda2$app_fullRelease() {
        return f42lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_fullRelease, reason: not valid java name */
    public final Function2 m3117getLambda3$app_fullRelease() {
        return f43lambda3;
    }

    /* JADX INFO: renamed from: getLambda-4$app_fullRelease, reason: not valid java name */
    public final Function2 m3118getLambda4$app_fullRelease() {
        return f44lambda4;
    }
}
