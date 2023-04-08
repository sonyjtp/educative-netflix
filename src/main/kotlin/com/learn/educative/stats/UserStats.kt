package com.learn.educative.stats

import com.learn.educative.datastructures.IntPriorityQueue


internal class UserStats {
    private var allAges = IntPriorityQueue()

    fun findMedianAge(ages: List<Int>): Double {
        for (age in ages) {
            allAges.insert(age)
        }
        return allAges.findMedian()
    }
}
