package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import ch.qos.logback.core.CoreConstants;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import kellinwood.security.zipsigner.ZipSigner;

/* JADX INFO: loaded from: classes.dex */
public abstract class CameraConfigurationUtils {
    private static final Pattern SEMICOLON = Pattern.compile(";");

    public static void setFocus(Camera.Parameters parameters, CameraSettings.FocusMode focusMode, boolean z) {
        String strFindSettableValue;
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (z || focusMode == CameraSettings.FocusMode.AUTO) {
            strFindSettableValue = findSettableValue("focus mode", supportedFocusModes, ZipSigner.MODE_AUTO);
        } else if (focusMode == CameraSettings.FocusMode.CONTINUOUS) {
            strFindSettableValue = findSettableValue("focus mode", supportedFocusModes, "continuous-picture", "continuous-video", ZipSigner.MODE_AUTO);
        } else if (focusMode == CameraSettings.FocusMode.INFINITY) {
            strFindSettableValue = findSettableValue("focus mode", supportedFocusModes, "infinity");
        } else {
            strFindSettableValue = focusMode == CameraSettings.FocusMode.MACRO ? findSettableValue("focus mode", supportedFocusModes, "macro") : null;
        }
        if (!z && strFindSettableValue == null) {
            strFindSettableValue = findSettableValue("focus mode", supportedFocusModes, "macro", "edof");
        }
        if (strFindSettableValue != null) {
            if (strFindSettableValue.equals(parameters.getFocusMode())) {
                Log.i("CameraConfiguration", "Focus mode already set to " + strFindSettableValue);
                return;
            }
            parameters.setFocusMode(strFindSettableValue);
        }
    }

    public static void setTorch(Camera.Parameters parameters, boolean z) {
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        String strFindSettableValue = z ? findSettableValue("flash mode", supportedFlashModes, "torch", "on") : findSettableValue("flash mode", supportedFlashModes, "off");
        if (strFindSettableValue != null) {
            if (strFindSettableValue.equals(parameters.getFlashMode())) {
                Log.i("CameraConfiguration", "Flash mode already set to " + strFindSettableValue);
                return;
            }
            Log.i("CameraConfiguration", "Setting flash mode to " + strFindSettableValue);
            parameters.setFlashMode(strFindSettableValue);
        }
    }

    public static void setBestExposure(Camera.Parameters parameters, boolean z) {
        int minExposureCompensation = parameters.getMinExposureCompensation();
        int maxExposureCompensation = parameters.getMaxExposureCompensation();
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        if (minExposureCompensation != 0 || maxExposureCompensation != 0) {
            if (exposureCompensationStep > 0.0f) {
                int iRound = Math.round((z ? 0.0f : 1.5f) / exposureCompensationStep);
                float f = exposureCompensationStep * iRound;
                int iMax = Math.max(Math.min(iRound, maxExposureCompensation), minExposureCompensation);
                if (parameters.getExposureCompensation() == iMax) {
                    Log.i("CameraConfiguration", "Exposure compensation already set to " + iMax + " / " + f);
                    return;
                }
                Log.i("CameraConfiguration", "Setting exposure compensation to " + iMax + " / " + f);
                parameters.setExposureCompensation(iMax);
                return;
            }
        }
        Log.i("CameraConfiguration", "Camera does not support exposure compensation");
    }

    public static void setBestPreviewFPS(Camera.Parameters parameters) {
        setBestPreviewFPS(parameters, 10, 20);
    }

