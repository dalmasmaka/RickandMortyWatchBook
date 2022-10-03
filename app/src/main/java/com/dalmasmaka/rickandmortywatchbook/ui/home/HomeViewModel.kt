package com.dalmasmaka.rickandmortywatchbook.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dalmasmaka.rickandmortywatchbook.data.models.Character
import com.dalmasmaka.rickandmortywatchbook.data.models.CharacterResponse
import com.dalmasmaka.rickandmortywatchbook.repository.CharactersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    val charactersList : MutableLiveData<List<Character>> = MutableLiveData<List<Character>>()
    val repository = CharactersRepository()
    fun getAllCharacters(){
        repository.apiService.getCharacters()
            .enqueue(object : Callback<CharacterResponse> {
                override fun onResponse(
                    call: Call<CharacterResponse>,
                    response: Response<CharacterResponse>
                ) {
                    charactersList.value=response.body()?.results
                }

                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}