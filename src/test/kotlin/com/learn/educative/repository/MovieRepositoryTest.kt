package com.learn.educative.repository

import com.learn.educative.dataclass.Genre
import com.learn.educative.dataclass.Movie
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainAnyOf
import io.kotest.matchers.collections.shouldContainOnly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MovieRepositoryTest : FunSpec({

 lateinit var movieRepository: MovieRepository

 beforeTest {
     movieRepository = MovieRepository()
 }

    test("mapMoviesByGenre") {
        val movieMap = movieRepository.mapMoviesByGenre()
        movieMap.keys shouldContainAnyOf Genre.values().toSet()
    }

    test("getMoviesByGenre") {
        val movieMap = movieRepository.mapMoviesByGenre()
        val comedyMovies = movieMap[Genre.COMEDY]
        comedyMovies shouldNotBe null
        comedyMovies shouldContainOnly movieMap.values.flatten().filter {
                movie -> movie?.genre == Genre.COMEDY
        }
    }

    test("getMovieById") {
        movieRepository.getMovieById(1) shouldBe Movie(1, "Kumbalangi Nights", Genre.DRAMA, rating = 8.5)
    }
})
