package org.fdroid.index;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSigner;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarIndexVerifier.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH$J,\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u0002H\u00120\u0014J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Lorg/fdroid/index/JarIndexVerifier;", "", "jarFile", "Ljava/io/File;", "expectedSigningCertificate", "", "expectedSigningFingerprint", "<init>", "(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;)V", "jsonFileName", "getJsonFileName", "()Ljava/lang/String;", "checkAttributes", "", "attributes", "Ljava/util/jar/Attributes;", "getStreamAndVerify", "Lkotlin/Pair;", "T", "certificateAndStream", "Lkotlin/Function1;", "Ljava/io/InputStream;", "getX509Certificate", "Ljava/security/cert/X509Certificate;", "jarEntry", "Ljava/util/jar/JarEntry;", "verifyAndGetSigningCertificate", "rawCertFromJar", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class JarIndexVerifier {
    private final String expectedSigningCertificate;
    private final String expectedSigningFingerprint;
    private final File jarFile;

    protected abstract void checkAttributes(Attributes attributes) throws SigningException;

    protected abstract String getJsonFileName();

    public JarIndexVerifier(File jarFile, String str, String str2) {
        Intrinsics.checkNotNullParameter(jarFile, "jarFile");
        this.jarFile = jarFile;
        this.expectedSigningCertificate = str;
        this.expectedSigningFingerprint = str2;
        if (str != null && str.length() <= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (str2 != null && str2.length() <= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (str != null && str2 != null) {
            throw new IllegalArgumentException("Providing a signing certificate and a fingerprint makes no sense.");
        }
    }

    public final <T> Pair getStreamAndVerify(Function1 certificateAndStream) throws IOException, SigningException {
        Intrinsics.checkNotNullParameter(certificateAndStream, "certificateAndStream");
        JarFile jarFile = new JarFile(this.jarFile, true);
        try {
            ZipEntry entry = jarFile.getEntry(getJsonFileName());
            JarEntry jarEntry = entry instanceof JarEntry ? (JarEntry) entry : null;
            if (jarEntry == null) {
                throw new SigningException("No entry for " + getJsonFileName());
            }
            if (jarEntry.getAttributes() == null) {
                throw new SigningException("No attributes for " + getJsonFileName());
            }
            Attributes attributes = jarEntry.getAttributes();
            Intrinsics.checkNotNullExpressionValue(attributes, "getAttributes(...)");
            checkAttributes(attributes);
            try {
                InputStream inputStream = jarFile.getInputStream(jarEntry);
                try {
                    Intrinsics.checkNotNull(inputStream);
                    Object objInvoke = certificateAndStream.invoke(inputStream);
                    CloseableKt.closeFinally(inputStream, null);
                    Pair pair = new Pair(verifyAndGetSigningCertificate(getX509Certificate(jarEntry)), objInvoke);
                    CloseableKt.closeFinally(jarFile, null);
                    return pair;
                } finally {
                }
            } catch (SecurityException e) {
                throw new SigningException(e);
            }
        } finally {
        }
    }

    private final X509Certificate getX509Certificate(JarEntry jarEntry) throws SigningException {
        CodeSigner[] codeSigners = jarEntry.getCodeSigners();
        if (codeSigners == null || codeSigners.length == 0) {
            throw new SigningException("No signature found in index, did you read stream until end?");
        }
        if (codeSigners.length != 1) {
            throw new SigningException("index.jar must be signed by a single code signer");
        }
        List<? extends Certificate> certificates = codeSigners[0].getSignerCertPath().getCertificates();
        if (certificates.size() != 1) {
            throw new SigningException("index.jar code signers must only have a single certificate");
        }
        Certificate certificate = certificates.get(0);
        Intrinsics.checkNotNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
        return (X509Certificate) certificate;
    }

    private final String verifyAndGetSigningCertificate(X509Certificate rawCertFromJar) throws SigningException, CertificateEncodingException {
        IndexUtils indexUtils = IndexUtils.INSTANCE;
        byte[] encoded = rawCertFromJar.getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "getEncoded(...)");
        String hex$index_release = indexUtils.toHex$index_release(encoded);
        if (hex$index_release.length() == 0) {
            throw new SigningException("No signing certificate");
        }
        if (hex$index_release.length() < 512) {
            throw new SigningException("Certificate size of " + (hex$index_release.length() / 2) + " is too short.");
        }
        if (this.expectedSigningCertificate == null && this.expectedSigningFingerprint != null) {
            byte[] encoded2 = rawCertFromJar.getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded2, "getEncoded(...)");
            if (!Intrinsics.areEqual(this.expectedSigningFingerprint, indexUtils.toHex$index_release(indexUtils.sha256$index_release(encoded2)))) {
                throw new SigningException("Expected certificate fingerprint does not match");
            }
        }
        String str = this.expectedSigningCertificate;
        if (str == null || Intrinsics.areEqual(str, hex$index_release)) {
            return hex$index_release;
        }
        throw new SigningException("Signing certificate does not match");
    }
}
