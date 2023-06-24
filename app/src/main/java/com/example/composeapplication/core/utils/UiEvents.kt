package com.example.composeapplication.core.utils

sealed class UiEvents {
    data class SnackbarEvent(val message: String) : UiEvents()
    data class NavigateEvent(val route: String) : UiEvents()
}