package mu;

import kotlin.jvm.functions.Function0;
import org.slf4j.Logger;

/* JADX INFO: compiled from: KLogger.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KLogger extends Logger {
    void debug(Function0 function0);

    void error(Throwable th, Function0 function0);

    void error(Function0 function0);

    void info(Function0 function0);

    void warn(Throwable th, Function0 function0);

    void warn(Function0 function0);
}
