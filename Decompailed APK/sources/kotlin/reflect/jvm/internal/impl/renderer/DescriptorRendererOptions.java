package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.Set;

/* JADX INFO: compiled from: DescriptorRenderer.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface DescriptorRendererOptions {
    AnnotationArgumentsRenderingPolicy getAnnotationArgumentsRenderingPolicy();

    boolean getDebugMode();

    boolean getEnhancedTypes();

    Set getExcludedTypeAnnotationClasses();

    boolean getIncludeAnnotationArguments();

    boolean getIncludeEmptyAnnotationArguments();

    void setClassifierNamePolicy(ClassifierNamePolicy classifierNamePolicy);

    void setDebugMode(boolean z);

    void setExcludedTypeAnnotationClasses(Set set);

    void setModifiers(Set set);

    void setParameterNameRenderingPolicy(ParameterNameRenderingPolicy parameterNameRenderingPolicy);

    void setReceiverAfterName(boolean z);

    void setRenderCompanionObjectName(boolean z);

    void setStartFromName(boolean z);

    void setTextFormat(RenderingFormat renderingFormat);

    void setWithDefinedIn(boolean z);

    void setWithoutSuperTypes(boolean z);

    void setWithoutTypeParameters(boolean z);

    /* JADX INFO: compiled from: DescriptorRenderer.kt */
    public static final class DefaultImpls {
        public static boolean getIncludeAnnotationArguments(DescriptorRendererOptions descriptorRendererOptions) {
            return descriptorRendererOptions.getAnnotationArgumentsRenderingPolicy().getIncludeAnnotationArguments();
        }

        public static boolean getIncludeEmptyAnnotationArguments(DescriptorRendererOptions descriptorRendererOptions) {
            return descriptorRendererOptions.getAnnotationArgumentsRenderingPolicy().getIncludeEmptyAnnotationArguments();
        }
    }
}
