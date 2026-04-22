================================================================================
        IE3112 MOBILE SECURITY ASSIGNMENT - COMPLETE GUIDE SUMMARY
================================================================================

WHAT YOU HAVE:
✅ ASSIGNMENT_GUIDE.md (15,000+ words) - COMPREHENSIVE EXPLANATION
   - Part 1: Comprehensive explanation of everything
   - Part 2: Key concepts explained in detail
   - Part 3: Detailed step-by-step plan
   - Part 4: Complete action plan with timeline
   - Part 5: Checklist for full marks
   - Part 6: Common mistakes to avoid
   - Part 7: Tools and resources

✅ EXECUTION_PLAN.md (8,000+ words) - ACTIONABLE STEPS
   - Week-by-week breakdown
   - Day-by-day tasks
   - Code examples for each phase
   - Testing procedures
   - Documentation templates

✅ QUICK_REFERENCE.md - ONE PAGE SUMMARY
   - Quick overview
   - Decision matrices
   - Key code snippets
   - Viva preparation
   - Success criteria

================================================================================
                            THE THREE PHASES
================================================================================

PHASE 1: REVERSE ENGINEERING (Understand the app)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Timeline: Days 1-7 (1 week)
Weight: 25% of assignment

What you do:
  1. Extract APK (unzip)
  2. Decompile using apktool
  3. Analyze AndroidManifest.xml
  4. Examine DEX files (Java code)
  5. Understand app components
  6. Map application flow
  7. Write detailed report

Deliverables:
  • Phase 1 Report (PDF) - Detailed analysis of app structure
  
Key things to document:
  ✓ Package name
  ✓ All Activities (UI screens)
  ✓ All Services (background tasks)
  ✓ All Broadcast Receivers (event listeners)
  ✓ All Permissions requested
  ✓ Application flow diagram
  ✓ Security findings

Target: Make this report so detailed that someone could rebuild the app
        from your documentation.


PHASE 2: MALWARE INJECTION (Add hidden service)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Timeline: Days 8-14 (1 week)
Weight: 25% of assignment

What you do:
  1. Plan trigger (when malware activates)
  2. Plan action (what malware does)
  3. Write TriggerReceiver.java (activation logic)
  4. Write TrojanService.java (malicious logic)
  5. Modify AndroidManifest.xml (register components)
  6. Add necessary permissions
  7. Compile and recompile APK
  8. Sign APK with debug key
  9. Test on emulator

Decision: Choose TRIGGER
  ✅ App Launch (EASIEST, RECOMMENDED)
  ✅ Connectivity Change (good)
  ✅ Boot Complete (possible)
  ❌ Time-based (hard to test)

Decision: Choose ACTION
  ✅ Simulated data exfil to localhost (IMPRESSIVE)
  ✅ Background CPU spike (good)
  ✅ Unauthorized notification (simple)
  ✅ Log to file (proves execution)

Code You'll Write:
  • TriggerReceiver.java (~20 lines)
    - Listens for trigger events
    - Starts TrojanService when triggered
    
  • TrojanService.java (~80 lines)
    - Runs in background
    - Gathers simulated device info
    - Attempts connection to localhost
    - Logs activity to file
    
  • Modified AndroidManifest.xml
    - Add new permissions
    - Add <service> declaration
    - Add <receiver> declaration

Deliverables:
  • Modified APK (amaze_modified_signed.apk)
  • Source code files
  • Modified manifest
  • Phase 2 Report explaining all changes

Target: Successfully inject malware that:
        - Maintains original app functionality
        - Runs completely hidden
        - Executes on trigger reliably
        - Logs all activity for proof


PHASE 3: SECURITY ANALYSIS (How to prevent/detect)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Timeline: Days 20-21 (included in testing)
Weight: 10% of assignment

What you do:
  1. Explain how attack works step-by-step
  2. Discuss 7 defense mechanisms
  3. Explain how users can protect themselves
  4. Describe detection methods
  5. Provide real-world examples
  6. Discuss legal implications

Defense Mechanisms to Discuss:
  1. Code Obfuscation - Make code unreadable
  2. Anti-Tampering - Detect app modification
  3. Manifest Verification - Verify no changes
  4. Component Whitelisting - Only allow known components
  5. Permission Auditing - Flag suspicious permissions
  6. Runtime Monitoring - Watch for suspicious behavior
  7. Secure Coding - Input validation, proper exceptions, etc.

