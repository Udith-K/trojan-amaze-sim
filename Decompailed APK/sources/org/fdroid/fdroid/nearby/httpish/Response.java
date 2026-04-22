package org.fdroid.fdroid.nearby.httpish;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.nearby.BluetoothConnection;

/* JADX INFO: loaded from: classes2.dex */
public class Response {
    private static final String TAG = "bluetooth.Response";
    private final InputStream contentStream;
    private final Map<String, String> headers;
    private final int statusCode;

    public Response(int i, Map<String, String> map) {
        this(i, map, (InputStream) null);
    }

    public Response(int i, Map<String, String> map, InputStream inputStream) {
        this.statusCode = i;
        this.headers = map;
        this.contentStream = inputStream;
    }

    public Response(int i, String str, String str2) {
        this.statusCode = i;
        HashMap map = new HashMap();
        this.headers = map;
        map.put("Content-Type", str);
        this.contentStream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
    }

    public Response(int i, String str, InputStream inputStream) {
        this.statusCode = i;
        HashMap map = new HashMap();
        this.headers = map;
        map.put("Content-Type", str);
        this.contentStream = inputStream;
    }

    public void addHeader(String str, String str2) {
        this.headers.put(str, str2);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public int getFileSize() {
        Map<String, String> map = this.headers;
        if (map != null) {
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, String> next = it.next();
                if ("content-length".equals(next.getKey().toLowerCase(Locale.ENGLISH))) {
                    try {
                        return Integer.parseInt(next.getValue());
                    } catch (NumberFormatException unused) {
                    }
                }
            }
        }
        return -1;
    }

    public FileDetails toFileDetails() {
        FileDetails fileDetails = new FileDetails();
        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            Header.process(fileDetails, entry.getKey(), entry.getValue());
        }
        return fileDetails;
    }

    public InputStream toContentStream() throws UnsupportedOperationException {
        InputStream inputStream = this.contentStream;
        if (inputStream != null) {
            return inputStream;
        }
        throw new UnsupportedOperationException("This kind of response doesn't have a content stream. Did you perform a HEAD request instead of a GET request?");
    }

    public void send(BluetoothConnection bluetoothConnection) throws IOException {
        Utils.debugLog(TAG, "Sending Bluetooth HTTP-ish response...");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bluetoothConnection.getOutputStream());
        outputStreamWriter.write("HTTP(ish)/0.1 200 OK\n");
        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            outputStreamWriter.write(entry.getKey());
            outputStreamWriter.write(": ");
            outputStreamWriter.write(entry.getValue());
            outputStreamWriter.write("\n");
        }
        outputStreamWriter.write("\n");
        outputStreamWriter.flush();
        InputStream inputStream = this.contentStream;
        if (inputStream != null) {
            Utils.copy(inputStream, bluetoothConnection.getOutputStream());
        }
        outputStreamWriter.flush();
    }

    public static class Builder {
        private InputStream contentStream;
        private String etag;
        private int statusCode = 200;
        private int fileSize = -1;

        public Builder() {
        }

        public Builder(InputStream inputStream) {
            this.contentStream = inputStream;
        }

        public Builder setStatusCode(int i) {
            this.statusCode = i;
            return this;
        }

        public Builder setFileSize(int i) {
            this.fileSize = i;
            return this;
        }

        public Builder setETag(String str) {
            this.etag = str;
            return this;
        }

        public Response build() {
            HashMap map = new HashMap(3);
            int i = this.fileSize;
            if (i > 0) {
                map.put("Content-Length", Integer.toString(i));
            }
            String str = this.etag;
            if (str != null) {
                map.put("ETag", str);
            }
            return new Response(this.statusCode, map, this.contentStream);
        }
    }
}
