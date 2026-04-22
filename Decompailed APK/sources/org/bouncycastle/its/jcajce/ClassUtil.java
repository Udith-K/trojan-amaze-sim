package org.bouncycastle.its.jcajce;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;
import org.bouncycastle.util.Integers;

/* JADX INFO: loaded from: classes2.dex */
class ClassUtil {
    private static final Class gcmSpecClass = loadClass(ClassUtil.class, "javax.crypto.spec.GCMParameterSpec");

    ClassUtil() {
    }

    public static AlgorithmParameterSpec getGCMSpec(final byte[] bArr, final int i) {
        if (gcmSpecClass != null) {
            try {
                return (AlgorithmParameterSpec) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.its.jcajce.ClassUtil.1
                    @Override // java.security.PrivilegedAction
                    public Object run() {
                        try {
                            return ClassUtil.gcmSpecClass.getConstructor(Integer.TYPE, byte[].class).newInstance(Integers.valueOf(i), bArr);
                        } catch (NoSuchMethodException e) {
                            throw new IllegalStateException("no matching constructor: " + e.getMessage());
                        } catch (Exception e2) {
                            throw new IllegalStateException("constructor failed" + e2.getMessage());
                        }
                    }
                });
            } catch (IllegalStateException unused) {
            }
        }
        return new AEADParameterSpec(bArr, i);
    }

    static Class loadClass(Class cls, final String str) {
        try {
            ClassLoader classLoader = cls.getClassLoader();
            return classLoader != null ? classLoader.loadClass(str) : (Class) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.its.jcajce.ClassUtil.2
                @Override // java.security.PrivilegedAction
                public Object run() {
                    try {
                        return Class.forName(str);
                    } catch (Exception unused) {
                        return null;
                    }
                }
            });
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
