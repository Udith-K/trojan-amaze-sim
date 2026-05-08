# Trojan-Amaze-Sim
### Educational Android Security Simulation

> **ACADEMIC PROJECT ONLY**  
> IE3112 – Mobile Security  
> Sri Lanka Institute of Information Technology (SLIIT)

---

## 📘 Project Overview

This repository contains an **educational, controlled simulation** of Android
application reverse engineering and **Trojan-style background service injection**
using the open-source **Amaze File Manager** application.

The purpose of this project is to **demonstrate security risks, attack vectors,
and defensive considerations** in Android application development — strictly
within an academic and ethical context.

---

## 🎯 Learning Objectives

Through this project, students gain practical understanding of:

- Android APK reverse engineering (static analysis)
- Android application architecture (Activities, Services, Receivers)
- How malicious logic *can be injected* after tampering
- Trigger-based background execution (simulated)
- Security implications and mitigation strategies
- Ethical boundaries of offensive security research

---

## ⚠️ Ethical & Academic Compliance

This project strictly follows **university cybersecurity ethics policies**.

✅ Tested only in Android Emulator  
✅ No real user data accessed  
✅ No network exfiltration or C2 communication  
✅ No modified APKs distributed publicly  
✅ No real malware deployment  

❌ **This project must NOT be used outside an educational environment**

---

## 🧪 Project Scope

- Reverse engineering using JADX
- Analysis of `AndroidManifest.xml`
- Controlled injection of a **simulated background service**
- Menu-based and time-based trigger demonstration
- Logcat-based execution verification
- Security risk assessment and defenses

---

## 📁 Repository Structure (Educational Reference)
├── README.md
├── docs/                (guides & explanations)
├── src/
│   └── code-snippets/
│       └── DemoService.kt
├── evidence/            (screenshots & logs)
├── reports/             (final academic report PDF)
└── tools/               (helper scripts if required)
> ⚠️ Only **sanitized, non-deployable code snippets** are included.

---

## 🧩 Project Phases

### Phase 1 – Reverse Engineering
- APK decompilation
- Component mapping
- Permission analysis

### Phase 2 – Simulation of Trojan Injection
- Background service design (simulated)
- Trigger logic implementation
- Manifest modification (demonstration)

### Phase 3 – Security Analysis
- Risk identification
- Attack flow explanation
- Mitigation strategies

### Phase 4 – Testing & Documentation
- Emulator testing
- Logcat verification
- Screenshots & reporting

---

## 👥 Group Members

This is a **4-member group assignment** completed by:

1. **D.M.M.P.S. Mahamohottala**  
2. **Udith T.G.K**  
3. **Jayasinghe J.M.S.S**  
4. **Peiris W.S.S.N**  

BSc (Hons) in Information Technology – Cyber Security  
Sri Lanka Institute of Information Technology (SLIIT)

---

## 🤝 Contribution Statement

All group members contributed collaboratively to:
- Research and analysis
- Reverse engineering study
- Simulation design
- Testing and validation
- Documentation and reporting

---

## ⚖️ Disclaimer

This repository exists **solely for academic learning**.

Any techniques discussed or demonstrated are intended to help
developers and security professionals **understand, detect, and prevent**
Android application tampering.

---

*Last updated: April 2026*
