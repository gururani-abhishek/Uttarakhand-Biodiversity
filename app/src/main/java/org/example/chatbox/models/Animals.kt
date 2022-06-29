package org.example.chatbox.models

data class Animals(
    val sciName : String = "",
    val localName : String = "",
    val animalImage : String = "",
    val favBy : ArrayList<String> = ArrayList()
)
