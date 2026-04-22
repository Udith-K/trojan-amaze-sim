package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.PlatformMagnifierFactory;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.contextmenu.ContextMenuState_androidKt;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: TextFieldSelectionManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextFieldSelectionManager_androidKt {
    public static final boolean isShiftPressed(PointerEvent pointerEvent) {
        return false;
    }

    public static final Modifier textFieldMagnifier(Modifier modifier, TextFieldSelectionManager textFieldSelectionManager) {
        return !Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null) ? modifier : ComposedModifierKt.composed$default(modifier, null, new C01251(textFieldSelectionManager), 1, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionManager.android.kt */
    static final class C01251 extends Lambda implements Function3 {
        final /* synthetic */ TextFieldSelectionManager $manager;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01251(TextFieldSelectionManager textFieldSelectionManager) {
            super(3);
            this.$manager = textFieldSelectionManager;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i) {
            composer.startReplaceGroup(1980580247);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1980580247, i, -1, "androidx.compose.foundation.text.selection.textFieldMagnifier.<anonymous> (TextFieldSelectionManager.android.kt:48)");
            }
            final Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntSize.m2471boximpl(IntSize.Companion.m2480getZeroYbymL2g()), null, 2, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            boolean zChangedInstance = composer.changedInstance(this.$manager);
            final TextFieldSelectionManager textFieldSelectionManager = this.$manager;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        return Offset.m1150boximpl(m570invokeF1C5BW0());
                    }

                    /* JADX INFO: renamed from: invoke-F1C5BW0, reason: not valid java name */
                    public final long m570invokeF1C5BW0() {
                        return TextFieldSelectionManagerKt.m565calculateSelectionMagnifierCenterAndroidO0kMr_c(textFieldSelectionManager, TextFieldSelectionManager_androidKt.C01251.invoke$lambda$1(mutableState));
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            Function0 function0 = (Function0) objRememberedValue2;
            boolean zChanged = composer.changed(density);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Modifier invoke(final Function0 function02) {
                        Modifier.Companion companion2 = Modifier.Companion;
                        Function1 function1 = new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$2$1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                return Offset.m1150boximpl(m571invoketuRUvjQ((Density) obj));
                            }

                            /* JADX INFO: renamed from: invoke-tuRUvjQ, reason: not valid java name */
                            public final long m571invoketuRUvjQ(Density density2) {
                                return ((Offset) function02.invoke()).m1168unboximpl();
                            }
                        };
                        final Density density2 = density;
                        final MutableState mutableState2 = mutableState;
                        return Magnifier_androidKt.m139magnifierjPUL71Q(companion2, function1, (490 & 2) != 0 ? null : null, (490 & 4) != 0 ? null : new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$textFieldMagnifier$1$2$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                m572invokeEaSLcWc(((DpSize) obj).m2446unboximpl());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-EaSLcWc, reason: not valid java name */
                            public final void m572invokeEaSLcWc(long j) {
                                MutableState mutableState3 = mutableState2;
                                Density density3 = density2;
                                TextFieldSelectionManager_androidKt.C01251.invoke$lambda$2(mutableState3, IntSizeKt.IntSize(density3.mo204roundToPx0680j_4(DpSize.m2443getWidthD9Ej5fM(j)), density3.mo204roundToPx0680j_4(DpSize.m2442getHeightD9Ej5fM(j))));
                            }
                        }, (490 & 8) != 0 ? Float.NaN : 0.0f, (490 & 16) != 0 ? false : true, (490 & 32) != 0 ? DpSize.Companion.m2447getUnspecifiedMYxV2XQ() : 0L, (490 & 64) != 0 ? Dp.Companion.m2425getUnspecifiedD9Ej5fM() : 0.0f, (490 & 128) != 0 ? Dp.Companion.m2425getUnspecifiedD9Ej5fM() : 0.0f, (490 & 256) != 0, (490 & 512) == 0 ? PlatformMagnifierFactory.Companion.getForCurrentPlatform() : null);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifierAnimatedSelectionMagnifier = SelectionMagnifierKt.animatedSelectionMagnifier(modifier, function0, (Function1) objRememberedValue3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return modifierAnimatedSelectionMagnifier;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2(MutableState mutableState, long j) {
            mutableState.setValue(IntSize.m2471boximpl(j));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long invoke$lambda$1(MutableState mutableState) {
            return ((IntSize) mutableState.getValue()).m2479unboximpl();
        }
    }

    public static final Function1 contextMenuBuilder(final TextFieldSelectionManager textFieldSelectionManager, final ContextMenuState contextMenuState) {
        return new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt.contextMenuBuilder.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((ContextMenuScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(ContextMenuScope contextMenuScope) {
                ClipboardManager clipboardManager$foundation_release;
                textFieldSelectionManager.getVisualTransformation$foundation_release();
                boolean zM2111getCollapsedimpl = TextRange.m2111getCollapsedimpl(textFieldSelectionManager.getValue$foundation_release().m2239getSelectiond9O1mEE());
                final ContextMenuState contextMenuState2 = contextMenuState;
                final TextContextMenuItems textContextMenuItems = TextContextMenuItems.Cut;
                boolean z = !zM2111getCollapsedimpl && textFieldSelectionManager.getEditable();
                final TextFieldSelectionManager textFieldSelectionManager2 = textFieldSelectionManager;
                ContextMenuScope.item$default(contextMenuScope, new Function2() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        return invoke((Composer) obj, ((Number) obj2).intValue());
                    }

                    public final String invoke(Composer composer, int i) {
                        composer.startReplaceGroup(-1451087197);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1451087197, i, -1, "androidx.compose.foundation.text.TextItem.<anonymous> (ContextMenu.android.kt:98)");
                        }
                        String strResolvedString = textContextMenuItems.resolvedString(composer, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer.endReplaceGroup();
                        return strResolvedString;
                    }
                }, null, z, null, new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m566invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m566invoke() {
                        textFieldSelectionManager2.cut$foundation_release();
                        ContextMenuState_androidKt.close(contextMenuState2);
                    }
                }, 10, null);
                final ContextMenuState contextMenuState3 = contextMenuState;
                final TextContextMenuItems textContextMenuItems2 = TextContextMenuItems.Copy;
                boolean z2 = !zM2111getCollapsedimpl;
                final TextFieldSelectionManager textFieldSelectionManager3 = textFieldSelectionManager;
                ContextMenuScope.item$default(contextMenuScope, new Function2() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        return invoke((Composer) obj, ((Number) obj2).intValue());
                    }

                    public final String invoke(Composer composer, int i) {
                        composer.startReplaceGroup(-1451087197);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1451087197, i, -1, "androidx.compose.foundation.text.TextItem.<anonymous> (ContextMenu.android.kt:98)");
                        }
                        String strResolvedString = textContextMenuItems2.resolvedString(composer, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer.endReplaceGroup();
                        return strResolvedString;
                    }
                }, null, z2, null, new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m567invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m567invoke() {
                        textFieldSelectionManager3.copy$foundation_release(false);
                        ContextMenuState_androidKt.close(contextMenuState3);
                    }
                }, 10, null);
                final ContextMenuState contextMenuState4 = contextMenuState;
                final TextContextMenuItems textContextMenuItems3 = TextContextMenuItems.Paste;
                boolean z3 = textFieldSelectionManager.getEditable() && (clipboardManager$foundation_release = textFieldSelectionManager.getClipboardManager$foundation_release()) != null && clipboardManager$foundation_release.hasText();
                final TextFieldSelectionManager textFieldSelectionManager4 = textFieldSelectionManager;
                ContextMenuScope.item$default(contextMenuScope, new Function2() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        return invoke((Composer) obj, ((Number) obj2).intValue());
                    }

                    public final String invoke(Composer composer, int i) {
                        composer.startReplaceGroup(-1451087197);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1451087197, i, -1, "androidx.compose.foundation.text.TextItem.<anonymous> (ContextMenu.android.kt:98)");
                        }
                        String strResolvedString = textContextMenuItems3.resolvedString(composer, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer.endReplaceGroup();
                        return strResolvedString;
                    }
                }, null, z3, null, new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m568invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m568invoke() {
                        textFieldSelectionManager4.paste$foundation_release();
                        ContextMenuState_androidKt.close(contextMenuState4);
                    }
                }, 10, null);
                final ContextMenuState contextMenuState5 = contextMenuState;
                final TextContextMenuItems textContextMenuItems4 = TextContextMenuItems.SelectAll;
                boolean z4 = TextRange.m2113getLengthimpl(textFieldSelectionManager.getValue$foundation_release().m2239getSelectiond9O1mEE()) != textFieldSelectionManager.getValue$foundation_release().getText().length();
                final TextFieldSelectionManager textFieldSelectionManager5 = textFieldSelectionManager;
                ContextMenuScope.item$default(contextMenuScope, new Function2() { // from class: androidx.compose.foundation.text.ContextMenu_androidKt$TextItem$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        return invoke((Composer) obj, ((Number) obj2).intValue());
                    }

                    public final String invoke(Composer composer, int i) {
                        composer.startReplaceGroup(-1451087197);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1451087197, i, -1, "androidx.compose.foundation.text.TextItem.<anonymous> (ContextMenu.android.kt:98)");
                        }
                        String strResolvedString = textContextMenuItems4.resolvedString(composer, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer.endReplaceGroup();
                        return strResolvedString;
                    }
                }, null, z4, null, new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$contextMenuBuilder$1$invoke$$inlined$TextItem$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m569invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m569invoke() {
                        textFieldSelectionManager5.selectAll$foundation_release();
                        ContextMenuState_androidKt.close(contextMenuState5);
                    }
                }, 10, null);
            }
        };
    }
}
