package com.dalmasmaka.rickandmortywatchbook.ui.characterdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dalmasmaka.rickandmortywatchbook.data.models.Character
import com.dalmasmaka.rickandmortywatchbook.data.models.Episode
import com.dalmasmaka.rickandmortywatchbook.databinding.ItemEpisodesBinding
import com.dalmasmaka.rickandmortywatchbook.databinding.ListItemCharacterBinding
import com.dalmasmaka.rickandmortywatchbook.ui.home.CharactersAdapter
import com.squareup.picasso.Picasso

class EpisodesAdapter(val itemClick: (episodeId: String) -> Unit) :
    RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {
    var episodes: List<Episode> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val binding: ItemEpisodesBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            ItemEpisodesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = episodes[position]
        with(holder) {
            binding.episodeNameTextView.text = episode.name
            binding.episodeCountTextView.text = episode.episode
            binding.episodeDateTextView.text = episode.airDate
        }
        holder.itemView.setOnClickListener {
            itemClick.invoke(episode.id.toString())
        }
    }

    override fun getItemCount(): Int = episodes.size
}