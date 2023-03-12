package com.learn.educative.helper

import java.util.PriorityQueue

internal class MedianAge {
    private var firstHalf: PriorityQueue<Int> = PriorityQueue { a: Int, b: Int -> b - a }
    private var secondHalf: PriorityQueue<Int> = PriorityQueue { a: Int, b: Int -> a - b }

    fun insert(num: Int) {
        if (firstHalf.isEmpty() || firstHalf.peek() > num) firstHalf.offer(num) else secondHalf.offer(num)
        if (firstHalf.size > secondHalf.size + 1) secondHalf.offer(firstHalf.poll())
        else if (firstHalf.size < secondHalf.size) firstHalf.offer(secondHalf.poll())
    }

    fun findMedian(): Double {
        return if (firstHalf.size == secondHalf.size) {
            firstHalf.peek() / 2.0 + secondHalf.peek() / 2.0
        } else firstHalf.peek().toDouble()
    }
}
