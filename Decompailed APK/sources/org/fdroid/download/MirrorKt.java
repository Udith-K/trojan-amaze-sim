package org.fdroid.download;

import ch.qos.logback.core.CoreConstants;
import io.ktor.http.Url;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: Mirror.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0004H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"isLocal", "", "Lorg/fdroid/download/Mirror;", "isOnion", "Lio/ktor/http/Url;", "download_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class MirrorKt {
    public static final boolean isLocal(Mirror mirror) {
        return mirror != null && mirror.isLocal();
    }

    public static final boolean isOnion(Url url) {
        Intrinsics.checkNotNullParameter(url, "<this>");
        return StringsKt.endsWith$default(url.getHost(), ".onion", false, 2, (Object) null);
    }

    public static final boolean isLocal(Url url) {
        Integer intOrNull;
        Intrinsics.checkNotNullParameter(url, "<this>");
        if (!new Regex("[0-9.]{7,15}").matches(url.getHost())) {
            return false;
        }
        if (!StringsKt.startsWith$default(url.getHost(), "172.", false, 2, (Object) null)) {
            return StringsKt.startsWith$default(url.getHost(), "169.254.", false, 2, (Object) null) || StringsKt.startsWith$default(url.getHost(), "10.", false, 2, (Object) null) || StringsKt.startsWith$default(url.getHost(), "192.168.", false, 2, (Object) null) || Intrinsics.areEqual(url.getHost(), Preferences.DEFAULT_PROXY_HOST);
        }
        String strSubstring = StringsKt.substring(url.getHost(), new IntRange(4, 6));
        if (!StringsKt.endsWith$default((CharSequence) strSubstring, CoreConstants.DOT, false, 2, (Object) null) || (intOrNull = StringsKt.toIntOrNull(StringsKt.trimEnd(strSubstring, CoreConstants.DOT))) == null) {
            return false;
        }
        int iIntValue = intOrNull.intValue();
        return 16 <= iIntValue && iIntValue < 32;
    }
}
