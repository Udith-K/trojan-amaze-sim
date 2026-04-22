package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
interface CMSReadable {
    InputStream getInputStream() throws CMSException, IOException;
}
