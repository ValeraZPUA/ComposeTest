package com.example.composetest.ui.bottomNav.profileScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composetest.App
import javax.inject.Inject

class ProfileScreen() {

    @Inject
    lateinit var viewModel: ProfileScreenViewModel

    init {
        App.appComponent.inject(this)
        viewModel.getRandomUser()
    }

    @Composable
    fun ProfileScreen() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Profile")
        }
    }
}