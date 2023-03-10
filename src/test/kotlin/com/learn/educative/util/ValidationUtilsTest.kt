package com.learn.educative.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ValidationUtilsTest : FunSpec({


   test("findLetterFrequency") {
       forAll(
           row("abC", mutableMapOf('A' to 1, 'B' to 1, 'C' to 1)),
           row("aabC", mutableMapOf('A' to 2, 'B' to 1, 'C' to 1)),
           row("aabBC", mutableMapOf('A' to 2, 'B' to 2, 'C' to 1)),
           row("The quick brown fox jumps over the lazy dog",
           mutableMapOf(
               'A' to 1, 'B' to 1, 'C' to 1, 'D' to 1, 'E' to 3, 'F' to 1,'G' to 1, 'H' to 2, 'I' to 1, 'J' to 1,
               'K' to 1, 'L' to 1, 'M' to 1, 'N' to 1, 'O' to 4, 'P' to 1, 'Q' to 1, 'R' to 2, 'S' to 1, 'T' to 2,
               'U' to 2, 'V' to 1, 'W' to 1, 'X' to 1, 'Y' to 1, 'Z' to 1
               )
           ),
        ) { word, frequencyMap -> ValidationUtils.findLetterFrequency(word) shouldBe frequencyMap }
    }

    test("foundSimilarTitles") {
        val letterFreqMap = mapOf(mapOf('A' to 1, 'E' to 2, 'R' to 1, 'S' to 1, 'T' to 1) to listOf(
            "easter", "eaters", "seater", "teaser"))
        forAll(
           row("aretes", 4),
           row("easter", 4),
           row("eATErs", 4),
           row("easer", 0)
        ) { word, count -> ValidationUtils.foundSimilarTitles(word, letterFreqMap).size shouldBe count }
    }
})