Deliverables:
  • Phase 3 Report (PDF) - Comprehensive security analysis

Target: Demonstrate deep understanding of how to defend against
        malware injection attacks.


PHASE 4: TESTING & EVIDENCE (Proof it works)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Timeline: Days 15-19 (1 week)
Weight: 15% (demonstration) + 10% (documentation) = 25%

Screenshots Required (6+ images):
  1. Installation success
  2. App launching normally
  3. Logcat showing service startup
  4. Proof of execution (file/network activity)
  5. Original manifest (before)
  6. Modified manifest (after)
  7. Source code

Video Demonstration (5-10 minutes):
  Record yourself:
  • Installing modified APK
  • Launching Amaze (showing it works)
  • Triggering the malware
  • Showing logcat with execution logs
  • Showing proof file created
  • Reviewing source code
  • Explaining how it works

Proof of Execution:
  ✓ Logcat messages: "TrojanService: === MALICIOUS SERVICE STARTED ==="
  ✓ Log file created: trojan_activity.log
  ✓ Service ran: timestamps in log file
  ✓ No actual harm: only simulated, no real data stolen

================================================================================
                        VIVA EXAMINATION (20%)
================================================================================

Both students will be questioned individually on:

✓ Reverse engineering process (how you decompiled)
✓ Android components (Activities, Services, Receivers)
✓ Trigger mechanism (how malware activates)
✓ Service logic (what malware actually does)
✓ Recompilation process (how you modified APK)
✓ Security implications (why this is dangerous)
✓ Defense mechanisms (how to prevent)
✓ Code explanation (line-by-line understanding)
✓ Live demonstration (install and run modified APK)

Expected Questions:
  Q1: Walk us through your reverse engineering process
  Q2: How did you identify the Activities and Services?
  Q3: Explain your trigger mechanism
  Q4: Show us the malicious service code and explain it
  Q5: How did you modify AndroidManifest.xml?
  Q6: Why doesn't the malware get detected?
  Q7: What are the security implications?
  Q8: How would you defend against this attack?
  Q9: Can you demonstrate it working?
  Q10: What was the hardest part and why?

Success Tips:
  ✅ Understand EVERY line of your code
  ✅ Practice explaining without notes
  ✅ Know Android basics inside-out
  ✅ Be ready to discuss ethical implications
  ✅ Have working demo prepared
  ✅ Both students must be ready
  ✅ Answer questions directly and confidently

================================================================================
                        MARKS BREAKDOWN (100%)
================================================================================

Phase 1: Reverse Engineering                           25%
  - APK structure analysis (3%)
  - Component mapping (4%)
  - Code understanding (6%)
  - Report quality (6%)
  - Screenshots/evidence (6%)

Phase 2: Malware Injection Implementation              25%
  - Service implementation (6%)
  - Trigger mechanism (6%)
  - Manifest modifications (5%)
  - APK recompilation (4%)
  - Code quality (4%)

Phase 3: Security Analysis & Defense Discussion        10%
  - Defense mechanisms (5%)
  - Detection methods (3%)
  - Real-world examples (2%)

Documentation Quality                                   10%
  - Report formatting (3%)
  - Code comments (3%)
  - Clarity of explanation (4%)

Demonstration Evidence                                  15%
  - Screenshots (5%)
  - Video demonstration (10%)

Viva Performance                                        15%
  - Technical understanding (8%)
  - Code explanation (5%)
  - Response quality (2%)

TOTAL                                                  100%

================================================================================
                        GETTING 100/100
================================================================================

Go for 100/100 by:

✅ Phase 1 (25%):
   • Exhaustively analyze every component
   • Create comprehensive report with diagrams
   • Include code examples
   • Explain security findings
   • Take quality screenshots

✅ Phase 2 (25%):
   • Write clean, commented code
   • Properly integrate into APK
   • Ensure 100% functionality preserved
   • Document each modification
   • Successfully sign and test

✅ Phase 3 (10%):
   • Discuss all defense mechanisms
   • Provide code examples for each
   • Explain real-world implications
   • Show detection techniques
   • Cite real-world examples

