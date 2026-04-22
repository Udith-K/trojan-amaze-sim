package androidx.compose.animation.core;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StartOffsetType {
    public static final Companion Companion = new Companion(null);
    private static final int Delay = m65constructorimpl(-1);
    private static final int FastForward = m65constructorimpl(1);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m65constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: compiled from: AnimationSpec.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getDelay-Eo1U57Q, reason: not valid java name */
        public final int m66getDelayEo1U57Q() {
            return StartOffsetType.Delay;
        }
    }
}
