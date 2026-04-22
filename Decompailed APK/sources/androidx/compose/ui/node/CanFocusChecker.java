package androidx.compose.ui.node;

import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.internal.InlineClassHelperKt;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: NodeKind.kt */
/* JADX INFO: loaded from: classes.dex */
final class CanFocusChecker implements FocusProperties {
    public static final CanFocusChecker INSTANCE = new CanFocusChecker();
    private static Boolean canFocusValue;

    @Override // androidx.compose.ui.focus.FocusProperties
    public /* synthetic */ FocusRequester getDown() {
        return FocusRequester.Companion.getDefault();
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public /* synthetic */ FocusRequester getEnd() {
        return FocusRequester.Companion.getDefault();
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public /* synthetic */ Function1 getEnter() {
        return new Function1() { // from class: androidx.compose.ui.focus.FocusProperties$enter$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m1117invoke3ESFkO8(((FocusDirection) obj).m1094unboximpl());
            }

            /* JADX INFO: renamed from: invoke-3ESFkO8, reason: not valid java name */
            public final FocusRequester m1117invoke3ESFkO8(int i) {
                return FocusRequester.Companion.getDefault();
            }
        };
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public /* synthetic */ Function1 getExit() {
        return new Function1() { // from class: androidx.compose.ui.focus.FocusProperties$exit$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m1118invoke3ESFkO8(((FocusDirection) obj).m1094unboximpl());
            }

            /* JADX INFO: renamed from: invoke-3ESFkO8, reason: not valid java name */
            public final FocusRequester m1118invoke3ESFkO8(int i) {
                return FocusRequester.Companion.getDefault();
            }
        };
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public /* synthetic */ FocusRequester getLeft() {
        return FocusRequester.Companion.getDefault();
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public /* synthetic */ FocusRequester getNext() {
        return FocusRequester.Companion.getDefault();
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public /* synthetic */ FocusRequester getPrevious() {
        return FocusRequester.Companion.getDefault();
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public /* synthetic */ FocusRequester getRight() {
        return FocusRequester.Companion.getDefault();
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public /* synthetic */ FocusRequester getStart() {
        return FocusRequester.Companion.getDefault();
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public /* synthetic */ FocusRequester getUp() {
        return FocusRequester.Companion.getDefault();
    }

    private CanFocusChecker() {
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public boolean getCanFocus() {
        Boolean bool = canFocusValue;
        if (bool == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("canFocus is read before it is written");
            throw new KotlinNothingValueException();
        }
        return bool.booleanValue();
    }

    @Override // androidx.compose.ui.focus.FocusProperties
    public void setCanFocus(boolean z) {
        canFocusValue = Boolean.valueOf(z);
    }

    public final boolean isCanFocusSet() {
        return canFocusValue != null;
    }

    public final void reset() {
        canFocusValue = null;
    }
}
