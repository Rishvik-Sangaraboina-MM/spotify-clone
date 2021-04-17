package com.example.baseapp.ui.home.music

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.baseapp.databinding.ItemSongBinding
import com.example.baseapp.ui.home.music.SongsRecyclerAdapter.SongViewHolder
import com.example.baseapp.util.SongItemClickListener
import com.example.domain.entity.Song

class SongsRecyclerAdapter : RecyclerView.Adapter<SongViewHolder>() {

  private var list = listOf<Song>()

  var isGridLayout: Boolean = false

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

  fun addResponse(responses: List<Song>) {
    list = responses
    notifyDataSetChanged()
  }

  fun setOnClickListener(songItemClickListener: SongItemClickListener) {
    this.songItemClickListener = songItemClickListener
  }

  inner class SongViewHolder(private val binding: ItemSongBinding) : ViewHolder(binding.root) {
    fun bind(position: Int) {
      val song = list[position]
      if (isGridLayout)
        binding.itemSong.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
      with(binding) {
        songTitle.text = song.trackName
        songArtist.text = song.artistName
        imgUrl = song.artworkUrl100
        binding.root.setOnClickListener {
          songItemClickListener.onSongItemClick(list, position)
        }
      }
    }
  }
}