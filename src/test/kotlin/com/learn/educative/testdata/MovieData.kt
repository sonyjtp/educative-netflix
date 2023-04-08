package com.learn.educative.testdata

import com.learn.educative.datastructures.LinkedListNode
import com.learn.educative.repository.MovieRepository

internal object MovieData {

    fun <T> createLinkedLisNodes(dataLists: List<List<T>>): List<LinkedListNode<T>> = dataLists.map {
        createLinkedListNodes(it)
    }.flatten()

    fun getCombinations(vararg ids: List<Int>): List<String> {
        val combinations  = mutableListOf<String>()
        ids.forEach {
            val combination = StringBuffer()
            for (id in it) {
                combination.append("${getMovieById(id)};")
            }
            combinations.add(combination.toString())
        }
        return combinations
    }

    fun getMovieList(ids: List<Int>) = ids.map { getMovieById(it)!! }.toList()

    private fun <T> createLinkedListNodes(dataList: List<T>): List<LinkedListNode<T>>  = dataList.map {
        LinkedListNode<T>(value = it as Int)
    }.toList()

    private fun getMovieById(id: Int) = MovieRepository().getMovieById(id)

}

