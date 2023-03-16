package com.learn.educative.datastructures

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class IntPriorityQueueTest : FunSpec({
    test("insert") {
        val medianAge = IntPriorityQueue()
        medianAge.insert(22)
        medianAge.insert(35)
        medianAge.findMedian() shouldBe 28.5
        medianAge.insert(30)
        medianAge.findMedian() shouldBe 30.0
        medianAge.insert(25)
        medianAge.findMedian() shouldBe 27.5
    }
})
