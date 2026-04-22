package org.fdroid.fdroid.net;

import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;
import org.apache.commons.io.input.BoundedInputStream;
import org.fdroid.IndexFile;
import org.fdroid.download.Downloader;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.nearby.BluetoothClient;
import org.fdroid.fdroid.nearby.BluetoothConnection;
import org.fdroid.fdroid.nearby.httpish.FileDetails;
import org.fdroid.fdroid.nearby.httpish.Request;
import org.fdroid.fdroid.nearby.httpish.Response;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothDownloader extends Downloader {
    public static final String SCHEME = "bluetooth";
    private static final String TAG = "BluetoothDownloader";
    private final BluetoothConnection connection;
    private FileDetails fileDetails;
    private final String sourcePath;

    public static boolean isBluetoothUri(Uri uri) {
        return SCHEME.equals(uri.getScheme()) && Pattern.matches("([0-9A-F]{2}-)+[0-9A-F]{2}", uri.getHost());
    }

    BluetoothDownloader(Uri uri, IndexFile indexFile, File file) throws IOException {
        super(indexFile, file);
        this.connection = new BluetoothClient(uri.getHost().replace("-", ":")).openConnection();
        this.sourcePath = uri.getPath();
    }

    @Override // org.fdroid.download.Downloader
    protected InputStream getInputStream(boolean z) throws IOException {
        Response responseSend = Request.createGET(this.sourcePath, this.connection).send();
        this.fileDetails = responseSend.toFileDetails();
        BoundedInputStream boundedInputStream = new BoundedInputStream(responseSend.toContentStream(), this.fileDetails.getFileSize());
        boundedInputStream.setPropagateClose(false);
        return boundedInputStream;
    }

    private FileDetails getFileDetails() {
        if (this.fileDetails == null) {
            Utils.debugLog(TAG, "Going to Bluetooth \"server\" to get file details.");
            try {
                this.fileDetails = Request.createHEAD(this.sourcePath, this.connection).send().toFileDetails();
            } catch (IOException e) {
                Log.e(TAG, "Error getting file details from Bluetooth \"server\"", e);
            }
        }
        return this.fileDetails;
    }

    @Override // org.fdroid.download.Downloader
    public boolean hasChanged() {
        FileDetails fileDetails = getFileDetails();
        return fileDetails == null || fileDetails.getCacheTag() == null || fileDetails.getCacheTag().equals(getCacheTag());
    }

    @Override // org.fdroid.download.Downloader
    public long totalDownloadSize() {
        if (getIndexFile().getSize() != null) {
            return getIndexFile().getSize().longValue();
        }
        FileDetails fileDetails = getFileDetails();
        if (fileDetails != null) {
            return fileDetails.getFileSize();
        }
        return -1L;
    }

    @Override // org.fdroid.download.Downloader
    public void download() throws InterruptedException, IOException {
        downloadFromStream(false);
        this.connection.closeQuietly();
    }

    @Override // org.fdroid.download.Downloader
    public void close() {
        BluetoothConnection bluetoothConnection = this.connection;
        if (bluetoothConnection != null) {
            bluetoothConnection.closeQuietly();
        }
    }
}
