package controllers

import models.Club

/**
 * The ClubAPI class is responsible for managing the clubs within the system.
 * It allows operations such as adding new clubs, retrieving all clubs, and checking
 * if a club exists by its ID.
 */
class ClubAPI {
    // A mutable list to store all the clubs
    private val clubs = mutableListOf<Club>()

    /**
     * Adds a new club to the system.
     *
     * @param name The name of the club to be added.
     * @return The newly created Club object.
     */
    fun addClub(name: String): Club {
        val clubId = clubs.size + 1
        val club = Club(clubId, name)
        clubs.add(club)
        return club
    }

    /**
     * Retrieves all the clubs in the system.
     *
     * @return A list of all the clubs.
     */
    fun getAllClubs(): List<Club> = clubs

    /**
     * Checks if a club with a given ID exists.
     *
     * @param clubId The ID of the club to be checked.
     * @return The Club object if found, or null if the club with the given ID does not exist.
     */
    fun clubExists(clubId: Int): Club? {
        return clubs.find { it.id == clubId }
    }
}

