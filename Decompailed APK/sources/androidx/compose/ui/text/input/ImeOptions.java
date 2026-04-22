package androidx.compose.ui.text.input;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.ui.text.intl.LocaleList;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImeOptions.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ImeOptions {
    public static final Companion Companion = new Companion(null);
    private static final ImeOptions Default = new ImeOptions(false, 0, false, 0, 0, null, null, 127, null);
    private final boolean autoCorrect;
    private final int capitalization;
    private final LocaleList hintLocales;
    private final int imeAction;
    private final int keyboardType;
    private final boolean singleLine;

    public /* synthetic */ ImeOptions(boolean z, int i, boolean z2, int i2, int i3, PlatformImeOptions platformImeOptions, LocaleList localeList, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, i, z2, i2, i3, platformImeOptions, localeList);
    }

    public final PlatformImeOptions getPlatformImeOptions() {
        return null;
    }

    private ImeOptions(boolean z, int i, boolean z2, int i2, int i3, PlatformImeOptions platformImeOptions, LocaleList localeList) {
        this.singleLine = z;
        this.capitalization = i;
        this.autoCorrect = z2;
        this.keyboardType = i2;
        this.imeAction = i3;
        this.hintLocales = localeList;
    }

    public final boolean getSingleLine() {
        return this.singleLine;
    }

    public /* synthetic */ ImeOptions(boolean z, int i, boolean z2, int i2, int i3, PlatformImeOptions platformImeOptions, LocaleList localeList, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? false : z, (i4 & 2) != 0 ? KeyboardCapitalization.Companion.m2215getNoneIUNYP9k() : i, (i4 & 4) != 0 ? true : z2, (i4 & 8) != 0 ? KeyboardType.Companion.m2233getTextPjHm6EE() : i2, (i4 & 16) != 0 ? ImeAction.Companion.m2194getDefaulteUduSuo() : i3, (i4 & 32) != 0 ? null : platformImeOptions, (i4 & 64) != 0 ? LocaleList.Companion.getEmpty() : localeList, null);
    }

    /* JADX INFO: renamed from: getCapitalization-IUNYP9k, reason: not valid java name */
    public final int m2203getCapitalizationIUNYP9k() {
        return this.capitalization;
    }

    public final boolean getAutoCorrect() {
        return this.autoCorrect;
    }

    /* JADX INFO: renamed from: getKeyboardType-PjHm6EE, reason: not valid java name */
    public final int m2205getKeyboardTypePjHm6EE() {
        return this.keyboardType;
    }

    /* JADX INFO: renamed from: getImeAction-eUduSuo, reason: not valid java name */
    public final int m2204getImeActioneUduSuo() {
        return this.imeAction;
    }

    public final LocaleList getHintLocales() {
        return this.hintLocales;
    }

    /* JADX INFO: compiled from: ImeOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ImeOptions getDefault() {
            return ImeOptions.Default;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImeOptions)) {
            return false;
        }
        ImeOptions imeOptions = (ImeOptions) obj;
        if (this.singleLine != imeOptions.singleLine || !KeyboardCapitalization.m2210equalsimpl0(this.capitalization, imeOptions.capitalization) || this.autoCorrect != imeOptions.autoCorrect || !KeyboardType.m2222equalsimpl0(this.keyboardType, imeOptions.keyboardType) || !ImeAction.m2190equalsimpl0(this.imeAction, imeOptions.imeAction)) {
            return false;
        }
        imeOptions.getClass();
        return Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual(this.hintLocales, imeOptions.hintLocales);
    }

    public int hashCode() {
        return (((((((((ChangeSize$$ExternalSyntheticBackport0.m(this.singleLine) * 31) + KeyboardCapitalization.m2211hashCodeimpl(this.capitalization)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.autoCorrect)) * 31) + KeyboardType.m2223hashCodeimpl(this.keyboardType)) * 31) + ImeAction.m2191hashCodeimpl(this.imeAction)) * 961) + this.hintLocales.hashCode();
    }

    public String toString() {
        return "ImeOptions(singleLine=" + this.singleLine + ", capitalization=" + ((Object) KeyboardCapitalization.m2212toStringimpl(this.capitalization)) + ", autoCorrect=" + this.autoCorrect + ", keyboardType=" + ((Object) KeyboardType.m2224toStringimpl(this.keyboardType)) + ", imeAction=" + ((Object) ImeAction.m2192toStringimpl(this.imeAction)) + ", platformImeOptions=" + ((Object) null) + ", hintLocales=" + this.hintLocales + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
