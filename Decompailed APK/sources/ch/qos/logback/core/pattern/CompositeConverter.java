package ch.qos.logback.core.pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class CompositeConverter extends DynamicConverter {
    Converter childConverter;

    @Override // ch.qos.logback.core.pattern.Converter
    public String convert(Object obj) {
        StringBuilder sb = new StringBuilder();
        for (Converter converter = this.childConverter; converter != null; converter = converter.next) {
            converter.write(sb, obj);
        }
        return transform(obj, sb.toString());
    }

    public Converter getChildConverter() {
        return this.childConverter;
    }

    public void setChildConverter(Converter converter) {
        this.childConverter = converter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CompositeConverter<");
        FormatInfo formatInfo = this.formattingInfo;
        if (formatInfo != null) {
            sb.append(formatInfo);
        }
        if (this.childConverter != null) {
            sb.append(", children: ");
            sb.append(this.childConverter);
        }
        sb.append(">");
        return sb.toString();
    }

    protected abstract String transform(Object obj, String str);
}
