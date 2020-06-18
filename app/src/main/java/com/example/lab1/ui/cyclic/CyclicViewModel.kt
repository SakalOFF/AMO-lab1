package com.example.lab1.ui.cyclic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CyclicViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Cyclic Fragment"
    }
    val text: LiveData<String> = _text
}