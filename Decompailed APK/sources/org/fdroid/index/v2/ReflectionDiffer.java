package org.fdroid.index.v2;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.full.KClasses;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.modules.SerializersModule;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;

/* JADX INFO: compiled from: ReflectionDiffer.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00012\u0006\u0010\u0006\u001a\u0002H\u00052\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ/\u0010\n\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00050\f2\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0004\b\r\u0010\u000eJC\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0006\u001a\u0004\u0018\u0001H\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\u0015J8\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010j\u0002`\u00172\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010j\u0002`\u00172\u0006\u0010\u0007\u001a\u00020\bH\u0002J8\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00190\u0010j\u0002`\u001a2\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00190\u0010j\u0002`\u001a2\u0006\u0010\u0007\u001a\u00020\bH\u0002JP\u0010\u001b\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010j\u0002`\u00170\u00102\"\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010j\u0002`\u00170\u00102\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00020\u001eH\u0002J\u000e\u0010\u001f\u001a\u0004\u0018\u00010 *\u00020\u001eH\u0002J\u000e\u0010!\u001a\u0004\u0018\u00010\b*\u00020\u001eH\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0011H\u0002J;\u0010%\u001a\u0002H\u0005\"\u0006\b\u0000\u0010\u0005\u0018\u0001*\u00020&2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u00050)H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010*\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006+"}, d2 = {"Lorg/fdroid/index/v2/ReflectionDiffer;", "", "<init>", "()V", "applyDiff", "T", "obj", "diff", "Lkotlinx/serialization/json/JsonObject;", "(Ljava/lang/Object;Lkotlinx/serialization/json/JsonObject;)Ljava/lang/Object;", "constructFromJson", "factory", "Lkotlin/reflect/KFunction;", "constructFromJson$index_release", "(Lkotlin/reflect/KFunction;Lkotlinx/serialization/json/JsonObject;)Ljava/lang/Object;", "diffMap", "", "", BonjourPeer.TYPE, "Lkotlin/reflect/KType;", Action.KEY_ATTRIBUTE, "(Lkotlin/reflect/KType;Ljava/lang/Object;Ljava/lang/String;Lkotlinx/serialization/json/JsonObject;)Ljava/util/Map;", "applyTextDiff", "Lorg/fdroid/index/v2/LocalizedTextV2;", "applyFileDiff", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", "applyMapTextDiff", "primitiveOrNull", "Lkotlinx/serialization/json/JsonPrimitive;", "Lkotlinx/serialization/json/JsonElement;", "jsonArrayOrNull", "Lkotlinx/serialization/json/JsonArray;", "jsonObjectOrNull", "e", "", "msg", "decodeOr", "Lkotlinx/serialization/json/Json;", "json", CoreConstants.DEFAULT_CONTEXT_NAME, "Lkotlin/Function0;", "(Lkotlinx/serialization/json/Json;Ljava/lang/String;Lkotlinx/serialization/json/JsonObject;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ReflectionDiffer {
    public static final ReflectionDiffer INSTANCE = new ReflectionDiffer();

    private ReflectionDiffer() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T applyDiff(T obj, JsonObject diff) throws SerializationException {
        T next;
        Object objApplyDiff;
        JsonArray<JsonElement> jsonArrayJsonArrayOrNull;
        String contentOrNull;
        JsonElement jsonElement;
        JsonPrimitive jsonPrimitivePrimitiveOrNull;
        JsonPrimitive jsonPrimitivePrimitiveOrNull2;
        JsonPrimitive jsonPrimitivePrimitiveOrNull3;
        JsonPrimitive jsonPrimitivePrimitiveOrNull4;
        Intrinsics.checkNotNullParameter(obj, "obj");
        Intrinsics.checkNotNullParameter(diff, "diff");
        KFunction primaryConstructor = KClasses.getPrimaryConstructor(Reflection.getOrCreateKotlinClass(obj.getClass()));
        if (primaryConstructor == null) {
            e("no primary constructor " + Reflection.getOrCreateKotlinClass(obj.getClass()));
            throw new KotlinNothingValueException();
        }
        HashMap map = new HashMap();
        for (KParameter kParameter : primaryConstructor.getParameters()) {
            Iterator<T> it = KClasses.getMemberProperties(Reflection.getOrCreateKotlinClass(obj.getClass())).iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual(((KProperty1) next).getName(), kParameter.getName())) {
                    break;
                }
            }
            KProperty1 kProperty1 = (KProperty1) next;
            if (kProperty1 == null) {
                INSTANCE.e("no member property for constructor, is data class?");
                throw new KotlinNothingValueException();
            }
            if (!diff.containsKey((Object) kProperty1.getName())) {
                map.put(kParameter, kProperty1.getGetter().call(obj));
            } else if (diff.get((Object) kProperty1.getName()) instanceof JsonNull) {
                if (kParameter.getType().isMarkedNullable()) {
                    map.put(kParameter, null);
                } else if (!kParameter.isOptional()) {
                    INSTANCE.e("not nullable: " + kParameter.getName());
                    throw new KotlinNothingValueException();
                }
            } else {
                KClassifier classifier = kProperty1.getReturnType().getClassifier();
                if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    JsonElement jsonElement2 = (JsonElement) diff.get((Object) kProperty1.getName());
                    if (jsonElement2 == null || (jsonPrimitivePrimitiveOrNull4 = INSTANCE.primitiveOrNull(jsonElement2)) == null || (objApplyDiff = JsonElementKt.getIntOrNull(jsonPrimitivePrimitiveOrNull4)) == null) {
                        INSTANCE.e(kProperty1.getName() + " no int");
                        throw new KotlinNothingValueException();
                    }
                } else if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    JsonElement jsonElement3 = (JsonElement) diff.get((Object) kProperty1.getName());
                    if (jsonElement3 == null || (jsonPrimitivePrimitiveOrNull3 = INSTANCE.primitiveOrNull(jsonElement3)) == null || (objApplyDiff = JsonElementKt.getLongOrNull(jsonPrimitivePrimitiveOrNull3)) == null) {
                        INSTANCE.e(kProperty1.getName() + " no long");
                        throw new KotlinNothingValueException();
                    }
                } else if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(String.class))) {
                    JsonElement jsonElement4 = (JsonElement) diff.get((Object) kProperty1.getName());
                    if (jsonElement4 == null || (jsonPrimitivePrimitiveOrNull2 = INSTANCE.primitiveOrNull(jsonElement4)) == null || (objApplyDiff = JsonElementKt.getContentOrNull(jsonPrimitivePrimitiveOrNull2)) == null) {
                        INSTANCE.e(kProperty1.getName() + " no string");
                        throw new KotlinNothingValueException();
                    }
                } else if (!Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(List.class))) {
                    if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Map.class))) {
                        objApplyDiff = INSTANCE.diffMap(kProperty1.getReturnType(), kProperty1.getGetter().call(obj), kProperty1.getName(), diff);
                    } else {
                        Object objCall = kProperty1.getGetter().call(obj);
                        Object obj2 = diff.get((Object) kProperty1.getName());
                        JsonObject jsonObject = obj2 instanceof JsonObject ? (JsonObject) obj2 : null;
                        if (jsonObject == null) {
                            INSTANCE.e(kProperty1.getName() + " no dict");
                            throw new KotlinNothingValueException();
                        }
                        if (objCall == null) {
                            KClassifier classifier2 = kProperty1.getReturnType().getClassifier();
                            Intrinsics.checkNotNull(classifier2, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
                            KFunction primaryConstructor2 = KClasses.getPrimaryConstructor((KClass) classifier2);
                            Intrinsics.checkNotNull(primaryConstructor2);
                            objApplyDiff = INSTANCE.constructFromJson$index_release(primaryConstructor2, jsonObject);
                        } else {
                            objApplyDiff = INSTANCE.applyDiff(objCall, jsonObject);
                        }
                    }
                } else {
                    JsonElement jsonElement5 = (JsonElement) diff.get((Object) kProperty1.getName());
                    if (jsonElement5 == null || (jsonArrayJsonArrayOrNull = INSTANCE.jsonArrayOrNull(jsonElement5)) == null) {
                        INSTANCE.e(kProperty1.getName() + " no array");
                        throw new KotlinNothingValueException();
                    }
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(jsonArrayJsonArrayOrNull, 10));
                    for (JsonElement jsonElement6 : jsonArrayJsonArrayOrNull) {
                        if (Intrinsics.areEqual(kProperty1.getName(), "features")) {
                            ReflectionDiffer reflectionDiffer = INSTANCE;
                            JsonObject jsonObjectJsonObjectOrNull = reflectionDiffer.jsonObjectOrNull(jsonElement6);
                            if (jsonObjectJsonObjectOrNull == null || (jsonElement = (JsonElement) jsonObjectJsonObjectOrNull.get((Object) "name")) == null || (jsonPrimitivePrimitiveOrNull = reflectionDiffer.primitiveOrNull(jsonElement)) == null || (contentOrNull = JsonElementKt.getContentOrNull(jsonPrimitivePrimitiveOrNull)) == null) {
                                reflectionDiffer.e("features without primitive name: " + jsonElement6);
                                throw new KotlinNothingValueException();
                            }
                        } else {
                            ReflectionDiffer reflectionDiffer2 = INSTANCE;
                            JsonPrimitive jsonPrimitivePrimitiveOrNull5 = reflectionDiffer2.primitiveOrNull(jsonElement6);
                            if (jsonPrimitivePrimitiveOrNull5 == null || (contentOrNull = JsonElementKt.getContentOrNull(jsonPrimitivePrimitiveOrNull5)) == null) {
                                reflectionDiffer2.e(kProperty1.getName() + " non-primitive array");
                                throw new KotlinNothingValueException();
                            }
                        }
                        arrayList.add(contentOrNull);
                    }
                    objApplyDiff = arrayList;
                }
                map.put(kParameter, objApplyDiff);
            }
        }
        return (T) primaryConstructor.callBy(map);
    }

    public final <T> T constructFromJson$index_release(KFunction factory, JsonObject diff) throws SerializationException {
        JsonObject jsonObjectJsonObjectOrNull;
        Object objConstructFromJson$index_release;
        JsonArray<JsonElement> jsonArrayJsonArrayOrNull;
        String contentOrNull;
        JsonPrimitive jsonPrimitivePrimitiveOrNull;
        JsonPrimitive jsonPrimitivePrimitiveOrNull2;
        JsonPrimitive jsonPrimitivePrimitiveOrNull3;
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(diff, "diff");
        HashMap map = new HashMap();
        for (KParameter kParameter : factory.getParameters()) {
            if (!diff.containsKey((Object) kParameter.getName())) {
                if (!kParameter.isOptional()) {
                    INSTANCE.e(kParameter.getName() + " required but not found");
                    throw new KotlinNothingValueException();
                }
            } else if (diff.get((Object) kParameter.getName()) instanceof JsonNull) {
                if (kParameter.getType().isMarkedNullable()) {
                    map.put(kParameter, null);
                } else if (!kParameter.isOptional()) {
                    INSTANCE.e("not nullable: " + kParameter.getName());
                    throw new KotlinNothingValueException();
                }
            } else {
                KClassifier classifier = kParameter.getType().getClassifier();
                if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    JsonElement jsonElement = (JsonElement) diff.get((Object) kParameter.getName());
                    if (jsonElement == null || (jsonPrimitivePrimitiveOrNull3 = INSTANCE.primitiveOrNull(jsonElement)) == null || (objConstructFromJson$index_release = JsonElementKt.getIntOrNull(jsonPrimitivePrimitiveOrNull3)) == null) {
                        INSTANCE.e(kParameter.getName() + " no int");
                        throw new KotlinNothingValueException();
                    }
                } else if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    JsonElement jsonElement2 = (JsonElement) diff.get((Object) kParameter.getName());
                    if (jsonElement2 == null || (jsonPrimitivePrimitiveOrNull2 = INSTANCE.primitiveOrNull(jsonElement2)) == null || (objConstructFromJson$index_release = JsonElementKt.getLongOrNull(jsonPrimitivePrimitiveOrNull2)) == null) {
                        INSTANCE.e(kParameter.getName() + " no long");
                        throw new KotlinNothingValueException();
                    }
                } else if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(String.class))) {
                    JsonElement jsonElement3 = (JsonElement) diff.get((Object) kParameter.getName());
                    if (jsonElement3 == null || (jsonPrimitivePrimitiveOrNull = INSTANCE.primitiveOrNull(jsonElement3)) == null || (objConstructFromJson$index_release = JsonElementKt.getContentOrNull(jsonPrimitivePrimitiveOrNull)) == null) {
                        INSTANCE.e(kParameter.getName() + " no string");
                        throw new KotlinNothingValueException();
                    }
                } else if (!Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(List.class))) {
                    if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(Map.class))) {
                        objConstructFromJson$index_release = INSTANCE.diffMap(kParameter.getType(), null, kParameter.getName(), diff);
                    } else {
                        ReflectionDiffer reflectionDiffer = INSTANCE;
                        KClassifier classifier2 = kParameter.getType().getClassifier();
                        Intrinsics.checkNotNull(classifier2, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
                        KFunction primaryConstructor = KClasses.getPrimaryConstructor((KClass) classifier2);
                        Intrinsics.checkNotNull(primaryConstructor);
                        JsonElement jsonElement4 = (JsonElement) diff.get((Object) kParameter.getName());
                        if (jsonElement4 == null || (jsonObjectJsonObjectOrNull = reflectionDiffer.jsonObjectOrNull(jsonElement4)) == null) {
                            reflectionDiffer.e(kParameter.getName() + " no dict");
                            throw new KotlinNothingValueException();
                        }
                        objConstructFromJson$index_release = reflectionDiffer.constructFromJson$index_release(primaryConstructor, jsonObjectJsonObjectOrNull);
                    }
                } else {
                    JsonElement jsonElement5 = (JsonElement) diff.get((Object) kParameter.getName());
                    if (jsonElement5 == null || (jsonArrayJsonArrayOrNull = INSTANCE.jsonArrayOrNull(jsonElement5)) == null) {
                        INSTANCE.e(kParameter.getName() + " no array");
                        throw new KotlinNothingValueException();
                    }
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(jsonArrayJsonArrayOrNull, 10));
                    for (JsonElement jsonElement6 : jsonArrayJsonArrayOrNull) {
                        ReflectionDiffer reflectionDiffer2 = INSTANCE;
                        JsonPrimitive jsonPrimitivePrimitiveOrNull4 = reflectionDiffer2.primitiveOrNull(jsonElement6);
                        if (jsonPrimitivePrimitiveOrNull4 == null || (contentOrNull = JsonElementKt.getContentOrNull(jsonPrimitivePrimitiveOrNull4)) == null) {
                            reflectionDiffer2.e(kParameter.getName() + " non-primitive array");
                            throw new KotlinNothingValueException();
                        }
                        arrayList.add(contentOrNull);
                    }
                    objConstructFromJson$index_release = arrayList;
                }
                map.put(kParameter, objConstructFromJson$index_release);
            }
        }
        return (T) factory.callBy(map);
    }

    private final <T> Map<String, Object> diffMap(KType type, T obj, String key, JsonObject diff) {
        Map<String, ? extends Map<String, String>> map;
        JsonObject jsonObjectJsonObjectOrNull;
        JsonObject jsonObjectJsonObjectOrNull2;
        JsonObject jsonObjectJsonObjectOrNull3;
        JsonObject jsonObjectJsonObjectOrNull4;
        JsonObject jsonObjectJsonObjectOrNull5;
        JsonObject jsonObjectJsonObjectOrNull6;
        KTypeProjection.Companion companion = KTypeProjection.Companion;
        if (Intrinsics.areEqual(type, Reflection.typeOf(Map.class, companion.invariant(Reflection.typeOf(String.class)), companion.invariant(Reflection.typeOf(String.class))))) {
            map = obj instanceof Map ? (Map) obj : null;
            if (map == null) {
                map = new HashMap<>();
            }
            JsonElement jsonElement = (JsonElement) diff.get((Object) key);
            if (jsonElement != null && (jsonObjectJsonObjectOrNull6 = jsonObjectOrNull(jsonElement)) != null) {
                return applyTextDiff(map, jsonObjectJsonObjectOrNull6);
            }
            e(key + " no map");
            throw new KotlinNothingValueException();
        }
        if (Intrinsics.areEqual(type, Reflection.nullableTypeOf(Map.class, companion.invariant(Reflection.typeOf(String.class)), companion.invariant(Reflection.typeOf(String.class))))) {
            map = obj instanceof Map ? (Map) obj : null;
            if (map == null) {
                map = new HashMap<>();
            }
            JsonElement jsonElement2 = (JsonElement) diff.get((Object) key);
            if (jsonElement2 != null && (jsonObjectJsonObjectOrNull5 = jsonObjectOrNull(jsonElement2)) != null) {
                return applyTextDiff(map, jsonObjectJsonObjectOrNull5);
            }
            e(key + " no map");
            throw new KotlinNothingValueException();
        }
        if (Intrinsics.areEqual(type, Reflection.typeOf(Map.class, companion.invariant(Reflection.typeOf(String.class)), companion.invariant(Reflection.typeOf(FileV2.class))))) {
            map = obj instanceof Map ? (Map) obj : null;
            if (map == null) {
                map = new HashMap<>();
            }
            JsonElement jsonElement3 = (JsonElement) diff.get((Object) key);
            if (jsonElement3 != null && (jsonObjectJsonObjectOrNull4 = jsonObjectOrNull(jsonElement3)) != null) {
                return applyFileDiff(map, jsonObjectJsonObjectOrNull4);
            }
            e(key + " no map");
            throw new KotlinNothingValueException();
        }
        if (Intrinsics.areEqual(type, Reflection.nullableTypeOf(Map.class, companion.invariant(Reflection.typeOf(String.class)), companion.invariant(Reflection.typeOf(FileV2.class))))) {
            map = obj instanceof Map ? (Map) obj : null;
            if (map == null) {
                map = new HashMap<>();
            }
            JsonElement jsonElement4 = (JsonElement) diff.get((Object) key);
            if (jsonElement4 != null && (jsonObjectJsonObjectOrNull3 = jsonObjectOrNull(jsonElement4)) != null) {
                return applyFileDiff(map, jsonObjectJsonObjectOrNull3);
            }
            e(key + " no map");
            throw new KotlinNothingValueException();
        }
        if (Intrinsics.areEqual(type, Reflection.typeOf(Map.class, companion.invariant(Reflection.typeOf(String.class)), companion.invariant(Reflection.typeOf(Map.class, companion.invariant(Reflection.typeOf(String.class)), companion.invariant(Reflection.typeOf(String.class))))))) {
            map = obj instanceof Map ? (Map) obj : null;
            if (map == null) {
                map = new HashMap<>();
            }
            JsonElement jsonElement5 = (JsonElement) diff.get((Object) key);
            if (jsonElement5 != null && (jsonObjectJsonObjectOrNull2 = jsonObjectOrNull(jsonElement5)) != null) {
                return applyMapTextDiff(map, jsonObjectJsonObjectOrNull2);
            }
            e(key + " no map");
            throw new KotlinNothingValueException();
        }
        if (Intrinsics.areEqual(type, Reflection.nullableTypeOf(Map.class, companion.invariant(Reflection.typeOf(String.class)), companion.invariant(Reflection.typeOf(Map.class, companion.invariant(Reflection.typeOf(String.class)), companion.invariant(Reflection.typeOf(String.class))))))) {
            map = obj instanceof Map ? (Map) obj : null;
            if (map == null) {
                map = new HashMap<>();
            }
            JsonElement jsonElement6 = (JsonElement) diff.get((Object) key);
            if (jsonElement6 != null && (jsonObjectJsonObjectOrNull = jsonObjectOrNull(jsonElement6)) != null) {
                return applyMapTextDiff(map, jsonObjectJsonObjectOrNull);
            }
            e(key + " no map");
            throw new KotlinNothingValueException();
        }
        e("Unknown map: " + key + ": " + type + " = " + diff.get((Object) key));
        throw new KotlinNothingValueException();
    }

    private final Map<String, String> applyTextDiff(Map<String, String> obj, JsonObject diff) throws SerializationException {
        String contentOrNull;
        Map<String, String> mutableMap = MapsKt.toMutableMap(obj);
        for (Map.Entry entry : diff.entrySet()) {
            String str = (String) entry.getKey();
            JsonElement jsonElement = (JsonElement) entry.getValue();
            if (jsonElement instanceof JsonNull) {
                mutableMap.remove(str);
            } else {
                JsonPrimitive jsonPrimitivePrimitiveOrNull = INSTANCE.primitiveOrNull(jsonElement);
                if (jsonPrimitivePrimitiveOrNull == null || (contentOrNull = JsonElementKt.getContentOrNull(jsonPrimitivePrimitiveOrNull)) == null) {
                    throw new SerializationException("no string: " + jsonElement);
                }
                mutableMap.put(str, contentOrNull);
            }
        }
        return mutableMap;
    }

    private final Map<String, FileV2> applyFileDiff(Map<String, FileV2> obj, JsonObject diff) throws SerializationException {
        Object objConstructFromJson$index_release;
        Map<String, FileV2> mutableMap = MapsKt.toMutableMap(obj);
        for (Map.Entry entry : diff.entrySet()) {
            String str = (String) entry.getKey();
            JsonElement jsonElement = (JsonElement) entry.getValue();
            if (jsonElement instanceof JsonNull) {
                mutableMap.remove(str);
            } else {
                ReflectionDiffer reflectionDiffer = INSTANCE;
                JsonObject jsonObjectJsonObjectOrNull = reflectionDiffer.jsonObjectOrNull(jsonElement);
                if (jsonObjectJsonObjectOrNull == null) {
                    throw new SerializationException("no FileV2: " + jsonElement);
                }
                if (obj.containsKey(str)) {
                    FileV2 fileV2 = obj.get(str);
                    Intrinsics.checkNotNull(fileV2, "null cannot be cast to non-null type org.fdroid.index.v2.FileV2");
                    objConstructFromJson$index_release = reflectionDiffer.applyDiff(fileV2, jsonObjectJsonObjectOrNull);
                } else {
                    KFunction primaryConstructor = KClasses.getPrimaryConstructor(Reflection.getOrCreateKotlinClass(FileV2.class));
                    Intrinsics.checkNotNull(primaryConstructor);
                    objConstructFromJson$index_release = reflectionDiffer.constructFromJson$index_release(primaryConstructor, jsonObjectJsonObjectOrNull);
                }
                mutableMap.put(str, (FileV2) objConstructFromJson$index_release);
            }
        }
        return mutableMap;
    }

    private final Map<String, Map<String, String>> applyMapTextDiff(Map<String, ? extends Map<String, String>> obj, JsonObject diff) throws SerializationException {
        Map<String, String> mapApplyTextDiff;
        Map<String, Map<String, String>> mutableMap = MapsKt.toMutableMap(obj);
        for (Map.Entry entry : diff.entrySet()) {
            String str = (String) entry.getKey();
            JsonElement jsonElement = (JsonElement) entry.getValue();
            if (jsonElement instanceof JsonNull) {
                mutableMap.remove(str);
            } else {
                ReflectionDiffer reflectionDiffer = INSTANCE;
                JsonObject jsonObjectJsonObjectOrNull = reflectionDiffer.jsonObjectOrNull(jsonElement);
                if (jsonObjectJsonObjectOrNull == null) {
                    throw new SerializationException("no FileV2: " + jsonElement);
                }
                if (obj.containsKey(str)) {
                    Map<String, String> map = obj.get(str);
                    Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.String>");
                    mapApplyTextDiff = reflectionDiffer.applyTextDiff(map, jsonObjectJsonObjectOrNull);
                } else {
                    mapApplyTextDiff = reflectionDiffer.applyTextDiff(new HashMap(), jsonObjectJsonObjectOrNull);
                }
                mutableMap.put(str, mapApplyTextDiff);
            }
        }
        return mutableMap;
    }

    private final JsonPrimitive primitiveOrNull(JsonElement jsonElement) {
        try {
            return JsonElementKt.getJsonPrimitive(jsonElement);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private final JsonArray jsonArrayOrNull(JsonElement jsonElement) {
        try {
            return JsonElementKt.getJsonArray(jsonElement);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private final JsonObject jsonObjectOrNull(JsonElement jsonElement) {
        try {
            return JsonElementKt.getJsonObject(jsonElement);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private final Void e(String msg) throws SerializationException {
        throw new SerializationException(msg);
    }

    public final /* synthetic */ <T> T decodeOr(Json json, String key, JsonObject json2, Function0 function0) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(json2, "json");
        Intrinsics.checkNotNullParameter(function0, "default");
        if (json2.containsKey((Object) key)) {
            SerializersModule serializersModule = json.getSerializersModule();
            Intrinsics.reifiedOperationMarker(6, "T");
            MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
            return (T) json.decodeFromJsonElement(SerializersKt.serializer(serializersModule, null), json2);
        }
        return (T) function0.invoke();
    }
}
