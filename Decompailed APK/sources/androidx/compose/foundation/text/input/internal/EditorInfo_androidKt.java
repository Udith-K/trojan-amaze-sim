package androidx.compose.foundation.text.input.internal;

import android.os.Build;
import android.view.inputmethod.EditorInfo;
import androidx.compose.foundation.text.handwriting.StylusHandwriting_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.core.view.inputmethod.EditorInfoCompat;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: EditorInfo.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class EditorInfo_androidKt {
    private static final boolean hasFlag(int i, int i2) {
        return (i & i2) == i2;
    }

    /* JADX INFO: renamed from: update-pLxbY9I$default, reason: not valid java name */
    public static /* synthetic */ void m476updatepLxbY9I$default(EditorInfo editorInfo, CharSequence charSequence, long j, ImeOptions imeOptions, String[] strArr, int i, Object obj) {
        if ((i & 8) != 0) {
            strArr = null;
        }
        m475updatepLxbY9I(editorInfo, charSequence, j, imeOptions, strArr);
    }

    /* JADX INFO: renamed from: update-pLxbY9I, reason: not valid java name */
    public static final void m475updatepLxbY9I(EditorInfo editorInfo, CharSequence charSequence, long j, ImeOptions imeOptions, String[] strArr) {
        int iM2204getImeActioneUduSuo = imeOptions.m2204getImeActioneUduSuo();
        ImeAction.Companion companion = ImeAction.Companion;
        int i = 3;
        int i2 = 6;
        if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2194getDefaulteUduSuo())) {
            if (!imeOptions.getSingleLine()) {
                i2 = 0;
            }
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2198getNoneeUduSuo())) {
            i2 = 1;
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2196getGoeUduSuo())) {
            i2 = 2;
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2197getNexteUduSuo())) {
            i2 = 5;
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2199getPreviouseUduSuo())) {
            i2 = 7;
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2200getSearcheUduSuo())) {
            i2 = 3;
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2201getSendeUduSuo())) {
            i2 = 4;
        } else if (!ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2195getDoneeUduSuo())) {
            throw new IllegalStateException("invalid ImeAction");
        }
        editorInfo.imeOptions = i2;
        imeOptions.getPlatformImeOptions();
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleListHelper.INSTANCE.setHintLocales(editorInfo, imeOptions.getHintLocales());
        }
        int iM2205getKeyboardTypePjHm6EE = imeOptions.m2205getKeyboardTypePjHm6EE();
        KeyboardType.Companion companion2 = KeyboardType.Companion;
        if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2233getTextPjHm6EE())) {
            i = 1;
        } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2226getAsciiPjHm6EE())) {
            editorInfo.imeOptions |= Integer.MIN_VALUE;
            i = 1;
        } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2229getNumberPjHm6EE())) {
            i = 2;
        } else if (!KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2232getPhonePjHm6EE())) {
            if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2235getUriPjHm6EE())) {
                i = 17;
            } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2228getEmailPjHm6EE())) {
                i = 33;
            } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2231getPasswordPjHm6EE())) {
                i = 129;
            } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2230getNumberPasswordPjHm6EE())) {
                i = 18;
            } else {
                if (!KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2227getDecimalPjHm6EE())) {
                    throw new IllegalStateException("Invalid Keyboard Type");
                }
                i = 8194;
            }
        }
        editorInfo.inputType = i;
        if (!imeOptions.getSingleLine() && hasFlag(editorInfo.inputType, 1)) {
            editorInfo.inputType |= PKIFailureInfo.unsupportedVersion;
            if (ImeAction.m2190equalsimpl0(imeOptions.m2204getImeActioneUduSuo(), companion.m2194getDefaulteUduSuo())) {
                editorInfo.imeOptions |= 1073741824;
            }
        }
        if (hasFlag(editorInfo.inputType, 1)) {
            int iM2203getCapitalizationIUNYP9k = imeOptions.m2203getCapitalizationIUNYP9k();
            KeyboardCapitalization.Companion companion3 = KeyboardCapitalization.Companion;
            if (KeyboardCapitalization.m2210equalsimpl0(iM2203getCapitalizationIUNYP9k, companion3.m2214getCharactersIUNYP9k())) {
                editorInfo.inputType |= PKIFailureInfo.certConfirmed;
            } else if (KeyboardCapitalization.m2210equalsimpl0(iM2203getCapitalizationIUNYP9k, companion3.m2218getWordsIUNYP9k())) {
                editorInfo.inputType |= 8192;
            } else if (KeyboardCapitalization.m2210equalsimpl0(iM2203getCapitalizationIUNYP9k, companion3.m2216getSentencesIUNYP9k())) {
                editorInfo.inputType |= 16384;
            }
            if (imeOptions.getAutoCorrect()) {
                editorInfo.inputType |= 32768;
            }
        }
        editorInfo.initialSelStart = TextRange.m2117getStartimpl(j);
        editorInfo.initialSelEnd = TextRange.m2112getEndimpl(j);
        EditorInfoCompat.setInitialSurroundingText(editorInfo, charSequence);
        if (strArr != null) {
            EditorInfoCompat.setContentMimeTypes(editorInfo, strArr);
        }
        editorInfo.imeOptions |= 33554432;
        if (StylusHandwriting_androidKt.isStylusHandwritingSupported() && !KeyboardType.m2222equalsimpl0(imeOptions.m2205getKeyboardTypePjHm6EE(), companion2.m2231getPasswordPjHm6EE()) && !KeyboardType.m2222equalsimpl0(imeOptions.m2205getKeyboardTypePjHm6EE(), companion2.m2230getNumberPasswordPjHm6EE())) {
            EditorInfoCompat.setStylusHandwritingEnabled(editorInfo, true);
            EditorInfoApi34.INSTANCE.setHandwritingGestures(editorInfo);
        } else {
            EditorInfoCompat.setStylusHandwritingEnabled(editorInfo, false);
        }
    }
}
