package androidx.compose.foundation.text;

import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.intl.LocaleList;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KeyboardOptions.kt */
/* JADX INFO: loaded from: classes.dex */
public final class KeyboardOptions {
    public static final Companion Companion = new Companion(null);
    private static final KeyboardOptions Default = new KeyboardOptions(0, null, 0, 0, null, null, null, 127, null);
    private static final KeyboardOptions SecureTextField = new KeyboardOptions(0, Boolean.FALSE, KeyboardType.Companion.m2231getPasswordPjHm6EE(), 0, null, null, null, 121, null);
    private final Boolean autoCorrectEnabled;
    private final int capitalization;
    private final LocaleList hintLocales;
    private final int imeAction;
    private final int keyboardType;
    private final Boolean showKeyboardOnFocus;

    public /* synthetic */ KeyboardOptions(int i, Boolean bool, int i2, int i3, PlatformImeOptions platformImeOptions, Boolean bool2, LocaleList localeList, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, bool, i2, i3, platformImeOptions, bool2, localeList);
    }

    private KeyboardOptions(int i, Boolean bool, int i2, int i3, PlatformImeOptions platformImeOptions, Boolean bool2, LocaleList localeList) {
        this.capitalization = i;
        this.autoCorrectEnabled = bool;
        this.keyboardType = i2;
        this.imeAction = i3;
        this.showKeyboardOnFocus = bool2;
        this.hintLocales = localeList;
    }

    public /* synthetic */ KeyboardOptions(int i, Boolean bool, int i2, int i3, PlatformImeOptions platformImeOptions, Boolean bool2, LocaleList localeList, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? KeyboardCapitalization.Companion.m2217getUnspecifiedIUNYP9k() : i, (i4 & 2) != 0 ? null : bool, (i4 & 4) != 0 ? KeyboardType.Companion.m2234getUnspecifiedPjHm6EE() : i2, (i4 & 8) != 0 ? ImeAction.Companion.m2202getUnspecifiedeUduSuo() : i3, (i4 & 16) != 0 ? null : platformImeOptions, (i4 & 32) != 0 ? null : bool2, (i4 & 64) == 0 ? localeList : null, null);
    }

    /* JADX INFO: compiled from: KeyboardOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KeyboardOptions getDefault() {
            return KeyboardOptions.Default;
        }
    }

    private final boolean getAutoCorrectOrDefault() {
        Boolean bool = this.autoCorrectEnabled;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    /* JADX INFO: renamed from: getCapitalizationOrDefault-IUNYP9k, reason: not valid java name */
    private final int m388getCapitalizationOrDefaultIUNYP9k() {
        KeyboardCapitalization keyboardCapitalizationM2207boximpl = KeyboardCapitalization.m2207boximpl(this.capitalization);
        int iM2213unboximpl = keyboardCapitalizationM2207boximpl.m2213unboximpl();
        KeyboardCapitalization.Companion companion = KeyboardCapitalization.Companion;
        if (KeyboardCapitalization.m2210equalsimpl0(iM2213unboximpl, companion.m2217getUnspecifiedIUNYP9k())) {
            keyboardCapitalizationM2207boximpl = null;
        }
        return keyboardCapitalizationM2207boximpl != null ? keyboardCapitalizationM2207boximpl.m2213unboximpl() : companion.m2215getNoneIUNYP9k();
    }

    /* JADX INFO: renamed from: getKeyboardTypeOrDefault-PjHm6EE, reason: not valid java name */
    private final int m389getKeyboardTypeOrDefaultPjHm6EE() {
        KeyboardType keyboardTypeM2219boximpl = KeyboardType.m2219boximpl(this.keyboardType);
        int iM2225unboximpl = keyboardTypeM2219boximpl.m2225unboximpl();
        KeyboardType.Companion companion = KeyboardType.Companion;
        if (KeyboardType.m2222equalsimpl0(iM2225unboximpl, companion.m2234getUnspecifiedPjHm6EE())) {
            keyboardTypeM2219boximpl = null;
        }
        return keyboardTypeM2219boximpl != null ? keyboardTypeM2219boximpl.m2225unboximpl() : companion.m2233getTextPjHm6EE();
    }

    /* JADX INFO: renamed from: getImeActionOrDefault-eUduSuo$foundation_release, reason: not valid java name */
    public final int m390getImeActionOrDefaulteUduSuo$foundation_release() {
        ImeAction imeActionM2187boximpl = ImeAction.m2187boximpl(this.imeAction);
        int iM2193unboximpl = imeActionM2187boximpl.m2193unboximpl();
        ImeAction.Companion companion = ImeAction.Companion;
        if (ImeAction.m2190equalsimpl0(iM2193unboximpl, companion.m2202getUnspecifiedeUduSuo())) {
            imeActionM2187boximpl = null;
        }
        return imeActionM2187boximpl != null ? imeActionM2187boximpl.m2193unboximpl() : companion.m2194getDefaulteUduSuo();
    }

    private final LocaleList getHintLocalesOrDefault() {
        LocaleList localeList = this.hintLocales;
        return localeList == null ? LocaleList.Companion.getEmpty() : localeList;
    }

    public final ImeOptions toImeOptions$foundation_release(boolean z) {
        return new ImeOptions(z, m388getCapitalizationOrDefaultIUNYP9k(), getAutoCorrectOrDefault(), m389getKeyboardTypeOrDefaultPjHm6EE(), m390getImeActionOrDefaulteUduSuo$foundation_release(), null, getHintLocalesOrDefault(), null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyboardOptions)) {
            return false;
        }
        KeyboardOptions keyboardOptions = (KeyboardOptions) obj;
        if (!KeyboardCapitalization.m2210equalsimpl0(this.capitalization, keyboardOptions.capitalization) || !Intrinsics.areEqual(this.autoCorrectEnabled, keyboardOptions.autoCorrectEnabled) || !KeyboardType.m2222equalsimpl0(this.keyboardType, keyboardOptions.keyboardType) || !ImeAction.m2190equalsimpl0(this.imeAction, keyboardOptions.imeAction)) {
            return false;
        }
        keyboardOptions.getClass();
        return Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual(this.showKeyboardOnFocus, keyboardOptions.showKeyboardOnFocus) && Intrinsics.areEqual(this.hintLocales, keyboardOptions.hintLocales);
    }

    public int hashCode() {
        int iM2211hashCodeimpl = KeyboardCapitalization.m2211hashCodeimpl(this.capitalization) * 31;
        Boolean bool = this.autoCorrectEnabled;
        int iHashCode = (((((iM2211hashCodeimpl + (bool != null ? bool.hashCode() : 0)) * 31) + KeyboardType.m2223hashCodeimpl(this.keyboardType)) * 31) + ImeAction.m2191hashCodeimpl(this.imeAction)) * 961;
        Boolean bool2 = this.showKeyboardOnFocus;
        int iHashCode2 = (iHashCode + (bool2 != null ? bool2.hashCode() : 0)) * 31;
        LocaleList localeList = this.hintLocales;
        return iHashCode2 + (localeList != null ? localeList.hashCode() : 0);
    }

    public String toString() {
        return "KeyboardOptions(capitalization=" + ((Object) KeyboardCapitalization.m2212toStringimpl(this.capitalization)) + ", autoCorrectEnabled=" + this.autoCorrectEnabled + ", keyboardType=" + ((Object) KeyboardType.m2224toStringimpl(this.keyboardType)) + ", imeAction=" + ((Object) ImeAction.m2192toStringimpl(this.imeAction)) + ", platformImeOptions=" + ((Object) null) + "showKeyboardOnFocus=" + this.showKeyboardOnFocus + ", hintLocales=" + this.hintLocales + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
