package org.example

import java.lang.Exception

const val HERO_NAME: String = "Madrigal"
var playerLevel: Int = 0

fun main() {
    println("$HERO_NAME announces her presence to the world.")
    println("What level is $HERO_NAME?")
    val playerLevelInput = readln()?.toIntOrNull() ?: 0

    println("$HERO_NAME's level is $playerLevel.")
    readBountyBoard()
    println("Time passes...")
    println("$HERO_NAME returns from her quest.")
    playerLevel++
    println("$playerLevel")
    readBountyBoard()
}

private fun readBountyBoard() {
    val message: String = try {
        val quest: String? = obtainQuest(playerLevel)
        quest?.replace("Nogartse", "xxxxxxxx")
            ?.let { censoredQuest ->
                """
                    $HERO_NAME approaches the bounty board. It reads:
                        "$censoredQuest"
                    """.trimIndent()
            } ?: "$HERO_NAME approaches the bounty board, but it is blank."
    } catch(e: Exception) {
        "$HERO_NAME cannot read what is on the bounty board."
    }
    println(message)
}

private fun obtainQuest(
    playerLevel: Int,
    playerClass: String = "paladin",
    hasBeFriendsBarbarians: Boolean = true,
    hasAngeredBarbarians: Boolean = false
): String? {
    if(playerLevel <= 0) {
        throw InvalidPLayerLevelException()
    }
    return when(playerLevel) {
        1 -> "Meet Mr. Bubbles in the land of soft things."
        in 2..5 -> {
            val canTalkToBarbarians = !hasAngeredBarbarians && (hasBeFriendsBarbarians || playerClass == "barbarian")
            if(canTalkToBarbarians) {
                "Convince the barbarians to call off their invasion."
            } else {
                "Save the town from the barbarian invasions."
            }
        }
        6 -> "Locate the enchanted sword."
        7 -> "Recover the long-last artifact of creation."
        8 -> "Defeat Nogartse, bringer of death and eater of worlds."
        else -> null
    }
}

class InvalidPLayerLevelException():
    IllegalArgumentException("Invalid player level (must be at least 1).")
