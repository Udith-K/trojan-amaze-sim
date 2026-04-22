# QUICK REFERENCE GUIDE - IE3112 Assignment

## THE ASSIGNMENT IN 60 SECONDS

**What:** Reverse engineer Android APK → Add hidden malicious service → Demonstrate it works

**Why:** Learn how malware works and how to defend against it (EDUCATIONAL ONLY)

**Your App:** Amaze File Manager (file manager app - perfect choice!)

**Time:** 4 weeks

**Marks:** 100 total
- Phase 1 (Reverse Engineering): 25%
- Phase 2 (Injection): 25%
- Security Analysis: 10%
- Documentation: 10%
- Demonstration: 15%
- Viva: 20%

---

## THREE PHASES

### PHASE 1: REVERSE ENGINEERING (What app does?)
```
Extract APK → Decompile → Analyze manifest → Understand code structure
↓
Deliverable: Detailed report (25%)
```

Key deliverables:
- List all Activities (UI screens)
- List all Services (background tasks)
- List all Receivers (event listeners)
- List all Permissions
- Draw application flow
- Identify any security issues

### PHASE 2: MALWARE INJECTION (Hide malicious code)
```
Create Service → Create Receiver → Modify Manifest → Recompile → Sign
↓
Deliverable: Modified APK + Code explanation (25%)
```

What you're adding:
- **Service**: Runs in background, hidden from user
- **Receiver**: Listens for trigger events (app launch, connectivity change)
- **Permissions**: Network access, device state access
- **Manifest**: Registers new components

What it does (SIMULATED):
- ✅ Attempts to "exfiltrate" fake device info to localhost
- ✅ OR runs CPU spike
- ✅ OR generates notification
- ❌ NOT real data theft, NOT real hacking

### PHASE 3: SECURITY ANALYSIS (How to prevent?)
```
How to prevent this attack → How to detect this attack → Real-world examples
↓
Deliverable: Detailed security report (10%)
```

Topics:
- Code obfuscation
- Anti-tampering checks
- Runtime monitoring
- User protection
- Detection methods

---

## QUICK EXECUTION CHECKLIST

### Week 1: Foundation
- [ ] Day 1-2: Extract APK (unzip)
- [ ] Day 3-4: Decompile (apktool or online)
- [ ] Day 5: Analyze manifest
- [ ] Day 6: Analyze code
- [ ] Day 7: Write Phase 1 report

### Week 2: Implementation
- [ ] Day 8-9: Plan your malware (trigger + action)
- [ ] Day 10-11: Write code (Receiver + Service)
- [ ] Day 12: Modify manifest
- [ ] Day 13: Compile code
- [ ] Day 14: Sign APK

### Week 3: Testing
- [ ] Day 15: Install APK
- [ ] Day 16-17: Test trigger + prove execution
- [ ] Day 18: Take screenshots
- [ ] Day 19: Record video (5-10 min)
- [ ] Day 20-21: Write Phase 2 & 3 reports

### Week 4: Final
- [ ] Day 22-24: Review all work
- [ ] Day 25-26: Prepare viva
- [ ] Day 27: Submit everything
- [ ] Day 28: Rest!

---

## TRIGGER & ACTION DECISION MATRIX

**Choose TRIGGER (when malicious service starts):**

| Trigger | Difficulty | Reliability | Best For |
|---------|-----------|-----------|----------|
| **App Launch** | ⭐ Easy | 99% | ✅ RECOMMENDED |
| **Connectivity Change** | ⭐ Easy | 95% | ✅ Good |
| **Boot Complete** | ⭐ Easy | 90% | OK |
| **User Interaction** | ⭐⭐ Medium | 80% | Hard to demo |
| **Time-based** | ⭐⭐ Medium | 50% | Don't use |

**Choose ACTION (what malicious service does):**

| Action | Difficulty | Wow Factor | Best For |
|--------|-----------|-----------|----------|
| **Simulated Data Exfil** | ⭐ Easy | ⭐⭐⭐ High | ✅ RECOMMENDED |
| **CPU Spike** | ⭐ Easy | ⭐⭐ Medium | Alternative |
| **Notification** | ⭐ Easy | ⭐ Low | Easy but basic |
| **Log to File** | ⭐ Easy | ⭐⭐ Medium | Good proof |

