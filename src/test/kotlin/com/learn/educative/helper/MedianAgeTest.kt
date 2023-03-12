package com.learn.educative.helper

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MedianAgeTest : FunSpec({
    test("insert") {
        val medianAge = MedianAge()
        medianAge.insert(22)
        medianAge.insert(35)
        medianAge.findMedian() shouldBe 28.5
        medianAge.insert(30)
        medianAge.findMedian() shouldBe 30.0
        medianAge.insert(25)
        medianAge.findMedian() shouldBe 27.5
    }
})
