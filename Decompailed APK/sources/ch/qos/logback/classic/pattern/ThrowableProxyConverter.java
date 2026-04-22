package ch.qos.logback.classic.pattern;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.boolex.EventEvaluator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ThrowableProxyConverter extends ThrowableHandlingConverter {
    protected static final int BUILDER_CAPACITY = 2048;
    int lengthOption;
    List<EventEvaluator> evaluatorList = null;
    List<String> ignoredStackTraceLines = null;
    int errorCount = 0;

    private void addEvaluator(EventEvaluator eventEvaluator) {
        if (this.evaluatorList == null) {
            this.evaluatorList = new ArrayList();
        }
        this.evaluatorList.add(eventEvaluator);
    }

    private void addIgnoreStackTraceLine(String str) {
        if (this.ignoredStackTraceLines == null) {
            this.ignoredStackTraceLines = new ArrayList();
        }
        this.ignoredStackTraceLines.add(str);
    }

    private boolean isIgnoredStackTraceLine(String str) {
        List<String> list = this.ignoredStackTraceLines;
        if (list == null) {
            return false;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (str.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    private void printIgnoredCount(StringBuilder sb, int i) {
        sb.append(" [");
        sb.append(i);
        sb.append(" skipped]");
    }

    private void printStackLine(StringBuilder sb, int i, StackTraceElementProxy stackTraceElementProxy) {
        sb.append(stackTraceElementProxy);
        extraData(sb, stackTraceElementProxy);
        if (i > 0) {
            printIgnoredCount(sb, i);
        }
    }

    private void recursiveAppend(StringBuilder sb, String str, int i, IThrowableProxy iThrowableProxy) {
        if (iThrowableProxy == null) {
            return;
        }
        subjoinFirstLine(sb, str, i, iThrowableProxy);
        sb.append(CoreConstants.LINE_SEPARATOR);
        subjoinSTEPArray(sb, i, iThrowableProxy);
        IThrowableProxy[] suppressed = iThrowableProxy.getSuppressed();
        if (suppressed != null) {
            for (IThrowableProxy iThrowableProxy2 : suppressed) {
                recursiveAppend(sb, CoreConstants.SUPPRESSED, i + 1, iThrowableProxy2);
            }
        }
        recursiveAppend(sb, CoreConstants.CAUSED_BY, i, iThrowableProxy.getCause());
    }

    private void subjoinExceptionMessage(StringBuilder sb, IThrowableProxy iThrowableProxy) {
        sb.append(iThrowableProxy.getClassName());
        sb.append(": ");
        sb.append(iThrowableProxy.getMessage());
    }

    private void subjoinFirstLine(StringBuilder sb, String str, int i, IThrowableProxy iThrowableProxy) {
        ThrowableProxyUtil.indent(sb, i - 1);
        if (str != null) {
            sb.append(str);
        }
        subjoinExceptionMessage(sb, iThrowableProxy);
    }

    @Override // ch.qos.logback.core.pattern.Converter
    public String convert(ILoggingEvent iLoggingEvent) {
        IThrowableProxy throwableProxy = iLoggingEvent.getThrowableProxy();
        if (throwableProxy == null) {
            return "";
        }
        List<EventEvaluator> list = this.evaluatorList;
        if (list == null || list.size() <= 0) {
            return throwableProxyToString(throwableProxy);
        }
        ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.evaluatorList.get(0));
        throw null;
    }

    protected void extraData(StringBuilder sb, StackTraceElementProxy stackTraceElementProxy) {
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x000a  */
    @Override // ch.qos.logback.core.pattern.DynamicConverter, ch.qos.logback.core.spi.LifeCycle
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void start() {
        /*
            r6 = this;
            java.lang.String r0 = r6.getFirstOption()
            r1 = 2147483647(0x7fffffff, float:NaN)
            r2 = 1
            if (r0 != 0) goto Ld
        La:
            r6.lengthOption = r1
            goto L48
        Ld:
            java.util.Locale r3 = java.util.Locale.US
            java.lang.String r0 = r0.toLowerCase(r3)
            java.lang.String r3 = "full"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L1c
            goto La
        L1c:
            java.lang.String r3 = "short"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L27
            r6.lengthOption = r2
            goto L48
        L27:
            int r3 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L2e
            r6.lengthOption = r3     // Catch: java.lang.NumberFormatException -> L2e
            goto L48
        L2e:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Could not parse ["
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = "] as an integer"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r6.addError(r0)
            goto La
        L48:
            java.util.List r0 = r6.getOptionList()
            if (r0 == 0) goto L79
            int r1 = r0.size()
            if (r1 <= r2) goto L79
            int r1 = r0.size()
        L58:
            if (r2 >= r1) goto L79
            java.lang.Object r3 = r0.get(r2)
            java.lang.String r3 = (java.lang.String) r3
            ch.qos.logback.core.Context r4 = r6.getContext()
            java.lang.String r5 = "EVALUATOR_MAP"
            java.lang.Object r4 = r4.getObject(r5)
            java.util.Map r4 = (java.util.Map) r4
            java.lang.Object r4 = r4.get(r3)
            androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(r4)
            r6.addIgnoreStackTraceLine(r3)
            int r2 = r2 + 1
            goto L58
        L79:
            super.start()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.qos.logback.classic.pattern.ThrowableProxyConverter.start():void");
    }

    @Override // ch.qos.logback.core.pattern.DynamicConverter, ch.qos.logback.core.spi.LifeCycle
    public void stop() {
        this.evaluatorList = null;
        super.stop();
    }

    protected void subjoinSTEPArray(StringBuilder sb, int i, IThrowableProxy iThrowableProxy) {
        StackTraceElementProxy[] stackTraceElementProxyArray = iThrowableProxy.getStackTraceElementProxyArray();
        int commonFrames = iThrowableProxy.getCommonFrames();
        int length = this.lengthOption;
        boolean z = length > stackTraceElementProxyArray.length;
        if (z) {
            length = stackTraceElementProxyArray.length;
        }
        if (commonFrames > 0 && z) {
            length -= commonFrames;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            StackTraceElementProxy stackTraceElementProxy = stackTraceElementProxyArray[i3];
            if (isIgnoredStackTraceLine(stackTraceElementProxy.toString())) {
                i2++;
                if (length < stackTraceElementProxyArray.length) {
                    length++;
                }
            } else {
                ThrowableProxyUtil.indent(sb, i);
                printStackLine(sb, i2, stackTraceElementProxy);
                sb.append(CoreConstants.LINE_SEPARATOR);
                i2 = 0;
            }
        }
        if (i2 > 0) {
            printIgnoredCount(sb, i2);
            sb.append(CoreConstants.LINE_SEPARATOR);
        }
        if (commonFrames <= 0 || !z) {
            return;
        }
        ThrowableProxyUtil.indent(sb, i);
        sb.append("... ");
        sb.append(iThrowableProxy.getCommonFrames());
        sb.append(" common frames omitted");
        sb.append(CoreConstants.LINE_SEPARATOR);
    }

    protected String throwableProxyToString(IThrowableProxy iThrowableProxy) {
        StringBuilder sb = new StringBuilder(2048);
        recursiveAppend(sb, null, 1, iThrowableProxy);
        return sb.toString();
    }
}
