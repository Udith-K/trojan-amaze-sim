package org.bouncycastle.est;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface ESTClient {
    ESTResponse doRequest(ESTRequest eSTRequest) throws IOException;
}
