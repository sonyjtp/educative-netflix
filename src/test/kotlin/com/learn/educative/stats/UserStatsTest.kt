package com.learn.educative.stats

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class UserStatsTest : FunSpec({

    lateinit var userStats:UserStats

    beforeSpec {
        userStats = UserStats()
    }

    test("findMedianAge") {
        userStats.findMedianAge(listOf(22, 35, 30, 25)) shouldBe 27.5
    }
})
