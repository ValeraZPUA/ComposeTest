package com.example.composetest.di

import androidx.compose.runtime.Composable
import com.example.composetest.di.modules.NetworkModule
import com.example.composetest.ui.bottomNav.ProfileScreen
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class
    ]
)
interface AppComponent {
    fun inject(profileScreen: ProfileScreen)
}