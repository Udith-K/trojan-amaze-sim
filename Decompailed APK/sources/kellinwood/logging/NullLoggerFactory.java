package kellinwood.logging;

/* JADX INFO: loaded from: classes.dex */
public class NullLoggerFactory implements LoggerFactory {
    static LoggerInterface logger = new LoggerInterface() { // from class: kellinwood.logging.NullLoggerFactory.1
        @Override // kellinwood.logging.LoggerInterface
        public void debug(String str) {
        }

        @Override // kellinwood.logging.LoggerInterface
        public void error(String str) {
        }

        @Override // kellinwood.logging.LoggerInterface
        public boolean isDebugEnabled() {
            return false;
        }

        @Override // kellinwood.logging.LoggerInterface
        public void warning(String str) {
        }
    };

    @Override // kellinwood.logging.LoggerFactory
    public LoggerInterface getLogger(String str) {
        return logger;
    }
}
