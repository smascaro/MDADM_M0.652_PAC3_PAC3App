package edu.uoc.android.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Referency {
    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("nom")
    @Expose
    var nom: String? = null
}
