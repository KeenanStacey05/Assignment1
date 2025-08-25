import controllers.ClubAPI
import controllers.PlayerAPI
import models.Club
import models.Player

fun main() {

    val clubAPI = ClubAPI()
    val playerAPI = PlayerAPI(clubAPI)

    clubAPI.addClub(Club(1, "Chelsea"))
    clubAPI.addClub(Club(2, "Man Utd"))
    clubAPI.addClub(Club(3, "Liverpool"))
    clubAPI.addClub(Club(4, "Man City"))
    clubAPI.addClub(Club(5, "Arsenal"))
    clubAPI.addClub(Club(6, "Spurs"))

    playerAPI.addPlayer(Player(1, "Palmer", 1, 22, "Midfielder"))
    playerAPI.addPlayer(Player(2, "Fernandes", 2, 28, "Midfielder"))
    playerAPI.addPlayer(Player(3, "Salah", 3, 33, "Forward"))
    playerAPI.addPlayer(Player(4, "Haaland", 4, 24, "Forward"))
    playerAPI.addPlayer(Player(5, "Saliba", 5, 25, "Defender"))
    playerAPI.addPlayer(Player(6, "Vicario", 6, 27, "Goalkeeper"))

    // Adding players to a club
    playerAPI.addPlayerToClub(1, 1)
    playerAPI.addPlayerToClub(2, 2)
    playerAPI.addPlayerToClub(3, 3)
    playerAPI.addPlayerToClub(4, 4)
    playerAPI.addPlayerToClub(5, 5)
    playerAPI.addPlayerToClub(6, 6)

    println("1.All Players:")
    playerAPI.getAllPlayers().forEach { println(it) }

    println()

    println("2.All Clubs:")
    clubAPI.getAllClubs().forEach { println(it) }

    println()
    // Displaying players by club
    println("3.Displaying Players by Club")

    println("\nPlayers at Chelsea:")
    playerAPI.getPlayersByClub(1).forEach { println(it) }

    println("\nPlayers at Man Utd:")
    playerAPI.getPlayersByClub(2).forEach { println(it) }

    println("\nPlayers at Liverpool:")
    playerAPI.getPlayersByClub(3).forEach { println(it) }

    println("\nPlayers at Man City:")
    playerAPI.getPlayersByClub(4).forEach { println(it) }

    println("\nPlayers at Arsenal:")
    playerAPI.getPlayersByClub(5).forEach { println(it) }

    println("\nPlayers at Spurs:")
    playerAPI.getPlayersByClub(6).forEach { println(it) }



}


