package androidx.activity.compose;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.State;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: ActivityResultRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
final class ActivityResultRegistryKt$rememberLauncherForActivityResult$1$1 extends Lambda implements Function1 {
    final /* synthetic */ ActivityResultRegistry $activityResultRegistry;
    final /* synthetic */ ActivityResultContract $contract;
    final /* synthetic */ State $currentOnResult;
    final /* synthetic */ String $key;
    final /* synthetic */ ActivityResultLauncherHolder $realLauncher;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ActivityResultRegistryKt$rememberLauncherForActivityResult$1$1(ActivityResultLauncherHolder activityResultLauncherHolder, ActivityResultRegistry activityResultRegistry, String str, ActivityResultContract activityResultContract, State state) {
        super(1);
        this.$realLauncher = activityResultLauncherHolder;
        this.$activityResultRegistry = activityResultRegistry;
        this.$key = str;
        this.$contract = activityResultContract;
        this.$currentOnResult = state;
    }

    @Override // kotlin.jvm.functions.Function1
    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
        ActivityResultLauncherHolder activityResultLauncherHolder = this.$realLauncher;
        ActivityResultRegistry activityResultRegistry = this.$activityResultRegistry;
        String str = this.$key;
        ActivityResultContract activityResultContract = this.$contract;
        final State state = this.$currentOnResult;
        activityResultLauncherHolder.setLauncher(activityResultRegistry.register(str, activityResultContract, new ActivityResultCallback() { // from class: androidx.activity.compose.ActivityResultRegistryKt$rememberLauncherForActivityResult$1$1$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ActivityResultRegistryKt$rememberLauncherForActivityResult$1$1.invoke$lambda$0(state, obj);
            }
        }));
        final ActivityResultLauncherHolder activityResultLauncherHolder2 = this.$realLauncher;
        return new DisposableEffectResult() { // from class: androidx.activity.compose.ActivityResultRegistryKt$rememberLauncherForActivityResult$1$1$invoke$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                activityResultLauncherHolder2.unregister();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(State state, Object obj) {
        ((Function1) state.getValue()).invoke(obj);
    }
}
