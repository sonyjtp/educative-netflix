package com.learn.educative.stats

import com.learn.educative.dataclass.EventType
import com.learn.educative.dataclass.SessionWindow
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import java.util.*

internal class SessionStatsTest : FunSpec({

    lateinit var sessionStats: SessionStats

    beforeSpec {
        sessionStats = SessionStats()
    }

    test("findSessionEventsMedians") {
        forAll (
//            row(listOf(1, 2), 1, listOf(1.0, 2.0)),
            row(listOf(1, 3, -1, -3, 5, 3, 6, 7), 3, listOf(1.0, -1.0, -1.0, 3.0, 5.0, 6.0)),
        ) { eventCounts, windowSize, result ->
            val sessionWindow = SessionWindow(
                minQueue =  PriorityQueue(Collections.reverseOrder()), maxQueue = PriorityQueue(), EventType.UNKNOWN,
                windowSize = windowSize, eventCounts = eventCounts)
            sessionStats.findSessionEventsMedians(sessionWindow) shouldBe result
        }
    }
})
