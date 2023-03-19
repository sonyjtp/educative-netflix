package com.learn.educative.dataclass

import java.util.*

internal  data class MovieStack (
    private val maxSize: Int = 5,
    val maxRatingStack: Stack<Movie> = Stack<Movie>(),
    val viewStack: Stack<Movie> = Stack<Movie>()
) {
    fun pop(): Movie {
        maxRatingStack.pop()
        val top = viewStack.peek()
        viewStack.pop()
        return top
    }

    fun push(movie: Movie) {
        viewStack.push(movie)
        if (maxRatingStack.isNotEmpty() && maxRatingStack.peek().rating > movie.rating) {
            maxRatingStack.push(maxRatingStack.peek())
        } else maxRatingStack.push(movie)
    }

    fun getMaxRatedMovie(): Movie = maxRatingStack.peek()

}
