package kotlinx.serialization.descriptors;

import java.util.List;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: SerialDescriptor.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface SerialDescriptor {
    List getAnnotations();

    List getElementAnnotations(int i);

    SerialDescriptor getElementDescriptor(int i);

    int getElementIndex(String str);

    String getElementName(int i);

    int getElementsCount();

    SerialKind getKind();

    String getSerialName();

    boolean isElementOptional(int i);

    boolean isInline();

    boolean isNullable();

    /* JADX INFO: compiled from: SerialDescriptor.kt */
    public static final class DefaultImpls {
        public static boolean isInline(SerialDescriptor serialDescriptor) {
            return false;
        }

        public static boolean isNullable(SerialDescriptor serialDescriptor) {
            return false;
        }

        public static List getAnnotations(SerialDescriptor serialDescriptor) {
            return CollectionsKt.emptyList();
        }
    }
}
