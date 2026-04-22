package org.bouncycastle.tsp.ers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bouncycastle.operator.DigestCalculator;

/* JADX INFO: loaded from: classes2.dex */
public class ERSFileData extends ERSCachingData {
    private final File content;

    public ERSFileData(File file) throws FileNotFoundException {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("directory not allowed as ERSFileData");
        }
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath() + " does not exist");
        }
        if (file.canRead()) {
            this.content = file;
            return;
        }
        throw new FileNotFoundException(file.getAbsolutePath() + " is not readable");
    }

    @Override // org.bouncycastle.tsp.ers.ERSCachingData
    protected byte[] calculateHash(DigestCalculator digestCalculator) {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.content);
            byte[] bArrCalculateDigest = ERSUtil.calculateDigest(digestCalculator, fileInputStream);
            fileInputStream.close();
            return bArrCalculateDigest;
        } catch (IOException unused) {
            throw new IllegalStateException("unable to process " + this.content.getAbsolutePath());
        }
    }
}
