package com.learn.educative.repository

import com.learn.educative.dataclass.Genre
import com.learn.educative.dataclass.Movie


internal class MovieRepository {

    //temporary
    private val movieMap = mapOf(
        1 to Movie(1, "Kumbalangi Nights", Genre.DRAMA, rating = 8.5),
        2 to Movie(2, "Sandesham", Genre.DRAMA, rating = 9.0),
        3 to Movie(3, "Kireedam", Genre.DRAMA, rating = 8.9),
        4 to Movie(4, "Angamaly Diaries", Genre.ACTION, rating = 7.9),
        5 to Movie(5, "In Harihar Nagar", Genre.COMEDY, rating = 8.6),
        6 to Movie(6, "#Home", Genre.FAMILY, rating = 8.8),
        7 to Movie(7, "Bhoothakaalam", Genre.HORROR, rating = 7.5),
        8 to Movie(8, "Traffic", Genre.THRILLER, rating = 8.1),
        9 to Movie(9, "Gangs of Wasseypur", Genre.ACTION, rating = 8.2),
        10 to Movie(10, "The Hangover", Genre.COMEDY, rating = 8.2),
        11 to Movie(11, "Little Miss Sunshine", Genre.FAMILY, rating = 7.8),
        12 to Movie(12, "The Shining", Genre.HORROR, rating = 8.4),
        13 to Movie(13, "Joker", Genre.THRILLER, rating = 8.4),
        14 to Movie(14, "My Dear Kuttichaathan", Genre.FANTASY, rating = 7.8),
        15 to Movie(15, "Avatar", Genre.FANTASY, rating = 7.9)
    )

    //temporary
    fun mapMoviesByGenre() = mapOf(
        Genre.ACTION to getMoviesByGenre(Genre.ACTION),
        Genre.COMEDY to getMoviesByGenre(Genre.COMEDY),
        Genre.DRAMA to getMoviesByGenre(Genre.DRAMA),
        Genre.FAMILY to getMoviesByGenre(Genre.FAMILY),
        Genre.FANTASY to getMoviesByGenre(Genre.FANTASY),
        Genre.HORROR to getMoviesByGenre(Genre.HORROR),
        Genre.THRILLER to getMoviesByGenre(Genre.THRILLER)
    )

    //temporary
    fun getMoviesByGenre(genre: Genre): List<Movie?> {
        return when(genre) {
            Genre.ACTION -> listOf(movieMap[4], movieMap[9])
            Genre.COMEDY -> listOf(movieMap[5], movieMap[10])
            Genre.DRAMA -> listOf(movieMap[1], movieMap[2])
            Genre.FAMILY -> listOf(movieMap[6], movieMap[11])
            Genre.FANTASY -> listOf(movieMap[14], movieMap[15])
            Genre.HORROR -> listOf(movieMap[7], movieMap[12])
            Genre.THRILLER -> listOf(movieMap[8], movieMap[13])
            Genre.UNKNOWN -> listOf()
        }
    }

}
