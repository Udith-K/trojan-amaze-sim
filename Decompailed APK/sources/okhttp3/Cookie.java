package okhttp3;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: Cookie.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class Cookie {
    private final String domain;
    private final long expiresAt;
    private final boolean hostOnly;
    private final boolean httpOnly;
    private final String name;
    private final String path;
    private final boolean persistent;
    private final boolean secure;
    private final String value;
    public static final Companion Companion = new Companion(null);
    private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
    private static final Pattern MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    public /* synthetic */ Cookie(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, j, str3, str4, z, z2, z3, z4);
    }

    private Cookie(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.name = str;
        this.value = str2;
        this.expiresAt = j;
        this.domain = str3;
        this.path = str4;
        this.secure = z;
        this.httpOnly = z2;
        this.persistent = z3;
        this.hostOnly = z4;
    }

    public final String name() {
        return this.name;
    }

    public final String value() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Cookie) {
            Cookie cookie = (Cookie) obj;
            if (Intrinsics.areEqual(cookie.name, this.name) && Intrinsics.areEqual(cookie.value, this.value) && cookie.expiresAt == this.expiresAt && Intrinsics.areEqual(cookie.domain, this.domain) && Intrinsics.areEqual(cookie.path, this.path) && cookie.secure == this.secure && cookie.httpOnly == this.httpOnly && cookie.persistent == this.persistent && cookie.hostOnly == this.hostOnly) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((((((527 + this.name.hashCode()) * 31) + this.value.hashCode()) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.expiresAt)) * 31) + this.domain.hashCode()) * 31) + this.path.hashCode()) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.secure)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.httpOnly)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.persistent)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.hostOnly);
    }

    public String toString() {
        return toString$okhttp(false);
    }

    public final String toString$okhttp(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append('=');
        sb.append(this.value);
        if (this.persistent) {
            if (this.expiresAt == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(DatesKt.toHttpDateString(new Date(this.expiresAt)));
            }
        }
        if (!this.hostOnly) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.domain);
        }
        sb.append("; path=");
        sb.append(this.path);
        if (this.secure) {
            sb.append("; secure");
        }
        if (this.httpOnly) {
            sb.append("; httponly");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString()");
        return string;
    }

    /* JADX INFO: compiled from: Cookie.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final boolean domainMatch(String str, String str2) {
            if (Intrinsics.areEqual(str, str2)) {
                return true;
            }
            return StringsKt.endsWith$default(str, str2, false, 2, (Object) null) && str.charAt((str.length() - str2.length()) - 1) == '.' && !Util.canParseAsIpAddress(str);
        }

        public final Cookie parse(HttpUrl url, String setCookie) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(setCookie, "setCookie");
            return parse$okhttp(System.currentTimeMillis(), url, setCookie);
        }

        /* JADX WARN: Removed duplicated region for block: B:43:0x00d7 A[PHI: r1
  0x00d7: PHI (r1v23 long) = (r1v7 long), (r1v11 long) binds: [B:42:0x00d5, B:53:0x00fd] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final okhttp3.Cookie parse$okhttp(long r26, okhttp3.HttpUrl r28, java.lang.String r29) {
            /*
                Method dump skipped, instruction units count: 361
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.Cookie.Companion.parse$okhttp(long, okhttp3.HttpUrl, java.lang.String):okhttp3.Cookie");
        }

        private final long parseExpires(String str, int i, int i2) {
            int iDateCharacterOffset = dateCharacterOffset(str, i, i2, false);
            Matcher matcher = Cookie.TIME_PATTERN.matcher(str);
            int i3 = -1;
            int i4 = -1;
            int i5 = -1;
            int iIndexOf$default = -1;
            int i6 = -1;
            int i7 = -1;
            while (iDateCharacterOffset < i2) {
                int iDateCharacterOffset2 = dateCharacterOffset(str, iDateCharacterOffset + 1, i2, true);
                matcher.region(iDateCharacterOffset, iDateCharacterOffset2);
                if (i4 != -1 || !matcher.usePattern(Cookie.TIME_PATTERN).matches()) {
                    if (i5 != -1 || !matcher.usePattern(Cookie.DAY_OF_MONTH_PATTERN).matches()) {
                        if (iIndexOf$default != -1 || !matcher.usePattern(Cookie.MONTH_PATTERN).matches()) {
                            if (i3 == -1 && matcher.usePattern(Cookie.YEAR_PATTERN).matches()) {
                                String strGroup = matcher.group(1);
                                Intrinsics.checkNotNullExpressionValue(strGroup, "matcher.group(1)");
                                i3 = Integer.parseInt(strGroup);
                            }
                        } else {
                            String strGroup2 = matcher.group(1);
                            Intrinsics.checkNotNullExpressionValue(strGroup2, "matcher.group(1)");
                            Locale US = Locale.US;
                            Intrinsics.checkNotNullExpressionValue(US, "US");
                            String lowerCase = strGroup2.toLowerCase(US);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                            String strPattern = Cookie.MONTH_PATTERN.pattern();
                            Intrinsics.checkNotNullExpressionValue(strPattern, "MONTH_PATTERN.pattern()");
                            iIndexOf$default = StringsKt.indexOf$default((CharSequence) strPattern, lowerCase, 0, false, 6, (Object) null) / 4;
                        }
                    } else {
                        String strGroup3 = matcher.group(1);
                        Intrinsics.checkNotNullExpressionValue(strGroup3, "matcher.group(1)");
                        i5 = Integer.parseInt(strGroup3);
                    }
                } else {
                    String strGroup4 = matcher.group(1);
                    Intrinsics.checkNotNullExpressionValue(strGroup4, "matcher.group(1)");
                    i4 = Integer.parseInt(strGroup4);
                    String strGroup5 = matcher.group(2);
                    Intrinsics.checkNotNullExpressionValue(strGroup5, "matcher.group(2)");
                    i6 = Integer.parseInt(strGroup5);
                    String strGroup6 = matcher.group(3);
                    Intrinsics.checkNotNullExpressionValue(strGroup6, "matcher.group(3)");
                    i7 = Integer.parseInt(strGroup6);
                }
                iDateCharacterOffset = dateCharacterOffset(str, iDateCharacterOffset2 + 1, i2, false);
            }
            if (70 <= i3 && i3 < 100) {
                i3 += 1900;
            }
            if (i3 >= 0 && i3 < 70) {
                i3 += 2000;
            }
            if (i3 < 1601) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (iIndexOf$default == -1) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (1 > i5 || i5 >= 32) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (i4 < 0 || i4 >= 24) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (i6 < 0 || i6 >= 60) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (i7 < 0 || i7 >= 60) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar(Util.UTC);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.set(1, i3);
            gregorianCalendar.set(2, iIndexOf$default - 1);
            gregorianCalendar.set(5, i5);
            gregorianCalendar.set(11, i4);
            gregorianCalendar.set(12, i6);
            gregorianCalendar.set(13, i7);
            gregorianCalendar.set(14, 0);
            return gregorianCalendar.getTimeInMillis();
        }

        private final int dateCharacterOffset(String str, int i, int i2, boolean z) {
            while (i < i2) {
                char cCharAt = str.charAt(i);
                if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || ('0' <= cCharAt && cCharAt < ':') || (('a' <= cCharAt && cCharAt < '{') || (('A' <= cCharAt && cCharAt < '[') || cCharAt == ':'))) == (!z)) {
                    return i;
                }
                i++;
            }
            return i2;
        }

        private final long parseMaxAge(String str) {
            try {
                long j = Long.parseLong(str);
                if (j <= 0) {
                    return Long.MIN_VALUE;
                }
                return j;
            } catch (NumberFormatException e) {
                if (new Regex("-?\\d+").matches(str)) {
                    if (StringsKt.startsWith$default(str, "-", false, 2, (Object) null)) {
                        return Long.MIN_VALUE;
                    }
                    return Preferences.UPDATE_INTERVAL_DISABLED;
                }
                throw e;
            }
        }

        private final String parseDomain(String str) {
            if (StringsKt.endsWith$default(str, ".", false, 2, (Object) null)) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            String canonicalHost = HostnamesKt.toCanonicalHost(StringsKt.removePrefix(str, "."));
            if (canonicalHost != null) {
                return canonicalHost;
            }
            throw new IllegalArgumentException();
        }

        public final List parseAll(HttpUrl url, Headers headers) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(headers, "headers");
            List listValues = headers.values("Set-Cookie");
            int size = listValues.size();
            ArrayList arrayList = null;
            for (int i = 0; i < size; i++) {
                Cookie cookie = parse(url, (String) listValues.get(i));
                if (cookie != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(cookie);
                }
            }
            if (arrayList != null) {
                List listUnmodifiableList = Collections.unmodifiableList(arrayList);
                Intrinsics.checkNotNullExpressionValue(listUnmodifiableList, "{\n        Collections.un…ableList(cookies)\n      }");
                return listUnmodifiableList;
            }
            return CollectionsKt.emptyList();
        }
    }
}
