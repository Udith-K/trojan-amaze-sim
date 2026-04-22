package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;

/* JADX INFO: compiled from: WindowInsets.kt */
/* JADX INFO: loaded from: classes.dex */
public interface WindowInsets {
    public static final Companion Companion = Companion.$$INSTANCE;

    int getBottom(Density density);

    int getLeft(Density density, LayoutDirection layoutDirection);

    int getRight(Density density, LayoutDirection layoutDirection);

    int getTop(Density density);

    /* JADX INFO: compiled from: WindowInsets.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
