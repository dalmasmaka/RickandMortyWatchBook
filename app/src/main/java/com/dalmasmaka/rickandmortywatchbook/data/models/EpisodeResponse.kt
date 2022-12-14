
package com.dalmasmaka.rickandmortywatchbook.data.models

import com.google.gson.annotations.SerializedName

data class EpisodeResponse (
    @SerializedName("info") val info: Info,
    @SerializedName("results") val results: List<Episode>
)