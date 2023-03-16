package com.learn.educative.datastructures

internal data class DoublyLinkedList(
    var head: LinkedListNode? = null,
    var tail: LinkedListNode? = null,
    var size: Int = 0
) {
    fun insertAtHead(key: Int, data: Int) {
        val node = LinkedListNode(key, data)
        head?.apply {
            node.next = this
            this.prev = node
        }?:run {
            tail = node
        }
        head = node
        size ++
    }

    fun insertAtTail(key: Int, data: Int) {
        val node = LinkedListNode(key, data)
        tail?.apply {
            node.prev = this
            this.next = node
        }?:run {
            head = node
            node.prev = null
        }
        node.next = null
        tail = node
        size ++
    }

    fun removeNode(node: LinkedListNode?): LinkedListNode? {
        return node?.let {
            it.prev?.let { prev -> prev.next = it.next }
            it.next?.let { next -> next.prev = it.prev }
            if (it == head) head = head!!.next
            if (it == tail) tail = tail!!.prev
            size --
            it
        }
    }

    fun removeNodeByData(data: Int) {
        var node = head
        while (node != null) {
            if (node.data == data) removeNode(node)
            node = node.next
        }
    }

    fun removeHead() = removeNode(head)

    fun removeTail() = removeNode(tail)
    override fun toString(): String {
        return "DLL(head:$head, tail:$tail, size:$size)"
    }
}
