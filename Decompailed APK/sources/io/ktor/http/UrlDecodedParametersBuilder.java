package io.ktor.http;

import io.ktor.util.StringValues;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UrlDecodedParametersBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class UrlDecodedParametersBuilder implements ParametersBuilder {
    private final boolean caseInsensitiveName;
    private final ParametersBuilder encodedParametersBuilder;

    public UrlDecodedParametersBuilder(ParametersBuilder encodedParametersBuilder) {
        Intrinsics.checkNotNullParameter(encodedParametersBuilder, "encodedParametersBuilder");
        this.encodedParametersBuilder = encodedParametersBuilder;
        this.caseInsensitiveName = encodedParametersBuilder.getCaseInsensitiveName();
    }

    @Override // io.ktor.http.ParametersBuilder
    public Parameters build() {
        return UrlDecodedParametersBuilderKt.decodeParameters(this.encodedParametersBuilder);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public boolean getCaseInsensitiveName() {
        return this.caseInsensitiveName;
    }

    @Override // io.ktor.util.StringValuesBuilder
    public List getAll(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ArrayList arrayList = null;
        List all = this.encodedParametersBuilder.getAll(CodecsKt.encodeURLParameter$default(name, false, 1, null));
        if (all != null) {
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(all, 10));
            Iterator it = all.iterator();
            while (it.hasNext()) {
                arrayList.add(CodecsKt.decodeURLQueryComponent$default((String) it.next(), 0, 0, true, null, 11, null));
            }
        }
        return arrayList;
    }

    @Override // io.ktor.util.StringValuesBuilder
    public Set names() {
        Set setNames = this.encodedParametersBuilder.names();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setNames, 10));
        Iterator it = setNames.iterator();
        while (it.hasNext()) {
            arrayList.add(CodecsKt.decodeURLQueryComponent$default((String) it.next(), 0, 0, false, null, 15, null));
        }
        return CollectionsKt.toSet(arrayList);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public boolean isEmpty() {
        return this.encodedParametersBuilder.isEmpty();
    }

    @Override // io.ktor.util.StringValuesBuilder
    public Set entries() {
        return UrlDecodedParametersBuilderKt.decodeParameters(this.encodedParametersBuilder).entries();
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void append(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this.encodedParametersBuilder.append(CodecsKt.encodeURLParameter$default(name, false, 1, null), CodecsKt.encodeURLParameterValue(value));
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void appendAll(StringValues stringValues) {
        Intrinsics.checkNotNullParameter(stringValues, "stringValues");
        UrlDecodedParametersBuilderKt.appendAllEncoded(this.encodedParametersBuilder, stringValues);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void appendAll(String name, Iterable values) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(values, "values");
        ParametersBuilder parametersBuilder = this.encodedParametersBuilder;
        String strEncodeURLParameter$default = CodecsKt.encodeURLParameter$default(name, false, 1, null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));
        Iterator it = values.iterator();
        while (it.hasNext()) {
            arrayList.add(CodecsKt.encodeURLParameterValue((String) it.next()));
        }
        parametersBuilder.appendAll(strEncodeURLParameter$default, arrayList);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void clear() {
        this.encodedParametersBuilder.clear();
    }
}
