package kotlin.reflect;

/* JADX INFO: compiled from: KClass.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KClass extends KDeclarationContainer, KAnnotatedElement, KClassifier {
    String getQualifiedName();

    String getSimpleName();

    boolean isInstance(Object obj);

    boolean isValue();
}
