package com.fariq.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmEntities")
data class FilmEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: String,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "image")
        var image: String,

        @ColumnInfo(name = "desc")
        var desc: String,

        @ColumnInfo(name = "genre")
        var genre: String,

        @ColumnInfo(name = "year")
        var year: Int,

        @ColumnInfo(name = "favorite")
        var favorite: Boolean,

        @ColumnInfo(name = "type")
        var type: String
)