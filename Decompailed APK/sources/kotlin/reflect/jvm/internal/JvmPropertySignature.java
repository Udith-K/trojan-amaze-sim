package kotlin.reflect.jvm.internal;

import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.JvmFunctionSignature;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.NameUtils;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;

/* JADX INFO: compiled from: RuntimeTypeMapper.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class JvmPropertySignature {
    public /* synthetic */ JvmPropertySignature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String asString();

    private JvmPropertySignature() {
    }

    /* JADX INFO: compiled from: RuntimeTypeMapper.kt */
    public static final class KotlinProperty extends JvmPropertySignature {
        private final PropertyDescriptor descriptor;
        private final NameResolver nameResolver;
        private final ProtoBuf$Property proto;
        private final JvmProtoBuf.JvmPropertySignature signature;
        private final String string;
        private final TypeTable typeTable;

        public final PropertyDescriptor getDescriptor() {
            return this.descriptor;
        }

        public final ProtoBuf$Property getProto() {
            return this.proto;
        }

        public final JvmProtoBuf.JvmPropertySignature getSignature() {
            return this.signature;
        }

        public final NameResolver getNameResolver() {
            return this.nameResolver;
        }

        public final TypeTable getTypeTable() {
            return this.typeTable;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KotlinProperty(PropertyDescriptor descriptor, ProtoBuf$Property proto, JvmProtoBuf.JvmPropertySignature signature, NameResolver nameResolver, TypeTable typeTable) {
            String str;
            super(null);
            Intrinsics.checkNotNullParameter(descriptor, "descriptor");
            Intrinsics.checkNotNullParameter(proto, "proto");
            Intrinsics.checkNotNullParameter(signature, "signature");
            Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
            Intrinsics.checkNotNullParameter(typeTable, "typeTable");
            this.descriptor = descriptor;
            this.proto = proto;
            this.signature = signature;
            this.nameResolver = nameResolver;
            this.typeTable = typeTable;
            if (signature.hasGetter()) {
                str = nameResolver.getString(signature.getGetter().getName()) + nameResolver.getString(signature.getGetter().getDesc());
            } else {
                JvmMemberSignature.Field jvmFieldSignature$default = JvmProtoBufUtil.getJvmFieldSignature$default(JvmProtoBufUtil.INSTANCE, proto, nameResolver, typeTable, false, 8, null);
                if (jvmFieldSignature$default != null) {
                    String strComponent1 = jvmFieldSignature$default.component1();
                    str = JvmAbi.getterName(strComponent1) + getManglingSuffix() + "()" + jvmFieldSignature$default.component2();
                } else {
                    throw new KotlinReflectionInternalError("No field signature for property: " + descriptor);
                }
            }
            this.string = str;
        }

        private final String getManglingSuffix() {
            String string;
            DeclarationDescriptor containingDeclaration = this.descriptor.getContainingDeclaration();
            Intrinsics.checkNotNullExpressionValue(containingDeclaration, "getContainingDeclaration(...)");
            if (Intrinsics.areEqual(this.descriptor.getVisibility(), DescriptorVisibilities.INTERNAL) && (containingDeclaration instanceof DeserializedClassDescriptor)) {
                ProtoBuf$Class classProto = ((DeserializedClassDescriptor) containingDeclaration).getClassProto();
                GeneratedMessageLite.GeneratedExtension classModuleName = JvmProtoBuf.classModuleName;
                Intrinsics.checkNotNullExpressionValue(classModuleName, "classModuleName");
                Integer num = (Integer) ProtoBufUtilKt.getExtensionOrNull(classProto, classModuleName);
                if (num == null || (string = this.nameResolver.getString(num.intValue())) == null) {
                    string = "main";
                }
                return CoreConstants.DOLLAR + NameUtils.sanitizeAsJavaIdentifier(string);
            }
            if (Intrinsics.areEqual(this.descriptor.getVisibility(), DescriptorVisibilities.PRIVATE) && (containingDeclaration instanceof PackageFragmentDescriptor)) {
                PropertyDescriptor propertyDescriptor = this.descriptor;
                Intrinsics.checkNotNull(propertyDescriptor, "null cannot be cast to non-null type org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedPropertyDescriptor");
                DeserializedContainerSource containerSource = ((DeserializedPropertyDescriptor) propertyDescriptor).getContainerSource();
                if (!(containerSource instanceof JvmPackagePartSource)) {
                    return "";
                }
                JvmPackagePartSource jvmPackagePartSource = (JvmPackagePartSource) containerSource;
                if (jvmPackagePartSource.getFacadeClassName() != null) {
                    return CoreConstants.DOLLAR + jvmPackagePartSource.getSimpleName().asString();
                }
                return "";
            }
            return "";
        }

        @Override // kotlin.reflect.jvm.internal.JvmPropertySignature
        public String asString() {
            return this.string;
        }
    }

    /* JADX INFO: compiled from: RuntimeTypeMapper.kt */
    public static final class JavaMethodProperty extends JvmPropertySignature {
        private final Method getterMethod;
        private final Method setterMethod;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JavaMethodProperty(Method getterMethod, Method method) {
            super(null);
            Intrinsics.checkNotNullParameter(getterMethod, "getterMethod");
            this.getterMethod = getterMethod;
            this.setterMethod = method;
        }

        public final Method getGetterMethod() {
            return this.getterMethod;
        }

        public final Method getSetterMethod() {
            return this.setterMethod;
        }

        @Override // kotlin.reflect.jvm.internal.JvmPropertySignature
        public String asString() {
            return RuntimeTypeMapperKt.getSignature(this.getterMethod);
        }
    }

    /* JADX INFO: compiled from: RuntimeTypeMapper.kt */
    public static final class JavaField extends JvmPropertySignature {
        private final Field field;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JavaField(Field field) {
            super(null);
            Intrinsics.checkNotNullParameter(field, "field");
            this.field = field;
        }

        public final Field getField() {
            return this.field;
        }

        @Override // kotlin.reflect.jvm.internal.JvmPropertySignature
        public String asString() {
            StringBuilder sb = new StringBuilder();
            String name = this.field.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            sb.append(JvmAbi.getterName(name));
            sb.append("()");
            Class<?> type = this.field.getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            sb.append(ReflectClassUtilKt.getDesc(type));
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: RuntimeTypeMapper.kt */
    public static final class MappedKotlinProperty extends JvmPropertySignature {
        private final JvmFunctionSignature.KotlinFunction getterSignature;
        private final JvmFunctionSignature.KotlinFunction setterSignature;

        public final JvmFunctionSignature.KotlinFunction getGetterSignature() {
            return this.getterSignature;
        }

        public final JvmFunctionSignature.KotlinFunction getSetterSignature() {
            return this.setterSignature;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MappedKotlinProperty(JvmFunctionSignature.KotlinFunction getterSignature, JvmFunctionSignature.KotlinFunction kotlinFunction) {
            super(null);
            Intrinsics.checkNotNullParameter(getterSignature, "getterSignature");
            this.getterSignature = getterSignature;
            this.setterSignature = kotlinFunction;
        }

        @Override // kotlin.reflect.jvm.internal.JvmPropertySignature
        public String asString() {
            return this.getterSignature.asString();
        }
    }
}
