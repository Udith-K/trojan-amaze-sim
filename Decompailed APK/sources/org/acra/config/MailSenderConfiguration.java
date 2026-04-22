package org.acra.config;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MailSenderConfiguration.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\u0018\u00002\u00020\u0001BE\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u0012\u0010\fR\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\b\u0010\u000f\u001a\u0004\b\u0014\u0010\u0011R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011¨\u0006\u0016"}, d2 = {"Lorg/acra/config/MailSenderConfiguration;", "Lorg/acra/config/Configuration;", "", "enabled", "", "mailTo", "reportAsFile", "reportFileName", "subject", "body", "<init>", "(ZLjava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "()Z", "Z", "getEnabled", "Ljava/lang/String;", "getMailTo", "()Ljava/lang/String;", "getReportAsFile", "getReportFileName", "getSubject", "getBody", "acra-mail_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MailSenderConfiguration implements Configuration {
    private final String body;
    private final boolean enabled;
    private final String mailTo;
    private final boolean reportAsFile;
    private final String reportFileName;
    private final String subject;

    public MailSenderConfiguration(boolean z, String mailTo, boolean z2, String reportFileName, String str, String str2) {
        Intrinsics.checkNotNullParameter(mailTo, "mailTo");
        Intrinsics.checkNotNullParameter(reportFileName, "reportFileName");
        this.enabled = z;
        this.mailTo = mailTo;
        this.reportAsFile = z2;
        this.reportFileName = reportFileName;
        this.subject = str;
        this.body = str2;
    }

    public final String getMailTo() {
        return this.mailTo;
    }

    public final boolean getReportAsFile() {
        return this.reportAsFile;
    }

    public /* synthetic */ MailSenderConfiguration(boolean z, String str, boolean z2, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, str, (i & 4) != 0 ? true : z2, (i & 8) != 0 ? "ACRA-report.stacktrace" : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4);
    }

    public final String getReportFileName() {
        return this.reportFileName;
    }

    public final String getSubject() {
        return this.subject;
    }

    public final String getBody() {
        return this.body;
    }

    @Override // org.acra.config.Configuration
    /* JADX INFO: renamed from: enabled, reason: from getter */
    public boolean getEnabled() {
        return this.enabled;
    }
}
