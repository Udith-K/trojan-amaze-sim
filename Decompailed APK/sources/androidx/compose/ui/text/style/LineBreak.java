package androidx.compose.ui.text.style;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LineBreak.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LineBreak {
    public static final Companion Companion = new Companion(null);
    private static final int Heading;
    private static final int Paragraph;
    private static final int Simple;
    private static final int Unspecified;
    private final int mask;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ LineBreak m2287boximpl(int i) {
        return new LineBreak(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m2288constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2289equalsimpl(int i, Object obj) {
        return (obj instanceof LineBreak) && i == ((LineBreak) obj).m2296unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2290equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2294hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m2289equalsimpl(this.mask, obj);
    }

    public int hashCode() {
        return m2294hashCodeimpl(this.mask);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2296unboximpl() {
        return this.mask;
    }

    private /* synthetic */ LineBreak(int i) {
        this.mask = i;
    }

    /* JADX INFO: renamed from: getStrategy-fcGXIks, reason: not valid java name */
    public static final int m2291getStrategyfcGXIks(int i) {
        return Strategy.m2299constructorimpl(LineBreak_androidKt.unpackByte1(i));
    }

    /* JADX INFO: renamed from: getStrictness-usljTpc, reason: not valid java name */
    public static final int m2292getStrictnessusljTpc(int i) {
        return Strictness.m2305constructorimpl(LineBreak_androidKt.unpackByte2(i));
    }

    /* JADX INFO: renamed from: getWordBreak-jp8hJ3c, reason: not valid java name */
    public static final int m2293getWordBreakjp8hJ3c(int i) {
        return WordBreak.m2312constructorimpl(LineBreak_androidKt.unpackByte3(i));
    }

    public String toString() {
        return m2295toStringimpl(this.mask);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2295toStringimpl(int i) {
        return "LineBreak(strategy=" + ((Object) Strategy.m2301toStringimpl(m2291getStrategyfcGXIks(i))) + ", strictness=" + ((Object) Strictness.m2307toStringimpl(m2292getStrictnessusljTpc(i))) + ", wordBreak=" + ((Object) WordBreak.m2314toStringimpl(m2293getWordBreakjp8hJ3c(i))) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: LineBreak.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getSimple-rAG3T2k, reason: not valid java name */
        public final int m2297getSimplerAG3T2k() {
            return LineBreak.Simple;
        }

        /* JADX INFO: renamed from: getUnspecified-rAG3T2k, reason: not valid java name */
        public final int m2298getUnspecifiedrAG3T2k() {
            return LineBreak.Unspecified;
        }
    }

    static {
        Strategy.Companion companion = Strategy.Companion;
        int iM2304getSimplefcGXIks = companion.m2304getSimplefcGXIks();
        Strictness.Companion companion2 = Strictness.Companion;
        int iM2310getNormalusljTpc = companion2.m2310getNormalusljTpc();
        WordBreak.Companion companion3 = WordBreak.Companion;
        Simple = m2288constructorimpl(LineBreak_androidKt.packBytes(iM2304getSimplefcGXIks, iM2310getNormalusljTpc, companion3.m2315getDefaultjp8hJ3c()));
        Heading = m2288constructorimpl(LineBreak_androidKt.packBytes(companion.m2302getBalancedfcGXIks(), companion2.m2309getLooseusljTpc(), companion3.m2316getPhrasejp8hJ3c()));
        Paragraph = m2288constructorimpl(LineBreak_androidKt.packBytes(companion.m2303getHighQualityfcGXIks(), companion2.m2311getStrictusljTpc(), companion3.m2315getDefaultjp8hJ3c()));
        Unspecified = m2288constructorimpl(0);
    }

    /* JADX INFO: compiled from: LineBreak.android.kt */
    public static final class Strategy {
        public static final Companion Companion = new Companion(null);
        private static final int Simple = m2299constructorimpl(1);
        private static final int HighQuality = m2299constructorimpl(2);
        private static final int Balanced = m2299constructorimpl(3);
        private static final int Unspecified = m2299constructorimpl(0);

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static int m2299constructorimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m2300equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* JADX INFO: compiled from: LineBreak.android.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getSimple-fcGXIks, reason: not valid java name */
            public final int m2304getSimplefcGXIks() {
                return Strategy.Simple;
            }

            /* JADX INFO: renamed from: getHighQuality-fcGXIks, reason: not valid java name */
            public final int m2303getHighQualityfcGXIks() {
                return Strategy.HighQuality;
            }

            /* JADX INFO: renamed from: getBalanced-fcGXIks, reason: not valid java name */
            public final int m2302getBalancedfcGXIks() {
                return Strategy.Balanced;
            }
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m2301toStringimpl(int i) {
            return m2300equalsimpl0(i, Simple) ? "Strategy.Simple" : m2300equalsimpl0(i, HighQuality) ? "Strategy.HighQuality" : m2300equalsimpl0(i, Balanced) ? "Strategy.Balanced" : m2300equalsimpl0(i, Unspecified) ? "Strategy.Unspecified" : "Invalid";
        }
    }

    /* JADX INFO: compiled from: LineBreak.android.kt */
    public static final class Strictness {
        public static final Companion Companion = new Companion(null);
        private static final int Default = m2305constructorimpl(1);
        private static final int Loose = m2305constructorimpl(2);
        private static final int Normal = m2305constructorimpl(3);
        private static final int Strict = m2305constructorimpl(4);
        private static final int Unspecified = m2305constructorimpl(0);

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static int m2305constructorimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m2306equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* JADX INFO: compiled from: LineBreak.android.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getDefault-usljTpc, reason: not valid java name */
            public final int m2308getDefaultusljTpc() {
                return Strictness.Default;
            }

            /* JADX INFO: renamed from: getLoose-usljTpc, reason: not valid java name */
            public final int m2309getLooseusljTpc() {
                return Strictness.Loose;
            }

            /* JADX INFO: renamed from: getNormal-usljTpc, reason: not valid java name */
            public final int m2310getNormalusljTpc() {
                return Strictness.Normal;
            }

            /* JADX INFO: renamed from: getStrict-usljTpc, reason: not valid java name */
            public final int m2311getStrictusljTpc() {
                return Strictness.Strict;
            }
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m2307toStringimpl(int i) {
            return m2306equalsimpl0(i, Default) ? "Strictness.None" : m2306equalsimpl0(i, Loose) ? "Strictness.Loose" : m2306equalsimpl0(i, Normal) ? "Strictness.Normal" : m2306equalsimpl0(i, Strict) ? "Strictness.Strict" : m2306equalsimpl0(i, Unspecified) ? "Strictness.Unspecified" : "Invalid";
        }
    }

    /* JADX INFO: compiled from: LineBreak.android.kt */
    public static final class WordBreak {
        public static final Companion Companion = new Companion(null);
        private static final int Default = m2312constructorimpl(1);
        private static final int Phrase = m2312constructorimpl(2);
        private static final int Unspecified = m2312constructorimpl(0);

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static int m2312constructorimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m2313equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* JADX INFO: compiled from: LineBreak.android.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getDefault-jp8hJ3c, reason: not valid java name */
            public final int m2315getDefaultjp8hJ3c() {
                return WordBreak.Default;
            }

            /* JADX INFO: renamed from: getPhrase-jp8hJ3c, reason: not valid java name */
            public final int m2316getPhrasejp8hJ3c() {
                return WordBreak.Phrase;
            }
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m2314toStringimpl(int i) {
            return m2313equalsimpl0(i, Default) ? "WordBreak.None" : m2313equalsimpl0(i, Phrase) ? "WordBreak.Phrase" : m2313equalsimpl0(i, Unspecified) ? "WordBreak.Unspecified" : "Invalid";
        }
    }
}
