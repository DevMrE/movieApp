# 🎬 MovieApp

> ⚠️ **This project is intended solely for application/portfolio purposes.**
> It does not represent my full knowledge, capabilities, or potential as a developer.
> The app is meant to provide a brief insight into my development style and way of working.

> 💡 **The solution approaches, architectural decisions, and implementations used in this project are not final.**
> They serve purely as an illustration of how certain problems can be approached.
> In a real-world or production environment, solutions would be designed, reviewed, and refined more thoroughly.

---

## 📌 About the Project

MovieApp is a cross-platform application built with **Kotlin Multiplatform (KMP)**, running on **Android** and **iOS**. The app displays movies, series, and related information fetched from an external API.

---

## 📌 Overview

MovieApp is a Kotlin Multiplatform (KMP) application targeting Android and iOS.

The project demonstrates:
- Modular architecture
- Clean Architecture principles
- Shared business logic
- Platform abstraction (device operations)

---

## 🏗️ Architecture

Layers:
- Presentation (Compose + ViewModels)
- Domain (contracts, models)
- Data (repositories, providers)
- Platform (Android / iOS)

---

## 🧭 Architecture Diagram

graph TD
UI --> ViewModel
ViewModel --> UseCase
UseCase --> Repository
Repository --> Provider
Provider --> Android
Provider --> iOS

---

## 📁 Project Structure

movieapp/
- composeApp/
- iosApp/
- core/
- device_operations/
- movie/
- series/
- search/
- content_detail/
- discover/

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

## 🔐 Permissions

Permissions handled via abstraction layer.

---

## 🔒 Device Operations

Encapsulates:
- Camera
- Image picker
- Location

See device_operations/README.md

---

## 🧠 Architectural Decisions

- Flow instead of callbacks
- ViewModel driven execution
- No UI-driven permission logic
- Platform isolation

---

## 🌍 Localization

The app currently supports the following languages:

| Language | Code |
|---|---|
| English | `en` |
| German | `de` |

The language is automatically selected based on the device's system language.

---

## 🎨 UI

- Compose Multiplatform
- Light/Dark support

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
- LinkedIn: [linkedin.com/in/your-profile](https://linkedin.com/in/your-profile)

