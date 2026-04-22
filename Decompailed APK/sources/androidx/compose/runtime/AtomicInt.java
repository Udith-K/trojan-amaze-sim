package androidx.compose.runtime;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ActualJvm.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AtomicInt extends AtomicInteger {
    public AtomicInt(int i) {
        super(i);
    }

    @Override // java.lang.Number
    public final /* bridge */ byte byteValue() {
        return toByte();
    }

    @Override // java.util.concurrent.atomic.AtomicInteger, java.lang.Number
    public final /* bridge */ double doubleValue() {
        return toDouble();
    }

    @Override // java.util.concurrent.atomic.AtomicInteger, java.lang.Number
    public final /* bridge */ float floatValue() {
        return toFloat();
    }

    @Override // java.util.concurrent.atomic.AtomicInteger, java.lang.Number
    public final /* bridge */ int intValue() {
        return toInt();
    }

    @Override // java.util.concurrent.atomic.AtomicInteger, java.lang.Number
    public final /* bridge */ long longValue() {
        return toLong();
    }

    @Override // java.lang.Number
    public final /* bridge */ short shortValue() {
        return toShort();
    }

    public /* bridge */ double toDouble() {
        return super.doubleValue();
    }

    public /* bridge */ float toFloat() {
        return super.floatValue();
    }

    public /* bridge */ int toInt() {
        return super.intValue();
    }

    public /* bridge */ long toLong() {
        return super.longValue();
    }

    public final int add(int i) {
        return addAndGet(i);
    }

    public byte toByte() {
        return (byte) intValue();
    }

    public short toShort() {
        return (short) intValue();
    }
}
