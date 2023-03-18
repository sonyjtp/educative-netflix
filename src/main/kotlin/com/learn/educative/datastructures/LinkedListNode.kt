package com.learn.educative.datastructures
import mu.KotlinLogging

internal data class LinkedListNode(
    var key:Int = 0,
    var value:Int,
    var frequency: Int = 0,
    var next: LinkedListNode? = null,
    var prev: LinkedListNode? = null) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    fun display(head: LinkedListNode? = this) {
        var temp = head
        logger.debug { "LinkedList chain starting at node ${temp?.value}: " }
        while (temp != null) {
            logger.info("Node data: ${temp.value}")
            temp = temp.next
        }
    }

    override fun toString(): String {
        return "LLNode(key:$key, value:$value, frequency:$frequency, prev:${prev?.key}, next:${next?.key})"
    }
}

