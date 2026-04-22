package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: ModuleDescriptor.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface ModuleDescriptor extends DeclarationDescriptor {
    KotlinBuiltIns getBuiltIns();

    Object getCapability(ModuleCapability moduleCapability);

    List getExpectedByModules();

    PackageViewDescriptor getPackage(FqName fqName);

    Collection getSubPackagesOf(FqName fqName, Function1 function1);

    boolean shouldSeeInternalsOf(ModuleDescriptor moduleDescriptor);

    /* JADX INFO: compiled from: ModuleDescriptor.kt */
    public static final class DefaultImpls {
        public static DeclarationDescriptor getContainingDeclaration(ModuleDescriptor moduleDescriptor) {
            return null;
        }

        public static Object accept(ModuleDescriptor moduleDescriptor, DeclarationDescriptorVisitor visitor, Object obj) {
            Intrinsics.checkNotNullParameter(visitor, "visitor");
            return visitor.visitModuleDeclaration(moduleDescriptor, obj);
        }
    }
}
