package com.example.instabus

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.response_row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)
        setSupportActionBar(toolbar)




        val retrofit = Retrofit.Builder()
            .baseUrl("http://barcelonaapi.marcpous.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val api = retrofit.create(BarcelonaService::class.java)

        api.getBarcelonaData().enqueue(object : Callback<Response>{

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                response.body()!!.data.nearstations?.let { showData(response.body()!!) }
                d("houssam", "teeeeeeeeeeeeeest")
                d("houssam", "Resonse :${response.body()}")

            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                d("houssam", "teeeeeeeeeeeeeest2")
                d("houssam", "${t.message}")
            }
        })
    }

    private fun showData(st: Response?) {

       myFirstRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ResponseAdapter(st!!.data.nearstations)
        }
    }
}


