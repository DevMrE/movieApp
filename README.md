
# 🎬 MovieApp

> ⚠️ **This project is intended solely for application/portfolio purposes.**
> It does not represent my full knowledge, capabilities, or potential as a developer.
> The app is meant to provide a brief insight into my development style and way of working.

> 💡 **The solution approaches, architectural decisions, and implementations used in this project are not final.**
> They serve purely as an illustration of how certain problems can be approached.
> In a real-world or production environment, solutions would be designed, reviewed, and refined more thoroughly.

---

## 📌 About the Project

MovieApp is a cross-platform application built with **Kotlin Multiplatform (KMP)**, running on **Android** and **iOS**.

The application demonstrates:

- Clean Architecture in a KMP environment
- Cross-platform state handling with Flow
- shared business logic and shared UI
- Platform abstraction (Android / iOS)
- Hardware & permission handling via a shared module

---

## ⚠️ Known Limitations

- The app is under active development and is **not production-ready**.
- Some features may be incomplete or still work in progress.
- The approaches and patterns used throughout the codebase are **illustrative only** and should not be considered best practices or final solutions.

---

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| Kotlin Multiplatform (KMP) | Cross-platform shared logic |
| Jetpack Compose / Compose Multiplatform | UI |
| Koin | Dependency Injection |
| Ktor | HTTP Client |
| Coroutines & Flow | Asynchronous programming |
| FusedLocationProvider | Location (Android) |
| CoreLocation | Location (iOS) |

---

## 📱 Supported Platforms

| Platform | Minimum Version |
|---|---|
| Android | API 35 (Android 15) |
| iOS | 15.0 |

---

## 📁 Project Structure

```
movieapp/
├── composeApp/          # Main app module (Android, iOS, Desktop entry points)
├── iosApp/              # iOS Xcode project
├── core/                # Shared core utilities, DI, networking
├── movie/               # Movie feature module
├── series/              # Series feature module
├── search/              # Search feature module
├── content_detail/      # Content detail feature module
├── discover/            # Discover feature module
├── device_operations/   # hardware-related module

```
## 🔒 Device Operations

The `device_operations` module abstracts all hardware-related functionality such as:

- Camera
- Gallery
- Location
- Microphone
- Notifications

It is designed to be:
- Platform-agnostic
- Flow-based
- ViewModel-driven
- Fully decoupled from UI

👉 **Detailed documentation and usage examples:**  
[Device Operations README](./device_operations/README.md)

---

## 🌍 Localization

The app currently supports the following languages:

| Language | Code |
|---|---|
| English | `en` |
| German | `de` |

The language is automatically selected based on the device's system language.

---

## 🎨 Appearance

The app fully supports both **Light Mode** and **Dark Mode**, automatically adapting to the system appearance setting of the device.

---

## 📄 License & Terms of Use

**All rights reserved.**

This project is **strictly intended for application and demonstration purposes only**. Any commercial use, redistribution, duplication, or usage of the code — in whole or in part — is **expressly prohibited without the prior written consent of the author**.

This includes but is not limited to:
- Any form of commercial use
- Selling or licensing the code
- Using it in your own products or services
- Publicly redistributing the source code

If you are interested in a collaboration or licensing, feel free to reach out directly.

---

## 👤 Author

**Emrah Cicek**
- GitHub: [@DevMrE](https://github.com/DevMrE)

---
