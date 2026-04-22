package org.fdroid.database;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import org.fdroid.index.v2.ReflectionDiffer;

/* JADX INFO: compiled from: DbDiffUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JÀ\u0001\u0010\u0004\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00060\f2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u000f0\u000e2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\u00060\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u00112\u0018\u0010\u0015\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f\u0012\u0004\u0012\u00020\u00050\u00112\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u000f0\u00112\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\fJ\u0082\u0001\u0010\u0018\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u001e\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f0\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u00112\u001e\u0010\u001c\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f\u0012\u0004\u0012\u00020\u00050\u000eJb\u0010\u0018\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f0\u00112\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0018\u0010\u001c\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\f\u0012\u0004\u0012\u00020\u00050\u0011J\u001c\u0010\u001d\u001a\u00020\u0005*\u00020\b2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\fH\u0002¨\u0006\u001f"}, d2 = {"Lorg/fdroid/database/DbDiffUtils;", "", "<init>", "()V", "diffAndUpdateTable", "", "T", "jsonObject", "Lkotlinx/serialization/json/JsonObject;", "jsonObjectKey", "", "itemList", "", "itemFinder", "Lkotlin/Function2;", "", "newItem", "Lkotlin/Function1;", "deleteAll", "Lkotlin/Function0;", "deleteOne", "insertReplace", "isNewItemValid", "keyDenyList", "diffAndUpdateListTable", "listParser", "Lkotlinx/serialization/json/JsonArray;", "deleteList", "insertNewList", "checkDenyList", "list", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DbDiffUtils {
    public static final DbDiffUtils INSTANCE = new DbDiffUtils();

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean diffAndUpdateTable$lambda$0(Object it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return true;
    }

    private DbDiffUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> void diffAndUpdateTable(JsonObject jsonObject, String jsonObjectKey, List<? extends T> itemList, final Function2 itemFinder, Function1 newItem, Function0 deleteAll, Function1 deleteOne, Function1 insertReplace, Function1 isNewItemValid, List<String> keyDenyList) throws SerializationException {
        JsonObject jsonObject2;
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        Intrinsics.checkNotNullParameter(jsonObjectKey, "jsonObjectKey");
        Intrinsics.checkNotNullParameter(itemList, "itemList");
        Intrinsics.checkNotNullParameter(itemFinder, "itemFinder");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        Intrinsics.checkNotNullParameter(deleteAll, "deleteAll");
        Intrinsics.checkNotNullParameter(deleteOne, "deleteOne");
        Intrinsics.checkNotNullParameter(insertReplace, "insertReplace");
        Intrinsics.checkNotNullParameter(isNewItemValid, "isNewItemValid");
        if (jsonObject.containsKey((Object) jsonObjectKey)) {
            if (Intrinsics.areEqual(jsonObject.get((Object) jsonObjectKey), JsonNull.INSTANCE)) {
                deleteAll.invoke();
                return;
            }
            JsonElement jsonElement = (JsonElement) jsonObject.get((Object) jsonObjectKey);
            if (jsonElement == null || (jsonObject2 = JsonElementKt.getJsonObject(jsonElement)) == null) {
                throw new IllegalStateException(("no " + jsonObjectKey + " object").toString());
            }
            List mutableList = CollectionsKt.toMutableList((Collection) itemList);
            for (Map.Entry entry : jsonObject2.entrySet()) {
                final String str = (String) entry.getKey();
                JsonElement jsonElement2 = (JsonElement) entry.getValue();
                if (jsonElement2 instanceof JsonNull) {
                    CollectionsKt.removeAll(mutableList, new Function1() { // from class: org.fdroid.database.DbDiffUtils$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(DbDiffUtils.diffAndUpdateTable$lambda$3$lambda$1(itemFinder, str, obj));
                        }
                    });
                    deleteOne.invoke(str);
                } else {
                    INSTANCE.checkDenyList(JsonElementKt.getJsonObject(jsonElement2), keyDenyList);
                    Iterator it = mutableList.iterator();
                    int i = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i = -1;
                            break;
                        } else if (((Boolean) itemFinder.invoke(str, it.next())).booleanValue()) {
                            break;
                        } else {
                            i++;
                        }
                    }
                    Object obj = i == -1 ? null : mutableList.get(i);
                    if (obj == null) {
                        Object objApplyDiff = ReflectionDiffer.INSTANCE.applyDiff(newItem.invoke(str), JsonElementKt.getJsonObject(jsonElement2));
                        if (!((Boolean) isNewItemValid.invoke(objApplyDiff)).booleanValue()) {
                            throw new SerializationException(String.valueOf(newItem));
                        }
                        mutableList.add(objApplyDiff);
                    } else {
                        mutableList.set(i, ReflectionDiffer.INSTANCE.applyDiff(obj, JsonElementKt.getJsonObject(jsonElement2)));
                    }
                }
            }
            insertReplace.invoke(mutableList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean diffAndUpdateTable$lambda$3$lambda$1(Function2 function2, String str, Object it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ((Boolean) function2.invoke(str, it)).booleanValue();
    }

    public final <T> void diffAndUpdateListTable(JsonObject jsonObject, String jsonObjectKey, Function2 listParser, Function0 deleteAll, Function1 deleteList, Function2 insertNewList) throws SerializationException {
        JsonObject jsonObject2;
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        Intrinsics.checkNotNullParameter(jsonObjectKey, "jsonObjectKey");
        Intrinsics.checkNotNullParameter(listParser, "listParser");
        Intrinsics.checkNotNullParameter(deleteAll, "deleteAll");
        Intrinsics.checkNotNullParameter(deleteList, "deleteList");
        Intrinsics.checkNotNullParameter(insertNewList, "insertNewList");
        if (jsonObject.containsKey((Object) jsonObjectKey)) {
            if (Intrinsics.areEqual(jsonObject.get((Object) jsonObjectKey), JsonNull.INSTANCE)) {
                deleteAll.invoke();
                return;
            }
            JsonElement jsonElement = (JsonElement) jsonObject.get((Object) jsonObjectKey);
            if (jsonElement == null || (jsonObject2 = JsonElementKt.getJsonObject(jsonElement)) == null) {
                throw new IllegalStateException(("no " + jsonObjectKey + " object").toString());
            }
            for (Map.Entry entry : jsonObject2.entrySet()) {
                String str = (String) entry.getKey();
                JsonElement jsonElement2 = (JsonElement) entry.getValue();
                if (jsonElement2 instanceof JsonNull) {
                    deleteList.invoke(str);
                } else {
                    List list = (List) listParser.invoke(str, JsonElementKt.getJsonArray(jsonElement2));
                    deleteList.invoke(str);
                    insertNewList.invoke(str, list);
                }
            }
        }
    }

    public final <T> void diffAndUpdateListTable(JsonObject jsonObject, String jsonObjectKey, Function1 listParser, Function0 deleteList, Function1 insertNewList) throws SerializationException {
        JsonArray jsonArray;
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        Intrinsics.checkNotNullParameter(jsonObjectKey, "jsonObjectKey");
        Intrinsics.checkNotNullParameter(listParser, "listParser");
        Intrinsics.checkNotNullParameter(deleteList, "deleteList");
        Intrinsics.checkNotNullParameter(insertNewList, "insertNewList");
        if (jsonObject.containsKey((Object) jsonObjectKey)) {
            if (Intrinsics.areEqual(jsonObject.get((Object) jsonObjectKey), JsonNull.INSTANCE)) {
                deleteList.invoke();
                return;
            }
            JsonElement jsonElement = (JsonElement) jsonObject.get((Object) jsonObjectKey);
            if (jsonElement == null || (jsonArray = JsonElementKt.getJsonArray(jsonElement)) == null) {
                throw new IllegalStateException(("no " + jsonObjectKey + " array").toString());
            }
            List list = (List) listParser.invoke(jsonArray);
            deleteList.invoke();
            insertNewList.invoke(list);
        }
    }

    private final void checkDenyList(JsonObject jsonObject, List<String> list) {
        if (list != null) {
            for (String str : list) {
                if (jsonObject.containsKey((Object) str)) {
                    throw new SerializationException(str);
                }
            }
        }
    }
}
