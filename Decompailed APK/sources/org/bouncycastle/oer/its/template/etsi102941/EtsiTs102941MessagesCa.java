package org.bouncycastle.oer.its.template.etsi102941;

import org.bouncycastle.oer.OERDefinition;
import org.bouncycastle.oer.its.template.etsi102941.basetypes.EtsiTs102941BaseTypes;
import org.bouncycastle.oer.its.template.etsi103097.EtsiTs103097Module;

/* JADX INFO: loaded from: classes2.dex */
public class EtsiTs102941MessagesCa {
    public static final OERDefinition.Builder AuthorizationRequestMessage;
    public static final OERDefinition.Builder AuthorizationRequestMessageWithPop;
    public static final OERDefinition.Builder AuthorizationResponseMessage;
    public static final OERDefinition.Builder AuthorizationValidationRequestMessage;
    public static final OERDefinition.Builder CaCertificateRekeyingMessage;
    public static final OERDefinition.Builder CaCertificateRequestMessage;
    public static final OERDefinition.Builder CertificateRevocationListMessage;
    public static final OERDefinition.Builder EnrolmentRequestMessage;
    public static final OERDefinition.Builder EnrolmentResponseMessage;
    public static final OERDefinition.Builder EtsiTs102941Data;
    public static final OERDefinition.Builder EtsiTs102941DataContent;
    public static final OERDefinition.Builder RcaCertificateTrustListMessage;
    public static final OERDefinition.Builder RcaDoubleSignedLinkCertificateMessage;
    public static final OERDefinition.Builder RcaSingleSignedLinkCertificateMessage;
    public static final OERDefinition.Builder TlmCertificateTrustListMessage;
    public static final OERDefinition.Builder TlmLinkCertificateMessage;

    static {
        OERDefinition.Builder builder = EtsiTs103097Module.EtsiTs103097Data_SignedAndEncrypted_Unicast;
        EnrolmentRequestMessage = builder.typeName("EnrolmentRequestMessage");
        EnrolmentResponseMessage = builder.typeName("EnrolmentResponseMessage");
        AuthorizationRequestMessage = EtsiTs103097Module.EtsiTs103097Data_Encrypted_Unicast.typeName("AuthorizationRequestMessage");
        AuthorizationRequestMessageWithPop = builder.typeName("AuthorizationRequestMessageWithPop");
        AuthorizationResponseMessage = builder.typeName("AuthorizationResponseMessage");
        OERDefinition.Builder builder2 = EtsiTs103097Module.EtsiTs103097Data_Signed;
        CertificateRevocationListMessage = builder2.typeName("CertificateRevocationListMessage");
        TlmCertificateTrustListMessage = builder2.typeName("TlmCertificateTrustListMessage");
        RcaCertificateTrustListMessage = builder2.typeName("RcaCertificateTrustListMessage");
        OERDefinition.Builder builderTypeName = builder2.typeName("RcaSingleSignedLinkCertificateMessage");
        RcaSingleSignedLinkCertificateMessage = builderTypeName;
        OERDefinition.Builder builderTypeName2 = OERDefinition.choice(EtsiTs102941TypesEnrolment.InnerEcRequestSignedForPop.label("enrolmentRequest"), EtsiTs102941TypesEnrolment.InnerEcResponse.label("enrolmentResponse"), EtsiTs102941TypesAuthorization.InnerAtRequest.label("authorizationRequest"), EtsiTs102941TypesAuthorization.InnerAtResponse.label("authorizationResponse"), EtsiTs102941TrustLists.ToBeSignedCrl.label("certificateRevocationList"), EtsiTs102941TrustLists.ToBeSignedTlmCtl.label("certificateTrustListTlm"), EtsiTs102941TrustLists.ToBeSignedRcaCtl.label("certificateTrustListRca"), EtsiTs102941TypesAuthorizationValidation.AuthorizationValidationRequest.label("authorizationValidationRequest"), EtsiTs102941TypesAuthorizationValidation.AuthorizationValidationResponse.label("authorizationValidationResponse"), EtsiTs102941TypesCaManagement.CaCertificateRequest.label("caCertificateRequest"), OERDefinition.extension(EtsiTs102941TypesLinkCertificate.ToBeSignedLinkCertificateTlm.label("linkCertificateTlm"), EtsiTs102941TypesLinkCertificate.ToBeSignedLinkCertificateRca.label("singleSignedLinkCertificateRca"), builderTypeName.label("doubleSignedlinkCertificateRca"))).typeName("EtsiTs102941DataContent");
        EtsiTs102941DataContent = builderTypeName2;
        EtsiTs102941Data = OERDefinition.seq(EtsiTs102941BaseTypes.Version.label("version"), builderTypeName2.label("content")).typeName("EtsiTs102941Data");
        AuthorizationValidationRequestMessage = builder.typeName("AuthorizationValidationRequestMessage");
        CaCertificateRequestMessage = builder2.typeName("CaCertificateRequestMessage");
        CaCertificateRekeyingMessage = builder2.typeName("CaCertificateRekeyingMessage");
        TlmLinkCertificateMessage = builder2.typeName("TlmLinkCertificateMessage");
        RcaDoubleSignedLinkCertificateMessage = builder2.typeName("RcaDoubleSignedLinkCertificateMessage");
    }
}
