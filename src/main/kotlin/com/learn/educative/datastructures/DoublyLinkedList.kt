package com.learn.educative.datastructures

import mu.KotlinLogging

internal class DoublyLinkedList<T>(
    var head: LinkedListNode<T>? = null,
    var tail: LinkedListNode<T>? = null,
    var size: Int = 0
) {
    private val logger = KotlinLogging.logger(this::class.java.name)

    fun insertAtHead(key: Int, data: Int) {
        val node = LinkedListNode<T>(key, data)
        head?.apply {
            node.next = this
            this.prev = node
        }?:run { tail = node }
        head = node
        size ++
    }

    /**
     * Appends a node to the tail of the `DoublyLinkedList`. Additionally, if the DLL doesn't have a head, add it as the
     *  head as well.
     *
     * @param node The `LinkedListNode` to be added
     */
    fun append(node: LinkedListNode<T>) {
        node.next = null
        node.prev = null
        if (head == null) {
            head = node
        } else {
            tail?.next = node
            node.prev = tail
        }
        size ++
        tail = node
    }

    /**
     * Inserts a `LinkedListNode` to the end of the datastructure
     *
     * @param key Key of the node
     * @param value Value of the node
     */
    fun insertAtTail(key: Int, value: Int) {
        logger.info { "Adding new key-value=$key:$value" }
        val node = LinkedListNode<T>(key, value)
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
     * Removes node from the chain of nodes and rearranges it. When removing the node:
     *  Set `next` of the previous node to the node's `next`
     *  Set `prev` of the next node to the node's `prev`
     *  Decrease the size of the DLL by one.
     *  Clear `next` and `prev` of the node
     *
     * @param node The node to be removed.
     *
     * @return the removed node
     */
    fun remove(node: LinkedListNode<T>?): LinkedListNode<T>? {
        logger.debug { "Removing node with key=${node?.key}" }
        node?.let {
            it.prev?.let { prev -> prev.next = it.next } ?: run { head = node.next }
            it.next?.let { next -> next.prev = it.prev } ?: run { tail = node.prev }
            if (it == head) head = it.next
            if (it == tail) tail = it.prev
            size --
            node.next = null
            node.prev = null
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
    fun removeHead(): LinkedListNode<T>? = remove(head)

    fun removeTail() = remove(tail)
    override fun toString(): String {
        return "DLL(head:$head, tail:$tail, size:$size)"
    }
}
