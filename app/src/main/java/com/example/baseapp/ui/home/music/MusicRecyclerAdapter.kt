package com.example.baseapp.ui.home.music

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseapp.databinding.LayoutMusicListBinding
import com.example.baseapp.ui.home.music.MusicRecyclerAdapter.MusicViewHolder
import com.example.baseapp.util.SongItemClickListener
import com.example.domain.entity.Song

class MusicRecyclerAdapter(
  private val title: String,
  private val songItemClickListener: SongItemClickListener
) : RecyclerView.Adapter<MusicViewHolder>() {

  private val list = arrayListOf<Song>()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): MusicViewHolder {
    val binding = LayoutMusicListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return MusicViewHolder(binding)
  }

  override fun onBindViewHolder(
    holder: MusicViewHolder,
    position: Int
  ) {
    holder.bind()
  }

  override fun getItemCount(): Int = 1

  fun addResponse(responses: List<Song>) {
    list.addAll(responses)
    notifyDataSetChanged()
  }

  inner class MusicViewHolder(private val binding: LayoutMusicListBinding) : RecyclerView.ViewHolder(
    binding.root
  ) {
    fun bind() {
      with(binding) {
        listTitle.text = title
        if (listRecyclerView.adapter == null) {
          listRecyclerView.adapter = SongsRecyclerAdapter()
        }
        with(listRecyclerView.adapter as SongsRecyclerAdapter) {
          addResponse(list)
          setOnClickListener(songItemClickListener)
        }
      }
    }
  }
}