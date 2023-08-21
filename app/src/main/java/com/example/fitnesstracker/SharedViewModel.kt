package com.example.fitnesstracker

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val userEmail = mutableStateOf("")
}