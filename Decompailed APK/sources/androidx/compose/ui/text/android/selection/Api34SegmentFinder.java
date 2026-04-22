package androidx.compose.ui.text.android.selection;

import androidx.compose.ui.text.android.AndroidLayoutApi34$$ExternalSyntheticApiModelOutline2;

/* JADX INFO: compiled from: SegmentFinder.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Api34SegmentFinder {
    public static final Api34SegmentFinder INSTANCE = new Api34SegmentFinder();

    private Api34SegmentFinder() {
    }

    public final android.text.SegmentFinder toAndroidSegmentFinder$ui_text_release(final SegmentFinder segmentFinder) {
        return AndroidLayoutApi34$$ExternalSyntheticApiModelOutline2.m(new android.text.SegmentFinder() { // from class: androidx.compose.ui.text.android.selection.Api34SegmentFinder$toAndroidSegmentFinder$1
            public int previousStartBoundary(int i) {
                return segmentFinder.previousStartBoundary(i);
            }

            public int previousEndBoundary(int i) {
                return segmentFinder.previousEndBoundary(i);
            }

            public int nextStartBoundary(int i) {
                return segmentFinder.nextStartBoundary(i);
            }

            public int nextEndBoundary(int i) {
                return segmentFinder.nextEndBoundary(i);
            }
        });
    }
}
