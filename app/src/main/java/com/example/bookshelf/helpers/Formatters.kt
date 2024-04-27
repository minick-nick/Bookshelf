package com.example.bookshelf.helpers

import org.jetbrains.annotations.VisibleForTesting

@VisibleForTesting
internal fun formatListOfNames(listOfNames: List<String>) : String {
    var temp = listOfNames.toString()

    if (listOfNames.size > 1) {
        val indexOfLastComma = temp.lastIndexOf(",")
        temp = temp.replaceRange(indexOfLastComma, indexOfLastComma+1, " &")
    }

    return temp.replace("[\\[\\]]".toRegex(), "")
}