**Best Combination: App Launch + Simulated Data Exfiltration**

---

## KEY CODE YOU'LL WRITE

### TriggerReceiver.java (Activation)
```java
public class TriggerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // When event happens, start the service
        Intent serviceIntent = new Intent(context, TrojanService.class);
        context.startService(serviceIntent);
    }
}
```

### TrojanService.java (Malicious Logic)
```java
public class TrojanService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start background thread for malicious activity
        new Thread(() -> {
            performExfiltration(); // Or cpuSpike() or createNotification()
        }).start();
        return START_STICKY; // Keep running
    }
    
    private void performExfiltration() {
        try {
            // Gather SIMULATED device info (no real data)
            String deviceInfo = "Device: " + Build.MODEL + "\n";
            
            // Try to connect to localhost:8888 (will fail, that's OK)
            Socket socket = new Socket("127.0.0.1", 8888);
            socket.getOutputStream().write(deviceInfo.getBytes());
            socket.close();
            
            // Prove it ran by logging to file
            logToFile("Exfiltration completed");
        } catch (Exception e) {
            logToFile("Error: " + e.getMessage());
        }
    }
}
```

### AndroidManifest.xml Changes
```xml
<!-- ADD THESE PERMISSIONS: -->
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

<!-- ADD THESE INSIDE <application> TAG: -->

<!-- The malicious service -->
<service
    android:name="com.example.malware.TrojanService"
    android:enabled="true"
    android:exported="false"
    android:process=":trojan" />

<!-- The trigger receiver -->
<receiver
    android:name="com.example.malware.TriggerReceiver"
    android:enabled="true"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
</receiver>
```

---

## PROOF OF EXECUTION (What you'll show)

```bash
# See service starting in logs
adb logcat | grep TrojanService
# Output: D TrojanService: === MALICIOUS SERVICE STARTED ===

# See proof file created
adb shell cat /sdcard/Android/data/com.amaze.filemanager/files/trojan_activity.log
# Output: [HH:MM:SS] Service started
#         [HH:MM:SS] Exfiltration attempted
#         [HH:MM:SS] Exfiltration failed (no server): [reason]
```

**Why it "fails" (on purpose):**
- No actual server running on localhost:8888
- This is CONTROLLED ENVIRONMENT
- Proves simulated-only, not real attack
- No actual harm done

---

## FILES YOU'LL DELIVER

### Code Files:
- ✅ Modified APK (amaze_modified_signed.apk)
- ✅ TrojanService.java
- ✅ TriggerReceiver.java
- ✅ Modified AndroidManifest.xml

### Documentation:
- ✅ Phase 1 Report (Reverse Engineering) - PDF
- ✅ Phase 2 Report (Malware Injection) - PDF
- ✅ Phase 3 Report (Security Analysis) - PDF

### Evidence:
- ✅ 6+ Screenshots (install, code, logs, manifest)
- ✅ Video Demonstration (5-10 minutes)

### Legal:
- ✅ Signed Ethical Declaration Form

---

## PERFECT ANSWER TO VIVA QUESTIONS

### Q: How does reverse engineering work?
A: "An APK is a ZIP file containing compiled Java code (DEX format) and resources. We extract it, decompile the DEX to readable code using apktool, and analyze the AndroidManifest.xml to understand the app structure, components, and permissions."

### Q: How did you inject the malware?
A: "I created two new Java classes: TriggerReceiver (listens for events) and TrojanService (performs malicious action). I registered them in AndroidManifest.xml with necessary permissions, compiled the code back to DEX, rebuilt the APK, and signed it with a debug key."

### Q: Why doesn't it get caught?
A: "The service runs in a separate process (`:trojan`), has `android:exported=false` (hidden), uses no suspicious class names, and the action is triggered silently on app launch. From the user's perspective, nothing appears different."

### Q: Why is this a problem in real attacks?
A: "Attackers can steal real data (files, messages, contacts), harvest credentials, install ransomware, or use the device in a botnet. Users won't know because the service is completely hidden."

