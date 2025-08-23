package controllers

import models.Club

class ClubAPI {
    private val clubs = mutableListOf<Club>()

    fun addClub(club: Club) {
        //add code for adding a dept with a unique id
        clubs.add(club)
    }

    fun getAllClubs(): List<Club> = clubs

    fun clubExists(clubId: Int): Club? {
        return clubs.find { it -> it.id == clubId }
    }
}

