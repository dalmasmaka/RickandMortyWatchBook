package com.dalmasmaka.rickandmortywatchbook.repository

import androidx.lifecycle.MutableLiveData
import com.dalmasmaka.rickandmortywatchbook.data.models.CharacterResponse
import com.dalmasmaka.rickandmortywatchbook.data.service.ApiService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersRepository  {

    val BASE_URL="http://rickandmortyapi.com/"

    val apiService: ApiService
    val characters: MutableLiveData<CharacterResponse> = MutableLiveData<CharacterResponse>()
    init {
        val client = OkHttpClient.Builder().build()
        apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


}