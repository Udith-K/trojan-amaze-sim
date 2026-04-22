package org.fdroid.fdroid.views;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.ComponentActivityKt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.ui.theme.ThemeKt;
import org.fdroid.fdroid.views.IpfsGatewaySettingsActivity;

/* JADX INFO: compiled from: IpfsGatewaySettingsActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\b"}, d2 = {"Lorg/fdroid/fdroid/views/IpfsGatewaySettingsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IpfsGatewaySettingsActivity extends AppCompatActivity {
    public static final int $stable = 0;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable$default(this, null, null, 3, null);
        super.onCreate(savedInstanceState);
        final Preferences preferences = Preferences.get();
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-935875152, true, new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivity.onCreate.1

            /* JADX INFO: renamed from: org.fdroid.fdroid.views.IpfsGatewaySettingsActivity$onCreate$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: IpfsGatewaySettingsActivity.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            static final class C00651 implements Function2 {
                final /* synthetic */ Preferences $prefs;
                final /* synthetic */ IpfsGatewaySettingsActivity this$0;

                C00651(Preferences preferences, IpfsGatewaySettingsActivity ipfsGatewaySettingsActivity) {
                    this.$prefs = preferences;
                    this.this$0 = ipfsGatewaySettingsActivity;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    if ((i & 3) != 2 || !composer.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-758562694, i, -1, "org.fdroid.fdroid.views.IpfsGatewaySettingsActivity.onCreate.<anonymous>.<anonymous> (IpfsGatewaySettingsActivity.kt:58)");
                        }
                        Preferences preferences = this.$prefs;
                        Intrinsics.checkNotNull(preferences);
                        composer.startReplaceGroup(1018251962);
                        boolean zChanged = composer.changed(this.this$0);
                        final IpfsGatewaySettingsActivity ipfsGatewaySettingsActivity = this.this$0;
                        Object objRememberedValue = composer.rememberedValue();
                        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                            objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivity$onCreate$1$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return IpfsGatewaySettingsActivity.AnonymousClass1.C00651.invoke$lambda$1$lambda$0(ipfsGatewaySettingsActivity);
                                }
                            };
                            composer.updateRememberedValue(objRememberedValue);
                        }
                        composer.endReplaceGroup();
                        IpfsGatewaySettingsActivityKt.IpfsGatewaySettingsScreen((Function0) objRememberedValue, preferences, composer, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer.skipToGroupEnd();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$1$lambda$0(IpfsGatewaySettingsActivity ipfsGatewaySettingsActivity) {
                    ipfsGatewaySettingsActivity.getOnBackPressedDispatcher().onBackPressed();
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i) {
                if ((i & 3) != 2 || !composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-935875152, i, -1, "org.fdroid.fdroid.views.IpfsGatewaySettingsActivity.onCreate.<anonymous> (IpfsGatewaySettingsActivity.kt:57)");
                    }
                    ThemeKt.FDroidContent(false, false, ComposableLambdaKt.rememberComposableLambda(-758562694, true, new C00651(preferences, this), composer, 54), composer, 384, 3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer.skipToGroupEnd();
            }
        }), 1, null);
    }
}
