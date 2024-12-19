package com.jonathanreategui.dragonballapp.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jonathanreategui.dragonballapp.databinding.ItemRowLayoutBinding
import com.jonathanreategui.dragonballapp.data.models.DragonBallCharacter

class ItemRowAdapter(
    private val onItemClick: (DragonBallCharacter) -> Unit
) : ListAdapter<DragonBallCharacter, ItemRowAdapter.CharacterViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DragonBallCharacter>() {
            override fun areItemsTheSame(oldItem: DragonBallCharacter, newItem: DragonBallCharacter): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DragonBallCharacter, newItem: DragonBallCharacter): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class CharacterViewHolder(private val binding: ItemRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: DragonBallCharacter) {
            Glide.with(binding.imageThumbnail.context)
                .load(character.image)
                .into(binding.imageThumbnail)
            binding.title.text = character.name
            binding.race.text = "Race: ${character.race}"
            binding.ki.text = "Ki: ${character.ki}"
            binding.root.setOnClickListener {
                onItemClick(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
