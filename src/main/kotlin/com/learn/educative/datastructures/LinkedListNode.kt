package com.learn.educative.datastructures
import kotlinx.serialization.Serializable
import mu.KotlinLogging

@Serializable
internal data class LinkedListNode<T>(
    var key:Int = 0,
    var value:Int = 0,
    var frequency: Int = 0,
    var next: LinkedListNode<T>? = null,
    var prev: LinkedListNode<T>? = null) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    fun display(head: LinkedListNode<T>? = this) {
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

