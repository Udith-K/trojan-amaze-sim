package kellinwood.logging;

/* JADX INFO: loaded from: classes.dex */
public interface LoggerInterface {
    void debug(String str);

    void error(String str);

    boolean isDebugEnabled();

    void warning(String str);
}
