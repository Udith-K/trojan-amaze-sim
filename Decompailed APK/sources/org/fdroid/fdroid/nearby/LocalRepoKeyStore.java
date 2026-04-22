package org.fdroid.fdroid.nearby;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;
import kellinwood.security.zipsigner.ZipSigner;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Utils;

/* JADX INFO: loaded from: classes2.dex */
public final class LocalRepoKeyStore {
    private static final String DEFAULT_INDEX_CERT_INFO = "O=Kerplapp,OU=GuardianProject";
    private static final String DEFAULT_KEY_ALGO = "RSA";
    private static final int DEFAULT_KEY_BITS = 2048;
    public static final String DEFAULT_SIG_ALG = "SHA1withRSA";
    private static final String HTTP_CERT_ALIAS = "https";
    private static final String INDEX_CERT_ALIAS = "fdroid";
    private static final String TAG = "LocalRepoKeyStore";
    private static LocalRepoKeyStore localRepoKeyStore;
    private KeyManager[] keyManagers;
    private KeyStore keyStore;
    private File keyStoreFile;

    public static LocalRepoKeyStore get(Context context) throws InitException {
        if (localRepoKeyStore == null) {
            localRepoKeyStore = new LocalRepoKeyStore(context);
        }
        return localRepoKeyStore;
    }

