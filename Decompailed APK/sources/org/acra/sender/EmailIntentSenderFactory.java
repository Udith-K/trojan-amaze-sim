package org.acra.sender;

import android.content.Context;
import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.acra.config.CoreConfiguration;
import org.acra.config.MailSenderConfiguration;
import org.acra.plugins.HasConfigPlugin;

/* JADX INFO: compiled from: EmailIntentSenderFactory.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lorg/acra/sender/EmailIntentSenderFactory;", "Lorg/acra/plugins/HasConfigPlugin;", "Lorg/acra/sender/ReportSenderFactory;", "<init>", "()V", "create", "Lorg/acra/sender/ReportSender;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "acra-mail_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class EmailIntentSenderFactory extends HasConfigPlugin implements ReportSenderFactory {
    public EmailIntentSenderFactory() {
        super(MailSenderConfiguration.class);
    }

    @Override // org.acra.sender.ReportSenderFactory
    public ReportSender create(Context context, CoreConfiguration config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        return new EmailIntentSender(config);
    }
}
