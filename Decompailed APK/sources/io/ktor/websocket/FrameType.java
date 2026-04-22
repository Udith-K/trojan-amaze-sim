package io.ktor.websocket;

import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: FrameType.kt */
/* JADX INFO: loaded from: classes.dex */
public enum FrameType {
    TEXT(false, 1),
    BINARY(false, 2),
    CLOSE(true, 8),
    PING(true, 9),
    PONG(true, 10);

    public static final Companion Companion = new Companion(null);
    private static final FrameType[] byOpcodeArray;
    private static final int maxOpcode;
    private final boolean controlFrame;
    private final int opcode;

    FrameType(boolean z, int i) {
        this.controlFrame = z;
        this.opcode = i;
    }

    static {
        FrameType frameType;
        FrameType[] frameTypeArrValues = values();
        if (frameTypeArrValues.length == 0) {
            frameType = null;
        } else {
            frameType = frameTypeArrValues[0];
            int lastIndex = ArraysKt.getLastIndex(frameTypeArrValues);
            if (lastIndex != 0) {
                int i = frameType.opcode;
                IntIterator it = new IntRange(1, lastIndex).iterator();
                while (it.hasNext()) {
                    FrameType frameType2 = frameTypeArrValues[it.nextInt()];
                    int i2 = frameType2.opcode;
                    if (i < i2) {
                        frameType = frameType2;
                        i = i2;
                    }
                }
            }
        }
        Intrinsics.checkNotNull(frameType);
        int i3 = frameType.opcode;
        maxOpcode = i3;
        int i4 = i3 + 1;
        FrameType[] frameTypeArr = new FrameType[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            FrameType[] frameTypeArrValues2 = values();
            int length = frameTypeArrValues2.length;
            FrameType frameType3 = null;
            int i6 = 0;
            boolean z = false;
            while (true) {
                if (i6 >= length) {
                    if (!z) {
                        break;
                    }
                } else {
                    FrameType frameType4 = frameTypeArrValues2[i6];
                    if (frameType4.opcode == i5) {
                        if (z) {
                            break;
                        }
                        z = true;
                        frameType3 = frameType4;
                    }
                    i6++;
                }
            }
            frameType3 = null;
            frameTypeArr[i5] = frameType3;
        }
        byOpcodeArray = frameTypeArr;
    }

    /* JADX INFO: compiled from: FrameType.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
