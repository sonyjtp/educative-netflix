package com.learn.educative.cache

import com.learn.educative.dataclass.Movie
import com.learn.educative.datastructures.LinkedListNode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MostRecentlyWatchedCacheTest : FunSpec({
    test("addToCache") {
        val cache = MostRecentlyWatchedCache<Movie>(2)
        val expected1 = LinkedListNode<Int>(1, 10)
        val expected2 = LinkedListNode(2, 20, prev = expected1)
        val expected3 = LinkedListNode(3, 30, prev = expected2)
        cache.get(1) shouldBe null
        cache.set(1, 10)
        cache.get(1) shouldBe LinkedListNode(1, 10, 0, null, null)
        cache.set(2, 20)
        cache.toString() shouldBe "MostRecentlyWatchedCache(" +
                "cache:{1=$expected1, 2=$expected2}, " +
                "cachedNodes:DLL(head:${LinkedListNode(1, 10, next = expected2)}, tail:$expected2, size:2))"
        cache.set(3, 30)
        cache.toString() shouldBe "MostRecentlyWatchedCache(" +
                "cache:{2=${LinkedListNode(2, 20, next = expected3)}, 3=$expected3}, " +
                "cachedNodes:DLL(head:${LinkedListNode(2, 20, next = expected3)}, tail:$expected3, size:2))"
    }
})
