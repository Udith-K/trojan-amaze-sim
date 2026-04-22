package org.acra.attachment;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;

/* JADX INFO: compiled from: DefaultAttachmentProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class DefaultAttachmentProvider implements AttachmentUriProvider {
    @Override // org.acra.attachment.AttachmentUriProvider
    public List getAttachments(Context context, CoreConfiguration configuration) {
        Uri uri;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        List<String> attachmentUris = configuration.getAttachmentUris();
        ArrayList arrayList = new ArrayList();
        for (String str : attachmentUris) {
            try {
                uri = Uri.parse(str);
            } catch (Exception e) {
                ACRA.log.e(ACRA.LOG_TAG, "Failed to parse Uri " + str, e);
                uri = null;
            }
            if (uri != null) {
                arrayList.add(uri);
            }
        }
        return arrayList;
    }
}
