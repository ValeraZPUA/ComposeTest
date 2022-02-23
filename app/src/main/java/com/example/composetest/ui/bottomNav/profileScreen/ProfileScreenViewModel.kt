package com.example.composetest.ui.bottomNav.profileScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.models.NameX
import com.example.composetest.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileScreenViewModel @Inject constructor(private val apiInterface: ApiInterface) : ViewModel() {

    private val _name = MutableLiveData<NameX>()
    val name: LiveData<NameX> get() = _name

    fun getRandomUser() {
        viewModelScope.launch {
            flow {
                emit(apiInterface.getRandomUser())
            }.flowOn(Dispatchers.IO).collect {
                Log.d("tag22", "getRandomUser: ${it.name}")
                _name.value = it
            }
        }
    }
}