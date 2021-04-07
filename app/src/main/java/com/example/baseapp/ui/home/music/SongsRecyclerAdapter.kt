package com.example.baseapp.ui.home.music

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.baseapp.databinding.ItemSongBinding
import com.example.baseapp.ui.home.music.SongsRecyclerAdapter.SongViewHolder
import com.example.baseapp.util.SongItemClickListener
import com.example.domain.entity.SongResponse

class SongsRecyclerAdapter : RecyclerView.Adapter<SongViewHolder>() {

  private var list = listOf<SongResponse>()

  private lateinit var songItemClickListener: SongItemClickListener

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
    list = responses
    notifyDataSetChanged()
  }

  fun setOnClickListener(songItemClickListener: SongItemClickListener) {
    this.songItemClickListener = songItemClickListener
  }

  inner class SongViewHolder(private val binding: ItemSongBinding) : ViewHolder(binding.root) {
    fun bind(position: Int) {
      val songResponse = list[position]
      with(binding) {
        songTitle.text = songResponse.trackName
        songArtist.text = songResponse.artistName
        imgUrl = songResponse.artworkUrl100
        binding.root.setOnClickListener {
          songItemClickListener.onSongItemClick(list, position)
        }
      }
    }
  }
}



