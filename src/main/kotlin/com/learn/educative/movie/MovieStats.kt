package com.learn.educative.movie

import com.learn.educative.datastructures.IntPriorityQueue
import com.learn.educative.datastructures.LinkedListNode
import com.learn.educative.util.NodeUtils


internal class MovieStats {
    private var allAges = IntPriorityQueue()

    fun fetchTopMovies(topMoviesByCountries: List<LinkedListNode>) = NodeUtils.findRootNodeByData(topMoviesByCountries)

    fun findMedianAge(ages: List<Int>): Double {
        for (age in ages) {
            allAges.insert(age)
        }
        return allAges.findMedian()
    }

    fun isChangingPopularity(ratings: IntArray): Boolean {
        var (inc, dec) = listOf(true, true)
        for (i in 0 until ratings.size - 1) {
            if (ratings[i] > ratings[i + 1]) inc = false
            if (ratings[i] < ratings[i + 1]) dec = false
        }
        return inc || dec
    }
}
