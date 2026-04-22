package org.acra.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;

/* JADX INFO: compiled from: StreamReader.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class StreamReader {
    public static final Companion Companion = new Companion(null);
    private static final Regex newline = new Regex("\\r?\\n");
    private Function1 filter;
    private final InputStream inputStream;
    private int limit;
    private int timeout;

    public StreamReader(InputStream inputStream, int i, int i2, Function1 function1) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        this.inputStream = inputStream;
        this.limit = i;
        this.timeout = i2;
        this.filter = function1;
    }

    public /* synthetic */ StreamReader(InputStream inputStream, int i, int i2, Function1 function1, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(inputStream, (i3 & 2) != 0 ? -1 : i, (i3 & 4) != 0 ? -1 : i2, (i3 & 8) != 0 ? null : function1);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StreamReader(String filename) {
        this(new File(filename));
        Intrinsics.checkNotNullParameter(filename, "filename");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public StreamReader(File file) {
        this(new FileInputStream(file), 0, 0, null, 14, null);
        Intrinsics.checkNotNullParameter(file, "file");
    }

    public final StreamReader setLimit(int i) {
        this.limit = i;
        return this;
    }

    public final StreamReader setTimeout(int i) {
        this.timeout = i;
        return this;
    }

    public final StreamReader setFilter(Function1 function1) {
        this.filter = function1;
        return this;
    }

    public final String read() {
        String fully = this.timeout == -1 ? readFully() : readWithTimeout();
        Function1 function1 = this.filter;
        if (function1 != null) {
            List listSplit = newline.split(fully, 0);
            List arrayList = new ArrayList();
            for (Object obj : listSplit) {
                if (((Boolean) function1.invoke(obj)).booleanValue()) {
                    arrayList.add(obj);
                }
            }
            int i = this.limit;
            if (i != -1) {
                arrayList = CollectionsKt.takeLast(arrayList, i);
            }
            String strJoinToString$default = CollectionsKt.joinToString$default(arrayList, "\n", null, null, 0, null, null, 62, null);
            if (strJoinToString$default != null) {
                return strJoinToString$default;
            }
        }
        return this.limit == -1 ? fully : CollectionsKt.joinToString$default(CollectionsKt.takeLast(newline.split(fully, 0), this.limit), "\n", null, null, 0, null, null, 62, null);
    }

    private final String readFully() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream, Charsets.UTF_8), 8192);
        try {
            String text = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            return text;
        } finally {
        }
    }

    private final String readWithTimeout() {
        long jCurrentTimeMillis = System.currentTimeMillis() + ((long) this.timeout);
        InputStream inputStream = this.inputStream;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            while (true) {
                int until = readUntil(inputStream, bArr, jCurrentTimeMillis);
                if (until != -1) {
                    byteArrayOutputStream.write(bArr, 0, until);
                } else {
                    String string = byteArrayOutputStream.toString();
                    CloseableKt.closeFinally(inputStream, null);
                    Intrinsics.checkNotNullExpressionValue(string, "use(...)");
                    return string;
                }
            }
        } finally {
        }
    }

    private final int readUntil(InputStream inputStream, byte[] bArr, long j) {
        int i;
        int i2 = 0;
        while (System.currentTimeMillis() < j && i2 < bArr.length && (i = inputStream.read(bArr, i2, Math.min(this.inputStream.available(), bArr.length - i2))) != -1) {
            i2 += i;
        }
        return i2;
    }

    /* JADX INFO: compiled from: StreamReader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
