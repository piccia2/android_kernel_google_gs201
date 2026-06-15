#!/bin/bash
# Minimalizing LineageOS for DockerOS

echo "Disabling LineageOS Default Apps..."
# Disable Default Launcher (Trebuchet)
adb shell pm disable-user --user 0 org.lineageos.trebuchet

# Disable standard user apps
adb shell pm disable-user --user 0 org.lineageos.jelly # Browser
adb shell pm disable-user --user 0 org.lineageos.recorder # Recorder
adb shell pm disable-user --user 0 org.lineageos.eleven # Music Player
adb shell pm disable-user --user 0 org.lineageos.gallery # Gallery
adb shell pm disable-user --user 0 com.android.calendar # Calendar
adb shell pm disable-user --user 0 com.android.contacts # Contacts
adb shell pm disable-user --user 0 com.android.deskclock # Clock
adb shell pm disable-user --user 0 com.android.messaging # Messages
adb shell pm disable-user --user 0 com.android.email # Email

echo "LineageOS system apps disabled successfully. Ensure DockerLauncher is installed and set as default HOME."
