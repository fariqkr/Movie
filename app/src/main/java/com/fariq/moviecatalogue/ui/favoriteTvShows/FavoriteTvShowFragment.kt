package com.fariq.moviecatalogue.ui.favoriteTvShows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fariq.moviecatalogue.databinding.FragmentTvShowBinding
import com.fariq.moviecatalogue.ui.tvShows.TvShowAdapter
import com.fariq.moviecatalogue.viewmodel.ViewModelFactory
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class FavoriteTvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = _fragmentTvShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()
            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { tvShows ->
                tvShowAdapter.submitList(tvShows)
            })

            with(binding?.rvTvShow) {
                val mLayoutManager = FlexboxLayoutManager(activity)
                mLayoutManager.flexDirection = FlexDirection.ROW
                mLayoutManager.flexWrap = FlexWrap.WRAP
                mLayoutManager.justifyContent = JustifyContent.SPACE_AROUND
                this?.layoutManager = mLayoutManager
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvShowBinding = null
    }
}