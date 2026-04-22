package kotlin.reflect.jvm.internal;

import java.io.IOException;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: ReflectionObjectRenderer.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ReflectionObjectRenderer {
    public static final ReflectionObjectRenderer INSTANCE = new ReflectionObjectRenderer();
    private static final DescriptorRenderer renderer = DescriptorRenderer.FQ_NAMES_IN_TYPES;

    /* JADX INFO: compiled from: ReflectionObjectRenderer.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KParameter.Kind.values().length];
            try {
                iArr[KParameter.Kind.EXTENSION_RECEIVER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KParameter.Kind.INSTANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KParameter.Kind.VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ReflectionObjectRenderer() {
    }

    private final void appendReceiverType(StringBuilder sb, ReceiverParameterDescriptor receiverParameterDescriptor) {
        if (receiverParameterDescriptor != null) {
            KotlinType type = receiverParameterDescriptor.getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            sb.append(renderType(type));
            sb.append(".");
        }
    }

    private final void appendReceivers(StringBuilder sb, CallableDescriptor callableDescriptor) {
        ReceiverParameterDescriptor instanceReceiverParameter = UtilKt.getInstanceReceiverParameter(callableDescriptor);
        ReceiverParameterDescriptor extensionReceiverParameter = callableDescriptor.getExtensionReceiverParameter();
        appendReceiverType(sb, instanceReceiverParameter);
        boolean z = (instanceReceiverParameter == null || extensionReceiverParameter == null) ? false : true;
        if (z) {
            sb.append("(");
        }
        appendReceiverType(sb, extensionReceiverParameter);
        if (z) {
            sb.append(")");
        }
    }

    private final String renderCallable(CallableDescriptor callableDescriptor) {
        if (callableDescriptor instanceof PropertyDescriptor) {
            return renderProperty((PropertyDescriptor) callableDescriptor);
        }
        if (callableDescriptor instanceof FunctionDescriptor) {
            return renderFunction((FunctionDescriptor) callableDescriptor);
        }
        throw new IllegalStateException(("Illegal callable: " + callableDescriptor).toString());
    }

    public final String renderProperty(PropertyDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        StringBuilder sb = new StringBuilder();
        sb.append(descriptor.isVar() ? "var " : "val ");
        ReflectionObjectRenderer reflectionObjectRenderer = INSTANCE;
        reflectionObjectRenderer.appendReceivers(sb, descriptor);
        DescriptorRenderer descriptorRenderer = renderer;
        Name name = descriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        sb.append(descriptorRenderer.renderName(name, true));
        sb.append(": ");
        KotlinType type = descriptor.getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        sb.append(reflectionObjectRenderer.renderType(type));
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final String renderFunction(FunctionDescriptor descriptor) throws IOException {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        StringBuilder sb = new StringBuilder();
        sb.append("fun ");
        ReflectionObjectRenderer reflectionObjectRenderer = INSTANCE;
        reflectionObjectRenderer.appendReceivers(sb, descriptor);
        DescriptorRenderer descriptorRenderer = renderer;
        Name name = descriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        sb.append(descriptorRenderer.renderName(name, true));
        List valueParameters = descriptor.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "getValueParameters(...)");
        CollectionsKt___CollectionsKt.joinTo(valueParameters, sb, (124 & 2) != 0 ? ", " : ", ", (124 & 4) != 0 ? "" : "(", (124 & 8) == 0 ? ")" : "", (124 & 16) != 0 ? -1 : 0, (124 & 32) != 0 ? "..." : null, (124 & 64) != 0 ? null : new Function1() { // from class: kotlin.reflect.jvm.internal.ReflectionObjectRenderer$$Lambda$0
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return ReflectionObjectRenderer.renderFunction$lambda$2$lambda$1((ValueParameterDescriptor) obj);
            }
        });
        sb.append(": ");
        KotlinType returnType = descriptor.getReturnType();
        Intrinsics.checkNotNull(returnType);
        sb.append(reflectionObjectRenderer.renderType(returnType));
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence renderFunction$lambda$2$lambda$1(ValueParameterDescriptor valueParameterDescriptor) {
        ReflectionObjectRenderer reflectionObjectRenderer = INSTANCE;
        KotlinType type = valueParameterDescriptor.getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        return reflectionObjectRenderer.renderType(type);
    }

    public final String renderLambda(FunctionDescriptor invoke) throws IOException {
        Intrinsics.checkNotNullParameter(invoke, "invoke");
        StringBuilder sb = new StringBuilder();
        ReflectionObjectRenderer reflectionObjectRenderer = INSTANCE;
        reflectionObjectRenderer.appendReceivers(sb, invoke);
        List valueParameters = invoke.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "getValueParameters(...)");
        CollectionsKt___CollectionsKt.joinTo(valueParameters, sb, (124 & 2) != 0 ? ", " : ", ", (124 & 4) != 0 ? "" : "(", (124 & 8) == 0 ? ")" : "", (124 & 16) != 0 ? -1 : 0, (124 & 32) != 0 ? "..." : null, (124 & 64) != 0 ? null : new Function1() { // from class: kotlin.reflect.jvm.internal.ReflectionObjectRenderer$$Lambda$1
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return ReflectionObjectRenderer.renderLambda$lambda$4$lambda$3((ValueParameterDescriptor) obj);
            }
        });
        sb.append(" -> ");
        KotlinType returnType = invoke.getReturnType();
        Intrinsics.checkNotNull(returnType);
        sb.append(reflectionObjectRenderer.renderType(returnType));
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence renderLambda$lambda$4$lambda$3(ValueParameterDescriptor valueParameterDescriptor) {
        ReflectionObjectRenderer reflectionObjectRenderer = INSTANCE;
        KotlinType type = valueParameterDescriptor.getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        return reflectionObjectRenderer.renderType(type);
    }

    public final String renderParameter(KParameterImpl parameter) {
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        StringBuilder sb = new StringBuilder();
        int i = WhenMappings.$EnumSwitchMapping$0[parameter.getKind().ordinal()];
        if (i == 1) {
            sb.append("extension receiver parameter");
        } else if (i == 2) {
            sb.append("instance parameter");
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            sb.append("parameter #" + parameter.getIndex() + ' ' + parameter.getName());
        }
        sb.append(" of ");
        sb.append(INSTANCE.renderCallable(parameter.getCallable().getDescriptor()));
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final String renderType(KotlinType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return renderer.renderType(type);
    }
}
