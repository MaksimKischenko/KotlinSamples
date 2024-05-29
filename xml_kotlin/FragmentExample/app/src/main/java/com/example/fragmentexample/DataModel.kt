package com.example.fragmentexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {
    val message: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val messageForFragment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}