    public static void setBestPreviewFPS(Camera.Parameters parameters, int i, int i2) {
        int[] next;
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        Log.i("CameraConfiguration", "Supported FPS ranges: " + toString((Collection) supportedPreviewFpsRange));
        if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.isEmpty()) {
            return;
        }
        Iterator<int[]> it = supportedPreviewFpsRange.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            int i3 = next[0];
            int i4 = next[1];
            if (i3 >= i * 1000 && i4 <= i2 * 1000) {
                break;
            }
        }
        if (next == null) {
            Log.i("CameraConfiguration", "No suitable FPS range?");
            return;
        }
        int[] iArr = new int[2];
        parameters.getPreviewFpsRange(iArr);
        if (Arrays.equals(iArr, next)) {
            Log.i("CameraConfiguration", "FPS range already set to " + Arrays.toString(next));
            return;
        }
        Log.i("CameraConfiguration", "Setting FPS range to " + Arrays.toString(next));
        parameters.setPreviewFpsRange(next[0], next[1]);
    }

    public static void setFocusArea(Camera.Parameters parameters) {
        if (parameters.getMaxNumFocusAreas() > 0) {
            Log.i("CameraConfiguration", "Old focus areas: " + toString((Iterable) parameters.getFocusAreas()));
            List<Camera.Area> listBuildMiddleArea = buildMiddleArea(400);
            Log.i("CameraConfiguration", "Setting focus area to : " + toString((Iterable) listBuildMiddleArea));
            parameters.setFocusAreas(listBuildMiddleArea);
            return;
        }
        Log.i("CameraConfiguration", "Device does not support focus areas");
    }

    public static void setMetering(Camera.Parameters parameters) {
        if (parameters.getMaxNumMeteringAreas() > 0) {
            Log.i("CameraConfiguration", "Old metering areas: " + parameters.getMeteringAreas());
            List<Camera.Area> listBuildMiddleArea = buildMiddleArea(400);
            Log.i("CameraConfiguration", "Setting metering area to : " + toString((Iterable) listBuildMiddleArea));
            parameters.setMeteringAreas(listBuildMiddleArea);
            return;
        }
        Log.i("CameraConfiguration", "Device does not support metering areas");
    }

    private static List buildMiddleArea(int i) {
        int i2 = -i;
        return Collections.singletonList(new Camera.Area(new Rect(i2, i2, i, i), 1));
    }

    public static void setVideoStabilization(Camera.Parameters parameters) {
        if (parameters.isVideoStabilizationSupported()) {
            if (parameters.getVideoStabilization()) {
                Log.i("CameraConfiguration", "Video stabilization already enabled");
                return;
            } else {
                Log.i("CameraConfiguration", "Enabling video stabilization...");
                parameters.setVideoStabilization(true);
                return;
            }
        }
        Log.i("CameraConfiguration", "This device does not support video stabilization");
    }

    public static void setBarcodeSceneMode(Camera.Parameters parameters) {
        if ("barcode".equals(parameters.getSceneMode())) {
            Log.i("CameraConfiguration", "Barcode scene mode already set");
            return;
        }
        String strFindSettableValue = findSettableValue("scene mode", parameters.getSupportedSceneModes(), "barcode");
        if (strFindSettableValue != null) {
            parameters.setSceneMode(strFindSettableValue);
        }
    }

    public static void setInvertColor(Camera.Parameters parameters) {
        if ("negative".equals(parameters.getColorEffect())) {
            Log.i("CameraConfiguration", "Negative effect already set");
            return;
        }
        String strFindSettableValue = findSettableValue("color effect", parameters.getSupportedColorEffects(), "negative");
        if (strFindSettableValue != null) {
            parameters.setColorEffect(strFindSettableValue);
        }
    }

    private static String findSettableValue(String str, Collection collection, String... strArr) {
        Log.i("CameraConfiguration", "Requesting " + str + " value from among: " + Arrays.toString(strArr));
        Log.i("CameraConfiguration", "Supported " + str + " values: " + collection);
        if (collection != null) {
            for (String str2 : strArr) {
                if (collection.contains(str2)) {
                    Log.i("CameraConfiguration", "Can set " + str + " to: " + str2);
                    return str2;
                }
            }
        }
        Log.i("CameraConfiguration", "No supported values match");
        return null;
    }

    private static String toString(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            sb.append(Arrays.toString((int[]) it.next()));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private static String toString(Iterable iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Camera.Area area = (Camera.Area) it.next();
            sb.append(area.rect);
            sb.append(CoreConstants.COLON_CHAR);
            sb.append(area.weight);
            sb.append(' ');
        }
        return sb.toString();
    }
}
