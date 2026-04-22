package androidx.compose.runtime;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Composer.kt */
/* JADX INFO: loaded from: classes.dex */
abstract class GroupKind {
    public static final Companion Companion = new Companion(null);
    private static final int Group = m1013constructorimpl(0);
    private static final int Node = m1013constructorimpl(1);
    private static final int ReusableNode = m1013constructorimpl(2);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m1013constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: compiled from: Composer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getGroup-ULZAiWs, reason: not valid java name */
        public final int m1014getGroupULZAiWs() {
            return GroupKind.Group;
        }

        /* JADX INFO: renamed from: getNode-ULZAiWs, reason: not valid java name */
        public final int m1015getNodeULZAiWs() {
            return GroupKind.Node;
        }

        /* JADX INFO: renamed from: getReusableNode-ULZAiWs, reason: not valid java name */
        public final int m1016getReusableNodeULZAiWs() {
            return GroupKind.ReusableNode;
        }
    }
}
