package com.sergey.listoffilms.fragments.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding4.widget.textChangeEvents
import com.sergey.listoffilms.autocomplete.AutoSuggestAdapter
import com.sergey.listoffilms.databinding.MainFragmentBinding
import com.sergey.listoffilms.fragments.main.vm.MainViewModel
import com.sergey.listoffilms.fragments.main.vm.MainViewModelFactory
import dagger.android.support.AndroidSupportInjection
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var viewBinding: MainFragmentBinding

    @Inject
    lateinit var adapter: FilmsAdapter
    private val autoCompleteAdapter by lazy {
        AutoSuggestAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line
        )
    }
    private var disposable = Disposable.disposed()


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = MainFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.listOfFilms.adapter = adapter
        viewBinding.search.apply {
            threshold = 2
            setAdapter(autoCompleteAdapter)
        }
        viewModel.filmsMutableLiveData.observe(
            viewLifecycleOwner, { adapter.submitList(it) })
        viewModel.autoCompleteMutableLiveData.observe(
            viewLifecycleOwner,
            { autoCompleteAdapter.setSearchData(it) })

    }

    override fun onResume() {
        super.onResume()
        disposable = viewBinding.search.textChangeEvents()
            .map { it.text }
            .subscribeOn(AndroidSchedulers.mainThread())
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter { !it.isNullOrBlank() }
            .subscribe({ viewModel.searchFilms(it) }, { Timber.e(it) })
    }

    override fun onPause() {
        super.onPause()
        disposable.dispose()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}