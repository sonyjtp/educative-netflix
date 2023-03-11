package com.learn.educative.testdata

import com.learn.educative.helper.LinkedListNode

object MovieData {

    internal fun createLinkLisNodes(dataLists: List<List<Int>>): List<LinkedListNode> = dataLists.map {
        createLinkListNodes(it)
    }.flatten()
    private fun createLinkListNodes(dataList: List<Int>): List<LinkedListNode>  = dataList.map {
        LinkedListNode(data = it)
    }.toList()




}
