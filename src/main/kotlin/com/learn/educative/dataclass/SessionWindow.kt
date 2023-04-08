package com.learn.educative.dataclass

import java.util.*

internal data class SessionWindow(
    val minQueue: PriorityQueue<Int>,
    val maxQueue: PriorityQueue<Int>,
    val eventType: EventType,
    val windowSize: Int,
    val eventCounts: List<Int>)
