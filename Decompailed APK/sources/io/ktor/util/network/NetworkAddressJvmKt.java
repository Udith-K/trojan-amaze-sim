package io.ktor.util.network;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkAddressJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class NetworkAddressJvmKt {
    public static final int getPort(SocketAddress socketAddress) {
        Intrinsics.checkNotNullParameter(socketAddress, "<this>");
        InetSocketAddress inetSocketAddress = socketAddress instanceof InetSocketAddress ? (InetSocketAddress) socketAddress : null;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getPort();
        }
        return 0;
    }
}
