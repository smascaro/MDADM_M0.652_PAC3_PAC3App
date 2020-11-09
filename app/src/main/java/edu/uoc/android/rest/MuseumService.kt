package edu.uoc.android.rest

import edu.uoc.android.rest.models.Museums
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface MuseumService {
    @GET("/api/dataset/museus/format/json/pag-ini/{pagIni}/pag-fi/{pagFi}")
   suspend fun museums(@Path("pagIni") pagIni: String?, @Path("pagFi") pagFi: String?): Museums?
}