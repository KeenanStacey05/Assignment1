package models

data class Player(
    val id: Int,
    val name: String,
    var clubId: Int,
    val age: Int,
    val position: String
)
