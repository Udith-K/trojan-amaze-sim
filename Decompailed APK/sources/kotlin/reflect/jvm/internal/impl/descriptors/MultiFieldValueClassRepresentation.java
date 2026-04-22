package kotlin.reflect.jvm.internal.impl.descriptors;

import ch.qos.logback.core.CoreConstants;
import java.util.List;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: MultiFieldValueClassRepresentation.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class MultiFieldValueClassRepresentation extends ValueClassRepresentation {
    private final Map map;
    private final List underlyingPropertyNamesToTypes;

    public List getUnderlyingPropertyNamesToTypes() {
        return this.underlyingPropertyNamesToTypes;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiFieldValueClassRepresentation(List underlyingPropertyNamesToTypes) {
        super(null);
        Intrinsics.checkNotNullParameter(underlyingPropertyNamesToTypes, "underlyingPropertyNamesToTypes");
        this.underlyingPropertyNamesToTypes = underlyingPropertyNamesToTypes;
        Map map = MapsKt.toMap(getUnderlyingPropertyNamesToTypes());
        if (map.size() == getUnderlyingPropertyNamesToTypes().size()) {
            this.map = map;
            return;
        }
        throw new IllegalArgumentException("Some properties have the same names");
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation
    public boolean containsPropertyWithName(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.map.containsKey(name);
    }

    public String toString() {
        return "MultiFieldValueClassRepresentation(underlyingPropertyNamesToTypes=" + getUnderlyingPropertyNamesToTypes() + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
