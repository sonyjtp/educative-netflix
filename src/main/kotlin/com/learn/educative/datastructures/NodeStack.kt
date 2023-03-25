package com.learn.educative.datastructures

import mu.KotlinLogging

internal class NodeStack<T> : Collection<T> {
    private val logger = KotlinLogging.logger(this::class.java.name)

    private var head: Node<T>? = null
    override var size: Int = 0
        private set

    private class Node<T>(var value: T) {
       var next: Node<T>? = null
   }

    fun verifySession(pushSequence:List<Int>,popSequence:List<Int>): Boolean
    {
        logger.info { "Verifying session..." }
        val localStack= NodeStack<Int>()
        var i=0
        pushSequence.forEach{
            localStack.push(it)
            while(!localStack.isEmpty() && localStack.top() == popSequence[i])
            {
                localStack.pop()
                i++
            }
        }
        return localStack.isEmpty().also {
            if(it)logger.info { "Valid session" }  else logger.error { "Faulty session!" }
        }
    }

    private fun push(item: T) {
        logger.debug { "Adding new item: ${item.toString()} to the session stack" }
        val new = Node(item)
        new.next = head
        head = new
        size++
    }
    private fun top(): T {
        if (isEmpty()) throw NoSuchElementException()
        return head!!.value
    }

    private fun pop(): T {
        if (isEmpty()) throw NoSuchElementException()
        val old = head!!
        head = old.next
        size--
        return old.value
    }

    override fun isEmpty(): Boolean = this.size == 0
    override fun contains(element: T): Boolean {
        for (obj in this) {
            if (obj == element) return true
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            if (!contains(element)) return false
        }
        return true
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            var node = head

            override fun hasNext(): Boolean {
                return node != null
            }

            override fun next(): T {
                if (!hasNext()) throw NoSuchElementException()
                val current = node!!
                node = current.next
                return current.value
            }
        }
    }
}
