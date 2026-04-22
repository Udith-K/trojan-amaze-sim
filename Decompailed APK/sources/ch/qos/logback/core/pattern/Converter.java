package ch.qos.logback.core.pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class Converter {
    Converter next;

    public abstract String convert(Object obj);

    public final Converter getNext() {
        return this.next;
    }

    public final void setNext(Converter converter) {
        if (this.next != null) {
            throw new IllegalStateException("Next converter has been already set");
        }
        this.next = converter;
    }

    public void write(StringBuilder sb, Object obj) {
        sb.append(convert(obj));
    }
}
