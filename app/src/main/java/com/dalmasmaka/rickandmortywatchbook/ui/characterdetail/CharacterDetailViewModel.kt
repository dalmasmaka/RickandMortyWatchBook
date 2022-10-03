package com.mirtneg.rickandmortywarchbool.ui.characterDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dalmasmaka.rickandmortywatchbook.data.models.Character
import com.dalmasmaka.rickandmortywatchbook.data.models.Episode
import com.dalmasmaka.rickandmortywatchbook.data.models.EpisodeResponse
import com.dalmasmaka.rickandmortywatchbook.repository.CharactersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailViewModel : ViewModel() {
    val repository = CharactersRepository()
    val character = MutableLiveData<Character>()
    val characterResponse = MutableLiveData<Character>()
    val episodeResponse = MutableLiveData<List<Episode>>()

    fun getCharacterById(characterId: String) {
        repository.apiService.getCharacterById(characterId).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                val character = response.body()
                character?.let {
                    getEpisodesPerCharacter(it.name.substring(0, it.name.indexOf(" ")))
                    characterResponse.postValue(it)
                }

            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }


    fun getEpisodesPerCharacter(episodes: String){
        repository.apiService.getEpisodesPerCharacter(episodes).enqueue(object: Callback<List<Episode>>{
            override fun onResponse(call: Call<List<Episode>>, response: Response<List<Episode>>) {
                episodeResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Episode>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}