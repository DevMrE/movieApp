# 🔒 Device Operations Module

## Purpose

Platform-agnostic abstraction for:
- Camera
- Gallery
- Location

---

## API
```kotlin
interface DeviceOperationsController {
    fun capturePhoto(): Flow<OperationResult<Media>>
    fun pickImages(): Flow<OperationResult<List<Media>>>
    fun getCurrentLocation(): Flow<OperationResult<Location>>
}
```
---

## Result Model
```kotlin
sealed interface OperationResult<out T> {
    data class Success<T>(val data: T) : OperationResult<T>
    data object Denied : OperationResult<Nothing>
    data object Cancelled : OperationResult<Nothing>
}
```
---

## Example Usage
```kotlin
viewModelScope.launch {
    deviceOperationsController.getCurrentLocation().collectLatest { result ->
        result.onGranted { data ->
            
        }.onDenied {
            
        }.onCancelled {
            
        }
    }
}
```
---

## Architecture Diagram

graph TD
VM --> Controller
Controller --> Permission
Controller --> Provider
Provider --> Android
Provider --> iOS

---

## ADR

- Flow over callbacks
- Explicit result handling
- Platform abstraction
- ViewModel-driven execution
