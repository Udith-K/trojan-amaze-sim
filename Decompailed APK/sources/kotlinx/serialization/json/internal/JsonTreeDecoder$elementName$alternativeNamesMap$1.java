package kotlinx.serialization.json.internal;

import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* JADX INFO: compiled from: TreeJsonDecoder.kt */
/* JADX INFO: loaded from: classes2.dex */
/* synthetic */ class JsonTreeDecoder$elementName$alternativeNamesMap$1 extends FunctionReferenceImpl implements Function0 {
    JsonTreeDecoder$elementName$alternativeNamesMap$1(Object obj) {
        super(0, obj, JsonNamesMapKt.class, "buildAlternativeNamesMap", "buildAlternativeNamesMap(Lkotlinx/serialization/descriptors/SerialDescriptor;)Ljava/util/Map;", 1);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Map invoke() {
        return JsonNamesMapKt.buildAlternativeNamesMap((SerialDescriptor) this.receiver);
    }
}
