package javax.jmdns.impl;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import java.net.InetAddress;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import javax.jmdns.impl.tasks.RecordReaper;
import javax.jmdns.impl.tasks.Responder;
import javax.jmdns.impl.tasks.resolver.ServiceInfoResolver;
import javax.jmdns.impl.tasks.resolver.ServiceResolver;
import javax.jmdns.impl.tasks.state.Announcer;
import javax.jmdns.impl.tasks.state.Canceler;
import javax.jmdns.impl.tasks.state.Prober;
import javax.jmdns.impl.tasks.state.Renewer;

/* JADX INFO: loaded from: classes.dex */
public interface DNSTaskStarter {
    void cancelStateTimer();

    void cancelTimer();

    void purgeStateTimer();

    void purgeTimer();

    void startAnnouncer();

    void startCanceler();

    void startProber();

    void startReaper();

    void startRenewer();

    void startResponder(DNSIncoming dNSIncoming, InetAddress inetAddress, int i);

    void startServiceInfoResolver(ServiceInfoImpl serviceInfoImpl);

    void startServiceResolver(String str);

    public static final class Factory {
        private static final AtomicReference _databaseClassDelegate = new AtomicReference();
        private static volatile Factory _instance;
        private final ConcurrentMap _instances = new ConcurrentHashMap(20);

        private Factory() {
        }

        protected static DNSTaskStarter newDNSTaskStarter(JmDNSImpl jmDNSImpl) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(_databaseClassDelegate.get());
            return new DNSTaskStarterImpl(jmDNSImpl);
        }

        public static Factory getInstance() {
            if (_instance == null) {
                synchronized (Factory.class) {
                    try {
                        if (_instance == null) {
                            _instance = new Factory();
                        }
                    } finally {
                    }
                }
            }
            return _instance;
        }

        public DNSTaskStarter getStarter(JmDNSImpl jmDNSImpl) {
            DNSTaskStarter dNSTaskStarter = (DNSTaskStarter) this._instances.get(jmDNSImpl);
            if (dNSTaskStarter != null) {
                return dNSTaskStarter;
            }
            this._instances.putIfAbsent(jmDNSImpl, newDNSTaskStarter(jmDNSImpl));
            return (DNSTaskStarter) this._instances.get(jmDNSImpl);
        }

        public void disposeStarter(JmDNSImpl jmDNSImpl) {
            this._instances.remove(jmDNSImpl);
        }
    }

    public static final class DNSTaskStarterImpl implements DNSTaskStarter {
        private final JmDNSImpl _jmDNSImpl;
        private final Timer _stateTimer;
        private final Timer _timer;

        public static class StarterTimer extends Timer {
            private volatile boolean _cancelled;

            public StarterTimer(String str, boolean z) {
                super(str, z);
                this._cancelled = false;
            }

            @Override // java.util.Timer
            public synchronized void cancel() {
                if (this._cancelled) {
                    return;
                }
                this._cancelled = true;
                super.cancel();
            }

            @Override // java.util.Timer
            public synchronized void schedule(TimerTask timerTask, long j) {
                if (this._cancelled) {
                    return;
                }
                super.schedule(timerTask, j);
            }

            @Override // java.util.Timer
            public synchronized void schedule(TimerTask timerTask, Date date) {
                if (this._cancelled) {
                    return;
                }
                super.schedule(timerTask, date);
            }

            @Override // java.util.Timer
            public synchronized void schedule(TimerTask timerTask, long j, long j2) {
                if (this._cancelled) {
                    return;
                }
                super.schedule(timerTask, j, j2);
            }

            @Override // java.util.Timer
            public synchronized void schedule(TimerTask timerTask, Date date, long j) {
                if (this._cancelled) {
                    return;
                }
                super.schedule(timerTask, date, j);
            }

            @Override // java.util.Timer
            public synchronized void scheduleAtFixedRate(TimerTask timerTask, long j, long j2) {
                if (this._cancelled) {
                    return;
                }
                super.scheduleAtFixedRate(timerTask, j, j2);
            }

            @Override // java.util.Timer
            public synchronized void scheduleAtFixedRate(TimerTask timerTask, Date date, long j) {
                if (this._cancelled) {
                    return;
                }
                super.scheduleAtFixedRate(timerTask, date, j);
            }
        }

        public DNSTaskStarterImpl(JmDNSImpl jmDNSImpl) {
            this._jmDNSImpl = jmDNSImpl;
            this._timer = new StarterTimer("JmDNS(" + jmDNSImpl.getName() + ").Timer", true);
            this._stateTimer = new StarterTimer("JmDNS(" + jmDNSImpl.getName() + ").State.Timer", false);
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void purgeTimer() {
            this._timer.purge();
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void purgeStateTimer() {
            this._stateTimer.purge();
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void cancelTimer() {
            this._timer.cancel();
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void cancelStateTimer() {
            this._stateTimer.cancel();
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void startProber() {
            new Prober(this._jmDNSImpl).start(this._stateTimer);
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void startAnnouncer() {
            new Announcer(this._jmDNSImpl).start(this._stateTimer);
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void startRenewer() {
            new Renewer(this._jmDNSImpl).start(this._stateTimer);
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void startCanceler() {
            new Canceler(this._jmDNSImpl).start(this._stateTimer);
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void startReaper() {
            new RecordReaper(this._jmDNSImpl).start(this._timer);
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void startServiceInfoResolver(ServiceInfoImpl serviceInfoImpl) {
            new ServiceInfoResolver(this._jmDNSImpl, serviceInfoImpl).start(this._timer);
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void startServiceResolver(String str) {
            new ServiceResolver(this._jmDNSImpl, str).start(this._timer);
        }

        @Override // javax.jmdns.impl.DNSTaskStarter
        public void startResponder(DNSIncoming dNSIncoming, InetAddress inetAddress, int i) {
            new Responder(this._jmDNSImpl, dNSIncoming, inetAddress, i).start(this._timer);
        }
    }
}
