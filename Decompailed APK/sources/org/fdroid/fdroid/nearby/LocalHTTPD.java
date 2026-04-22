package org.fdroid.fdroid.nearby;

import android.content.Context;
import android.net.Uri;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import fi.iki.elonen.NanoHTTPD;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TimeZone;
import org.fdroid.fdroid.nearby.LocalRepoKeyStore;

/* JADX INFO: loaded from: classes2.dex */
public class LocalHTTPD extends NanoHTTPD {
    public static final String[] INDEX_FILE_NAMES = {"index.html"};
    private static final DateFormat RFC_1123;
    private static final String TAG = "LocalHTTPD";
    private final WeakReference<Context> context;
    protected List<File> rootDirs;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        RFC_1123 = simpleDateFormat;
        simpleDateFormat.setLenient(false);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public LocalHTTPD(Context context, String str, int i, File file, boolean z) {
        super(str, i);
        this.rootDirs = Collections.singletonList(file);
        this.context = new WeakReference<>(context.getApplicationContext());
        if (z) {
            enableHTTPS();
        }
        HashMap map = new HashMap();
        NanoHTTPD.MIME_TYPES = map;
        map.put("apk", "application/vnd.android.package-archive");
        NanoHTTPD.MIME_TYPES.put("html", NanoHTTPD.MIME_HTML);
        NanoHTTPD.MIME_TYPES.put("png", "image/png");
        NanoHTTPD.MIME_TYPES.put("xml", "application/xml");
    }

    private boolean canServeUri(String str, File file) {
        return new File(file, str).exists();
    }

