package ch.qos.logback.classic;

import ch.qos.logback.classic.joran.action.LoggerAction;
import ch.qos.logback.classic.pattern.CallerDataConverter;
import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import ch.qos.logback.classic.pattern.ContextNameConverter;
import ch.qos.logback.classic.pattern.DateConverter;
import ch.qos.logback.classic.pattern.EnsureExceptionHandling;
import ch.qos.logback.classic.pattern.ExtendedThrowableProxyConverter;
import ch.qos.logback.classic.pattern.FileOfCallerConverter;
import ch.qos.logback.classic.pattern.LevelConverter;
import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import ch.qos.logback.classic.pattern.LocalSequenceNumberConverter;
import ch.qos.logback.classic.pattern.LoggerConverter;
import ch.qos.logback.classic.pattern.MDCConverter;
import ch.qos.logback.classic.pattern.MarkerConverter;
import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.pattern.MethodOfCallerConverter;
import ch.qos.logback.classic.pattern.NopThrowableInformationConverter;
import ch.qos.logback.classic.pattern.PropertyConverter;
import ch.qos.logback.classic.pattern.RelativeTimeConverter;
import ch.qos.logback.classic.pattern.RootCauseFirstThrowableProxyConverter;
import ch.qos.logback.classic.pattern.ThreadConverter;
import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import ch.qos.logback.core.pattern.parser.Parser;
import java.util.HashMap;
import java.util.Map;
import org.fdroid.fdroid.installer.ErrorDialogActivity;

/* JADX INFO: loaded from: classes.dex */
public class PatternLayout extends PatternLayoutBase {
    public static final Map<String, String> defaultConverterMap;

    static {
        HashMap map = new HashMap();
        defaultConverterMap = map;
        map.putAll(Parser.DEFAULT_COMPOSITE_CONVERTER_MAP);
        map.put("d", DateConverter.class.getName());
        map.put("date", DateConverter.class.getName());
        map.put("r", RelativeTimeConverter.class.getName());
        map.put("relative", RelativeTimeConverter.class.getName());
        map.put(LoggerAction.LEVEL_ATTRIBUTE, LevelConverter.class.getName());
        map.put("le", LevelConverter.class.getName());
        map.put("p", LevelConverter.class.getName());
        map.put("t", ThreadConverter.class.getName());
        map.put("thread", ThreadConverter.class.getName());
        map.put("lo", LoggerConverter.class.getName());
        map.put("logger", LoggerConverter.class.getName());
        map.put("c", LoggerConverter.class.getName());
        map.put("m", MessageConverter.class.getName());
        map.put("msg", MessageConverter.class.getName());
        map.put(ErrorDialogActivity.EXTRA_MESSAGE, MessageConverter.class.getName());
        map.put("C", ClassOfCallerConverter.class.getName());
        map.put(Action.CLASS_ATTRIBUTE, ClassOfCallerConverter.class.getName());
        map.put("M", MethodOfCallerConverter.class.getName());
        map.put("method", MethodOfCallerConverter.class.getName());
        map.put("L", LineOfCallerConverter.class.getName());
        map.put("line", LineOfCallerConverter.class.getName());
        map.put("F", FileOfCallerConverter.class.getName());
        map.put(Action.FILE_ATTRIBUTE, FileOfCallerConverter.class.getName());
        map.put("X", MDCConverter.class.getName());
        map.put("mdc", MDCConverter.class.getName());
        map.put("ex", ThrowableProxyConverter.class.getName());
        map.put("exception", ThrowableProxyConverter.class.getName());
        map.put("rEx", RootCauseFirstThrowableProxyConverter.class.getName());
        map.put("rootException", RootCauseFirstThrowableProxyConverter.class.getName());
        map.put("throwable", ThrowableProxyConverter.class.getName());
        map.put("xEx", ExtendedThrowableProxyConverter.class.getName());
        map.put("xException", ExtendedThrowableProxyConverter.class.getName());
        map.put("xThrowable", ExtendedThrowableProxyConverter.class.getName());
        map.put("nopex", NopThrowableInformationConverter.class.getName());
        map.put("nopexception", NopThrowableInformationConverter.class.getName());
        map.put("cn", ContextNameConverter.class.getName());
        map.put("contextName", ContextNameConverter.class.getName());
        map.put("caller", CallerDataConverter.class.getName());
        map.put("marker", MarkerConverter.class.getName());
        map.put("property", PropertyConverter.class.getName());
        map.put("n", LineSeparatorConverter.class.getName());
        map.put("lsn", LocalSequenceNumberConverter.class.getName());
    }

    public PatternLayout() {
        this.postCompileProcessor = new EnsureExceptionHandling();
    }

    @Override // ch.qos.logback.core.pattern.PatternLayoutBase, ch.qos.logback.core.LayoutBase, ch.qos.logback.core.Layout
    public String doLayout(ILoggingEvent iLoggingEvent) {
        return !isStarted() ? "" : writeLoopOnConverters(iLoggingEvent);
    }

    @Override // ch.qos.logback.core.pattern.PatternLayoutBase
    public Map<String, String> getDefaultConverterMap() {
        return defaultConverterMap;
    }
}
