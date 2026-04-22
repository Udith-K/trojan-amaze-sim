package kellinwood.security.zipsigner;

/* JADX INFO: loaded from: classes.dex */
public class ProgressEvent {
    public static final int PRORITY_IMPORTANT = 1;
    public static final int PRORITY_NORMAL = 0;
    private String message;
    private int percentDone;
    private int priority;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public int getPercentDone() {
        return this.percentDone;
    }

    public void setPercentDone(int i) {
        this.percentDone = i;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }
}
