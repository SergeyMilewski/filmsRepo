package com.sergey.listoffilms.fragments.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sergey.listoffilms.databinding.MainFragmentBinding
import com.sergey.listoffilms.fragments.main.vm.MainViewModel
import com.sergey.listoffilms.fragments.main.vm.MainViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var viewBinding: MainFragmentBinding

    private val adapter: FilmsAdapter by lazy { FilmsAdapter() }


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
        viewModel.filmsMutableLiveData.observe(
            viewLifecycleOwner, { pagedList -> adapter.submitList(pagedList) })
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}