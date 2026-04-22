package androidx.compose.runtime.saveable;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.saveable.SaveableStateHolderImpl;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SaveableStateHolder.kt */
/* JADX INFO: loaded from: classes.dex */
final class SaveableStateHolderImpl implements SaveableStateHolder {
    public static final Companion Companion = new Companion(null);
    private static final Saver Saver = SaverKt.Saver(new Function2() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl$Companion$Saver$1
        @Override // kotlin.jvm.functions.Function2
        public final Map invoke(SaverScope saverScope, SaveableStateHolderImpl saveableStateHolderImpl) {
            return saveableStateHolderImpl.saveAll();
        }
    }, new Function1() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl$Companion$Saver$2
        @Override // kotlin.jvm.functions.Function1
        public final SaveableStateHolderImpl invoke(Map map) {
            return new SaveableStateHolderImpl(map);
        }
    });
    private SaveableStateRegistry parentSaveableStateRegistry;
    private final Map registryHolders;
    private final Map savedStates;

    public SaveableStateHolderImpl(Map map) {
        this.savedStates = map;
        this.registryHolders = new LinkedHashMap();
    }

    public /* synthetic */ SaveableStateHolderImpl(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashMap() : map);
    }

    public final SaveableStateRegistry getParentSaveableStateRegistry() {
        return this.parentSaveableStateRegistry;
    }

    public final void setParentSaveableStateRegistry(SaveableStateRegistry saveableStateRegistry) {
        this.parentSaveableStateRegistry = saveableStateRegistry;
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateHolder
    public void SaveableStateProvider(final Object obj, final Function2 function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1198538093);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(obj) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(this) ? 256 : 128;
        }
        if ((i2 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1198538093, i2, -1, "androidx.compose.runtime.saveable.SaveableStateHolderImpl.SaveableStateProvider (SaveableStateHolder.kt:75)");
            }
            composerStartRestartGroup.startReusableGroup(207, obj);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                SaveableStateRegistry saveableStateRegistry = this.parentSaveableStateRegistry;
                if (!(saveableStateRegistry != null ? saveableStateRegistry.canBeSaved(obj) : true)) {
                    throw new IllegalArgumentException(("Type of the key " + obj + " is not supported. On Android you can only use types which can be stored inside the Bundle.").toString());
                }
                objRememberedValue = new RegistryHolder(obj);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final RegistryHolder registryHolder = (RegistryHolder) objRememberedValue;
            CompositionLocalKt.CompositionLocalProvider(SaveableStateRegistryKt.getLocalSaveableStateRegistry().provides(registryHolder.getRegistry()), function2, composerStartRestartGroup, (i2 & 112) | ProvidedValue.$stable);
            Unit unit = Unit.INSTANCE;
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(this) | composerStartRestartGroup.changedInstance(obj) | composerStartRestartGroup.changedInstance(registryHolder);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl$SaveableStateProvider$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        boolean zContainsKey = this.this$0.registryHolders.containsKey(obj);
                        Object obj2 = obj;
                        if (!zContainsKey) {
                            this.this$0.savedStates.remove(obj);
                            this.this$0.registryHolders.put(obj, registryHolder);
                            final SaveableStateHolderImpl.RegistryHolder registryHolder2 = registryHolder;
                            final SaveableStateHolderImpl saveableStateHolderImpl = this.this$0;
                            final Object obj3 = obj;
                            return new DisposableEffectResult() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl$SaveableStateProvider$1$1$1$invoke$$inlined$onDispose$1
                                @Override // androidx.compose.runtime.DisposableEffectResult
                                public void dispose() {
                                    registryHolder2.saveTo(saveableStateHolderImpl.savedStates);
                                    saveableStateHolderImpl.registryHolders.remove(obj3);
                                }
                            };
                        }
                        throw new IllegalArgumentException(("Key " + obj2 + " was used multiple times ").toString());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.DisposableEffect(unit, (Function1) objRememberedValue2, composerStartRestartGroup, 6);
            composerStartRestartGroup.endReusableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl.SaveableStateProvider.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    invoke((Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    SaveableStateHolderImpl.this.SaveableStateProvider(obj, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map saveAll() {
        Map mutableMap = MapsKt.toMutableMap(this.savedStates);
        Iterator it = this.registryHolders.values().iterator();
        while (it.hasNext()) {
            ((RegistryHolder) it.next()).saveTo(mutableMap);
        }
        if (mutableMap.isEmpty()) {
            return null;
        }
        return mutableMap;
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateHolder
    public void removeState(Object obj) {
        RegistryHolder registryHolder = (RegistryHolder) this.registryHolders.get(obj);
        if (registryHolder != null) {
            registryHolder.setShouldSave(false);
        } else {
            this.savedStates.remove(obj);
        }
    }

    /* JADX INFO: compiled from: SaveableStateHolder.kt */
    public final class RegistryHolder {
        private final Object key;
        private final SaveableStateRegistry registry;
        private boolean shouldSave = true;

        public RegistryHolder(Object obj) {
            this.key = obj;
            this.registry = SaveableStateRegistryKt.SaveableStateRegistry((Map) SaveableStateHolderImpl.this.savedStates.get(obj), new Function1() { // from class: androidx.compose.runtime.saveable.SaveableStateHolderImpl$RegistryHolder$registry$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Object obj2) {
                    SaveableStateRegistry parentSaveableStateRegistry = saveableStateHolderImpl.getParentSaveableStateRegistry();
                    return Boolean.valueOf(parentSaveableStateRegistry != null ? parentSaveableStateRegistry.canBeSaved(obj2) : true);
                }
            });
        }

        public final void setShouldSave(boolean z) {
            this.shouldSave = z;
        }

        public final SaveableStateRegistry getRegistry() {
            return this.registry;
        }

        public final void saveTo(Map map) {
            if (this.shouldSave) {
                Map mapPerformSave = this.registry.performSave();
                if (mapPerformSave.isEmpty()) {
                    map.remove(this.key);
                } else {
                    map.put(this.key, mapPerformSave);
                }
            }
        }
    }

    /* JADX INFO: compiled from: SaveableStateHolder.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver getSaver() {
            return SaveableStateHolderImpl.Saver;
        }
    }
}
