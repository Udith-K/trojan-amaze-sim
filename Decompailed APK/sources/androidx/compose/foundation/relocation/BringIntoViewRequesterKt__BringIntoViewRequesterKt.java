package androidx.compose.foundation.relocation;

import androidx.compose.ui.Modifier;

/* JADX INFO: compiled from: BringIntoViewRequester.kt */
/* JADX INFO: loaded from: classes.dex */
abstract /* synthetic */ class BringIntoViewRequesterKt__BringIntoViewRequesterKt {
    public static final BringIntoViewRequester BringIntoViewRequester() {
        return new BringIntoViewRequesterImpl();
    }

    public static final Modifier bringIntoViewRequester(Modifier modifier, BringIntoViewRequester bringIntoViewRequester) {
        return modifier.then(new BringIntoViewRequesterElement(bringIntoViewRequester));
    }
}
