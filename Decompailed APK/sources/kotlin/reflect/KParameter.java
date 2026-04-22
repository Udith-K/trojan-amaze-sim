package kotlin.reflect;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: KParameter.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KParameter extends KAnnotatedElement {
    int getIndex();

    Kind getKind();

    String getName();

    KType getType();

    boolean isOptional();

    boolean isVararg();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: KParameter.kt */
    public static final class Kind {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Kind[] $VALUES;
        public static final Kind INSTANCE = new Kind("INSTANCE", 0);
        public static final Kind EXTENSION_RECEIVER = new Kind("EXTENSION_RECEIVER", 1);
        public static final Kind VALUE = new Kind("VALUE", 2);

        private static final /* synthetic */ Kind[] $values() {
            return new Kind[]{INSTANCE, EXTENSION_RECEIVER, VALUE};
        }

        public static Kind valueOf(String str) {
            return (Kind) Enum.valueOf(Kind.class, str);
        }

        public static Kind[] values() {
            return (Kind[]) $VALUES.clone();
        }

        private Kind(String str, int i) {
        }

        static {
            Kind[] kindArr$values = $values();
            $VALUES = kindArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(kindArr$values);
        }
    }
}
