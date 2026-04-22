package io.ktor.util.date;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Date.kt */
/* JADX INFO: loaded from: classes.dex */
public enum Month {
    JANUARY("Jan"),
    FEBRUARY("Feb"),
    MARCH("Mar"),
    APRIL("Apr"),
    MAY("May"),
    JUNE("Jun"),
    JULY("Jul"),
    AUGUST("Aug"),
    SEPTEMBER("Sep"),
    OCTOBER("Oct"),
    NOVEMBER("Nov"),
    DECEMBER("Dec");

    public static final Companion Companion = new Companion(null);
    private final String value;

    Month(String str) {
        this.value = str;
    }

    /* JADX INFO: compiled from: Date.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Month from(int i) {
            return Month.values()[i];
        }
    }
}
