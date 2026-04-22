package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.EditorInfo;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: EditorInfo.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class EditorInfoApi34 {
    public static final EditorInfoApi34 INSTANCE = new EditorInfoApi34();

    private EditorInfoApi34() {
    }

    public final void setHandwritingGestures(EditorInfo editorInfo) {
        editorInfo.setSupportedHandwritingGestures(CollectionsKt.listOf((Object[]) new Class[]{EditorInfoApi34$$ExternalSyntheticApiModelOutline0.m(), EditorInfoApi34$$ExternalSyntheticApiModelOutline4.m(), EditorInfoApi34$$ExternalSyntheticApiModelOutline1.m(), EditorInfoApi34$$ExternalSyntheticApiModelOutline2.m(), EditorInfoApi34$$ExternalSyntheticApiModelOutline5.m(), EditorInfoApi34$$ExternalSyntheticApiModelOutline6.m(), EditorInfoApi34$$ExternalSyntheticApiModelOutline7.m()}));
        editorInfo.setSupportedHandwritingGesturePreviews(SetsKt.setOf((Object[]) new Class[]{EditorInfoApi34$$ExternalSyntheticApiModelOutline0.m(), EditorInfoApi34$$ExternalSyntheticApiModelOutline4.m(), EditorInfoApi34$$ExternalSyntheticApiModelOutline1.m(), EditorInfoApi34$$ExternalSyntheticApiModelOutline2.m()}));
    }
}
