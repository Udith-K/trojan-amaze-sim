package org.fdroid.fdroid.compose;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.unit.Dp;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.LocalLifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;

/* JADX INFO: compiled from: ComposeUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0003¢\u0006\u0004\b\u0002\u0010\u0003J;\u0010\r\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0004\b\r\u0010\u000eJE\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J)\u0010\u0018\u001a\u00020\u00072\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00070\u0014H\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lorg/fdroid/fdroid/compose/ComposeUtils;", "", "<init>", "()V", "", TextBundle.TEXT_ENTRY, "Lkotlin/Function0;", "", "onClick", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/graphics/vector/ImageVector;", "imageVector", "FDroidButton", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/graphics/Color;", "color", "FDroidOutlineButton-yrwZFoE", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/vector/ImageVector;JLandroidx/compose/runtime/Composer;II)V", "FDroidOutlineButton", "Lkotlin/Function2;", "Landroidx/lifecycle/LifecycleOwner;", "Landroidx/lifecycle/Lifecycle$Event;", "onEvent", "LifecycleEventListener", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "CaptionText", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ComposeUtils {
    public static final int $stable = 0;
    public static final ComposeUtils INSTANCE = new ComposeUtils();

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CaptionText$lambda$7(ComposeUtils composeUtils, String str, int i, Composer composer, int i2) {
        composeUtils.CaptionText(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FDroidButton$lambda$0(ComposeUtils composeUtils, String str, Function0 function0, Modifier modifier, ImageVector imageVector, int i, int i2, Composer composer, int i3) {
        composeUtils.FDroidButton(str, function0, modifier, imageVector, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FDroidOutlineButton_yrwZFoE$lambda$1(ComposeUtils composeUtils, String str, Function0 function0, Modifier modifier, ImageVector imageVector, long j, int i, int i2, Composer composer, int i3) {
        composeUtils.m2969FDroidOutlineButtonyrwZFoE(str, function0, modifier, imageVector, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LifecycleEventListener$lambda$6(ComposeUtils composeUtils, Function2 function2, int i, Composer composer, int i2) {
        composeUtils.LifecycleEventListener(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private ComposeUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void FDroidButton(final java.lang.String r21, final kotlin.jvm.functions.Function0 r22, androidx.compose.ui.Modifier r23, androidx.compose.ui.graphics.vector.ImageVector r24, androidx.compose.runtime.Composer r25, final int r26, final int r27) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.compose.ComposeUtils.FDroidButton(java.lang.String, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.vector.ImageVector, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: FDroidOutlineButton-yrwZFoE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m2969FDroidOutlineButtonyrwZFoE(final java.lang.String r25, final kotlin.jvm.functions.Function0 r26, androidx.compose.ui.Modifier r27, androidx.compose.ui.graphics.vector.ImageVector r28, long r29, androidx.compose.runtime.Composer r31, final int r32, final int r33) {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.compose.ComposeUtils.m2969FDroidOutlineButtonyrwZFoE(java.lang.String, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.vector.ImageVector, long, androidx.compose.runtime.Composer, int, int):void");
    }

    public final void LifecycleEventListener(final Function2 onEvent, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(onEvent, "onEvent");
        Composer composerStartRestartGroup = composer.startRestartGroup(-910953084);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(onEvent) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-910953084, i2, -1, "org.fdroid.fdroid.compose.ComposeUtils.LifecycleEventListener (ComposeUtils.kt:92)");
            }
            final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(onEvent, composerStartRestartGroup, i2 & 14);
            final State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(composerStartRestartGroup.consume(LocalLifecycleOwnerKt.getLocalLifecycleOwner()), composerStartRestartGroup, 0);
            Object value = stateRememberUpdatedState2.getValue();
            composerStartRestartGroup.startReplaceGroup(1409960892);
            boolean zChanged = composerStartRestartGroup.changed(stateRememberUpdatedState2) | composerStartRestartGroup.changed(stateRememberUpdatedState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.compose.ComposeUtils$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ComposeUtils.LifecycleEventListener$lambda$5$lambda$4(stateRememberUpdatedState2, stateRememberUpdatedState, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceGroup();
            EffectsKt.DisposableEffect(value, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.compose.ComposeUtils$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ComposeUtils.LifecycleEventListener$lambda$6(this.f$0, onEvent, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LifecycleEventListener$lambda$5$lambda$4(State state, final State state2, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final Lifecycle lifecycle = ((LifecycleOwner) state.getValue()).getLifecycle();
        final LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: org.fdroid.fdroid.compose.ComposeUtils$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                ComposeUtils.LifecycleEventListener$lambda$5$lambda$4$lambda$2(state2, lifecycleOwner, event);
            }
        };
        lifecycle.addObserver(lifecycleEventObserver);
        return new DisposableEffectResult() { // from class: org.fdroid.fdroid.compose.ComposeUtils$LifecycleEventListener$lambda$5$lambda$4$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                lifecycle.removeObserver(lifecycleEventObserver);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LifecycleEventListener$lambda$5$lambda$4$lambda$2(State state, LifecycleOwner owner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(event, "event");
        ((Function2) state.getValue()).invoke(owner, event);
    }

    public final void CaptionText(final String text, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(text, "text");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1851403916);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(text) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1851403916, i2, -1, "org.fdroid.fdroid.compose.ComposeUtils.CaptionText (ComposeUtils.kt:113)");
            }
            float f = 0;
            composer2 = composerStartRestartGroup;
            TextKt.m772Text4IGK_g(text, PaddingKt.m264paddingqDBjuR0(Modifier.Companion, Dp.m2416constructorimpl(f), Dp.m2416constructorimpl(16), Dp.m2416constructorimpl(f), Dp.m2416constructorimpl(4)), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composer2, (i2 & 14) | 48, 0, 65532);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.compose.ComposeUtils$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ComposeUtils.CaptionText$lambda$7(this.f$0, text, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
