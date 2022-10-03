package com.dalmasmaka.rickandmortywatchbook.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dalmasmaka.rickandmortywatchbook.databinding.ListItemCharacterBinding
import com.dalmasmaka.rickandmortywatchbook.data.models.Character

import com.squareup.picasso.Picasso

class CharactersAdapter( private val itemClick: (characterId:String) -> Unit) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    var characterItems:List<Character> = mutableListOf()
    set(value)  {
        field=value;
        notifyDataSetChanged()
    }
    class ViewHolder(val binding: ListItemCharacterBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterItems.get(position)
        with(holder) {
            binding.name.text = character.name
            binding.species.text = character.species
            Picasso.get().load(character.image).into(binding.characterImage)
        }
        holder.itemView.setOnClickListener {
            itemClick.invoke(character.id.toString())
        }
    }

    override fun getItemCount(): Int = characterItems.size
}