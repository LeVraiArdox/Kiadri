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

## Notifications Setup (Cloud Functions)

The app supports real-time push notifications when a new activity is added or when an activity is validated. This relies on Firebase Cloud Messaging (FCM) and Cloud Functions.

1. Ensure your Firebase project is on the **Blaze (Pay-as-you-go)** plan (required for Cloud Functions, though typical usage fits well within the free tier).
2. Install the Firebase CLI if you haven't already: `npm install -g firebase-tools`
3. Login to Firebase via your terminal: `npx firebase login`
4. From the root of this project, deploy the backend scripts:
   ```bash
   npx firebase deploy --only functions
   ```
*(Note: If this is your first time deploying Gen 2 functions, it may fail initially with an Eventarc permission error. This is normal. Wait 3-5 minutes for Google Cloud to provision the background permissions, then run the deploy command again.)*

## Credits
- Gemini to generate this readme
