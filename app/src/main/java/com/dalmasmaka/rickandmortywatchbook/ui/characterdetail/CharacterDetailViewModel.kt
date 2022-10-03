package com.dalmasmaka.rickandmortywatchbook.ui.characterDetail

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

    private val repository = CharactersRepository()
    val character = MutableLiveData<Character>()
    val episodeResponse = MutableLiveData<List<Episode>>()
    val characterResponse = MutableLiveData<Character>()
    fun getCharacterById(characterId : String){
        repository.apiService.getCharacterById(characterId).enqueue(object : retrofit2.Callback<Character>{
            override fun onResponse(
                call: retrofit2.Call<Character>,
                response: Response<Character>
            ) {
                val character = response.body()

                character?.let {
                    var episodes = ""
                    it.episode.forEach{episode ->
                        episodes += episode.substring(episode.lastIndexOf("/") + 1 , episode.length) + ","
                    }
                    getEpisodePerCharacter(episodes)
                    characterResponse.postValue(it)
                }
            }

            override fun onFailure(call: retrofit2.Call<Character>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getEpisodePerCharacter(episodes : String){
        repository.apiService.getEpisodePerCharacter(episodes).enqueue(object : Callback<List<Episode>>{
            override fun onResponse(
                call: Call<List<Episode>>,
                response: Response<List<Episode>>
            ) {
                episodeResponse.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Episode>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}