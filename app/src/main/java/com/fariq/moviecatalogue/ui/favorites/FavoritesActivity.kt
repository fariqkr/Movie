package com.fariq.moviecatalogue.ui.favorites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fariq.moviecatalogue.databinding.ActivityFavoritesBinding


class FavoritesActivity : AppCompatActivity() {
    private var _activityFavoritesBinding: ActivityFavoritesBinding? = null
    private val binding get() = _activityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        _activityFavoritesBinding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val sectionsPagerAdapter = SectionsPagerFavoritesAdapter(this, supportFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tabs?.setupWithViewPager(binding?.viewPager)

        supportActionBar?.elevation = 0f

    }

    override fun onDestroy() {
        super.onDestroy()
        _activityFavoritesBinding = null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}