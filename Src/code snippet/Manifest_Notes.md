## Manifest Security Notes

Registering services inside AndroidManifest.xml allows them to be executed
by the Android OS.

If an attacker injects a new service into the manifest:
- The service becomes trusted by the system
- Existing app permissions may be abused
- Background execution may occur silently

Mitigation strategies include:
- APK integrity verification
- Signature validation
- Runtime tamper detection
- Restricting foreground service usage
