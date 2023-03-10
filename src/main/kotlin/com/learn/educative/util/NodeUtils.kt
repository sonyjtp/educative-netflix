package com.learn.educative.util

import com.learn.educative.helper.LinkedListNode

internal object NodeUtils {

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
