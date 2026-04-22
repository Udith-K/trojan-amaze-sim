package io.ktor.http;

import io.ktor.http.HeaderValueWithParameters;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.bouncycastle.i18n.TextBundle;

/* JADX INFO: compiled from: ContentTypes.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ContentType extends HeaderValueWithParameters {
    private final String contentSubtype;
    private final String contentType;
    public static final Companion Companion = new Companion(null);
    private static final ContentType Any = new ContentType("*", "*", null, 4, null);

    public final String getContentType() {
        return this.contentType;
    }

    private ContentType(String str, String str2, String str3, List list) {
        super(str3, list);
        this.contentType = str;
        this.contentSubtype = str2;
    }

    public /* synthetic */ ContentType(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? CollectionsKt.emptyList() : list);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ContentType(String contentType, String contentSubtype, List parameters) {
        this(contentType, contentSubtype, contentType + '/' + contentSubtype, parameters);
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(contentSubtype, "contentSubtype");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
    }

    public final ContentType withParameter(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        return hasParameter(name, value) ? this : new ContentType(this.contentType, this.contentSubtype, getContent(), CollectionsKt.plus((Collection) getParameters(), (Object) new HeaderValueParam(name, value)));
    }

    private final boolean hasParameter(String str, String str2) {
        int size = getParameters().size();
        if (size == 0) {
            return false;
        }
        if (size == 1) {
            HeaderValueParam headerValueParam = (HeaderValueParam) getParameters().get(0);
            if (!StringsKt.equals(headerValueParam.getName(), str, true) || !StringsKt.equals(headerValueParam.getValue(), str2, true)) {
                return false;
            }
        } else {
            List<HeaderValueParam> parameters = getParameters();
            if ((parameters instanceof Collection) && parameters.isEmpty()) {
                return false;
            }
            for (HeaderValueParam headerValueParam2 : parameters) {
                if (!StringsKt.equals(headerValueParam2.getName(), str, true) || !StringsKt.equals(headerValueParam2.getValue(), str2, true)) {
                }
            }
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ContentType) {
            ContentType contentType = (ContentType) obj;
            if (StringsKt.equals(this.contentType, contentType.contentType, true) && StringsKt.equals(this.contentSubtype, contentType.contentSubtype, true) && Intrinsics.areEqual(getParameters(), contentType.getParameters())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.contentType;
        Locale locale = Locale.ROOT;
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        int iHashCode = lowerCase.hashCode();
        String lowerCase2 = this.contentSubtype.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return iHashCode + (iHashCode * 31) + lowerCase2.hashCode() + (getParameters().hashCode() * 31);
    }

    /* JADX INFO: compiled from: ContentTypes.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ContentType parse(String value) throws BadContentTypeFormatException {
            Intrinsics.checkNotNullParameter(value, "value");
            if (StringsKt.isBlank(value)) {
                return getAny();
            }
            HeaderValueWithParameters.Companion companion = HeaderValueWithParameters.Companion;
            HeaderValue headerValue = (HeaderValue) CollectionsKt.last(HttpHeaderValueParserKt.parseHeaderValue(value));
            String value2 = headerValue.getValue();
            List params = headerValue.getParams();
            int iIndexOf$default = StringsKt.indexOf$default((CharSequence) value2, '/', 0, false, 6, (Object) null);
            if (iIndexOf$default == -1) {
                if (Intrinsics.areEqual(StringsKt.trim(value2).toString(), "*")) {
                    return ContentType.Companion.getAny();
                }
                throw new BadContentTypeFormatException(value);
            }
            String strSubstring = value2.substring(0, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            String string = StringsKt.trim(strSubstring).toString();
            if (string.length() == 0) {
                throw new BadContentTypeFormatException(value);
            }
            String strSubstring2 = value2.substring(iIndexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
            String string2 = StringsKt.trim(strSubstring2).toString();
            if (StringsKt.contains$default((CharSequence) string, ' ', false, 2, (Object) null) || StringsKt.contains$default((CharSequence) string2, ' ', false, 2, (Object) null)) {
                throw new BadContentTypeFormatException(value);
            }
            if (string2.length() == 0 || StringsKt.contains$default((CharSequence) string2, '/', false, 2, (Object) null)) {
                throw new BadContentTypeFormatException(value);
            }
            return new ContentType(string, string2, params);
        }

        public final ContentType getAny() {
            return ContentType.Any;
        }
    }

    /* JADX INFO: compiled from: ContentTypes.kt */
    public static final class Application {
        private static final ContentType Any;
        private static final ContentType Atom;
        private static final ContentType Cbor;
        private static final ContentType Docx;
        private static final ContentType FormUrlEncoded;
        private static final ContentType GZip;
        private static final ContentType HalJson;
        public static final Application INSTANCE = new Application();
        private static final ContentType JavaScript;
        private static final ContentType Json;
        private static final ContentType OctetStream;
        private static final ContentType Pdf;
        private static final ContentType Pptx;
        private static final ContentType ProblemJson;
        private static final ContentType ProblemXml;
        private static final ContentType ProtoBuf;
        private static final ContentType Rss;
        private static final ContentType Wasm;
        private static final ContentType Xlsx;
        private static final ContentType Xml;
        private static final ContentType Xml_Dtd;
        private static final ContentType Zip;

        private Application() {
        }

        static {
            int i = 4;
            DefaultConstructorMarker defaultConstructorMarker = null;
            List list = null;
            Any = new ContentType("application", "*", list, i, defaultConstructorMarker);
            int i2 = 4;
            DefaultConstructorMarker defaultConstructorMarker2 = null;
            List list2 = null;
            Atom = new ContentType("application", "atom+xml", list2, i2, defaultConstructorMarker2);
            Cbor = new ContentType("application", "cbor", list, i, defaultConstructorMarker);
            Json = new ContentType("application", "json", list2, i2, defaultConstructorMarker2);
            HalJson = new ContentType("application", "hal+json", list, i, defaultConstructorMarker);
            JavaScript = new ContentType("application", "javascript", list2, i2, defaultConstructorMarker2);
            OctetStream = new ContentType("application", "octet-stream", list, i, defaultConstructorMarker);
            Rss = new ContentType("application", "rss+xml", list2, i2, defaultConstructorMarker2);
            Xml = new ContentType("application", "xml", list, i, defaultConstructorMarker);
            Xml_Dtd = new ContentType("application", "xml-dtd", list2, i2, defaultConstructorMarker2);
            Zip = new ContentType("application", "zip", list, i, defaultConstructorMarker);
            GZip = new ContentType("application", "gzip", list2, i2, defaultConstructorMarker2);
            FormUrlEncoded = new ContentType("application", "x-www-form-urlencoded", list, i, defaultConstructorMarker);
            Pdf = new ContentType("application", "pdf", list2, i2, defaultConstructorMarker2);
            Xlsx = new ContentType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet", list, i, defaultConstructorMarker);
            Docx = new ContentType("application", "vnd.openxmlformats-officedocument.wordprocessingml.document", list2, i2, defaultConstructorMarker2);
            Pptx = new ContentType("application", "vnd.openxmlformats-officedocument.presentationml.presentation", list, i, defaultConstructorMarker);
            ProtoBuf = new ContentType("application", "protobuf", list2, i2, defaultConstructorMarker2);
            Wasm = new ContentType("application", "wasm", list, i, defaultConstructorMarker);
            ProblemJson = new ContentType("application", "problem+json", list2, i2, defaultConstructorMarker2);
            ProblemXml = new ContentType("application", "problem+xml", list, i, defaultConstructorMarker);
        }

        public final ContentType getOctetStream() {
            return OctetStream;
        }
    }

    /* JADX INFO: compiled from: ContentTypes.kt */
    public static final class Text {
        private static final ContentType Any;
        private static final ContentType CSS;
        private static final ContentType CSV;
        private static final ContentType EventStream;
        private static final ContentType Html;
        public static final Text INSTANCE = new Text();
        private static final ContentType JavaScript;
        private static final ContentType Plain;
        private static final ContentType VCard;
        private static final ContentType Xml;

        private Text() {
        }

        static {
            int i = 4;
            DefaultConstructorMarker defaultConstructorMarker = null;
            List list = null;
            Any = new ContentType(TextBundle.TEXT_ENTRY, "*", list, i, defaultConstructorMarker);
            int i2 = 4;
            DefaultConstructorMarker defaultConstructorMarker2 = null;
            List list2 = null;
            Plain = new ContentType(TextBundle.TEXT_ENTRY, "plain", list2, i2, defaultConstructorMarker2);
            CSS = new ContentType(TextBundle.TEXT_ENTRY, "css", list, i, defaultConstructorMarker);
            CSV = new ContentType(TextBundle.TEXT_ENTRY, "csv", list2, i2, defaultConstructorMarker2);
            Html = new ContentType(TextBundle.TEXT_ENTRY, "html", list, i, defaultConstructorMarker);
            JavaScript = new ContentType(TextBundle.TEXT_ENTRY, "javascript", list2, i2, defaultConstructorMarker2);
            VCard = new ContentType(TextBundle.TEXT_ENTRY, "vcard", list, i, defaultConstructorMarker);
            Xml = new ContentType(TextBundle.TEXT_ENTRY, "xml", list2, i2, defaultConstructorMarker2);
            EventStream = new ContentType(TextBundle.TEXT_ENTRY, "event-stream", list, i, defaultConstructorMarker);
        }

        public final ContentType getPlain() {
            return Plain;
        }
    }
}