    private String encodeUri(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "/ ", true);
        String str2 = "";
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            if ("/".equals(strNextToken)) {
                str2 = str2 + "/";
            } else if (" ".equals(strNextToken)) {
                str2 = str2 + "%20";
            } else {
                try {
                    str2 = str2 + URLEncoder.encode(strNextToken, "UTF-8");
                } catch (UnsupportedEncodingException unused) {
                }
            }
        }
        return str2;
    }

    private String findIndexFileInDirectory(File file) {
        for (String str : INDEX_FILE_NAMES) {
            if (new File(file, str).isFile()) {
                return str;
            }
        }
        return null;
    }

    protected NanoHTTPD.Response getForbiddenResponse(String str) {
        return newFixedLengthResponse(NanoHTTPD.Response.Status.FORBIDDEN, NanoHTTPD.MIME_PLAINTEXT, "FORBIDDEN: " + str);
    }

    protected NanoHTTPD.Response getInternalErrorResponse(String str) {
        return newFixedLengthResponse(NanoHTTPD.Response.Status.INTERNAL_ERROR, NanoHTTPD.MIME_PLAINTEXT, "INTERNAL ERROR: " + str);
    }

    protected NanoHTTPD.Response getNotFoundResponse() {
        return newFixedLengthResponse(NanoHTTPD.Response.Status.NOT_FOUND, NanoHTTPD.MIME_PLAINTEXT, "Error 404, file not found.");
    }

    protected String listDirectory(String str, File file) {
        String strSubstring;
        int iLastIndexOf;
        String str2 = "Directory " + str;
        StringBuilder sb = new StringBuilder("<html><head><title>" + str2 + "</title><style><!--\nspan.dirname { font-weight: bold; }\nspan.filesize { font-size: 75%; }\n// -->\n</style></head><body><h1>" + str2 + "</h1>");
        String strSubstring2 = (str.length() <= 1 || (iLastIndexOf = (strSubstring = str.substring(0, str.length() - 1)).lastIndexOf(47)) < 0 || iLastIndexOf >= strSubstring.length()) ? null : str.substring(0, iLastIndexOf + 1);
        String[] list = file.list(new FilenameFilter() { // from class: org.fdroid.fdroid.nearby.LocalHTTPD$$ExternalSyntheticLambda0
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str3) {
                return LocalHTTPD.lambda$listDirectory$0(file2, str3);
            }
        });
        Objects.requireNonNull(list);
        List<String> listAsList = Arrays.asList(list);
        Collections.sort(listAsList);
        String[] list2 = file.list(new FilenameFilter() { // from class: org.fdroid.fdroid.nearby.LocalHTTPD$$ExternalSyntheticLambda1
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str3) {
                return LocalHTTPD.lambda$listDirectory$1(file2, str3);
            }
        });
        Objects.requireNonNull(list2);
        List listAsList2 = Arrays.asList(list2);
        Collections.sort(listAsList2);
        if (strSubstring2 != null || listAsList2.size() + listAsList.size() > 0) {
            sb.append("<ul>");
            if (strSubstring2 != null || listAsList2.size() > 0) {
                sb.append("<section class=\"directories\">");
                if (strSubstring2 != null) {
                    sb.append("<li><a rel=\"directory\" href=\"");
                    sb.append(strSubstring2);
                    sb.append("\"><span class=\"dirname\">..</span></a></li>");
                }
                Iterator it = listAsList2.iterator();
                while (it.hasNext()) {
                    String str3 = ((String) it.next()) + "/";
                    sb.append("<li><a rel=\"directory\" href=\"");
                    sb.append(encodeUri(str + str3));
                    sb.append("\"><span class=\"dirname\">");
                    sb.append(str3);
                    sb.append("</span></a></li>");
                }
                sb.append("</section>");
            }
            if (listAsList.size() > 0) {
                sb.append("<section class=\"files\">");
                for (String str4 : listAsList) {
                    sb.append("<li><a href=\"");
                    sb.append(encodeUri(str + str4));
                    sb.append("\"><span class=\"filename\">");
                    sb.append(str4);
                    sb.append("</span></a>");
                    long length = new File(file, str4).length();
                    sb.append("&nbsp;<span class=\"filesize\">(");
                    if (length < 1024) {
                        sb.append(length);
                        sb.append(" bytes");
                    } else if (length < 1048576) {
                        sb.append(length / 1024);
                        sb.append(".");
                        sb.append(((length % 1024) / 10) % 100);
                        sb.append(" KB");
                    } else {
                        sb.append(length / 1048576);
                        sb.append(".");
                        sb.append(((length % 1048576) / AbstractComponentTracker.LINGERING_TIMEOUT) % 100);
                        sb.append(" MB");
                    }
                    sb.append(")</span></li>");
                }
                sb.append("</section>");
            }
            sb.append("</ul>");
        }
        sb.append("</body></html>");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$listDirectory$0(File file, String str) {
        return new File(file, str).isFile();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$listDirectory$1(File file, String str) {
        return new File(file, str).isDirectory();
    }

    public static NanoHTTPD.Response addResponseHeaders(NanoHTTPD.Response response) {
        response.setKeepAlive(false);
        response.setGzipEncoding(false);
        response.addHeader("Connection", "close");
        response.addHeader("Content-Security-Policy", "default-src 'none'; img-src 'self'; style-src 'self' 'unsafe-inline';");
        return response;
    }

    public static NanoHTTPD.Response newFixedLengthResponse(String str) {
        return addResponseHeaders(NanoHTTPD.newFixedLengthResponse(str));
    }

    public static NanoHTTPD.Response newFixedLengthResponse(NanoHTTPD.Response.IStatus iStatus, String str, InputStream inputStream, long j) {
        return addResponseHeaders(NanoHTTPD.newFixedLengthResponse(iStatus, str, inputStream, j));
    }

    public static NanoHTTPD.Response newFixedLengthResponse(NanoHTTPD.Response.IStatus iStatus, String str, String str2) {
        NanoHTTPD.Response responseNewFixedLengthResponse = NanoHTTPD.newFixedLengthResponse(iStatus, str, str2);
        addResponseHeaders(responseNewFixedLengthResponse);
        responseNewFixedLengthResponse.addHeader("Accept-Ranges", "bytes");
        return responseNewFixedLengthResponse;
    }

    private NanoHTTPD.Response respond(Map<String, String> map, NanoHTTPD.IHTTPSession iHTTPSession, String str) {
        return defaultRespond(map, iHTTPSession, str);
    }

    private NanoHTTPD.Response defaultRespond(Map<String, String> map, NanoHTTPD.IHTTPSession iHTTPSession, String str) {
        String strReplace = str.trim().replace(File.separatorChar, '/');
        boolean zCanServeUri = false;
        if (strReplace.indexOf(63) >= 0) {
            strReplace = strReplace.substring(0, strReplace.indexOf(63));
        }
        if (strReplace.contains("../")) {
            return getForbiddenResponse("Won't serve ../ for security reasons.");
        }
        File file = null;
        for (int i = 0; !zCanServeUri && i < this.rootDirs.size(); i++) {
            file = this.rootDirs.get(i);
            zCanServeUri = canServeUri(strReplace, file);
        }
        if (!zCanServeUri) {
            return getNotFoundResponse();
        }
        File file2 = new File(file, strReplace);
        if (file2.isDirectory() && !strReplace.endsWith("/")) {
            String str2 = strReplace + "/";
            NanoHTTPD.Response responseNewFixedLengthResponse = newFixedLengthResponse(NanoHTTPD.Response.Status.REDIRECT, NanoHTTPD.MIME_HTML, "<html><body>Redirected: <a href=\"" + str2 + "\">" + str2 + "</a></body></html>");
            responseNewFixedLengthResponse.addHeader("Location", str2);
            return responseNewFixedLengthResponse;
        }
        if (file2.isDirectory()) {
            String strFindIndexFileInDirectory = findIndexFileInDirectory(file2);
            if (strFindIndexFileInDirectory == null) {
                if (file2.canRead()) {
                    return newFixedLengthResponse(NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_HTML, listDirectory(strReplace, file2));
                }
                return getForbiddenResponse("No directory listing.");
            }
            return respond(map, iHTTPSession, strReplace + strFindIndexFileInDirectory);
        }
        NanoHTTPD.Response responseServeFile = serveFile(strReplace, map, file2, NanoHTTPD.getMimeTypeForFile(strReplace));
        return responseServeFile != null ? responseServeFile : getNotFoundResponse();
    }

    @Override // fi.iki.elonen.NanoHTTPD
    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession iHTTPSession) {
        Map headers = iHTTPSession.getHeaders();
        iHTTPSession.getParms();
        String uri = iHTTPSession.getUri();
        if (iHTTPSession.getMethod() == NanoHTTPD.Method.POST) {
            try {
                iHTTPSession.parseBody(new HashMap());
                return handlePost(iHTTPSession);
            } catch (NanoHTTPD.ResponseException e) {
                return newFixedLengthResponse(e.getStatus(), NanoHTTPD.MIME_PLAINTEXT, e.getMessage());
            } catch (IOException unused) {
                return newFixedLengthResponse(NanoHTTPD.Response.Status.INTERNAL_ERROR, NanoHTTPD.MIME_PLAINTEXT, "Internal server error, check logcat on server for details.");
            }
        }
        for (File file : this.rootDirs) {
            if (!file.isDirectory()) {
                return getInternalErrorResponse("given path is not a directory (" + file + ").");
            }
        }
        return respond(Collections.unmodifiableMap(headers), iHTTPSession, uri);
    }

    private NanoHTTPD.Response handlePost(NanoHTTPD.IHTTPSession iHTTPSession) {
        String path = Uri.parse(iHTTPSession.getUri()).getPath();
        path.hashCode();
        if (path.equals("/request-swap")) {
            if (!iHTTPSession.getParms().containsKey("repo")) {
                return newFixedLengthResponse(NanoHTTPD.Response.Status.BAD_REQUEST, NanoHTTPD.MIME_PLAINTEXT, "Requires 'repo' parameter to be posted.");
            }
            SwapWorkflowActivity.requestSwap(this.context.get(), (String) iHTTPSession.getParms().get("repo"));
            return newFixedLengthResponse(NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT, "Swap request received.");
        }
        return newFixedLengthResponse("");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x017b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    fi.iki.elonen.NanoHTTPD.Response serveFile(java.lang.String r21, java.util.Map<java.lang.String, java.lang.String> r22, java.io.File r23, java.lang.String r24) {
        /*
            Method dump skipped, instruction units count: 455
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.nearby.LocalHTTPD.serveFile(java.lang.String, java.util.Map, java.io.File, java.lang.String):fi.iki.elonen.NanoHTTPD$Response");
    }

    private NanoHTTPD.Response newFixedFileResponse(File file, String str) throws FileNotFoundException {
        NanoHTTPD.Response responseNewFixedLengthResponse = newFixedLengthResponse(NanoHTTPD.Response.Status.OK, str, new FileInputStream(file), (int) file.length());
        addResponseHeaders(responseNewFixedLengthResponse);
        responseNewFixedLengthResponse.addHeader("Accept-Ranges", "bytes");
        return responseNewFixedLengthResponse;
    }

    private void enableHTTPS() {
        try {
            LocalRepoKeyStore localRepoKeyStore = LocalRepoKeyStore.get(this.context.get());
            makeSecure(NanoHTTPD.makeSSLSocketFactory(localRepoKeyStore.getKeyStore(), localRepoKeyStore.getKeyManagers()), null);
        } catch (IOException | LocalRepoKeyStore.InitException e) {
            e.printStackTrace();
        }
    }
}
