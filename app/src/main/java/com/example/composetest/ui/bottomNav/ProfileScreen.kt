package com.example.composetest.ui.bottomNav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composetest.App
import com.example.composetest.di.modules.NetworkModule

class ProfileScreen(private val api: NetworkModule) {

    init {
        App.appComponent.inject(this)
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