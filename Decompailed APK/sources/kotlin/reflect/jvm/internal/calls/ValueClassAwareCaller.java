package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.KDeclarationContainerImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ValueClassAwareCaller.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ValueClassAwareCaller implements Caller {
    private final Caller caller;
    private final BoxUnboxData data;
    private final boolean hasMfvcParameters;
    private final boolean isDefault;
    private final Member member;
    private final IntRange[] slices;

    /* JADX WARN: Removed duplicated region for block: B:27:0x007b A[LOOP:1: B:25:0x0075->B:27:0x007b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0101  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ValueClassAwareCaller(kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r11, kotlin.reflect.jvm.internal.calls.Caller r12, boolean r13) {
        /*
            Method dump skipped, instruction units count: 585
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.calls.ValueClassAwareCaller.<init>(kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.calls.Caller, boolean):void");
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    /* JADX INFO: renamed from: getMember */
    public Member mo2745getMember() {
        return this.member;
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public Type getReturnType() {
        return this.caller.getReturnType();
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public List getParameterTypes() {
        return this.caller.getParameterTypes();
    }

    /* JADX INFO: compiled from: ValueClassAwareCaller.kt */
    private static final class BoxUnboxData {
        private final IntRange argumentRange;
        private final Method box;
        private final List[] unboxParameters;

        public BoxUnboxData(IntRange argumentRange, List[] unboxParameters, Method method) {
            Intrinsics.checkNotNullParameter(argumentRange, "argumentRange");
            Intrinsics.checkNotNullParameter(unboxParameters, "unboxParameters");
            this.argumentRange = argumentRange;
            this.unboxParameters = unboxParameters;
            this.box = method;
        }

        public final IntRange getArgumentRange() {
            return this.argumentRange;
        }

        public final Method getBox() {
            return this.box;
        }

        public final List[] getUnboxParameters() {
            return this.unboxParameters;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean data$lambda$5$lambda$3(ClassDescriptor makeKotlinParameterTypes) {
        Intrinsics.checkNotNullParameter(makeKotlinParameterTypes, "$this$makeKotlinParameterTypes");
        return InlineClassesUtilsKt.isValueClass(makeKotlinParameterTypes);
    }

    private static final int data$lambda$5$typeSize(KotlinType kotlinType) {
        List mfvcUnboxMethods = ValueClassAwareCallerKt.getMfvcUnboxMethods(TypeSubstitutionKt.asSimpleType(kotlinType));
        if (mfvcUnboxMethods != null) {
            return mfvcUnboxMethods.size();
        }
        return 1;
    }

    public final IntRange getRealSlicesOfParameters(int i) {
        IntRange intRange;
        if (i >= 0) {
            IntRange[] intRangeArr = this.slices;
            if (i < intRangeArr.length) {
                return intRangeArr[i];
            }
        }
        IntRange[] intRangeArr2 = this.slices;
        if (intRangeArr2.length == 0) {
            intRange = new IntRange(i, i);
        } else {
            int length = (i - intRangeArr2.length) + ((IntRange) ArraysKt.last(intRangeArr2)).getLast() + 1;
            intRange = new IntRange(length, length);
        }
        return intRange;
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public Object call(Object[] args) throws IllegalAccessException, InvocationTargetException {
        Object objInvoke;
        Object objDefaultPrimitiveValue;
        Object objDefaultPrimitiveValue2;
        Intrinsics.checkNotNullParameter(args, "args");
        IntRange argumentRange = this.data.getArgumentRange();
        List[] unboxParameters = this.data.getUnboxParameters();
        Method box = this.data.getBox();
        if (!argumentRange.isEmpty()) {
            if (this.hasMfvcParameters) {
                List listCreateListBuilder = CollectionsKt.createListBuilder(args.length);
                int first = argumentRange.getFirst();
                for (int i = 0; i < first; i++) {
                    listCreateListBuilder.add(args[i]);
                }
                int first2 = argumentRange.getFirst();
                int last = argumentRange.getLast();
                if (first2 <= last) {
                    while (true) {
                        List<Method> list = unboxParameters[first2];
                        Object obj = args[first2];
                        if (list == null) {
                            listCreateListBuilder.add(obj);
                        } else {
                            for (Method method : list) {
                                if (obj != null) {
                                    objDefaultPrimitiveValue2 = method.invoke(obj, null);
                                } else {
                                    Class<?> returnType = method.getReturnType();
                                    Intrinsics.checkNotNullExpressionValue(returnType, "getReturnType(...)");
                                    objDefaultPrimitiveValue2 = UtilKt.defaultPrimitiveValue(returnType);
                                }
                                listCreateListBuilder.add(objDefaultPrimitiveValue2);
                            }
                        }
                        if (first2 == last) {
                            break;
                        }
                        first2++;
                    }
                }
                int last2 = argumentRange.getLast() + 1;
                int lastIndex = ArraysKt.getLastIndex(args);
                if (last2 <= lastIndex) {
                    while (true) {
                        listCreateListBuilder.add(args[last2]);
                        if (last2 == lastIndex) {
                            break;
                        }
                        last2++;
                    }
                }
                args = CollectionsKt.build(listCreateListBuilder).toArray(new Object[0]);
            } else {
                int length = args.length;
                Object[] objArr = new Object[length];
                for (int i2 = 0; i2 < length; i2++) {
                    int first3 = argumentRange.getFirst();
                    if (i2 <= argumentRange.getLast() && first3 <= i2) {
                        List list2 = unboxParameters[i2];
                        Method method2 = list2 != null ? (Method) CollectionsKt.single(list2) : null;
                        objDefaultPrimitiveValue = args[i2];
                        if (method2 != null) {
                            if (objDefaultPrimitiveValue != null) {
                                objDefaultPrimitiveValue = method2.invoke(objDefaultPrimitiveValue, null);
                            } else {
                                Class<?> returnType2 = method2.getReturnType();
                                Intrinsics.checkNotNullExpressionValue(returnType2, "getReturnType(...)");
                                objDefaultPrimitiveValue = UtilKt.defaultPrimitiveValue(returnType2);
                            }
                        }
                    } else {
                        objDefaultPrimitiveValue = args[i2];
                    }
                    objArr[i2] = objDefaultPrimitiveValue;
                }
                args = objArr;
            }
        }
        Object objCall = this.caller.call(args);
        return (objCall == IntrinsicsKt.getCOROUTINE_SUSPENDED() || box == null || (objInvoke = box.invoke(null, objCall)) == null) ? objCall : objInvoke;
    }

    /* JADX INFO: compiled from: ValueClassAwareCaller.kt */
    public static final class MultiFieldValueClassPrimaryConstructorCaller implements Caller {
        private final Method boxMethod;
        private final Method constructorImpl;
        private final List originalParametersGroups;
        private final List parameterTypes;
        private final List parameterUnboxMethods;

        public Void getMember() {
            return null;
        }

        public MultiFieldValueClassPrimaryConstructorCaller(FunctionDescriptor descriptor, KDeclarationContainerImpl container, String constructorDesc, List originalParameters) {
            Collection collectionListOf;
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(container, "container");
            Intrinsics.checkNotNullParameter(constructorDesc, "constructorDesc");
            Intrinsics.checkNotNullParameter(originalParameters, "originalParameters");
            Method methodFindMethodBySignature = container.findMethodBySignature("constructor-impl", constructorDesc);
            Intrinsics.checkNotNull(methodFindMethodBySignature);
            this.constructorImpl = methodFindMethodBySignature;
            Method methodFindMethodBySignature2 = container.findMethodBySignature("box-impl", StringsKt.removeSuffix(constructorDesc, "V") + ReflectClassUtilKt.getDesc(container.getJClass()));
            Intrinsics.checkNotNull(methodFindMethodBySignature2);
            this.boxMethod = methodFindMethodBySignature2;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(originalParameters, 10));
            Iterator it = originalParameters.iterator();
            while (it.hasNext()) {
                KotlinType type = ((ParameterDescriptor) it.next()).getType();
                Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                arrayList.add(ValueClassAwareCallerKt.getValueClassUnboxMethods(TypeSubstitutionKt.asSimpleType(type), descriptor));
            }
            this.parameterUnboxMethods = arrayList;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(originalParameters, 10));
            int i = 0;
            for (Object obj : originalParameters) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = ((ParameterDescriptor) obj).getType().getConstructor().mo2781getDeclarationDescriptor();
                Intrinsics.checkNotNull(classifierDescriptorMo2781getDeclarationDescriptor, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptorMo2781getDeclarationDescriptor;
                List list = (List) this.parameterUnboxMethods.get(i);
                if (list != null) {
                    collectionListOf = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator it2 = list.iterator();
                    while (it2.hasNext()) {
                        collectionListOf.add(((Method) it2.next()).getReturnType());
                    }
                } else {
                    Class javaClass = UtilKt.toJavaClass(classDescriptor);
                    Intrinsics.checkNotNull(javaClass);
                    collectionListOf = CollectionsKt.listOf(javaClass);
                }
                arrayList2.add(collectionListOf);
                i = i2;
            }
            this.originalParametersGroups = arrayList2;
            this.parameterTypes = CollectionsKt.flatten(arrayList2);
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        /* JADX INFO: renamed from: getMember */
        public /* bridge */ /* synthetic */ Member mo2745getMember() {
            return (Member) getMember();
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        public Type getReturnType() {
            Class<?> returnType = this.boxMethod.getReturnType();
            Intrinsics.checkNotNullExpressionValue(returnType, "getReturnType(...)");
            return returnType;
        }

        public final List getOriginalParametersGroups() {
            return this.originalParametersGroups;
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        public List getParameterTypes() {
            return this.parameterTypes;
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        public Object call(Object[] args) throws IllegalAccessException, InvocationTargetException {
            Collection collectionListOf;
            Intrinsics.checkNotNullParameter(args, "args");
            List<Pair> listZip = ArraysKt.zip(args, this.parameterUnboxMethods);
            ArrayList arrayList = new ArrayList();
            for (Pair pair : listZip) {
                Object objComponent1 = pair.component1();
                List list = (List) pair.component2();
                if (list == null) {
                    collectionListOf = CollectionsKt.listOf(objComponent1);
                } else {
                    collectionListOf = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        collectionListOf.add(((Method) it.next()).invoke(objComponent1, null));
                    }
                }
                CollectionsKt.addAll(arrayList, collectionListOf);
            }
            Object[] array = arrayList.toArray(new Object[0]);
            this.constructorImpl.invoke(null, Arrays.copyOf(array, array.length));
            return this.boxMethod.invoke(null, Arrays.copyOf(array, array.length));
        }
    }
}
