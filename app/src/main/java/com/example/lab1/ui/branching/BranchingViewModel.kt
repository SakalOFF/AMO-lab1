package com.example.lab1.ui.branching

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BranchingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Branching Fragment"
    }
    val text: LiveData<String> = _text
}