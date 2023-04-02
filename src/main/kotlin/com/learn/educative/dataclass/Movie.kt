package com.learn.educative.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String = "",
    val genre: Genre = Genre.UNKNOWN,
    val rating: Double = 0.0)
