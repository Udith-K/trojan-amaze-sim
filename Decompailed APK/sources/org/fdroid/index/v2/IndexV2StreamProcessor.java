package org.fdroid.index.v2;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: IndexV2StreamProcessor.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\tH&¨\u0006\u000b"}, d2 = {"Lorg/fdroid/index/v2/IndexV2StreamProcessor;", "", "process", "", "version", "", "inputStream", "Ljava/io/InputStream;", "onAppProcessed", "Lkotlin/Function1;", "", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface IndexV2StreamProcessor {
    void process(long version, InputStream inputStream, Function1 onAppProcessed);
}
