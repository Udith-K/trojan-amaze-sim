package org.fdroid.repo;

import android.net.Uri;
import ch.qos.logback.core.joran.action.Action;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;

/* JADX INFO: compiled from: RepoUriGetter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0016\u0010\r\u001a\u0004\u0018\u00010\u0007*\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0007H\u0002¨\u0006\u0010"}, d2 = {"Lorg/fdroid/repo/RepoUriGetter;", "", "<init>", "()V", "getUri", "Lorg/fdroid/repo/RepoUriGetter$NormalizedUri;", "url", "", "isSwapUri", "", "uri", "Landroid/net/Uri;", "getFdroidLinkUri", "getQueryParameterOrNull", Action.KEY_ATTRIBUTE, "NormalizedUri", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RepoUriGetter {
    public static final RepoUriGetter INSTANCE = new RepoUriGetter();

    private RepoUriGetter() {
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x017b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final org.fdroid.repo.RepoUriGetter.NormalizedUri getUri(java.lang.String r14) {
        /*
            Method dump skipped, instruction units count: 480
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.repo.RepoUriGetter.getUri(java.lang.String):org.fdroid.repo.RepoUriGetter$NormalizedUri");
    }

    public final boolean isSwapUri(Uri uri) {
        String lowerCase;
        Intrinsics.checkNotNullParameter(uri, "uri");
        String queryParameterOrNull = getQueryParameterOrNull(uri, "swap");
        if (queryParameterOrNull == null) {
            queryParameterOrNull = getQueryParameterOrNull(uri, "SWAP");
        }
        if (queryParameterOrNull != null) {
            String scheme = uri.getScheme();
            if (scheme != null) {
                lowerCase = scheme.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            } else {
                lowerCase = null;
            }
            if (Intrinsics.areEqual(lowerCase, "http")) {
                return true;
            }
        }
        return false;
    }

    private final Uri getFdroidLinkUri(Uri uri) {
        Uri uri2 = Uri.parse(uri.getEncodedFragment());
        Intrinsics.checkNotNullExpressionValue(uri2, "parse(...)");
        return uri2;
    }

    private final String getQueryParameterOrNull(Uri uri, String str) {
        try {
            return uri.getQueryParameter(str);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: compiled from: RepoUriGetter.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J7\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001b"}, d2 = {"Lorg/fdroid/repo/RepoUriGetter$NormalizedUri;", "", "uri", "Landroid/net/Uri;", BonjourPeer.FINGERPRINT, "", "username", "password", "<init>", "(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getUri", "()Landroid/net/Uri;", "getFingerprint", "()Ljava/lang/String;", "getUsername", "getPassword", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class NormalizedUri {
        private final String fingerprint;
        private final String password;
        private final Uri uri;
        private final String username;

        public static /* synthetic */ NormalizedUri copy$default(NormalizedUri normalizedUri, Uri uri, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                uri = normalizedUri.uri;
            }
            if ((i & 2) != 0) {
                str = normalizedUri.fingerprint;
            }
            if ((i & 4) != 0) {
                str2 = normalizedUri.username;
            }
            if ((i & 8) != 0) {
                str3 = normalizedUri.password;
            }
            return normalizedUri.copy(uri, str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Uri getUri() {
            return this.uri;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFingerprint() {
            return this.fingerprint;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getUsername() {
            return this.username;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPassword() {
            return this.password;
        }

        public final NormalizedUri copy(Uri uri, String fingerprint, String username, String password) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            return new NormalizedUri(uri, fingerprint, username, password);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NormalizedUri)) {
                return false;
            }
            NormalizedUri normalizedUri = (NormalizedUri) other;
            return Intrinsics.areEqual(this.uri, normalizedUri.uri) && Intrinsics.areEqual(this.fingerprint, normalizedUri.fingerprint) && Intrinsics.areEqual(this.username, normalizedUri.username) && Intrinsics.areEqual(this.password, normalizedUri.password);
        }

        public int hashCode() {
            int iHashCode = this.uri.hashCode() * 31;
            String str = this.fingerprint;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.username;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.password;
            return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "NormalizedUri(uri=" + this.uri + ", fingerprint=" + this.fingerprint + ", username=" + this.username + ", password=" + this.password + ")";
        }

        public NormalizedUri(Uri uri, String str, String str2, String str3) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            this.uri = uri;
            this.fingerprint = str;
            this.username = str2;
            this.password = str3;
        }

        public /* synthetic */ NormalizedUri(Uri uri, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(uri, str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3);
        }

        public final Uri getUri() {
            return this.uri;
        }

        public final String getFingerprint() {
            return this.fingerprint;
        }

        public final String getUsername() {
            return this.username;
        }

        public final String getPassword() {
            return this.password;
        }
    }
}
