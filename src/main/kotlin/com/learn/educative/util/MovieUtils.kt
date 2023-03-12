package com.learn.educative.util

import com.learn.educative.helper.LinkedListNode

internal object MovieUtils {

    /**
     * Creates a map of characters in a search string to the number of occurrences of each character
     *
     * @param searchString The search string
     *
     * @return MutableMap<Char, Int> Map of characters to their frequency in the searchString
     */
    fun findLetterFrequency(searchString: String): MutableMap<Char, Int> {
        val letterFrequency = mutableMapOf<Char, Int>()
        val charArr = searchString.uppercase().replace(" ", "").toCharArray()
        charArr.map {
            letterFrequency.put(it, letterFrequency.getOrElse(it) { 0 } + 1)
        }
        return letterFrequency
    }

    /**
     * Retrieves a list of movies that have the same alphabets and the same number used in the searchString
     *
     * @param searchString The title searched for
     * @param letterFrequencyTitleMap a map of existing movies' letter frequencies
     *
     * @return a list of matches
     */
    fun retrieveSimilarTitles(
        searchString: String, letterFrequencyTitleMap: Map<Map<Char, Int>, List<String>>
    ): List<String>  = letterFrequencyTitleMap[findLetterFrequency(searchString)] ?: listOf()

    private fun findRootNodeByData(node1: LinkedListNode?, node2: LinkedListNode?): LinkedListNode? {
        var left = node1
        var right = node2
        val temp = LinkedListNode(data = -1)
        var prev: LinkedListNode? = temp
        while (left != null && right != null) {
            if (left.data <= right.data) {
                prev!!.next = left
                left = left.next
            } else {
                prev!!.next = right
                right = right.next
            }
            prev = prev.next
        }
        if (left == null) prev!!.next = right else prev!!.next = left
        return temp.next

    }

    fun findRootNodeByData(nodeList: List<LinkedListNode>): LinkedListNode? {
        if (nodeList.isNotEmpty()) {
            var result: LinkedListNode? = nodeList[0]
            for (i in 1 until nodeList.size)  {
                result = findRootNodeByData(result, nodeList[i])
            }
            return result
        }
        return  LinkedListNode(data =  -1)
    }
}
