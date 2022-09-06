package com.example.to_do

import androidx.lifecycle.ViewModel
import com.example.to_do.model.task

class taskViewModel : ViewModel() {
    var listTask = mutableListOf<task>()
}