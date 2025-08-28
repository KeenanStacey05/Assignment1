import controllers.ClubAPI
import controllers.PlayerAPI
import models.Club
import models.Player
import utils.readNextInt
import utils.readNextLine


fun main() {

    val clubAPI = ClubAPI()
    val playerAPI = PlayerAPI(clubAPI)

    var choice: Int

    do {
        choice = readNextInt(
            """
                      |Menu:
                      |  1. Add Club
                      |  2. List Clubs
                      |  0. Exit
                      |  > """.trimMargin("|")
        )

        when (choice) {
            1 -> {
                // Add Club
                val name = readNextLine("Name: ")
                val club = clubAPI.addClub(name)
                println(" Club added: $club")
            }
            2 -> {
                // List all clubs
                val clubs = clubAPI.getAllClubs()
                if (clubs.isEmpty()) {
                    println(" No clubs found.")
                } else {
                    println("📋 Clubs:")
                    clubs.forEach { println(" - ${it.clubId}: ${it.name}") }
                }
            }
            0 -> println(" Exiting...")
            else -> println(" Invalid option")
        }
    } while (choice != 0)
}









