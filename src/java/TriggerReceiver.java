package com.example.malware;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * TriggerReceiver - Malware Activation Receiver
 * 
 * EDUCATIONAL PURPOSE ONLY
 * 
 * This broadcast receiver demonstrates how malware can:
 * 1. Listen for system or app events
 * 2. Automatically trigger when event occurs
 * 3. Start background service invisibly
 * 4. Execute malicious code without user interaction
 * 
 * Events this listens for:
 * - App launch (MAIN action)
 * - Device connectivity changes
 * 
 * In real malware, might listen for:
 * - Device boot
 * - User unlocking device
 * - Specific app opening
 * - Network changes
 * - SMS received
 * - Phone call received
 */
public class TriggerReceiver extends BroadcastReceiver {
    private static final String TAG = "TriggerReceiver";
    
    /**
     * Called when a broadcast matching our intent-filter is received
     * 
     * @param context Context of the application
     * @param intent The intent that triggered this receiver
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // Log the trigger event
        Log.d(TAG, "=== BROADCAST RECEIVED ===");
        Log.d(TAG, "Action: " + intent.getAction());
        Log.d(TAG, "Starting malicious service...");
        
        // Create intent to start the malicious service
        Intent serviceIntent = new Intent(context, TrojanService.class);
        
        // Start the service
        // Service runs in background without UI or user knowledge
        context.startService(serviceIntent);
        
        Log.d(TAG, "Service start request sent");
    }
    
    /**
     * HOW THIS WORKS IN PRACTICE:
     * 
     * 1. User launches Amaze File Manager app
     * 2. Android system broadcasts android.intent.action.MAIN
     * 3. Our TriggerReceiver's onReceive() is called automatically
     * 4. We create an Intent for TrojanService
     * 5. We call context.startService(serviceIntent)
     * 6. TrojanService.onStartCommand() is called
     * 7. Service performs malicious action in background thread
     * 8. User has no idea service is running
     * 
     * ADVANTAGES OF THIS TRIGGER:
     * - Fires every time app is launched (reliable)
     * - Easy to hide in manifest among other receivers
     * - No unusual permissions needed
     * - Very difficult to detect without reverse engineering
     * 
     * ALTERNATIVE TRIGGERS (not used here):
     * 
     * Connectivity Change:
     * - <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
     * - Fires when WiFi/data enabled/disabled
     * - Good for continuous activation
     * 
     * Boot Complete:
     * - <action android:name="android.intent.action.BOOT_COMPLETED"/>
     * - Fires when device boots
     * - Needs RECEIVE_BOOT_COMPLETED permission
     * - Ensures service survives device restart
     * 
     * Package Replacement:
     * - <action android:name="android.intent.action.PACKAGE_REPLACED"/>
     * - Fires when app is updated
     * - Good for persistence after updates
     * 
     * Time-based (harder):
     * - AlarmManager to trigger at specific times
     * - More complex to implement
     */
    
    /**
     * MANIFEST CONFIGURATION:
     * 
     * In AndroidManifest.xml, this receiver is registered as:
     * 
     * <receiver
     *     android:name="com.example.malware.TriggerReceiver"
     *     android:enabled="true"
     *     android:exported="true">
     *     
     *     <intent-filter>
     *         <action android:name="android.intent.action.MAIN"/>
     *         <category android:name="android.intent.category.LAUNCHER"/>
     *     </intent-filter>
     *     
     *     <!-- ALTERNATIVE: Connectivity trigger
     *     <intent-filter>
     *         <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
     *     </intent-filter>
     *     -->
     *     
     * </receiver>
     * 
     * ATTRIBUTES EXPLAINED:
     * - android:name: Full class path
     * - android:enabled="true": Receiver is active
     * - android:exported="true": Can receive broadcasts from system
     * - <intent-filter>: What events trigger this receiver
     * - <action>: The broadcast action to listen for
     * - <category>: Additional filter (LAUNCHER = app launcher)
     */
    
