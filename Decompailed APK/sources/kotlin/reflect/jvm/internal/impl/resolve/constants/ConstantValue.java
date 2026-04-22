package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: constantValues.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ConstantValue {
    private final Object value;

    public abstract KotlinType getType(ModuleDescriptor moduleDescriptor);

    public ConstantValue(Object obj) {
        this.value = obj;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            Object value = getValue();
            ConstantValue constantValue = obj instanceof ConstantValue ? (ConstantValue) obj : null;
            if (!Intrinsics.areEqual(value, constantValue != null ? constantValue.getValue() : null)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        Object value = getValue();
        if (value != null) {
            return value.hashCode();
        }
        return 0;
    }

    public String toString() {
        return String.valueOf(getValue());
    }
}
