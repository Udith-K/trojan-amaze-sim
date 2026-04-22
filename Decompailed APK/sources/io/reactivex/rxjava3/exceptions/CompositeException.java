package io.reactivex.rxjava3.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class CompositeException extends RuntimeException {
    private Throwable cause;
    private final List exceptions;
    private final String message;

    public CompositeException(Throwable... thArr) {
        this(thArr == null ? Collections.singletonList(new NullPointerException("exceptions was null")) : Arrays.asList(thArr));
    }

    public CompositeException(Iterable iterable) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (iterable != null) {
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                Throwable th = (Throwable) it.next();
                if (th instanceof CompositeException) {
                    linkedHashSet.addAll(((CompositeException) th).getExceptions());
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException("Throwable was null!"));
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException("errors was null"));
        }
        if (linkedHashSet.isEmpty()) {
            throw new IllegalArgumentException("errors is empty");
        }
        List listUnmodifiableList = Collections.unmodifiableList(new ArrayList(linkedHashSet));
        this.exceptions = listUnmodifiableList;
        this.message = listUnmodifiableList.size() + " exceptions occurred. ";
    }

    public List getExceptions() {
        return this.exceptions;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        int i;
        try {
            if (this.cause == null) {
                String property = System.getProperty("line.separator");
                if (this.exceptions.size() > 1) {
                    IdentityHashMap identityHashMap = new IdentityHashMap();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Multiple exceptions (");
                    sb.append(this.exceptions.size());
                    sb.append(")");
                    sb.append(property);
                    for (Throwable cause : this.exceptions) {
                        int i2 = 0;
                        while (true) {
                            if (cause != null) {
                                for (int i3 = 0; i3 < i2; i3++) {
                                    sb.append("  ");
                                }
                                sb.append("|-- ");
                                sb.append(cause.getClass().getCanonicalName());
                                sb.append(": ");
                                String message = cause.getMessage();
                                if (message != null && message.contains(property)) {
                                    sb.append(property);
                                    for (String str : message.split(property)) {
                                        for (int i4 = 0; i4 < i2 + 2; i4++) {
                                            sb.append("  ");
                                        }
                                        sb.append(str);
                                        sb.append(property);
                                    }
                                } else {
                                    sb.append(message);
                                    sb.append(property);
                                }
                                int i5 = 0;
                                while (true) {
                                    i = i2 + 2;
                                    if (i5 >= i) {
                                        break;
                                    }
                                    sb.append("  ");
                                    i5++;
                                }
                                StackTraceElement[] stackTrace = cause.getStackTrace();
                                if (stackTrace.length > 0) {
                                    sb.append("at ");
                                    sb.append(stackTrace[0]);
                                    sb.append(property);
                                }
                                if (!identityHashMap.containsKey(cause)) {
                                    identityHashMap.put(cause, Boolean.TRUE);
                                    cause = cause.getCause();
                                    i2++;
                                } else {
                                    Throwable cause2 = cause.getCause();
                                    if (cause2 != null) {
                                        for (int i6 = 0; i6 < i; i6++) {
                                            sb.append("  ");
                                        }
                                        sb.append("|-- ");
                                        sb.append("(cause not expanded again) ");
                                        sb.append(cause2.getClass().getCanonicalName());
                                        sb.append(": ");
                                        sb.append(cause2.getMessage());
                                        sb.append(property);
                                    }
                                }
                            }
                        }
                    }
                    this.cause = new ExceptionOverview(sb.toString().trim());
                } else {
                    this.cause = (Throwable) this.exceptions.get(0);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.cause;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        printStackTrace(new WrappedPrintStream(printStream));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        printStackTrace(new WrappedPrintWriter(printWriter));
    }

    private void printStackTrace(PrintStreamOrWriter printStreamOrWriter) {
        printStreamOrWriter.append(this).append("\n");
        for (StackTraceElement stackTraceElement : getStackTrace()) {
            printStreamOrWriter.append("\tat ").append(stackTraceElement).append("\n");
        }
        int i = 1;
        for (Throwable th : this.exceptions) {
            printStreamOrWriter.append("  ComposedException ").append(Integer.valueOf(i)).append(" :\n");
            appendStackTrace(printStreamOrWriter, th, "\t");
            i++;
        }
        printStreamOrWriter.append("\n");
    }

    private void appendStackTrace(PrintStreamOrWriter printStreamOrWriter, Throwable th, String str) {
        printStreamOrWriter.append(str).append(th).append('\n');
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            printStreamOrWriter.append("\t\tat ").append(stackTraceElement).append('\n');
        }
        if (th.getCause() != null) {
            printStreamOrWriter.append("\tCaused by: ");
            appendStackTrace(printStreamOrWriter, th.getCause(), "");
        }
    }

    static abstract class PrintStreamOrWriter {
        abstract PrintStreamOrWriter append(Object obj);

        PrintStreamOrWriter() {
        }
    }

    static final class WrappedPrintStream extends PrintStreamOrWriter {
        private final PrintStream printStream;

        WrappedPrintStream(PrintStream printStream) {
            this.printStream = printStream;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.reactivex.rxjava3.exceptions.CompositeException.PrintStreamOrWriter
        public WrappedPrintStream append(Object obj) {
            this.printStream.print(obj);
            return this;
        }
    }

    static final class WrappedPrintWriter extends PrintStreamOrWriter {
        private final PrintWriter printWriter;

        WrappedPrintWriter(PrintWriter printWriter) {
            this.printWriter = printWriter;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.reactivex.rxjava3.exceptions.CompositeException.PrintStreamOrWriter
        public WrappedPrintWriter append(Object obj) {
            this.printWriter.print(obj);
            return this;
        }
    }

    static final class ExceptionOverview extends RuntimeException {
        ExceptionOverview(String str) {
            super(str);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }
}
