package org.fdroid.fdroid.views.repos;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ContentPasteKt;
import androidx.compose.material3.TextFieldKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.Updater;
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
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.compose.ComposeUtils;

/* JADX INFO: compiled from: AddRepoIntroScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class AddRepoIntroScreenKt$AddRepoIntroContent$1$4 implements Function3 {
    final /* synthetic */ FocusRequester $focusRequester;
    final /* synthetic */ Function1 $onFetchRepo;
    final /* synthetic */ MutableState $textState;

    AddRepoIntroScreenKt$AddRepoIntroContent$1$4(MutableState mutableState, Function1 function1, FocusRequester focusRequester) {
        this.$textState = mutableState;
        this.$onFetchRepo = function1;
        this.$focusRequester = focusRequester;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((AnimatedVisibilityScope) obj, (Composer) obj2, ((Number) obj3).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer composer, int i) {
        final MutableState mutableState;
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-397246604, i, -1, "org.fdroid.fdroid.views.repos.AddRepoIntroContent.<anonymous>.<anonymous> (AddRepoIntroScreen.kt:188)");
        }
        Alignment.Companion companion = Alignment.Companion;
        Alignment.Horizontal end = companion.getEnd();
        Arrangement arrangement = Arrangement.INSTANCE;
        float f = 16;
        Arrangement.HorizontalOrVertical horizontalOrVerticalM245spacedBy0680j_4 = arrangement.m245spacedBy0680j_4(Dp.m2416constructorimpl(f));
        final MutableState mutableState2 = this.$textState;
        final Function1 function1 = this.$onFetchRepo;
        final FocusRequester focusRequester = this.$focusRequester;
        Modifier.Companion companion2 = Modifier.Companion;
        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM245spacedBy0680j_4, end, composer, 54);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, companion2);
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
        TextFieldValue textFieldValue = (TextFieldValue) mutableState2.getValue();
        KeyboardOptions keyboardOptions = new KeyboardOptions(0, null, 0, ImeAction.Companion.m2196getGoeUduSuo(), null, null, null, 119, null);
        composer.startReplaceGroup(2008812159);
        boolean zChanged = composer.changed(function1);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$AddRepoIntroContent$1$4$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AddRepoIntroScreenKt$AddRepoIntroContent$1$4.invoke$lambda$11$lambda$1$lambda$0(function1, mutableState2, (KeyboardActionScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceGroup();
        KeyboardActions keyboardActions = new KeyboardActions(null, (Function1) objRememberedValue, null, null, null, null, 61, null);
        Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), focusRequester);
        composer.startReplaceGroup(2008821627);
        Object objRememberedValue2 = composer.rememberedValue();
        Composer.Companion companion4 = Composer.Companion;
        if (objRememberedValue2 == companion4.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$AddRepoIntroContent$1$4$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AddRepoIntroScreenKt$AddRepoIntroContent$1$4.invoke$lambda$11$lambda$3$lambda$2(focusRequester, (LayoutCoordinates) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceGroup();
        Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(modifierFocusRequester, (Function1) objRememberedValue2);
        composer.startReplaceGroup(2008805918);
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion4.getEmpty()) {
            objRememberedValue3 = new Function1() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$AddRepoIntroContent$1$4$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AddRepoIntroScreenKt$AddRepoIntroContent$1$4.invoke$lambda$11$lambda$5$lambda$4(mutableState2, (TextFieldValue) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        composer.endReplaceGroup();
        TextFieldKt.TextField(textFieldValue, (Function1) objRememberedValue3, modifierOnGloballyPositioned, false, false, null, null, null, null, null, null, null, null, false, null, keyboardOptions, keyboardActions, false, 0, 2, null, null, null, composer, 48, 805502976, 0, 7766008);
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.m245spacedBy0680j_4(Dp.m2416constructorimpl(f)), companion.getCenterVertically(), composer, 54);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, companion2);
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
        Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyRowMeasurePolicy, companion3.getSetMeasurePolicy());
        Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
        Function2 setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
        if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
            composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
        }
        Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        final ClipboardManager clipboardManager = (ClipboardManager) composer.consume(CompositionLocalsKt.getLocalClipboardManager());
        ComposeUtils composeUtils = ComposeUtils.INSTANCE;
        String strStringResource = StringResources_androidKt.stringResource(R.string.paste, composer, 0);
        ImageVector contentPaste = ContentPasteKt.getContentPaste(Icons.INSTANCE.getDefault());
        composer.startReplaceGroup(511323305);
        boolean zChangedInstance = composer.changedInstance(clipboardManager);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue4 == companion4.getEmpty()) {
            mutableState = mutableState2;
            objRememberedValue4 = new Function0() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$AddRepoIntroContent$1$4$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AddRepoIntroScreenKt$AddRepoIntroContent$1$4.invoke$lambda$11$lambda$10$lambda$7$lambda$6(clipboardManager, mutableState);
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        } else {
            mutableState = mutableState2;
        }
        composer.endReplaceGroup();
        final MutableState mutableState3 = mutableState;
        composeUtils.m2969FDroidOutlineButtonyrwZFoE(strStringResource, (Function0) objRememberedValue4, null, contentPaste, 0L, composer, 196608, 20);
        SpacerKt.Spacer(RowScope.CC.weight$default(rowScopeInstance, companion2, 1.0f, false, 2, null), composer, 0);
        String strStringResource2 = StringResources_androidKt.stringResource(R.string.repo_add_add, composer, 0);
        composer.startReplaceGroup(511338474);
        boolean zChanged2 = composer.changed(function1);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue5 == companion4.getEmpty()) {
            objRememberedValue5 = new Function0() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$AddRepoIntroContent$1$4$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AddRepoIntroScreenKt$AddRepoIntroContent$1$4.invoke$lambda$11$lambda$10$lambda$9$lambda$8(function1, mutableState3);
                }
            };
            composer.updateRememberedValue(objRememberedValue5);
        }
        composer.endReplaceGroup();
        composeUtils.FDroidButton(strStringResource2, (Function0) objRememberedValue5, null, null, composer, 24576, 12);
        composer.endNode();
        composer.endNode();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$11$lambda$5$lambda$4(MutableState mutableState, TextFieldValue it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$11$lambda$1$lambda$0(Function1 function1, MutableState mutableState, KeyboardActionScope KeyboardActions) {
        Intrinsics.checkNotNullParameter(KeyboardActions, "$this$KeyboardActions");
        function1.invoke(((TextFieldValue) mutableState.getValue()).getText());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$11$lambda$3$lambda$2(FocusRequester focusRequester, LayoutCoordinates it) {
        Intrinsics.checkNotNullParameter(it, "it");
        focusRequester.requestFocus();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$11$lambda$10$lambda$7$lambda$6(ClipboardManager clipboardManager, MutableState mutableState) {
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
    public static final Unit invoke$lambda$11$lambda$10$lambda$9$lambda$8(Function1 function1, MutableState mutableState) {
        function1.invoke(((TextFieldValue) mutableState.getValue()).getText());
        return Unit.INSTANCE;
    }
}
