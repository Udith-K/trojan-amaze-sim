package androidx.compose.ui.text.input;

import android.view.Choreographer;
import android.view.inputmethod.EditorInfo;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.emoji2.text.EmojiCompat;
import java.util.concurrent.Executor;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: TextInputServiceAndroid.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextInputServiceAndroid_androidKt {
    private static final boolean hasFlag(int i, int i2) {
        return (i & i2) == i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateWithEmojiCompat(EditorInfo editorInfo) {
        if (EmojiCompat.isConfigured()) {
            EmojiCompat.get();
            throw null;
        }
    }

    public static final void update(EditorInfo editorInfo, ImeOptions imeOptions, TextFieldValue textFieldValue) {
        int iM2204getImeActioneUduSuo = imeOptions.m2204getImeActioneUduSuo();
        ImeAction.Companion companion = ImeAction.Companion;
        int i = 6;
        if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2194getDefaulteUduSuo())) {
            if (!imeOptions.getSingleLine()) {
                i = 0;
            }
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2198getNoneeUduSuo())) {
            i = 1;
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2196getGoeUduSuo())) {
            i = 2;
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2197getNexteUduSuo())) {
            i = 5;
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2199getPreviouseUduSuo())) {
            i = 7;
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2200getSearcheUduSuo())) {
            i = 3;
        } else if (ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2201getSendeUduSuo())) {
            i = 4;
        } else if (!ImeAction.m2190equalsimpl0(iM2204getImeActioneUduSuo, companion.m2195getDoneeUduSuo())) {
            throw new IllegalStateException("invalid ImeAction");
        }
        editorInfo.imeOptions = i;
        imeOptions.getPlatformImeOptions();
        int iM2205getKeyboardTypePjHm6EE = imeOptions.m2205getKeyboardTypePjHm6EE();
        KeyboardType.Companion companion2 = KeyboardType.Companion;
        if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2233getTextPjHm6EE())) {
            editorInfo.inputType = 1;
        } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2226getAsciiPjHm6EE())) {
            editorInfo.inputType = 1;
            editorInfo.imeOptions |= Integer.MIN_VALUE;
        } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2229getNumberPjHm6EE())) {
            editorInfo.inputType = 2;
        } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2232getPhonePjHm6EE())) {
            editorInfo.inputType = 3;
        } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2235getUriPjHm6EE())) {
            editorInfo.inputType = 17;
        } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2228getEmailPjHm6EE())) {
            editorInfo.inputType = 33;
        } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2231getPasswordPjHm6EE())) {
            editorInfo.inputType = 129;
        } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2230getNumberPasswordPjHm6EE())) {
            editorInfo.inputType = 18;
        } else if (KeyboardType.m2222equalsimpl0(iM2205getKeyboardTypePjHm6EE, companion2.m2227getDecimalPjHm6EE())) {
            editorInfo.inputType = 8194;
        } else {
            throw new IllegalStateException("Invalid Keyboard Type");
        }
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
        editorInfo.initialSelStart = TextRange.m2117getStartimpl(textFieldValue.m2239getSelectiond9O1mEE());
        editorInfo.initialSelEnd = TextRange.m2112getEndimpl(textFieldValue.m2239getSelectiond9O1mEE());
        EditorInfoCompat.setInitialSurroundingText(editorInfo, textFieldValue.getText());
        editorInfo.imeOptions |= 33554432;
    }

    public static final Executor asExecutor(final Choreographer choreographer) {
        return new Executor() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid_androidKt$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                TextInputServiceAndroid_androidKt.asExecutor$lambda$2(choreographer, runnable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void asExecutor$lambda$2(Choreographer choreographer, final Runnable runnable) {
        choreographer.postFrameCallback(new Choreographer.FrameCallback() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid_androidKt$$ExternalSyntheticLambda1
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                runnable.run();
            }
        });
    }
}
