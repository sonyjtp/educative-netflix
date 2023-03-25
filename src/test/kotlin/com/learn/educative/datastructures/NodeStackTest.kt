package com.learn.educative.datastructures

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class NodeStackTest : FunSpec({

    test("verifySession") {
        val nodeStack = NodeStack<Int>()
        forAll(
            row(listOf(1, 2, 3, 4, 5, 6), listOf(5, 6, 3, 4, 1, 2), false),
            row(listOf(1, 2, 3, 4, 5, 6), listOf(5, 6, 4, 3, 2, 1), true)
        ) { pushSequence, popSequence, result -> nodeStack.verifySession(pushSequence, popSequence) shouldBe result }
    }
})
