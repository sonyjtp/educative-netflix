package com.learn.educative.cache

import com.learn.educative.datastructures.DoublyLinkedList
import com.learn.educative.datastructures.LinkedListNode

internal class MostRecentlyWatchedCache(var capacity: Int) {
    private var cache = HashMap<Int, LinkedListNode>(capacity)
    private var cachedNodes: DoublyLinkedList = DoublyLinkedList()

    fun get(key: Int): LinkedListNode? {
        return cache[key]?.let { node ->
            val value = node.data
            cachedNodes.let {
                it.removeNode(node) // remove the node from where it is now...
                it.insertAtTail(key, value) // ... and add it to the tail, which is the most recent one
                it.tail
            }
        }
    }

    fun set(key: Int, value: Int) {
        cache[key]?.let {
            cachedNodes.removeNode(it)
        }?:run {
            evictIfNeeded()
        }
        cachedNodes.insertAtTail(key, value)
        cache[key] = cachedNodes.tail!!
    }

    private fun evictIfNeeded() {
        if (cachedNodes.size >= capacity) {
            val node = cachedNodes.removeHead()
            cache.remove(node?.key)
        }
    }

    fun printNodes() {
        var curr = cachedNodes.head
        while(curr != null) {
            println("(${curr.key}, ${curr.data})")
            curr = curr.next
        }
    }

    override fun toString(): String {
        return "MostRecentlyWatchedCache(cache:$cache, cachedNodes:$cachedNodes)"
    }


}
