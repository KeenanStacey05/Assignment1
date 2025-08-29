package controllers

import models.Club
import utils.isValidListIndex
import persistence.Serializer

class ClubAPI(private val serializer: Serializer) {

    private var club = ArrayList<Club>()

    private val clubs = mutableListOf<Club>()

    private var lastId = 0

    private fun getId() = lastId++

    fun addClub(name: String): Club {
        val club = Club(getId(), name)
        clubs.add(club)
        return club
    }

    fun getAllClubs(): List<Club> = clubs

    fun clubExists(clubId: Int): Club? {
        return clubs.find { it -> it.clubId == clubId }
    }

    fun updateClub(indexToUpdate: Int, club: Club?): Boolean {

        val foundClub = clubExists(indexToUpdate)


        if ((foundClub != null) && (club != null)) {
            foundClub.name = club.name
            foundClub.clubId = club.clubId
            return true
        }

        return false
    }

    fun deleteClub(indexToDelete: Int): Club? {
        return if (isValidListIndex(indexToDelete, clubs)) {
            clubs.removeAt(indexToDelete)
        } else {
            null
        }
    }

    @Throws(Exception::class)
    fun load() {

        club = serializer.read() as ArrayList<Club>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(clubs)
    }

}