    /**
     * SECURITY IMPLICATIONS:
     * 
     * Why is this dangerous?
     * 
     * 1. STEALTH:
     *    - Receiver doesn't show in UI
     *    - Service has no visible window
     *    - No indication service is running
     *    - User doesn't know it's there
     * 
     * 2. PERSISTENCE:
     *    - Activates automatically on trigger
     *    - Service restarts if killed (START_STICKY)
     *    - Survives app update (package install triggers)
     *    - Survives device reboot (BOOT_COMPLETED trigger)
     * 
     * 3. PRIVILEGE:
     *    - Runs with app's permissions
     *    - Can access anything app can access
     *    - Can request additional permissions
     *    - Runs with same UID as app
     * 
     * 4. SCOPE:
     *    - Can affect entire device
     *    - Can affect other apps
     *    - Can affect user experience
     *    - Can cause real damage
     * 
     * REAL-WORLD EXAMPLES:
     * - Trojan.GenericKD.6842738: Activates on device boot
     * - XcodeGhost: Triggered by app launch
     * - SonicSpy: Triggered by app launch
     * - HiddenCobra: Multiple triggers
     */
    
    /**
     * DETECTION METHODS:
     * 
     * How can this be detected?
     * 
     * 1. STATIC ANALYSIS (without running):
     *    - Decompile APK
     *    - Look for unexpected receivers in manifest
     *    - Search for suspicious service classes
     *    - Check for obfuscated code
     * 
     * 2. DYNAMIC ANALYSIS (while running):
     *    - Monitor logcat for suspicious logs
     *    - Check running services: adb shell ps -A
     *    - Watch network traffic
     *    - Monitor CPU and memory usage
     * 
     * 3. TOOLS:
     *    - MobSF: Automated APK analysis
     *    - Androguard: Deep analysis
     *    - Frida: Runtime inspection
     *    - Virus Total: Signature matching
     * 
     * 4. USER INDICATORS:
     *    - Settings > Apps > Running: See service
     *    - Settings > Apps > Permissions: Unexpected access
     *    - Battery drain: Service running constantly
     *    - Network traffic: Unexpected uploads
     */
    
    /**
     * DEFENSE MECHANISMS:
     * 
     * How can developers prevent this attack?
     * 
     * 1. CODE OBFUSCATION (ProGuard/R8):
     *    - Makes TriggerReceiver unreadable after decompilation
     *    - Names become a, b, c instead of descriptive
     *    - Makes reverse engineering much harder
     * 
     * 2. MANIFEST VERIFICATION:
     *    - Developer hashes manifest
     *    - At runtime, recalculates hash
     *    - If modified, app refuses to run
     *    - Would catch our receiver registration
     * 
     * 3. COMPONENT WHITELISTING:
     *    - At runtime, verify only expected receivers exist
     *    - If unexpected receiver found, reject
     *    - Would prevent our service from running
     * 
     * 4. PERMISSION AUDITING:
     *    - Flag unexpected permissions
     *    - User is alerted if permissions change
     *    - Would flag our INTERNET permission request
     * 
     * 5. APK SIGNATURE VERIFICATION:
     *    - Verify APK is signed with legitimate certificate
     *    - Our modification changes signature
     *    - Modified APK would be rejected
     * 
     * 6. ANTI-TAMPERING:
     *    - CRC check of code
     *    - Hash verification of resources
     *    - Checksum of manifest
     *    - All would detect our modifications
     * 
     * COMBINED DEFENSE IS MOST EFFECTIVE:
     * - Obfuscation + manifest verification
     * - Component whitelisting + signature check
     * - Multiple layers make attack much harder
     */
    
    /**
     * EDUCATIONAL VALUE:
     * 
     * By understanding how this works, you learn:
     * 
     * 1. ANDROID SECURITY CONCEPTS:
     *    - Intent system and broadcasts
     *    - Component lifecycle
     *    - Permission model
     *    - Process isolation
     * 
     * 2. ATTACK TECHNIQUES:
     *    - How malware hides
     *    - How persistence is achieved
     *    - How autostart works
     *    - How privilege is escalated
     * 
     * 3. DEFENSE STRATEGIES:
     *    - How to detect attacks
     *    - How to prevent injection
     *    - How to verify integrity
     *    - How to protect apps
     * 
     * 4. REVERSE ENGINEERING:
     *    - How to analyze apps
     *    - How to find malware
     *    - How to understand code
     *    - How to track behavior
     * 
     * THIS KNOWLEDGE IS POWERFUL:
     * - Use it to build secure apps ✅
     * - Use it to detect malware ✅
     * - Use it to secure devices ✅
     * - DON'T use it for illegal hacking ❌
     */
}
