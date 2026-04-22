package kotlinx.serialization.descriptors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SerialDescriptors.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ClassSerialDescriptorBuilder {
    private List annotations;
    private final List elementAnnotations;
    private final List elementDescriptors;
    private final List elementNames;
    private final List elementOptionality;
    private final String serialName;
    private final Set uniqueNames;

    public ClassSerialDescriptorBuilder(String serialName) {
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        this.serialName = serialName;
        this.annotations = CollectionsKt.emptyList();
        this.elementNames = new ArrayList();
        this.uniqueNames = new HashSet();
        this.elementDescriptors = new ArrayList();
        this.elementAnnotations = new ArrayList();
        this.elementOptionality = new ArrayList();
    }

    public final List getAnnotations() {
        return this.annotations;
    }

    public final void setAnnotations(List list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.annotations = list;
    }

    public final List getElementNames$kotlinx_serialization_core() {
        return this.elementNames;
    }

    public final List getElementDescriptors$kotlinx_serialization_core() {
        return this.elementDescriptors;
    }

    public final List getElementAnnotations$kotlinx_serialization_core() {
        return this.elementAnnotations;
    }

    public final List getElementOptionality$kotlinx_serialization_core() {
        return this.elementOptionality;
    }

    public static /* synthetic */ void element$default(ClassSerialDescriptorBuilder classSerialDescriptorBuilder, String str, SerialDescriptor serialDescriptor, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 8) != 0) {
            z = false;
        }
        classSerialDescriptorBuilder.element(str, serialDescriptor, list, z);
    }

    public final void element(String elementName, SerialDescriptor descriptor, List annotations, boolean z) {
        Intrinsics.checkNotNullParameter(elementName, "elementName");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        if (!this.uniqueNames.add(elementName)) {
            throw new IllegalArgumentException(("Element with name '" + elementName + "' is already registered").toString());
        }
        this.elementNames.add(elementName);
        this.elementDescriptors.add(descriptor);
        this.elementAnnotations.add(annotations);
        this.elementOptionality.add(Boolean.valueOf(z));
    }
}
