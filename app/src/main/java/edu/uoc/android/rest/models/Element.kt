package edu.uoc.android.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Element {
    @SerializedName("punt_id")
    @Expose
    var puntId: String? = null

    @SerializedName("adreca_nom")
    @Expose
    var adrecaNom: String? = null

    @SerializedName("descripcio")
    @Expose
    var descripcio: String? = null

    @SerializedName("grup_adreca")
    @Expose
    var grupAdreca: GrupAdreca? = null

    @SerializedName("localitzacio")
    @Expose
    var localitzacio: String? = null

    @SerializedName("imatge")
    @Expose
    var imatge: List<String>? = null

    @SerializedName("url_general")
    @Expose
    var urlGeneral: String? = null

    @SerializedName("email")
    @Expose
    var email: List<String>? = null

    @SerializedName("telefon_contacte")
    @Expose
    var telefonContacte: List<String>? = null

    @SerializedName("fax")
    @Expose
    var fax: List<Any>? = null

    @SerializedName("horaris")
    @Expose
    var horaris: String? = null

    @SerializedName("rel_municipis")
    @Expose
    var relMunicipis: RelMunicipis? = null

    @SerializedName("rel_temes")
    @Expose
    var relTemes: RelTemes? = null

    @SerializedName("tags")
    @Expose
    var tags: List<String>? = null

    @SerializedName("categoria")
    @Expose
    var categoria: List<String>? = null

    @SerializedName("rel_comarca")
    @Expose
    var relComarca: List<String>? = null

    @SerializedName("id_secundari")
    @Expose
    var idSecundari: String? = null

    @SerializedName("cercador_codi")
    @Expose
    var cercadorCodi: String? = null

    @SerializedName("director")
    @Expose
    var director: String? = null

    @SerializedName("xarxes_socials")
    @Expose
    var xarxesSocials: List<Any>? = null

    @SerializedName("url_relacionades")
    @Expose
    var urlRelacionades: List<String>? = null

    @SerializedName("inici_horari_hivern")
    @Expose
    var iniciHorariHivern: String? = null

    @SerializedName("inici_horari_estiu")
    @Expose
    var iniciHorariEstiu: String? = null
}
