package org.example

fun main()
{
    println(performCombatAction(playerIsBoosted = false, enemyEncounterStatus = true))
}

fun performCombatAction(enemyName: String = "Darth Vader",
                        playerIsBoosted: Boolean,
                        enemyEncounterStatus: Boolean): String
{
    return when(playerIsBoosted && enemyEncounterStatus || enemyEncounterStatus)
    {
        true->"You are attacking $enemyName with 2X normal health."
        else ->
        {
            "No enemies found."
        }
    }
}
