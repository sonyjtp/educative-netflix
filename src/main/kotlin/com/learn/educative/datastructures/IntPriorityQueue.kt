package com.learn.educative.datastructures

import java.util.*

internal class IntPriorityQueue {
    private val firstHalf = PriorityQueue { a: Int, b: Int -> b - a }
    private val secondHalf = PriorityQueue { a: Int, b: Int -> a - b }

    fun insert(num: Int) {
        if (firstHalf.isEmpty() || firstHalf.peek() > num) firstHalf.offer(num) else secondHalf.offer(num)
        rearrange()
    }

    private fun rearrange() {
        if (firstHalf.size > secondHalf.size + 1) secondHalf.offer(firstHalf.poll())
        else if (firstHalf.size < secondHalf.size) firstHalf.offer(secondHalf.poll())
    }

    fun findMedian(): Double {
        return if (firstHalf.size == secondHalf.size) {
            firstHalf.peek() / 2.0 + secondHalf.peek() / 2.0
        } else firstHalf.peek().toDouble()
    }
}
