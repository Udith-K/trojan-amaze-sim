package org.fdroid.database;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import org.bouncycastle.i18n.TextBundle;
import org.fdroid.index.IndexParser;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: compiled from: Converters.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\u0004\u0018\u0001`\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0007J&\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u001a\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\u0004\u0018\u0001`\fH\u0007J&\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006j\u0004\u0018\u0001`\u00112\b\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0007J&\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u001a\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006j\u0004\u0018\u0001`\u0011H\u0007J0\u0010\u0014\u001a \u0012\u0004\u0012\u00020\u0007\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\f\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0007J0\u0010\u0015\u001a\u0004\u0018\u00010\u00072$\u0010\u000f\u001a \u0012\u0004\u0012\u00020\u0007\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\f\u0018\u00010\u0006H\u0007J\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u00172\b\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0007J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0017H\u0007R \u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\n\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/fdroid/database/Converters;", "", "<init>", "()V", "localizedTextV2Serializer", "Lkotlinx/serialization/KSerializer;", "", "", "localizedFileV2Serializer", "Lorg/fdroid/index/v2/FileV2;", "mapOfLocalizedTextV2Serializer", "fromStringToLocalizedTextV2", "Lorg/fdroid/index/v2/LocalizedTextV2;", Action.VALUE_ATTRIBUTE, "localizedTextV2toString", TextBundle.TEXT_ENTRY, "fromStringToLocalizedFileV2", "Lorg/fdroid/index/v2/LocalizedFileV2;", "localizedFileV2toString", Action.FILE_ATTRIBUTE, "fromStringToMapOfLocalizedTextV2", "mapOfLocalizedTextV2toString", "fromStringToListString", "", "listStringToString", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Converters {
    public static final Converters INSTANCE = new Converters();
    private static final KSerializer localizedFileV2Serializer;
    private static final KSerializer localizedTextV2Serializer;
    private static final KSerializer mapOfLocalizedTextV2Serializer;

    private Converters() {
    }

    static {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        KSerializer kSerializerMapSerializer = BuiltinSerializersKt.MapSerializer(BuiltinSerializersKt.serializer(stringCompanionObject), BuiltinSerializersKt.serializer(stringCompanionObject));
        localizedTextV2Serializer = kSerializerMapSerializer;
        localizedFileV2Serializer = BuiltinSerializersKt.MapSerializer(BuiltinSerializersKt.serializer(stringCompanionObject), FileV2.INSTANCE.serializer());
        mapOfLocalizedTextV2Serializer = BuiltinSerializersKt.MapSerializer(BuiltinSerializersKt.serializer(stringCompanionObject), kSerializerMapSerializer);
    }

    public final Map<String, String> fromStringToLocalizedTextV2(String value) {
        if (value != null) {
            return (Map) IndexParser.getJson().decodeFromString(localizedTextV2Serializer, value);
        }
        return null;
    }

    public final String localizedTextV2toString(Map<String, String> text) {
        if (text != null) {
            return IndexParser.getJson().encodeToString(localizedTextV2Serializer, text);
        }
        return null;
    }

    public final Map<String, FileV2> fromStringToLocalizedFileV2(String value) {
        if (value != null) {
            return (Map) IndexParser.getJson().decodeFromString(localizedFileV2Serializer, value);
        }
        return null;
    }

    public final String localizedFileV2toString(Map<String, FileV2> file) {
        if (file != null) {
            return IndexParser.getJson().encodeToString(localizedFileV2Serializer, file);
        }
        return null;
    }

    public final Map<String, Map<String, String>> fromStringToMapOfLocalizedTextV2(String value) {
        if (value != null) {
            return (Map) IndexParser.getJson().decodeFromString(mapOfLocalizedTextV2Serializer, value);
        }
        return null;
    }

    public final String mapOfLocalizedTextV2toString(Map<String, ? extends Map<String, String>> text) {
        if (text != null) {
            return IndexParser.getJson().encodeToString(mapOfLocalizedTextV2Serializer, text);
        }
        return null;
    }

    public final List<String> fromStringToListString(String value) {
        List listSplit$default;
        if (value == null || (listSplit$default = StringsKt.split$default((CharSequence) value, new char[]{CoreConstants.COMMA_CHAR}, false, 0, 6, (Object) null)) == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSplit$default) {
            if (((String) obj).length() > 0) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final String listStringToString(List<String> text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        return CollectionsKt.joinToString$default(text, ",", ",", ",", 0, null, new Function1() { // from class: org.fdroid.database.Converters$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Converters.listStringToString$lambda$7((String) obj);
            }
        }, 24, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence listStringToString$lambda$7(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.replace$default(it, CoreConstants.COMMA_CHAR, '_', false, 4, (Object) null);
    }
}
