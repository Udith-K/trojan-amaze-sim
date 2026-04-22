package kotlinx.serialization.json;

import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;

/* JADX INFO: compiled from: JsonEncoder.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface JsonEncoder extends Encoder, CompositeEncoder {
    Json getJson();
}
