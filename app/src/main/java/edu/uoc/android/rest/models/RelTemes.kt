package edu.uoc.android.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RelTemes {
    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("elements")
    @Expose
    var elements: String? = null
}
