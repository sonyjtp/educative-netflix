package com.learn.educative.cache

import com.learn.educative.datastructures.LinkedListNode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MostRecentlyWatchedCacheTest : FunSpec({
    test("addToCache") {
        val cache = MostRecentlyWatchedCache(2)
        cache.get(1) shouldBe null
        cache.set(1, 10)
        cache.get(1) shouldBe LinkedListNode(1, 10, null, null)
        cache.set(2, 20)
        cache.toString() shouldBe "MostRecentlyWatchedCache(" +
                "cache:{" +
                "1=LLNode(key:1, data:10, next:null, prev:null), " +
                "2=LLNode(key:2, data:20, next:null, prev:1)}, " +
                "cachedNodes:DLL(head:LLNode(key:1, data:10, next:2, prev:null), " +
                "tail:LLNode(key:2, data:20, next:null, prev:1), " +
                "size:2))"
        cache.set(3, 30)
        cache.toString() shouldBe "MostRecentlyWatchedCache(" +
                "cache:{" +
                "2=LLNode(key:2, data:20, next:3, prev:null), " +
                "3=LLNode(key:3, data:30, next:null, prev:2)}, " +
                "cachedNodes:DLL(head:LLNode(key:2, data:20, next:3, prev:null), " +
                "tail:LLNode(key:3, data:30, next:null, prev:2), " +
                "size:2))"
        cache.set(2, 22)
        cache.toString() shouldBe "MostRecentlyWatchedCache(" +
                "cache:{" +
                "2=LLNode(key:2, data:22, next:null, prev:3), " +
                "3=LLNode(key:3, data:30, next:2, prev:null)}, " +
                "cachedNodes:DLL(head:LLNode(key:3, data:30, next:2, prev:null), " +
                "tail:LLNode(key:2, data:22, next:null, prev:3), " +
                "size:2))"
    }



})
