package edu.uoc.android.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GrupProvincia {
    @SerializedName("provincia_codi")
    @Expose
    var provinciaCodi: String? = null

    @SerializedName("provincia_nom")
    @Expose
    var provinciaNom: String? = null
}
