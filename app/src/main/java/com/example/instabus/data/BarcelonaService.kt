package com.example.instabus.data
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface BarcelonaService {

        @GET("/bus/nearstation/latlon/%2041.3985182/2.1917991/1.json/")
         fun getBarcelonaData(): Call<com.example.a3andm.data.Response>


}
