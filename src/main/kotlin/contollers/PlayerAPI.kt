package contollers

import controllers.ClubAPI
import models.Player

class PlayerAPI(private val clubAPI: ClubAPI) {
    private val players = mutableListOf<Player>()


    fun addPlayer(name: String, age: Int, position: String): Player {
        val playerId = players.size + 1
        val player = Player(playerId, name, age, 0, position)
        players.add(player)
        return player
    }


    fun getAllPlayers(): List<Player> = players


    fun getPlayersByClub(clubId: Int): List<Player> {
        return players.filter { it.clubId == clubId }
    }


    fun addPlayerToClub(playerId: Int, clubId: Int): String {
        val player = players.find { it.id == playerId }
        if (player == null) {
            return "Player with ID $playerId does not exist."
        } else {
            val club = clubAPI.clubExists(clubId)
            if (club == null) {
                return "Club with ID $clubId does not exist."
            } else {
                player.clubId = clubId
                return "Player ${player.name} added to club ${club.name}."
            }
        }
    }


    fun getPlayersByPosition(position: String): List<Player> {
        return players.filter { it.position.equals(position, ignoreCase = true) }
    }

}