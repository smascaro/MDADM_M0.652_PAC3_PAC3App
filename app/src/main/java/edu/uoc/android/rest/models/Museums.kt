package edu.uoc.android.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Museums {
    @SerializedName("nom")
    @Expose
    var nom: String? = null

    @SerializedName("machinename")
    @Expose
    var machinename: String? = null

    @SerializedName("descripcio")
    @Expose
    var descripcio: String? = null

    @SerializedName("paraules_clau")
    @Expose
    var paraulesClau: List<String>? = null

    @SerializedName("llicencia")
    @Expose
    var llicencia: String? = null

    @SerializedName("freq_actualitzacio")
    @Expose
    var freqActualitzacio: Int? = null

    @SerializedName("sector")
    @Expose
    var sector: List<String>? = null

    @SerializedName("tema")
    @Expose
    var tema: List<String>? = null

    @SerializedName("responsable")
    @Expose
    var responsable: String? = null

    @SerializedName("idioma")
    @Expose
    var idioma: String? = null

    @SerializedName("home_page")
    @Expose
    var homePage: String? = null

    @SerializedName("referencies")
    @Expose
    var referencies: List<Referency>? = null

    @SerializedName("tipus")
    @Expose
    var tipus: String? = null

    @SerializedName("estat")
    @Expose
    var estat: String? = null

    @SerializedName("creacio")
    @Expose
    var creacio: String? = null

    @SerializedName("modificacio")
    @Expose
    var modificacio: String? = null

    @SerializedName("entitats")
    @Expose
    var entitats: Int? = null

    @SerializedName("elements")
    @Expose
    var elements: List<Element>? = null
}
