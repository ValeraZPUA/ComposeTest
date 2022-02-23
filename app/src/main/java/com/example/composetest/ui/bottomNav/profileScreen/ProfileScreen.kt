package com.example.composetest.ui.bottomNav.profileScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composetest.App
import com.example.composetest.models.NameX
import javax.inject.Inject

class ProfileScreen {

    @Inject
    lateinit var viewModel: ProfileScreenViewModel

    init {
        App.appComponent.inject(this)
        viewModel.getRandomUser()

    }

    @Composable
    fun ProfileScreen() {
        val nameState: State<NameX?> = viewModel.name.observeAsState()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = nameState.value?.name ?: "Profile")
        }
    }
}