package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class BeepManager {
    private static final String TAG = "BeepManager";
    private final Context context;
    private boolean beepEnabled = true;
    private boolean vibrateEnabled = false;

    public BeepManager(Activity activity) {
        activity.setVolumeControlStream(3);
        this.context = activity.getApplicationContext();
    }

    public void setBeepEnabled(boolean z) {
        this.beepEnabled = z;
    }

    public synchronized void playBeepSoundAndVibrate() {
        Vibrator vibrator;
        try {
            if (this.beepEnabled) {
                playBeepSound();
            }
            if (this.vibrateEnabled && (vibrator = (Vibrator) this.context.getSystemService("vibrator")) != null) {
                vibrator.vibrate(200L);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public MediaPlayer playBeepSound() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setContentType(2).build());
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.google.zxing.client.android.BeepManager$$ExternalSyntheticLambda0
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer2) {
                BeepManager.lambda$playBeepSound$0(mediaPlayer2);
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.google.zxing.client.android.BeepManager$$ExternalSyntheticLambda1
            @Override // android.media.MediaPlayer.OnErrorListener
            public final boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                return BeepManager.lambda$playBeepSound$1(mediaPlayer2, i, i2);
            }
        });
        try {
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = this.context.getResources().openRawResourceFd(R$raw.zxing_beep);
            try {
                mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
                assetFileDescriptorOpenRawResourceFd.close();
                mediaPlayer.setVolume(0.1f, 0.1f);
                mediaPlayer.prepare();
                mediaPlayer.start();
                return mediaPlayer;
            } catch (Throwable th) {
                assetFileDescriptorOpenRawResourceFd.close();
                throw th;
            }
        } catch (IOException e) {
            Log.w(TAG, e);
            mediaPlayer.reset();
            mediaPlayer.release();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$playBeepSound$0(MediaPlayer mediaPlayer) {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$playBeepSound$1(MediaPlayer mediaPlayer, int i, int i2) {
        Log.w(TAG, "Failed to beep " + i + ", " + i2);
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        return true;
    }
}
