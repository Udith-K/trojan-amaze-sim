package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextInclusionStrategy;

/* JADX INFO: compiled from: TextInclusionStrategy.kt */
/* JADX INFO: loaded from: classes.dex */
public interface TextInclusionStrategy {
    public static final Companion Companion = Companion.$$INSTANCE;

    boolean isIncluded(Rect rect, Rect rect2);

    /* JADX INFO: compiled from: TextInclusionStrategy.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final TextInclusionStrategy AnyOverlap = new TextInclusionStrategy() { // from class: androidx.compose.ui.text.TextInclusionStrategy$Companion$$ExternalSyntheticLambda0
            @Override // androidx.compose.ui.text.TextInclusionStrategy
            public final boolean isIncluded(Rect rect, Rect rect2) {
                return rect.overlaps(rect2);
            }
        };
        private static final TextInclusionStrategy ContainsAll = new TextInclusionStrategy() { // from class: androidx.compose.ui.text.TextInclusionStrategy$Companion$$ExternalSyntheticLambda1
            @Override // androidx.compose.ui.text.TextInclusionStrategy
            public final boolean isIncluded(Rect rect, Rect rect2) {
                return TextInclusionStrategy.Companion.ContainsAll$lambda$1(rect, rect2);
            }
        };
        private static final TextInclusionStrategy ContainsCenter = new TextInclusionStrategy() { // from class: androidx.compose.ui.text.TextInclusionStrategy$Companion$$ExternalSyntheticLambda2
            @Override // androidx.compose.ui.text.TextInclusionStrategy
            public final boolean isIncluded(Rect rect, Rect rect2) {
                return TextInclusionStrategy.Companion.ContainsCenter$lambda$2(rect, rect2);
            }
        };

        private Companion() {
        }

        public final TextInclusionStrategy getAnyOverlap() {
            return AnyOverlap;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean ContainsAll$lambda$1(Rect rect, Rect rect2) {
            return !rect2.isEmpty() && rect.getLeft() >= rect2.getLeft() && rect.getRight() <= rect2.getRight() && rect.getTop() >= rect2.getTop() && rect.getBottom() <= rect2.getBottom();
        }

        public final TextInclusionStrategy getContainsCenter() {
            return ContainsCenter;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean ContainsCenter$lambda$2(Rect rect, Rect rect2) {
            return rect2.m1176containsk4lQ0M(rect.m1178getCenterF1C5BW0());
        }
    }
}
