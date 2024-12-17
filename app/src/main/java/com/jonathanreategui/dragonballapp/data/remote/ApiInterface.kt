package com.jonathanreategui.dragonballapp.data.remote

import retrofit2.http.GET
import com.jonathanreategui.dragonballapp.data.models.CharactersResponse

interface ApiInterface {
    @GET("characters")
    suspend fun getCharacters(): CharactersResponse
}
