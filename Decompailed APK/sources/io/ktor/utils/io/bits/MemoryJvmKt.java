package io.ktor.utils.io.bits;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemoryJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class MemoryJvmKt {
    /* JADX INFO: renamed from: copyTo-9zorpBc, reason: not valid java name */
    public static final void m2619copyTo9zorpBc(ByteBuffer copyTo, byte[] destination, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (copyTo.hasArray() && !copyTo.isReadOnly()) {
            System.arraycopy(copyTo.array(), copyTo.arrayOffset() + i, destination, i3, i2);
        } else {
            copyTo.duplicate().get(destination, i3, i2);
        }
    }

    /* JADX INFO: renamed from: copyTo-62zg_DM, reason: not valid java name */
    public static final void m2618copyTo62zg_DM(ByteBuffer copyTo, ByteBuffer destination, int i) {
        Intrinsics.checkNotNullParameter(copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        int iRemaining = destination.remaining();
        if (copyTo.hasArray() && !copyTo.isReadOnly() && destination.hasArray() && !destination.isReadOnly()) {
            int iPosition = destination.position();
            System.arraycopy(copyTo.array(), copyTo.arrayOffset() + i, destination.array(), destination.arrayOffset() + iPosition, iRemaining);
            destination.position(iPosition + iRemaining);
        } else {
            ByteBuffer byteBufferDuplicate = copyTo.duplicate();
            byteBufferDuplicate.limit(iRemaining + i);
            byteBufferDuplicate.position(i);
            destination.put(byteBufferDuplicate);
        }
    }

    /* JADX INFO: renamed from: copyTo-SG11BkQ, reason: not valid java name */
    public static final void m2620copyToSG11BkQ(ByteBuffer copyTo, ByteBuffer destination, int i) {
        Intrinsics.checkNotNullParameter(copyTo, "$this$copyTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (copyTo.hasArray() && !copyTo.isReadOnly()) {
            byte[] bArrArray = copyTo.array();
            Intrinsics.checkNotNullExpressionValue(bArrArray, "array()");
            int iArrayOffset = copyTo.arrayOffset() + copyTo.position();
            int iRemaining = copyTo.remaining();
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArrArray, iArrayOffset, iRemaining).slice().order(ByteOrder.BIG_ENDIAN);
            Intrinsics.checkNotNullExpressionValue(byteBufferOrder, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
            Memory.m2615copyToJT6ljtQ(Memory.m2614constructorimpl(byteBufferOrder), destination, 0, iRemaining, i);
            copyTo.position(copyTo.limit());
            return;
        }
        sliceSafe(destination, i, copyTo.remaining()).put(copyTo);
    }

    public static final ByteBuffer sliceSafe(ByteBuffer byteBuffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        ByteBuffer myDuplicate$lambda$1 = byteBuffer.duplicate();
        Intrinsics.checkNotNullExpressionValue(myDuplicate$lambda$1, "myDuplicate$lambda$1");
        myDuplicate$lambda$1.position(i);
        myDuplicate$lambda$1.limit(i + i2);
        ByteBuffer mySlice$lambda$2 = myDuplicate$lambda$1.slice();
        Intrinsics.checkNotNullExpressionValue(mySlice$lambda$2, "mySlice$lambda$2");
        return mySlice$lambda$2;
    }
}
