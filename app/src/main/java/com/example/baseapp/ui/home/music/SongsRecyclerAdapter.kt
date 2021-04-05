package com.example.baseapp.ui.home.music

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST
import android.support.v4.media.MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE
import android.support.v4.media.MediaMetadataCompat.METADATA_KEY_MEDIA_ID
import android.support.v4.media.MediaMetadataCompat.METADATA_KEY_TITLE
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.baseapp.databinding.ItemSongBinding
import com.example.baseapp.ui.home.music.SongsRecyclerAdapter.SongViewHolder
import com.example.baseapp.util.SongItemClickListener
import com.example.domain.entity.SongResponse

class SongsRecyclerAdapter : RecyclerView.Adapter<SongViewHolder>() {

  private val list = arrayListOf<SongResponse>()

  private lateinit var songItemClickListener: SongItemClickListener

  private var playlist = emptyList<MediaMetadataCompat>()

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
          songItemClickListener.onSongItemClick(mapToMediaMetaDataCompatList(list), position)
        }
      }
    }
  }

  fun mapToMediaMetaDataCompatList(list: List<SongResponse>): List<MediaMetadataCompat> {
    return list.map {
      MediaMetadataCompat.Builder()
        .putString(METADATA_KEY_ALBUM_ARTIST, it.artistName)
        .putString(METADATA_KEY_TITLE, it.trackName)
        .putString(METADATA_KEY_DISPLAY_TITLE, it.trackCensoredName)
        .putString(METADATA_KEY_MEDIA_ID, it.previewUrl)
        .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, it.artworkUrl100)
        .build()
    }
  }
}



