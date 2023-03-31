package com.learn.educative.dataclass

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MovieStackTest : FunSpec({

    test("push and pop") {
        val movieStack = MovieStack()
        val movie1 = Movie(1, "Kumbalangi Nights", Genre.DRAMA, rating = 8.5)
        val movie2 = Movie(2, "Sandesham", Genre.DRAMA, rating = 9.0)
        val movie3 = Movie(3, "Kireedam", Genre.DRAMA, rating = 8.9)
        movieStack.push(movie1)
        movieStack.push(movie2)
        movieStack.push(movie3)
        movieStack.getMaxRatedMovie() shouldBe movie2
        movieStack.pop() shouldBe movie3
        movieStack.getMaxRatedMovie() shouldBe movie2
    }
})
