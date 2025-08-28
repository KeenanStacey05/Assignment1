package controllers

import models.Player

class PlayerAPI(private val clubAPI:ClubAPI) {
    private val players = mutableListOf<Player>()

    private var lastId = 0

    private fun getId() = lastId++



    fun addPlayer(name: String, clubId: Int, age: Int, position: String): Boolean {
        // 1. Check if club exists
        if (clubAPI.clubExists(clubId) == null) {
            println("Cannot add player: Club with ID $clubId does not exist.")
            return false
        }

        // 2. Generate unique player ID
        val player = Player(
            id = getId(),
            name = name,
            clubId = clubId,
            age = age,
            position = position
        )

        // 3. Add player
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
        } else if (clubAPI.clubExists(clubId) != null) {
            return "models.Club with ID \${clubId} does not exist."
        } else {
            players[players.indexOf(player)] = player.copy(clubId = clubId)
            return "models.Player \${player.name} moved to club ID \${clubId}."
        }
    }
}

