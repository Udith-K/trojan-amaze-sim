package org.bouncycastle.cms;

import java.io.IOException;
import org.bouncycastle.asn1.cms.KEKRecipientInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* JADX INFO: loaded from: classes2.dex */
public class KEKRecipientInformation extends RecipientInformation {

    /* JADX INFO: renamed from: info, reason: collision with root package name */
    private KEKRecipientInfo f72info;

    KEKRecipientInformation(KEKRecipientInfo kEKRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(kEKRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        this.f72info = kEKRecipientInfo;
        this.rid = new KEKRecipientId(kEKRecipientInfo.getKekid().getKeyIdentifier().getOctets());
    }

    @Override // org.bouncycastle.cms.RecipientInformation
    protected RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException, IOException {
        return ((KEKRecipient) recipient).getRecipientOperator(this.keyEncAlg, this.messageAlgorithm, this.f72info.getEncryptedKey().getOctets());
    }
}
