package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ModuleDescriptorImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ModuleDependenciesImpl implements ModuleDependencies {
    private final List allDependencies;
    private final Set allExpectedByDependencies;
    private final List directExpectedByDependencies;
    private final Set modulesWhoseInternalsAreVisible;

    public ModuleDependenciesImpl(List allDependencies, Set modulesWhoseInternalsAreVisible, List directExpectedByDependencies, Set allExpectedByDependencies) {
        Intrinsics.checkNotNullParameter(allDependencies, "allDependencies");
        Intrinsics.checkNotNullParameter(modulesWhoseInternalsAreVisible, "modulesWhoseInternalsAreVisible");
        Intrinsics.checkNotNullParameter(directExpectedByDependencies, "directExpectedByDependencies");
        Intrinsics.checkNotNullParameter(allExpectedByDependencies, "allExpectedByDependencies");
        this.allDependencies = allDependencies;
        this.modulesWhoseInternalsAreVisible = modulesWhoseInternalsAreVisible;
        this.directExpectedByDependencies = directExpectedByDependencies;
        this.allExpectedByDependencies = allExpectedByDependencies;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDependencies
    public List getAllDependencies() {
        return this.allDependencies;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDependencies
    public Set getModulesWhoseInternalsAreVisible() {
        return this.modulesWhoseInternalsAreVisible;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDependencies
    public List getDirectExpectedByDependencies() {
        return this.directExpectedByDependencies;
    }
}
