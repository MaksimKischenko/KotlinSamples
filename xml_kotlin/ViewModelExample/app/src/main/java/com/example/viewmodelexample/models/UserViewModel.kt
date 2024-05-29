package com.example.viewmodelexample.models


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewmodelexample.data.UserData

class UserViewModel : ViewModel() {
    var userList : MutableLiveData<List<User>> = MutableLiveData()

    init {
        userList.value = UserData.getUsers()
    }

    fun getListUsers() = userList

    fun updateListUsers() {
        userList.value = UserData.getAnotherUser()
    }
}