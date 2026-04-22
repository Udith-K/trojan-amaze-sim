package org.acra.data;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.acra.ReportField;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: StringFormat.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J6\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u0014"}, d2 = {"Lorg/acra/data/StringFormat;", "", "matchingHttpContentType", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getMatchingHttpContentType", "()Ljava/lang/String;", "JSON", "KEY_VALUE_LIST", "toFormattedString", "data", "Lorg/acra/data/CrashReportData;", "order", "", "Lorg/acra/ReportField;", "mainJoiner", "subJoiner", "urlEncode", "", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class StringFormat {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ StringFormat[] $VALUES;
    public static final StringFormat JSON = new StringFormat("JSON", 0) { // from class: org.acra.data.StringFormat.JSON
        {
            String str = "application/json";
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // org.acra.data.StringFormat
        public String toFormattedString(CrashReportData data, List order, String mainJoiner, String subJoiner, boolean z) throws JSONException {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(order, "order");
            Intrinsics.checkNotNullParameter(mainJoiner, "mainJoiner");
            Intrinsics.checkNotNullParameter(subJoiner, "subJoiner");
            Map mutableMap = MapsKt.toMutableMap(data.toMap());
            JSONStringer jSONStringerObject = new JSONStringer().object();
            Iterator it = order.iterator();
            while (it.hasNext()) {
                ReportField reportField = (ReportField) it.next();
                jSONStringerObject.key(reportField.toString()).value(mutableMap.remove(reportField.toString()));
            }
            for (Map.Entry entry : mutableMap.entrySet()) {
                String str = (String) entry.getKey();
                jSONStringerObject.key(str).value(entry.getValue());
            }
            String string = jSONStringerObject.endObject().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
    };
    public static final StringFormat KEY_VALUE_LIST = new StringFormat("KEY_VALUE_LIST", 1) { // from class: org.acra.data.StringFormat.KEY_VALUE_LIST
        {
            String str = "application/x-www-form-urlencoded";
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        @Override // org.acra.data.StringFormat
        public String toFormattedString(CrashReportData data, List order, String mainJoiner, String subJoiner, boolean z) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(order, "order");
            Intrinsics.checkNotNullParameter(mainJoiner, "mainJoiner");
            Intrinsics.checkNotNullParameter(subJoiner, "subJoiner");
            Map mutableMap = MapsKt.toMutableMap(toStringMap(data.toMap(), subJoiner));
            StringBuilder sb = new StringBuilder();
            Iterator it = order.iterator();
            while (it.hasNext()) {
                ReportField reportField = (ReportField) it.next();
                append(sb, reportField.toString(), (String) mutableMap.remove(reportField.toString()), mainJoiner, z);
            }
            for (Map.Entry entry : mutableMap.entrySet()) {
                append(sb, (String) entry.getKey(), (String) entry.getValue(), mainJoiner, z);
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }

        private final void append(StringBuilder sb, String str, String str2, String str3, boolean z) {
            if (sb.length() > 0) {
                sb.append(str3);
            }
            if (z) {
                str = str != null ? URLEncoder.encode(str, "UTF-8") : null;
                str2 = str2 != null ? URLEncoder.encode(str2, "UTF-8") : null;
            }
            sb.append(str);
            sb.append('=');
            sb.append(str2);
        }

        private final String valueToString(String str, Object obj) {
            if (obj instanceof JSONObject) {
                return CollectionsKt.joinToString$default(flatten((JSONObject) obj), str, null, null, 0, null, null, 62, null);
            }
            return String.valueOf(obj);
        }

        private final List flatten(JSONObject jSONObject) {
            Object obj;
            Collection collectionListOf;
            Iterator<String> itKeys = jSONObject.keys();
            Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
            List<String> list = SequencesKt.toList(SequencesKt.asSequence(itKeys));
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                try {
                    obj = jSONObject.get(str);
                } catch (JSONException unused) {
                    obj = null;
                }
                if (obj instanceof JSONObject) {
                    List listFlatten = flatten((JSONObject) obj);
                    collectionListOf = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFlatten, 10));
                    Iterator it = listFlatten.iterator();
                    while (it.hasNext()) {
                        collectionListOf.add(str + "." + ((String) it.next()));
                    }
                } else {
                    collectionListOf = CollectionsKt.listOf(str + "=" + obj);
                }
                CollectionsKt.addAll(arrayList, collectionListOf);
            }
            return arrayList;
        }

        private final Map toStringMap(Map map, String str) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
            for (Map.Entry entry : map.entrySet()) {
                linkedHashMap.put(entry.getKey(), valueToString(str, entry.getValue()));
            }
            return MapsKt.toMap(linkedHashMap);
        }
    };
    private final String matchingHttpContentType;

    private static final /* synthetic */ StringFormat[] $values() {
        return new StringFormat[]{JSON, KEY_VALUE_LIST};
    }

    public /* synthetic */ StringFormat(String str, int i, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public abstract String toFormattedString(CrashReportData data, List<? extends ReportField> order, String mainJoiner, String subJoiner, boolean urlEncode) throws Exception;

    private StringFormat(String str, int i, String str2) {
        this.matchingHttpContentType = str2;
    }

    public final String getMatchingHttpContentType() {
        return this.matchingHttpContentType;
    }

    static {
        StringFormat[] stringFormatArr$values = $values();
        $VALUES = stringFormatArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(stringFormatArr$values);
    }

    public static StringFormat valueOf(String str) {
        return (StringFormat) Enum.valueOf(StringFormat.class, str);
    }

    public static StringFormat[] values() {
        return (StringFormat[]) $VALUES.clone();
    }
}
