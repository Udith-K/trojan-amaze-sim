package kotlinx.serialization.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* JADX INFO: compiled from: Tagged.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class NamedValueDecoder extends TaggedDecoder {
    protected abstract String composeName(String str, String str2);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public final String getTag(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        return nested(elementName(serialDescriptor, i));
    }

    protected final String nested(String nestedName) {
        Intrinsics.checkNotNullParameter(nestedName, "nestedName");
        String str = (String) getCurrentTagOrNull();
        if (str == null) {
            str = "";
        }
        return composeName(str, nestedName);
    }

    protected String elementName(SerialDescriptor desc, int i) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        return desc.getElementName(i);
    }
}
