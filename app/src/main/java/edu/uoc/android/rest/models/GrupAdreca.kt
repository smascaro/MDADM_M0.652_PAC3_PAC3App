package edu.uoc.android.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GrupAdreca {
    @SerializedName("adreca")
    @Expose
    var adreca: String? = null

    @SerializedName("codi_postal")
    @Expose
    var codiPostal: String? = null

    @SerializedName("municipi_nom")
    @Expose
    var municipiNom: String? = null

    @SerializedName("adreca_completa")
    @Expose
    var adrecaCompleta: String? = null
}
