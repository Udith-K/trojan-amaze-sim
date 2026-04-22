package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import ch.qos.logback.core.CoreConstants;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: loaded from: classes2.dex */
public enum JvmPrimitiveType {
    BOOLEAN(PrimitiveType.BOOLEAN, "boolean", "Z", "java.lang.Boolean"),
    CHAR(PrimitiveType.CHAR, "char", "C", "java.lang.Character"),
    BYTE(PrimitiveType.BYTE, "byte", "B", "java.lang.Byte"),
    SHORT(PrimitiveType.SHORT, "short", "S", "java.lang.Short"),
    INT(PrimitiveType.INT, "int", "I", "java.lang.Integer"),
    FLOAT(PrimitiveType.FLOAT, "float", "F", "java.lang.Float"),
    LONG(PrimitiveType.LONG, "long", "J", "java.lang.Long"),
    DOUBLE(PrimitiveType.DOUBLE, "double", "D", "java.lang.Double");

    private final String desc;
    private final String name;
    private final PrimitiveType primitiveType;
    private final FqName wrapperFqName;
    private static final Map TYPE_BY_NAME = new HashMap();
    private static final Map TYPE_BY_PRIMITIVE_TYPE = new EnumMap(PrimitiveType.class);
    private static final Map TYPE_BY_DESC = new HashMap();
    private static final Set WRAPPER_CLASS_INTERNAL_NAMES = new HashSet();
    private static final Map OWNER_TO_BOXING_METHOD_DESCRIPTOR = new HashMap();

    /* JADX WARN: Removed duplicated region for block: B:7:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static /* synthetic */ void $$$reportNull$$$0(int r8) {
        /*
            Method dump skipped, instruction units count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType.$$$reportNull$$$0(int):void");
    }

    static {
        for (JvmPrimitiveType jvmPrimitiveType : values()) {
            TYPE_BY_NAME.put(jvmPrimitiveType.getJavaKeywordName(), jvmPrimitiveType);
            TYPE_BY_PRIMITIVE_TYPE.put(jvmPrimitiveType.getPrimitiveType(), jvmPrimitiveType);
            TYPE_BY_DESC.put(jvmPrimitiveType.getDesc(), jvmPrimitiveType);
            String strReplace = jvmPrimitiveType.wrapperFqName.asString().replace(CoreConstants.DOT, '/');
            WRAPPER_CLASS_INTERNAL_NAMES.add(strReplace);
            OWNER_TO_BOXING_METHOD_DESCRIPTOR.put(strReplace, "(" + jvmPrimitiveType.desc + ")L" + strReplace + ";");
        }
    }

    public static JvmPrimitiveType get(String str) {
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        JvmPrimitiveType jvmPrimitiveType = (JvmPrimitiveType) TYPE_BY_NAME.get(str);
        if (jvmPrimitiveType != null) {
            return jvmPrimitiveType;
        }
        throw new AssertionError("Non-primitive type name passed: " + str);
    }

    public static JvmPrimitiveType get(PrimitiveType primitiveType) {
        if (primitiveType == null) {
            $$$reportNull$$$0(5);
        }
        JvmPrimitiveType jvmPrimitiveType = (JvmPrimitiveType) TYPE_BY_PRIMITIVE_TYPE.get(primitiveType);
        if (jvmPrimitiveType == null) {
            $$$reportNull$$$0(6);
        }
        return jvmPrimitiveType;
    }

    JvmPrimitiveType(PrimitiveType primitiveType, String str, String str2, String str3) {
        if (primitiveType == null) {
            $$$reportNull$$$0(8);
        }
        if (str == null) {
            $$$reportNull$$$0(9);
        }
        if (str2 == null) {
            $$$reportNull$$$0(10);
        }
        if (str3 == null) {
            $$$reportNull$$$0(11);
        }
        this.primitiveType = primitiveType;
        this.name = str;
        this.desc = str2;
        this.wrapperFqName = new FqName(str3);
    }

    public PrimitiveType getPrimitiveType() {
        PrimitiveType primitiveType = this.primitiveType;
        if (primitiveType == null) {
            $$$reportNull$$$0(12);
        }
        return primitiveType;
    }

    public String getJavaKeywordName() {
        String str = this.name;
        if (str == null) {
            $$$reportNull$$$0(13);
        }
        return str;
    }

    public String getDesc() {
        String str = this.desc;
        if (str == null) {
            $$$reportNull$$$0(14);
        }
        return str;
    }

    public FqName getWrapperFqName() {
        FqName fqName = this.wrapperFqName;
        if (fqName == null) {
            $$$reportNull$$$0(15);
        }
        return fqName;
    }
}
