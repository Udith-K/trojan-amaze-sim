package io.ktor.util;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StringValues.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StringValuesImpl implements StringValues {
    private final boolean caseInsensitiveName;
    private final Map values;

    public StringValuesImpl(boolean z, Map values) {
        Intrinsics.checkNotNullParameter(values, "values");
        this.caseInsensitiveName = z;
        Map mapCaseInsensitiveMap = z ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap();
        for (Map.Entry entry : values.entrySet()) {
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList.add((String) list.get(i));
            }
            mapCaseInsensitiveMap.put(str, arrayList);
        }
        this.values = mapCaseInsensitiveMap;
    }

    @Override // io.ktor.util.StringValues
    public final boolean getCaseInsensitiveName() {
        return this.caseInsensitiveName;
    }

    @Override // io.ktor.util.StringValues
    public String get(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List listListForKey = listForKey(name);
        if (listListForKey != null) {
            return (String) kotlin.collections.CollectionsKt.firstOrNull(listListForKey);
        }
        return null;
    }

    @Override // io.ktor.util.StringValues
    public List getAll(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return listForKey(name);
    }

    @Override // io.ktor.util.StringValues
    public Set names() {
        return CollectionsJvmKt.unmodifiable(this.values.keySet());
    }

    @Override // io.ktor.util.StringValues
    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    @Override // io.ktor.util.StringValues
    public Set entries() {
        return CollectionsJvmKt.unmodifiable(this.values.entrySet());
    }

    @Override // io.ktor.util.StringValues
    public void forEach(Function2 body) {
        Intrinsics.checkNotNullParameter(body, "body");
        for (Map.Entry entry : this.values.entrySet()) {
            body.invoke((String) entry.getKey(), (List) entry.getValue());
        }
    }

    private final List listForKey(String str) {
        return (List) this.values.get(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StringValues)) {
            return false;
        }
        StringValues stringValues = (StringValues) obj;
        if (this.caseInsensitiveName != stringValues.getCaseInsensitiveName()) {
            return false;
        }
        return StringValuesKt.entriesEquals(entries(), stringValues.entries());
    }

    public int hashCode() {
        return StringValuesKt.entriesHashCode(entries(), ChangeSize$$ExternalSyntheticBackport0.m(this.caseInsensitiveName) * 31);
    }
}