✅ Documentation (10%):
   • Professional formatting (PDF)
   • Clear section headings
   • Code properly highlighted
   • All modifications explained
   • Appendices with supplementary info

✅ Demonstration (15%):
   • 6+ high-quality screenshots
   • 8-10 minute well-narrated video
   • Clear proof of execution
   • Shows trigger and activation
   • Demonstrates understanding

✅ Viva (15%):
   • Answer all questions confidently
   • Explain code line-by-line
   • Run live demonstration
   • Discuss security implications
   • Show deep understanding

✅ Ethical Compliance:
   • Sign declaration form
   • Don't use sensitive apps
   • Don't steal real data
   • Only test on owned devices
   • Document everything

================================================================================
                        TIMELINE
================================================================================

WEEK 1: Foundation (Days 1-7)
┌──────────────────────────────────────────────┐
│ Day 1-2: Extract and organize APK            │
│ Day 3-4: Decompile APK                       │
│ Day 5: Analyze manifest                      │
│ Day 6: Analyze code structure                │
│ Day 7: Write Phase 1 Report                  │
└──────────────────────────────────────────────┘
Outcome: Complete understanding of app structure

WEEK 2: Implementation (Days 8-14)
┌──────────────────────────────────────────────┐
│ Day 8-9: Plan trigger and action             │
│ Day 10-11: Write Receiver and Service        │
│ Day 12: Modify manifest                      │
│ Day 13: Compile APK                          │
│ Day 14: Sign and verify APK                  │
└──────────────────────────────────────────────┘
Outcome: Working modified APK ready for testing

WEEK 3: Testing (Days 15-21)
┌──────────────────────────────────────────────┐
│ Day 15: Install modified APK                 │
│ Day 16-17: Test trigger and execution        │
│ Day 18: Take all screenshots                 │
│ Day 19: Record demonstration video           │
│ Day 20-21: Write Phase 2 & Phase 3 reports   │
└──────────────────────────────────────────────┘
Outcome: Complete evidence and documentation

WEEK 4: Finishing (Days 22-28)
┌──────────────────────────────────────────────┐
│ Day 22-24: Final review of all work          │
│ Day 25-26: Prepare viva presentation         │
│ Day 27: Submit all deliverables              │
│ Day 28: Rest and confidence review           │
└──────────────────────────────────────────────┘
Outcome: Ready for viva examination

================================================================================
                        FILE CHECKLIST
================================================================================

Before submission, you must have:

Code Files:
  ☐ Modified APK (amaze_modified_signed.apk)
  ☐ TrojanService.java (source code)
  ☐ TriggerReceiver.java (source code)
  ☐ Modified AndroidManifest.xml
  ☐ Original AndroidManifest.xml (for comparison)

Documentation Files:
  ☐ Phase 1 Report (PDF) - Reverse Engineering
  ☐ Phase 2 Report (PDF) - Malware Injection
  ☐ Phase 3 Report (PDF) - Security Analysis
  ☐ All reports combined into single PDF (optional but recommended)

Evidence Files:
  ☐ Screenshots folder (6+ images)
  ☐ Demonstration video (MP4, 5-10 minutes)
  ☐ Logcat output (text file showing execution)

Legal Documents:
  ☐ Signed Educational Use & Ethical Responsibility Declaration
  ☐ Agreement to use only in controlled environment

Submission Package:
  ☐ All above files organized
  ☐ README explaining structure
  ☐ Contact information

================================================================================
                        START NOW!
================================================================================

This assignment is substantial but manageable if you:

1. Start IMMEDIATELY (don't wait)
2. Follow the week-by-week plan
3. Understand each component deeply
4. Test frequently
5. Document as you go
6. Practice your viva explanation
7. Maintain ethical standards

Estimated Total Time: 80-100 hours (spread over 4 weeks)
= 20-25 hours per week
= 3-4 hours per day

You have THREE DETAILED GUIDES:
  1. ASSIGNMENT_GUIDE.md (comprehensive - read this first)
  2. EXECUTION_PLAN.md (step-by-step - reference while working)
  3. QUICK_REFERENCE.md (one-page - quick lookup)

Good luck! You've got this! 🎓

================================================================================
