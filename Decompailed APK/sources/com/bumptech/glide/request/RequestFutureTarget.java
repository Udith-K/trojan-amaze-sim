package com.bumptech.glide.request;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public class RequestFutureTarget implements FutureTarget, RequestListener {
    private static final Waiter DEFAULT_WAITER = new Waiter();
    private final boolean assertBackgroundThread;
    private GlideException exception;
    private final int height;
    private boolean isCancelled;
    private boolean loadFailed;
    private Request request;
    private Object resource;
    private boolean resultReceived;
    private final Waiter waiter;
    private final int width;

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadCleared(Drawable drawable) {
    }

    @Override // com.bumptech.glide.request.target.Target
    public void onLoadStarted(Drawable drawable) {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
    }

    @Override // com.bumptech.glide.request.target.Target
    public void removeCallback(SizeReadyCallback sizeReadyCallback) {
    }

    public RequestFutureTarget(int i, int i2) {
        this(i, i2, true, DEFAULT_WAITER);
    }

    RequestFutureTarget(int i, int i2, boolean z, Waiter waiter) {
        this.width = i;
        this.height = i2;
        this.assertBackgroundThread = z;
        this.waiter = waiter;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        synchronized (this) {
            try {
                if (isDone()) {
                    return false;
                }
                this.isCancelled = true;
                this.waiter.notifyAll(this);
                Request request = null;
                if (z) {
                    Request request2 = this.request;
                    this.request = null;
                    request = request2;
                }
                if (request != null) {
                    request.clear();
                }
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.util.concurrent.Future
    public synchronized boolean isCancelled() {
        return this.isCancelled;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0012  */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean isDone() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isCancelled     // Catch: java.lang.Throwable -> L10
            if (r0 != 0) goto L12
            boolean r0 = r1.resultReceived     // Catch: java.lang.Throwable -> L10
            if (r0 != 0) goto L12
            boolean r0 = r1.loadFailed     // Catch: java.lang.Throwable -> L10
            if (r0 == 0) goto Le
            goto L12
        Le:
            r0 = 0
            goto L13
        L10:
            r0 = move-exception
            goto L15
        L12:
            r0 = 1
        L13:
            monitor-exit(r1)
            return r0
        L15:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L10
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.RequestFutureTarget.isDone():boolean");
    }

    @Override // java.util.concurrent.Future
    public Object get() {
        try {
            return doGet(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    @Override // java.util.concurrent.Future
    public Object get(long j, TimeUnit timeUnit) {
        return doGet(Long.valueOf(timeUnit.toMillis(j)));
    }

    @Override // com.bumptech.glide.request.target.Target
    public void getSize(SizeReadyCallback sizeReadyCallback) {
        sizeReadyCallback.onSizeReady(this.width, this.height);
    }

    @Override // com.bumptech.glide.request.target.Target
    public synchronized void setRequest(Request request) {
        this.request = request;
    }

    @Override // com.bumptech.glide.request.target.Target
    public synchronized Request getRequest() {
        return this.request;
    }

    @Override // com.bumptech.glide.request.target.Target
    public synchronized void onLoadFailed(Drawable drawable) {
    }

    @Override // com.bumptech.glide.request.target.Target
    public synchronized void onResourceReady(Object obj, Transition transition) {
    }

    private synchronized Object doGet(Long l) {
        try {
            if (this.assertBackgroundThread && !isDone()) {
                Util.assertBackgroundThread();
            }
            if (this.isCancelled) {
                throw new CancellationException();
            }
            if (this.loadFailed) {
                throw new ExecutionException(this.exception);
            }
            if (this.resultReceived) {
                return this.resource;
            }
            if (l == null) {
                this.waiter.waitForTimeout(this, 0L);
            } else if (l.longValue() > 0) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                long jLongValue = l.longValue() + jCurrentTimeMillis;
                while (!isDone() && jCurrentTimeMillis < jLongValue) {
                    this.waiter.waitForTimeout(this, jLongValue - jCurrentTimeMillis);
                    jCurrentTimeMillis = System.currentTimeMillis();
                }
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            if (this.loadFailed) {
                throw new ExecutionException(this.exception);
            }
            if (this.isCancelled) {
                throw new CancellationException();
            }
            if (!this.resultReceived) {
                throw new TimeoutException();
            }
            return this.resource;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.bumptech.glide.request.RequestListener
    public synchronized boolean onLoadFailed(GlideException glideException, Object obj, Target target, boolean z) {
        this.loadFailed = true;
        this.exception = glideException;
        this.waiter.notifyAll(this);
        return false;
    }

    @Override // com.bumptech.glide.request.RequestListener
    public synchronized boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z) {
        this.resultReceived = true;
        this.resource = obj;
        this.waiter.notifyAll(this);
        return false;
    }

    public String toString() {
        Request request;
        String str;
        String str2 = super.toString() + "[status=";
        synchronized (this) {
            try {
                request = null;
                if (this.isCancelled) {
                    str = "CANCELLED";
                } else if (this.loadFailed) {
                    str = "FAILURE";
                } else if (this.resultReceived) {
                    str = "SUCCESS";
                } else {
                    str = "PENDING";
                    request = this.request;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (request != null) {
            return str2 + str + ", request=[" + request + "]]";
        }
        return str2 + str + "]";
    }

    static class Waiter {
        Waiter() {
        }

        void waitForTimeout(Object obj, long j) throws InterruptedException {
            obj.wait(j);
        }

        void notifyAll(Object obj) {
            obj.notifyAll();
        }
    }
}
