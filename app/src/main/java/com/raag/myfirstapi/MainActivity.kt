package com.raag.myfirstapi

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.raag.myfirstapi.adpater.MainAdapter
import com.raag.myfirstapi.data.Countries
import com.raag.myfirstapi.databinding.ActivityMainBinding
import com.raag.myfirstapi.model.MainCountriesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    companion object {
        const val BASE_URL = "https://restcountries.eu/rest/v2/"
    }

    lateinit var adapter: MainAdapter
    lateinit var linearLayout: LinearLayoutManager

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(true)
        linearLayout = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayout

        getCountries()
    }
    private fun getCountries() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainCountriesApi::class.java)

        val retrofit = retrofitBuilder.getCountry()

        retrofit.enqueue(object : Callback<List<Countries>> {
            override fun onResponse(call: Call<List<Countries>>,response: Response<List<Countries>>) {
                val responseBody = response.body()!!
                adapter = MainAdapter(this@MainActivity, responseBody)
                adapter.notifyDataSetChanged()
                binding.recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<List<Countries>>, t: Throwable) {
                d("MainActivity", "Error cargando datos" + t.message)
            }
        })
    }

}
