package com.kmp.movieapp.device_operations.platform

import platform.UIKit.UIViewController

/**
 * Provides the current UIKit view controller used to present iOS system screens.
 */
object IOSDeviceOperationsBinder {

    private var viewController: UIViewController? = null

    /**
     * Binds the current view controller.
     */
    fun bind(viewController: UIViewController) {
        this.viewController = viewController
    }

    /**
     * Clears the current binding when the given view controller is no longer valid.
     */
    fun unbind(viewController: UIViewController) {
        if (this.viewController === viewController) {
            this.viewController = null
        }
    }

    /**
     * Returns the currently bound view controller for internal iOS components.
     */
    internal fun getViewController(): UIViewController? = viewController
}