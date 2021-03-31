package com.example.baseapp.ui.home.music

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.baseapp.databinding.ItemSongBinding
import com.example.baseapp.ui.home.music.SongsRecyclerAdapter.SongViewHolder
import com.example.domain.entity.SongResponse

class SongsRecyclerAdapter : RecyclerView.Adapter<SongViewHolder>() {

  private val list = arrayListOf<SongResponse>()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): SongViewHolder {
    val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return SongViewHolder(binding)
  }

  override fun onBindViewHolder(
    holder: SongViewHolder,
    position: Int
  ) {
    holder.bind(holder.bindingAdapterPosition)
  }

  override fun getItemCount(): Int = list.size

  fun addResponse(responses: List<SongResponse>) {
    list.addAll(responses)
    notifyDataSetChanged()
  }

  inner class SongViewHolder(private val binding: ItemSongBinding) : ViewHolder(binding.root) {
    fun bind(position: Int) {
      val songResponse = list[position]
      binding.songTitle.text = songResponse.trackName
      binding.songArtist.text = songResponse.artistName
      Glide.with(binding.root)
        .load(songResponse.artworkUrl100)
        .centerCrop()
        .into(binding.songPreview)
    }
  }
}