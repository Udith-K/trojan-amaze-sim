package kotlinx.serialization.json.internal;

import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Composers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ComposerForUnsignedNumbers extends Composer {
    private final boolean forceQuoting;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerForUnsignedNumbers(JsonWriter writer, boolean z) {
        super(writer);
        Intrinsics.checkNotNullParameter(writer, "writer");
        this.forceQuoting = z;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(int i) {
        boolean z = this.forceQuoting;
        int iM2671constructorimpl = UInt.m2671constructorimpl(i);
        if (z) {
            printQuoted(Long.toString(((long) iM2671constructorimpl) & BodyPartID.bodyIdMax, 10));
        } else {
            print(Long.toString(((long) iM2671constructorimpl) & BodyPartID.bodyIdMax, 10));
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(long j) {
        boolean z = this.forceQuoting;
        long jM2693constructorimpl = ULong.m2693constructorimpl(j);
        if (z) {
            printQuoted(ComposerForUnsignedNumbers$$ExternalSyntheticBackport5.m(jM2693constructorimpl, 10));
        } else {
            print(ComposerForUnsignedNumbers$$ExternalSyntheticBackport6.m(jM2693constructorimpl, 10));
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(byte b) {
        boolean z = this.forceQuoting;
        String strM2652toStringimpl = UByte.m2652toStringimpl(UByte.m2649constructorimpl(b));
        if (z) {
            printQuoted(strM2652toStringimpl);
        } else {
            print(strM2652toStringimpl);
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(short s) {
        boolean z = this.forceQuoting;
        String strM2719toStringimpl = UShort.m2719toStringimpl(UShort.m2716constructorimpl(s));
        if (z) {
            printQuoted(strM2719toStringimpl);
        } else {
            print(strM2719toStringimpl);
        }
    }
}
