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
        }?:run { tail = node }
        head = node
        size ++
    }

    fun append(node: LinkedListNode) { // to tail
        node.next = null
        node.prev = null
        if (head == node) {
            head = node
        } else {
            tail?.next = node
            node.prev = tail
        }
        tail = node
    }

    /**
     * Inserts a `LinkedListNode` to the end of the datastructure
     *
     * @param key Key of the node
     * @param value Value of the node
     */
    fun insertAtTail(key: Int, value: Int) {
        val node = LinkedListNode(key, value)
        tail?.apply {
            node.prev = this
            this.next = node
        }?:run { // if a tail doesn't exist this node is the only element in the datastructure
            head = node
            node.prev = null
        }
        node.next = null
        tail = node
        size ++
    }

    /**
     * Removes node from the chain of nodes and rearranges it.
     *
     * @param node The node to be removed.
     *
     * @return the removed node
     */
    fun remove(node: LinkedListNode?): LinkedListNode? {
        node?.let {
            it.prev?.let { prev -> prev.next = it.next } ?: run { head = node.next }
            it.next?.let { next -> next.prev = it.prev } ?: run { tail = node.prev }
            if (it == head) head = it.next
            if (it == tail) tail = it.prev
            size --
        }
        return node
    }

    fun removeNodeByData(data: Int) {
        var node = head
        while (node != null) {
            if (node.value == data) remove(node)
            node = node.next
        }
    }

    /**
     * Removes the head
     */
    fun removeHead(): LinkedListNode? = remove(head)

    fun removeTail() = remove(tail)
    override fun toString(): String {
        return "DLL(head:$head, tail:$tail, size:$size)"
    }
}
