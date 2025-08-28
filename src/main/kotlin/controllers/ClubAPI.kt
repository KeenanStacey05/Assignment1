package controllers

import models.Club

class ClubAPI {
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
}

