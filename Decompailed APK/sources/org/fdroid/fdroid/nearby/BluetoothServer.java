package org.fdroid.fdroid.nearby;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import android.webkit.MimeTypeMap;
import fi.iki.elonen.NanoHTTPD;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.nearby.httpish.Request;
import org.fdroid.fdroid.nearby.httpish.Response;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothServer extends Thread {
    private static final String TAG = "BluetoothServer";
    private final List<ClientConnection> clients = new ArrayList();
    private BluetoothServerSocket serverSocket;
    private final File webRoot;

    public BluetoothServer(File file) {
        this.webRoot = file;
    }

    public void close() {
        Iterator<ClientConnection> it = this.clients.iterator();
        while (it.hasNext()) {
            it.next().interrupt();
        }
        interrupt();
        BluetoothServerSocket bluetoothServerSocket = this.serverSocket;
        if (bluetoothServerSocket != null) {
            Utils.closeQuietly(bluetoothServerSocket);
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            this.serverSocket = defaultAdapter.listenUsingInsecureRfcommWithServiceRecord("FDroid App Swap", BluetoothConstants.fdroidUuid());
            while (!isInterrupted()) {
                if (!defaultAdapter.isEnabled()) {
                    Utils.debugLog(TAG, "User disabled Bluetooth from outside, stopping.");
                    return;
                }
                try {
                    BluetoothSocket bluetoothSocketAccept = this.serverSocket.accept();
                    if (bluetoothSocketAccept == null) {
                        continue;
                    } else if (isInterrupted()) {
                        Utils.debugLog(TAG, "Server stopped after socket accepted from client, but before initiating connection.");
                        return;
                    } else {
                        ClientConnection clientConnection = new ClientConnection(bluetoothSocketAccept, this.webRoot);
                        clientConnection.start();
                        this.clients.add(clientConnection);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error receiving client connection over Bluetooth server socket, will continue listening for other clients", e);
                }
            }
            Utils.debugLog(TAG, "Server stopped so will terminate loop looking for client connections.");
        } catch (IOException e2) {
            Log.e(TAG, "Error starting Bluetooth server socket, will stop the server now", e2);
        }
    }

    private static class ClientConnection extends Thread {
        private final BluetoothSocket socket;
        private final File webRoot;

        ClientConnection(BluetoothSocket bluetoothSocket, File file) {
            this.socket = bluetoothSocket;
            this.webRoot = file;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Utils.debugLog(BluetoothServer.TAG, "Listening for incoming Bluetooth requests from client");
            try {
                BluetoothConnection bluetoothConnection = new BluetoothConnection(this.socket);
                bluetoothConnection.open();
                do {
                    try {
                        Utils.debugLog(BluetoothServer.TAG, "Listening for new Bluetooth request from client.");
                        handleRequest(Request.listenForRequest(bluetoothConnection)).send(bluetoothConnection);
                    } catch (IOException e) {
                        Log.e(BluetoothServer.TAG, "Error receiving incoming connection over bluetooth", e);
                    }
                } while (!isInterrupted());
                bluetoothConnection.closeQuietly();
            } catch (IOException e2) {
                Log.e(BluetoothServer.TAG, "Error listening for incoming connections over bluetooth", e2);
            }
        }

        private Response handleRequest(Request request) {
            int fileSize;
            int i;
            Utils.debugLog(BluetoothServer.TAG, "Received Bluetooth request from client, will process it now.");
            Response.Builder builder = null;
            try {
                if (request.getMethod().equals(Request.Methods.HEAD)) {
                    fileSize = -1;
                    builder = new Response.Builder();
                    i = 404;
                } else {
                    Response responseRespond = respond(new HashMap(), "/" + request.getPath());
                    Response.Builder builder2 = new Response.Builder(responseRespond.toContentStream());
                    try {
                        int statusCode = responseRespond.getStatusCode();
                        fileSize = responseRespond.getFileSize();
                        i = statusCode;
                        builder = builder2;
                    } catch (Exception e) {
                        e = e;
                        builder = builder2;
                        Log.e(BluetoothServer.TAG, "error processing request; sending 500 response", e);
                        if (builder == null) {
                            builder = new Response.Builder();
                        }
                        return builder.setStatusCode(500).setFileSize(0).build();
                    }
                }
                return builder.setStatusCode(i).setFileSize(fileSize).build();
            } catch (Exception e2) {
                e = e2;
            }
        }

        private Response respond(Map<String, String> map, String str) {
            String strReplace = str.trim().replace(File.separatorChar, '/');
            if (strReplace.indexOf(63) >= 0) {
                strReplace = strReplace.substring(0, strReplace.indexOf(63));
            }
            if (strReplace.contains("../")) {
                return createResponse(NanoHTTPD.Response.Status.FORBIDDEN, NanoHTTPD.MIME_PLAINTEXT, "FORBIDDEN: Won't serve ../ for security reasons.");
            }
            File file = new File(this.webRoot, strReplace);
            if (!file.exists()) {
                return createResponse(NanoHTTPD.Response.Status.NOT_FOUND, NanoHTTPD.MIME_PLAINTEXT, "Error 404, file not found.");
            }
            if (file.isDirectory() && !strReplace.endsWith("/")) {
                String str2 = strReplace + "/";
                Response responseCreateResponse = createResponse(NanoHTTPD.Response.Status.REDIRECT, NanoHTTPD.MIME_HTML, "<html><body>Redirected: <a href=\"" + str2 + "\">" + str2 + "</a></body></html>");
                responseCreateResponse.addHeader("Location", str2);
                return responseCreateResponse;
            }
            if (file.isDirectory()) {
                String strFindIndexFileInDirectory = findIndexFileInDirectory(file);
                if (strFindIndexFileInDirectory == null) {
                    if (file.canRead()) {
                        return createResponse(NanoHTTPD.Response.Status.NOT_FOUND, NanoHTTPD.MIME_HTML, "");
                    }
                    return createResponse(NanoHTTPD.Response.Status.FORBIDDEN, NanoHTTPD.MIME_PLAINTEXT, "FORBIDDEN: No directory listing.");
                }
                return respond(map, strReplace + strFindIndexFileInDirectory);
            }
            Response responseServeFile = serveFile(strReplace, map, file, getMimeTypeForFile(strReplace));
            return responseServeFile != null ? responseServeFile : createResponse(NanoHTTPD.Response.Status.NOT_FOUND, NanoHTTPD.MIME_PLAINTEXT, "Error 404, file not found.");
        }

        /* JADX WARN: Removed duplicated region for block: B:39:0x0134 A[Catch: IOException -> 0x0151, TryCatch #1 {IOException -> 0x0151, blocks: (B:32:0x00c5, B:34:0x00d2, B:35:0x0109, B:36:0x0124, B:37:0x0125, B:39:0x0134, B:40:0x013b), top: B:49:0x0079 }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x013b A[Catch: IOException -> 0x0151, TRY_LEAVE, TryCatch #1 {IOException -> 0x0151, blocks: (B:32:0x00c5, B:34:0x00d2, B:35:0x0109, B:36:0x0124, B:37:0x0125, B:39:0x0134, B:40:0x013b), top: B:49:0x0079 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        org.fdroid.fdroid.nearby.httpish.Response serveFile(java.lang.String r25, java.util.Map<java.lang.String, java.lang.String> r26, java.io.File r27, java.lang.String r28) {
            /*
                Method dump skipped, instruction units count: 348
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.nearby.BluetoothServer.ClientConnection.serveFile(java.lang.String, java.util.Map, java.io.File, java.lang.String):org.fdroid.fdroid.nearby.httpish.Response");
        }

        private Response createResponse(NanoHTTPD.Response.Status status, String str, String str2) {
            return new Response(status.getRequestStatus(), str, str2);
        }

        private Response createResponse(NanoHTTPD.Response.Status status, String str, InputStream inputStream) {
            return new Response(status.getRequestStatus(), str, inputStream);
        }

        public static String getMimeTypeForFile(String str) {
            String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
            if (fileExtensionFromUrl != null) {
                return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
            }
            return null;
        }

        private String findIndexFileInDirectory(File file) {
            if (new File(file, "index.html").exists()) {
                return "index.html";
            }
            return null;
        }
    }
}
