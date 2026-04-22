package org.bouncycastle.mime;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public interface MimeParserProvider {
    MimeParser createParser(InputStream inputStream) throws IOException;

    MimeParser createParser(Headers headers, InputStream inputStream) throws IOException;
}
