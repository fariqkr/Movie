package com.fariq.moviecatalogue.ui.favoriteTvShows

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fariq.moviecatalogue.R
import com.fariq.moviecatalogue.data.source.local.entity.FilmEntity
import com.fariq.moviecatalogue.databinding.ItemTvShowBinding
import com.fariq.moviecatalogue.ui.details.DetailActivity

class FavoriteTvShowAdapter : PagedListAdapter<FilmEntity, FavoriteTvShowAdapter.CourseViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FilmEntity>() {
            override fun areItemsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemTvShowBinding = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemTvShowBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val film = getItem(position)
        if (film != null) {
            holder.bind(film)
        }
    }

    class CourseViewHolder(private val binding: ItemTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(filmEntity: FilmEntity) {
            binding.title.text = filmEntity.title
            binding.year.text = "(${filmEntity.year})"
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_FILM, filmEntity.id)
                intent.putExtra(DetailActivity.TYPE, "TV Show")
                itemView.context.startActivity(intent)
            }

            Glide.with(itemView.context)
                    .load(filmEntity.image)
                    .centerCrop()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .transform(RoundedCorners(5))
                    .into(binding.image)

        }
    }
}