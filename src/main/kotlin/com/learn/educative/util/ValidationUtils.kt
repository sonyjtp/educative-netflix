package com.learn.educative.util
object ValidationUtils {

    fun findLetterFrequency(word: String): MutableMap<Char, Int> {
        val letterFrequency = mutableMapOf<Char, Int>()
        val charArr = word.uppercase().replace(" ", "").toCharArray()
        charArr.map {
            letterFrequency.put(it, letterFrequency.getOrElse(it) { 0 } + 1)
        }
        return letterFrequency
    }

    fun foundSimilarTitles(
        input: String, letterFrequencyTitleMap: Map<Map<Char, Int>, List<String>>
    ): List<String>  = letterFrequencyTitleMap[findLetterFrequency(input)] ?: listOf()
}
