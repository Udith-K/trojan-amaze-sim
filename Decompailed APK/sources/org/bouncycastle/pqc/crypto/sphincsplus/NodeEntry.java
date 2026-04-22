package org.bouncycastle.pqc.crypto.sphincsplus;

/* JADX INFO: loaded from: classes2.dex */
class NodeEntry {
    final int nodeHeight;
    final byte[] nodeValue;

    NodeEntry(byte[] bArr, int i) {
        this.nodeValue = bArr;
        this.nodeHeight = i;
    }
}
