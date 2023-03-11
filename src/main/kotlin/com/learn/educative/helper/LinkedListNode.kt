package com.learn.educative.helper

internal data class LinkedListNode(
    var key:Int = 0,
    var data:Int,
    var next: LinkedListNode? = null,
    var arbitraryPointer: LinkedListNode? = null)

