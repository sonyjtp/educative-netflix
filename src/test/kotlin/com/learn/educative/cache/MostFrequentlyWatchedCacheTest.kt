package com.learn.educative.cache

import com.learn.educative.datastructures.LinkedListNode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MostFrequentlyWatchedCacheTest : FunSpec({

    test("addToCache") {
        val cache = MostFrequentlyWatchedCache(2)
        val expected1 = LinkedListNode(1, 1, frequency = 1, next = LinkedListNode(2, 2))
        val expected2 = LinkedListNode(2, 2, frequency = 1, prev = expected1)
        val expected3 = LinkedListNode(3, 3, 1)
        val expected4 = LinkedListNode(4, 4, frequency = 1, prev = expected2)
        val expected1a = LinkedListNode(1, 1, frequency = 2)
        val expected1b = LinkedListNode(1, 1, frequency = 2, next = expected3)
        val expected2a = LinkedListNode(2, 2, frequency = 1, next = expected4)
        val expected3a = LinkedListNode(3, 3, frequency = 2, prev = expected1)
        cache.set(1, 1)
        cache.set(2, 2)
        cache.toString() shouldBe "MostFrequentlyWatchedCache(keyCache={1=$expected1, 2=$expected2}, " +
                "frequencyCache={1=DLL(head:$expected1, tail:$expected2, size:2)})"
        cache.get(1)
        cache.set(3, 3)
        cache.toString() shouldBe "MostFrequentlyWatchedCache(keyCache={" +
                "1=${LinkedListNode(1,1, 2)}, " +
                "3=$expected3}, " +
                "frequencyCache={" +
                "1=DLL(head:$expected3, tail:$expected3, size:1), " +
                "2=DLL(head:$expected1a, tail:$expected1a, size:1)})"
        cache.get(3)
        cache.set(2, 2)
        cache.set(4, 4)
        cache.toString() shouldBe "MostFrequentlyWatchedCache(keyCache={" +
                "1=$expected1b, 2=$expected2a, 3=$expected3a, 4=$expected4}, " +
                "frequencyCache={" +
                "1=DLL(head:$expected2a, tail:$expected4, size:2), " +
                "2=DLL(head:$expected1b, tail:$expected3a, size:2)})"
    }

})
