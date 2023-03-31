package com.learn.educative.service

import com.learn.educative.dataclass.Genre
import com.learn.educative.dataclass.Movie
import com.learn.educative.repository.MovieRepository
import jakarta.inject.Singleton

@Singleton
internal class MovieAggregationService {

    private val movieRepository = MovieRepository()
    private var movieMapCache:  Map<Genre, List<Movie?>>? = null // thread unsafe

    fun buildGenreCombinations(genres: List<Genre>): List<String> {
        movieMapCache = getMovieMap()
        val combinations = mutableListOf<String>()
        if (genres.isNotEmpty()) {
            combinations.addAll(backTrack(0, combinations, mutableListOf(), genres))
            movieMapCache = null
        }
        return combinations
    }

    /**
     * Function to generate all combinations of movies of a genre
     */
    private fun backTrack(index: Int, combinations: MutableList<String>, path: MutableList<String>, genres: List<Genre>): List<String> {
        if (path.size == genres.size) {
            combinations.add(path.joinToString(""))
        } else {
            movieMapCache?.get(genres[index])?.let { movies ->
                for (movie in movies) {
                    path.add("$movie;")
                    backTrack(index + 1, combinations,  path, genres)
                    if (path.size > 0) path.removeAt(path.size - 1)
                }
            }
        }
        return combinations
    }

    private fun getMovieMap() = movieRepository.mapMoviesByGenre()

}
