package com.dalmasmaka.rickandmortywatchbook.data.service

import com.dalmasmaka.rickandmortywatchbook.data.models.Character
import com.dalmasmaka.rickandmortywatchbook.data.models.CharacterResponse
import com.dalmasmaka.rickandmortywatchbook.data.models.Episode
import com.dalmasmaka.rickandmortywatchbook.data.models.EpisodeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/api/character")
    fun getCharacters() : Call<CharacterResponse>

    @GET("/api/character/{characterId}")
    fun getCharacterById(@Path("characterId") characterId : String ) : Call<Character>

    @GET("/api/episode/{episodes}")
    fun getEpisodePerCharacter(@Path("episodes") episodes : String) : Call<List<Episode>>
}

//@Path("path") path: String,
//@Query("page") page:Int?=100