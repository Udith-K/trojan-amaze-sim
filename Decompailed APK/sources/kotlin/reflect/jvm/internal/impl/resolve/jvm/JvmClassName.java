package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import ch.qos.logback.core.CoreConstants;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: loaded from: classes2.dex */
public class JvmClassName {
    private FqName fqName;
    private final String internalName;

    /* JADX WARN: Removed duplicated region for block: B:7:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static /* synthetic */ void $$$reportNull$$$0(int r9) {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName.$$$reportNull$$$0(int):void");
    }

    public static JvmClassName byInternalName(String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        return new JvmClassName(str);
    }

    public static JvmClassName byClassId(ClassId classId) {
        if (classId == null) {
            $$$reportNull$$$0(1);
        }
        return new JvmClassName(internalNameByClassId(classId));
    }

    public static String internalNameByClassId(ClassId classId) {
        if (classId == null) {
            $$$reportNull$$$0(2);
        }
        FqName packageFqName = classId.getPackageFqName();
        String strReplace = classId.getRelativeClassName().asString().replace(CoreConstants.DOT, CoreConstants.DOLLAR);
        if (!packageFqName.isRoot()) {
            strReplace = packageFqName.asString().replace(CoreConstants.DOT, '/') + "/" + strReplace;
        }
        if (strReplace == null) {
            $$$reportNull$$$0(3);
        }
        return strReplace;
    }

    public static JvmClassName byFqNameWithoutInnerClasses(FqName fqName) {
        if (fqName == null) {
            $$$reportNull$$$0(4);
        }
        JvmClassName jvmClassName = new JvmClassName(fqName.asString().replace(CoreConstants.DOT, '/'));
        jvmClassName.fqName = fqName;
        return jvmClassName;
    }

    private JvmClassName(String str) {
        if (str == null) {
            $$$reportNull$$$0(7);
        }
        this.internalName = str;
    }

    public FqName getFqNameForTopLevelClassMaybeWithDollars() {
        return new FqName(this.internalName.replace('/', CoreConstants.DOT));
    }

    public FqName getPackageFqName() {
        int iLastIndexOf = this.internalName.lastIndexOf("/");
        if (iLastIndexOf == -1) {
            FqName fqName = FqName.ROOT;
            if (fqName == null) {
                $$$reportNull$$$0(9);
            }
            return fqName;
        }
        return new FqName(this.internalName.substring(0, iLastIndexOf).replace('/', CoreConstants.DOT));
    }

    public String getInternalName() {
        String str = this.internalName;
        if (str == null) {
            $$$reportNull$$$0(10);
        }
        return str;
    }

    public String toString() {
        return this.internalName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.internalName.equals(((JvmClassName) obj).internalName);
    }

    public int hashCode() {
        return this.internalName.hashCode();
    }
}
