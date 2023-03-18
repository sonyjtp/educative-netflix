package com.learn.educative.cache

import com.learn.educative.datastructures.DoublyLinkedList
import com.learn.educative.datastructures.LinkedListNode

/**
 * Maintain titles in the order of when it was last accessed. The number of movies in cache at a time =`capacity`.
 * If the datastructure is at capacity the new one should be inserted, and the oldest one should be removed.
 *  To maintain the list of most recently watched movies we use a `DoublyLinkedList`, its tail having the most recently
 *   watched movie among all recently watched movie. When a recently watched movie is watched again, it should be
 *   moved to the tail. Searching if the most recently watched movie already exists in a `DoublyLinkedList` is
 *   expensive. So we use a `HashMap` to store the pointers to it.
 */
internal class MostRecentlyWatchedCache(private var capacity: Int) {
    /** Holds the keys of recently watched movies */
    private var keys = HashMap<Int, LinkedListNode>(capacity)
    /** Holds the order of recently watched movies */
    private var cachedNodes: DoublyLinkedList = DoublyLinkedList()

    /**
     * To get the key from `cache`
     *
     * @param key - key of the node
     *
     * @return `null` if the node does not exist in `cache`. If it does, removes the node from its current
     *  position and moves it to the tail.
     */
    fun get(key: Int): LinkedListNode? {
        return keys[key]?.let { node ->
            val value = node.value
            cachedNodes.let {
                it.remove(node) // remove the node from where it is now...
                it.insertAtTail(key, value) // ... and add it to the tail, which is the most recent one
                it.tail
            }
        }
    }

    /**
     * Sets the node in the cache.
     * If the node already exists, removes it.
     * If the node doesn't already exist and the cache is at capacity, removes the oldest one before inserting
     *
     * @param key Key of the node
     * @param value Value of the node
     */
    fun set(key: Int, value: Int) {
        keys[key]?.let {
            cachedNodes.remove(it)
        }?:run {
            evictIfNeeded()
        }
        cachedNodes.insertAtTail(key, value)
        keys[key] = cachedNodes.tail!!
    }

    /**
     * Removes the oldest node (head) by its key if the `DoublyLinkedList` is at capacity.
     *
     */
    private fun evictIfNeeded() {
        if (cachedNodes.size >= capacity) {
            val node = cachedNodes.removeHead()
            keys.remove(node?.key)
        }
    }

    override fun toString(): String {
        return "MostRecentlyWatchedCache(cache:$keys, cachedNodes:$cachedNodes)"
    }


}
