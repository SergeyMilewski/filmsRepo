package com.sergey.listoffilms.fragments.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sergey.listoffilms.GlideApp
import com.sergey.listoffilms.api.models.Movie
import com.sergey.listoffilms.databinding.FilmDetailsFragmentLayoutBinding
import com.sergey.listoffilms.fragments.main.CLICKED_ITEM
import dagger.android.support.AndroidSupportInjection

class FilmDetailsFragment : Fragment() {

    private lateinit var viewBinding: FilmDetailsFragmentLayoutBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FilmDetailsFragmentLayoutBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie : Movie? = arguments?.getParcelable(CLICKED_ITEM)
        GlideApp.with(this)
            .load(movie?.imgUrl())
            .into(viewBinding.imgBackground)
        viewBinding.title.text = movie?.title
        viewBinding.overview.text = movie?.overview
        viewBinding.released.text = movie?.releaseDate
        viewBinding.rankingValue.text = movie?.voteAverage.toString()
        viewBinding.rating.rating = movie?.voteAverage?.let { it*5/10 }?:0f
    }
}