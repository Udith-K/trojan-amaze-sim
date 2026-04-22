package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;

/* JADX INFO: loaded from: classes2.dex */
public class CMSSignedDataGenerator extends CMSSignedGenerator {
    private boolean isDefiniteLength;
    private List signerInfs;

    public CMSSignedDataGenerator() {
        this.signerInfs = new ArrayList();
        this.isDefiniteLength = false;
    }

    public CMSSignedDataGenerator(DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        super(digestAlgorithmIdentifierFinder);
        this.signerInfs = new ArrayList();
        this.isDefiniteLength = false;
    }

    public CMSSignedData generate(CMSTypedData cMSTypedData) throws CMSException {
        return generate(cMSTypedData, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.bouncycastle.cms.CMSSignedData generate(org.bouncycastle.cms.CMSTypedData r12, boolean r13) throws org.bouncycastle.cms.CMSException {
        /*
            Method dump skipped, instruction units count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cms.CMSSignedDataGenerator.generate(org.bouncycastle.cms.CMSTypedData, boolean):org.bouncycastle.cms.CMSSignedData");
    }

    public SignerInformationStore generateCounterSigners(SignerInformation signerInformation) throws CMSException {
        return generate(new CMSProcessableByteArray(null, signerInformation.getSignature()), false).getSignerInfos();
    }

    public void setDefiniteLengthEncoding(boolean z) {
        this.isDefiniteLength = z;
    }
}
