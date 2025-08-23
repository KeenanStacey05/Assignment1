package controllers

import models.Player

class PlayerAPI(private val clubAPI:ClubAPI) {
    private val players = mutableListOf<Player>()

    fun addPlayer(player: Player){
        // add code to validate that playerId and clubId exist
        // add code for adding a player with a unique id
        players.add(player)
    }

    fun getAllPlayers(): List<Player> = players

    fun getPlayersByClub(clubId: Int): List<Player> =
        players.filter { it.clubId == clubId }

    fun addPlayerToClub(playerId: Int, clubId: Int) : String {
        val player = players.find { it.id == playerId }
        if (player == null) {
            return "models.Player with ID \${playerId} does not exist"
        } else if (clubAPI.clubExists(clubId) != null) {
            return "models.Club with ID \${clubId} does not exist."
        } else {
            players[players.indexOf(player)] = player.copy(clubId = clubId)
            return "models.Player \${player.name} moved to club ID \${clubId}."
        }
    }
}

