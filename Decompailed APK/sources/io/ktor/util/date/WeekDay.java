package io.ktor.util.date;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Date.kt */
/* JADX INFO: loaded from: classes.dex */
public enum WeekDay {
    MONDAY("Mon"),
    TUESDAY("Tue"),
    WEDNESDAY("Wed"),
    THURSDAY("Thu"),
    FRIDAY("Fri"),
    SATURDAY("Sat"),
    SUNDAY("Sun");

    public static final Companion Companion = new Companion(null);
    private final String value;

    WeekDay(String str) {
        this.value = str;
    }

    /* JADX INFO: compiled from: Date.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WeekDay from(int i) {
            return WeekDay.values()[i];
        }
    }
}
