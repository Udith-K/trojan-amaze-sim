package kotlin.reflect;

import java.util.List;

/* JADX INFO: compiled from: KTypeParameter.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KTypeParameter extends KClassifier {
    String getName();

    List getUpperBounds();

    KVariance getVariance();
}
