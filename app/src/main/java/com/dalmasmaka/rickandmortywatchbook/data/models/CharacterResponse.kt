package com.dalmasmaka.rickandmortywatchbook.data.models

import com.google.gson.annotations.SerializedName

class CharacterResponse (
    @SerializedName("info") val info:Info,
    @SerializedName("results") val results:List<Character>
)