package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* JADX INFO: loaded from: classes2.dex */
public class KeyTransRecipientInformation extends RecipientInformation {

    /* JADX INFO: renamed from: info, reason: collision with root package name */
    private KeyTransRecipientInfo f74info;

    KeyTransRecipientInformation(KeyTransRecipientInfo keyTransRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        KeyTransRecipientId keyTransRecipientId;
        super(keyTransRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        this.f74info = keyTransRecipientInfo;
        RecipientIdentifier recipientIdentifier = keyTransRecipientInfo.getRecipientIdentifier();
        boolean zIsTagged = recipientIdentifier.isTagged();
        ASN1Encodable id = recipientIdentifier.getId();
        if (zIsTagged) {
            keyTransRecipientId = new KeyTransRecipientId(ASN1OctetString.getInstance(id).getOctets());
        } else {
            IssuerAndSerialNumber issuerAndSerialNumber = IssuerAndSerialNumber.getInstance(id);
            keyTransRecipientId = new KeyTransRecipientId(issuerAndSerialNumber.getName(), issuerAndSerialNumber.getSerialNumber().getValue());
        }
        this.rid = keyTransRecipientId;
    }

    @Override // org.bouncycastle.cms.RecipientInformation
    protected RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException {
        return ((KeyTransRecipient) recipient).getRecipientOperator(this.keyEncAlg, this.messageAlgorithm, this.f74info.getEncryptedKey().getOctets());
    }
}
