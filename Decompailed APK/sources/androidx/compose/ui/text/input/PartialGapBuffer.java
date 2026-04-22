package androidx.compose.ui.text.input;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: compiled from: GapBuffer.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PartialGapBuffer {
    private GapBuffer buffer;
    private String text;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private int bufStart = -1;
    private int bufEnd = -1;

    public PartialGapBuffer(String str) {
        this.text = str;
    }

    /* JADX INFO: compiled from: GapBuffer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final int getLength() {
        GapBuffer gapBuffer = this.buffer;
        return gapBuffer == null ? this.text.length() : (this.text.length() - (this.bufEnd - this.bufStart)) + gapBuffer.length();
    }

    public final void replace(int i, int i2, String str) {
        if (i > i2) {
            throw new IllegalArgumentException(("start index must be less than or equal to end index: " + i + " > " + i2).toString());
        }
        if (i < 0) {
            throw new IllegalArgumentException(("start must be non-negative, but was " + i).toString());
        }
        GapBuffer gapBuffer = this.buffer;
        if (gapBuffer == null) {
            int iMax = Math.max(GF2Field.MASK, str.length() + 128);
            char[] cArr = new char[iMax];
            int iMin = Math.min(i, 64);
            int iMin2 = Math.min(this.text.length() - i2, 64);
            int i3 = i - iMin;
            GapBuffer_jvmKt.toCharArray(this.text, cArr, 0, i3, i);
            int i4 = iMax - iMin2;
            int i5 = iMin2 + i2;
            GapBuffer_jvmKt.toCharArray(this.text, cArr, i4, i2, i5);
            GapBufferKt.toCharArray(str, cArr, iMin);
            this.buffer = new GapBuffer(cArr, iMin + str.length(), i4);
            this.bufStart = i3;
            this.bufEnd = i5;
            return;
        }
        int i6 = this.bufStart;
        int i7 = i - i6;
        int i8 = i2 - i6;
        if (i7 < 0 || i8 > gapBuffer.length()) {
            this.text = toString();
            this.buffer = null;
            this.bufStart = -1;
            this.bufEnd = -1;
            replace(i, i2, str);
            return;
        }
        gapBuffer.replace(i7, i8, str);
    }

    public final char get(int i) {
        GapBuffer gapBuffer = this.buffer;
        if (gapBuffer == null) {
            return this.text.charAt(i);
        }
        if (i < this.bufStart) {
            return this.text.charAt(i);
        }
        int length = gapBuffer.length();
        int i2 = this.bufStart;
        if (i < length + i2) {
            return gapBuffer.get(i - i2);
        }
        return this.text.charAt(i - ((length - this.bufEnd) + i2));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public String toString() {
        GapBuffer gapBuffer = this.buffer;
        if (gapBuffer == null) {
            return this.text;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) this.text, 0, this.bufStart);
        gapBuffer.append(sb);
        String str = this.text;
        sb.append((CharSequence) str, this.bufEnd, str.length());
        return sb.toString();
    }
}
