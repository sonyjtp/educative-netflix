package com.learn.educative.util

import com.learn.educative.testdata.MovieData
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class NodeUtilsTest : FunSpec({


    test("findHighestRankedMovie") {
        val movieLists = MovieData.createLinkedLisNodes(
            listOf(
                listOf(3, 7, 9),
                listOf(2,3,5,8, 11),
                listOf(1),
                listOf(10),
                listOf(1,5,9,10)
                )
        )
        val nodes = mutableListOf<Int>()
        var nextNode = NodeUtils.findRootNodeByData(movieLists)?.next
        nextNode?.display()
        while (nextNode != null) {
            nodes.add(nextNode!!.data)
            nextNode = nextNode?.next
        }
        nodes.joinToString("") shouldBe "1233557899101011"

    }
})
