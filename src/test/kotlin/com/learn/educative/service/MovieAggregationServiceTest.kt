package com.learn.educative.service

import com.learn.educative.dataclass.Genre
import io.kotest.core.spec.style.FunSpec

class MovieAggregationServiceTest : FunSpec({

    lateinit var movieAggregationService: MovieAggregationService

    beforeTest {
        movieAggregationService = MovieAggregationService()
    }

    test("buildGenreCombinations") {
        var genres = listOf<Genre>()
        var combinations = movieAggregationService.buildGenreCombinations(genres)
//        println(combinations)
//        genres = listOf(Genre.COMEDY)
//        combinations = movieAggregationService.buildGenreCombinations(genres)
//        println(combinations)
        genres = listOf(Genre.COMEDY, Genre.DRAMA)
        combinations = movieAggregationService.buildGenreCombinations(genres)
        println(combinations)

    }


    test("getMovieMap") { }
})
