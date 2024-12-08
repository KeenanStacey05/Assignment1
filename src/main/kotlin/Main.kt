import contollers.PlayerAPI
import controllers.ClubAPI
import utils.readNextLine

fun main() {
    val clubAPI = ClubAPI()
    val playerAPI = PlayerAPI(clubAPI)
    val menu = Menu(clubAPI, playerAPI)
    menu.displayMenu()
}

class Menu(private val clubAPI: ClubAPI, private val playerAPI: PlayerAPI) {

    fun displayMenu() {
        var running = true
        while (running) {
            println("Player and Club Management System")
            println("\n--- Menu ---")
            println("1. Create a new club")
            println("2. Create a new player")
            println("3. List all clubs")
            println("4. List all players")
            println("5. List players by club")
            println("6. Add player to a club")
            println("7. List players by position")
            println("9. Exit")
            print("Enter your choice: ")

            when (readNextLine("").toIntOrNull()) {
                1 -> createClub()
                2 -> createPlayer()
                3 -> listClubs()
                4 -> listPlayers()
                5 -> listPlayersByClub()
                6 -> addPlayerToClub()
                7 -> listPlayersByPosition()
                8 -> {
                    println("Exiting...")
                    running = false
                }
                else -> println("Invalid option. Please try again.")
            }
        }
    }

    private fun createClub() {
        print("Enter the name of the new club: ")
        val clubName = readNextLine("").trim()
        if (clubName.isNotBlank()) {
            val club = clubAPI.addClub(clubName)
            println("Club '${club.name}' created successfully!")
        } else {
            println("Club name cannot be empty.")
        }
    }

    private fun createPlayer() {
        print("Enter the player's name: ")
        val playerName = readNextLine("").trim()
        print("Enter the player's age: ")
        val playerAge = readNextLine("").toIntOrNull()
        print("Enter the player's position (e.g., Forward, Midfielder, Defender): ")
        val playerPosition = readNextLine("").trim()

        if (playerName.isNotBlank() && playerAge != null && playerPosition.isNotBlank()) {
            val player = playerAPI.addPlayer(playerName, playerAge, playerPosition)
            println("Player '${player.name}' created successfully!")
        } else {
            println("Invalid player details.")
        }
    }

    private fun listClubs() {
        val clubs = clubAPI.getAllClubs()
        if (clubs.isEmpty()) {
            println("No clubs available.")
        } else {
            clubs.forEach { club ->
                println("Club ID: ${club.id}, Name: ${club.name}")
            }
        }
    }

    private fun listPlayers() {
        val players = playerAPI.getAllPlayers()
        if (players.isEmpty()) {
            println("No players available.")
        } else {
            players.forEach { player ->
                println("Player ID: ${player.id}, Name: ${player.name}, Age: ${player.age}, Position: ${player.position}, Club ID: ${player.clubId}")
            }
        }
    }

    private fun listPlayersByClub() {
        print("Enter the club ID to list players: ")
        val clubId = readNextLine("").toIntOrNull()
        if (clubId != null) {
            val players = playerAPI.getPlayersByClub(clubId)
            if (players.isEmpty()) {
                println("No players found for this club.")
            } else {
                players.forEach { player ->
                    println("Player ID: ${player.id}, Name: ${player.name}, Age: ${player.age}, Position: ${player.position}")
                }
            }
        } else {
            println("Invalid club ID.")
        }
    }

    private fun addPlayerToClub() {
        print("Enter the player ID: ")
        val playerId = readNextLine("").toIntOrNull()
        print("Enter the club ID: ")
        val clubId = readNextLine("").toIntOrNull()

        if (playerId != null && clubId != null) {
            val result = playerAPI.addPlayerToClub(playerId, clubId)
            println(result)
        } else {
            println("Invalid player or club ID.")
        }
    }

    private fun listPlayersByPosition() {
        print("Enter the position to filter players (e.g., Forward, Midfielder, Defender): ")
        val position = readNextLine("").trim()
        if (position.isNotBlank()) {
            val players = playerAPI.getPlayersByPosition(position)
            if (players.isEmpty()) {
                println("No players found for the position '$position'.")
            } else {
                players.forEach { player ->
                    println("Player ID: ${player.id}, Name: ${player.name}, Age: ${player.age}, Position: ${player.position}")
                }
            }
        } else {
            println("Position cannot be empty.")
        }




    }
}