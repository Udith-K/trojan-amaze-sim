package org.acra.sender;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;
import org.acra.util.IOUtils;

/* JADX INFO: compiled from: LegacySenderService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\r"}, d2 = {"Lorg/acra/sender/LegacySenderService;", "Landroid/app/Service;", "<init>", "()V", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "onBind", "Landroid/os/IBinder;", "Companion", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LegacySenderService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (!intent.hasExtra("acraConfig")) {
            if (!ACRA.DEV_LOGGING) {
                return 3;
            }
            ACRA.log.d(ACRA.LOG_TAG, "SenderService was started but no valid intent was delivered, will now quit");
            return 3;
        }
        final CoreConfiguration coreConfiguration = (CoreConfiguration) IOUtils.INSTANCE.deserialize(CoreConfiguration.class, intent.getStringExtra("acraConfig"));
        if (coreConfiguration == null) {
            return 3;
        }
        new Thread(new Runnable() { // from class: org.acra.sender.LegacySenderService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LegacySenderService.onStartCommand$lambda$0(this.f$0, coreConfiguration, intent);
            }
        }).start();
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStartCommand$lambda$0(LegacySenderService legacySenderService, CoreConfiguration coreConfiguration, Intent intent) {
        SendingConductor sendingConductor = new SendingConductor(legacySenderService, coreConfiguration);
        Bundle extras = intent.getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        sendingConductor.sendReports(false, extras);
        legacySenderService.stopSelf();
    }
}
