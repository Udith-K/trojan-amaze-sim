package io.ktor.http;

import ch.qos.logback.core.CoreConstants;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Url.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Url {
    public static final Companion Companion = new Companion(null);
    private final Lazy encodedFragment$delegate;
    private final Lazy encodedPassword$delegate;
    private final Lazy encodedPath$delegate;
    private final Lazy encodedPathAndQuery$delegate;
    private final Lazy encodedQuery$delegate;
    private final Lazy encodedUser$delegate;
    private final String fragment;
    private final String host;
    private final Parameters parameters;
    private final String password;
    private final List pathSegments;
    private final URLProtocol protocol;
    private final int specifiedPort;
    private final boolean trailingQuery;
    private final String urlString;
    private final String user;

    public Url(URLProtocol protocol, String host, int i, List pathSegments, Parameters parameters, String fragment, String str, String str2, boolean z, String urlString) {
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        this.protocol = protocol;
        this.host = host;
        this.specifiedPort = i;
        this.pathSegments = pathSegments;
        this.parameters = parameters;
        this.fragment = fragment;
        this.user = str;
        this.password = str2;
        this.trailingQuery = z;
        this.urlString = urlString;
        if ((i < 0 || i >= 65536) && i != 0) {
            throw new IllegalArgumentException("port must be between 0 and 65535, or 0 if not set");
        }
        this.encodedPath$delegate = LazyKt.lazy(new Function0() { // from class: io.ktor.http.Url$encodedPath$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                int iIndexOf$default;
                if (this.this$0.getPathSegments().isEmpty() || (iIndexOf$default = StringsKt.indexOf$default((CharSequence) this.this$0.urlString, '/', this.this$0.getProtocol().getName().length() + 3, false, 4, (Object) null)) == -1) {
                    return "";
                }
                int iIndexOfAny$default = StringsKt.indexOfAny$default(this.this$0.urlString, new char[]{'?', '#'}, iIndexOf$default, false, 4, null);
                if (iIndexOfAny$default == -1) {
                    String strSubstring = this.this$0.urlString.substring(iIndexOf$default);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                    return strSubstring;
                }
                String strSubstring2 = this.this$0.urlString.substring(iIndexOf$default, iIndexOfAny$default);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                return strSubstring2;
            }
        });
        this.encodedQuery$delegate = LazyKt.lazy(new Function0() { // from class: io.ktor.http.Url$encodedQuery$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                int iIndexOf$default = StringsKt.indexOf$default((CharSequence) this.this$0.urlString, '?', 0, false, 6, (Object) null) + 1;
                if (iIndexOf$default != 0) {
                    int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) this.this$0.urlString, '#', iIndexOf$default, false, 4, (Object) null);
                    if (iIndexOf$default2 == -1) {
                        String strSubstring = this.this$0.urlString.substring(iIndexOf$default);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                        return strSubstring;
                    }
                    String strSubstring2 = this.this$0.urlString.substring(iIndexOf$default, iIndexOf$default2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    return strSubstring2;
                }
                return "";
            }
        });
        this.encodedPathAndQuery$delegate = LazyKt.lazy(new Function0() { // from class: io.ktor.http.Url$encodedPathAndQuery$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                int iIndexOf$default = StringsKt.indexOf$default((CharSequence) this.this$0.urlString, '/', this.this$0.getProtocol().getName().length() + 3, false, 4, (Object) null);
                if (iIndexOf$default != -1) {
                    int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) this.this$0.urlString, '#', iIndexOf$default, false, 4, (Object) null);
                    if (iIndexOf$default2 == -1) {
                        String strSubstring = this.this$0.urlString.substring(iIndexOf$default);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                        return strSubstring;
                    }
                    String strSubstring2 = this.this$0.urlString.substring(iIndexOf$default, iIndexOf$default2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    return strSubstring2;
                }
                return "";
            }
        });
        this.encodedUser$delegate = LazyKt.lazy(new Function0() { // from class: io.ktor.http.Url$encodedUser$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                if (this.this$0.getUser() == null) {
                    return null;
                }
                if (this.this$0.getUser().length() == 0) {
                    return "";
                }
                int length = this.this$0.getProtocol().getName().length() + 3;
                String strSubstring = this.this$0.urlString.substring(length, StringsKt.indexOfAny$default(this.this$0.urlString, new char[]{CoreConstants.COLON_CHAR, '@'}, length, false, 4, null));
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                return strSubstring;
            }
        });
        this.encodedPassword$delegate = LazyKt.lazy(new Function0() { // from class: io.ktor.http.Url$encodedPassword$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                if (this.this$0.getPassword() == null) {
                    return null;
                }
                if (this.this$0.getPassword().length() == 0) {
                    return "";
                }
                String strSubstring = this.this$0.urlString.substring(StringsKt.indexOf$default((CharSequence) this.this$0.urlString, CoreConstants.COLON_CHAR, this.this$0.getProtocol().getName().length() + 3, false, 4, (Object) null) + 1, StringsKt.indexOf$default((CharSequence) this.this$0.urlString, '@', 0, false, 6, (Object) null));
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                return strSubstring;
            }
        });
        this.encodedFragment$delegate = LazyKt.lazy(new Function0() { // from class: io.ktor.http.Url$encodedFragment$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                int iIndexOf$default = StringsKt.indexOf$default((CharSequence) this.this$0.urlString, '#', 0, false, 6, (Object) null) + 1;
                if (iIndexOf$default != 0) {
                    String strSubstring = this.this$0.urlString.substring(iIndexOf$default);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                    return strSubstring;
                }
                return "";
            }
        });
    }

    public final URLProtocol getProtocol() {
        return this.protocol;
    }

    public final String getHost() {
        return this.host;
    }

    public final int getSpecifiedPort() {
        return this.specifiedPort;
    }

    public final List getPathSegments() {
        return this.pathSegments;
    }

    public final String getUser() {
        return this.user;
    }

    public final String getPassword() {
        return this.password;
    }

    public final boolean getTrailingQuery() {
        return this.trailingQuery;
    }

    public final int getPort() {
        Integer numValueOf = Integer.valueOf(this.specifiedPort);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        return numValueOf != null ? numValueOf.intValue() : this.protocol.getDefaultPort();
    }

    public final String getEncodedPath() {
        return (String) this.encodedPath$delegate.getValue();
    }

    public final String getEncodedQuery() {
        return (String) this.encodedQuery$delegate.getValue();
    }

    public final String getEncodedUser() {
        return (String) this.encodedUser$delegate.getValue();
    }

    public final String getEncodedPassword() {
        return (String) this.encodedPassword$delegate.getValue();
    }

    public final String getEncodedFragment() {
        return (String) this.encodedFragment$delegate.getValue();
    }

    public String toString() {
        return this.urlString;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && Url.class == obj.getClass() && Intrinsics.areEqual(this.urlString, ((Url) obj).urlString);
    }

    public int hashCode() {
        return this.urlString.hashCode();
    }

    /* JADX INFO: compiled from: Url.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
