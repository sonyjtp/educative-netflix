package com.learn.educative.stats

import com.learn.educative.datastructures.LinkedListNode
import com.learn.educative.util.NodeUtils


internal object MovieStats {

    fun fetchTopMovies(topMoviesByCountries: List<LinkedListNode<Int>>) = NodeUtils.findRootNodeByData(
        topMoviesByCountries
    )

    fun isChangingPopularity(ratings: IntArray): Boolean {
        var (inc, dec) = listOf(true, true)
        for (i in 0 until ratings.size - 1) {
            if (ratings[i] > ratings[i + 1]) inc = false
            if (ratings[i] < ratings[i + 1]) dec = false
        }
        return inc || dec
    }
}
