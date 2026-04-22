package androidx.compose.ui.platform;

import java.text.BreakIterator;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AccessibilityIterators.android.kt */
/* JADX INFO: loaded from: classes.dex */
public class AccessibilityIterators$CharacterTextSegmentIterator extends AccessibilityIterators$AbstractTextSegmentIterator {
    private static AccessibilityIterators$CharacterTextSegmentIterator instance;
    private BreakIterator impl;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ AccessibilityIterators$CharacterTextSegmentIterator(Locale locale, DefaultConstructorMarker defaultConstructorMarker) {
        this(locale);
    }

    /* JADX INFO: compiled from: AccessibilityIterators.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AccessibilityIterators$CharacterTextSegmentIterator getInstance(Locale locale) {
            if (AccessibilityIterators$CharacterTextSegmentIterator.instance == null) {
                AccessibilityIterators$CharacterTextSegmentIterator.instance = new AccessibilityIterators$CharacterTextSegmentIterator(locale, null);
            }
            AccessibilityIterators$CharacterTextSegmentIterator accessibilityIterators$CharacterTextSegmentIterator = AccessibilityIterators$CharacterTextSegmentIterator.instance;
            Intrinsics.checkNotNull(accessibilityIterators$CharacterTextSegmentIterator, "null cannot be cast to non-null type androidx.compose.ui.platform.AccessibilityIterators.CharacterTextSegmentIterator");
            return accessibilityIterators$CharacterTextSegmentIterator;
        }
    }

    private AccessibilityIterators$CharacterTextSegmentIterator(Locale locale) {
        onLocaleChanged(locale);
    }

    @Override // androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator
    public void initialize(String str) {
        super.initialize(str);
        BreakIterator breakIterator = this.impl;
        if (breakIterator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("impl");
            breakIterator = null;
        }
        breakIterator.setText(str);
    }

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public int[] following(int i) {
        int length = getText().length();
        if (length <= 0 || i >= length) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        do {
            BreakIterator breakIterator = this.impl;
            if (breakIterator == null) {
                Intrinsics.throwUninitializedPropertyAccessException("impl");
                breakIterator = null;
            }
            if (!breakIterator.isBoundary(i)) {
                BreakIterator breakIterator2 = this.impl;
                if (breakIterator2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("impl");
                    breakIterator2 = null;
                }
                i = breakIterator2.following(i);
            } else {
                BreakIterator breakIterator3 = this.impl;
                if (breakIterator3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("impl");
                    breakIterator3 = null;
                }
                int iFollowing = breakIterator3.following(i);
                if (iFollowing == -1) {
                    return null;
                }
                return getRange(i, iFollowing);
            }
        } while (i != -1);
        return null;
    }

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public int[] preceding(int i) {
        int length = getText().length();
        if (length <= 0 || i <= 0) {
            return null;
        }
        if (i > length) {
            i = length;
        }
        do {
            BreakIterator breakIterator = this.impl;
            if (breakIterator == null) {
                Intrinsics.throwUninitializedPropertyAccessException("impl");
                breakIterator = null;
            }
            if (!breakIterator.isBoundary(i)) {
                BreakIterator breakIterator2 = this.impl;
                if (breakIterator2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("impl");
                    breakIterator2 = null;
                }
                i = breakIterator2.preceding(i);
            } else {
                BreakIterator breakIterator3 = this.impl;
                if (breakIterator3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("impl");
                    breakIterator3 = null;
                }
                int iPreceding = breakIterator3.preceding(i);
                if (iPreceding == -1) {
                    return null;
                }
                return getRange(iPreceding, i);
            }
        } while (i != -1);
        return null;
    }

    private final void onLocaleChanged(Locale locale) {
        this.impl = BreakIterator.getCharacterInstance(locale);
    }
}
