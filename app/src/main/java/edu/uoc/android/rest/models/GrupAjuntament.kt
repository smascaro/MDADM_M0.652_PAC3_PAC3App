package edu.uoc.android.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GrupAjuntament {
    @SerializedName("adreca-completa")
    @Expose
    var adrecaCompleta: String? = null

    @SerializedName("adreca")
    @Expose
    var adreca: String? = null

    @SerializedName("codi_postal")
    @Expose
    var codiPostal: String? = null

    @SerializedName("localitzacio")
    @Expose
    var localitzacio: String? = null

    @SerializedName("telefon_contacte")
    @Expose
    var telefonContacte: String? = null

    @SerializedName("fax")
    @Expose
    var fax: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("url_general")
    @Expose
    var urlGeneral: String? = null

    @SerializedName("cif")
    @Expose
    var cif: String? = null
}
