package org.fdroid.fdroid.net;

import android.annotation.SuppressLint;
import android.net.Uri;
import ch.qos.logback.core.joran.action.Action;
import info.guardianproject.netcipher.NetCipher;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.fdroid.IndexFile;
import org.fdroid.database.Repository;
import org.fdroid.download.DownloadRequest;
import org.fdroid.download.Downloader;
import org.fdroid.download.HttpDownloader;
import org.fdroid.download.HttpDownloaderV2;
import org.fdroid.download.HttpManager;
import org.fdroid.download.Mirror;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.Utils;
import org.fdroid.index.IndexFormatVersion;

/* JADX INFO: loaded from: classes2.dex */
public class DownloaderFactory extends org.fdroid.download.DownloaderFactory {
    private static final String TAG = "DownloaderFactory";
    public static final DownloaderFactory INSTANCE = new DownloaderFactory();
    public static final HttpManager HTTP_MANAGER = new HttpManager(Utils.getUserAgent(), FDroidApp.queryString, NetCipher.getProxy(), new DnsWithCache(), new FDroidMirrorParameterManager());

    @Override // org.fdroid.download.DownloaderFactory
    public Downloader create(Repository repository, Uri uri, IndexFile indexFile, File file) throws IOException {
        return create(repository, repository.getMirrors(), uri, indexFile, file, null);
    }

    @Override // org.fdroid.download.DownloaderFactory
    @SuppressLint({"MissingPermission"})
    protected Downloader create(Repository repository, List<Mirror> list, Uri uri, IndexFile indexFile, File file, Mirror mirror) throws IOException {
        String scheme = uri.getScheme();
        if (BluetoothDownloader.SCHEME.equals(scheme)) {
            return new BluetoothDownloader(uri, indexFile, file);
        }
        if ("content".equals(scheme)) {
            return new TreeUriDownloader(uri, indexFile, file);
        }
        if (Action.FILE_ATTRIBUTE.equals(scheme)) {
            return new LocalFileDownloader(uri, indexFile, file);
        }
        Utils.debugLog(TAG, "Using suffix " + uri.toString().replace(Utils.getRepoAddress(repository), "") + " with mirrors " + list);
        Proxy proxy = NetCipher.getProxy();
        DownloadRequest downloadRequest = new DownloadRequest(indexFile, list, proxy, repository.getUsername(), repository.getPassword(), mirror);
        Preferences preferences = Preferences.get();
        boolean zIsForceOldIndexEnabled = preferences.isForceOldIndexEnabled();
        boolean z = repository.getFormatVersion() == null || repository.getFormatVersion() == IndexFormatVersion.ONE;
        if (zIsForceOldIndexEnabled || z) {
            return new HttpDownloader(HTTP_MANAGER, downloadRequest, file);
        }
        if (downloadRequest.getIndexFile().getIpfsCidV1() != null && preferences.isIpfsEnabled()) {
            ArrayList arrayList = new ArrayList(list);
            arrayList.addAll(loadIpfsGateways(preferences));
            downloadRequest = new DownloadRequest(downloadRequest.getIndexFile(), arrayList, proxy, repository.getUsername(), repository.getPassword(), mirror);
        }
        return new HttpDownloaderV2(HTTP_MANAGER, downloadRequest, file);
    }

    private static List<Mirror> loadIpfsGateways(Preferences preferences) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = preferences.getActiveIpfsGateways().iterator();
        while (it.hasNext()) {
            arrayList.add(new Mirror(it.next(), null, true));
        }
        return arrayList;
    }
}
