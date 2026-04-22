package kotlin.reflect.jvm.internal.impl.load.kotlin;

import ch.qos.logback.core.joran.action.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.SpecialJvmAnnotations;
import kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractBinaryClassAnnotationAndConstantLoader extends AbstractBinaryClassAnnotationLoader implements AnnotationAndConstantLoader {
    private final MemoizedFunctionToNotNull storage;

    protected abstract Object loadConstant(String str, Object obj);

    protected abstract Object transformToUnsignedConstant(Object obj);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractBinaryClassAnnotationAndConstantLoader(StorageManager storageManager, KotlinClassFinder kotlinClassFinder) {
        super(kotlinClassFinder);
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(kotlinClassFinder, "kotlinClassFinder");
        this.storage = storageManager.createMemoizedFunction(new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$$Lambda$0
            private final AbstractBinaryClassAnnotationAndConstantLoader arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return AbstractBinaryClassAnnotationAndConstantLoader.storage$lambda$0(this.arg$0, (KotlinJvmBinaryClass) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnnotationsContainerWithConstants storage$lambda$0(AbstractBinaryClassAnnotationAndConstantLoader abstractBinaryClassAnnotationAndConstantLoader, KotlinJvmBinaryClass kotlinClass) {
        Intrinsics.checkNotNullParameter(kotlinClass, "kotlinClass");
        return abstractBinaryClassAnnotationAndConstantLoader.loadAnnotationsAndInitializers(kotlinClass);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader
    public AnnotationsContainerWithConstants getAnnotationsContainer(KotlinJvmBinaryClass binaryClass) {
        Intrinsics.checkNotNullParameter(binaryClass, "binaryClass");
        return (AnnotationsContainerWithConstants) this.storage.invoke(binaryClass);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public Object loadAnnotationDefaultValue(ProtoContainer container, ProtoBuf$Property proto, KotlinType expectedType) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(expectedType, "expectedType");
        return loadConstantFromProperty(container, proto, AnnotatedCallableKind.PROPERTY_GETTER, expectedType, new Function2() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$$Lambda$1
            @Override // kotlin.jvm.functions.Function2
            public Object invoke(Object obj, Object obj2) {
                return AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationDefaultValue$lambda$1((AnnotationsContainerWithConstants) obj, (MemberSignature) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object loadAnnotationDefaultValue$lambda$1(AnnotationsContainerWithConstants loadConstantFromProperty, MemberSignature it) {
        Intrinsics.checkNotNullParameter(loadConstantFromProperty, "$this$loadConstantFromProperty");
        Intrinsics.checkNotNullParameter(it, "it");
        return loadConstantFromProperty.getAnnotationParametersDefaultValues().get(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object loadPropertyConstant$lambda$2(AnnotationsContainerWithConstants loadConstantFromProperty, MemberSignature it) {
        Intrinsics.checkNotNullParameter(loadConstantFromProperty, "$this$loadConstantFromProperty");
        Intrinsics.checkNotNullParameter(it, "it");
        return loadConstantFromProperty.getPropertyConstants().get(it);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public Object loadPropertyConstant(ProtoContainer container, ProtoBuf$Property proto, KotlinType expectedType) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(expectedType, "expectedType");
        return loadConstantFromProperty(container, proto, AnnotatedCallableKind.PROPERTY, expectedType, new Function2() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$$Lambda$2
            @Override // kotlin.jvm.functions.Function2
            public Object invoke(Object obj, Object obj2) {
                return AbstractBinaryClassAnnotationAndConstantLoader.loadPropertyConstant$lambda$2((AnnotationsContainerWithConstants) obj, (MemberSignature) obj2);
            }
        });
    }

    private final Object loadConstantFromProperty(ProtoContainer protoContainer, ProtoBuf$Property protoBuf$Property, AnnotatedCallableKind annotatedCallableKind, KotlinType kotlinType, Function2 function2) {
        Object objInvoke;
        KotlinJvmBinaryClass kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers = findClassWithAnnotationsAndInitializers(protoContainer, AbstractBinaryClassAnnotationLoader.Companion.getSpecialCaseContainerClass(protoContainer, true, true, Flags.IS_CONST.get(protoBuf$Property.getFlags()), JvmProtoBufUtil.isMovedFromInterfaceCompanion(protoBuf$Property), getKotlinClassFinder(), getJvmMetadataVersion()));
        if (kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers == null) {
            return null;
        }
        MemberSignature callableSignature = getCallableSignature(protoBuf$Property, protoContainer.getNameResolver(), protoContainer.getTypeTable(), annotatedCallableKind, kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers.getClassHeader().getMetadataVersion().isAtLeast(DeserializedDescriptorResolver.Companion.getKOTLIN_1_3_RC_METADATA_VERSION$descriptors_jvm()));
        if (callableSignature == null || (objInvoke = function2.invoke(this.storage.invoke(kotlinJvmBinaryClassFindClassWithAnnotationsAndInitializers), callableSignature)) == null) {
            return null;
        }
        return UnsignedTypes.isUnsignedType(kotlinType) ? transformToUnsignedConstant(objInvoke) : objInvoke;
    }

    private final AnnotationsContainerWithConstants loadAnnotationsAndInitializers(final KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        final HashMap map = new HashMap();
        final HashMap map2 = new HashMap();
        final HashMap map3 = new HashMap();
        kotlinJvmBinaryClass.visitMembers(new KotlinJvmBinaryClass.MemberVisitor() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader.loadAnnotationsAndInitializers.1
            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MemberVisitor
            public KotlinJvmBinaryClass.MethodAnnotationVisitor visitMethod(Name name, String desc) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(desc, "desc");
                MemberSignature.Companion companion = MemberSignature.Companion;
                String strAsString = name.asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
                return new AnnotationVisitorForMethod(this, companion.fromMethodNameAndDesc(strAsString, desc));
            }

            @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MemberVisitor
            public KotlinJvmBinaryClass.AnnotationVisitor visitField(Name name, String desc, Object obj) {
                Object objLoadConstant;
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(desc, "desc");
                MemberSignature.Companion companion = MemberSignature.Companion;
                String strAsString = name.asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
                MemberSignature memberSignatureFromFieldNameAndDesc = companion.fromFieldNameAndDesc(strAsString, desc);
                if (obj != null && (objLoadConstant = AbstractBinaryClassAnnotationAndConstantLoader.this.loadConstant(desc, obj)) != null) {
                    map2.put(memberSignatureFromFieldNameAndDesc, objLoadConstant);
                }
                return new MemberAnnotationVisitor(this, memberSignatureFromFieldNameAndDesc);
            }

            /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1$AnnotationVisitorForMethod */
            /* JADX INFO: compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
            public final class AnnotationVisitorForMethod extends MemberAnnotationVisitor implements KotlinJvmBinaryClass.MethodAnnotationVisitor {
                final /* synthetic */ AnonymousClass1 this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnnotationVisitorForMethod(AnonymousClass1 anonymousClass1, MemberSignature signature) {
                    super(anonymousClass1, signature);
                    Intrinsics.checkNotNullParameter(signature, "signature");
                    this.this$0 = anonymousClass1;
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MethodAnnotationVisitor
                public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitParameterAnnotation(int i, ClassId classId, SourceElement source) {
                    Intrinsics.checkNotNullParameter(classId, "classId");
                    Intrinsics.checkNotNullParameter(source, "source");
                    MemberSignature memberSignatureFromMethodSignatureAndParameterIndex = MemberSignature.Companion.fromMethodSignatureAndParameterIndex(getSignature(), i);
                    List arrayList = (List) map.get(memberSignatureFromMethodSignatureAndParameterIndex);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        map.put(memberSignatureFromMethodSignatureAndParameterIndex, arrayList);
                    }
                    return AbstractBinaryClassAnnotationAndConstantLoader.this.loadAnnotationIfNotSpecial(classId, source, arrayList);
                }
            }

            /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationsAndInitializers$1$MemberAnnotationVisitor */
            /* JADX INFO: compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
            public class MemberAnnotationVisitor implements KotlinJvmBinaryClass.AnnotationVisitor {
                private final ArrayList result;
                private final MemberSignature signature;
                final /* synthetic */ AnonymousClass1 this$0;

                public MemberAnnotationVisitor(AnonymousClass1 anonymousClass1, MemberSignature signature) {
                    Intrinsics.checkNotNullParameter(signature, "signature");
                    this.this$0 = anonymousClass1;
                    this.signature = signature;
                    this.result = new ArrayList();
                }

                protected final MemberSignature getSignature() {
                    return this.signature;
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
                public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(ClassId classId, SourceElement source) {
                    Intrinsics.checkNotNullParameter(classId, "classId");
                    Intrinsics.checkNotNullParameter(source, "source");
                    return AbstractBinaryClassAnnotationAndConstantLoader.this.loadAnnotationIfNotSpecial(classId, source, this.result);
                }

                @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor
                public void visitEnd() {
                    if (this.result.isEmpty()) {
                        return;
                    }
                    map.put(this.signature, this.result);
                }
            }
        }, getCachedFileContent(kotlinJvmBinaryClass));
        return new AnnotationsContainerWithConstants(map, map2, map3);
    }

    protected final boolean isRepeatableWithImplicitContainer(ClassId annotationClassId, Map arguments) {
        Intrinsics.checkNotNullParameter(annotationClassId, "annotationClassId");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        if (!Intrinsics.areEqual(annotationClassId, SpecialJvmAnnotations.INSTANCE.getJAVA_LANG_ANNOTATION_REPEATABLE())) {
            return false;
        }
        Object obj = arguments.get(Name.identifier(Action.VALUE_ATTRIBUTE));
        KClassValue kClassValue = obj instanceof KClassValue ? (KClassValue) obj : null;
        if (kClassValue == null) {
            return false;
        }
        Object value = kClassValue.getValue();
        KClassValue.Value.NormalClass normalClass = value instanceof KClassValue.Value.NormalClass ? (KClassValue.Value.NormalClass) value : null;
        if (normalClass == null) {
            return false;
        }
        return isImplicitRepeatableContainer(normalClass.getClassId());
    }
}
