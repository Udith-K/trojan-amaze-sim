package androidx.compose.ui.text.intl;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Locale.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Locale {
    public static final Companion Companion = new Companion(null);
    private final java.util.Locale platformLocale;

    public Locale(java.util.Locale locale) {
        this.platformLocale = locale;
    }

    /* JADX INFO: compiled from: Locale.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Locale getCurrent() {
            return PlatformLocaleKt.getPlatformLocaleDelegate().getCurrent().get(0);
        }
    }

    public final java.util.Locale getPlatformLocale() {
        return this.platformLocale;
    }

    public Locale(String str) {
        this(PlatformLocaleKt.getPlatformLocaleDelegate().parseLanguageTag(str));
    }

    public final String toLanguageTag() {
        return PlatformLocale_jvmKt.getLanguageTag(this.platformLocale);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Locale)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return Intrinsics.areEqual(toLanguageTag(), ((Locale) obj).toLanguageTag());
    }

    public int hashCode() {
        return toLanguageTag().hashCode();
    }

    public String toString() {
        return toLanguageTag();
    }
}
