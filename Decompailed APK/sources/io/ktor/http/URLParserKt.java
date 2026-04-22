package io.ktor.http;

import ch.qos.logback.core.joran.action.Action;
import io.ktor.util.CharsetKt;
import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: URLParser.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class URLParserKt {
    private static final List ROOT_PATH = CollectionsKt.listOf("");

    public static final List getROOT_PATH() {
        return ROOT_PATH;
    }

    public static final URLBuilder takeFrom(URLBuilder uRLBuilder, String urlString) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        if (StringsKt.isBlank(urlString)) {
            return uRLBuilder;
        }
        try {
            return takeFromUnsafe(uRLBuilder, urlString);
        } catch (Throwable th) {
            throw new URLParserException(urlString, th);
        }
    }

    private static final void parseFile(URLBuilder uRLBuilder, String str, int i, int i2, int i3) {
        if (i3 != 2) {
            if (i3 == 3) {
                uRLBuilder.setHost("");
                StringBuilder sb = new StringBuilder();
                sb.append('/');
                String strSubstring = str.substring(i, i2);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                sb.append(strSubstring);
                URLBuilderKt.setEncodedPath(uRLBuilder, sb.toString());
                return;
            }
            throw new IllegalArgumentException("Invalid file url: " + str);
        }
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, '/', i, false, 4, (Object) null);
        if (iIndexOf$default == -1 || iIndexOf$default == i2) {
            String strSubstring2 = str.substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
            uRLBuilder.setHost(strSubstring2);
        } else {
            String strSubstring3 = str.substring(i, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
            uRLBuilder.setHost(strSubstring3);
            String strSubstring4 = str.substring(iIndexOf$default, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring4, "this as java.lang.String…ing(startIndex, endIndex)");
            URLBuilderKt.setEncodedPath(uRLBuilder, strSubstring4);
        }
    }

    private static final void parseMailto(URLBuilder uRLBuilder, String str, int i, int i2) {
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, "@", i, false, 4, (Object) null);
        if (iIndexOf$default == -1) {
            throw new IllegalArgumentException("Invalid mailto url: " + str + ", it should contain '@'.");
        }
        String strSubstring = str.substring(i, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        uRLBuilder.setUser(CodecsKt.decodeURLPart$default(strSubstring, 0, 0, null, 7, null));
        String strSubstring2 = str.substring(iIndexOf$default + 1, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
        uRLBuilder.setHost(strSubstring2);
    }

    public static final URLBuilder takeFromUnsafe(URLBuilder uRLBuilder, String urlString) {
        int i;
        List listEmptyList;
        List listEmptyList2;
        int i2;
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        int length = urlString.length();
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                i3 = -1;
                break;
            }
            if (!CharsKt.isWhitespace(urlString.charAt(i3))) {
                break;
            }
            i3++;
        }
        int length2 = urlString.length() - 1;
        if (length2 >= 0) {
            while (true) {
                int i4 = length2 - 1;
                if (!CharsKt.isWhitespace(urlString.charAt(length2))) {
                    i = length2;
                    break;
                }
                if (i4 < 0) {
                    break;
                }
                length2 = i4;
            }
            i = -1;
        } else {
            i = -1;
        }
        int i5 = i + 1;
        int iFindScheme = findScheme(urlString, i3, i5);
        if (iFindScheme > 0) {
            String strSubstring = urlString.substring(i3, i3 + iFindScheme);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            uRLBuilder.setProtocol(URLProtocol.Companion.createOrDefault(strSubstring));
            i3 += iFindScheme + 1;
        }
        int iCount = count(urlString, i3, i5, '/');
        int iIntValue = i3 + iCount;
        if (Intrinsics.areEqual(uRLBuilder.getProtocol().getName(), Action.FILE_ATTRIBUTE)) {
            parseFile(uRLBuilder, urlString, iIntValue, i5, iCount);
            return uRLBuilder;
        }
        if (Intrinsics.areEqual(uRLBuilder.getProtocol().getName(), "mailto")) {
            if (iCount != 0) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            parseMailto(uRLBuilder, urlString, iIntValue, i5);
            return uRLBuilder;
        }
        if (iCount >= 2) {
            int i6 = iIntValue;
            while (true) {
                i2 = i6;
                Integer numValueOf = Integer.valueOf(StringsKt.indexOfAny$default(urlString, CharsetKt.toCharArray("@/\\?#"), i6, false, 4, null));
                if (numValueOf.intValue() <= 0) {
                    numValueOf = null;
                }
                iIntValue = numValueOf != null ? numValueOf.intValue() : i5;
                if (iIntValue >= i5 || urlString.charAt(iIntValue) != '@') {
                    break;
                }
                int iIndexOfColonInHostPort = indexOfColonInHostPort(urlString, i2, iIntValue);
                if (iIndexOfColonInHostPort != -1) {
                    String strSubstring2 = urlString.substring(i2, iIndexOfColonInHostPort);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    uRLBuilder.setEncodedUser(strSubstring2);
                    String strSubstring3 = urlString.substring(iIndexOfColonInHostPort + 1, iIntValue);
                    Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
                    uRLBuilder.setEncodedPassword(strSubstring3);
                } else {
                    String strSubstring4 = urlString.substring(i2, iIntValue);
                    Intrinsics.checkNotNullExpressionValue(strSubstring4, "this as java.lang.String…ing(startIndex, endIndex)");
                    uRLBuilder.setEncodedUser(strSubstring4);
                }
                i6 = iIntValue + 1;
            }
            fillHost(uRLBuilder, urlString, i2, iIntValue);
        }
        int query = iIntValue;
        if (query >= i5) {
            uRLBuilder.setEncodedPathSegments(urlString.charAt(i) == '/' ? ROOT_PATH : CollectionsKt.emptyList());
            return uRLBuilder;
        }
        if (iCount == 0) {
            listEmptyList = CollectionsKt.dropLast(uRLBuilder.getEncodedPathSegments(), 1);
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        uRLBuilder.setEncodedPathSegments(listEmptyList);
        Integer numValueOf2 = Integer.valueOf(StringsKt.indexOfAny$default(urlString, CharsetKt.toCharArray("?#"), query, false, 4, null));
        Integer num = numValueOf2.intValue() > 0 ? numValueOf2 : null;
        int iIntValue2 = num != null ? num.intValue() : i5;
        if (iIntValue2 > query) {
            String strSubstring5 = urlString.substring(query, iIntValue2);
            Intrinsics.checkNotNullExpressionValue(strSubstring5, "this as java.lang.String…ing(startIndex, endIndex)");
            List listEmptyList3 = (uRLBuilder.getEncodedPathSegments().size() == 1 && ((CharSequence) CollectionsKt.first(uRLBuilder.getEncodedPathSegments())).length() == 0) ? CollectionsKt.emptyList() : uRLBuilder.getEncodedPathSegments();
            List listSplit$default = Intrinsics.areEqual(strSubstring5, "/") ? ROOT_PATH : StringsKt.split$default((CharSequence) strSubstring5, new char[]{'/'}, false, 0, 6, (Object) null);
            if (iCount == 1) {
                listEmptyList2 = ROOT_PATH;
            } else {
                listEmptyList2 = CollectionsKt.emptyList();
            }
            uRLBuilder.setEncodedPathSegments(CollectionsKt.plus((Collection) listEmptyList3, (Iterable) CollectionsKt.plus((Collection) listEmptyList2, (Iterable) listSplit$default)));
            query = iIntValue2;
        }
        if (query < i5 && urlString.charAt(query) == '?') {
            query = parseQuery(uRLBuilder, urlString, query, i5);
        }
        parseFragment(uRLBuilder, urlString, query, i5);
        return uRLBuilder;
    }

    private static final int parseQuery(final URLBuilder uRLBuilder, String str, int i, int i2) {
        int i3 = i + 1;
        if (i3 == i2) {
            uRLBuilder.setTrailingQuery(true);
            return i2;
        }
        Integer numValueOf = Integer.valueOf(StringsKt.indexOf$default((CharSequence) str, '#', i3, false, 4, (Object) null));
        if (numValueOf.intValue() <= 0) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            i2 = numValueOf.intValue();
        }
        String strSubstring = str.substring(i3, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        QueryKt.parseQueryString$default(strSubstring, 0, 0, false, 6, null).forEach(new Function2() { // from class: io.ktor.http.URLParserKt.parseQuery.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((String) obj, (List) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(String key, List values) {
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(values, "values");
                uRLBuilder.getEncodedParameters().appendAll(key, values);
            }
        });
        return i2;
    }

    private static final void parseFragment(URLBuilder uRLBuilder, String str, int i, int i2) {
        if (i >= i2 || str.charAt(i) != '#') {
            return;
        }
        String strSubstring = str.substring(i + 1, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        uRLBuilder.setEncodedFragment(strSubstring);
    }

    private static final void fillHost(URLBuilder uRLBuilder, String str, int i, int i2) {
        Integer numValueOf = Integer.valueOf(indexOfColonInHostPort(str, i, i2));
        if (numValueOf.intValue() <= 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : i2;
        String strSubstring = str.substring(i, iIntValue);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        uRLBuilder.setHost(strSubstring);
        int i3 = iIntValue + 1;
        if (i3 < i2) {
            String strSubstring2 = str.substring(i3, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
            uRLBuilder.setPort(Integer.parseInt(strSubstring2));
            return;
        }
        uRLBuilder.setPort(0);
    }

    private static final int findScheme(String str, int i, int i2) {
        int i3;
        int i4;
        char cCharAt = str.charAt(i);
        if (('a' > cCharAt || cCharAt >= '{') && ('A' > cCharAt || cCharAt >= '[')) {
            i3 = i;
            i4 = i3;
        } else {
            i3 = i;
            i4 = -1;
        }
        while (i3 < i2) {
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 == ':') {
                if (i4 == -1) {
                    return i3 - i;
                }
                throw new IllegalArgumentException("Illegal character in scheme at position " + i4);
            }
            if (cCharAt2 == '/' || cCharAt2 == '?' || cCharAt2 == '#') {
                break;
            }
            if (i4 == -1 && (('a' > cCharAt2 || cCharAt2 >= '{') && (('A' > cCharAt2 || cCharAt2 >= '[') && (('0' > cCharAt2 || cCharAt2 >= ':') && cCharAt2 != '.' && cCharAt2 != '+' && cCharAt2 != '-')))) {
                i4 = i3;
            }
            i3++;
        }
        return -1;
    }

    private static final int count(String str, int i, int i2, char c) {
        int i3 = 0;
        while (true) {
            int i4 = i + i3;
            if (i4 >= i2 || str.charAt(i4) != c) {
                break;
            }
            i3++;
        }
        return i3;
    }

    private static final int indexOfColonInHostPort(String str, int i, int i2) {
        boolean z = false;
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '[') {
                z = true;
            } else if (cCharAt == ']') {
                z = false;
            } else if (cCharAt == ':' && !z) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
