package com.example.classAndObject.controlFlow

/**
 * Return and Jumps
 * */

/** Break and continue labels */
fun breakNormal() {
    for (i in 1..3) {
        print("$i ")
        for (j in 1..3) {
            if (i == 2) break
        }
        println("$i")
    }
    ln()
}

fun breakLabel() {
    breakL@ for (i in 1..3) {
        print("$i ")
        for (j in 1..3) {
            if (i == 2) break@breakL
        }
        println("$i")
    }
    ln()
}

fun continueNormal() {
    for (i in 1..3) {
        print("$i ")
        for (j in 1..3) {
            if (i == 2) continue
        }
        println("$i")
    }
    ln()
}

fun continueLabel() {
    continueL@ for (i in 1..3) {
        print("$i ")
        for (j in 1..3) {
            if (i == 2) continue@continueL
        }
        println("$i")
    }
    ln()
}

fun returnNormal() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return
        print(it)
    }
    print(" done with explicit label")
}

fun returnLabel() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit
        print(it)
    }
    print(" done with explicit label")
    ln()
}

fun returnLikeBreak() {
    val value = run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop "return value"
            print(it)
        }
    }
    print(" done with nested loop")
    println(value)
    ln()
}

fun main() {
    ln()
    // breakNormal()
    // breakLabel()
    // continueNormal()
    // continueLabel()
    returnNormal()
    ln()
    returnLabel()
    returnLikeBreak()
}

fun ln() {
    println("\n------------------------------------------------------------------------------------")
}
