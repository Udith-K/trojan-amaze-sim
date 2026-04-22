package androidx.compose.ui.platform;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: AndroidUriHandler.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidUriHandler implements UriHandler {
    private final Context context;

    public AndroidUriHandler(Context context) {
        this.context = context;
    }

    @Override // androidx.compose.ui.platform.UriHandler
    public void openUri(String str) {
        try {
            this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (ActivityNotFoundException e) {
            throw new IllegalArgumentException("Can't open " + str + CoreConstants.DOT, e);
        }
    }
}
