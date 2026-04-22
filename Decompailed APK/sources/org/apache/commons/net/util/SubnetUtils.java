package org.apache.commons.net.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: loaded from: classes2.dex */
public class SubnetUtils {
    private static final Pattern addressPattern = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})");
    private static final Pattern cidrPattern = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})/(\\d{1,3})");
    private int netmask = 0;
    private int address = 0;
    private int network = 0;
    private int broadcast = 0;
    private boolean inclusiveHostCount = false;

    int pop(int i) {
        int i2 = i - ((i >>> 1) & 1431655765);
        int i3 = (i2 & 858993459) + ((i2 >>> 2) & 858993459);
        int i4 = 252645135 & (i3 + (i3 >>> 4));
        int i5 = i4 + (i4 >>> 8);
        return (i5 + (i5 >>> 16)) & 63;
    }

    public SubnetUtils(String str) {
        calculate(str);
    }

    public SubnetUtils(String str, String str2) {
        calculate(toCidrNotation(str, str2));
    }

    public boolean isInclusiveHostCount() {
        return this.inclusiveHostCount;
    }

    public final class SubnetInfo {
        private SubnetInfo() {
        }

        private int netmask() {
            return SubnetUtils.this.netmask;
        }

        private int network() {
            return SubnetUtils.this.network;
        }

        private int address() {
            return SubnetUtils.this.address;
        }

        private int broadcast() {
            return SubnetUtils.this.broadcast;
        }

        private long networkLong() {
            return ((long) SubnetUtils.this.network) & BodyPartID.bodyIdMax;
        }

        private long broadcastLong() {
            return ((long) SubnetUtils.this.broadcast) & BodyPartID.bodyIdMax;
        }

        private int low() {
            if (SubnetUtils.this.isInclusiveHostCount()) {
                return network();
            }
            if (broadcastLong() - networkLong() > 1) {
                return network() + 1;
            }
            return 0;
        }

        private int high() {
            if (SubnetUtils.this.isInclusiveHostCount()) {
                return broadcast();
            }
            if (broadcastLong() - networkLong() > 1) {
                return broadcast() - 1;
            }
            return 0;
        }

        public boolean isInRange(String str) {
            return isInRange(SubnetUtils.this.toInteger(str));
        }

        public boolean isInRange(int i) {
            long j = ((long) i) & BodyPartID.bodyIdMax;
            return j >= (((long) low()) & BodyPartID.bodyIdMax) && j <= (BodyPartID.bodyIdMax & ((long) high()));
        }

        public String getBroadcastAddress() {
            SubnetUtils subnetUtils = SubnetUtils.this;
            return subnetUtils.format(subnetUtils.toArray(broadcast()));
        }

        public String getNetworkAddress() {
            SubnetUtils subnetUtils = SubnetUtils.this;
            return subnetUtils.format(subnetUtils.toArray(network()));
        }

        public String getNetmask() {
            SubnetUtils subnetUtils = SubnetUtils.this;
            return subnetUtils.format(subnetUtils.toArray(netmask()));
        }

        public String getLowAddress() {
            SubnetUtils subnetUtils = SubnetUtils.this;
            return subnetUtils.format(subnetUtils.toArray(low()));
        }

        public String getHighAddress() {
            SubnetUtils subnetUtils = SubnetUtils.this;
            return subnetUtils.format(subnetUtils.toArray(high()));
        }

        public int getAddressCount() {
            long addressCountLong = getAddressCountLong();
            if (addressCountLong <= 2147483647L) {
                return (int) addressCountLong;
            }
            throw new RuntimeException("Count is larger than an integer: " + addressCountLong);
        }

        public long getAddressCountLong() {
            long jBroadcastLong = (broadcastLong() - networkLong()) + ((long) (SubnetUtils.this.isInclusiveHostCount() ? 1 : -1));
            if (jBroadcastLong < 0) {
                return 0L;
            }
            return jBroadcastLong;
        }

        public String getCidrSignature() {
            SubnetUtils subnetUtils = SubnetUtils.this;
            String str = subnetUtils.format(subnetUtils.toArray(address()));
            SubnetUtils subnetUtils2 = SubnetUtils.this;
            return subnetUtils.toCidrNotation(str, subnetUtils2.format(subnetUtils2.toArray(netmask())));
        }

        public String toString() {
            return "CIDR Signature:\t[" + getCidrSignature() + "] Netmask: [" + getNetmask() + "]\nNetwork:\t[" + getNetworkAddress() + "]\nBroadcast:\t[" + getBroadcastAddress() + "]\nFirst Address:\t[" + getLowAddress() + "]\nLast Address:\t[" + getHighAddress() + "]\n# Addresses:\t[" + getAddressCount() + "]\n";
        }
    }

    public final SubnetInfo getInfo() {
        return new SubnetInfo();
    }

    private void calculate(String str) {
        Matcher matcher = cidrPattern.matcher(str);
        if (matcher.matches()) {
            this.address = matchAddress(matcher);
            int iRangeCheck = rangeCheck(Integer.parseInt(matcher.group(5)), 0, 32);
            for (int i = 0; i < iRangeCheck; i++) {
                this.netmask |= 1 << (31 - i);
            }
            int i2 = this.address;
            int i3 = this.netmask;
            int i4 = i2 & i3;
            this.network = i4;
            this.broadcast = i4 | (~i3);
            return;
        }
        throw new IllegalArgumentException("Could not parse [" + str + "]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int toInteger(String str) {
        Matcher matcher = addressPattern.matcher(str);
        if (matcher.matches()) {
            return matchAddress(matcher);
        }
        throw new IllegalArgumentException("Could not parse [" + str + "]");
    }

    private int matchAddress(Matcher matcher) {
        int iRangeCheck = 0;
        for (int i = 1; i <= 4; i++) {
            iRangeCheck |= (rangeCheck(Integer.parseInt(matcher.group(i)), 0, GF2Field.MASK) & GF2Field.MASK) << ((4 - i) * 8);
        }
        return iRangeCheck;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] toArray(int i) {
        int[] iArr = new int[4];
        for (int i2 = 3; i2 >= 0; i2--) {
            iArr[i2] = iArr[i2] | ((i >>> ((3 - i2) * 8)) & GF2Field.MASK);
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String format(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iArr.length; i++) {
            sb.append(iArr[i]);
            if (i != iArr.length - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private int rangeCheck(int i, int i2, int i3) {
        if (i >= i2 && i <= i3) {
            return i;
        }
        throw new IllegalArgumentException("Value [" + i + "] not in range [" + i2 + "," + i3 + "]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String toCidrNotation(String str, String str2) {
        return str + "/" + pop(toInteger(str2));
    }
}
