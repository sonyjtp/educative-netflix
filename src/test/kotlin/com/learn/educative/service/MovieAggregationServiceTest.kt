package com.learn.educative.service

import com.learn.educative.dataclass.Genre
import com.learn.educative.testdata.MovieData
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MovieAggregationServiceTest : FunSpec({

    lateinit var movieAggregationService: MovieAggregationService

    beforeSpec {
        movieAggregationService = MovieAggregationService()
    }

    test("buildGenreCombinations") {
        forAll(
            row (listOf(), emptyList()),
            row (
                listOf(Genre.COMEDY),
                MovieData.getCombinations(listOf(5), listOf(10))
            ),
            row (
                listOf(Genre.COMEDY, Genre.DRAMA),
                    MovieData.getCombinations(
                        listOf(5, 1),
                        listOf(5, 2),
                        listOf(10, 1),
                        listOf(10, 2)
                    )
                )
        ) { genres, combinations ->
            movieAggregationService.buildGenreCombinations(genres) shouldBe combinations
        }
    }

    test("buildMarathonCombinations") {
        val movies = MovieData.getMovieList(listOf(1, 2, 3))
        println(movieAggregationService.buildMovieMarathon(movies))
    }


    test("getMovieMap") { }
})
