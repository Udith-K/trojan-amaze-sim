package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public interface CMSProcessable {
    Object getContent();

    void write(OutputStream outputStream) throws CMSException, IOException;
}
