package androidx.compose.ui.platform;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.res.ImageVectorCache;
import androidx.compose.ui.res.ResourceIdCache;
import androidx.lifecycle.compose.LocalLifecycleOwnerKt;
import androidx.savedstate.SavedStateRegistryOwner;
import ch.qos.logback.core.CoreConstants;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: AndroidCompositionLocals.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a%\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0003¢\u0006\u0004\b\n\u0010\u000b\u001a!\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0003¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0015\"\u001d\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00168\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0018\u001a\u0004\b\u001c\u0010\u001a\" \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00168\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u0018\u001a\u0004\b\u001e\u0010\u001a\" \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\u00168\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001f\u0010\u0018\u001a\u0004\b \u0010\u001a\"\u001d\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\u00168\u0006¢\u0006\f\n\u0004\b\"\u0010\u0018\u001a\u0004\b#\u0010\u001a\"\u001d\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\u00168\u0006¢\u0006\f\n\u0004\b%\u0010\u0018\u001a\u0004\b&\u0010\u001a\" \u0010+\u001a\b\u0012\u0004\u0012\u00020'0\u00168FX\u0087\u0004¢\u0006\f\u0012\u0004\b)\u0010*\u001a\u0004\b(\u0010\u001a¨\u0006,²\u0006\u000e\u0010\r\u001a\u00020\f8\n@\nX\u008a\u008e\u0002"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView;", "owner", "Lkotlin/Function0;", "", "content", "ProvideAndroidCompositionLocals", "(Landroidx/compose/ui/platform/AndroidComposeView;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Landroid/content/Context;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroidx/compose/ui/res/ResourceIdCache;", "obtainResourceIdCache", "(Landroid/content/Context;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/res/ResourceIdCache;", "Landroid/content/res/Configuration;", "configuration", "Landroidx/compose/ui/res/ImageVectorCache;", "obtainImageVectorCache", "(Landroid/content/Context;Landroid/content/res/Configuration;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/res/ImageVectorCache;", "", "name", "", "noLocalProvidedFor", "(Ljava/lang/String;)Ljava/lang/Void;", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalConfiguration", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalConfiguration", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalContext", "getLocalContext", "LocalImageVectorCache", "getLocalImageVectorCache", "LocalResourceIdCache", "getLocalResourceIdCache", "Landroidx/savedstate/SavedStateRegistryOwner;", "LocalSavedStateRegistryOwner", "getLocalSavedStateRegistryOwner", "Landroid/view/View;", "LocalView", "getLocalView", "Landroidx/lifecycle/LifecycleOwner;", "getLocalLifecycleOwner", "getLocalLifecycleOwner$annotations", "()V", "LocalLifecycleOwner", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidCompositionLocals_androidKt {
    private static final ProvidableCompositionLocal LocalConfiguration = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalConfiguration$1
        @Override // kotlin.jvm.functions.Function0
        public final Configuration invoke() {
            AndroidCompositionLocals_androidKt.noLocalProvidedFor("LocalConfiguration");
            throw new KotlinNothingValueException();
        }
    }, 1, null);
    private static final ProvidableCompositionLocal LocalContext = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalContext$1
        @Override // kotlin.jvm.functions.Function0
        public final Context invoke() {
            AndroidCompositionLocals_androidKt.noLocalProvidedFor("LocalContext");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal LocalImageVectorCache = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalImageVectorCache$1
        @Override // kotlin.jvm.functions.Function0
        public final ImageVectorCache invoke() {
            AndroidCompositionLocals_androidKt.noLocalProvidedFor("LocalImageVectorCache");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal LocalResourceIdCache = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalResourceIdCache$1
        @Override // kotlin.jvm.functions.Function0
        public final ResourceIdCache invoke() {
            AndroidCompositionLocals_androidKt.noLocalProvidedFor("LocalResourceIdCache");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal LocalSavedStateRegistryOwner = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalSavedStateRegistryOwner$1
        @Override // kotlin.jvm.functions.Function0
        public final SavedStateRegistryOwner invoke() {
            AndroidCompositionLocals_androidKt.noLocalProvidedFor("LocalSavedStateRegistryOwner");
            throw new KotlinNothingValueException();
        }
    });
    private static final ProvidableCompositionLocal LocalView = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalView$1
        @Override // kotlin.jvm.functions.Function0
        public final View invoke() {
            AndroidCompositionLocals_androidKt.noLocalProvidedFor("LocalView");
            throw new KotlinNothingValueException();
        }
    });

    public static final ProvidableCompositionLocal getLocalConfiguration() {
        return LocalConfiguration;
    }

    public static final ProvidableCompositionLocal getLocalContext() {
        return LocalContext;
    }

    public static final ProvidableCompositionLocal getLocalLifecycleOwner() {
        return LocalLifecycleOwnerKt.getLocalLifecycleOwner();
    }

    private static final Configuration ProvideAndroidCompositionLocals$lambda$1(MutableState mutableState) {
        return (Configuration) mutableState.getValue();
    }

    public static final ProvidableCompositionLocal getLocalView() {
        return LocalView;
    }

    public static final void ProvideAndroidCompositionLocals(final AndroidComposeView androidComposeView, final Function2 function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1396852028);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(androidComposeView) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1396852028, i2, -1, "androidx.compose.ui.platform.ProvideAndroidCompositionLocals (AndroidCompositionLocals.android.kt:91)");
            }
            Context context = androidComposeView.getContext();
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new Configuration(context.getResources().getConfiguration()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((Configuration) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Configuration configuration) {
                        mutableState.setValue(new Configuration(configuration));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            androidComposeView.setConfigurationChangeObserver((Function1) objRememberedValue2);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new AndroidUriHandler(context);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final AndroidUriHandler androidUriHandler = (AndroidUriHandler) objRememberedValue3;
            AndroidComposeView.ViewTreeOwners viewTreeOwners = androidComposeView.getViewTreeOwners();
            if (viewTreeOwners == null) {
                throw new IllegalStateException("Called when the ViewTreeOwnersAvailability is not yet in Available state");
            }
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = DisposableSaveableStateRegistry_androidKt.DisposableSaveableStateRegistry(androidComposeView, viewTreeOwners.getSavedStateRegistryOwner());
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final DisposableSaveableStateRegistry disposableSaveableStateRegistry = (DisposableSaveableStateRegistry) objRememberedValue4;
            Unit unit = Unit.INSTANCE;
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(disposableSaveableStateRegistry);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new Function1() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$2$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                        final DisposableSaveableStateRegistry disposableSaveableStateRegistry2 = disposableSaveableStateRegistry;
                        return new DisposableEffectResult() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$2$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                disposableSaveableStateRegistry2.dispose();
                            }
                        };
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            EffectsKt.DisposableEffect(unit, (Function1) objRememberedValue5, composerStartRestartGroup, 6);
            CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{LocalConfiguration.provides(ProvideAndroidCompositionLocals$lambda$1(mutableState)), LocalContext.provides(context), LocalLifecycleOwnerKt.getLocalLifecycleOwner().provides(viewTreeOwners.getLifecycleOwner()), LocalSavedStateRegistryOwner.provides(viewTreeOwners.getSavedStateRegistryOwner()), SaveableStateRegistryKt.getLocalSaveableStateRegistry().provides(disposableSaveableStateRegistry), LocalView.provides(androidComposeView.getView()), LocalImageVectorCache.provides(obtainImageVectorCache(context, ProvideAndroidCompositionLocals$lambda$1(mutableState), composerStartRestartGroup, 0)), LocalResourceIdCache.provides(obtainResourceIdCache(context, composerStartRestartGroup, 0)), CompositionLocalsKt.getLocalProvidableScrollCaptureInProgress().provides(Boolean.valueOf(((Boolean) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalScrollCaptureInProgress())).booleanValue() | androidComposeView.getScrollCaptureInProgress$ui_release()))}, ComposableLambdaKt.rememberComposableLambda(1471621628, true, new Function2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.ProvideAndroidCompositionLocals.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    if ((i3 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1471621628, i3, -1, "androidx.compose.ui.platform.ProvideAndroidCompositionLocals.<anonymous> (AndroidCompositionLocals.android.kt:131)");
                        }
                        CompositionLocalsKt.ProvideCommonCompositionLocals(androidComposeView, androidUriHandler, function2, composer2, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.ProvideAndroidCompositionLocals.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    AndroidCompositionLocals_androidKt.ProvideAndroidCompositionLocals(androidComposeView, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    private static final ResourceIdCache obtainResourceIdCache(final Context context, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1348507246, i, -1, "androidx.compose.ui.platform.obtainResourceIdCache (AndroidCompositionLocals.android.kt:141)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new ResourceIdCache();
            composer.updateRememberedValue(objRememberedValue);
        }
        final ResourceIdCache resourceIdCache = (ResourceIdCache) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new ComponentCallbacks2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1
                @Override // android.content.ComponentCallbacks
                public void onConfigurationChanged(Configuration configuration) {
                    resourceIdCache.clear();
                }

                @Override // android.content.ComponentCallbacks
                public void onLowMemory() {
                    resourceIdCache.clear();
                }

                @Override // android.content.ComponentCallbacks2
                public void onTrimMemory(int i2) {
                    resourceIdCache.clear();
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        final AndroidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1 androidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1 = (AndroidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1) objRememberedValue2;
        boolean zChangedInstance = composer.changedInstance(context);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new Function1() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainResourceIdCache$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                    context.getApplicationContext().registerComponentCallbacks(androidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1);
                    final Context context2 = context;
                    final AndroidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1 androidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$12 = androidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$1;
                    return new DisposableEffectResult() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainResourceIdCache$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            context2.getApplicationContext().unregisterComponentCallbacks(androidCompositionLocals_androidKt$obtainResourceIdCache$callbacks$1$12);
                        }
                    };
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        EffectsKt.DisposableEffect(resourceIdCache, (Function1) objRememberedValue3, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return resourceIdCache;
    }

    private static final ImageVectorCache obtainImageVectorCache(final Context context, Configuration configuration, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-485908294, i, -1, "androidx.compose.ui.platform.obtainImageVectorCache (AndroidCompositionLocals.android.kt:172)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new ImageVectorCache();
            composer.updateRememberedValue(objRememberedValue);
        }
        final ImageVectorCache imageVectorCache = (ImageVectorCache) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        Object obj = objRememberedValue2;
        if (objRememberedValue2 == companion.getEmpty()) {
            Configuration configuration2 = new Configuration();
            if (configuration != null) {
                configuration2.setTo(configuration);
            }
            composer.updateRememberedValue(configuration2);
            obj = configuration2;
        }
        final Configuration configuration3 = (Configuration) obj;
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new ComponentCallbacks2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1
                @Override // android.content.ComponentCallbacks
                public void onConfigurationChanged(Configuration configuration4) {
                    imageVectorCache.prune(configuration3.updateFrom(configuration4));
                    configuration3.setTo(configuration4);
                }

                @Override // android.content.ComponentCallbacks
                public void onLowMemory() {
                    imageVectorCache.clear();
                }

                @Override // android.content.ComponentCallbacks2
                public void onTrimMemory(int i2) {
                    imageVectorCache.clear();
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        final AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1 androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1 = (AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1) objRememberedValue3;
        boolean zChangedInstance = composer.changedInstance(context);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = new Function1() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                    context.getApplicationContext().registerComponentCallbacks(androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1);
                    final Context context2 = context;
                    final AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1 androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$12 = androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1;
                    return new DisposableEffectResult() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            context2.getApplicationContext().unregisterComponentCallbacks(androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$12);
                        }
                    };
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        EffectsKt.DisposableEffect(imageVectorCache, (Function1) objRememberedValue4, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return imageVectorCache;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void noLocalProvidedFor(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }
}
