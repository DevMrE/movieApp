package com.kmp.movieapp.content_detail.presentation

sealed interface ContentDetailAction {

    data object OnPlayClicked: ContentDetailAction

    data object OnSharedClicked: ContentDetailAction
}