package kotlinx.coroutines.flow;

import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: SharingStarted.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface SharingStarted {
    public static final Companion Companion = Companion.$$INSTANCE;

    Flow command(StateFlow stateFlow);

    /* JADX INFO: compiled from: SharingStarted.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final SharingStarted Eagerly = new StartedEagerly();
        private static final SharingStarted Lazily = new StartedLazily();

        private Companion() {
        }

        public final SharingStarted getEagerly() {
            return Eagerly;
        }

        public final SharingStarted getLazily() {
            return Lazily;
        }

        public static /* synthetic */ SharingStarted WhileSubscribed$default(Companion companion, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = 0;
            }
            if ((i & 2) != 0) {
                j2 = Preferences.UPDATE_INTERVAL_DISABLED;
            }
            return companion.WhileSubscribed(j, j2);
        }

        public final SharingStarted WhileSubscribed(long j, long j2) {
            return new StartedWhileSubscribed(j, j2);
        }
    }
}
