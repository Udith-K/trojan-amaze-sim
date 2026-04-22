package androidx.compose.foundation.text.input.internal;

import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: LegacyPlatformTextInputServiceAdapter.android.kt */
/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class LegacyPlatformTextInputServiceAdapter_androidKt$inputMethodManagerFactory$1 extends FunctionReferenceImpl implements Function1 {
    public static final LegacyPlatformTextInputServiceAdapter_androidKt$inputMethodManagerFactory$1 INSTANCE = new LegacyPlatformTextInputServiceAdapter_androidKt$inputMethodManagerFactory$1();

    LegacyPlatformTextInputServiceAdapter_androidKt$inputMethodManagerFactory$1() {
        super(1, InputMethodManagerImpl.class, "<init>", "<init>(Landroid/view/View;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final InputMethodManagerImpl invoke(View view) {
        return new InputMethodManagerImpl(view);
    }
}
