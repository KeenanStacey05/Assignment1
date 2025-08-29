import controllers.ClubAPI
import controllers.PlayerAPI
import models.Club
import models.Player
import utils.readNextInt
import utils.readNextLine
import persistence.XMLSerializer
import persistence.JSONSerializer
import persistence.Serializer
import java.io.File

fun main() {

    val clubAPI = ClubAPI(XMLSerializer(File("clubs.xml")))
    val playerAPI = PlayerAPI(clubAPI)

    clubAPI.load()

    var choice: Int

    do {
        choice = readNextInt(
            """
                      |Menu:
                      |  1. Add Club
                      |  2. List Clubs
                      |  3. Add Player
                      |  4. List Players
                      |  5. Listing Players by their Club
                      |  6. Adding Players to a Club
                      |  7. Updating a Club
                      |  8. Updating a Player
                      |  9. Deleting a Club
                      |  10. Deleting a Player
                      |  11. Load Clubs
                      |  12. Save Clubs
                      |  0. Exit
                      |  > """.trimMargin("|")
        )

        when (choice) {
            1 -> {
                // Add Club
                val name = readNextLine("Name: ")
                val club = clubAPI.addClub(name)
                println(" Club added: $club")
                clubAPI.store()
            }
            2 -> {
                // List all clubs
                val clubs = clubAPI.getAllClubs()
                if (clubs.isEmpty()) {
                    println(" No clubs found.")
                } else {
                    println(" Clubs:")
                    clubs.forEach { println(" - ${it.clubId}: ${it.name}") }
                }

            }

            3 -> {
                val name = readNextLine("Name: ")
                val clubId = readNextInt("Club ID: ")
                val age = readNextInt("Age: ")
                val position = readNextLine("Position: ")

                val result = playerAPI.addPlayer(name, clubId, age, position)
                println(result)
            }

            4 -> {
                // List all clubs
                val players = playerAPI.getAllPlayers()
                if (players.isEmpty()) {
                    println(" No players found.")
                } else {
                    println(" Players:")
                    players.forEach { println(" - ${it.id}: ${it.name}: ${it.age}: ${it.clubId}: ${it.position}") }
                }

            }

            5 -> {
            // List players by club
                val clubId = readNextInt("Enter Club ID: ")
                val players = playerAPI.getPlayersByClub(clubId)

                if (players.isEmpty()) {
                    println(" No players found for Club ID $clubId.")
                } else {
                    println(" Players in Club $clubId:")
                    players.forEach {
                        println(" - ${it.id}: ${it.name}, Age: ${it.age}, Position: ${it.position}")
                    }
                }
            }

            6 -> {
                //Adding Players to a Club
                val playerId = readNextInt("Enter Player ID: ")
                var clubId = readNextInt("Enter new Club ID: ")

                val result = playerAPI.addPlayerToClub(playerId, clubId)
                println(result)
            }

            7 -> {

                val indexToUpdate = readNextInt("Enter index of Club to update: ")
                val newName = readNextLine("Enter new Club Name: ")
                val newClubId = readNextInt("Enter new Club ID: ")

                val updatedClub = Club(newClubId, newName)

                val result = clubAPI.updateClub(indexToUpdate, updatedClub)

                if (result) {
                    println("Club updated successfully.")
                    clubAPI.store()
                } else {
                    println("Club update failed. Check if index is valid.")
                }

            }

            8 -> {
                val indexToUpdate = readNextInt("Enter index of Player to update: ")

                val newId = readNextInt("Enter new Player ID: ")
                val newName = readNextLine("Enter new Player Name: ")
                val newAge = readNextInt("Enter new Player Age: ")
                val newClubId = readNextInt("Enter new Club ID: ")
                val newPosition = readNextLine("Enter new Player Position: ")

                val updatedPlayer = Player(newId, newName, newAge, newClubId, newPosition)

                val result = playerAPI.updatePlayer(indexToUpdate, updatedPlayer)

                if (result) {
                    println("Player updated successfully.")
                } else {
                    println("Player update failed. Check if index is valid.")
                }
            }

            9 -> {
                val clubs = clubAPI.getAllClubs()
                if (clubs.isEmpty()) {
                    println(" No clubs found.")
                } else {
                    println(" Clubs:")
                    clubs.forEach { println(" - ${it.clubId}: ${it.name}") }

                    val indexToDelete = readNextInt("Enter the index of the club to delete: ")

                    val deletedClub = clubAPI.deleteClub(indexToDelete)
                    if (deletedClub != null) {
                        println("Delete Successful! Deleted club: ${deletedClub.name}")
                        clubAPI.store()
                    } else {
                        println("Delete NOT Successful. Invalid index.")
                    }
                }
            }

            10 -> {
                val players = playerAPI.getAllPlayers()
                if (players.isEmpty()) {
                    println(" No players found.")
                } else {
                    println(" Players:")
                    players.forEach { println(" - ${it.id}: ${it.name}: ${it.age}: ${it.clubId}: ${it.position}") }

                    val indexToDelete = readNextInt("Enter the index of the player to delete: ")

                    val deletedPlayer = playerAPI.deletePlayer(indexToDelete)
                    if (deletedPlayer != null) {
                        println("Delete Successful! Deleted player: ${deletedPlayer.name}")
                    } else {
                        println("Delete NOT Successful. Invalid index.")
                    }
                }
            }

            11 -> {
                clubAPI.load()
                println("Clubs loaded successfully.")
            }

            12 -> {
                clubAPI.store()
                println("Clubs stored successfully.")
            }


            0 -> println(" Exiting...")
            else -> println(" Invalid option")

        }
    } while (choice != 0)
    clubAPI.store()
}









