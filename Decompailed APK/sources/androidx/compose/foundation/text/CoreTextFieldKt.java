package androidx.compose.foundation.text;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.relocation.BringIntoViewRequester;
import androidx.compose.foundation.text.selection.OffsetProvider;
import androidx.compose.foundation.text.selection.SelectionHandleAnchor;
import androidx.compose.foundation.text.selection.SelectionHandleInfo;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoreTextField.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CoreTextFieldKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CoreTextField$lambda$11(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x012a A[PHI: r23
  0x012a: PHI (r23v23 int) = (r23v0 int), (r23v3 int), (r23v4 int) binds: [B:100:0x0128, B:108:0x013e, B:107:0x013b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x03d4  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x043d  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x056d  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x0587  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0589  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0597  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0599  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x05a8  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x05aa  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x05b6  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x05c6  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x05d1 A[PHI: r3 r40 r44
  0x05d1: PHI (r3v22 androidx.compose.ui.text.input.ImeOptions) = (r3v23 androidx.compose.ui.text.input.ImeOptions), (r3v24 androidx.compose.ui.text.input.ImeOptions) binds: [B:319:0x05cf, B:316:0x05c1] A[DONT_GENERATE, DONT_INLINE]
  0x05d1: PHI (r40v2 int) = (r40v3 int), (r40v5 int) binds: [B:319:0x05cf, B:316:0x05c1] A[DONT_GENERATE, DONT_INLINE]
  0x05d1: PHI (r44v2 int) = (r44v3 int), (r44v4 int) binds: [B:319:0x05cf, B:316:0x05c1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:321:0x05d3  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x05f5  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0621  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x065b  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x0664  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x066a  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0697  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x06b1  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x06b3  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x06bd  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x06bf  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x06d7  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0717  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x0719  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0730  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x0748  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x074a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x075f  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0761  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0774  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0799  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x079b  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x07a1  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x07a3  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x07af  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x07b1  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x07da  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x07e0  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0817  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x0838  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0855  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x0857  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x0871  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x087b  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0899  */
    /* JADX WARN: Removed duplicated region for block: B:450:0x089c  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x08e5  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x08eb  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x094b  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x094f  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x0956  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x09b5  */
    /* JADX WARN: Removed duplicated region for block: B:483:0x09da  */
    /* JADX WARN: Removed duplicated region for block: B:485:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void CoreTextField(final androidx.compose.ui.text.input.TextFieldValue r54, final kotlin.jvm.functions.Function1 r55, androidx.compose.ui.Modifier r56, androidx.compose.ui.text.TextStyle r57, androidx.compose.ui.text.input.VisualTransformation r58, kotlin.jvm.functions.Function1 r59, androidx.compose.foundation.interaction.MutableInteractionSource r60, androidx.compose.ui.graphics.Brush r61, boolean r62, int r63, int r64, androidx.compose.ui.text.input.ImeOptions r65, androidx.compose.foundation.text.KeyboardActions r66, boolean r67, boolean r68, kotlin.jvm.functions.Function3 r69, androidx.compose.runtime.Composer r70, final int r71, final int r72, final int r73) {
        /*
            Method dump skipped, instruction units count: 2550
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, boolean, int, int, androidx.compose.ui.text.input.ImeOptions, androidx.compose.foundation.text.KeyboardActions, boolean, boolean, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CoreTextFieldRootBox(final Modifier modifier, final TextFieldSelectionManager textFieldSelectionManager, final Function2 function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-20551815);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(textFieldSelectionManager) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-20551815, i2, -1, "androidx.compose.foundation.text.CoreTextFieldRootBox (CoreTextField.kt:801)");
            }
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
            ComposeUiNode.Companion companion = ComposeUiNode.Companion;
            Function0 constructor = companion.getConstructor();
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
            Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ContextMenu_androidKt.ContextMenuArea(textFieldSelectionManager, function2, composerStartRestartGroup, (i2 >> 3) & 126);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextFieldRootBox.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    CoreTextFieldKt.CoreTextFieldRootBox(modifier, textFieldSelectionManager, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    private static final Modifier previewKeyEventToDeselectOnBack(Modifier modifier, final LegacyTextFieldState legacyTextFieldState, final TextFieldSelectionManager textFieldSelectionManager) {
        return KeyInputModifierKt.onPreviewKeyEvent(modifier, new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.previewKeyEventToDeselectOnBack.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m382invokeZmokQxo(((KeyEvent) obj).m1620unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m382invokeZmokQxo(android.view.KeyEvent keyEvent) {
                boolean z;
                if (legacyTextFieldState.getHandleState() == HandleState.Selection && KeyEventHelpers_androidKt.m384cancelsTextSelectionZmokQxo(keyEvent)) {
                    z = true;
                    TextFieldSelectionManager.m552deselect_kEHs6E$foundation_release$default(textFieldSelectionManager, null, 1, null);
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tapToFocus(LegacyTextFieldState legacyTextFieldState, FocusRequester focusRequester, boolean z) {
        SoftwareKeyboardController keyboardController;
        if (!legacyTextFieldState.getHasFocus()) {
            focusRequester.requestFocus();
        } else {
            if (!z || (keyboardController = legacyTextFieldState.getKeyboardController()) == null) {
                return;
            }
            keyboardController.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startInputSession(TextInputService textInputService, LegacyTextFieldState legacyTextFieldState, TextFieldValue textFieldValue, ImeOptions imeOptions, OffsetMapping offsetMapping) {
        legacyTextFieldState.setInputSession(TextFieldDelegate.Companion.onFocus$foundation_release(textInputService, textFieldValue, legacyTextFieldState.getProcessor(), imeOptions, legacyTextFieldState.getOnValueChange(), legacyTextFieldState.getOnImeActionPerformed()));
        notifyFocusedRect(legacyTextFieldState, textFieldValue, offsetMapping);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void endInputSession(LegacyTextFieldState legacyTextFieldState) {
        TextInputSession inputSession = legacyTextFieldState.getInputSession();
        if (inputSession != null) {
            TextFieldDelegate.Companion.onBlur$foundation_release(inputSession, legacyTextFieldState.getProcessor(), legacyTextFieldState.getOnValueChange());
        }
        legacyTextFieldState.setInputSession(null);
    }

    public static final Object bringSelectionEndIntoView(BringIntoViewRequester bringIntoViewRequester, TextFieldValue textFieldValue, TextDelegate textDelegate, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, Continuation continuation) {
        Rect rect;
        int iOriginalToTransformed = offsetMapping.originalToTransformed(TextRange.m2114getMaximpl(textFieldValue.m2239getSelectiond9O1mEE()));
        if (iOriginalToTransformed < textLayoutResult.getLayoutInput().getText().length()) {
            rect = textLayoutResult.getBoundingBox(iOriginalToTransformed);
        } else if (iOriginalToTransformed != 0) {
            rect = textLayoutResult.getBoundingBox(iOriginalToTransformed - 1);
        } else {
            rect = new Rect(0.0f, 0.0f, 1.0f, IntSize.m2475getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate.getStyle(), textDelegate.getDensity(), textDelegate.getFontFamilyResolver(), null, 0, 24, null)));
        }
        Object objBringIntoView = bringIntoViewRequester.bringIntoView(rect, continuation);
        return objBringIntoView == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBringIntoView : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SelectionToolbarAndHandles(final TextFieldSelectionManager textFieldSelectionManager, final boolean z, Composer composer, final int i) {
        int i2;
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        Composer composerStartRestartGroup = composer.startRestartGroup(626339208);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(textFieldSelectionManager) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(626339208, i2, -1, "androidx.compose.foundation.text.SelectionToolbarAndHandles (CoreTextField.kt:1146)");
            }
            if (z) {
                composerStartRestartGroup.startReplaceGroup(-1286242594);
                LegacyTextFieldState state$foundation_release = textFieldSelectionManager.getState$foundation_release();
                TextLayoutResult textLayoutResult = null;
                if (state$foundation_release != null && (layoutResult = state$foundation_release.getLayoutResult()) != null && (value = layoutResult.getValue()) != null) {
                    LegacyTextFieldState state$foundation_release2 = textFieldSelectionManager.getState$foundation_release();
                    if (!(state$foundation_release2 != null ? state$foundation_release2.isLayoutResultStale() : true)) {
                        textLayoutResult = value;
                    }
                }
                if (textLayoutResult == null) {
                    composerStartRestartGroup.startReplaceGroup(-1285984396);
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1285984395);
                    if (!TextRange.m2111getCollapsedimpl(textFieldSelectionManager.getValue$foundation_release().m2239getSelectiond9O1mEE())) {
                        composerStartRestartGroup.startReplaceGroup(-1680616096);
                        int iOriginalToTransformed = textFieldSelectionManager.getOffsetMapping$foundation_release().originalToTransformed(TextRange.m2117getStartimpl(textFieldSelectionManager.getValue$foundation_release().m2239getSelectiond9O1mEE()));
                        int iOriginalToTransformed2 = textFieldSelectionManager.getOffsetMapping$foundation_release().originalToTransformed(TextRange.m2112getEndimpl(textFieldSelectionManager.getValue$foundation_release().m2239getSelectiond9O1mEE()));
                        ResolvedTextDirection bidiRunDirection = textLayoutResult.getBidiRunDirection(iOriginalToTransformed);
                        ResolvedTextDirection bidiRunDirection2 = textLayoutResult.getBidiRunDirection(Math.max(iOriginalToTransformed2 - 1, 0));
                        LegacyTextFieldState state$foundation_release3 = textFieldSelectionManager.getState$foundation_release();
                        if (state$foundation_release3 != null && state$foundation_release3.getShowSelectionHandleStart()) {
                            composerStartRestartGroup.startReplaceGroup(-1680216289);
                            TextFieldSelectionManagerKt.TextFieldSelectionHandle(true, bidiRunDirection, textFieldSelectionManager, composerStartRestartGroup, ((i2 << 6) & 896) | 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1679975078);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        LegacyTextFieldState state$foundation_release4 = textFieldSelectionManager.getState$foundation_release();
                        if (state$foundation_release4 != null && state$foundation_release4.getShowSelectionHandleEnd()) {
                            composerStartRestartGroup.startReplaceGroup(-1679895904);
                            TextFieldSelectionManagerKt.TextFieldSelectionHandle(false, bidiRunDirection2, textFieldSelectionManager, composerStartRestartGroup, ((i2 << 6) & 896) | 6);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(-1679655654);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1679637798);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    LegacyTextFieldState state$foundation_release5 = textFieldSelectionManager.getState$foundation_release();
                    if (state$foundation_release5 != null) {
                        if (textFieldSelectionManager.isTextChanged$foundation_release()) {
                            state$foundation_release5.setShowFloatingToolbar(false);
                        }
                        if (state$foundation_release5.getHasFocus()) {
                            if (state$foundation_release5.getShowFloatingToolbar()) {
                                textFieldSelectionManager.showSelectionToolbar$foundation_release();
                            } else {
                                textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }
                composerStartRestartGroup.endReplaceGroup();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(651305535);
                composerStartRestartGroup.endReplaceGroup();
                textFieldSelectionManager.hideSelectionToolbar$foundation_release();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.SelectionToolbarAndHandles.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    CoreTextFieldKt.SelectionToolbarAndHandles(textFieldSelectionManager, z, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final void TextFieldCursorHandle(final TextFieldSelectionManager textFieldSelectionManager, Composer composer, final int i) {
        int i2;
        AnnotatedString transformedText$foundation_release;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1436003720);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(textFieldSelectionManager) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1436003720, i2, -1, "androidx.compose.foundation.text.TextFieldCursorHandle (CoreTextField.kt:1189)");
            }
            LegacyTextFieldState state$foundation_release = textFieldSelectionManager.getState$foundation_release();
            if (state$foundation_release == null || !state$foundation_release.getShowCursorHandle() || (transformedText$foundation_release = textFieldSelectionManager.getTransformedText$foundation_release()) == null || transformedText$foundation_release.length() <= 0) {
                composerStartRestartGroup.startReplaceGroup(-284257090);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-285446808);
                boolean zChanged = composerStartRestartGroup.changed(textFieldSelectionManager);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = textFieldSelectionManager.cursorDragObserver$foundation_release();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                TextDragObserver textDragObserver = (TextDragObserver) objRememberedValue;
                final long jM557getCursorPositiontuRUvjQ$foundation_release = textFieldSelectionManager.m557getCursorPositiontuRUvjQ$foundation_release((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity()));
                boolean zChanged2 = composerStartRestartGroup.changed(jM557getCursorPositiontuRUvjQ$foundation_release);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                    objRememberedValue2 = new OffsetProvider() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1
                        @Override // androidx.compose.foundation.text.selection.OffsetProvider
                        /* JADX INFO: renamed from: provide-F1C5BW0, reason: not valid java name */
                        public final long mo380provideF1C5BW0() {
                            return jM557getCursorPositiontuRUvjQ$foundation_release;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                OffsetProvider offsetProvider = (OffsetProvider) objRememberedValue2;
                Modifier.Companion companion = Modifier.Companion;
                boolean zChangedInstance = composerStartRestartGroup.changedInstance(textDragObserver) | composerStartRestartGroup.changedInstance(textFieldSelectionManager);
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || objRememberedValue3 == Composer.Companion.getEmpty()) {
                    objRememberedValue3 = new CoreTextFieldKt$TextFieldCursorHandle$2$1(textDragObserver, textFieldSelectionManager, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, textDragObserver, (Function2) objRememberedValue3);
                boolean zChanged3 = composerStartRestartGroup.changed(jM557getCursorPositiontuRUvjQ$foundation_release);
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged3 || objRememberedValue4 == Composer.Companion.getEmpty()) {
                    objRememberedValue4 = new Function1() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((SemanticsPropertyReceiver) obj);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            semanticsPropertyReceiver.set(SelectionHandlesKt.getSelectionHandleInfoKey(), new SelectionHandleInfo(Handle.Cursor, jM557getCursorPositiontuRUvjQ$foundation_release, SelectionHandleAnchor.Middle, true, null));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                AndroidCursorHandle_androidKt.m370CursorHandleUSBMPiE(offsetProvider, SemanticsModifierKt.semantics$default(modifierPointerInput, false, (Function1) objRememberedValue4, 1, null), 0L, composerStartRestartGroup, 0, 4);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.TextFieldCursorHandle.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    CoreTextFieldKt.TextFieldCursorHandle(textFieldSelectionManager, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyFocusedRect(LegacyTextFieldState legacyTextFieldState, TextFieldValue textFieldValue, OffsetMapping offsetMapping) {
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
            if (layoutResult == null) {
                return;
            }
            TextInputSession inputSession = legacyTextFieldState.getInputSession();
            if (inputSession == null) {
                return;
            }
            LayoutCoordinates layoutCoordinates = legacyTextFieldState.getLayoutCoordinates();
            if (layoutCoordinates == null) {
                return;
            }
            TextFieldDelegate.Companion.notifyFocusedRect$foundation_release(textFieldValue, legacyTextFieldState.getTextDelegate(), layoutResult.getValue(), layoutCoordinates, inputSession, legacyTextFieldState.getHasFocus(), offsetMapping);
            Unit unit = Unit.INSTANCE;
        } finally {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
        }
    }
}
