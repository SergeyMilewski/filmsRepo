package com.sergey.listoffilms.fragments.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sergey.listoffilms.api.models.Movie
import com.sergey.listoffilms.repository.DataRepository
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private var disposable = Disposable.disposed()

    val filmsMutableLiveData: LiveData<PagedList<Movie>> = dataRepository.getNowPlaying()

    val autoCompleteMutableLiveData = MutableLiveData<List<Movie>>(emptyList())

    fun searchFilms(input: CharSequence) {
        disposable.dispose()
        disposable = dataRepository.searchFilms(input)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { autoCompleteMutableLiveData.postValue(it.body()?.results) },
                { Timber.e(it) })
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}