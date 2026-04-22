package kellinwood.security.zipsigner;

import java.util.Locale;
import kellinwood.security.zipsigner.ResourceAdapter;

/* JADX INFO: loaded from: classes.dex */
public class DefaultResourceAdapter implements ResourceAdapter {

    /* JADX INFO: renamed from: kellinwood.security.zipsigner.DefaultResourceAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$kellinwood$security$zipsigner$ResourceAdapter$Item;

        static {
            int[] iArr = new int[ResourceAdapter.Item.values().length];
            $SwitchMap$kellinwood$security$zipsigner$ResourceAdapter$Item = iArr;
            try {
                iArr[ResourceAdapter.Item.INPUT_SAME_AS_OUTPUT_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$kellinwood$security$zipsigner$ResourceAdapter$Item[ResourceAdapter.Item.AUTO_KEY_SELECTION_ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$kellinwood$security$zipsigner$ResourceAdapter$Item[ResourceAdapter.Item.LOADING_CERTIFICATE_AND_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$kellinwood$security$zipsigner$ResourceAdapter$Item[ResourceAdapter.Item.PARSING_CENTRAL_DIRECTORY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$kellinwood$security$zipsigner$ResourceAdapter$Item[ResourceAdapter.Item.GENERATING_MANIFEST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$kellinwood$security$zipsigner$ResourceAdapter$Item[ResourceAdapter.Item.GENERATING_SIGNATURE_FILE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$kellinwood$security$zipsigner$ResourceAdapter$Item[ResourceAdapter.Item.GENERATING_SIGNATURE_BLOCK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$kellinwood$security$zipsigner$ResourceAdapter$Item[ResourceAdapter.Item.COPYING_ZIP_ENTRY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    @Override // kellinwood.security.zipsigner.ResourceAdapter
    public String getString(ResourceAdapter.Item item, Object... objArr) {
        switch (AnonymousClass1.$SwitchMap$kellinwood$security$zipsigner$ResourceAdapter$Item[item.ordinal()]) {
            case 1:
                return "Input and output files are the same.  Specify a different name for the output.";
            case 2:
                return "Unable to auto-select key for signing " + objArr[0];
            case 3:
                return "Loading certificate and private key";
            case 4:
                return "Parsing the input's central directory";
            case 5:
                return "Generating manifest";
            case 6:
                return "Generating signature file";
            case 7:
                return "Generating signature block file";
            case 8:
                return String.format(Locale.ENGLISH, "Copying zip entry %d of %d", objArr[0], objArr[1]);
            default:
                throw new IllegalArgumentException("Unknown item " + item);
        }
    }
}
