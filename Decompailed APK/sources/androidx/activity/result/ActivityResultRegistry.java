package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.os.BundleCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.random.Random;
import kotlin.sequences.SequencesKt;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: ActivityResultRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityResultRegistry {
    private static final Companion Companion = new Companion(null);
    private final Map rcToKey = new LinkedHashMap();
    private final Map keyToRc = new LinkedHashMap();
    private final Map keyToLifecycleContainers = new LinkedHashMap();
    private final List launchedKeys = new ArrayList();
    private final transient Map keyToCallback = new LinkedHashMap();
    private final Map parsedPendingResults = new LinkedHashMap();
    private final Bundle pendingResults = new Bundle();

    public abstract void onLaunch(int i, ActivityResultContract activityResultContract, Object obj, ActivityOptionsCompat activityOptionsCompat);

    public final ActivityResultLauncher register(final String key, LifecycleOwner lifecycleOwner, final ActivityResultContract contract, final ActivityResultCallback callback) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(contract, "contract");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            throw new IllegalStateException(("LifecycleOwner " + lifecycleOwner + " is attempting to register while current state is " + lifecycle.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.").toString());
        }
        registerKey(key);
        LifecycleContainer lifecycleContainer = (LifecycleContainer) this.keyToLifecycleContainers.get(key);
        if (lifecycleContainer == null) {
            lifecycleContainer = new LifecycleContainer(lifecycle);
        }
        lifecycleContainer.addObserver(new LifecycleEventObserver() { // from class: androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                ActivityResultRegistry.register$lambda$1(this.f$0, key, callback, contract, lifecycleOwner2, event);
            }
        });
        this.keyToLifecycleContainers.put(key, lifecycleContainer);
        return new ActivityResultLauncher() { // from class: androidx.activity.result.ActivityResultRegistry.register.2
            @Override // androidx.activity.result.ActivityResultLauncher
            public void launch(Object obj, ActivityOptionsCompat activityOptionsCompat) throws Exception {
                Object obj2 = ActivityResultRegistry.this.keyToRc.get(key);
                ActivityResultContract activityResultContract = contract;
                if (obj2 != null) {
                    int iIntValue = ((Number) obj2).intValue();
                    ActivityResultRegistry.this.launchedKeys.add(key);
                    try {
                        ActivityResultRegistry.this.onLaunch(iIntValue, contract, obj, activityOptionsCompat);
                        return;
                    } catch (Exception e) {
                        ActivityResultRegistry.this.launchedKeys.remove(key);
                        throw e;
                    }
                }
                throw new IllegalStateException(("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract + " and input " + obj + ". You must ensure the ActivityResultLauncher is registered before calling launch().").toString());
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public void unregister() {
                ActivityResultRegistry.this.unregister$activity_release(key);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void register$lambda$1(ActivityResultRegistry activityResultRegistry, String str, ActivityResultCallback activityResultCallback, ActivityResultContract activityResultContract, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(event, "event");
        if (Lifecycle.Event.ON_START == event) {
            activityResultRegistry.keyToCallback.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
            if (activityResultRegistry.parsedPendingResults.containsKey(str)) {
                Object obj = activityResultRegistry.parsedPendingResults.get(str);
                activityResultRegistry.parsedPendingResults.remove(str);
                activityResultCallback.onActivityResult(obj);
            }
            ActivityResult activityResult = (ActivityResult) BundleCompat.getParcelable(activityResultRegistry.pendingResults, str, ActivityResult.class);
            if (activityResult != null) {
                activityResultRegistry.pendingResults.remove(str);
                activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
                return;
            }
            return;
        }
        if (Lifecycle.Event.ON_STOP == event) {
            activityResultRegistry.keyToCallback.remove(str);
        } else if (Lifecycle.Event.ON_DESTROY == event) {
            activityResultRegistry.unregister$activity_release(str);
        }
    }

    public final ActivityResultLauncher register(final String key, final ActivityResultContract contract, ActivityResultCallback callback) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(contract, "contract");
        Intrinsics.checkNotNullParameter(callback, "callback");
        registerKey(key);
        this.keyToCallback.put(key, new CallbackAndContract(callback, contract));
        if (this.parsedPendingResults.containsKey(key)) {
            Object obj = this.parsedPendingResults.get(key);
            this.parsedPendingResults.remove(key);
            callback.onActivityResult(obj);
        }
        ActivityResult activityResult = (ActivityResult) BundleCompat.getParcelable(this.pendingResults, key, ActivityResult.class);
        if (activityResult != null) {
            this.pendingResults.remove(key);
            callback.onActivityResult(contract.parseResult(activityResult.getResultCode(), activityResult.getData()));
        }
        return new ActivityResultLauncher() { // from class: androidx.activity.result.ActivityResultRegistry.register.3
            @Override // androidx.activity.result.ActivityResultLauncher
            public void launch(Object obj2, ActivityOptionsCompat activityOptionsCompat) throws Exception {
                Object obj3 = ActivityResultRegistry.this.keyToRc.get(key);
                ActivityResultContract activityResultContract = contract;
                if (obj3 != null) {
                    int iIntValue = ((Number) obj3).intValue();
                    ActivityResultRegistry.this.launchedKeys.add(key);
                    try {
                        ActivityResultRegistry.this.onLaunch(iIntValue, contract, obj2, activityOptionsCompat);
                        return;
                    } catch (Exception e) {
                        ActivityResultRegistry.this.launchedKeys.remove(key);
                        throw e;
                    }
                }
                throw new IllegalStateException(("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract + " and input " + obj2 + ". You must ensure the ActivityResultLauncher is registered before calling launch().").toString());
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public void unregister() {
                ActivityResultRegistry.this.unregister$activity_release(key);
            }
        };
    }

    public final void unregister$activity_release(String key) {
        Integer num;
        Intrinsics.checkNotNullParameter(key, "key");
        if (!this.launchedKeys.contains(key) && (num = (Integer) this.keyToRc.remove(key)) != null) {
            this.rcToKey.remove(num);
        }
        this.keyToCallback.remove(key);
        if (this.parsedPendingResults.containsKey(key)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + key + ": " + this.parsedPendingResults.get(key));
            this.parsedPendingResults.remove(key);
        }
        if (this.pendingResults.containsKey(key)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + key + ": " + ((ActivityResult) BundleCompat.getParcelable(this.pendingResults, key, ActivityResult.class)));
            this.pendingResults.remove(key);
        }
        LifecycleContainer lifecycleContainer = (LifecycleContainer) this.keyToLifecycleContainers.get(key);
        if (lifecycleContainer != null) {
            lifecycleContainer.clearObservers();
            this.keyToLifecycleContainers.remove(key);
        }
    }

    public final void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(this.keyToRc.values()));
        outState.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(this.keyToRc.keySet()));
        outState.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(this.launchedKeys));
        outState.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", new Bundle(this.pendingResults));
    }

    public final void onRestoreInstanceState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
        if (stringArrayList == null || integerArrayList == null) {
            return;
        }
        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
        if (stringArrayList2 != null) {
            this.launchedKeys.addAll(stringArrayList2);
        }
        Bundle bundle2 = bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
        if (bundle2 != null) {
            this.pendingResults.putAll(bundle2);
        }
        int size = stringArrayList.size();
        for (int i = 0; i < size; i++) {
            String str = stringArrayList.get(i);
            if (this.keyToRc.containsKey(str)) {
                Integer num = (Integer) this.keyToRc.remove(str);
                if (!this.pendingResults.containsKey(str)) {
                    TypeIntrinsics.asMutableMap(this.rcToKey).remove(num);
                }
            }
            Integer num2 = integerArrayList.get(i);
            Intrinsics.checkNotNullExpressionValue(num2, "rcs[i]");
            int iIntValue = num2.intValue();
            String str2 = stringArrayList.get(i);
            Intrinsics.checkNotNullExpressionValue(str2, "keys[i]");
            bindRcKey(iIntValue, str2);
        }
    }

    public final boolean dispatchResult(int i, int i2, Intent intent) {
        String str = (String) this.rcToKey.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        doDispatch(str, i2, intent, (CallbackAndContract) this.keyToCallback.get(str));
        return true;
    }

    public final boolean dispatchResult(int i, Object obj) {
        String str = (String) this.rcToKey.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        CallbackAndContract callbackAndContract = (CallbackAndContract) this.keyToCallback.get(str);
        if ((callbackAndContract != null ? callbackAndContract.getCallback() : null) == null) {
            this.pendingResults.remove(str);
            this.parsedPendingResults.put(str, obj);
            return true;
        }
        ActivityResultCallback callback = callbackAndContract.getCallback();
        Intrinsics.checkNotNull(callback, "null cannot be cast to non-null type androidx.activity.result.ActivityResultCallback<O of androidx.activity.result.ActivityResultRegistry.dispatchResult>");
        if (!this.launchedKeys.remove(str)) {
            return true;
        }
        callback.onActivityResult(obj);
        return true;
    }

    private final void doDispatch(String str, int i, Intent intent, CallbackAndContract callbackAndContract) {
        if ((callbackAndContract != null ? callbackAndContract.getCallback() : null) != null && this.launchedKeys.contains(str)) {
            callbackAndContract.getCallback().onActivityResult(callbackAndContract.getContract().parseResult(i, intent));
            this.launchedKeys.remove(str);
        } else {
            this.parsedPendingResults.remove(str);
            this.pendingResults.putParcelable(str, new ActivityResult(i, intent));
        }
    }

    private final void registerKey(String str) {
        if (((Integer) this.keyToRc.get(str)) != null) {
            return;
        }
        bindRcKey(generateRandomNumber(), str);
    }

    private final int generateRandomNumber() {
        for (Number number : SequencesKt.generateSequence(new Function0() { // from class: androidx.activity.result.ActivityResultRegistry.generateRandomNumber.1
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(Random.Default.nextInt(2147418112) + PKIFailureInfo.notAuthorized);
            }
        })) {
            if (!this.rcToKey.containsKey(Integer.valueOf(number.intValue()))) {
                return number.intValue();
            }
        }
        throw new NoSuchElementException("Sequence contains no element matching the predicate.");
    }

    private final void bindRcKey(int i, String str) {
        this.rcToKey.put(Integer.valueOf(i), str);
        this.keyToRc.put(str, Integer.valueOf(i));
    }

    /* JADX INFO: compiled from: ActivityResultRegistry.kt */
    private static final class CallbackAndContract {
        private final ActivityResultCallback callback;
        private final ActivityResultContract contract;

        public CallbackAndContract(ActivityResultCallback callback, ActivityResultContract contract) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            Intrinsics.checkNotNullParameter(contract, "contract");
            this.callback = callback;
            this.contract = contract;
        }

        public final ActivityResultCallback getCallback() {
            return this.callback;
        }

        public final ActivityResultContract getContract() {
            return this.contract;
        }
    }

    /* JADX INFO: compiled from: ActivityResultRegistry.kt */
    private static final class LifecycleContainer {
        private final Lifecycle lifecycle;
        private final List observers;

        public LifecycleContainer(Lifecycle lifecycle) {
            Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
            this.lifecycle = lifecycle;
            this.observers = new ArrayList();
        }

        public final void addObserver(LifecycleEventObserver observer) {
            Intrinsics.checkNotNullParameter(observer, "observer");
            this.lifecycle.addObserver(observer);
            this.observers.add(observer);
        }

        public final void clearObservers() {
            Iterator it = this.observers.iterator();
            while (it.hasNext()) {
                this.lifecycle.removeObserver((LifecycleEventObserver) it.next());
            }
            this.observers.clear();
        }
    }

    /* JADX INFO: compiled from: ActivityResultRegistry.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
