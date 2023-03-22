package com.learn.educative.util

object StringUtils {
    /**
     * Creates a map of characters in a search string to the number of occurrences of each character
     *
     * @param searchString The search string
     *
     * @return MutableMap<Char, Int> Map of characters to their frequency in the searchString
     */
    fun findLetterFrequency(searchString: String): MutableMap<Char, Int> {
        val letterFrequency = mutableMapOf<Char, Int>()
        searchString.uppercase().replace(" ", "").map {
            letterFrequency.put(it, letterFrequency.getOrElse(it) { 0 } + 1)
        }
        return letterFrequency
    }

    /**
     * Retrieves a list of movies that have the same alphabets and the same number used in the searchString
     *
     * @param searchString The title searched for
     * @param letterFrequencyTitleMap a map of existing movies' letter frequencies
     *
     * @return a list of matches
     */
    fun retrieveSimilarTitles(searchString: String, letterFrequencyTitleMap: Map<Map<Char, Int>,
            List<String>>) = letterFrequencyTitleMap[findLetterFrequency(searchString)] ?: listOf()
}
