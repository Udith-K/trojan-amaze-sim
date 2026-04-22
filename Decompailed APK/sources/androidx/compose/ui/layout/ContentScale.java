package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Size;

/* JADX INFO: compiled from: ContentScale.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ContentScale {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: computeScaleFactor-H7hwNQA, reason: not valid java name */
    long mo1734computeScaleFactorH7hwNQA(long j, long j2);

    /* JADX INFO: compiled from: ContentScale.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final ContentScale Crop = new ContentScale() { // from class: androidx.compose.ui.layout.ContentScale$Companion$Crop$1
            @Override // androidx.compose.ui.layout.ContentScale
            /* JADX INFO: renamed from: computeScaleFactor-H7hwNQA */
            public long mo1734computeScaleFactorH7hwNQA(long j, long j2) {
                float fM1740computeFillMaxDimensioniLBOSCw = ContentScaleKt.m1740computeFillMaxDimensioniLBOSCw(j, j2);
                return ScaleFactorKt.ScaleFactor(fM1740computeFillMaxDimensioniLBOSCw, fM1740computeFillMaxDimensioniLBOSCw);
            }
        };
        private static final ContentScale Fit = new ContentScale() { // from class: androidx.compose.ui.layout.ContentScale$Companion$Fit$1
            @Override // androidx.compose.ui.layout.ContentScale
            /* JADX INFO: renamed from: computeScaleFactor-H7hwNQA */
            public long mo1734computeScaleFactorH7hwNQA(long j, long j2) {
                float fM1741computeFillMinDimensioniLBOSCw = ContentScaleKt.m1741computeFillMinDimensioniLBOSCw(j, j2);
                return ScaleFactorKt.ScaleFactor(fM1741computeFillMinDimensioniLBOSCw, fM1741computeFillMinDimensioniLBOSCw);
            }
        };
        private static final ContentScale FillHeight = new ContentScale() { // from class: androidx.compose.ui.layout.ContentScale$Companion$FillHeight$1
            @Override // androidx.compose.ui.layout.ContentScale
            /* JADX INFO: renamed from: computeScaleFactor-H7hwNQA */
            public long mo1734computeScaleFactorH7hwNQA(long j, long j2) {
                float fM1739computeFillHeightiLBOSCw = ContentScaleKt.m1739computeFillHeightiLBOSCw(j, j2);
                return ScaleFactorKt.ScaleFactor(fM1739computeFillHeightiLBOSCw, fM1739computeFillHeightiLBOSCw);
            }
        };
        private static final ContentScale FillWidth = new ContentScale() { // from class: androidx.compose.ui.layout.ContentScale$Companion$FillWidth$1
            @Override // androidx.compose.ui.layout.ContentScale
            /* JADX INFO: renamed from: computeScaleFactor-H7hwNQA */
            public long mo1734computeScaleFactorH7hwNQA(long j, long j2) {
                float fM1742computeFillWidthiLBOSCw = ContentScaleKt.m1742computeFillWidthiLBOSCw(j, j2);
                return ScaleFactorKt.ScaleFactor(fM1742computeFillWidthiLBOSCw, fM1742computeFillWidthiLBOSCw);
            }
        };
        private static final ContentScale Inside = new ContentScale() { // from class: androidx.compose.ui.layout.ContentScale$Companion$Inside$1
            @Override // androidx.compose.ui.layout.ContentScale
            /* JADX INFO: renamed from: computeScaleFactor-H7hwNQA */
            public long mo1734computeScaleFactorH7hwNQA(long j, long j2) {
                if (Size.m1196getWidthimpl(j) > Size.m1196getWidthimpl(j2) || Size.m1194getHeightimpl(j) > Size.m1194getHeightimpl(j2)) {
                    float fM1741computeFillMinDimensioniLBOSCw = ContentScaleKt.m1741computeFillMinDimensioniLBOSCw(j, j2);
                    return ScaleFactorKt.ScaleFactor(fM1741computeFillMinDimensioniLBOSCw, fM1741computeFillMinDimensioniLBOSCw);
                }
                return ScaleFactorKt.ScaleFactor(1.0f, 1.0f);
            }
        };
        private static final FixedScale None = new FixedScale(1.0f);
        private static final ContentScale FillBounds = new ContentScale() { // from class: androidx.compose.ui.layout.ContentScale$Companion$FillBounds$1
            @Override // androidx.compose.ui.layout.ContentScale
            /* JADX INFO: renamed from: computeScaleFactor-H7hwNQA */
            public long mo1734computeScaleFactorH7hwNQA(long j, long j2) {
                return ScaleFactorKt.ScaleFactor(ContentScaleKt.m1742computeFillWidthiLBOSCw(j, j2), ContentScaleKt.m1739computeFillHeightiLBOSCw(j, j2));
            }
        };

        private Companion() {
        }

        public final ContentScale getCrop() {
            return Crop;
        }

        public final ContentScale getFit() {
            return Fit;
        }

        public final ContentScale getInside() {
            return Inside;
        }

        public final FixedScale getNone() {
            return None;
        }
    }
}
