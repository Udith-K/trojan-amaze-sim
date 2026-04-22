package io.ktor.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StringValues.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StringValuesBuilderImpl implements StringValuesBuilder {
    private final boolean caseInsensitiveName;
    private final Map values;

    protected void validateName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
    }

    protected void validateValue(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
    }

    public StringValuesBuilderImpl(boolean z, int i) {
        this.caseInsensitiveName = z;
        this.values = z ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap(i);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public final boolean getCaseInsensitiveName() {
        return this.caseInsensitiveName;
    }

    protected final Map getValues() {
        return this.values;
    }

    @Override // io.ktor.util.StringValuesBuilder
    public List getAll(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return (List) this.values.get(name);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public Set names() {
        return this.values.keySet();
    }

    @Override // io.ktor.util.StringValuesBuilder
    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    @Override // io.ktor.util.StringValuesBuilder
    public Set entries() {
        return CollectionsJvmKt.unmodifiable(this.values.entrySet());
    }

    public void set(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        validateValue(value);
        List listEnsureListForKey = ensureListForKey(name);
        listEnsureListForKey.clear();
        listEnsureListForKey.add(value);
    }

    public String get(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List all = getAll(name);
        if (all != null) {
            return (String) kotlin.collections.CollectionsKt.firstOrNull(all);
        }
        return null;
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void append(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        validateValue(value);
        ensureListForKey(name).add(value);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void appendAll(StringValues stringValues) {
        Intrinsics.checkNotNullParameter(stringValues, "stringValues");
        stringValues.forEach(new Function2() { // from class: io.ktor.util.StringValuesBuilderImpl.appendAll.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((String) obj, (List) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(String name, List values) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(values, "values");
                StringValuesBuilderImpl.this.appendAll(name, values);
            }
        });
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void appendAll(String name, Iterable values) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(values, "values");
        List listEnsureListForKey = ensureListForKey(name);
        Iterator it = values.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            validateValue(str);
            listEnsureListForKey.add(str);
        }
    }

    public void remove(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.values.remove(name);
    }

    @Override // io.ktor.util.StringValuesBuilder
    public void clear() {
        this.values.clear();
    }

    private final List ensureListForKey(String str) {
        List list = (List) this.values.get(str);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        validateName(str);
        this.values.put(str, arrayList);
        return arrayList;
    }
}
