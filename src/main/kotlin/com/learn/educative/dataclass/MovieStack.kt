package com.learn.educative.dataclass

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import mu.KotlinLogging
import java.util.*

@Serializable
internal  data class MovieStack (
    private val maxSize: Int = 5,
    @Contextual
    private val maxRatingStack: Stack<Movie> = Stack<Movie>(),
    @Contextual
    private val viewStack: Stack<Movie> = Stack<Movie>()
) {
    private val logger = KotlinLogging.logger(this::class.java.name)
    private val pushSequence = mutableListOf<Int>()
    private val popSequence = mutableListOf<Int>()

    fun pop(): Movie {
        logger.debug { "Retrieving the last movie from movie stack" }
        maxRatingStack.pop()
        val top = viewStack.peek()
        popSequence.add(top.id)
        viewStack.pop()
        logger.debug { "Last movie: $top" }
        return top
    }

    fun push(movie: Movie) {
        logger.debug { "Adding movie ${movie.title} to the movie stack" }
        viewStack.push(movie)
        pushSequence.add(movie.id)
        maxRatingStack.push(
            if (maxRatingStack.isNotEmpty() && getMaxRatedMovie().rating > movie.rating) getMaxRatedMovie() else movie
        )
        logger.debug { "Movie with max rating: ${getMaxRatedMovie()}"}
    }

    fun getMaxRatedMovie(): Movie = maxRatingStack.peek()

}
