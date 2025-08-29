package controllers

import models.Club
import models.Player
import utils.isValidListIndex

class PlayerAPI(private val clubAPI:ClubAPI) {
    private val players = mutableListOf<Player>()

    private var lastId = 0

    private fun getId() = lastId++



    fun addPlayer(name: String, clubId: Int, age: Int, position: String): Boolean {
        if (clubAPI.clubExists(clubId) == null) {
            println("Cannot add player: Club with ID $clubId does not exist.")
            return false
        }

        val player = Player(
            id = getId(),
            name = name,
            clubId = clubId,
            age = age,
            position = position
        )

        players.add(player)
        println("Player added: $player")
        return true
    }

    fun getAllPlayers(): List<Player> = players

    fun getPlayersByClub(clubId: Int): List<Player> =
        players.filter { it.clubId == clubId }

    fun addPlayerToClub(playerId: Int, clubId: Int) : String {
        val player = players.find { it.id == playerId }
        if (player == null) {
            return "models.Player with ID \${playerId} does not exist"
        } else if (clubAPI.clubExists(clubId) == null) {
            return "models.Club with ID \${clubId} does not exist."
        } else {
            players[players.indexOf(player)] = player.copy(clubId = clubId)
            return "models.Player \${player.name} moved to club ID \${clubId}."
        }
    }


    fun playerExists(playerId: Int): Player? {
        return players.find { it -> it.id == playerId }
    }


    fun updatePlayer(indexToUpdate: Int, players: Player?): Boolean {

        val foundPlayer = playerExists(indexToUpdate)


        if ((foundPlayer != null) && (players != null)) {
            foundPlayer.name = players.name
            foundPlayer.id = players.id
            foundPlayer.clubId = players.clubId
            foundPlayer.age = players.age
            foundPlayer.position = players.position
            return true
        }

        return false
    }

    fun deletePlayer(indexToDelete: Int): Player? {
        return if (isValidListIndex(indexToDelete, players)) {
            players.removeAt(indexToDelete)
        } else {
            null
        }
    }


}