    public static class InitException extends Exception {
        public InitException(String str) {
            super(str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v6, types: [org.fdroid.fdroid.nearby.LocalRepoKeyStore$KerplappKeyManager-IA] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.Throwable, org.bouncycastle.operator.OperatorCreationException] */
    private LocalRepoKeyStore(Context context) throws InitException {
        try {
            File dir = context.getDir("keystore", 0);
            Utils.debugLog(TAG, "Generating LocalRepoKeyStore instance: " + dir.getAbsolutePath());
            this.keyStoreFile = new File(dir, "kerplapp.bks");
            Utils.debugLog(TAG, "Using default KeyStore type: " + KeyStore.getDefaultType());
            this.keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            ?? r3 = 0;
            r3 = 0;
            if (this.keyStoreFile.exists()) {
                try {
                    try {
                        Utils.debugLog(TAG, "Keystore already exists, loading...");
                        FileInputStream fileInputStream = new FileInputStream(this.keyStoreFile);
                        try {
                            this.keyStore.load(fileInputStream, "".toCharArray());
                            Utils.closeQuietly(fileInputStream);
                        } catch (IOException e) {
                            e = e;
                            r3 = fileInputStream;
                            Log.e(TAG, "Error while loading existing keystore. Will delete and create a new one.");
                            throw new InitException("Could not initialize local repo keystore: " + e);
                        } catch (Throwable th) {
                            th = th;
                            r3 = fileInputStream;
                            Utils.closeQuietly(r3);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e2) {
                    e = e2;
                }
            }
            if (!this.keyStoreFile.exists()) {
                Utils.debugLog(TAG, "Keystore doesn't exist, creating...");
                this.keyStore.load(null, "".toCharArray());
            }
            if (this.keyStore.getKey(INDEX_CERT_ALIAS, "".toCharArray()) == null) {
                KeyPair keyPairGenerateRandomKeypair = generateRandomKeypair();
                addToStore(INDEX_CERT_ALIAS, keyPairGenerateRandomKeypair, generateSelfSignedCertChain(keyPairGenerateRandomKeypair, new X500Name(DEFAULT_INDEX_CERT_INFO)));
            }
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(this.keyStore, "".toCharArray());
            this.keyManagers = new KeyManager[]{new KerplappKeyManager((X509KeyManager) keyManagerFactory.getKeyManagers()[0])};
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException | OperatorCreationException e3) {
            Log.e(TAG, "Error loading keystore", e3);
        }
    }

    public void setupHTTPSCertificate() {
        try {
            KeyPair kerplappKeypair = getKerplappKeypair();
            addToStore(HTTP_CERT_ALIAS, kerplappKeypair, generateSelfSignedCertChain(kerplappKeypair, new X500Name("CN=" + FDroidApp.ipAddressString), FDroidApp.ipAddressString));
        } catch (Exception e) {
            Log.e(TAG, "Failed to setup HTTPS certificate", e);
        }
    }

    public File getKeyStoreFile() {
        return this.keyStoreFile;
    }

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public KeyManager[] getKeyManagers() {
        return this.keyManagers;
    }

    public void signZip(File file, File file2) {
        try {
            ZipSigner zipSigner = new ZipSigner();
            zipSigner.setKeys("kerplapp", (X509Certificate) this.keyStore.getCertificate(INDEX_CERT_ALIAS), getKerplappKeypair().getPrivate(), DEFAULT_SIG_ALG, null);
            zipSigner.signZip(file.getAbsolutePath(), file2.getAbsolutePath());
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | GeneralSecurityException e) {
            Log.e(TAG, "Unable to sign local repo index", e);
        }
    }

    private KeyPair getKerplappKeypair() throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
        Key key = this.keyStore.getKey(INDEX_CERT_ALIAS, "".toCharArray());
        if (key instanceof PrivateKey) {
            return new KeyPair(this.keyStore.getCertificate(INDEX_CERT_ALIAS).getPublicKey(), (PrivateKey) key);
        }
        return null;
    }

    public Certificate getCertificate() {
        try {
            if (this.keyStore.getKey(INDEX_CERT_ALIAS, "".toCharArray()) instanceof PrivateKey) {
                return this.keyStore.getCertificate(INDEX_CERT_ALIAS);
            }
            return null;
        } catch (GeneralSecurityException e) {
            Log.e(TAG, "Unable to get certificate for local repo", e);
            return null;
        }
    }

    private void addToStore(String str, KeyPair keyPair, Certificate certificate) throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyStoreException, CertificateException {
        this.keyStore.setKeyEntry(str, keyPair.getPrivate(), "".toCharArray(), new Certificate[]{certificate});
        this.keyStore.store(new FileOutputStream(this.keyStoreFile), "".toCharArray());
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(this.keyStore, "".toCharArray());
        this.keyManagers = new KeyManager[]{new KerplappKeyManager((X509KeyManager) keyManagerFactory.getKeyManagers()[0])};
    }

    public static KeyPair generateRandomKeypair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(DEFAULT_KEY_ALGO);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static Certificate generateSelfSignedCertChain(KeyPair keyPair, X500Name x500Name) throws OperatorCreationException, IOException, CertificateException {
        return generateSelfSignedCertChain(keyPair, x500Name, null);
    }

    public static Certificate generateSelfSignedCertChain(KeyPair keyPair, X500Name x500Name, String str) throws OperatorCreationException, IOException, CertificateException {
        SecureRandom secureRandom = new SecureRandom();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        ContentSigner contentSignerBuild = new JcaContentSignerBuilder(DEFAULT_SIG_ALG).build(privateKey);
        SubjectPublicKeyInfo subjectPublicKeyInfo = new SubjectPublicKeyInfo(ASN1Sequence.getInstance(publicKey.getEncoded()));
        Date date = new Date();
        Locale locale = Locale.ENGLISH;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(locale);
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(1, 1);
        X509v3CertificateBuilder x509v3CertificateBuilder = new X509v3CertificateBuilder(x500Name, BigInteger.valueOf(secureRandom.nextLong()), new Time(date, locale), new Time(gregorianCalendar.getTime(), locale), x500Name, subjectPublicKeyInfo);
        if (str != null) {
            x509v3CertificateBuilder.addExtension(X509Extension.subjectAlternativeName, false, (ASN1Encodable) new GeneralNames(new GeneralName(7, str)));
        }
        return new JcaX509CertificateConverter().getCertificate(x509v3CertificateBuilder.build(contentSignerBuild));
    }

    private static final class KerplappKeyManager implements X509KeyManager {
        private final X509KeyManager wrapped;

        private KerplappKeyManager(X509KeyManager x509KeyManager) {
            this.wrapped = x509KeyManager;
        }

        @Override // javax.net.ssl.X509KeyManager
        public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
            return this.wrapped.chooseClientAlias(strArr, principalArr, socket);
        }

        @Override // javax.net.ssl.X509KeyManager
        public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
            return LocalRepoKeyStore.HTTP_CERT_ALIAS;
        }

        @Override // javax.net.ssl.X509KeyManager
        public X509Certificate[] getCertificateChain(String str) {
            return this.wrapped.getCertificateChain(str);
        }

        @Override // javax.net.ssl.X509KeyManager
        public String[] getClientAliases(String str, Principal[] principalArr) {
            return this.wrapped.getClientAliases(str, principalArr);
        }

        @Override // javax.net.ssl.X509KeyManager
        public PrivateKey getPrivateKey(String str) {
            return this.wrapped.getPrivateKey(str);
        }

        @Override // javax.net.ssl.X509KeyManager
        public String[] getServerAliases(String str, Principal[] principalArr) {
            return this.wrapped.getServerAliases(str, principalArr);
        }
    }
}
