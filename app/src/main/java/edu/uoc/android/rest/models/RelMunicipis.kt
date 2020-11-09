package edu.uoc.android.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RelMunicipis {
    @SerializedName("ine")
    @Expose
    var ine: String? = null

    @SerializedName("municipi_nom")
    @Expose
    var municipiNom: String? = null

    @SerializedName("municipi_nom_curt")
    @Expose
    var municipiNomCurt: String? = null

    @SerializedName("municipi_article")
    @Expose
    var municipiArticle: String? = null

    @SerializedName("municipi_transliterat")
    @Expose
    var municipiTransliterat: String? = null

    @SerializedName("municipi_curt_transliterat")
    @Expose
    var municipiCurtTransliterat: String? = null

    @SerializedName("centre_municipal")
    @Expose
    var centreMunicipal: String? = null

    @SerializedName("grup_comarca")
    @Expose
    var grupComarca: GrupComarca? = null

    @SerializedName("grup_provincia")
    @Expose
    var grupProvincia: GrupProvincia? = null

    @SerializedName("grup_ajuntament")
    @Expose
    var grupAjuntament: GrupAjuntament? = null

    @SerializedName("municipi_escut")
    @Expose
    var municipiEscut: String? = null

    @SerializedName("municipi_bandera")
    @Expose
    var municipiBandera: String? = null

    @SerializedName("municipi_vista")
    @Expose
    var municipiVista: String? = null

    @SerializedName("ine6")
    @Expose
    var ine6: String? = null

    @SerializedName("nom_dbpedia")
    @Expose
    var nomDbpedia: String? = null
}
