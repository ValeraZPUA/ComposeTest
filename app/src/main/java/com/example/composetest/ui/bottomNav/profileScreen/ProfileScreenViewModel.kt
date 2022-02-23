package com.example.composetest.ui.bottomNav.profileScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileScreenViewModel @Inject constructor(private val apiInterface: ApiInterface) : ViewModel() {

    fun getRandomUser() {

    }

}