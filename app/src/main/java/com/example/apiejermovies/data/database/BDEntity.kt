package com.example.apiejermovies.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class BDEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo( "id") val id: Int =0,
    @ColumnInfo( "title") val title: String,
    @ColumnInfo("image") val image: String
)
