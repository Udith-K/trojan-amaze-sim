package org.fdroid.fdroid.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.FloatingActionButtonKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SwitchKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.unit.Dp;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.IPreferencesIpfs;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.compose.ComposeUtils;
import org.fdroid.fdroid.ui.theme.ThemeKt;
import org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt;

/* JADX INFO: compiled from: IpfsGatewaySettingsActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u001a#\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001d\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n\u001a\u001d\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n\u001a\r\u0010\f\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\r¨\u0006\u000e²\u0006\n\u0010\b\u001a\u00020\tX\u008a\u008e\u0002²\u0006.\u0010\u000f\u001a&\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011 \u0012*\u0012\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00130\u0010X\u008a\u008e\u0002²\u0006.\u0010\u0014\u001a&\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011 \u0012*\u0012\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00130\u0010X\u008a\u008e\u0002"}, d2 = {"IpfsGatewaySettingsScreen", "", "onBackClicked", "Lkotlin/Function0;", "prefs", "Lorg/fdroid/fdroid/IPreferencesIpfs;", "(Lkotlin/jvm/functions/Function0;Lorg/fdroid/fdroid/IPreferencesIpfs;Landroidx/compose/runtime/Composer;I)V", "DefaultGatewaysSettings", "ipfsEnabled", "", "(Lorg/fdroid/fdroid/IPreferencesIpfs;ZLandroidx/compose/runtime/Composer;I)V", "UserGatewaysSettings", "IpfsGatewaySettingsScreenPreview", "(Landroidx/compose/runtime/Composer;I)V", "app_fullRelease", "disabledDefaultGateways", "", "", "kotlin.jvm.PlatformType", "", "userGateways"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class IpfsGatewaySettingsActivityKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DefaultGatewaysSettings$lambda$11(IPreferencesIpfs iPreferencesIpfs, boolean z, int i, Composer composer, int i2) {
        DefaultGatewaysSettings(iPreferencesIpfs, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit IpfsGatewaySettingsScreen$lambda$3(Function0 function0, IPreferencesIpfs iPreferencesIpfs, int i, Composer composer, int i2) {
        IpfsGatewaySettingsScreen(function0, iPreferencesIpfs, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit IpfsGatewaySettingsScreenPreview$lambda$22(int i, Composer composer, int i2) {
        IpfsGatewaySettingsScreenPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UserGatewaysSettings$lambda$21(IPreferencesIpfs iPreferencesIpfs, boolean z, int i, Composer composer, int i2) {
        UserGatewaysSettings(iPreferencesIpfs, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void IpfsGatewaySettingsScreen(final Function0 onBackClicked, final IPreferencesIpfs prefs, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(onBackClicked, "onBackClicked");
        Intrinsics.checkNotNullParameter(prefs, "prefs");
        Composer composerStartRestartGroup = composer.startRestartGroup(1016342442);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(onBackClicked) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(prefs) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1016342442, i2, -1, "org.fdroid.fdroid.views.IpfsGatewaySettingsScreen (IpfsGatewaySettingsActivity.kt:72)");
            }
            Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            composerStartRestartGroup.startReplaceGroup(-282265467);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(prefs.isIpfsEnabled()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            composer2 = composerStartRestartGroup;
            ScaffoldKt.m729ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(-359694738, true, new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt.IpfsGatewaySettingsScreen.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i3) {
                    if ((i3 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-359694738, i3, -1, "org.fdroid.fdroid.views.IpfsGatewaySettingsScreen.<anonymous> (IpfsGatewaySettingsActivity.kt:78)");
                        }
                        Function2 function2M3115getLambda1$app_fullRelease = ComposableSingletons$IpfsGatewaySettingsActivityKt.INSTANCE.m3115getLambda1$app_fullRelease();
                        final Function0 function0 = onBackClicked;
                        AppBarKt.m599TopAppBarGHTll3U(function2M3115getLambda1$app_fullRelease, null, ComposableLambdaKt.rememberComposableLambda(621031400, true, new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt.IpfsGatewaySettingsScreen.1.1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i4) {
                                if ((i4 & 3) != 2 || !composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(621031400, i4, -1, "org.fdroid.fdroid.views.IpfsGatewaySettingsScreen.<anonymous>.<anonymous> (IpfsGatewaySettingsActivity.kt:80)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$IpfsGatewaySettingsActivityKt.INSTANCE.m3116getLambda2$app_fullRelease(), composer4, 196608, 30);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer4.skipToGroupEnd();
                            }
                        }, composer3, 54), null, 0.0f, null, null, null, composer3, 390, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), null, null, ComposableLambdaKt.rememberComposableLambda(-593665551, true, new AnonymousClass2(context, mutableState), composerStartRestartGroup, 54), 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(-950568263, true, new AnonymousClass3(prefs, mutableState), composerStartRestartGroup, 54), composer2, 805330992, 493);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return IpfsGatewaySettingsActivityKt.IpfsGatewaySettingsScreen$lambda$3(onBackClicked, prefs, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void IpfsGatewaySettingsScreen$lambda$2(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final List<String> DefaultGatewaysSettings$lambda$5(MutableState mutableState) {
        return (List) mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean IpfsGatewaySettingsScreen$lambda$1(MutableState mutableState) {
        return ((Boolean) mutableState.getValue()).booleanValue();
    }

    private static final List<String> UserGatewaysSettings$lambda$13(MutableState mutableState) {
        return (List) mutableState.getValue();
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreen$2, reason: invalid class name */
    /* JADX INFO: compiled from: IpfsGatewaySettingsActivity.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass2 implements Function2 {
        final /* synthetic */ Context $context;
        final /* synthetic */ MutableState $ipfsEnabled$delegate;

        AnonymousClass2(Context context, MutableState mutableState) {
            this.$context = context;
            this.$ipfsEnabled$delegate = mutableState;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-593665551, i, -1, "org.fdroid.fdroid.views.IpfsGatewaySettingsScreen.<anonymous> (IpfsGatewaySettingsActivity.kt:93)");
                }
                if (IpfsGatewaySettingsActivityKt.IpfsGatewaySettingsScreen$lambda$1(this.$ipfsEnabled$delegate)) {
                    composer.startReplaceGroup(2022596750);
                    boolean zChangedInstance = composer.changedInstance(this.$context);
                    final Context context = this.$context;
                    Object objRememberedValue = composer.rememberedValue();
                    if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreen$2$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return IpfsGatewaySettingsActivityKt.AnonymousClass2.invoke$lambda$1$lambda$0(context);
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    composer.endReplaceGroup();
                    FloatingActionButtonKt.m683FloatingActionButtonXz6DiA((Function0) objRememberedValue, null, null, 0L, 0L, null, null, ComposableSingletons$IpfsGatewaySettingsActivityKt.INSTANCE.m3117getLambda3$app_fullRelease(), composer, 12582912, 126);
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$1$lambda$0(Context context) {
            context.startActivity(new Intent(context, (Class<?>) IpfsGatewayAddActivity.class));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreen$3, reason: invalid class name */
    /* JADX INFO: compiled from: IpfsGatewaySettingsActivity.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass3 implements Function3 {
        final /* synthetic */ MutableState $ipfsEnabled$delegate;
        final /* synthetic */ IPreferencesIpfs $prefs;

        AnonymousClass3(IPreferencesIpfs iPreferencesIpfs, MutableState mutableState) {
            this.$prefs = iPreferencesIpfs;
            this.$ipfsEnabled$delegate = mutableState;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((PaddingValues) obj, (Composer) obj2, ((Number) obj3).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(PaddingValues paddingValues, Composer composer, int i) {
            int i2;
            final MutableState mutableState;
            Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
            if ((i & 6) == 0) {
                i2 = i | (composer.changed(paddingValues) ? 4 : 2);
            } else {
                i2 = i;
            }
            if ((i2 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-950568263, i2, -1, "org.fdroid.fdroid.views.IpfsGatewaySettingsScreen.<anonymous> (IpfsGatewaySettingsActivity.kt:104)");
            }
            Modifier.Companion companion = Modifier.Companion;
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(PaddingKt.padding(companion, paddingValues), ScrollKt.rememberScrollState(0, composer, 0, 1), false, null, false, 14, null);
            final IPreferencesIpfs iPreferencesIpfs = this.$prefs;
            MutableState mutableState2 = this.$ipfsEnabled$delegate;
            Alignment.Companion companion2 = Alignment.Companion;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion2.getTopStart(), false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierVerticalScroll$default);
            ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
            Function0 constructor = companion3.getConstructor();
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM1035constructorimpl = Updater.m1035constructorimpl(composer);
            Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion3.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier modifierM261padding3ABfNKs = PaddingKt.m261padding3ABfNKs(companion, Dp.m2416constructorimpl(16));
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(arrangement.getTop(), companion2.getStart(), composer, 0);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierM261padding3ABfNKs);
            Function0 constructor2 = companion3.getConstructor();
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM1035constructorimpl2 = Updater.m1035constructorimpl(composer);
            Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyColumnMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
            if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.getStart(), companion2.getTop(), composer, 0);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer, modifierFillMaxWidth$default);
            Function0 constructor3 = companion3.getConstructor();
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor3);
            } else {
                composer.useNode();
            }
            Composer composerM1035constructorimpl3 = Updater.m1035constructorimpl(composer);
            Updater.m1036setimpl(composerM1035constructorimpl3, measurePolicyRowMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl3, currentCompositionLocalMap3, companion3.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash3 = companion3.getSetCompositeKeyHash();
            if (composerM1035constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM1035constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM1035constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m1036setimpl(composerM1035constructorimpl3, modifierMaterializeModifier3, companion3.getSetModifier());
            TextKt.m772Text4IGK_g(StringResources_androidKt.stringResource(R.string.ipfsgw_explainer, composer, 0), RowScope.CC.weight$default(RowScopeInstance.INSTANCE, companion, 1.0f, false, 2, null), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodyLarge(), composer, 0, 0, 65532);
            boolean zIpfsGatewaySettingsScreen$lambda$1 = IpfsGatewaySettingsActivityKt.IpfsGatewaySettingsScreen$lambda$1(mutableState2);
            composer.startReplaceGroup(309350487);
            boolean zChangedInstance = composer.changedInstance(iPreferencesIpfs);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                mutableState = mutableState2;
                objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreen$3$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return IpfsGatewaySettingsActivityKt.AnonymousClass3.invoke$lambda$4$lambda$3$lambda$2$lambda$1$lambda$0(iPreferencesIpfs, mutableState, ((Boolean) obj).booleanValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            } else {
                mutableState = mutableState2;
            }
            composer.endReplaceGroup();
            SwitchKt.Switch(zIpfsGatewaySettingsScreen$lambda$1, (Function1) objRememberedValue, null, null, false, null, null, composer, 0, 124);
            composer.endNode();
            IpfsGatewaySettingsActivityKt.DefaultGatewaysSettings(iPreferencesIpfs, IpfsGatewaySettingsActivityKt.IpfsGatewaySettingsScreen$lambda$1(mutableState), composer, 0);
            IpfsGatewaySettingsActivityKt.UserGatewaysSettings(iPreferencesIpfs, IpfsGatewaySettingsActivityKt.IpfsGatewaySettingsScreen$lambda$1(mutableState), composer, 0);
            SpacerKt.Spacer(SizeKt.m275height3ABfNKs(companion, Dp.m2416constructorimpl(64)), composer, 6);
            composer.endNode();
            composer.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$4$lambda$3$lambda$2$lambda$1$lambda$0(IPreferencesIpfs iPreferencesIpfs, MutableState mutableState, boolean z) {
            IpfsGatewaySettingsActivityKt.IpfsGatewaySettingsScreen$lambda$2(mutableState, z);
            iPreferencesIpfs.mo3125setIpfsEnabled(z);
            return Unit.INSTANCE;
        }
    }

    @SuppressLint({"MutableCollectionMutableState"})
    public static final void DefaultGatewaysSettings(final IPreferencesIpfs prefs, final boolean z, Composer composer, final int i) {
        Composer composer2;
        int i2 = 48;
        Intrinsics.checkNotNullParameter(prefs, "prefs");
        Composer composerStartRestartGroup = composer.startRestartGroup(-170757290);
        int i3 = (i & 6) == 0 ? (composerStartRestartGroup.changedInstance(prefs) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        int i4 = i3;
        if ((i4 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-170757290, i4, -1, "org.fdroid.fdroid.views.DefaultGatewaysSettings (IpfsGatewaySettingsActivity.kt:139)");
            }
            composerStartRestartGroup.startReplaceGroup(-1775870750);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Object obj = null;
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(prefs.getIpfsGwDisabledDefaults(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            Modifier.Companion companion = Modifier.Companion;
            int i5 = 0;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composerStartRestartGroup, 0);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
            Function0 constructor = companion2.getConstructor();
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM1035constructorimpl = Updater.m1035constructorimpl(composerStartRestartGroup);
            Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyColumnMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposeUtils.INSTANCE.CaptionText(StringResources_androidKt.stringResource(R.string.ipfsgw_caption_official_gateways, composerStartRestartGroup, 0), composerStartRestartGroup, 48);
            composerStartRestartGroup.startReplaceGroup(-1618876811);
            for (final String str : Preferences.DEFAULT_IPFS_GATEWAYS) {
                Modifier.Companion companion3 = Modifier.Companion;
                float f = 4;
                Modifier modifierM264paddingqDBjuR0 = PaddingKt.m264paddingqDBjuR0(SizeKt.fillMaxWidth$default(companion3, 0.0f, 1, obj), Dp.m2416constructorimpl(i2), Dp.m2416constructorimpl(f), Dp.m2416constructorimpl(i5), Dp.m2416constructorimpl(f));
                Arrangement.Horizontal start = Arrangement.INSTANCE.getStart();
                Alignment.Companion companion4 = Alignment.Companion;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, companion4.getTop(), composerStartRestartGroup, i5);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, i5);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM264paddingqDBjuR0);
                ComposeUiNode.Companion companion5 = ComposeUiNode.Companion;
                Function0 constructor2 = companion5.getConstructor();
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM1035constructorimpl2 = Updater.m1035constructorimpl(composerStartRestartGroup);
                Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyRowMeasurePolicy, companion5.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion5.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash2 = companion5.getSetCompositeKeyHash();
                if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion5.getSetModifier());
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                Intrinsics.checkNotNull(str);
                TextKt.m772Text4IGK_g(str, AlphaKt.alpha(rowScopeInstance.align(RowScope.CC.weight$default(rowScopeInstance, companion3, 1.0f, false, 2, null), companion4.getCenterVertically()), z ? 1.0f : 0.5f), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodyLarge(), composerStartRestartGroup, 0, 0, 65532);
                boolean zContains = true ^ DefaultGatewaysSettings$lambda$5(mutableState).contains(str);
                Modifier modifierAlign = rowScopeInstance.align(companion3, companion4.getCenterVertically());
                composerStartRestartGroup.startReplaceGroup(303816670);
                boolean zChanged = composerStartRestartGroup.changed(str) | composerStartRestartGroup.changedInstance(prefs);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.Companion.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return IpfsGatewaySettingsActivityKt.DefaultGatewaysSettings$lambda$10$lambda$9$lambda$8$lambda$7(str, prefs, mutableState, ((Boolean) obj2).booleanValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                composerStartRestartGroup.endReplaceGroup();
                Composer composer3 = composerStartRestartGroup;
                SwitchKt.Switch(zContains, (Function1) objRememberedValue2, modifierAlign, null, z, null, null, composerStartRestartGroup, (i4 << 9) & 57344, 104);
                composer3.endNode();
                i4 = i4;
                i5 = 0;
                mutableState = mutableState;
                obj = null;
                composerStartRestartGroup = composer3;
                i2 = 48;
            }
            composer2 = composerStartRestartGroup;
            composer2.endReplaceGroup();
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return IpfsGatewaySettingsActivityKt.DefaultGatewaysSettings$lambda$11(prefs, z, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DefaultGatewaysSettings$lambda$10$lambda$9$lambda$8$lambda$7(String str, IPreferencesIpfs iPreferencesIpfs, MutableState mutableState, boolean z) {
        List<String> listDefaultGatewaysSettings$lambda$5 = DefaultGatewaysSettings$lambda$5(mutableState);
        Intrinsics.checkNotNullExpressionValue(listDefaultGatewaysSettings$lambda$5, "DefaultGatewaysSettings$lambda$5(...)");
        List<String> mutableList = CollectionsKt.toMutableList((Collection) listDefaultGatewaysSettings$lambda$5);
        if (!z) {
            mutableList.add(str);
        } else {
            mutableList.remove(str);
        }
        mutableState.setValue(mutableList);
        iPreferencesIpfs.mo3126setIpfsGwDisabledDefaults(mutableList);
        return Unit.INSTANCE;
    }

    @SuppressLint({"MutableCollectionMutableState"})
    public static final void UserGatewaysSettings(final IPreferencesIpfs prefs, final boolean z, Composer composer, final int i) {
        Composer composer2;
        int i2 = 48;
        Intrinsics.checkNotNullParameter(prefs, "prefs");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1847366532);
        int i3 = (i & 6) == 0 ? (composerStartRestartGroup.changedInstance(prefs) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        int i4 = i3;
        if ((i4 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1847366532, i4, -1, "org.fdroid.fdroid.views.UserGatewaysSettings (IpfsGatewaySettingsActivity.kt:185)");
            }
            composerStartRestartGroup.startReplaceGroup(126795800);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(prefs.getIpfsGwUserList(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            ComposeUtils composeUtils = ComposeUtils.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(126800934);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(prefs);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return IpfsGatewaySettingsActivityKt.UserGatewaysSettings$lambda$16$lambda$15(prefs, mutableState, (LifecycleOwner) obj, (Lifecycle.Event) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceGroup();
            composeUtils.LifecycleEventListener((Function2) objRememberedValue2, composerStartRestartGroup, 48);
            Modifier.Companion companion2 = Modifier.Companion;
            int i5 = 0;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composerStartRestartGroup, 0);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
            ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
            Function0 constructor = companion3.getConstructor();
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM1035constructorimpl = Updater.m1035constructorimpl(composerStartRestartGroup);
            Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyColumnMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion3.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(1585485421);
            List<String> listUserGatewaysSettings$lambda$13 = UserGatewaysSettings$lambda$13(mutableState);
            Intrinsics.checkNotNullExpressionValue(listUserGatewaysSettings$lambda$13, "UserGatewaysSettings$lambda$13(...)");
            if (!listUserGatewaysSettings$lambda$13.isEmpty()) {
                composeUtils.CaptionText(StringResources_androidKt.stringResource(R.string.ipfsgw_caption_custom_gateways, composerStartRestartGroup, 0), composerStartRestartGroup, 48);
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(1585491099);
            for (final String str : UserGatewaysSettings$lambda$13(mutableState)) {
                Modifier.Companion companion4 = Modifier.Companion;
                float f = 4;
                Modifier modifierM264paddingqDBjuR0 = PaddingKt.m264paddingqDBjuR0(SizeKt.fillMaxWidth$default(companion4, 0.0f, 1, null), Dp.m2416constructorimpl(i2), Dp.m2416constructorimpl(f), Dp.m2416constructorimpl(i5), Dp.m2416constructorimpl(f));
                Arrangement.Horizontal start = Arrangement.INSTANCE.getStart();
                Alignment.Companion companion5 = Alignment.Companion;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, companion5.getTop(), composerStartRestartGroup, i5);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, i5);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM264paddingqDBjuR0);
                ComposeUiNode.Companion companion6 = ComposeUiNode.Companion;
                Function0 constructor2 = companion6.getConstructor();
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM1035constructorimpl2 = Updater.m1035constructorimpl(composerStartRestartGroup);
                Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyRowMeasurePolicy, companion6.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion6.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash2 = companion6.getSetCompositeKeyHash();
                if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion6.getSetModifier());
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                Intrinsics.checkNotNull(str);
                int i6 = i5;
                TextKt.m772Text4IGK_g(str, AlphaKt.alpha(rowScopeInstance.align(RowScope.CC.weight$default(rowScopeInstance, companion4, 1.0f, false, 2, null), companion5.getCenterVertically()), z ? 1.0f : 0.5f), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodyLarge(), composerStartRestartGroup, 0, 0, 65532);
                Modifier modifierAlign = rowScopeInstance.align(companion4, companion5.getCenterVertically());
                composerStartRestartGroup.startReplaceGroup(-1201382391);
                boolean zChanged = composerStartRestartGroup.changed(str) | composerStartRestartGroup.changedInstance(prefs);
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue3 == Composer.Companion.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return IpfsGatewaySettingsActivityKt.UserGatewaysSettings$lambda$20$lambda$19$lambda$18$lambda$17(str, prefs, mutableState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                composerStartRestartGroup.endReplaceGroup();
                Composer composer3 = composerStartRestartGroup;
                IconButtonKt.IconButton((Function0) objRememberedValue3, modifierAlign, z, null, null, ComposableSingletons$IpfsGatewaySettingsActivityKt.INSTANCE.m3118getLambda4$app_fullRelease(), composerStartRestartGroup, ((i4 << 3) & 896) | 196608, 24);
                composer3.endNode();
                i5 = i6;
                mutableState = mutableState;
                i4 = i4;
                composerStartRestartGroup = composer3;
                i2 = 48;
            }
            composer2 = composerStartRestartGroup;
            composer2.endReplaceGroup();
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return IpfsGatewaySettingsActivityKt.UserGatewaysSettings$lambda$21(prefs, z, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UserGatewaysSettings$lambda$16$lambda$15(IPreferencesIpfs iPreferencesIpfs, MutableState mutableState, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<unused var>");
        Intrinsics.checkNotNullParameter(event, "event");
        if (Lifecycle.Event.ON_RESUME == event) {
            mutableState.setValue(iPreferencesIpfs.getIpfsGwUserList());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UserGatewaysSettings$lambda$20$lambda$19$lambda$18$lambda$17(String str, IPreferencesIpfs iPreferencesIpfs, MutableState mutableState) {
        List<String> listUserGatewaysSettings$lambda$13 = UserGatewaysSettings$lambda$13(mutableState);
        Intrinsics.checkNotNullExpressionValue(listUserGatewaysSettings$lambda$13, "UserGatewaysSettings$lambda$13(...)");
        List<String> mutableList = CollectionsKt.toMutableList((Collection) listUserGatewaysSettings$lambda$13);
        mutableList.remove(str);
        mutableState.setValue(mutableList);
        iPreferencesIpfs.mo3127setIpfsGwUserList(mutableList);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreenPreview$prefs$1] */
    public static final void IpfsGatewaySettingsScreenPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1596343769);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1596343769, i, -1, "org.fdroid.fdroid.views.IpfsGatewaySettingsScreenPreview (IpfsGatewaySettingsActivity.kt:237)");
            }
            ThemeKt.FDroidContent(false, true, ComposableLambdaKt.rememberComposableLambda(-2143016975, true, new C02021(new IPreferencesIpfs() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreenPreview$prefs$1
                @Override // org.fdroid.fdroid.IPreferencesIpfs
                public boolean isIpfsEnabled() {
                    return true;
                }

                @Override // org.fdroid.fdroid.IPreferencesIpfs
                /* JADX INFO: renamed from: setIpfsGwDisabledDefaults, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ void mo3126setIpfsGwDisabledDefaults(List list) {
                    setIpfsGwDisabledDefaults((List<String>) list);
                }

                @Override // org.fdroid.fdroid.IPreferencesIpfs
                /* JADX INFO: renamed from: setIpfsGwUserList, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ void mo3127setIpfsGwUserList(List list) {
                    setIpfsGwUserList((List<String>) list);
                }

                @Override // org.fdroid.fdroid.IPreferencesIpfs
                /* JADX INFO: renamed from: setIpfsEnabled, reason: merged with bridge method [inline-methods] */
                public Void mo3125setIpfsEnabled(boolean enabled) {
                    throw new NotImplementedError(null, 1, null);
                }

                @Override // org.fdroid.fdroid.IPreferencesIpfs
                public List<String> getIpfsGwUserList() {
                    return CollectionsKt.listOf("https://my.imaginary.gateway/ifps/");
                }

                public Void setIpfsGwUserList(List<String> selectedSet) {
                    throw new NotImplementedError(null, 1, null);
                }

                @Override // org.fdroid.fdroid.IPreferencesIpfs
                public List<String> getIpfsGwDisabledDefaults() {
                    return CollectionsKt.listOf("https://4everland.io/ipfs/");
                }

                public Void setIpfsGwDisabledDefaults(List<String> selectedSet) {
                    throw new NotImplementedError(null, 1, null);
                }
            }), composerStartRestartGroup, 54), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return IpfsGatewaySettingsActivityKt.IpfsGatewaySettingsScreenPreview$lambda$22(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreenPreview$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: IpfsGatewaySettingsActivity.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02021 implements Function2 {
        final /* synthetic */ IpfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreenPreview$prefs$1 $prefs;

        C02021(IpfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreenPreview$prefs$1 ipfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreenPreview$prefs$1) {
            this.$prefs = ipfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreenPreview$prefs$1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2143016975, i, -1, "org.fdroid.fdroid.views.IpfsGatewaySettingsScreenPreview.<anonymous> (IpfsGatewaySettingsActivity.kt:251)");
                }
                composer.startReplaceGroup(-454902252);
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.IpfsGatewaySettingsActivityKt$IpfsGatewaySettingsScreenPreview$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                IpfsGatewaySettingsActivityKt.IpfsGatewaySettingsScreen((Function0) objRememberedValue, this.$prefs, composer, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    }
}
