package com.sergey.listoffilms.fragments.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sergey.listoffilms.repository.DataRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(dataRepository) as T
        } else {
            throw IllegalArgumentException("MainViewModel Not Found")
        }
    }
}