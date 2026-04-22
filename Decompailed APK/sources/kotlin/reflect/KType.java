package kotlin.reflect;

import java.util.List;

/* JADX INFO: compiled from: KType.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KType extends KAnnotatedElement {
    List getArguments();

    KClassifier getClassifier();

    boolean isMarkedNullable();
}
