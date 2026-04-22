package org.fdroid.fdroid.qr;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import androidx.core.content.ContextCompat;
import org.fdroid.fdroid.qr.CameraCharacteristicsChecker;

/* JADX INFO: loaded from: classes2.dex */
public class CameraCharacteristicsMinApiLevel21 extends CameraCharacteristicsChecker {
    private static final String TAG = "CameraCharMinApiLevel21";
    private final CameraManager cameraManager;

    private boolean isAutofocus(int i) {
        return i != 0;
    }

    CameraCharacteristicsMinApiLevel21(Context context) {
        this.cameraManager = (CameraManager) ContextCompat.getSystemService(context, CameraManager.class);
    }

    @Override // org.fdroid.fdroid.qr.CameraCharacteristicsChecker
    public boolean hasAutofocus() {
        try {
            return hasDeviceAutofocus();
        } catch (CameraCharacteristicsChecker.FDroidDeviceException e) {
            Log.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    private boolean hasDeviceAutofocus() throws CameraCharacteristicsChecker.FDroidDeviceException {
        try {
            for (String str : getCameraIdList()) {
                if (isLensFacingBack(str)) {
                    return testAutofocusModeForCamera(str);
                }
            }
            return false;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            throw new CameraCharacteristicsChecker.FDroidDeviceException("Exception accessing the camera list", e);
        }
    }

    private String[] getCameraIdList() throws CameraCharacteristicsChecker.FDroidDeviceException {
        try {
            return this.cameraManager.getCameraIdList();
        } catch (CameraAccessException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new CameraCharacteristicsChecker.FDroidDeviceException("Exception accessing the camera list", e);
        }
    }

    private boolean isLensFacingBack(String str) throws CameraCharacteristicsChecker.FDroidDeviceException {
        Integer num = (Integer) getCameraCharacteristics(str).get(CameraCharacteristics.LENS_FACING);
        return num != null && num.intValue() == 1;
    }

    private CameraCharacteristics getCameraCharacteristics(String str) throws CameraCharacteristicsChecker.FDroidDeviceException {
        try {
            return this.cameraManager.getCameraCharacteristics(str);
        } catch (CameraAccessException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new CameraCharacteristicsChecker.FDroidDeviceException("Exception accessing the camera id = " + str, e);
        }
    }

    private boolean testAutofocusModeForCamera(String str) throws CameraCharacteristicsChecker.FDroidDeviceException {
        try {
            int[] availableAFModes = getAvailableAFModes(str);
            if (availableAFModes != null) {
                return testAvailableMode(availableAFModes);
            }
            return false;
        } catch (CameraCharacteristicsChecker.FDroidDeviceException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new CameraCharacteristicsChecker.FDroidDeviceException("Exception accessing the camera id = " + str, e);
        }
    }

    private int[] getAvailableAFModes(String str) throws CameraCharacteristicsChecker.FDroidDeviceException {
        return (int[]) getCameraCharacteristics(str).get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
    }

    private boolean testAvailableMode(int[] iArr) {
        boolean zIsAutofocus = false;
        for (int i : iArr) {
            zIsAutofocus |= isAutofocus(i);
        }
        return zIsAutofocus;
    }
}
