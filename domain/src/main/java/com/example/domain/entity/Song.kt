package com.example.domain.entity

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class Song(
  val artistName: String,
  val artworkUrl100: String,
  val previewUrl: String,
  val trackName: String,
) : Parcelable {

  constructor(parcel: Parcel) : this(
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!,
    parcel.readString()!!
  )

  companion object CREATOR : Creator<Song> {
    override fun createFromParcel(parcel: Parcel): Song {
      return Song(parcel)
    }

    override fun newArray(size: Int): Array<Song?> {
      return arrayOfNulls(size)
    }
  }

  override fun describeContents(): Int = 0

  override fun writeToParcel(
    dest: Parcel,
    flags: Int
  ) {
    dest.writeString(artistName)
    dest.writeString(artworkUrl100)
    dest.writeString(previewUrl)
    dest.writeString(trackName)
  }
}
