package javax.jmdns.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import javax.jmdns.impl.constants.DNSConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
class SocketListener extends Thread {
    static Logger logger = LoggerFactory.getLogger(SocketListener.class.getName());
    private final JmDNSImpl _jmDNSImpl;

    SocketListener(JmDNSImpl jmDNSImpl) {
        StringBuilder sb = new StringBuilder();
        sb.append("SocketListener(");
        sb.append(jmDNSImpl != null ? jmDNSImpl.getName() : "");
        sb.append(")");
        super(sb.toString());
        setDaemon(true);
        this._jmDNSImpl = jmDNSImpl;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            DatagramPacket datagramPacket = new DatagramPacket(new byte[8972], 8972);
            while (!this._jmDNSImpl.isCanceling() && !this._jmDNSImpl.isCanceled()) {
                datagramPacket.setLength(8972);
                this._jmDNSImpl.getSocket().receive(datagramPacket);
                if (this._jmDNSImpl.isCanceling() || this._jmDNSImpl.isCanceled() || this._jmDNSImpl.isClosing() || this._jmDNSImpl.isClosed()) {
                    break;
                }
                try {
                    if (!this._jmDNSImpl.getLocalHost().shouldIgnorePacket(datagramPacket)) {
                        DNSIncoming dNSIncoming = new DNSIncoming(datagramPacket);
                        if (dNSIncoming.isValidResponseCode()) {
                            if (logger.isTraceEnabled()) {
                                logger.trace("{}.run() JmDNS in:{}", getName(), dNSIncoming.print(true));
                            }
                            if (dNSIncoming.isQuery()) {
                                int port = datagramPacket.getPort();
                                int i = DNSConstants.MDNS_PORT;
                                if (port != i) {
                                    this._jmDNSImpl.handleQuery(dNSIncoming, datagramPacket.getAddress(), datagramPacket.getPort());
                                }
                                JmDNSImpl jmDNSImpl = this._jmDNSImpl;
                                jmDNSImpl.handleQuery(dNSIncoming, jmDNSImpl.getGroup(), i);
                            } else {
                                this._jmDNSImpl.handleResponse(dNSIncoming);
                            }
                        } else if (logger.isDebugEnabled()) {
                            logger.debug("{}.run() JmDNS in message with error code: {}", getName(), dNSIncoming.print(true));
                        }
                    }
                } catch (IOException e) {
                    logger.warn(getName() + ".run() exception ", (Throwable) e);
                }
            }
        } catch (IOException e2) {
            if (!this._jmDNSImpl.isCanceling() && !this._jmDNSImpl.isCanceled() && !this._jmDNSImpl.isClosing() && !this._jmDNSImpl.isClosed()) {
                logger.warn(getName() + ".run() exception ", (Throwable) e2);
                this._jmDNSImpl.recover();
            }
        }
        logger.trace("{}.run() exiting.", getName());
    }
}
