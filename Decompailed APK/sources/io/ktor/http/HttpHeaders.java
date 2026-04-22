package io.ktor.http;

import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpHeaders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpHeaders {
    private static final String[] UnsafeHeadersArray;
    private static final List UnsafeHeadersList;
    public static final HttpHeaders INSTANCE = new HttpHeaders();
    private static final String Accept = "Accept";
    private static final String AcceptCharset = "Accept-Charset";
    private static final String AcceptEncoding = "Accept-Encoding";
    private static final String AcceptLanguage = "Accept-Language";
    private static final String AcceptRanges = "Accept-Ranges";
    private static final String Age = "Age";
    private static final String Allow = "Allow";
    private static final String ALPN = "ALPN";
    private static final String AuthenticationInfo = "Authentication-Info";
    private static final String Authorization = "Authorization";
    private static final String CacheControl = "Cache-Control";
    private static final String Connection = "Connection";
    private static final String ContentDisposition = "Content-Disposition";
    private static final String ContentEncoding = "Content-Encoding";
    private static final String ContentLanguage = "Content-Language";
    private static final String ContentLength = "Content-Length";
    private static final String ContentLocation = "Content-Location";
    private static final String ContentRange = "Content-Range";
    private static final String ContentType = "Content-Type";
    private static final String Cookie = "Cookie";
    private static final String DASL = "DASL";
    private static final String Date = "Date";
    private static final String DAV = "DAV";
    private static final String Depth = "Depth";
    private static final String Destination = "Destination";
    private static final String ETag = "ETag";
    private static final String Expect = "Expect";
    private static final String Expires = "Expires";
    private static final String From = "From";
    private static final String Forwarded = "Forwarded";
    private static final String Host = "Host";
    private static final String HTTP2Settings = "HTTP2-Settings";
    private static final String If = "If";
    private static final String IfMatch = "If-Match";
    private static final String IfModifiedSince = "If-Modified-Since";
    private static final String IfNoneMatch = "If-None-Match";
    private static final String IfRange = "If-Range";
    private static final String IfScheduleTagMatch = "If-Schedule-Tag-Match";
    private static final String IfUnmodifiedSince = "If-Unmodified-Since";
    private static final String LastModified = "Last-Modified";
    private static final String Location = "Location";
    private static final String LockToken = "Lock-Token";
    private static final String Link = "Link";
    private static final String MaxForwards = "Max-Forwards";
    private static final String MIMEVersion = "MIME-Version";
    private static final String OrderingType = "Ordering-Type";
    private static final String Origin = "Origin";
    private static final String Overwrite = "Overwrite";
    private static final String Position = "Position";
    private static final String Pragma = "Pragma";
    private static final String Prefer = "Prefer";
    private static final String PreferenceApplied = "Preference-Applied";
    private static final String ProxyAuthenticate = "Proxy-Authenticate";
    private static final String ProxyAuthenticationInfo = "Proxy-Authentication-Info";
    private static final String ProxyAuthorization = "Proxy-Authorization";
    private static final String PublicKeyPins = "Public-Key-Pins";
    private static final String PublicKeyPinsReportOnly = "Public-Key-Pins-Report-Only";
    private static final String Range = "Range";
    private static final String Referrer = "Referer";
    private static final String RetryAfter = "Retry-After";
    private static final String ScheduleReply = "Schedule-Reply";
    private static final String ScheduleTag = "Schedule-Tag";
    private static final String SecWebSocketAccept = "Sec-WebSocket-Accept";
    private static final String SecWebSocketExtensions = "Sec-WebSocket-Extensions";
    private static final String SecWebSocketKey = "Sec-WebSocket-Key";
    private static final String SecWebSocketProtocol = "Sec-WebSocket-Protocol";
    private static final String SecWebSocketVersion = "Sec-WebSocket-Version";
    private static final String Server = "Server";
    private static final String SetCookie = "Set-Cookie";
    private static final String SLUG = "SLUG";
    private static final String StrictTransportSecurity = "Strict-Transport-Security";
    private static final String TE = "TE";
    private static final String Timeout = "Timeout";
    private static final String Trailer = "Trailer";
    private static final String TransferEncoding = "Transfer-Encoding";
    private static final String Upgrade = "Upgrade";
    private static final String UserAgent = "User-Agent";
    private static final String Vary = "Vary";
    private static final String Via = "Via";
    private static final String Warning = "Warning";
    private static final String WWWAuthenticate = "WWW-Authenticate";
    private static final String AccessControlAllowOrigin = "Access-Control-Allow-Origin";
    private static final String AccessControlAllowMethods = "Access-Control-Allow-Methods";
    private static final String AccessControlAllowCredentials = "Access-Control-Allow-Credentials";
    private static final String AccessControlAllowHeaders = "Access-Control-Allow-Headers";
    private static final String AccessControlRequestMethod = "Access-Control-Request-Method";
    private static final String AccessControlRequestHeaders = "Access-Control-Request-Headers";
    private static final String AccessControlExposeHeaders = "Access-Control-Expose-Headers";
    private static final String AccessControlMaxAge = "Access-Control-Max-Age";
    private static final String XHttpMethodOverride = "X-Http-Method-Override";
    private static final String XForwardedHost = "X-Forwarded-Host";
    private static final String XForwardedServer = "X-Forwarded-Server";
    private static final String XForwardedProto = "X-Forwarded-Proto";
    private static final String XForwardedFor = "X-Forwarded-For";
    private static final String XForwardedPort = "X-Forwarded-Port";
    private static final String XRequestId = "X-Request-ID";
    private static final String XCorrelationId = "X-Correlation-ID";
    private static final String XTotalCount = "X-Total-Count";

    private HttpHeaders() {
    }

    static {
        String[] strArr = {"Transfer-Encoding", "Upgrade"};
        UnsafeHeadersArray = strArr;
        UnsafeHeadersList = ArraysKt.asList(strArr);
    }

    public final String getAccept() {
        return Accept;
    }

    public final String getAcceptCharset() {
        return AcceptCharset;
    }

    public final String getAuthorization() {
        return Authorization;
    }

    public final String getContentEncoding() {
        return ContentEncoding;
    }

    public final String getContentLength() {
        return ContentLength;
    }

    public final String getContentType() {
        return ContentType;
    }

    public final String getCookie() {
        return Cookie;
    }

    public final String getDate() {
        return Date;
    }

    public final String getETag() {
        return ETag;
    }

    public final String getExpires() {
        return Expires;
    }

    public final String getIfModifiedSince() {
        return IfModifiedSince;
    }

    public final String getIfUnmodifiedSince() {
        return IfUnmodifiedSince;
    }

    public final String getLastModified() {
        return LastModified;
    }

    public final String getLocation() {
        return Location;
    }

    public final String getRange() {
        return Range;
    }

    public final String getUserAgent() {
        return UserAgent;
    }

    public final List getUnsafeHeadersList() {
        return UnsafeHeadersList;
    }

    public final void checkHeaderName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        int i = 0;
        int i2 = 0;
        while (i < name.length()) {
            char cCharAt = name.charAt(i);
            int i3 = i2 + 1;
            if (Intrinsics.compare((int) cCharAt, 32) <= 0 || HttpHeadersKt.isDelimiter(cCharAt)) {
                throw new IllegalHeaderNameException(name, i2);
            }
            i++;
            i2 = i3;
        }
    }

    public final void checkHeaderValue(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        int i = 0;
        int i2 = 0;
        while (i < value.length()) {
            char cCharAt = value.charAt(i);
            int i3 = i2 + 1;
            if (Intrinsics.compare((int) cCharAt, 32) < 0 && cCharAt != '\t') {
                throw new IllegalHeaderValueException(value, i2);
            }
            i++;
            i2 = i3;
        }
    }
}
