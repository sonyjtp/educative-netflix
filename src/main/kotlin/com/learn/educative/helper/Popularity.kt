package com.learn.educative.helper

internal object Popularity {

    fun isChangingPopularity(ratings: IntArray): Boolean {
        var (inc, dec) = listOf(true, true)
        for (i in 0 until ratings.size - 1) {
            if (ratings[i] > ratings[i + 1]) inc = false
            if (ratings[i] < ratings[i + 1]) dec = false
        }
        return inc || dec
    }
}
