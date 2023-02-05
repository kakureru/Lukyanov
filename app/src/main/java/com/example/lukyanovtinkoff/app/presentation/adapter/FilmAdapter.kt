package com.example.lukyanovtinkoff.app.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lukyanovtinkoff.R
import com.example.lukyanovtinkoff.databinding.FilmCardLayoutBinding
import com.example.lukyanovtinkoff.domain.model.Film

class FilmAdapter(
    private val onClick: (filmId: Int) -> Unit,
    private val onLongClick: (film: Film) -> Unit
) : ListAdapter<Film, FilmAdapter.FilmViewHolder>(DiffCallback), Filterable {

    var filmList: ArrayList<Film> = ArrayList()
    var filmListFiltered: ArrayList<Film> = ArrayList()

    inner class FilmViewHolder(
        private val context: Context,
        private var binding: FilmCardLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener { onClick(getItem(adapterPosition).id) }
                root.setOnLongClickListener {
                    onLongClick(getItem(adapterPosition))
                    favouriteIcon.isVisible = !binding.favouriteIcon.isVisible
                    true
                }
            }
        }

        fun bind(film: Film) {
            binding.apply {
                titleTextView.text = film.name
                genreTextView.text = context.resources.getString(
                    R.string.genre_with_year,
                    capitalize(film.genres[0]),
                    film.year
                )
                favouriteIcon.isVisible = film.favourite
                Glide.with(context)
                    .load(film.posterUrlPreview)
                    .into(posterPreview)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            parent.context,
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

    fun addData(list: List<Film>) {
        filmList = list as ArrayList<Film>
        filmListFiltered = filmList
        submitList(filmListFiltered)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                Log.d("TAG", charString)
                filmListFiltered =
                    if (charString.isEmpty())
                        filmList
                    else {
                        val filteredList = ArrayList<Film>()
                        filmList.filter {
                            it.name.lowercase().contains(constraint.toString().lowercase())
                        }.forEach { filteredList.add(it) }
                        filteredList
                    }
                return FilterResults().apply { values = filmListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filmListFiltered =
                    if (results?.values == null)
                        ArrayList()
                    else
                        results.values as ArrayList<Film>
                submitList(filmListFiltered)
            }
        }
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

    private fun capitalize(s: String): String =
        s.substring(0, 1).uppercase() + s.substring(1).lowercase()
}