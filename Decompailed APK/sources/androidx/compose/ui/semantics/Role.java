package androidx.compose.ui.semantics;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SemanticsProperties.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Role {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int Button = m1997constructorimpl(0);
    private static final int Checkbox = m1997constructorimpl(1);
    private static final int Switch = m1997constructorimpl(2);
    private static final int RadioButton = m1997constructorimpl(3);
    private static final int Tab = m1997constructorimpl(4);
    private static final int Image = m1997constructorimpl(5);
    private static final int DropdownList = m1997constructorimpl(6);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Role m1996boximpl(int i) {
        return new Role(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m1997constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1998equalsimpl(int i, Object obj) {
        return (obj instanceof Role) && i == ((Role) obj).m2002unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1999equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2000hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m1998equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2000hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2002unboximpl() {
        return this.value;
    }

    /* JADX INFO: compiled from: SemanticsProperties.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getButton-o7Vup1c, reason: not valid java name */
        public final int m2003getButtono7Vup1c() {
            return Role.Button;
        }

        /* JADX INFO: renamed from: getCheckbox-o7Vup1c, reason: not valid java name */
        public final int m2004getCheckboxo7Vup1c() {
            return Role.Checkbox;
        }

        /* JADX INFO: renamed from: getSwitch-o7Vup1c, reason: not valid java name */
        public final int m2008getSwitcho7Vup1c() {
            return Role.Switch;
        }

        /* JADX INFO: renamed from: getRadioButton-o7Vup1c, reason: not valid java name */
        public final int m2007getRadioButtono7Vup1c() {
            return Role.RadioButton;
        }

        /* JADX INFO: renamed from: getTab-o7Vup1c, reason: not valid java name */
        public final int m2009getTabo7Vup1c() {
            return Role.Tab;
        }

        /* JADX INFO: renamed from: getImage-o7Vup1c, reason: not valid java name */
        public final int m2006getImageo7Vup1c() {
            return Role.Image;
        }

        /* JADX INFO: renamed from: getDropdownList-o7Vup1c, reason: not valid java name */
        public final int m2005getDropdownListo7Vup1c() {
            return Role.DropdownList;
        }
    }

    private /* synthetic */ Role(int i) {
        this.value = i;
    }

    public String toString() {
        return m2001toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2001toStringimpl(int i) {
        return m1999equalsimpl0(i, Button) ? "Button" : m1999equalsimpl0(i, Checkbox) ? "Checkbox" : m1999equalsimpl0(i, Switch) ? "Switch" : m1999equalsimpl0(i, RadioButton) ? "RadioButton" : m1999equalsimpl0(i, Tab) ? "Tab" : m1999equalsimpl0(i, Image) ? "Image" : m1999equalsimpl0(i, DropdownList) ? "DropdownList" : "Unknown";
    }
}
