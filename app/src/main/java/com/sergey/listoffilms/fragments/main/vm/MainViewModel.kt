package com.sergey.listoffilms.fragments.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sergey.listoffilms.api.models.Movie
import com.sergey.listoffilms.repository.DataRepository

class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val filmsMutableLiveData: LiveData<PagedList<Movie>> = dataRepository.getNowPlaying()


}