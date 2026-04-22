package org.acra.startup;

import java.io.File;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Report.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class Report {
    private boolean approve;
    private final boolean approved;
    private boolean delete;
    private final File file;

    public Report(File file, boolean z) {
        Intrinsics.checkNotNullParameter(file, "file");
        this.file = file;
        this.approved = z;
    }

    public final boolean getApproved() {
        return this.approved;
    }

    public final File getFile() {
        return this.file;
    }

    public final boolean getDelete() {
        return this.delete;
    }

    public final void setDelete(boolean z) {
        this.delete = z;
    }

    public final boolean getApprove() {
        return this.approve;
    }

    public final void setApprove(boolean z) {
        this.approve = z;
    }
}
