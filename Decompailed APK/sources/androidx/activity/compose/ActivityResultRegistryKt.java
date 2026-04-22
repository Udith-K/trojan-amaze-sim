package androidx.activity.compose;

import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import java.util.UUID;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ActivityResultRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityResultRegistryKt {
    public static final ManagedActivityResultLauncher rememberLauncherForActivityResult(ActivityResultContract activityResultContract, Function1 function1, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1408504823, i, -1, "androidx.activity.compose.rememberLauncherForActivityResult (ActivityResultRegistry.kt:82)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(activityResultContract, composer, i & 14);
        State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(function1, composer, (i >> 3) & 14);
        String str = (String) RememberSaveableKt.rememberSaveable(new Object[0], null, null, new Function0() { // from class: androidx.activity.compose.ActivityResultRegistryKt$rememberLauncherForActivityResult$key$1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return UUID.randomUUID().toString();
            }
        }, composer, 3072, 6);
        ActivityResultRegistryOwner current = LocalActivityResultRegistryOwner.INSTANCE.getCurrent(composer, 6);
        if (current == null) {
            throw new IllegalStateException("No ActivityResultRegistryOwner was provided via LocalActivityResultRegistryOwner");
        }
        ActivityResultRegistry activityResultRegistry = current.getActivityResultRegistry();
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new ActivityResultLauncherHolder();
            composer.updateRememberedValue(objRememberedValue);
        }
        ActivityResultLauncherHolder activityResultLauncherHolder = (ActivityResultLauncherHolder) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new ManagedActivityResultLauncher(activityResultLauncherHolder, stateRememberUpdatedState);
            composer.updateRememberedValue(objRememberedValue2);
        }
        ManagedActivityResultLauncher managedActivityResultLauncher = (ManagedActivityResultLauncher) objRememberedValue2;
        boolean zChangedInstance = composer.changedInstance(activityResultLauncherHolder) | composer.changedInstance(activityResultRegistry) | composer.changed(str) | composer.changedInstance(activityResultContract) | composer.changed(stateRememberUpdatedState2);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
            Object activityResultRegistryKt$rememberLauncherForActivityResult$1$1 = new ActivityResultRegistryKt$rememberLauncherForActivityResult$1$1(activityResultLauncherHolder, activityResultRegistry, str, activityResultContract, stateRememberUpdatedState2);
            composer.updateRememberedValue(activityResultRegistryKt$rememberLauncherForActivityResult$1$1);
            objRememberedValue3 = activityResultRegistryKt$rememberLauncherForActivityResult$1$1;
        }
        EffectsKt.DisposableEffect(activityResultRegistry, str, activityResultContract, (Function1) objRememberedValue3, composer, (i << 6) & 896);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return managedActivityResultLauncher;
    }
}
