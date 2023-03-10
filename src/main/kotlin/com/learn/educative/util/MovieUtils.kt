package com.learn.educative.util

import com.learn.educative.internal.LinkedListNode

internal object MovieUtils {

    fun findLetterFrequency(word: String): MutableMap<Char, Int> {
        val letterFrequency = mutableMapOf<Char, Int>()
        val charArr = word.uppercase().replace(" ", "").toCharArray()
        charArr.map {
            letterFrequency.put(it, letterFrequency.getOrElse(it) { 0 } + 1)
        }
        return letterFrequency
    }

    /**
     * Retrieves a list of movies that have the same alphabets and the same number used in the searchString
     * @param searchString The title searched for
     * @param letterFrequencyTitleMap a map of existing movies' letter frequencies
     * @return a list of matches
     */
    fun retrieveSimilarTitles(
        searchString: String, letterFrequencyTitleMap: Map<Map<Char, Int>, List<String>>
    ): List<String>  = letterFrequencyTitleMap[findLetterFrequency(searchString)] ?: listOf()

    private fun sortMoviesByRank(movie1: LinkedListNode?, movie2: LinkedListNode?): LinkedListNode? {
        var left = movie1
        var right = movie2
        var dummy = LinkedListNode(data = -1)
        var prev: LinkedListNode? = dummy
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
        return dummy.next

    }

    fun mergeAllMovieListsByRank(movieList: List<LinkedListNode>): LinkedListNode? {
        if (movieList.isNotEmpty()) {
            var result: LinkedListNode? = movieList[0]
            for (i in 1 until movieList.size)  {
                result = sortMoviesByRank(result, movieList[i])
            }
            return result
        }
        return  LinkedListNode(data =  -1)
    }
}
