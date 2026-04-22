package okhttp3.internal.ws;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.internal.Util;

/* JADX INFO: compiled from: WebSocketExtensions.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class WebSocketExtensions {
    public static final Companion Companion = new Companion(null);
    public final Integer clientMaxWindowBits;
    public final boolean clientNoContextTakeover;
    public final boolean perMessageDeflate;
    public final Integer serverMaxWindowBits;
    public final boolean serverNoContextTakeover;
    public final boolean unknownValues;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebSocketExtensions)) {
            return false;
        }
        WebSocketExtensions webSocketExtensions = (WebSocketExtensions) obj;
        return this.perMessageDeflate == webSocketExtensions.perMessageDeflate && Intrinsics.areEqual(this.clientMaxWindowBits, webSocketExtensions.clientMaxWindowBits) && this.clientNoContextTakeover == webSocketExtensions.clientNoContextTakeover && Intrinsics.areEqual(this.serverMaxWindowBits, webSocketExtensions.serverMaxWindowBits) && this.serverNoContextTakeover == webSocketExtensions.serverNoContextTakeover && this.unknownValues == webSocketExtensions.unknownValues;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v4, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7, types: [int] */
    /* JADX WARN: Type inference failed for: r2v9 */
    public int hashCode() {
        boolean z = this.perMessageDeflate;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        Integer num = this.clientMaxWindowBits;
        int iHashCode = (i + (num == null ? 0 : num.hashCode())) * 31;
        boolean z2 = this.clientNoContextTakeover;
        ?? r2 = z2;
        if (z2) {
            r2 = 1;
        }
        int i2 = (iHashCode + r2) * 31;
        Integer num2 = this.serverMaxWindowBits;
        int iHashCode2 = (i2 + (num2 != null ? num2.hashCode() : 0)) * 31;
        boolean z3 = this.serverNoContextTakeover;
        ?? r22 = z3;
        if (z3) {
            r22 = 1;
        }
        int i3 = (iHashCode2 + r22) * 31;
        boolean z4 = this.unknownValues;
        return i3 + (z4 ? 1 : z4);
    }

    public String toString() {
        return "WebSocketExtensions(perMessageDeflate=" + this.perMessageDeflate + ", clientMaxWindowBits=" + this.clientMaxWindowBits + ", clientNoContextTakeover=" + this.clientNoContextTakeover + ", serverMaxWindowBits=" + this.serverMaxWindowBits + ", serverNoContextTakeover=" + this.serverNoContextTakeover + ", unknownValues=" + this.unknownValues + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public WebSocketExtensions(boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4) {
        this.perMessageDeflate = z;
        this.clientMaxWindowBits = num;
        this.clientNoContextTakeover = z2;
        this.serverMaxWindowBits = num2;
        this.serverNoContextTakeover = z3;
        this.unknownValues = z4;
    }

    public final boolean noContextTakeover(boolean z) {
        if (z) {
            return this.clientNoContextTakeover;
        }
        return this.serverNoContextTakeover;
    }

    /* JADX INFO: compiled from: WebSocketExtensions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WebSocketExtensions parse(Headers responseHeaders) {
            Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
            int size = responseHeaders.size();
            boolean z = false;
            Integer intOrNull = null;
            boolean z2 = false;
            Integer intOrNull2 = null;
            boolean z3 = false;
            boolean z4 = false;
            for (int i = 0; i < size; i++) {
                if (StringsKt.equals(responseHeaders.name(i), "Sec-WebSocket-Extensions", true)) {
                    String strValue = responseHeaders.value(i);
                    int i2 = 0;
                    while (i2 < strValue.length()) {
                        int iDelimiterOffset$default = Util.delimiterOffset$default(strValue, CoreConstants.COMMA_CHAR, i2, 0, 4, null);
                        int iDelimiterOffset = Util.delimiterOffset(strValue, ';', i2, iDelimiterOffset$default);
                        String strTrimSubstring = Util.trimSubstring(strValue, i2, iDelimiterOffset);
                        int i3 = iDelimiterOffset + 1;
                        if (StringsKt.equals(strTrimSubstring, "permessage-deflate", true)) {
                            if (z) {
                                z4 = true;
                            }
                            i2 = i3;
                            while (i2 < iDelimiterOffset$default) {
                                int iDelimiterOffset2 = Util.delimiterOffset(strValue, ';', i2, iDelimiterOffset$default);
                                int iDelimiterOffset3 = Util.delimiterOffset(strValue, '=', i2, iDelimiterOffset2);
                                String strTrimSubstring2 = Util.trimSubstring(strValue, i2, iDelimiterOffset3);
                                String strRemoveSurrounding = iDelimiterOffset3 < iDelimiterOffset2 ? StringsKt.removeSurrounding(Util.trimSubstring(strValue, iDelimiterOffset3 + 1, iDelimiterOffset2), "\"") : null;
                                i2 = iDelimiterOffset2 + 1;
                                if (StringsKt.equals(strTrimSubstring2, "client_max_window_bits", true)) {
                                    if (intOrNull != null) {
                                        z4 = true;
                                    }
                                    intOrNull = strRemoveSurrounding != null ? StringsKt.toIntOrNull(strRemoveSurrounding) : null;
                                    if (intOrNull == null) {
                                        z4 = true;
                                    }
                                } else if (StringsKt.equals(strTrimSubstring2, "client_no_context_takeover", true)) {
                                    if (z2) {
                                        z4 = true;
                                    }
                                    if (strRemoveSurrounding != null) {
                                        z4 = true;
                                    }
                                    z2 = true;
                                } else if (StringsKt.equals(strTrimSubstring2, "server_max_window_bits", true)) {
                                    if (intOrNull2 != null) {
                                        z4 = true;
                                    }
                                    intOrNull2 = strRemoveSurrounding != null ? StringsKt.toIntOrNull(strRemoveSurrounding) : null;
                                    if (intOrNull2 == null) {
                                        z4 = true;
                                    }
                                } else if (StringsKt.equals(strTrimSubstring2, "server_no_context_takeover", true)) {
                                    if (z3) {
                                        z4 = true;
                                    }
                                    if (strRemoveSurrounding != null) {
                                        z4 = true;
                                    }
                                    z3 = true;
                                } else {
                                    z4 = true;
                                }
                            }
                            z = true;
                        } else {
                            i2 = i3;
                            z4 = true;
                        }
                    }
                }
            }
            return new WebSocketExtensions(z, intOrNull, z2, intOrNull2, z3, z4);
        }
    }
}
