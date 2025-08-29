package controllers

import models.Club
import utils.isValidListIndex
import persistence.Serializer

/**
 * Controller class for managing clubs.
 *
 * Handles CRUD operations (add, update, delete) and persistence
 * using the provided [Serializer].
 *
 * @property serializer The serializer used for saving and loading club data.
 */
class ClubAPI(private val serializer: Serializer) {

    /** Internal list used for loading clubs from storage. */
    private var club = ArrayList<Club>()

    /** Mutable list of clubs currently in memory. */
    private val clubs = mutableListOf<Club>()

    /** Tracks the last assigned club ID. */
    private var lastId = 0

    /** Generates a unique ID for a new club. */
    private fun getId() = lastId++

    /**
     * Adds a new club with the given [name].
     *
     * @param name The name of the club to add.
     * @return The newly created [Club] object.
     */
    fun addClub(name: String): Club {
        val club = Club(getId(), name)
        clubs.add(club)
        return club
    }

    /**
     * Returns a list of all clubs currently in memory.
     *
     * @return List of [Club].
     */
    fun getAllClubs(): List<Club> = clubs

    /**
     * Checks if a club with the given [clubId] exists.
     *
     * @param clubId The ID of the club to search for.
     * @return The [Club] if found, or `null` if not found.
     */
    fun clubExists(clubId: Int): Club? {
        return clubs.find { it.clubId == clubId }
    }

    /**
     * Updates the club at [indexToUpdate] with the details from [club].
     *
     * @param indexToUpdate The ID of the club to update.
     * @param club The new club data to apply.
     * @return `true` if the club was found and updated, `false` otherwise.
     */
    fun updateClub(indexToUpdate: Int, club: Club?): Boolean {
        val foundClub = clubExists(indexToUpdate)

        if ((foundClub != null) && (club != null)) {
            foundClub.name = club.name
            foundClub.clubId = club.clubId
            return true
        }

        return false
    }

    /**
     * Deletes the club at the given [indexToDelete].
     *
     * @param indexToDelete The index of the club in the list to delete.
     * @return The deleted [Club] if successful, or `null` if the index was invalid.
     */
    fun deleteClub(indexToDelete: Int): Club? {
        return if (isValidListIndex(indexToDelete, clubs)) {
            clubs.removeAt(indexToDelete)
        } else {
            null
        }
    }

    /**
     * Loads clubs from the serializer into memory.
     *
     * @throws Exception if reading from the serializer fails.
     */
    @Throws(Exception::class)
    fun load() {
        club = serializer.read() as ArrayList<Club>
    }

    /**
     * Stores the current list of clubs to the serializer.
     *
     * @throws Exception if writing to the serializer fails.
     */
    @Throws(Exception::class)
    fun store() {
        serializer.write(clubs)
    }
}