package org.coursera.littlelemon.data.menu

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class MenuItem(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    @SerialName("image") val imageUrl: String,
    val category: String
)

@Serializable
data class MenuResponse(
    val menu: List<MenuItem>
)

