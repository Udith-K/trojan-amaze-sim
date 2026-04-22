package ch.qos.logback.classic.pattern;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.boolex.EventEvaluator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class CallerDataConverter extends ClassicConverter {
    public static final String DEFAULT_CALLER_LINE_PREFIX = "Caller+";
    public static final String DEFAULT_RANGE_DELIMITER = "..";
    private int depthStart = 0;
    private int depthEnd = 5;
    List<EventEvaluator> evaluatorList = null;
    final int MAX_ERROR_COUNT = 4;
    int errorCount = 0;

    private void addEvaluator(EventEvaluator eventEvaluator) {
        if (this.evaluatorList == null) {
            this.evaluatorList = new ArrayList();
        }
        this.evaluatorList.add(eventEvaluator);
    }

    private void checkRange() {
        StringBuilder sb;
        String str;
        int i;
        int i2 = this.depthStart;
        if (i2 < 0 || (i = this.depthEnd) < 0) {
            sb = new StringBuilder();
            sb.append("Invalid depthStart/depthEnd range [");
            sb.append(this.depthStart);
            sb.append(", ");
            sb.append(this.depthEnd);
            str = "] (negative values are not allowed)";
        } else {
            if (i2 < i) {
                return;
            }
            sb = new StringBuilder();
            sb.append("Invalid depthEnd range [");
            sb.append(this.depthStart);
            sb.append(", ");
            sb.append(this.depthEnd);
            str = "] (start greater or equal to end)";
        }
        sb.append(str);
        addError(sb.toString());
    }

    private boolean isRange(String str) {
        return str.contains(getDefaultRangeDelimiter());
    }

    private String[] splitRange(String str) {
        return str.split(Pattern.quote(getDefaultRangeDelimiter()), 2);
    }

    @Override // ch.qos.logback.core.pattern.Converter
    public String convert(ILoggingEvent iLoggingEvent) {
        StringBuilder sb = new StringBuilder();
        List<EventEvaluator> list = this.evaluatorList;
        if (list != null) {
            if (list.size() <= 0) {
                return "";
            }
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.evaluatorList.get(0));
            throw null;
        }
        StackTraceElement[] callerData = iLoggingEvent.getCallerData();
        if (callerData != null) {
            int length = callerData.length;
            int i = this.depthStart;
            if (length > i) {
                int length2 = this.depthEnd;
                if (length2 >= callerData.length) {
                    length2 = callerData.length;
                }
                while (i < length2) {
                    sb.append(getCallerLinePrefix());
                    sb.append(i);
                    sb.append("\t at ");
                    sb.append(callerData[i]);
                    sb.append(CoreConstants.LINE_SEPARATOR);
                    i++;
                }
                return sb.toString();
            }
        }
        return CallerData.CALLER_DATA_NA;
    }

    protected String getCallerLinePrefix() {
        return DEFAULT_CALLER_LINE_PREFIX;
    }

    protected String getDefaultRangeDelimiter() {
        return DEFAULT_RANGE_DELIMITER;
    }

    @Override // ch.qos.logback.core.pattern.DynamicConverter, ch.qos.logback.core.spi.LifeCycle
    public void start() {
        String firstOption = getFirstOption();
        if (firstOption == null) {
            return;
        }
        try {
            if (isRange(firstOption)) {
                String[] strArrSplitRange = splitRange(firstOption);
                if (strArrSplitRange.length == 2) {
                    this.depthStart = Integer.parseInt(strArrSplitRange[0]);
                    this.depthEnd = Integer.parseInt(strArrSplitRange[1]);
                    checkRange();
                } else {
                    addError("Failed to parse depth option as range [" + firstOption + "]");
                }
            } else {
                this.depthEnd = Integer.parseInt(firstOption);
            }
        } catch (NumberFormatException e) {
            addError("Failed to parse depth option [" + firstOption + "]", e);
        }
        List<String> optionList = getOptionList();
        if (optionList == null || optionList.size() <= 1) {
            return;
        }
        int size = optionList.size();
        for (int i = 1; i < size; i++) {
            String str = optionList.get(i);
            Context context = getContext();
            if (context != null) {
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(((Map) context.getObject(CoreConstants.EVALUATOR_MAP)).get(str));
            }
        }
    }
}
