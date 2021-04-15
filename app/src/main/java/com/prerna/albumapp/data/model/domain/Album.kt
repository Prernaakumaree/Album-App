package com.prerna.albumapp.data.model.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class Album(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "title")
    val title: String
)