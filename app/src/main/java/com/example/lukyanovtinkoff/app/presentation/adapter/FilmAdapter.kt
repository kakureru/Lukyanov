package com.example.lukyanovtinkoff.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lukyanovtinkoff.databinding.FilmCardLayoutBinding
import com.example.lukyanovtinkoff.domain.model.Film

class FilmAdapter() : ListAdapter<Film, FilmAdapter.FilmViewHolder>(DiffCallback) {

    inner class FilmViewHolder(
        private var binding: FilmCardLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.apply {
                titleTextView.text = film.name
                genreTextView.text = film.genres[0]
                favouriteIcon.isVisible = film.favourite
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            FilmCardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem == newItem
            }
        }
    }
}