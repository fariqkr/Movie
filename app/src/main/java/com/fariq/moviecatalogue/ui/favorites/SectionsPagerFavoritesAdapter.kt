package com.fariq.moviecatalogue.ui.favorites

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fariq.moviecatalogue.R
import com.fariq.moviecatalogue.ui.favoriteMovies.FavoriteMovieFragment
import com.fariq.moviecatalogue.ui.favoriteTvShows.FavoriteTvShowFragment

class SectionsPagerFavoritesAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
 
    companion object {
        @StringRes
        private val TAB_TITLES = arrayOf(R.string.movies, R.string.tv_show)
    }
 
    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> FavoriteMovieFragment()
                1 -> FavoriteTvShowFragment()
                else -> Fragment()
            }
 
    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])
 
    override fun getCount(): Int = 2
 
}