package org.apache.commons.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.output.StringBuilderWriter;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes2.dex */
public abstract class IOUtils {
    public static final char DIR_SEPARATOR = File.separatorChar;
    public static final String LINE_SEPARATOR;

    static {
        StringBuilderWriter stringBuilderWriter = new StringBuilderWriter(4);
        try {
            PrintWriter printWriter = new PrintWriter(stringBuilderWriter);
            try {
                printWriter.println();
                LINE_SEPARATOR = stringBuilderWriter.toString();
                printWriter.close();
                stringBuilderWriter.close();
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    stringBuilderWriter.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static void closeQuietly(InputStream inputStream) {
        closeQuietly((Closeable) inputStream);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static BufferedReader toBufferedReader(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    public static String toString(InputStream inputStream, Charset charset) {
        StringBuilderWriter stringBuilderWriter = new StringBuilderWriter();
        try {
            copy(inputStream, stringBuilderWriter, charset);
            String string = stringBuilderWriter.toString();
            stringBuilderWriter.close();
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    stringBuilderWriter.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static List readLines(InputStream inputStream, Charset charset) {
        return readLines(new InputStreamReader(inputStream, Charsets.toCharset(charset)));
    }

    public static List readLines(Reader reader) throws IOException {
        BufferedReader bufferedReader = toBufferedReader(reader);
        ArrayList arrayList = new ArrayList();
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            arrayList.add(line);
        }
        return arrayList;
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) {
        long jCopyLarge = copyLarge(inputStream, outputStream);
        if (jCopyLarge > 2147483647L) {
            return -1;
        }
        return (int) jCopyLarge;
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, int i) {
        return copyLarge(inputStream, outputStream, new byte[i]);
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream) {
        return copy(inputStream, outputStream, PKIFailureInfo.certConfirmed);
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                return j;
            }
            outputStream.write(bArr, 0, i);
            j += (long) i;
        }
    }

    public static void copy(InputStream inputStream, Writer writer, Charset charset) {
        copy(new InputStreamReader(inputStream, Charsets.toCharset(charset)), writer);
    }

    public static int copy(Reader reader, Writer writer) {
        long jCopyLarge = copyLarge(reader, writer);
        if (jCopyLarge > 2147483647L) {
            return -1;
        }
        return (int) jCopyLarge;
    }

    public static long copyLarge(Reader reader, Writer writer) {
        return copyLarge(reader, writer, new char[PKIFailureInfo.certConfirmed]);
    }

    public static long copyLarge(Reader reader, Writer writer, char[] cArr) throws IOException {
        long j = 0;
        while (true) {
            int i = reader.read(cArr);
            if (-1 == i) {
                return j;
            }
            writer.write(cArr, 0, i);
            j += (long) i;
        }
    }
}
