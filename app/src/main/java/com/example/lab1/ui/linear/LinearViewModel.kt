package com.example.lab1.ui.linear

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LinearViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Linear Fragment"
    }
    val text: LiveData<String> = _text
}