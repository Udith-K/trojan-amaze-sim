package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: Annotations.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class FilteredAnnotations implements Annotations {
    private final Annotations delegate;
    private final Function1 fqNameFilter;
    private final boolean isDefinitelyNewInference;

    public FilteredAnnotations(Annotations delegate, boolean z, Function1 fqNameFilter) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(fqNameFilter, "fqNameFilter");
        this.delegate = delegate;
        this.isDefinitelyNewInference = z;
        this.fqNameFilter = fqNameFilter;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FilteredAnnotations(Annotations delegate, Function1 fqNameFilter) {
        this(delegate, false, fqNameFilter);
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(fqNameFilter, "fqNameFilter");
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        if (((Boolean) this.fqNameFilter.invoke(fqName)).booleanValue()) {
            return this.delegate.hasAnnotation(fqName);
        }
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    /* JADX INFO: renamed from: findAnnotation */
    public AnnotationDescriptor mo2748findAnnotation(FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        if (((Boolean) this.fqNameFilter.invoke(fqName)).booleanValue()) {
            return this.delegate.mo2748findAnnotation(fqName);
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        Annotations annotations = this.delegate;
        ArrayList arrayList = new ArrayList();
        for (Object obj : annotations) {
            if (shouldBeReturned((AnnotationDescriptor) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList.iterator();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        boolean z;
        Annotations annotations = this.delegate;
        if ((annotations instanceof Collection) && ((Collection) annotations).isEmpty()) {
            z = false;
        } else {
            Iterator it = annotations.iterator();
            while (it.hasNext()) {
                if (shouldBeReturned((AnnotationDescriptor) it.next())) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        return this.isDefinitelyNewInference ? !z : z;
    }

    private final boolean shouldBeReturned(AnnotationDescriptor annotationDescriptor) {
        FqName fqName = annotationDescriptor.getFqName();
        return fqName != null && ((Boolean) this.fqNameFilter.invoke(fqName)).booleanValue();
    }
}
