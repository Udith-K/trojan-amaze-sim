package javax.jmdns.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public interface DNSStatefulObject {
    boolean advanceState(DNSTask dNSTask);

    public static final class DNSStatefulObjectSemaphore {
        private static Logger logger = LoggerFactory.getLogger(DNSStatefulObjectSemaphore.class.getName());
        private final String _name;
        private final ConcurrentMap _semaphores = new ConcurrentHashMap(50);

        public DNSStatefulObjectSemaphore(String str) {
            this._name = str;
        }

        public void waitForEvent(long j) {
            Thread threadCurrentThread = Thread.currentThread();
            if (((Semaphore) this._semaphores.get(threadCurrentThread)) == null) {
                Semaphore semaphore = new Semaphore(1, true);
                semaphore.drainPermits();
                this._semaphores.putIfAbsent(threadCurrentThread, semaphore);
            }
            try {
                ((Semaphore) this._semaphores.get(threadCurrentThread)).tryAcquire(j, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logger.debug("Exception ", (Throwable) e);
            }
        }

        public void signalEvent() {
            Collection<Semaphore> collectionValues = this._semaphores.values();
            for (Semaphore semaphore : collectionValues) {
                semaphore.release();
                collectionValues.remove(semaphore);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("Semaphore: ");
            sb.append(this._name);
            if (this._semaphores.size() == 0) {
                sb.append(" no semaphores.");
            } else {
                sb.append(" semaphores:\n");
                for (Map.Entry entry : this._semaphores.entrySet()) {
                    sb.append("\tThread: ");
                    sb.append(((Thread) entry.getKey()).getName());
                    sb.append(' ');
                    sb.append(entry.getValue());
                    sb.append('\n');
                }
            }
            return sb.toString();
        }
    }

    public static class DefaultImplementation extends ReentrantLock implements DNSStatefulObject {
        private static Logger logger = LoggerFactory.getLogger(DefaultImplementation.class.getName());
        private volatile JmDNSImpl _dns = null;
        protected volatile DNSTask _task = null;
        protected volatile DNSState _state = DNSState.PROBING_1;
        private final DNSStatefulObjectSemaphore _announcing = new DNSStatefulObjectSemaphore("Announce");
        private final DNSStatefulObjectSemaphore _canceling = new DNSStatefulObjectSemaphore("Cancel");

        public JmDNSImpl getDns() {
            return this._dns;
        }

        protected void setDns(JmDNSImpl jmDNSImpl) {
            this._dns = jmDNSImpl;
        }

        public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
            if (this._task == null && this._state == dNSState) {
                lock();
                try {
                    if (this._task == null && this._state == dNSState) {
                        setTask(dNSTask);
                    }
                } finally {
                    unlock();
                }
            }
        }

        public void removeAssociationWithTask(DNSTask dNSTask) {
            if (this._task == dNSTask) {
                lock();
                try {
                    if (this._task == dNSTask) {
                        setTask(null);
                    }
                } finally {
                    unlock();
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x000f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean isAssociatedWithTask(javax.jmdns.impl.tasks.DNSTask r2, javax.jmdns.impl.constants.DNSState r3) {
            /*
                r1 = this;
                r1.lock()
                javax.jmdns.impl.tasks.DNSTask r0 = r1._task     // Catch: java.lang.Throwable -> Ld
                if (r0 != r2) goto Lf
                javax.jmdns.impl.constants.DNSState r2 = r1._state     // Catch: java.lang.Throwable -> Ld
                if (r2 != r3) goto Lf
                r2 = 1
                goto L10
            Ld:
                r2 = move-exception
                goto L14
            Lf:
                r2 = 0
            L10:
                r1.unlock()
                return r2
            L14:
                r1.unlock()
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.DNSStatefulObject.DefaultImplementation.isAssociatedWithTask(javax.jmdns.impl.tasks.DNSTask, javax.jmdns.impl.constants.DNSState):boolean");
        }

        protected void setTask(DNSTask dNSTask) {
            this._task = dNSTask;
        }

        protected void setState(DNSState dNSState) {
            lock();
            try {
                this._state = dNSState;
                if (isAnnounced()) {
                    this._announcing.signalEvent();
                }
                if (isCanceled()) {
                    this._canceling.signalEvent();
                    this._announcing.signalEvent();
                }
                unlock();
            } catch (Throwable th) {
                unlock();
                throw th;
            }
        }

        @Override // javax.jmdns.impl.DNSStatefulObject
        public boolean advanceState(DNSTask dNSTask) {
            if (this._task != dNSTask) {
                return true;
            }
            lock();
            try {
                if (this._task == dNSTask) {
                    setState(this._state.advance());
                } else {
                    logger.warn("Trying to advance state whhen not the owner. owner: {} perpetrator: {}", this._task, dNSTask);
                }
                unlock();
                return true;
            } catch (Throwable th) {
                unlock();
                throw th;
            }
        }

        public boolean revertState() {
            if (willCancel()) {
                return true;
            }
            lock();
            try {
                if (!willCancel()) {
                    setState(this._state.revert());
                    setTask(null);
                }
                return true;
            } finally {
                unlock();
            }
        }

        public boolean cancelState() {
            boolean z = false;
            if (!willCancel()) {
                lock();
                try {
                    if (!willCancel()) {
                        setState(DNSState.CANCELING_1);
                        setTask(null);
                        z = true;
                    }
                } finally {
                    unlock();
                }
            }
            return z;
        }

        public boolean recoverState() {
            lock();
            try {
                setState(DNSState.PROBING_1);
                setTask(null);
                unlock();
                return false;
            } catch (Throwable th) {
                unlock();
                throw th;
            }
        }

        public boolean isProbing() {
            return this._state.isProbing();
        }

        public boolean isAnnouncing() {
            return this._state.isAnnouncing();
        }

        public boolean isAnnounced() {
            return this._state.isAnnounced();
        }

        public boolean isCanceling() {
            return this._state.isCanceling();
        }

        public boolean isCanceled() {
            return this._state.isCanceled();
        }

        public boolean isClosing() {
            return this._state.isClosing();
        }

        public boolean isClosed() {
            return this._state.isClosed();
        }

        private boolean willCancel() {
            return this._state.isCanceled() || this._state.isCanceling();
        }

        private boolean willClose() {
            return this._state.isClosed() || this._state.isClosing();
        }

        public boolean waitForCanceled(long j) {
            if (!isCanceled()) {
                this._canceling.waitForEvent(j);
            }
            if (!isCanceled()) {
                this._canceling.waitForEvent(10L);
                if (!isCanceled() && !willClose()) {
                    logger.warn("Wait for canceled timed out: {}", this);
                }
            }
            return isCanceled();
        }

        @Override // java.util.concurrent.locks.ReentrantLock
        public String toString() {
            String str;
            String str2 = "NO DNS";
            try {
                StringBuilder sb = new StringBuilder();
                if (this._dns != null) {
                    str = "DNS: " + this._dns.getName() + " [" + this._dns.getInetAddress() + "]";
                } else {
                    str = "NO DNS";
                }
                sb.append(str);
                sb.append(" state: ");
                sb.append(this._state);
                sb.append(" task: ");
                sb.append(this._task);
                return sb.toString();
            } catch (IOException unused) {
                StringBuilder sb2 = new StringBuilder();
                if (this._dns != null) {
                    str2 = "DNS: " + this._dns.getName();
                }
                sb2.append(str2);
                sb2.append(" state: ");
                sb2.append(this._state);
                sb2.append(" task: ");
                sb2.append(this._task);
                return sb2.toString();
            }
        }
    }
}
