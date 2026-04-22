package org.fdroid.fdroid.nearby;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Process;
import android.widget.Toast;
import androidx.core.app.JobIntentService;
import androidx.documentfile.provider.DocumentFile;
import java.security.CodeSigner;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.views.repos.AddRepoActivity;
import org.fdroid.index.SigningException;
import org.fdroid.index.v1.IndexV1UpdaterKt;

/* JADX INFO: loaded from: classes2.dex */
public class TreeUriScannerIntentService extends JobIntentService {
    private static final String ACTION_SCAN_TREE_URI = "org.fdroid.fdroid.nearby.action.SCAN_TREE_URI";
    public static final String EXTERNAL_STORAGE_PROVIDER_AUTHORITY = "com.android.externalstorage.documents";
    private static final int JOB_ID = -516977484;
    public static final String TAG = "TreeUriScannerIntentSer";

    public static void scan(Context context, Uri uri) {
        Intent intent = new Intent(context, (Class<?>) TreeUriScannerIntentService.class);
        intent.setAction(ACTION_SCAN_TREE_URI);
        intent.setData(uri);
        JobIntentService.enqueueWork(context, (Class<?>) TreeUriScannerIntentService.class, JOB_ID, intent);
    }

    public static void onActivityResult(Context context, Intent intent) {
        Uri data;
        if (intent == null || (data = intent.getData()) == null) {
            return;
        }
        context.getContentResolver().takePersistableUriPermission(data, 3);
        Toast.makeText(context, String.format(context.getString(R.string.swap_toast_using_path), data.toString()), 0).show();
        scan(context, data);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        Uri data;
        if (ACTION_SCAN_TREE_URI.equals(intent.getAction()) && (data = intent.getData()) != null) {
            Process.setThreadPriority(19);
            searchDirectory(DocumentFile.fromTreeUri(this, data));
        }
    }

    private void searchDirectory(DocumentFile documentFile) {
        DocumentFile[] documentFileArrListFiles = documentFile.listFiles();
        if (documentFileArrListFiles == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (DocumentFile documentFile2 : documentFileArrListFiles) {
            if (documentFile2.isDirectory()) {
                arrayList.add(documentFile2);
            } else if (!z && IndexV1UpdaterKt.SIGNED_FILE_NAME.equals(documentFile2.getName())) {
                registerRepo(documentFile2);
                z = true;
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            searchDirectory((DocumentFile) it.next());
        }
    }

    private void registerRepo(DocumentFile documentFile) {
        DocumentFile parentFile = documentFile.getParentFile();
        if (parentFile != null) {
            registerRepo(this, parentFile.getUri());
        }
    }

    static void registerRepo(Context context, Uri uri) {
        FDroidApp.getRepoManager(context).fetchRepositoryPreview(uri.toString());
        Intent intent = new Intent(context, (Class<?>) AddRepoActivity.class);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    static X509Certificate getSigningCertFromJar(JarEntry jarEntry) throws SigningException {
        CodeSigner[] codeSigners = jarEntry.getCodeSigners();
        if (codeSigners == null || codeSigners.length == 0) {
            throw new SigningException("No signature found in index");
        }
        if (codeSigners.length > 1) {
            throw new SigningException("index.jar must be signed by a single code signer!");
        }
        List<? extends Certificate> certificates = codeSigners[0].getSignerCertPath().getCertificates();
        if (certificates.size() != 1) {
            throw new SigningException("index.jar code signers must only have a single certificate!");
        }
        return (X509Certificate) certificates.get(0);
    }
}
