package org.fdroid.fdroid.views;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.ComponentActivityKt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.ui.theme.ThemeKt;
import org.fdroid.fdroid.views.IpfsGatewayAddActivity;

/* JADX INFO: compiled from: IpfsGatewayAddActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\b"}, d2 = {"Lorg/fdroid/fdroid/views/IpfsGatewayAddActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IpfsGatewayAddActivity extends AppCompatActivity {
    public static final int $stable = 0;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable$default(this, null, null, 3, null);
        super.onCreate(savedInstanceState);
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(212474564, true, new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewayAddActivity.onCreate.1

            /* JADX INFO: renamed from: org.fdroid.fdroid.views.IpfsGatewayAddActivity$onCreate$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: IpfsGatewayAddActivity.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            static final class C00631 implements Function2 {
                final /* synthetic */ IpfsGatewayAddActivity this$0;

                C00631(IpfsGatewayAddActivity ipfsGatewayAddActivity) {
                    this.this$0 = ipfsGatewayAddActivity;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    if ((i & 3) != 2 || !composer.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(84197902, i, -1, "org.fdroid.fdroid.views.IpfsGatewayAddActivity.onCreate.<anonymous>.<anonymous> (IpfsGatewayAddActivity.kt:53)");
                        }
                        composer.startReplaceGroup(-1226174264);
                        boolean zChanged = composer.changed(this.this$0);
                        final IpfsGatewayAddActivity ipfsGatewayAddActivity = this.this$0;
                        Object objRememberedValue = composer.rememberedValue();
                        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                            objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.IpfsGatewayAddActivity$onCreate$1$1$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return IpfsGatewayAddActivity.AnonymousClass1.C00631.invoke$lambda$1$lambda$0(ipfsGatewayAddActivity);
                                }
                            };
                            composer.updateRememberedValue(objRememberedValue);
                        }
                        Function0 function0 = (Function0) objRememberedValue;
                        composer.endReplaceGroup();
                        composer.startReplaceGroup(-1226170952);
                        boolean zChanged2 = composer.changed(this.this$0);
                        final IpfsGatewayAddActivity ipfsGatewayAddActivity2 = this.this$0;
                        Object objRememberedValue2 = composer.rememberedValue();
                        if (zChanged2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: org.fdroid.fdroid.views.IpfsGatewayAddActivity$onCreate$1$1$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return IpfsGatewayAddActivity.AnonymousClass1.C00631.invoke$lambda$3$lambda$2(ipfsGatewayAddActivity2, (String) obj);
                                }
                            };
                            composer.updateRememberedValue(objRememberedValue2);
                        }
                        composer.endReplaceGroup();
                        IpfsGatewayAddActivityKt.IpfsGatewayAddScreen(function0, (Function1) objRememberedValue2, composer, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer.skipToGroupEnd();
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$1$lambda$0(IpfsGatewayAddActivity ipfsGatewayAddActivity) {
                    ipfsGatewayAddActivity.getOnBackPressedDispatcher().onBackPressed();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final Unit invoke$lambda$3$lambda$2(IpfsGatewayAddActivity ipfsGatewayAddActivity, String url) {
                    Intrinsics.checkNotNullParameter(url, "url");
                    if (!Preferences.DEFAULT_IPFS_GATEWAYS.contains(url)) {
                        List<String> ipfsGwUserList = Preferences.get().getIpfsGwUserList();
                        Intrinsics.checkNotNullExpressionValue(ipfsGwUserList, "getIpfsGwUserList(...)");
                        List<String> mutableList = CollectionsKt.toMutableList((Collection) ipfsGwUserList);
                        if (!mutableList.contains(url)) {
                            mutableList.add(url);
                            Preferences.get().mo3127setIpfsGwUserList(mutableList);
                        }
                    }
                    ipfsGatewayAddActivity.finish();
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
                        ComposerKt.traceEventStart(212474564, i, -1, "org.fdroid.fdroid.views.IpfsGatewayAddActivity.onCreate.<anonymous> (IpfsGatewayAddActivity.kt:52)");
                    }
                    ThemeKt.FDroidContent(false, false, ComposableLambdaKt.rememberComposableLambda(84197902, true, new C00631(IpfsGatewayAddActivity.this), composer, 54), composer, 384, 3);
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
