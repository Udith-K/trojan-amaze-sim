package androidx.compose.runtime.saveable;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.text.CharsKt;

/* JADX INFO: compiled from: SaveableStateRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SaveableStateRegistryKt {
    private static final ProvidableCompositionLocal LocalSaveableStateRegistry = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.runtime.saveable.SaveableStateRegistryKt$LocalSaveableStateRegistry$1
        @Override // kotlin.jvm.functions.Function0
        public final SaveableStateRegistry invoke() {
            return null;
        }
    });

    public static final SaveableStateRegistry SaveableStateRegistry(Map map, Function1 function1) {
        return new SaveableStateRegistryImpl(map, function1);
    }

    public static final ProvidableCompositionLocal getLocalSaveableStateRegistry() {
        return LocalSaveableStateRegistry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean fastIsBlank(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!CharsKt.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
