package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LazySaveableStateHolder.kt */
/* JADX INFO: loaded from: classes.dex */
final class LazySaveableStateHolder implements SaveableStateRegistry, SaveableStateHolder {
    public static final Companion Companion = new Companion(null);
    private final Set previouslyComposedKeys;
    private final MutableState wrappedHolder$delegate;
    private final SaveableStateRegistry wrappedRegistry;

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public boolean canBeSaved(Object obj) {
        return this.wrappedRegistry.canBeSaved(obj);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Object consumeRestored(String str) {
        return this.wrappedRegistry.consumeRestored(str);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public SaveableStateRegistry.Entry registerProvider(String str, Function0 function0) {
        return this.wrappedRegistry.registerProvider(str, function0);
    }

    public LazySaveableStateHolder(SaveableStateRegistry saveableStateRegistry) {
        this.wrappedRegistry = saveableStateRegistry;
        this.wrappedHolder$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.previouslyComposedKeys = new LinkedHashSet();
    }

    public LazySaveableStateHolder(final SaveableStateRegistry saveableStateRegistry, Map map) {
        this(SaveableStateRegistryKt.SaveableStateRegistry(map, new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Object obj) {
                SaveableStateRegistry saveableStateRegistry2 = saveableStateRegistry;
                return Boolean.valueOf(saveableStateRegistry2 != null ? saveableStateRegistry2.canBeSaved(obj) : true);
            }
        }));
    }

    public final SaveableStateHolder getWrappedHolder() {
        return (SaveableStateHolder) this.wrappedHolder$delegate.getValue();
    }

    public final void setWrappedHolder(SaveableStateHolder saveableStateHolder) {
        this.wrappedHolder$delegate.setValue(saveableStateHolder);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Map performSave() {
        SaveableStateHolder wrappedHolder = getWrappedHolder();
        if (wrappedHolder != null) {
            Iterator it = this.previouslyComposedKeys.iterator();
            while (it.hasNext()) {
                wrappedHolder.removeState(it.next());
            }
        }
        return this.wrappedRegistry.performSave();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateHolder
    public void SaveableStateProvider(final Object obj, final Function2 function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-697180401);
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
                ComposerKt.traceEventStart(-697180401, i2, -1, "androidx.compose.foundation.lazy.layout.LazySaveableStateHolder.SaveableStateProvider (LazySaveableStateHolder.kt:82)");
            }
            SaveableStateHolder wrappedHolder = getWrappedHolder();
            if (wrappedHolder == null) {
                throw new IllegalArgumentException("null wrappedHolder");
            }
            int i3 = i2 & 14;
            wrappedHolder.SaveableStateProvider(obj, function2, composerStartRestartGroup, i2 & 126);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(this) | composerStartRestartGroup.changedInstance(obj);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder$SaveableStateProvider$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        this.this$0.previouslyComposedKeys.remove(obj);
                        final LazySaveableStateHolder lazySaveableStateHolder = this.this$0;
                        final Object obj2 = obj;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder$SaveableStateProvider$2$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                lazySaveableStateHolder.previouslyComposedKeys.add(obj2);
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            EffectsKt.DisposableEffect(obj, (Function1) objRememberedValue, composerStartRestartGroup, i3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder.SaveableStateProvider.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    invoke((Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i4) {
                    LazySaveableStateHolder.this.SaveableStateProvider(obj, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateHolder
    public void removeState(Object obj) {
        SaveableStateHolder wrappedHolder = getWrappedHolder();
        if (wrappedHolder == null) {
            throw new IllegalArgumentException("null wrappedHolder");
        }
        wrappedHolder.removeState(obj);
    }

    /* JADX INFO: compiled from: LazySaveableStateHolder.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver saver(final SaveableStateRegistry saveableStateRegistry) {
            return SaverKt.Saver(new Function2() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder$Companion$saver$1
                @Override // kotlin.jvm.functions.Function2
                public final Map invoke(SaverScope saverScope, LazySaveableStateHolder lazySaveableStateHolder) {
                    Map mapPerformSave = lazySaveableStateHolder.performSave();
                    if (mapPerformSave.isEmpty()) {
                        return null;
                    }
                    return mapPerformSave;
                }
            }, new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazySaveableStateHolder$Companion$saver$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final LazySaveableStateHolder invoke(Map map) {
                    return new LazySaveableStateHolder(saveableStateRegistry, map);
                }
            });
        }
    }
}
