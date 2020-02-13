package com.example.instabus
import retrofit2.Call
import retrofit2.http.GET

interface BarcelonaService {

        @GET("/bus/nearstation/latlon/%2041.3985182/2.1917991/1.json/")
        fun getBarcelonaData(): Call<Response>


}
