package com.learn.educative.helper

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PopularityTest : FunSpec({

    test("insert") {
        forAll(
           row(intArrayOf(1, 2, 2, 3), true),
           row(intArrayOf(4, 5, 6, 3, 4), false),
           row(intArrayOf(8, 8, 7, 6, 5, 4, 4, 1), true)
        ) { ratings, isChanging -> Popularity.isChangingPopularity(ratings) shouldBe isChanging }
    }
})
