package ch.qos.logback.classic.joran.action;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

/* JADX INFO: loaded from: classes.dex */
public class LoggerContextListenerAction extends Action {
    boolean inError = false;
    LoggerContextListener lcl;

    @Override // ch.qos.logback.core.joran.action.Action
    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) throws ActionException {
        this.inError = false;
        String value = attributes.getValue(Action.CLASS_ATTRIBUTE);
        if (OptionHelper.isEmpty(value)) {
            addError("Mandatory \"class\" attribute not set for <loggerContextListener> element");
            this.inError = true;
            return;
        }
        try {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(OptionHelper.instantiateByClassName(value, (Class<?>) LoggerContextListener.class, this.context));
            interpretationContext.pushObject(null);
            addInfo("Adding LoggerContextListener of type [" + value + "] to the object stack");
        } catch (Exception e) {
            this.inError = true;
            addError("Could not create LoggerContextListener of type " + value + "].", e);
        }
    }

    @Override // ch.qos.logback.core.joran.action.Action
    public void end(InterpretationContext interpretationContext, String str) throws ActionException {
        if (this.inError) {
            return;
        }
        if (interpretationContext.peekObject() != null) {
            addWarn("The object on the top the of the stack is not the LoggerContextListener pushed earlier.");
        } else {
            ((LoggerContext) this.context).addListener(null);
            interpretationContext.popObject();
        }
    }
}
