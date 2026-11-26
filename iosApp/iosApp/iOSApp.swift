import SwiftUI
import Shared


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
