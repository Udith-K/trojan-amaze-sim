package org.acra.util;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;

/* JADX INFO: compiled from: IOUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class IOUtils {
    public static final IOUtils INSTANCE = new IOUtils();

    private IOUtils() {
    }

    public static final void safeClose(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    public static final void deleteFile(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (file.delete()) {
            return;
        }
        ACRA.log.w(ACRA.LOG_TAG, "Could not delete file: " + file);
    }

    public static final void writeStringToFile(File file, String content) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(content, "content");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        try {
            outputStreamWriter.write(content);
            outputStreamWriter.flush();
        } finally {
            safeClose(outputStreamWriter);
        }
    }

    public final String serialize(Serializable serializable) {
        Intrinsics.checkNotNullParameter(serializable, "serializable");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(serializable);
                String strEncodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
                CloseableKt.closeFinally(objectOutputStream, null);
                return strEncodeToString;
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final Serializable deserialize(Class clazz, String str) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        if (str != null) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(str, 0)));
                try {
                    Object object = objectInputStream.readObject();
                    if (clazz.isInstance(object)) {
                        Serializable serializable = (Serializable) clazz.cast(object);
                        CloseableKt.closeFinally(objectInputStream, null);
                        return serializable;
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(objectInputStream, null);
                } finally {
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
