package com.learn.educative.cache

import com.learn.educative.datastructures.DoublyLinkedList
import com.learn.educative.datastructures.LinkedListNode

/**
 * Maintains a `DoublyLinkedList` of size = `capacity` of movies that were most frequently watched, with the most
 * watched movie at the tail
 */
internal class MostFrequentlyWatchedCache(private var capacity: Int) {
    private var size = 0
    private var minFrequency = 0
    /** Maps a unique key (node's key) to each movie node */
    private var keyMap  = HashMap<Int, LinkedListNode>(capacity)
    /** Holds the top n (n = `capacity`) most frequently watched movies. The key is the number of
     * times watched and value is a `DoublyLinkedList` of movies*/
    private var frequencyCache = HashMap<Int, DoublyLinkedList>(capacity)

    /**
     * To get the key from `cache`
     *
     * @param key - key of the node
     *
     * @return `null` if the node does not exist in `cache`. If it does, removes the node from its current
     *  position. Increase the frequency of the node by 1 and map it to the key = frequency + 1
     */
    fun get(key: Int): LinkedListNode? {
        return keyMap[key]?.let {
            val temp = it
            frequencyCache[temp.frequency]?.remove(temp)
            if(frequencyCache[it.frequency] == null) {
                 frequencyCache.remove(it.frequency) // No need to maintain a key with null value in the frequencyCache
                if (minFrequency == it.frequency) minFrequency ++
            }
            it.frequency++
            if (!frequencyCache.containsKey(it.frequency)) frequencyCache[it.frequency] = DoublyLinkedList()
            frequencyCache[it.frequency]?.append(it)
            keyMap[key]
        }
    }

    /**
     * Set in keymap
     *
     * @param key - unique key
     * @param value - value of the node returned by the key
     */
    fun  set(key: Int, value: Int) {
        get(key)?.let {
            it.value = value
        } ?: run {
            if (size == capacity) {
                // remove the least-frequently-watched node (head of the DLL) if we are at capacity
                keyMap.remove(frequencyCache[minFrequency]?.head?.key)
                frequencyCache[minFrequency]?.remove(frequencyCache[minFrequency]?.head)
                // if no more nodes exist for the minFrequency key, remove that from the frequencyMap
                if(frequencyCache[minFrequency] == null) frequencyCache.remove(minFrequency)
            }
            minFrequency =  1
            // add the new node to the keyMap. The node will have frequency = minFrequency
            keyMap[key] = LinkedListNode(key, value, frequency = minFrequency)
            if (!frequencyCache.containsKey(keyMap[key]!!.frequency)) {
                // a new DLL for a new frequency key
                frequencyCache[keyMap[key]!!.frequency] = DoublyLinkedList()
            }
            val dll = frequencyCache[keyMap[key]!!.frequency]!!
            dll.append(keyMap[key]!!)
            size ++
        }
    }
    override fun toString(): String {
        return "MostFrequentlyWatchedCache(keyCache=$keyMap, frequencyCache=$frequencyCache)"
    }
}
