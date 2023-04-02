package com.learn.educative.service

import com.learn.educative.dataclass.Genre
import com.learn.educative.dataclass.Movie
import com.learn.educative.repository.MovieRepository
import jakarta.inject.Singleton

@Singleton
internal class MovieAggregationService {

    private val movieRepository = MovieRepository()
    @Volatile
    private var movieMapCache:  Map<Genre, List<Movie?>>? = null

    fun buildGenreCombinations(genres: List<Genre>): List<String> {
        movieMapCache = getMovieMap()
        val combinations = mutableListOf<String>()
        if (genres.isNotEmpty()) {
            buildCombinations(0, combinations, mutableListOf(), genres)
            movieMapCache = null
        }
        return combinations
    }

    /**
     * Recursive function to generate all combinations of movies of a genre
     *
     * @param index index of the genre in the genre list
     * @param combinations combinations of movies
     * @param path number of movies in a combination
     */
    private fun buildCombinations(index: Int, combinations: MutableList<String>, path: MutableList<String>,
                                  genres: List<Genre>){
        if (path.size == genres.size) {
            combinations.add(path.joinToString(""))
            return
        } else {
            movieMapCache?.get(genres[index])?.let { movies ->
                for (movie in movies) {
                    path.add("$movie;")
                    buildCombinations(index + 1, combinations,  path, genres)
                    if (path.size > 0) path.removeAt(path.size - 1)
                }
            }
        }
    }

    private fun getMovieMap() = movieRepository.mapMoviesByGenre()

}
