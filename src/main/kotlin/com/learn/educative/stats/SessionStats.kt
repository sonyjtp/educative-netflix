package com.learn.educative.stats

import com.learn.educative.dataclass.SessionWindow
import mu.KotlinLogging
import java.util.*


internal class SessionStats {

    companion object {
        private val logger = KotlinLogging.logger(this::class.java.name)
    }
    fun findSessionEventsMedians(sessionWindow: SessionWindow): List<Double> {
        logger.info { "Finding session event medians" }
        val medians = mutableListOf<Double>()
        val eventsToRemove = mutableMapOf<Int, Int>()
        var i = 0
        while (i < sessionWindow.windowSize) { // first add elements to minQueue
            sessionWindow.minQueue.add(sessionWindow.eventCounts[i])
            i ++
        }
        logger.debug { "No. of events added: $i" }
        moveToOtherQueue(sessionWindow) // move half of the elements to the other queue
        while (true) {
             medians.add(
                if (sessionWindow.windowSize and 1  == 1) sessionWindow.minQueue.peek().toDouble()
                else (sessionWindow.minQueue.peek() + sessionWindow.maxQueue.peek() / 2.0)
             )
            logger.debug { "Added median: ${medians.last()}" }
            if (i >= sessionWindow.eventCounts.size) break // all events have been processed
            // remove the (i - windowSize)th event, add new ith event.
            val (outgoing, incoming) = listOf(sessionWindow.eventCounts[i - sessionWindow.windowSize],
                sessionWindow.eventCounts[i])
            // store the outgoing number in the map
            eventsToRemove[outgoing] = eventsToRemove.getOrDefault(outgoing, 0) + 1
            logger.debug { "Incoming: $incoming" }
            logger.debug { "Outgoing: $outgoing" }
            logger.debug { "Event(s) to remove: ${eventsToRemove[outgoing]}" }
            balanceQueues(sessionWindow, incoming, outgoing)
            removeFromQueues(eventsToRemove, sessionWindow)
            i ++
        }
        return medians
    }

    private fun moveToOtherQueue(sessionWindow: SessionWindow) {
        for (i in 0 until sessionWindow.windowSize / 2) {
            sessionWindow.maxQueue.add(sessionWindow.minQueue.peek())
            sessionWindow.minQueue.poll()
        }
        logger.debug { "Min queue: ${sessionWindow.minQueue}" }
        logger.debug { "Max queue: ${sessionWindow.maxQueue}" }
    }

    private fun balanceQueues(sessionWindow: SessionWindow, incoming: Int, outgoing: Int) {
        var balance = 0
            //remove outgoing
        balance += if (outgoing <= sessionWindow.minQueue.peek()) -1 else 1 // how will outgoing be > minQueue.peek()?
        if (sessionWindow.minQueue.isNotEmpty() && incoming <= sessionWindow.minQueue.peek()) {
                balance ++
                sessionWindow.minQueue.add(incoming)
            logger.debug { "Added incoming to minQueue" }
        } else {
            sessionWindow.maxQueue.add(incoming)
             logger.debug { "Added incoming to maxQueue" }
            balance --
        }
        if (balance < 0) {
            sessionWindow.minQueue.add(sessionWindow.maxQueue.peek())
            sessionWindow.maxQueue.poll()
            balance++
        }
        // Re-balance maxQueue
        if (balance > 0) {
            sessionWindow.maxQueue.add(sessionWindow.minQueue.peek())
            sessionWindow.minQueue.poll()
        }
    }

    private fun removeFromQueues(nums: MutableMap<Int, Int>,sessionWindow: SessionWindow) {
        removeFromQueue(nums, sessionWindow.minQueue)
        removeFromQueue(nums, sessionWindow.maxQueue)
    }

    private fun removeFromQueue(nums: MutableMap<Int, Int>, queue: PriorityQueue<Int>) {
        while (queue.isNotEmpty() && nums.getOrDefault(queue.peek(), -1) > 0) {
            nums[queue.peek()] = nums[queue.peek()]!! - 1
            queue.poll()
        }
    }
}
