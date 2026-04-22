package ch.qos.logback.core.joran.action;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import ch.qos.logback.core.joran.action.ActionUtil;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.spi.PropertyDefiner;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

/* JADX INFO: loaded from: classes.dex */
public class DefinePropertyAction extends Action {
    PropertyDefiner definer;
    boolean inError;
    String propertyName;
    ActionUtil.Scope scope;
    String scopeStr;

    @Override // ch.qos.logback.core.joran.action.Action
    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) throws ActionException {
        StringBuilder sb;
        String str2;
        this.scopeStr = null;
        this.scope = null;
        this.propertyName = null;
        this.inError = false;
        this.propertyName = attributes.getValue("name");
        String value = attributes.getValue(Action.SCOPE_ATTRIBUTE);
        this.scopeStr = value;
        this.scope = ActionUtil.stringToScope(value);
        if (OptionHelper.isEmpty(this.propertyName)) {
            sb = new StringBuilder();
            str2 = "Missing property name for property definer. Near [";
        } else {
            String value2 = attributes.getValue(Action.CLASS_ATTRIBUTE);
            if (!OptionHelper.isEmpty(value2)) {
                try {
                    addInfo("About to instantiate property definer of type [" + value2 + "]");
                    ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(OptionHelper.instantiateByClassName(value2, (Class<?>) PropertyDefiner.class, this.context));
                    throw null;
                } catch (Exception e) {
                    this.inError = true;
                    addError("Could not create an PropertyDefiner of type [" + value2 + "].", e);
                    throw new ActionException(e);
                }
            }
            sb = new StringBuilder();
            str2 = "Missing class name for property definer. Near [";
        }
        sb.append(str2);
        sb.append(str);
        sb.append("] line ");
        sb.append(getLineNumber(interpretationContext));
        addError(sb.toString());
        this.inError = true;
    }

    @Override // ch.qos.logback.core.joran.action.Action
    public void end(InterpretationContext interpretationContext, String str) {
        if (this.inError) {
            return;
        }
        if (interpretationContext.peekObject() != null) {
            addWarn("The object at the of the stack is not the property definer for property named [" + this.propertyName + "] pushed earlier.");
            return;
        }
        addInfo("Popping property definer for property named [" + this.propertyName + "] from the object stack");
        interpretationContext.popObject();
        throw null;
    }
}
