package com.kmp.movieapp.content_detail.presentation.action

sealed interface DetailAction {

    data object OnPlayClicked: DetailAction

    data object OnSharedClicked: DetailAction
}