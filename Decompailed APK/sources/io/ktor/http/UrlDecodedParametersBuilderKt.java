package io.ktor.http;

import io.ktor.util.StringValues;
import io.ktor.util.StringValuesBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UrlDecodedParametersBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class UrlDecodedParametersBuilderKt {
    public static final Parameters decodeParameters(StringValuesBuilder parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        appendAllDecoded(parametersBuilderParametersBuilder$default, parameters);
        return parametersBuilderParametersBuilder$default.build();
    }

    public static final ParametersBuilder encodeParameters(StringValues parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        appendAllEncoded(parametersBuilderParametersBuilder$default, parameters);
        return parametersBuilderParametersBuilder$default;
    }

    private static final void appendAllDecoded(StringValuesBuilder stringValuesBuilder, StringValuesBuilder stringValuesBuilder2) {
        for (String str : stringValuesBuilder2.names()) {
            List all = stringValuesBuilder2.getAll(str);
            if (all == null) {
                all = CollectionsKt.emptyList();
            }
            String strDecodeURLQueryComponent$default = CodecsKt.decodeURLQueryComponent$default(str, 0, 0, false, null, 15, null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(all, 10));
            Iterator it = all.iterator();
            while (it.hasNext()) {
                arrayList.add(CodecsKt.decodeURLQueryComponent$default((String) it.next(), 0, 0, true, null, 11, null));
            }
            stringValuesBuilder.appendAll(strDecodeURLQueryComponent$default, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void appendAllEncoded(StringValuesBuilder stringValuesBuilder, StringValues stringValues) {
        for (String str : stringValues.names()) {
            List all = stringValues.getAll(str);
            if (all == null) {
                all = CollectionsKt.emptyList();
            }
            String strEncodeURLParameter$default = CodecsKt.encodeURLParameter$default(str, false, 1, null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(all, 10));
            Iterator it = all.iterator();
            while (it.hasNext()) {
                arrayList.add(CodecsKt.encodeURLParameterValue((String) it.next()));
            }
            stringValuesBuilder.appendAll(strEncodeURLParameter$default, arrayList);
        }
    }
}
