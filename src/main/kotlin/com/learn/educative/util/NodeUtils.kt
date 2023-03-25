package com.learn.educative.util

import com.learn.educative.datastructures.LinkedListNode

internal object NodeUtils {

    private fun findRootNodeByData(node1: LinkedListNode<Int>?, node2: LinkedListNode<Int>?): LinkedListNode<Int>? {
        var left = node1
        var right = node2
        val temp = LinkedListNode<Int>(value = -1)
        var prev: LinkedListNode<Int>? = temp
        while (left != null && right != null) {
            if (left.value <= right.value) {
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

    fun findRootNodeByData(nodeList: List<LinkedListNode<Int>>): LinkedListNode<Int>? {
        if (nodeList.isNotEmpty()) {
            var result: LinkedListNode<Int>? = nodeList[0]
            for (i in 1 until nodeList.size)  {
                result = findRootNodeByData(result, nodeList[i])
            }
            return result
        }
        return  null
    }
}
