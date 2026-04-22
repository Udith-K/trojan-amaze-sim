package androidx.compose.foundation.text.selection;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.text.selection.SelectionMagnifierKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: SelectionMagnifier.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SelectionMagnifierKt {
    private static final SpringSpec MagnifierSpringSpec;
    private static final long OffsetDisplacementThreshold;
    private static final AnimationVector2D UnspecifiedAnimationVector2D = new AnimationVector2D(Float.NaN, Float.NaN);
    private static final TwoWayConverter UnspecifiedSafeOffsetVectorConverter = VectorConvertersKt.TwoWayConverter(new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$UnspecifiedSafeOffsetVectorConverter$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return m542invokek4lQ0M(((Offset) obj).m1168unboximpl());
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final AnimationVector2D m542invokek4lQ0M(long j) {
            if (!OffsetKt.m1173isSpecifiedk4lQ0M(j)) {
                return SelectionMagnifierKt.UnspecifiedAnimationVector2D;
            }
            return new AnimationVector2D(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j));
        }
    }, new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$UnspecifiedSafeOffsetVectorConverter$2
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return Offset.m1150boximpl(m543invoketuRUvjQ((AnimationVector2D) obj));
        }

        /* JADX INFO: renamed from: invoke-tuRUvjQ, reason: not valid java name */
        public final long m543invoketuRUvjQ(AnimationVector2D animationVector2D) {
            return OffsetKt.Offset(animationVector2D.getV1(), animationVector2D.getV2());
        }
    });

    static {
        long jOffset = OffsetKt.Offset(0.01f, 0.01f);
        OffsetDisplacementThreshold = jOffset;
        MagnifierSpringSpec = new SpringSpec(0.0f, 0.0f, Offset.m1150boximpl(jOffset), 3, null);
    }

    public static final SpringSpec getMagnifierSpringSpec() {
        return MagnifierSpringSpec;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionMagnifierKt$animatedSelectionMagnifier$1, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionMagnifier.kt */
    static final class AnonymousClass1 extends Lambda implements Function3 {
        final /* synthetic */ Function0 $magnifierCenter;
        final /* synthetic */ Function1 $platformMagnifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Function0 function0, Function1 function1) {
            super(3);
            this.$magnifierCenter = function0;
            this.$platformMagnifier = function1;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i) {
            composer.startReplaceGroup(759876635);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(759876635, i, -1, "androidx.compose.foundation.text.selection.animatedSelectionMagnifier.<anonymous> (SelectionMagnifier.kt:65)");
            }
            final State stateRememberAnimatedMagnifierPosition = SelectionMagnifierKt.rememberAnimatedMagnifierPosition(this.$magnifierCenter, composer, 0);
            Function1 function1 = this.$platformMagnifier;
            boolean zChanged = composer.changed(stateRememberAnimatedMagnifierPosition);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$animatedSelectionMagnifier$1$1$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        return Offset.m1150boximpl(m544invokeF1C5BW0());
                    }

                    /* JADX INFO: renamed from: invoke-F1C5BW0, reason: not valid java name */
                    public final long m544invokeF1C5BW0() {
                        return SelectionMagnifierKt.AnonymousClass1.invoke$lambda$0(stateRememberAnimatedMagnifierPosition);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Modifier modifier2 = (Modifier) function1.invoke((Function0) objRememberedValue);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return modifier2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long invoke$lambda$0(State state) {
            return ((Offset) state.getValue()).m1168unboximpl();
        }
    }

    public static final Modifier animatedSelectionMagnifier(Modifier modifier, Function0 function0, Function1 function1) {
        return ComposedModifierKt.composed$default(modifier, null, new AnonymousClass1(function0, function1), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final State rememberAnimatedMagnifierPosition(Function0 function0, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1589795249, i, -1, "androidx.compose.foundation.text.selection.rememberAnimatedMagnifierPosition (SelectionMagnifier.kt:76)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(function0);
            composer.updateRememberedValue(objRememberedValue);
        }
        State state = (State) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new Animatable(Offset.m1150boximpl(rememberAnimatedMagnifierPosition$lambda$1(state)), UnspecifiedSafeOffsetVectorConverter, Offset.m1150boximpl(OffsetDisplacementThreshold), null, 8, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        Animatable animatable = (Animatable) objRememberedValue2;
        Unit unit = Unit.INSTANCE;
        boolean zChangedInstance = composer.changedInstance(animatable);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1(state, animatable, null);
            composer.updateRememberedValue(objRememberedValue3);
        }
        EffectsKt.LaunchedEffect(unit, (Function2) objRememberedValue3, composer, 6);
        State stateAsState = animatable.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateAsState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long rememberAnimatedMagnifierPosition$lambda$1(State state) {
        return ((Offset) state.getValue()).m1168unboximpl();
    }
}
