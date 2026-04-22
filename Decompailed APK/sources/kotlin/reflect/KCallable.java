package kotlin.reflect;

import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: KCallable.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KCallable extends KAnnotatedElement {
    Object call(Object... objArr);

    Object callBy(Map map);

    String getName();

    List getParameters();

    KType getReturnType();

    List getTypeParameters();

    KVisibility getVisibility();

    boolean isAbstract();

    boolean isFinal();

    boolean isOpen();

    boolean isSuspend();
}
