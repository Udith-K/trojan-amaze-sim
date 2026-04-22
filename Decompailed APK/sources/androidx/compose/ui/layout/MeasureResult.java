package androidx.compose.ui.layout;

import java.util.Map;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: MeasureResult.kt */
/* JADX INFO: loaded from: classes.dex */
public interface MeasureResult {
    Map getAlignmentLines();

    int getHeight();

    Function1 getRulers();

    int getWidth();

    void placeChildren();

    /* JADX INFO: renamed from: androidx.compose.ui.layout.MeasureResult$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: MeasureResult.kt */
    public abstract /* synthetic */ class CC {
        public static Function1 $default$getRulers(MeasureResult measureResult) {
            return null;
        }
    }
}
