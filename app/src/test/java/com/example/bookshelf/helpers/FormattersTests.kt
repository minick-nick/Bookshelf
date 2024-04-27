package com.example.bookshelf.helpers

import org.junit.Assert.assertEquals
import org.junit.Test

class FormattersTests {
    private val listOfNames = listOf(
        "Dominic Dofredo",
        "Angelica Dofredo",
        "Cendy Dofredo",
        "Kenny Dofredo"
    )
    @Test
    fun formatListOfNames_1Name() {
        val expectedOutput = "Dominic Dofredo"
        val actualOutput = formatListOfNames(listOfNames.take(1))
        assertEquals(expectedOutput,actualOutput)
    }

    @Test
    fun formatListOfNames_2Names() {
        val expectedOutput = "Dominic Dofredo & Angelica Dofredo"
        val actualOutput = formatListOfNames(listOfNames.take(2))
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun formatListOfNames_3Names() {
        val expectedOutput = "Dominic Dofredo, Angelica Dofredo & Cendy Dofredo"
        val actualOutput = formatListOfNames(listOfNames.take(3))
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun formatListOfNames_4Names() {
        val expectedOutput = "Dominic Dofredo, Angelica Dofredo, Cendy Dofredo & Kenny Dofredo"
        val actualOutput = formatListOfNames(listOfNames.take(4))
        assertEquals(expectedOutput, actualOutput)
    }
}