package com.learn.educative.stats

import com.learn.educative.dataclass.EventType
import com.learn.educative.dataclass.SessionWindow
import io.kotest.core.spec.style.FunSpec
import java.util.*

internal class SessionStatsTest : FunSpec({

    lateinit var sessionStats: SessionStats

    beforeSpec {
        sessionStats = SessionStats()
    }

    test("findSessionEventsMedians") {
        val sessionWindow = SessionWindow(
            minQueue =  PriorityQueue(), maxQueue = PriorityQueue(),
            EventType.UNKNOWN, windowSize = 3, eventCounts = listOf(1, 3, -1, -3, 5, 3, 6, 7)
        )
        print(sessionStats.findSessionEventsMedians(sessionWindow))
    }

})
