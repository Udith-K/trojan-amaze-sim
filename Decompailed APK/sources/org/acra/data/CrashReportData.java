package org.acra.data;

import java.util.Iterator;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.acra.ACRA;
import org.acra.ReportField;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CrashReportData.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CrashReportData {
    private final JSONObject content;

    public CrashReportData() {
        this.content = new JSONObject();
    }

    public CrashReportData(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.content = new JSONObject(json);
    }

    public final synchronized void put(String key, boolean z) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            this.content.put(key, z);
        } catch (JSONException unused) {
            ACRA.log.w(ACRA.LOG_TAG, "Failed to put value into CrashReportData: " + z);
        }
    }

    public final synchronized void put(String key, long j) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            this.content.put(key, j);
        } catch (JSONException unused) {
            ACRA.log.w(ACRA.LOG_TAG, "Failed to put value into CrashReportData: " + j);
        }
    }

    public final synchronized void put(String key, String str) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (str == null) {
            putNA(key);
            return;
        }
        try {
            this.content.put(key, str);
        } catch (JSONException unused) {
            ACRA.log.w(ACRA.LOG_TAG, "Failed to put value into CrashReportData: " + str);
        }
    }

    public final synchronized void put(String key, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (jSONObject == null) {
            putNA(key);
            return;
        }
        try {
            this.content.put(key, jSONObject);
        } catch (JSONException unused) {
            ACRA.log.w(ACRA.LOG_TAG, "Failed to put value into CrashReportData: " + jSONObject);
        }
    }

    public final synchronized void put(ReportField key, boolean z) {
        Intrinsics.checkNotNullParameter(key, "key");
        put(key.toString(), z);
    }

    public final synchronized void put(ReportField key, long j) {
        Intrinsics.checkNotNullParameter(key, "key");
        put(key.toString(), j);
    }

    public final synchronized void put(ReportField key, String str) {
        Intrinsics.checkNotNullParameter(key, "key");
        put(key.toString(), str);
    }

    public final synchronized void put(ReportField key, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(key, "key");
        put(key.toString(), jSONObject);
    }

    private final void putNA(String str) {
        try {
            this.content.put(str, "N/A");
        } catch (JSONException unused) {
        }
    }

    public final String getString(ReportField key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.content.optString(key.toString());
    }

    public final Object get(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.content.opt(key);
    }

    public final String toJSON() throws JSONException {
        try {
            return StringFormat.JSON.toFormattedString(this, CollectionsKt.emptyList(), "", "", false);
        } catch (JSONException e) {
            throw e;
        } catch (Exception e2) {
            throw new JSONException(e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair toMap$lambda$7(CrashReportData crashReportData, String str) {
        Intrinsics.checkNotNull(str);
        return TuplesKt.to(str, crashReportData.get(str));
    }

    public final Map toMap() {
        Iterator<String> itKeys = this.content.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
        return MapsKt.toMap(SequencesKt.map(SequencesKt.asSequence(itKeys), new Function1() { // from class: org.acra.data.CrashReportData$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CrashReportData.toMap$lambda$7(this.f$0, (String) obj);
            }
        }));
    }
}
