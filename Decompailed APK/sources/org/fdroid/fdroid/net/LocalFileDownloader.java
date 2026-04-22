package org.fdroid.fdroid.net;

import android.net.Uri;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.ProtocolException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.fdroid.IndexFile;
import org.fdroid.download.Downloader;
import org.fdroid.download.NotFoundException;

/* JADX INFO: loaded from: classes2.dex */
public class LocalFileDownloader extends Downloader {
    private InputStream inputStream;
    private final File sourceFile;

    @Override // org.fdroid.download.Downloader
    public boolean hasChanged() {
        return true;
    }

    LocalFileDownloader(Uri uri, IndexFile indexFile, File file) {
        super(indexFile, file);
        this.sourceFile = new File(uri.getPath());
    }

    @Override // org.fdroid.download.Downloader
    protected InputStream getInputStream(boolean z) throws IOException, NotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.sourceFile);
            this.inputStream = fileInputStream;
            return fileInputStream;
        } catch (FileNotFoundException unused) {
            throw new NotFoundException();
        } catch (SecurityException e) {
            throw new ProtocolException(e.getLocalizedMessage());
        }
    }

    @Override // org.fdroid.download.Downloader
    public void close() {
        IOUtils.closeQuietly(this.inputStream);
    }

    @Override // org.fdroid.download.Downloader
    protected long totalDownloadSize() {
        return this.sourceFile.length();
    }

    @Override // org.fdroid.download.Downloader
    public void download() throws InterruptedException, IOException {
        if (!this.sourceFile.exists()) {
            throw new ConnectException(this.sourceFile + " does not exist, try a mirror");
        }
        long length = this.sourceFile.length();
        long length2 = this.outputFile.length();
        if (length2 > length) {
            FileUtils.deleteQuietly(this.outputFile);
        } else {
            if (length2 == length && this.outputFile.isFile()) {
                return;
            }
            boolean z = length2 > 0;
            downloadFromStream(z);
        }
        downloadFromStream(z);
    }
}
