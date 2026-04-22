package androidx.recyclerview.widget;

import androidx.recyclerview.widget.DiffUtil;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class AsyncDifferConfig {
    private final Executor mBackgroundThreadExecutor;
    private final DiffUtil.ItemCallback mDiffCallback;
    private final Executor mMainThreadExecutor;

    AsyncDifferConfig(Executor executor, Executor executor2, DiffUtil.ItemCallback itemCallback) {
        this.mMainThreadExecutor = executor;
        this.mBackgroundThreadExecutor = executor2;
        this.mDiffCallback = itemCallback;
    }

    public Executor getMainThreadExecutor() {
        return this.mMainThreadExecutor;
    }

    public Executor getBackgroundThreadExecutor() {
        return this.mBackgroundThreadExecutor;
    }

    public DiffUtil.ItemCallback getDiffCallback() {
        return this.mDiffCallback;
    }

    public static final class Builder {
        private static Executor sDiffExecutor;
        private static final Object sExecutorLock = new Object();
        private Executor mBackgroundThreadExecutor;
        private final DiffUtil.ItemCallback mDiffCallback;
        private Executor mMainThreadExecutor;

        public Builder(DiffUtil.ItemCallback itemCallback) {
            this.mDiffCallback = itemCallback;
        }

        public AsyncDifferConfig build() {
            if (this.mBackgroundThreadExecutor == null) {
                synchronized (sExecutorLock) {
                    try {
                        if (sDiffExecutor == null) {
                            sDiffExecutor = Executors.newFixedThreadPool(2);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                this.mBackgroundThreadExecutor = sDiffExecutor;
            }
            return new AsyncDifferConfig(this.mMainThreadExecutor, this.mBackgroundThreadExecutor, this.mDiffCallback);
        }
    }
}
