package com.example.viewmodelexample.adapters

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class LifeCycleAdapter : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun listener(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.d("TAG", event.toString())
    }
}