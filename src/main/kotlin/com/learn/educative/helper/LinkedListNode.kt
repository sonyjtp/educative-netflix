package com.learn.educative.helper
import mu.KotlinLogging

internal data class LinkedListNode(
    var key:Int = 0,
    var data:Int,
    var next: LinkedListNode? = null,
    var arbitraryPointer: LinkedListNode? = null) {

    private val logger = KotlinLogging.logger {}

    fun display(head: LinkedListNode? = this) {
        var temp = head
        logger.debug { "LinkedList chain starting at node ${temp?.data}: " }
        while (temp != null) {
            logger.info("Node data: ${temp.data}")
            temp = temp.next
        }
    }
}

