package com.learn.educative.datastructures

import com.learn.educative.dataclass.Movie
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DoublyLinkedListTest : FunSpec({

  var doublyLinkedList  = DoublyLinkedList<Int>()

  beforeAny {
    doublyLinkedList = DoublyLinkedList()
  }

  test("insertAtHead") {
    val node1 = LinkedListNode<Int>(1, 1)
    val node2 = LinkedListNode(2, 2, next = node1)
    node1.prev = node2
    doublyLinkedList.insertAtHead(node1.key, node1.value)
    doublyLinkedList.insertAtHead(node2.key, node2.value)
    doublyLinkedList.toString() shouldBe "DLL(head:$node2, tail:$node1, size:2)"
  }

  test("append") {
    val node1 = LinkedListNode<Int>(1, Movie(1).id)
    val node2 = LinkedListNode(2, Movie(2).id, next = node1)
    val node3 = LinkedListNode(3, Movie(3).id, prev = node1)
    node1.next = node2
    node3.prev = node2
    doublyLinkedList.append(node1)
    doublyLinkedList.append(node2)
    doublyLinkedList.append(node3)
    doublyLinkedList.toString() shouldBe "DLL(head:$node1, tail:$node3, size:3)"
  }

  test("insertAtTail") {
    val node4 = LinkedListNode<Int>(4, 4)
    val node5 = LinkedListNode<Int>(5, 5)
    node4.next = node5
    node5.prev = node4
    doublyLinkedList.insertAtTail(4, 4)
    doublyLinkedList.insertAtTail(5, 5)
    doublyLinkedList.toString() shouldBe "DLL(head:$node4, tail:$node5, size:2)"
  }

//  test("remove") { }
//
//  test("removeNodeByData") { }
//
//  test("removeHead") { }
//
//  test("removeTail") { }
})
