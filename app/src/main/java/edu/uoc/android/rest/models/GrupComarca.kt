package edu.uoc.android.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GrupComarca {
    @SerializedName("comarca_codi")
    @Expose
    var comarcaCodi: String? = null

    @SerializedName("comarca_nom")
    @Expose
    var comarcaNom: String? = null
}
