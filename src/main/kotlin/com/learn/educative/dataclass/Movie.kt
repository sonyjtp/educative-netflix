package com.learn.educative.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class Movie( val id: Int, val title: String = "", val rating: Double = 0.0)
