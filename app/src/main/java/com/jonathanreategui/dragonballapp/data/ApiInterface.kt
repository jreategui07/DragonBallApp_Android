package com.jonathanreategui.dragonballapp.data

import retrofit2.http.GET
import com.jonathanreategui.dragonballapp.model.CharacterResponse

interface ApiInterface {
    @GET("characters")
    suspend fun getCharacters(): CharacterResponse
}
