package org.fdroid.index.v1;

import java.io.File;
import java.util.Set;
import java.util.jar.Attributes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.index.JarIndexVerifier;
import org.fdroid.index.SigningException;

/* JADX INFO: compiled from: IndexV1Verifier.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014R\u0014\u0010\t\u001a\u00020\u0005X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lorg/fdroid/index/v1/IndexV1Verifier;", "Lorg/fdroid/index/JarIndexVerifier;", "jarFile", "Ljava/io/File;", "expectedSigningCertificate", "", "expectedSigningFingerprint", "<init>", "(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;)V", "jsonFileName", "getJsonFileName", "()Ljava/lang/String;", "checkAttributes", "", "attributes", "Ljava/util/jar/Attributes;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IndexV1Verifier extends JarIndexVerifier {
    private final String jsonFileName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IndexV1Verifier(File jarFile, String str, String str2) {
        super(jarFile, str, str2);
        Intrinsics.checkNotNullParameter(jarFile, "jarFile");
        this.jsonFileName = IndexV1VerifierKt.DATA_FILE_NAME;
    }

    @Override // org.fdroid.index.JarIndexVerifier
    protected String getJsonFileName() {
        return this.jsonFileName;
    }

    @Override // org.fdroid.index.JarIndexVerifier
    protected void checkAttributes(Attributes attributes) throws SigningException {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        Set<Object> setKeySet = attributes.keySet();
        Intrinsics.checkNotNullExpressionValue(setKeySet, "<get-keys>(...)");
        for (Object obj : setKeySet) {
            if (!Intrinsics.areEqual(obj.toString(), "SHA1-Digest")) {
                throw new SigningException("Unsupported digest: " + obj);
            }
        }
    }
}
