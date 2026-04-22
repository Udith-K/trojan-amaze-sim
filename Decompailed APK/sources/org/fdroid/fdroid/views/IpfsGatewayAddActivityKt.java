package org.fdroid.fdroid.views;

import android.net.Uri;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ContentPasteKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextFieldKt;
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
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.res.ColorResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Dp;
import androidx.recyclerview.widget.ItemTouchHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.compose.ComposeUtils;
import org.fdroid.fdroid.ui.theme.ThemeKt;
import org.fdroid.fdroid.views.IpfsGatewayAddActivityKt;

/* JADX INFO: compiled from: IpfsGatewayAddActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u001a1\u0010\u0006\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u000f\u0010\b\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\u000b²\u0006\u000e\u0010\n\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002"}, d2 = {"Lkotlin/Function0;", "", "onBackClicked", "Lkotlin/Function1;", "", "onAddUserGateway", "IpfsGatewayAddScreen", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "IpfsGatewayAddScreenPreview", "(Landroidx/compose/runtime/Composer;I)V", "errorMsg", "app_fullRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class IpfsGatewayAddActivityKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit IpfsGatewayAddScreen$lambda$5(Function0 function0, Function1 function1, int i, Composer composer, int i2) {
        IpfsGatewayAddScreen(function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit IpfsGatewayAddScreenPreview$lambda$6(int i, Composer composer, int i2) {
        IpfsGatewayAddScreenPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void IpfsGatewayAddScreen(final Function0 onBackClicked, final Function1 onAddUserGateway, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(onBackClicked, "onBackClicked");
        Intrinsics.checkNotNullParameter(onAddUserGateway, "onAddUserGateway");
        Composer composerStartRestartGroup = composer.startRestartGroup(354774652);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(onBackClicked) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onAddUserGateway) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(354774652, i2, -1, "org.fdroid.fdroid.views.IpfsGatewayAddScreen (IpfsGatewayAddActivity.kt:78)");
            }
            composerStartRestartGroup.startReplaceGroup(4576852);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(4579108);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new FocusRequester();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            FocusRequester focusRequester = (FocusRequester) objRememberedValue2;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(4580710);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceGroup();
            composer2 = composerStartRestartGroup;
            ScaffoldKt.m729ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(7546680, true, new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewayAddActivityKt.IpfsGatewayAddScreen.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i3) {
                    if ((i3 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(7546680, i3, -1, "org.fdroid.fdroid.views.IpfsGatewayAddScreen.<anonymous> (IpfsGatewayAddActivity.kt:85)");
                        }
                        Function2 function2M3111getLambda1$app_fullRelease = ComposableSingletons$IpfsGatewayAddActivityKt.INSTANCE.m3111getLambda1$app_fullRelease();
                        final Function0 function0 = onBackClicked;
                        AppBarKt.m599TopAppBarGHTll3U(function2M3111getLambda1$app_fullRelease, null, ComposableLambdaKt.rememberComposableLambda(-281499010, true, new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewayAddActivityKt.IpfsGatewayAddScreen.1.1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i4) {
                                if ((i4 & 3) != 2 || !composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-281499010, i4, -1, "org.fdroid.fdroid.views.IpfsGatewayAddScreen.<anonymous>.<anonymous> (IpfsGatewayAddActivity.kt:87)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$IpfsGatewayAddActivityKt.INSTANCE.m3112getLambda2$app_fullRelease(), composer4, 196608, 30);
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
            }, composerStartRestartGroup, 54), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(1924022157, true, new AnonymousClass2(mutableState, focusRequester, (MutableState) objRememberedValue3, onAddUserGateway), composerStartRestartGroup, 54), composer2, 805306416, 509);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewayAddActivityKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return IpfsGatewayAddActivityKt.IpfsGatewayAddScreen$lambda$5(onBackClicked, onAddUserGateway, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String IpfsGatewayAddScreen$lambda$3(MutableState mutableState) {
        return (String) mutableState.getValue();
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.IpfsGatewayAddActivityKt$IpfsGatewayAddScreen$2, reason: invalid class name */
    /* JADX INFO: compiled from: IpfsGatewayAddActivity.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass2 implements Function3 {
        final /* synthetic */ MutableState $errorMsg$delegate;
        final /* synthetic */ FocusRequester $focusRequester;
        final /* synthetic */ Function1 $onAddUserGateway;
        final /* synthetic */ MutableState $textState;

        AnonymousClass2(MutableState mutableState, FocusRequester focusRequester, MutableState mutableState2, Function1 function1) {
            this.$textState = mutableState;
            this.$focusRequester = focusRequester;
            this.$errorMsg$delegate = mutableState2;
            this.$onAddUserGateway = function1;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((PaddingValues) obj, (Composer) obj2, ((Number) obj3).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(PaddingValues paddingValues, Composer composer, int i) {
            int i2;
            final MutableState mutableState;
            final MutableState mutableState2;
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
                ComposerKt.traceEventStart(1924022157, i2, -1, "org.fdroid.fdroid.views.IpfsGatewayAddScreen.<anonymous> (IpfsGatewayAddActivity.kt:99)");
            }
            Arrangement arrangement = Arrangement.INSTANCE;
            float f = 16;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM245spacedBy0680j_4 = arrangement.m245spacedBy0680j_4(Dp.m2416constructorimpl(f));
            Modifier.Companion companion = Modifier.Companion;
            Modifier modifierM261padding3ABfNKs = PaddingKt.m261padding3ABfNKs(PaddingKt.padding(companion, paddingValues), Dp.m2416constructorimpl(f));
            MutableState mutableState3 = this.$textState;
            final FocusRequester focusRequester = this.$focusRequester;
            final MutableState mutableState4 = this.$errorMsg$delegate;
            final Function1 function1 = this.$onAddUserGateway;
            Alignment.Companion companion2 = Alignment.Companion;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM245spacedBy0680j_4, companion2.getStart(), composer, 6);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM261padding3ABfNKs);
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
            Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyColumnMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion3.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            MaterialTheme materialTheme = MaterialTheme.INSTANCE;
            int i3 = MaterialTheme.$stable;
            TextKt.m772Text4IGK_g("Enter IPFS gateway URL", null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer, i3).getBodyLarge(), composer, 6, 0, 65534);
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(arrangement.getTop(), companion2.getStart(), composer, 0);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, companion);
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
            Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyColumnMeasurePolicy2, companion3.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
            if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
            TextFieldValue textFieldValue = (TextFieldValue) mutableState3.getValue();
            boolean z = IpfsGatewayAddActivityKt.IpfsGatewayAddScreen$lambda$3(mutableState4).length() > 0;
            Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), focusRequester);
            composer.startReplaceGroup(1676542649);
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion4 = Composer.Companion;
            if (objRememberedValue == companion4.getEmpty()) {
                objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.views.IpfsGatewayAddActivityKt$IpfsGatewayAddScreen$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return IpfsGatewayAddActivityKt.AnonymousClass2.invoke$lambda$10$lambda$4$lambda$1$lambda$0(focusRequester, (LayoutCoordinates) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceGroup();
            Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(modifierFocusRequester, (Function1) objRememberedValue);
            composer.startReplaceGroup(1676534236);
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == companion4.getEmpty()) {
                mutableState = mutableState3;
                objRememberedValue2 = new Function1() { // from class: org.fdroid.fdroid.views.IpfsGatewayAddActivityKt$IpfsGatewayAddScreen$2$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return IpfsGatewayAddActivityKt.AnonymousClass2.invoke$lambda$10$lambda$4$lambda$3$lambda$2(mutableState, (TextFieldValue) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            } else {
                mutableState = mutableState3;
            }
            composer.endReplaceGroup();
            MutableState mutableState5 = mutableState;
            TextFieldKt.TextField(textFieldValue, (Function1) objRememberedValue2, modifierOnGloballyPositioned, false, false, null, null, null, null, null, null, null, null, z, null, null, null, false, 0, 2, null, null, null, composer, 48, 805306368, 0, 7856120);
            composer.startReplaceGroup(1676546766);
            if (IpfsGatewayAddActivityKt.IpfsGatewayAddScreen$lambda$3(mutableState4).length() > 0) {
                TextKt.m772Text4IGK_g(IpfsGatewayAddActivityKt.IpfsGatewayAddScreen$lambda$3(mutableState4), null, ColorResources_androidKt.colorResource(R.color.fdroid_error, composer, 0), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer, i3).getBodyLarge(), composer, 0, 0, 65530);
            }
            composer.endReplaceGroup();
            composer.endNode();
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.m245spacedBy0680j_4(Dp.m2416constructorimpl(f)), companion2.getCenterVertically(), composer, 54);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer, companion);
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
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            final ClipboardManager clipboardManager = (ClipboardManager) composer.consume(CompositionLocalsKt.getLocalClipboardManager());
            ComposeUtils composeUtils = ComposeUtils.INSTANCE;
            String strStringResource = StringResources_androidKt.stringResource(R.string.paste, composer, 0);
            ImageVector contentPaste = ContentPasteKt.getContentPaste(Icons.INSTANCE.getDefault());
            composer.startReplaceGroup(1676570996);
            boolean zChangedInstance = composer.changedInstance(clipboardManager);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue3 == companion4.getEmpty()) {
                mutableState2 = mutableState5;
                objRememberedValue3 = new Function0() { // from class: org.fdroid.fdroid.views.IpfsGatewayAddActivityKt$IpfsGatewayAddScreen$2$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return IpfsGatewayAddActivityKt.AnonymousClass2.invoke$lambda$10$lambda$9$lambda$6$lambda$5(clipboardManager, mutableState2);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            } else {
                mutableState2 = mutableState5;
            }
            composer.endReplaceGroup();
            final MutableState mutableState6 = mutableState2;
            composeUtils.m2969FDroidOutlineButtonyrwZFoE(strStringResource, (Function0) objRememberedValue3, null, contentPaste, 0L, composer, 196608, 20);
            SpacerKt.Spacer(RowScope.CC.weight$default(rowScopeInstance, companion, 1.0f, false, 2, null), composer, 0);
            String strStringResource2 = StringResources_androidKt.stringResource(R.string.ipfsgw_add_add, composer, 0);
            composer.startReplaceGroup(1676584933);
            boolean zChanged = composer.changed(function1);
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChanged || objRememberedValue4 == companion4.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: org.fdroid.fdroid.views.IpfsGatewayAddActivityKt$IpfsGatewayAddScreen$2$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return IpfsGatewayAddActivityKt.AnonymousClass2.invoke$lambda$10$lambda$9$lambda$8$lambda$7(mutableState6, function1, mutableState4);
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            composer.endReplaceGroup();
            composeUtils.FDroidButton(strStringResource2, (Function0) objRememberedValue4, null, null, composer, 24576, 12);
            composer.endNode();
            composer.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$10$lambda$4$lambda$3$lambda$2(MutableState mutableState, TextFieldValue it) {
            Intrinsics.checkNotNullParameter(it, "it");
            mutableState.setValue(it);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$10$lambda$4$lambda$1$lambda$0(FocusRequester focusRequester, LayoutCoordinates it) {
            Intrinsics.checkNotNullParameter(it, "it");
            focusRequester.requestFocus();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$10$lambda$9$lambda$6$lambda$5(ClipboardManager clipboardManager, MutableState mutableState) {
            String text;
            if (clipboardManager.hasText()) {
                AnnotatedString text2 = clipboardManager.getText();
                if (text2 == null || (text = text2.getText()) == null) {
                    text = "";
                }
                mutableState.setValue(new TextFieldValue(text, 0L, (TextRange) null, 6, (DefaultConstructorMarker) null));
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$10$lambda$9$lambda$8$lambda$7(MutableState mutableState, Function1 function1, MutableState mutableState2) {
            String text;
            IpfsGatewayAddActivityKt.IpfsGatewayAddScreen$lambda$4(mutableState2, "");
            if (StringsKt.endsWith$default(((TextFieldValue) mutableState.getValue()).getText(), "/", false, 2, (Object) null)) {
                text = ((TextFieldValue) mutableState.getValue()).getText();
            } else {
                text = ((TextFieldValue) mutableState.getValue()).getText() + "/";
            }
            try {
                if (!CollectionsKt.contains(SetsKt.setOf((Object[]) new String[]{"http", "https"}), Uri.parse(text).getScheme())) {
                    IpfsGatewayAddActivityKt.IpfsGatewayAddScreen$lambda$4(mutableState2, "IPFS gateway URL should start with `https://`");
                    return Unit.INSTANCE;
                }
                function1.invoke(text);
                return Unit.INSTANCE;
            } catch (Exception e) {
                IpfsGatewayAddActivityKt.IpfsGatewayAddScreen$lambda$4(mutableState2, "could not parse uri (" + e + ")");
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void IpfsGatewayAddScreen$lambda$4(MutableState mutableState, String str) {
        mutableState.setValue(str);
    }

    public static final void IpfsGatewayAddScreenPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-384094241);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-384094241, i, -1, "org.fdroid.fdroid.views.IpfsGatewayAddScreenPreview (IpfsGatewayAddActivity.kt:179)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$IpfsGatewayAddActivityKt.INSTANCE.m3113getLambda3$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.IpfsGatewayAddActivityKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return IpfsGatewayAddActivityKt.IpfsGatewayAddScreenPreview$lambda$6(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
