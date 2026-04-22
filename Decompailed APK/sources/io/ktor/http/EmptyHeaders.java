package io.ktor.http;

import io.ktor.http.Headers;
import java.util.List;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Headers.kt */
/* JADX INFO: loaded from: classes.dex */
public final class EmptyHeaders implements Headers {
    public static final EmptyHeaders INSTANCE = new EmptyHeaders();

    @Override // io.ktor.util.StringValues
    public List getAll(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return null;
    }

    @Override // io.ktor.util.StringValues
    public boolean getCaseInsensitiveName() {
        return true;
    }

    private EmptyHeaders() {
    }

    @Override // io.ktor.util.StringValues
    public void forEach(Function2 function2) {
        Headers.DefaultImpls.forEach(this, function2);
    }

    @Override // io.ktor.util.StringValues
    public String get(String str) {
        return Headers.DefaultImpls.get(this, str);
    }

    @Override // io.ktor.util.StringValues
    public Set names() {
        return SetsKt.emptySet();
    }

    @Override // io.ktor.util.StringValues
    public Set entries() {
        return SetsKt.emptySet();
    }

    public String toString() {
        return "Headers " + entries();
    }
}
