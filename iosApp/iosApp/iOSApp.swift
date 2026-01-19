import SwiftUI
import ComposeApp


@main
struct iOSApp: App {
    
    init() {
        SetupIosStartConfigKt.setupIosStartConfig()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}