package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: Annotations.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface Annotations extends Iterable, KMappedMarker {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: findAnnotation */
    AnnotationDescriptor mo2748findAnnotation(FqName fqName);

    boolean hasAnnotation(FqName fqName);

    boolean isEmpty();

    /* JADX INFO: compiled from: Annotations.kt */
    public static final class DefaultImpls {
        public static boolean hasAnnotation(Annotations annotations, FqName fqName) {
            Intrinsics.checkNotNullParameter(fqName, "fqName");
            return annotations.mo2748findAnnotation(fqName) != null;
        }

        public static AnnotationDescriptor findAnnotation(Annotations annotations, FqName fqName) {
            Object next;
            Intrinsics.checkNotNullParameter(fqName, "fqName");
            Iterator it = annotations.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual(((AnnotationDescriptor) next).getFqName(), fqName)) {
                    break;
                }
            }
            return (AnnotationDescriptor) next;
        }
    }

    /* JADX INFO: compiled from: Annotations.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Annotations EMPTY = new Annotations() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion$EMPTY$1
            public Void findAnnotation(FqName fqName) {
                Intrinsics.checkNotNullParameter(fqName, "fqName");
                return null;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            public boolean isEmpty() {
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            /* JADX INFO: renamed from: findAnnotation, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ AnnotationDescriptor mo2748findAnnotation(FqName fqName) {
                return (AnnotationDescriptor) findAnnotation(fqName);
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            public boolean hasAnnotation(FqName fqName) {
                return Annotations.DefaultImpls.hasAnnotation(this, fqName);
            }

            @Override // java.lang.Iterable
            public Iterator iterator() {
                return CollectionsKt.emptyList().iterator();
            }

            public String toString() {
                return "EMPTY";
            }
        };

        private Companion() {
        }

        public final Annotations getEMPTY() {
            return EMPTY;
        }

        public final Annotations create(List annotations) {
            Intrinsics.checkNotNullParameter(annotations, "annotations");
            return annotations.isEmpty() ? EMPTY : new AnnotationsImpl(annotations);
        }
    }
}
