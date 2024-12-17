package com.jonathanreategui.dragonballapp.data.remote

import retrofit2.http.GET
import com.jonathanreategui.dragonballapp.data.models.CharacterResponse

interface ApiInterface {
    @GET("characters")
    suspend fun getCharacters(): CharacterResponse
}
