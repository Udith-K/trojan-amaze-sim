package org.bouncycastle.mime;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface MimeMultipartContext extends MimeContext {
    MimeContext createContext(int i) throws IOException;
}
