package kotlin.properties;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: ObservableProperty.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ObservableProperty implements ReadWriteProperty {
    private Object value;

    protected void afterChange(KProperty property, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(property, "property");
    }

    protected boolean beforeChange(KProperty property, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(property, "property");
        return true;
    }

    public ObservableProperty(Object obj) {
        this.value = obj;
    }

    @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
    public Object getValue(Object obj, KProperty property) {
        Intrinsics.checkNotNullParameter(property, "property");
        return this.value;
    }

    @Override // kotlin.properties.ReadWriteProperty
    public void setValue(Object obj, KProperty property, Object obj2) {
        Intrinsics.checkNotNullParameter(property, "property");
        Object obj3 = this.value;
        if (beforeChange(property, obj3, obj2)) {
            this.value = obj2;
            afterChange(property, obj3, obj2);
        }
    }

    public String toString() {
        return "ObservableProperty(value=" + this.value + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
