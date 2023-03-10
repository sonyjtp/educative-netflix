package com.learn.educative.internal

internal data class LinkedListNode(
    var key:Int = 0,
    var data:Int,
    var next: LinkedListNode? = null,
    var arbitraryPointer: LinkedListNode? = null)

