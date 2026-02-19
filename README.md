# Kiadri

An Android app designed to track friendship or couple activities, validate tasks with photos (camera or gallery), and generate a real-time synchronized scrapbook.

Because a simple shared text file was just too easy.

<img width="192" height="192" alt="image" src="https://github.com/user-attachments/assets/05960598-9672-4bd6-96a3-7170eb0f448b" />

## Features

* **Real-Time Sync:** Add, validate, and delete tasks instantly across devices using Firestore.
* **Visual Proofs:** Activity validation via the camera or the native Android Photo Picker.
* **Scrapbook Mode:** A Polaroid-style image grid featuring randomly generated rotation angles for that authentic messy look.
* **Fullscreen & Pinch-to-Zoom:** Immersive fullscreen memory viewing with native touch gesture handling.
* **File Management:** Direct image downloads to the phone's public pictures folder via `DownloadManager`.
* **Exclusive Dark Mode:** A forced dark theme featuring a true `#12140E` background, completely bypassing Material 3's muddy brown tint.
* **Security:** Silent anonymous authentication with locked-down Firestore/Storage rules. No bots allowed.

## Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose & Material Design 3
* **Images:** Coil (using `SubcomposeAsyncImage` for loading state management)
* **Backend as a Service:** Firebase (Auth, Cloud Firestore, Cloud Storage)

## Installation & Build

To compile this project locally, you'll need to show Google your credentials:

1. Clone this repository.
2. Create a project on the [Firebase Console](https://console.firebase.google.com/).
3. Enable **Firestore**, **Storage**, and **Authentication** (Anonymous mode).
4. Secure your Firebase rules (the app handles anonymous login on startup):
   ```javascript
   allow read, write: if request.auth != null;
   ```
5. Download your `google-services.json` file and drop it into the `app/` directory.
6. Build the APK.

## Credits
- Gemini to generate this readme
