# **CivicLens**

 CivicLens : An Offline-First Citizen Reporting System

---

### **1. Problem Statement**

Rapid urbanization has led to increasing civic issues such as potholes, uncollected waste, and damaged infrastructure. However, traditional methods of reporting these issues to authorities are often manual, slow, or inaccessible due to poor network connectivity in remote areas or congested city zones. Citizens lack a unified, reliable platform to report grievances with visual proof and location data when internet access is unstable.

### **2. Proposed Solution**

**CivicLens** is an Android-based mobile application designed to empower citizens to report civic grievances efficiently. The application adopts an **"Offline-First" architecture**, allowing users to capture geo-tagged photographs of incidents using the device’s sensors even without an internet connection. The data is cached locally and automatically synchronized with a central server via a background service once network connectivity is restored.

---

### **3. Key Features & Experiment Mapping**

This project seamlessly integrates all 12 required experiments into a cohesive workflow.

* **User Authentication (Exp 11):** Secure Login/Signup using Firebase Auth to track individual user contributions.
* **Smart Dashboard (Exp 2):** A responsive `ConstraintLayout` interface displaying a feed of local issues.
* **Incident Reporting (Exp 7 & 10):**
* **Sensors:** Auto-capture Latitude/Longitude using **GPS**.
* **Media:** Capture evidence using the **Camera**.
* **Database:** Store the report (Image + Loc + Text) locally in a **Room Database**.


* **Offline-Sync Engine (Exp 5, 8 & 12):**
* **Local Storage:** Uses `SharedPreferences` for user settings and Room DB for reports.
* **Background Tasks:** A `WorkManager` job that runs periodically to check for internet and upload pending reports (Background Service).


* **Interactive UI (Exp 3 & 4):**
* **Navigation:** Uses Fragments for "Map View," "Camera," and "Profile."
* **Gestures:** "Swipe-to-Refresh" to check report status; Swipe items to delete/edit.


* **Real-Time Updates (Exp 6 & 9):**
* **API:** Fetches global city stats or news via a REST API.
* **Notifications:** Push notifications (FCM) alert the user when their report status changes to "Resolved."



---

### **4. Technical Stack**

* **Platform:** Android (Java/Kotlin)
* **IDE:** Android Studio
* **UI/UX:** XML, ConstraintLayout, Material Design Components
* **Local Database:** Room Persistence Library (SQLite abstraction)
* **Cloud/Auth:** Firebase Authentication & Cloud Messaging
* **Networking:** Retrofit (for REST API) or Volley
* **Sensors:** LocationManager (GPS), CameraX API
* **Background Processing:** WorkManager / Android Services

---

### **5. Deliverables**

These are the tangible items you will produce for the project:

**Phase 1: Core Setup (Exp 1, 2, 11)**

* [ ] Functional Login/Signup Screen connected to Firebase.
* [ ] Home Dashboard (Recycler View) with dummy data.

**Phase 2: Data & Sensors (Exp 5, 7, 10)**

* [ ] "Create Report" Screen that launches the Camera and fetches GPS coordinates.
* [ ] Room Database implemented to save these reports locally on the phone.

**Phase 3: Logic & Sync (Exp 6, 8, 12)**

* [ ] A Background Service that detects Internet connection.
* [ ] Sync Logic: The code that reads from Room DB -> Uploads to Cloud -> Deletes from Local DB.

**Phase 4: Polish (Exp 3, 4, 9)**

* [ ] Push Notifications enabled for status updates.
* [ ] Fragments and Gesture Navigation (Swiping) fully refined.

---

### **6. List of Experiments**

1. ✅ Install and configure Android Studio or Xcode, and create your first "Hello, World!" application.
2. ✅ Design a responsive user interface using LinearLayout, ConstraintLayout (Android), or Storyboards (iOS).
3. Create an app with multiple screens using Intents and Fragments.
4. Build an app with buttons and gesture handling for event-driven interactions.
5. Save user data locally using SharedPreferences (Android) or Core Data (iOS).
6. Create an app to fetch and display data from a REST API.
7. Build an app that integrates a device sensor, such as GPS or accelerometer.
8. Create an app with media handling to access and display images or videos.
9. Implement notifications and background tasks using Android Services or iOS Background Modes.
10. Implement push notifications: Use Firebase Cloud Messaging (Android) or APNs (iOS) for real-time updates.
11. Create a database-backed app: Use SQLite or Room Database (Android) or Core Data with CloudKit (iOS).
12. Integrate user authentication: Implement login and signup functionality using Firebase Authentication or OAuth.
13. Implement background tasks: Use Android Services or iOS Background Modes for periodic or continuous tasks.
