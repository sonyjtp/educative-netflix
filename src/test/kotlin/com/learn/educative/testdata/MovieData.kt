package com.learn.educative.testdata

import com.learn.educative.datastructures.LinkedListNode

object MovieData {

    internal  fun <T> createLinkedLisNodes(dataLists: List<List<T>>): List<LinkedListNode<T>> = dataLists.map {
        createLinkedListNodes(it)
    }.flatten()
    private fun <T> createLinkedListNodes(dataList: List<T>): List<LinkedListNode<T>>  = dataList.map {
        LinkedListNode<T>(value = it as Int)
    }.toList()

}
