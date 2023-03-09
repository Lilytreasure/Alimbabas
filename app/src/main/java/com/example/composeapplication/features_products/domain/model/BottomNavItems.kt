package com.example.composeapplication.features_products.domain.model

import com.example.composeapplication.R
import com.example.composeapplication.features_products.presentation.destinations.Destination
import com.example.composeapplication.features_products.presentation.destinations.HomescreenDestination


sealed class BottomNavItem(var icon: Int,var destination: Destination) {
    object Home : BottomNavItem(
        icon = R.drawable.ic_home,
        destination = HomescreenDestination
    )

}
