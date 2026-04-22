package androidx.compose.ui.focus;

import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: FocusProperties.kt */
/* JADX INFO: loaded from: classes.dex */
public interface FocusProperties {
    boolean getCanFocus();

    FocusRequester getDown();

    FocusRequester getEnd();

    Function1 getEnter();

    Function1 getExit();

    FocusRequester getLeft();

    FocusRequester getNext();

    FocusRequester getPrevious();

    FocusRequester getRight();

    FocusRequester getStart();

    FocusRequester getUp();

    void setCanFocus(boolean z);

    /* JADX INFO: renamed from: androidx.compose.ui.focus.FocusProperties$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: FocusProperties.kt */
    public abstract /* synthetic */ class CC {
    }
}
