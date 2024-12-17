package com.jonathanreategui.dragonballapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val items: List<DragonBallCharacter>,
    val meta: Meta,
    val links: Links
)

@Serializable
data class DragonBallCharacter(
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String,
    val deletedAt: String? = null
)

@Serializable
data class Meta(
    val totalItems: Int,
    val itemCount: Int,
    val itemsPerPage: Int,
    val totalPages: Int,
    val currentPage: Int
)

@Serializable
data class Links(
    val first: String,
    val previous: String? = null,
    val next: String? = null,
    val last: String
)
