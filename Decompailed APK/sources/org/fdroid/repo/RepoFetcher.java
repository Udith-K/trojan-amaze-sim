package org.fdroid.repo;

import android.net.Uri;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.serialization.SerializationException;
import org.fdroid.database.Repository;
import org.fdroid.download.NotFoundException;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.index.SigningException;

/* JADX INFO: compiled from: RepoFetcher.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bà\u0080\u0001\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH¦@¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/fdroid/repo/RepoFetcher;", "", "fetchRepo", "", "uri", "Landroid/net/Uri;", "repo", "Lorg/fdroid/database/Repository;", "receiver", "Lorg/fdroid/repo/RepoPreviewReceiver;", BonjourPeer.FINGERPRINT, "", "(Landroid/net/Uri;Lorg/fdroid/database/Repository;Lorg/fdroid/repo/RepoPreviewReceiver;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface RepoFetcher {
    Object fetchRepo(Uri uri, Repository repository, RepoPreviewReceiver repoPreviewReceiver, String str, Continuation continuation) throws SerializationException, IOException, SigningException, NotFoundException;
}
