package com.kmp.movieapp.device_operations.di

import org.koin.core.module.Module

/**
 * Provides the platform-specific Koin module for device operations.
 *
 * Each platform is responsible for wiring its own providers and permission logic.
 */
expect val deviceModule: Module