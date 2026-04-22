package io.ktor.utils.io;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

/* JADX INFO: compiled from: ExceptionUtilsJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ExceptionUtilsJvmKt {
    private static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);
    private static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private static final WeakHashMap exceptionCtors = new WeakHashMap();

    /* JADX WARN: Multi-variable type inference failed */
    public static final Throwable tryCopyException(Throwable exception, Throwable cause) {
        Object objM2639constructorimpl;
        Intrinsics.checkNotNullParameter(exception, "exception");
        Intrinsics.checkNotNullParameter(cause, "cause");
        if (exception instanceof CopyableThrowable) {
            try {
                Result.Companion companion = Result.Companion;
                objM2639constructorimpl = Result.m2639constructorimpl(((CopyableThrowable) exception).createCopy());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th));
            }
            return (Throwable) (Result.m2644isFailureimpl(objM2639constructorimpl) ? null : objM2639constructorimpl);
        }
        ReentrantReadWriteLock reentrantReadWriteLock = cacheLock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        lock.lock();
        try {
            Function1 function1 = (Function1) exceptionCtors.get(exception.getClass());
            if (function1 != null) {
                return (Throwable) function1.invoke(exception);
            }
            int i = 0;
            if (throwableFields != fieldsCountOrDefault(exception.getClass(), 0)) {
                ReentrantReadWriteLock.ReadLock lock2 = reentrantReadWriteLock.readLock();
                int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
                for (int i2 = 0; i2 < readHoldCount; i2++) {
                    lock2.unlock();
                }
                ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
                writeLock.lock();
                try {
                    exceptionCtors.put(exception.getClass(), new Function1() { // from class: io.ktor.utils.io.ExceptionUtilsJvmKt$tryCopyException$4$1
                        @Override // kotlin.jvm.functions.Function1
                        public final Void invoke(Throwable it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            return null;
                        }
                    });
                    Unit unit = Unit.INSTANCE;
                    return null;
                } finally {
                    while (i < readHoldCount) {
                        lock2.lock();
                        i++;
                    }
                    writeLock.unlock();
                }
            }
            Constructor<?>[] constructors = exception.getClass().getConstructors();
            Intrinsics.checkNotNullExpressionValue(constructors, "exception.javaClass.constructors");
            Function1 function1CreateConstructor = null;
            for (Constructor constructor : ArraysKt.sortedWith(constructors, new Comparator() { // from class: io.ktor.utils.io.ExceptionUtilsJvmKt$tryCopyException$$inlined$sortedByDescending$1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((Constructor) obj2).getParameterTypes().length), Integer.valueOf(((Constructor) obj).getParameterTypes().length));
                }
            })) {
                Intrinsics.checkNotNullExpressionValue(constructor, "constructor");
                function1CreateConstructor = createConstructor(constructor);
                if (function1CreateConstructor != null) {
                    break;
                }
            }
            ReentrantReadWriteLock reentrantReadWriteLock2 = cacheLock;
            ReentrantReadWriteLock.ReadLock lock3 = reentrantReadWriteLock2.readLock();
            int readHoldCount2 = reentrantReadWriteLock2.getWriteHoldCount() == 0 ? reentrantReadWriteLock2.getReadHoldCount() : 0;
            for (int i3 = 0; i3 < readHoldCount2; i3++) {
                lock3.unlock();
            }
            ReentrantReadWriteLock.WriteLock writeLock2 = reentrantReadWriteLock2.writeLock();
            writeLock2.lock();
            try {
                exceptionCtors.put(exception.getClass(), function1CreateConstructor == null ? new Function1() { // from class: io.ktor.utils.io.ExceptionUtilsJvmKt$tryCopyException$5$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Void invoke(Throwable it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return null;
                    }
                } : function1CreateConstructor);
                Unit unit2 = Unit.INSTANCE;
                while (i < readHoldCount2) {
                    lock3.lock();
                    i++;
                }
                writeLock2.unlock();
                if (function1CreateConstructor != null) {
                    return (Throwable) function1CreateConstructor.invoke(cause);
                }
                return null;
            } catch (Throwable th2) {
                while (i < readHoldCount2) {
                    lock3.lock();
                    i++;
                }
                writeLock2.unlock();
                throw th2;
            }
        } finally {
            lock.unlock();
        }
    }

    private static final Function1 createConstructor(final Constructor constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        int length = parameterTypes.length;
        if (length == 0) {
            return new Function1() { // from class: io.ktor.utils.io.ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Throwable invoke(Throwable e) {
                    Object objM2639constructorimpl;
                    Intrinsics.checkNotNullParameter(e, "e");
                    try {
                        Result.Companion companion = Result.Companion;
                        Object objNewInstance = constructor.newInstance(null);
                        Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type kotlin.Throwable");
                        Throwable th = (Throwable) objNewInstance;
                        th.initCause(e);
                        objM2639constructorimpl = Result.m2639constructorimpl(th);
                    } catch (Throwable th2) {
                        Result.Companion companion2 = Result.Companion;
                        objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th2));
                    }
                    return (Throwable) (Result.m2644isFailureimpl(objM2639constructorimpl) ? null : objM2639constructorimpl);
                }
            };
        }
        if (length != 1) {
            if (length == 2 && Intrinsics.areEqual(parameterTypes[0], String.class) && Intrinsics.areEqual(parameterTypes[1], Throwable.class)) {
                return new Function1() { // from class: io.ktor.utils.io.ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Throwable invoke(Throwable e) {
                        Object objM2639constructorimpl;
                        Intrinsics.checkNotNullParameter(e, "e");
                        try {
                            Result.Companion companion = Result.Companion;
                            Object objNewInstance = constructor.newInstance(e.getMessage(), e);
                            Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type kotlin.Throwable");
                            objM2639constructorimpl = Result.m2639constructorimpl((Throwable) objNewInstance);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.Companion;
                            objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m2644isFailureimpl(objM2639constructorimpl)) {
                            objM2639constructorimpl = null;
                        }
                        return (Throwable) objM2639constructorimpl;
                    }
                };
            }
            return null;
        }
        Class<?> cls = parameterTypes[0];
        if (Intrinsics.areEqual(cls, Throwable.class)) {
            return new Function1() { // from class: io.ktor.utils.io.ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Throwable invoke(Throwable e) {
                    Object objM2639constructorimpl;
                    Intrinsics.checkNotNullParameter(e, "e");
                    try {
                        Result.Companion companion = Result.Companion;
                        Object objNewInstance = constructor.newInstance(e);
                        Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type kotlin.Throwable");
                        objM2639constructorimpl = Result.m2639constructorimpl((Throwable) objNewInstance);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.Companion;
                        objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m2644isFailureimpl(objM2639constructorimpl)) {
                        objM2639constructorimpl = null;
                    }
                    return (Throwable) objM2639constructorimpl;
                }
            };
        }
        if (Intrinsics.areEqual(cls, String.class)) {
            return new Function1() { // from class: io.ktor.utils.io.ExceptionUtilsJvmKt$createConstructor$$inlined$safeCtor$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Throwable invoke(Throwable e) {
                    Object objM2639constructorimpl;
                    Intrinsics.checkNotNullParameter(e, "e");
                    try {
                        Result.Companion companion = Result.Companion;
                        Object objNewInstance = constructor.newInstance(e.getMessage());
                        Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type kotlin.Throwable");
                        Throwable th = (Throwable) objNewInstance;
                        th.initCause(e);
                        objM2639constructorimpl = Result.m2639constructorimpl(th);
                    } catch (Throwable th2) {
                        Result.Companion companion2 = Result.Companion;
                        objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m2644isFailureimpl(objM2639constructorimpl)) {
                        objM2639constructorimpl = null;
                    }
                    return (Throwable) objM2639constructorimpl;
                }
            };
        }
        return null;
    }

    private static final int fieldsCountOrDefault(Class cls, int i) {
        Object objM2639constructorimpl;
        JvmClassMappingKt.getKotlinClass(cls);
        try {
            Result.Companion companion = Result.Companion;
            objM2639constructorimpl = Result.m2639constructorimpl(Integer.valueOf(fieldsCount$default(cls, 0, 1, null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th));
        }
        Integer numValueOf = Integer.valueOf(i);
        if (Result.m2644isFailureimpl(objM2639constructorimpl)) {
            objM2639constructorimpl = numValueOf;
        }
        return ((Number) objM2639constructorimpl).intValue();
    }

    static /* synthetic */ int fieldsCount$default(Class cls, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return fieldsCount(cls, i);
    }

    private static final int fieldsCount(Class cls, int i) {
        do {
            Field[] declaredFields = cls.getDeclaredFields();
            Intrinsics.checkNotNullExpressionValue(declaredFields, "declaredFields");
            int i2 = 0;
            for (Field field : declaredFields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    i2++;
                }
            }
            i += i2;
            cls = cls.getSuperclass();
        } while (cls != null);
        return i;
    }
}
