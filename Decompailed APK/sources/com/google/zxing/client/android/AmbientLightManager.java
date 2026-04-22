package com.google.zxing.client.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.journeyapps.barcodescanner.camera.CameraManager;
import com.journeyapps.barcodescanner.camera.CameraSettings;

/* JADX INFO: loaded from: classes.dex */
public final class AmbientLightManager implements SensorEventListener {
    private CameraManager cameraManager;
    private CameraSettings cameraSettings;
    private Context context;
    private Handler handler = new Handler();
    private Sensor lightSensor;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public AmbientLightManager(Context context, CameraManager cameraManager, CameraSettings cameraSettings) {
        this.context = context;
        this.cameraManager = cameraManager;
        this.cameraSettings = cameraSettings;
    }

    public void start() {
        if (this.cameraSettings.isAutoTorchEnabled()) {
            SensorManager sensorManager = (SensorManager) this.context.getSystemService("sensor");
            Sensor defaultSensor = sensorManager.getDefaultSensor(5);
            this.lightSensor = defaultSensor;
            if (defaultSensor != null) {
                sensorManager.registerListener(this, defaultSensor, 3);
            }
        }
    }

    public void stop() {
        if (this.lightSensor != null) {
            ((SensorManager) this.context.getSystemService("sensor")).unregisterListener(this);
            this.lightSensor = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setTorch$0(boolean z) {
        this.cameraManager.setTorch(z);
    }

    private void setTorch(final boolean z) {
        this.handler.post(new Runnable() { // from class: com.google.zxing.client.android.AmbientLightManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setTorch$0(z);
            }
        });
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        if (this.cameraManager != null) {
            if (f <= 45.0f) {
                setTorch(true);
            } else if (f >= 450.0f) {
                setTorch(false);
            }
        }
    }
}
