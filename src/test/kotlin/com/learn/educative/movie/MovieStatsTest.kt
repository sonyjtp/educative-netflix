package com.learn.educative.movie

import com.learn.educative.testdata.MovieData
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MovieStatsTest : FunSpec({

    test("fetchTopMovies") {
        val movieLists = MovieData.createLinkedLisNodes(
            listOf(
                listOf(3, 7, 9),
                listOf(2,3,5,8, 11),
                listOf(1),
                listOf(10),
                listOf(1,5,9,10)
                )
        )
        val nodes = mutableListOf<Int>()
        var nextNode = MovieStats().fetchTopMovies(movieLists)?.next
        nextNode?.display()
        while (nextNode != null) {
            nodes.add(nextNode!!.value)
            nextNode = nextNode?.next
        }
        nodes.joinToString("") shouldBe "1233557899101011"

    }

    test("findMedianAge") {
        MovieStats().findMedianAge(listOf(22, 35, 30, 25)) shouldBe 27.5
    }

    test("isChangingPopularity") {
        forAll(
           row(intArrayOf(1, 2, 2, 3), true),
           row(intArrayOf(4, 5, 6, 3, 4), false),
           row(intArrayOf(8, 8, 7, 6, 5, 4, 4, 1), true)
        ) { ratings, isChanging -> MovieStats().isChangingPopularity(ratings) shouldBe isChanging }
    }

})
