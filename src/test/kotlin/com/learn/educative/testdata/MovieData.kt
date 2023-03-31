package com.learn.educative.testdata

import com.learn.educative.dataclass.Genre
import com.learn.educative.datastructures.LinkedListNode
import com.learn.educative.repository.MovieRepository

internal object MovieData {

    fun <T> createLinkedLisNodes(dataLists: List<List<T>>): List<LinkedListNode<T>> = dataLists.map {
        createLinkedListNodes(it)
    }.flatten()


    private fun <T> createLinkedListNodes(dataList: List<T>): List<LinkedListNode<T>>  = dataList.map {
        LinkedListNode<T>(value = it as Int)
    }.toList()

    private fun getMoviesByGenre(genre: Genre) = MovieRepository().getMoviesByGenre(genre)

}

