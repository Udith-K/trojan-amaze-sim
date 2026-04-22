package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.activity.ImmLeaksCleaner;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.lang.reflect.Field;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImmLeaksCleaner.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ImmLeaksCleaner implements LifecycleEventObserver {
    public static final Companion Companion = new Companion(null);
    private static final Lazy cleaner$delegate = LazyKt.lazy(new Function0() { // from class: androidx.activity.ImmLeaksCleaner$Companion$cleaner$2
        @Override // kotlin.jvm.functions.Function0
        public final ImmLeaksCleaner.Cleaner invoke() {
            try {
                Field servedViewField = InputMethodManager.class.getDeclaredField("mServedView");
                servedViewField.setAccessible(true);
                Field nextServedViewField = InputMethodManager.class.getDeclaredField("mNextServedView");
                nextServedViewField.setAccessible(true);
                Field hField = InputMethodManager.class.getDeclaredField("mH");
                hField.setAccessible(true);
                Intrinsics.checkNotNullExpressionValue(hField, "hField");
                Intrinsics.checkNotNullExpressionValue(servedViewField, "servedViewField");
                Intrinsics.checkNotNullExpressionValue(nextServedViewField, "nextServedViewField");
                return new ImmLeaksCleaner.ValidCleaner(hField, servedViewField, nextServedViewField);
            } catch (NoSuchFieldException unused) {
                return ImmLeaksCleaner.FailedInitialization.INSTANCE;
            }
        }
    });
    private final Activity activity;

    public ImmLeaksCleaner(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        Object systemService = this.activity.getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        Cleaner cleaner = Companion.getCleaner();
        Object lock = cleaner.getLock(inputMethodManager);
        if (lock == null) {
            return;
        }
        synchronized (lock) {
            View servedView = cleaner.getServedView(inputMethodManager);
            if (servedView == null) {
                return;
            }
            if (servedView.isAttachedToWindow()) {
                return;
            }
            boolean zClearNextServedView = cleaner.clearNextServedView(inputMethodManager);
            if (zClearNextServedView) {
                inputMethodManager.isActive();
            }
        }
    }

    /* JADX INFO: compiled from: ImmLeaksCleaner.kt */
    public static abstract class Cleaner {
        public /* synthetic */ Cleaner(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract boolean clearNextServedView(InputMethodManager inputMethodManager);

        public abstract Object getLock(InputMethodManager inputMethodManager);

        public abstract View getServedView(InputMethodManager inputMethodManager);

        private Cleaner() {
        }
    }

    /* JADX INFO: compiled from: ImmLeaksCleaner.kt */
    public static final class FailedInitialization extends Cleaner {
        public static final FailedInitialization INSTANCE = new FailedInitialization();

        @Override // androidx.activity.ImmLeaksCleaner.Cleaner
        public boolean clearNextServedView(InputMethodManager inputMethodManager) {
            Intrinsics.checkNotNullParameter(inputMethodManager, "<this>");
            return false;
        }

        @Override // androidx.activity.ImmLeaksCleaner.Cleaner
        public Object getLock(InputMethodManager inputMethodManager) {
            Intrinsics.checkNotNullParameter(inputMethodManager, "<this>");
            return null;
        }

        @Override // androidx.activity.ImmLeaksCleaner.Cleaner
        public View getServedView(InputMethodManager inputMethodManager) {
            Intrinsics.checkNotNullParameter(inputMethodManager, "<this>");
            return null;
        }

        private FailedInitialization() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ImmLeaksCleaner.kt */
    public static final class ValidCleaner extends Cleaner {
        private final Field hField;
        private final Field nextServedViewField;
        private final Field servedViewField;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ValidCleaner(Field hField, Field servedViewField, Field nextServedViewField) {
            super(null);
            Intrinsics.checkNotNullParameter(hField, "hField");
            Intrinsics.checkNotNullParameter(servedViewField, "servedViewField");
            Intrinsics.checkNotNullParameter(nextServedViewField, "nextServedViewField");
            this.hField = hField;
            this.servedViewField = servedViewField;
            this.nextServedViewField = nextServedViewField;
        }

        @Override // androidx.activity.ImmLeaksCleaner.Cleaner
        public Object getLock(InputMethodManager inputMethodManager) {
            Intrinsics.checkNotNullParameter(inputMethodManager, "<this>");
            try {
                return this.hField.get(inputMethodManager);
            } catch (IllegalAccessException unused) {
                return null;
            }
        }

        @Override // androidx.activity.ImmLeaksCleaner.Cleaner
        public View getServedView(InputMethodManager inputMethodManager) {
            Intrinsics.checkNotNullParameter(inputMethodManager, "<this>");
            try {
                return (View) this.servedViewField.get(inputMethodManager);
            } catch (ClassCastException | IllegalAccessException unused) {
                return null;
            }
        }

        @Override // androidx.activity.ImmLeaksCleaner.Cleaner
        public boolean clearNextServedView(InputMethodManager inputMethodManager) {
            Intrinsics.checkNotNullParameter(inputMethodManager, "<this>");
            try {
                this.nextServedViewField.set(inputMethodManager, null);
                return true;
            } catch (IllegalAccessException unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: ImmLeaksCleaner.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Cleaner getCleaner() {
            return (Cleaner) ImmLeaksCleaner.cleaner$delegate.getValue();
        }
    }
}
