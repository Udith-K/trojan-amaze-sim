package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.jvm.internal.calls.Caller;

/* JADX INFO: compiled from: CallerImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class CallerImpl implements Caller {
    public static final Companion Companion = new Companion(null);
    private final Class instanceClass;
    private final Member member;
    private final List parameterTypes;
    private final Type returnType;

    public /* synthetic */ CallerImpl(Member member, Type type, Class cls, Type[] typeArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(member, type, cls, typeArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private CallerImpl(java.lang.reflect.Member r1, java.lang.reflect.Type r2, java.lang.Class r3, java.lang.reflect.Type[] r4) {
        /*
            r0 = this;
            r0.<init>()
            r0.member = r1
            r0.returnType = r2
            r0.instanceClass = r3
            if (r3 == 0) goto L27
            kotlin.jvm.internal.SpreadBuilder r1 = new kotlin.jvm.internal.SpreadBuilder
            r2 = 2
            r1.<init>(r2)
            r1.add(r3)
            r1.addSpread(r4)
            int r2 = r1.size()
            java.lang.reflect.Type[] r2 = new java.lang.reflect.Type[r2]
            java.lang.Object[] r1 = r1.toArray(r2)
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            if (r1 != 0) goto L2b
        L27:
            java.util.List r1 = kotlin.collections.ArraysKt.toList(r4)
        L2b:
            r0.parameterTypes = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.calls.CallerImpl.<init>(java.lang.reflect.Member, java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type[]):void");
    }

    public void checkArguments(Object[] objArr) {
        Caller.DefaultImpls.checkArguments(this, objArr);
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    /* JADX INFO: renamed from: getMember */
    public final Member mo2745getMember() {
        return this.member;
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public final Type getReturnType() {
        return this.returnType;
    }

    public final Class getInstanceClass() {
        return this.instanceClass;
    }

    @Override // kotlin.reflect.jvm.internal.calls.Caller
    public List getParameterTypes() {
        return this.parameterTypes;
    }

    protected final void checkObjectInstance(Object obj) {
        if (obj == null || !this.member.getDeclaringClass().isInstance(obj)) {
            throw new IllegalArgumentException("An object member requires the object instance passed as the first argument.");
        }
    }

    /* JADX INFO: compiled from: CallerImpl.kt */
    public static final class Constructor extends CallerImpl {
        /* JADX WARN: Illegal instructions before constructor call */
        public Constructor(java.lang.reflect.Constructor constructor) {
            Intrinsics.checkNotNullParameter(constructor, "constructor");
            Class declaringClass = constructor.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            Class declaringClass2 = constructor.getDeclaringClass();
            Class<?> declaringClass3 = declaringClass2.getDeclaringClass();
            Class<?> cls = (declaringClass3 == null || Modifier.isStatic(declaringClass2.getModifiers())) ? null : declaringClass3;
            Type[] genericParameterTypes = constructor.getGenericParameterTypes();
            Intrinsics.checkNotNullExpressionValue(genericParameterTypes, "getGenericParameterTypes(...)");
            super(constructor, declaringClass, cls, genericParameterTypes, null);
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        public Object call(Object[] args) {
            Intrinsics.checkNotNullParameter(args, "args");
            checkArguments(args);
            return ((java.lang.reflect.Constructor) mo2745getMember()).newInstance(Arrays.copyOf(args, args.length));
        }
    }

    /* JADX INFO: compiled from: CallerImpl.kt */
    public static final class BoundConstructor extends CallerImpl implements BoundCaller {
        private final Object boundReceiver;

        /* JADX WARN: Illegal instructions before constructor call */
        public BoundConstructor(java.lang.reflect.Constructor constructor, Object obj) {
            Intrinsics.checkNotNullParameter(constructor, "constructor");
            Class declaringClass = constructor.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            Type[] genericParameterTypes = constructor.getGenericParameterTypes();
            Intrinsics.checkNotNullExpressionValue(genericParameterTypes, "getGenericParameterTypes(...)");
            super(constructor, declaringClass, null, genericParameterTypes, null);
            this.boundReceiver = obj;
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        public Object call(Object[] args) {
            Intrinsics.checkNotNullParameter(args, "args");
            checkArguments(args);
            java.lang.reflect.Constructor constructor = (java.lang.reflect.Constructor) mo2745getMember();
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(this.boundReceiver);
            spreadBuilder.addSpread(args);
            return constructor.newInstance(spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        }
    }

    /* JADX INFO: compiled from: CallerImpl.kt */
    public static final class AccessorForHiddenConstructor extends CallerImpl {
        /* JADX WARN: Illegal instructions before constructor call */
        public AccessorForHiddenConstructor(java.lang.reflect.Constructor constructor) {
            Intrinsics.checkNotNullParameter(constructor, "constructor");
            Class declaringClass = constructor.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            Type[] genericParameterTypes = constructor.getGenericParameterTypes();
            Intrinsics.checkNotNullExpressionValue(genericParameterTypes, "getGenericParameterTypes(...)");
            super(constructor, declaringClass, null, (Type[]) (genericParameterTypes.length <= 1 ? new Type[0] : ArraysKt.copyOfRange(genericParameterTypes, 0, genericParameterTypes.length - 1)), null);
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        public Object call(Object[] args) {
            Intrinsics.checkNotNullParameter(args, "args");
            checkArguments(args);
            java.lang.reflect.Constructor constructor = (java.lang.reflect.Constructor) mo2745getMember();
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.addSpread(args);
            spreadBuilder.add(null);
            return constructor.newInstance(spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        }
    }

    /* JADX INFO: compiled from: CallerImpl.kt */
    public static final class AccessorForHiddenBoundConstructor extends CallerImpl implements BoundCaller {
        private final Object boundReceiver;

        /* JADX WARN: Illegal instructions before constructor call */
        public AccessorForHiddenBoundConstructor(java.lang.reflect.Constructor constructor, Object obj) {
            Intrinsics.checkNotNullParameter(constructor, "constructor");
            Class declaringClass = constructor.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            Type[] genericParameterTypes = constructor.getGenericParameterTypes();
            Intrinsics.checkNotNullExpressionValue(genericParameterTypes, "getGenericParameterTypes(...)");
            super(constructor, declaringClass, null, (Type[]) (genericParameterTypes.length <= 2 ? new Type[0] : ArraysKt.copyOfRange(genericParameterTypes, 1, genericParameterTypes.length - 1)), null);
            this.boundReceiver = obj;
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        public Object call(Object[] args) {
            Intrinsics.checkNotNullParameter(args, "args");
            checkArguments(args);
            java.lang.reflect.Constructor constructor = (java.lang.reflect.Constructor) mo2745getMember();
            SpreadBuilder spreadBuilder = new SpreadBuilder(3);
            spreadBuilder.add(this.boundReceiver);
            spreadBuilder.addSpread(args);
            spreadBuilder.add(null);
            return constructor.newInstance(spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        }
    }

    /* JADX INFO: compiled from: CallerImpl.kt */
    public static abstract class Method extends CallerImpl {
        private final boolean isVoidMethod;

        public /* synthetic */ Method(java.lang.reflect.Method method, boolean z, Type[] typeArr, DefaultConstructorMarker defaultConstructorMarker) {
            this(method, z, typeArr);
        }

        public /* synthetic */ Method(java.lang.reflect.Method method, boolean z, Type[] typeArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(method, (i & 2) != 0 ? !Modifier.isStatic(method.getModifiers()) : z, (i & 4) != 0 ? method.getGenericParameterTypes() : typeArr, null);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        private Method(java.lang.reflect.Method method, boolean z, Type[] typeArr) {
            Type genericReturnType = method.getGenericReturnType();
            Intrinsics.checkNotNullExpressionValue(genericReturnType, "getGenericReturnType(...)");
            super(method, genericReturnType, z ? method.getDeclaringClass() : null, typeArr, null);
            this.isVoidMethod = Intrinsics.areEqual(getReturnType(), Void.TYPE);
        }

        protected final Object callMethod(Object obj, Object[] args) {
            Intrinsics.checkNotNullParameter(args, "args");
            return this.isVoidMethod ? Unit.INSTANCE : ((java.lang.reflect.Method) mo2745getMember()).invoke(obj, Arrays.copyOf(args, args.length));
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class Static extends Method {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Static(java.lang.reflect.Method method) {
                super(method, false, null, 6, null);
                Intrinsics.checkNotNullParameter(method, "method");
            }

            @Override // kotlin.reflect.jvm.internal.calls.Caller
            public Object call(Object[] args) {
                Intrinsics.checkNotNullParameter(args, "args");
                checkArguments(args);
                return callMethod(null, args);
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class Instance extends Method {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Instance(java.lang.reflect.Method method) {
                super(method, false, null, 6, null);
                Intrinsics.checkNotNullParameter(method, "method");
            }

            @Override // kotlin.reflect.jvm.internal.calls.Caller
            public Object call(Object[] args) {
                Intrinsics.checkNotNullParameter(args, "args");
                checkArguments(args);
                return callMethod(args[0], args.length <= 1 ? new Object[0] : ArraysKt.copyOfRange(args, 1, args.length));
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class JvmStaticInObject extends Method {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public JvmStaticInObject(java.lang.reflect.Method method) {
                super(method, true, null, 4, null);
                Intrinsics.checkNotNullParameter(method, "method");
            }

            @Override // kotlin.reflect.jvm.internal.calls.Caller
            public Object call(Object[] args) {
                Intrinsics.checkNotNullParameter(args, "args");
                checkArguments(args);
                checkObjectInstance(ArraysKt.firstOrNull(args));
                return callMethod(null, args.length <= 1 ? new Object[0] : ArraysKt.copyOfRange(args, 1, args.length));
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class BoundStatic extends Method implements BoundCaller {
            private final Object boundReceiver;

            public final Object getBoundReceiver$kotlin_reflection() {
                return this.boundReceiver;
            }

            /* JADX WARN: Illegal instructions before constructor call */
            public BoundStatic(java.lang.reflect.Method method, Object obj) {
                Intrinsics.checkNotNullParameter(method, "method");
                Type[] genericParameterTypes = method.getGenericParameterTypes();
                Intrinsics.checkNotNullExpressionValue(genericParameterTypes, "getGenericParameterTypes(...)");
                super(method, false, (Type[]) (genericParameterTypes.length <= 1 ? new Type[0] : ArraysKt.copyOfRange(genericParameterTypes, 1, genericParameterTypes.length)), null);
                this.boundReceiver = obj;
            }

            @Override // kotlin.reflect.jvm.internal.calls.Caller
            public Object call(Object[] args) {
                Intrinsics.checkNotNullParameter(args, "args");
                checkArguments(args);
                SpreadBuilder spreadBuilder = new SpreadBuilder(2);
                spreadBuilder.add(this.boundReceiver);
                spreadBuilder.addSpread(args);
                return callMethod(null, spreadBuilder.toArray(new Object[spreadBuilder.size()]));
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class BoundStaticMultiFieldValueClass extends Method implements BoundCaller {
            private final Object[] boundReceiverComponents;

            public final Object[] getBoundReceiverComponents$kotlin_reflection() {
                return this.boundReceiverComponents;
            }

            /* JADX WARN: Illegal instructions before constructor call */
            public BoundStaticMultiFieldValueClass(java.lang.reflect.Method method, Object[] boundReceiverComponents) {
                Intrinsics.checkNotNullParameter(method, "method");
                Intrinsics.checkNotNullParameter(boundReceiverComponents, "boundReceiverComponents");
                Type[] genericParameterTypes = method.getGenericParameterTypes();
                Intrinsics.checkNotNullExpressionValue(genericParameterTypes, "getGenericParameterTypes(...)");
                super(method, false, (Type[]) ArraysKt.drop(genericParameterTypes, boundReceiverComponents.length).toArray(new Type[0]), null);
                this.boundReceiverComponents = boundReceiverComponents;
            }

            @Override // kotlin.reflect.jvm.internal.calls.Caller
            public Object call(Object[] args) {
                Intrinsics.checkNotNullParameter(args, "args");
                checkArguments(args);
                SpreadBuilder spreadBuilder = new SpreadBuilder(2);
                spreadBuilder.addSpread(this.boundReceiverComponents);
                spreadBuilder.addSpread(args);
                return callMethod(null, spreadBuilder.toArray(new Object[spreadBuilder.size()]));
            }

            public final int getReceiverComponentsCount() {
                return this.boundReceiverComponents.length;
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class BoundInstance extends Method implements BoundCaller {
            private final Object boundReceiver;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BoundInstance(java.lang.reflect.Method method, Object obj) {
                super(method, false, null, 4, null);
                Intrinsics.checkNotNullParameter(method, "method");
                this.boundReceiver = obj;
            }

            @Override // kotlin.reflect.jvm.internal.calls.Caller
            public Object call(Object[] args) {
                Intrinsics.checkNotNullParameter(args, "args");
                checkArguments(args);
                return callMethod(this.boundReceiver, args);
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class BoundJvmStaticInObject extends Method implements BoundCaller {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BoundJvmStaticInObject(java.lang.reflect.Method method) {
                super(method, false, null, 4, null);
                Intrinsics.checkNotNullParameter(method, "method");
            }

            @Override // kotlin.reflect.jvm.internal.calls.Caller
            public Object call(Object[] args) {
                Intrinsics.checkNotNullParameter(args, "args");
                checkArguments(args);
                return callMethod(null, args);
            }
        }
    }

    /* JADX INFO: compiled from: CallerImpl.kt */
    public static abstract class FieldGetter extends CallerImpl {
        public /* synthetic */ FieldGetter(Field field, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(field, z);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        private FieldGetter(Field field, boolean z) {
            Type genericType = field.getGenericType();
            Intrinsics.checkNotNullExpressionValue(genericType, "getGenericType(...)");
            super(field, genericType, z ? field.getDeclaringClass() : null, new Type[0], null);
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        public Object call(Object[] args) {
            Intrinsics.checkNotNullParameter(args, "args");
            checkArguments(args);
            return ((Field) mo2745getMember()).get(getInstanceClass() != null ? ArraysKt.first(args) : null);
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class Static extends FieldGetter {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Static(Field field) {
                super(field, false, null);
                Intrinsics.checkNotNullParameter(field, "field");
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class Instance extends FieldGetter {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Instance(Field field) {
                super(field, true, null);
                Intrinsics.checkNotNullParameter(field, "field");
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class JvmStaticInObject extends FieldGetter {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public JvmStaticInObject(Field field) {
                super(field, true, null);
                Intrinsics.checkNotNullParameter(field, "field");
            }

            @Override // kotlin.reflect.jvm.internal.calls.CallerImpl
            public void checkArguments(Object[] args) {
                Intrinsics.checkNotNullParameter(args, "args");
                super.checkArguments(args);
                checkObjectInstance(ArraysKt.firstOrNull(args));
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class BoundInstance extends FieldGetter implements BoundCaller {
            private final Object boundReceiver;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BoundInstance(Field field, Object obj) {
                super(field, false, null);
                Intrinsics.checkNotNullParameter(field, "field");
                this.boundReceiver = obj;
            }

            @Override // kotlin.reflect.jvm.internal.calls.CallerImpl.FieldGetter, kotlin.reflect.jvm.internal.calls.Caller
            public Object call(Object[] args) {
                Intrinsics.checkNotNullParameter(args, "args");
                checkArguments(args);
                return ((Field) mo2745getMember()).get(this.boundReceiver);
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class BoundJvmStaticInObject extends FieldGetter implements BoundCaller {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BoundJvmStaticInObject(Field field) {
                super(field, false, null);
                Intrinsics.checkNotNullParameter(field, "field");
            }
        }
    }

    /* JADX INFO: compiled from: CallerImpl.kt */
    public static abstract class FieldSetter extends CallerImpl {
        private final boolean notNull;

        public /* synthetic */ FieldSetter(Field field, boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
            this(field, z, z2);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        private FieldSetter(Field field, boolean z, boolean z2) {
            Class TYPE = Void.TYPE;
            Intrinsics.checkNotNullExpressionValue(TYPE, "TYPE");
            super(field, TYPE, z2 ? field.getDeclaringClass() : null, new Type[]{field.getGenericType()}, null);
            this.notNull = z;
        }

        @Override // kotlin.reflect.jvm.internal.calls.CallerImpl
        public void checkArguments(Object[] args) {
            Intrinsics.checkNotNullParameter(args, "args");
            super.checkArguments(args);
            if (this.notNull && ArraysKt.last(args) == null) {
                throw new IllegalArgumentException("null is not allowed as a value for this property.");
            }
        }

        @Override // kotlin.reflect.jvm.internal.calls.Caller
        public Object call(Object[] args) throws IllegalAccessException {
            Intrinsics.checkNotNullParameter(args, "args");
            checkArguments(args);
            ((Field) mo2745getMember()).set(getInstanceClass() != null ? ArraysKt.first(args) : null, ArraysKt.last(args));
            return Unit.INSTANCE;
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class Static extends FieldSetter {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Static(Field field, boolean z) {
                super(field, z, false, null);
                Intrinsics.checkNotNullParameter(field, "field");
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class Instance extends FieldSetter {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Instance(Field field, boolean z) {
                super(field, z, true, null);
                Intrinsics.checkNotNullParameter(field, "field");
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class JvmStaticInObject extends FieldSetter {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public JvmStaticInObject(Field field, boolean z) {
                super(field, z, true, null);
                Intrinsics.checkNotNullParameter(field, "field");
            }

            @Override // kotlin.reflect.jvm.internal.calls.CallerImpl.FieldSetter, kotlin.reflect.jvm.internal.calls.CallerImpl
            public void checkArguments(Object[] args) {
                Intrinsics.checkNotNullParameter(args, "args");
                super.checkArguments(args);
                checkObjectInstance(ArraysKt.firstOrNull(args));
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class BoundInstance extends FieldSetter implements BoundCaller {
            private final Object boundReceiver;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BoundInstance(Field field, boolean z, Object obj) {
                super(field, z, false, null);
                Intrinsics.checkNotNullParameter(field, "field");
                this.boundReceiver = obj;
            }

            @Override // kotlin.reflect.jvm.internal.calls.CallerImpl.FieldSetter, kotlin.reflect.jvm.internal.calls.Caller
            public Object call(Object[] args) throws IllegalAccessException {
                Intrinsics.checkNotNullParameter(args, "args");
                checkArguments(args);
                ((Field) mo2745getMember()).set(this.boundReceiver, ArraysKt.first(args));
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: compiled from: CallerImpl.kt */
        public static final class BoundJvmStaticInObject extends FieldSetter implements BoundCaller {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BoundJvmStaticInObject(Field field, boolean z) {
                super(field, z, false, null);
                Intrinsics.checkNotNullParameter(field, "field");
            }

            @Override // kotlin.reflect.jvm.internal.calls.CallerImpl.FieldSetter, kotlin.reflect.jvm.internal.calls.Caller
            public Object call(Object[] args) throws IllegalAccessException {
                Intrinsics.checkNotNullParameter(args, "args");
                checkArguments(args);
                ((Field) mo2745getMember()).set(null, ArraysKt.last(args));
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: compiled from: CallerImpl.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
