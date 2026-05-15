package com.kmp.movieapp.core.open_settings

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

class IosSettingsNavigator : SettingsNavigator {

    override fun openAppSettings() {
        runCatching {
            val url = NSURL.URLWithString(UIApplicationOpenSettingsURLString) ?: return

            dispatch_async(dispatch_get_main_queue()) {
                UIApplication.sharedApplication.openURL(
                    url,
                    options = emptyMap<Any?, Any>(),
                    completionHandler = null
                )
            }
        }
    }
}