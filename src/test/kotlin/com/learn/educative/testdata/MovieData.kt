package com.learn.educative.testdata

import com.learn.educative.datastructures.LinkedListNode

object MovieData {

    internal fun createLinkedLisNodes(dataLists: List<List<Int>>): List<LinkedListNode> = dataLists.map {
        createLinkedListNodes(it)
    }.flatten()
    private fun createLinkedListNodes(dataList: List<Int>): List<LinkedListNode>  = dataList.map {
        LinkedListNode(data = it)
    }.toList()

}
