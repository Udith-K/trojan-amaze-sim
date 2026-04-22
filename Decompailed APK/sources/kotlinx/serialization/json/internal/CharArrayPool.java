package kotlinx.serialization.json.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: CharArrayPool.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CharArrayPool {
    private static final int MAX_CHARS_IN_POOL;
    private static int charsTotal;
    public static final CharArrayPool INSTANCE = new CharArrayPool();
    private static final ArrayDeque arrays = new ArrayDeque();

    private CharArrayPool() {
    }

    static {
        Object objM2639constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            String property = System.getProperty("kotlinx.serialization.json.pool.size");
            Intrinsics.checkNotNullExpressionValue(property, "getProperty(\"kotlinx.ser…lization.json.pool.size\")");
            objM2639constructorimpl = Result.m2639constructorimpl(StringsKt.toIntOrNull(property));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2644isFailureimpl(objM2639constructorimpl)) {
            objM2639constructorimpl = null;
        }
        Integer num = (Integer) objM2639constructorimpl;
        MAX_CHARS_IN_POOL = num != null ? num.intValue() : PKIFailureInfo.badCertTemplate;
    }

    public final char[] take() {
        char[] cArr;
        synchronized (this) {
            cArr = (char[]) arrays.removeLastOrNull();
            if (cArr != null) {
                charsTotal -= cArr.length;
            } else {
                cArr = null;
            }
        }
        return cArr == null ? new char[128] : cArr;
    }

    public final void release(char[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        synchronized (this) {
            try {
                int i = charsTotal;
                if (array.length + i < MAX_CHARS_IN_POOL) {
                    charsTotal = i + array.length;
                    arrays.addLast(array);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
