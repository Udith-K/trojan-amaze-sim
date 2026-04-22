package androidx.compose.ui.text.input;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ImeAction.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ImeAction {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int Unspecified = m2188constructorimpl(-1);
    private static final int Default = m2188constructorimpl(1);
    private static final int None = m2188constructorimpl(0);
    private static final int Go = m2188constructorimpl(2);
    private static final int Search = m2188constructorimpl(3);
    private static final int Send = m2188constructorimpl(4);
    private static final int Previous = m2188constructorimpl(5);
    private static final int Next = m2188constructorimpl(6);
    private static final int Done = m2188constructorimpl(7);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ImeAction m2187boximpl(int i) {
        return new ImeAction(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m2188constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2189equalsimpl(int i, Object obj) {
        return (obj instanceof ImeAction) && i == ((ImeAction) obj).m2193unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2190equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2191hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m2189equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2191hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2193unboximpl() {
        return this.value;
    }

    private /* synthetic */ ImeAction(int i) {
        this.value = i;
    }

    public String toString() {
        return m2192toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2192toStringimpl(int i) {
        return m2190equalsimpl0(i, Unspecified) ? "Unspecified" : m2190equalsimpl0(i, None) ? "None" : m2190equalsimpl0(i, Default) ? "Default" : m2190equalsimpl0(i, Go) ? "Go" : m2190equalsimpl0(i, Search) ? "Search" : m2190equalsimpl0(i, Send) ? "Send" : m2190equalsimpl0(i, Previous) ? "Previous" : m2190equalsimpl0(i, Next) ? "Next" : m2190equalsimpl0(i, Done) ? "Done" : "Invalid";
    }

    /* JADX INFO: compiled from: ImeAction.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getUnspecified-eUduSuo, reason: not valid java name */
        public final int m2202getUnspecifiedeUduSuo() {
            return ImeAction.Unspecified;
        }

        /* JADX INFO: renamed from: getDefault-eUduSuo, reason: not valid java name */
        public final int m2194getDefaulteUduSuo() {
            return ImeAction.Default;
        }

        /* JADX INFO: renamed from: getNone-eUduSuo, reason: not valid java name */
        public final int m2198getNoneeUduSuo() {
            return ImeAction.None;
        }

        /* JADX INFO: renamed from: getGo-eUduSuo, reason: not valid java name */
        public final int m2196getGoeUduSuo() {
            return ImeAction.Go;
        }

        /* JADX INFO: renamed from: getSearch-eUduSuo, reason: not valid java name */
        public final int m2200getSearcheUduSuo() {
            return ImeAction.Search;
        }

        /* JADX INFO: renamed from: getSend-eUduSuo, reason: not valid java name */
        public final int m2201getSendeUduSuo() {
            return ImeAction.Send;
        }

        /* JADX INFO: renamed from: getPrevious-eUduSuo, reason: not valid java name */
        public final int m2199getPreviouseUduSuo() {
            return ImeAction.Previous;
        }

        /* JADX INFO: renamed from: getNext-eUduSuo, reason: not valid java name */
        public final int m2197getNexteUduSuo() {
            return ImeAction.Next;
        }

        /* JADX INFO: renamed from: getDone-eUduSuo, reason: not valid java name */
        public final int m2195getDoneeUduSuo() {
            return ImeAction.Done;
        }
    }
}
