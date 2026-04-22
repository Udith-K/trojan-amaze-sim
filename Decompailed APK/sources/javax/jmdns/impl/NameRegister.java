package javax.jmdns.impl;

import ch.qos.logback.core.CoreConstants;
import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
public interface NameRegister {

    public enum NameType {
        HOST,
        SERVICE
    }

    String incrementName(InetAddress inetAddress, String str, NameType nameType);

    public static abstract class BaseRegister implements NameRegister {
        protected String incrementNameWithDash(String str) {
            StringBuilder sb = new StringBuilder(str.length() + 5);
            int iIndexOf = str.indexOf(".local.");
            int iLastIndexOf = str.lastIndexOf(45);
            int i = 1;
            if (iLastIndexOf < 0) {
                sb.append(str.substring(0, iIndexOf));
            } else {
                try {
                    int i2 = Integer.parseInt(str.substring(iLastIndexOf + 1, iIndexOf)) + 1;
                    sb.append(str.substring(0, iLastIndexOf));
                    i = i2;
                } catch (Exception unused) {
                    sb.append(str.substring(0, iIndexOf));
                }
            }
            sb.append(CoreConstants.DASH_CHAR);
            sb.append(i);
            sb.append(".local.");
            return sb.toString();
        }

        protected String incrementNameWithParentesis(String str) {
            StringBuilder sb = new StringBuilder(str.length() + 5);
            int iLastIndexOf = str.lastIndexOf(40);
            int iLastIndexOf2 = str.lastIndexOf(41);
            if (iLastIndexOf >= 0 && iLastIndexOf < iLastIndexOf2) {
                try {
                    sb.append(str.substring(0, iLastIndexOf));
                    sb.append(CoreConstants.LEFT_PARENTHESIS_CHAR);
                    sb.append(Integer.parseInt(str.substring(iLastIndexOf + 1, iLastIndexOf2)) + 1);
                    sb.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
                } catch (NumberFormatException unused) {
                    sb.setLength(0);
                    sb.append(str);
                    sb.append(" (2)");
                }
            } else {
                sb.append(str);
                sb.append(" (2)");
            }
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: javax.jmdns.impl.NameRegister$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$NameRegister$NameType;

        static {
            int[] iArr = new int[NameType.values().length];
            $SwitchMap$javax$jmdns$impl$NameRegister$NameType = iArr;
            try {
                iArr[NameType.HOST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$NameRegister$NameType[NameType.SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class UniqueNamePerInterface extends BaseRegister {
        private final ConcurrentMap _hostNames = new ConcurrentHashMap();
        private final ConcurrentMap _serviceNames = new ConcurrentHashMap();

        @Override // javax.jmdns.impl.NameRegister
        public String incrementName(InetAddress inetAddress, String str, NameType nameType) {
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
            if (i != 1) {
                return i != 2 ? str : incrementNameWithParentesis(str);
            }
            return incrementNameWithDash(str);
        }
    }

    public static class Factory {
        private static volatile NameRegister _register;

        public static NameRegister getRegistry() {
            if (_register == null) {
                _register = new UniqueNamePerInterface();
            }
            return _register;
        }
    }
}
