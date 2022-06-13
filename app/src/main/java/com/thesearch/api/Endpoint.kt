package com.thesearch.api

import com.thesearch.response.SearchResponse
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {


    @POST("search.php")
    fun search(@Query("f") query:String):
            Call<SearchResponse>

}