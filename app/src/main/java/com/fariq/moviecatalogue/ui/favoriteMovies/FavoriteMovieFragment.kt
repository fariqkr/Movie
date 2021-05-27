package com.fariq.moviecatalogue.ui.favoriteMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fariq.moviecatalogue.databinding.FragmentMoviesBinding
import com.fariq.moviecatalogue.viewmodel.ViewModelFactory
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class FavoriteMovieFragment : Fragment() {

    private var _fragmentMoviesBinding: FragmentMoviesBinding? = null
    private val binding get() = _fragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            val movieAdapter = FavoriteMovieAdapter()
            viewModel.getFavoriteMovies().observe(
                viewLifecycleOwner,
                { movies -> movieAdapter.submitList(movies) }
            )

            with(binding?.rvMovie) {
                val mLayoutManager = FlexboxLayoutManager(activity)
                mLayoutManager.flexDirection = FlexDirection.ROW
                mLayoutManager.flexWrap = FlexWrap.WRAP
                mLayoutManager.justifyContent = JustifyContent.SPACE_AROUND
                this?.layoutManager = mLayoutManager
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMoviesBinding = null
    }
}