### Q: How can developers prevent this?
A: "Code obfuscation (makes it unreadable), runtime integrity checks (detect modifications), manifest whitelisting (reject unknown components), and secure signing (ensure app is from legitimate developer)."

### Q: What was the hardest part?
A: "The recompilation process was tricky - ensuring the decompiled code compiled back correctly, and getting the APK signed properly so it would install."

### Q: How would you detect this in production?
A: "Static analysis tools like MobSF can scan the APK for suspicious patterns. Dynamic analysis using Frida can intercept function calls at runtime. Antivirus software can signature-match known malware."

---

## DON'T FORGET!

### ✅ DO THIS:
- [x] Use Amaze File Manager (you chose well!)
- [x] Add only simulated malware
- [x] Keep original app functionality
- [x] Document everything thoroughly
- [x] Test on emulator/device you own
- [x] Practice viva presentation
- [x] Sign ethical declaration
- [x] Keep all deliverables organized

### ❌ DON'T DO THIS:
- [ ] Don't use banking/healthcare apps
- [ ] Don't steal real user data
- [ ] Don't leave app broken
- [ ] Don't publish modified APK
- [ ] Don't test on others' devices
- [ ] Don't skip documentation
- [ ] Don't plagiarize code
- [ ] Don't violate ethical policy

---

## ESTIMATED EFFORT

| Phase | Time | Difficulty |
|-------|------|-----------|
| 1. Reverse Engineering | 7 days | ⭐⭐ Medium |
| 2. Malware Injection | 7 days | ⭐⭐⭐ Hard |
| 3. Testing & Evidence | 5 days | ⭐⭐ Medium |
| 4. Documentation & Prep | 5 days | ⭐ Easy |
| **TOTAL** | **~28 days** | **Manageable if you start now** |

**Key Success Factors:**
1. Start EARLY (don't procrastinate)
2. Understand each component before writing code
3. Test frequently
4. Document as you go (not at the end)
5. Practice your viva explanation
6. Maintain ethical standards throughout

---

## RESOURCES YOU'LL NEED

### Tools (Most are Free):
- Android Studio (emulator)
- apktool (decompile APK)
- dex2jar (convert code)
- JD-GUI (view code)
- Text editor (VS Code, sublime, etc.)
- Git (version control)

### Online Tools (No Install):
- https://decompiler.com/ (APK decompilation)
- https://www.apksigner.com/ (APK signing)
- https://www.virustotal.com/ (APK scanning)

### Knowledge:
- Android basics (Activities, Services, Receivers)
- Java programming
- APK structure
- Reverse engineering concepts
- Security principles

---

## TIME MANAGEMENT TIPS

**Week 1:** Focus on understanding (reverse engineering)
- Don't rush - really understand each component
- This is the foundation for the rest

**Week 2:** Focus on implementation (coding)
- Write code carefully
- Test frequently
- Debug as you go

**Week 3:** Focus on evidence (testing & screenshots)
- Make everything work
- Document the proof
- Record demonstration

**Week 4:** Focus on finishing (reports & viva)
- Write clear, detailed reports
- Practice explaining everything
- Review all work before submission

---

## SUCCESS CRITERIA FOR 100/100

- ✅ APK successfully modified and installs
- ✅ Original app functionality completely preserved
- ✅ Malicious service activates on trigger reliably
- ✅ Logs created proving execution
- ✅ Phase 1 report is thorough and detailed
- ✅ Phase 2 report explains all modifications
- ✅ Phase 3 report discusses comprehensive defenses
- ✅ 6+ screenshot showing key steps
- ✅ Video demo clearly shows trigger and execution
- ✅ Can explain every single line of code
- ✅ Can answer unexpected viva questions
- ✅ All work is ethical and documented

---

## FINAL WORDS

This assignment teaches you REAL skills used by:
- Security researchers
- Malware analysts
- App developers
- Penetration testers

The knowledge is powerful. Use it responsibly.

**Your goal:** Learn deeply, work ethically, achieve 100/100. 

**Good luck!** 🎓

---

**For detailed information, see:**
- ASSIGNMENT_GUIDE.md (comprehensive explanation)
- EXECUTION_PLAN.md (step-by-step instructions)
