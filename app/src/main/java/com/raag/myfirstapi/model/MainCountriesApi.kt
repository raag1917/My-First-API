package com.raag.myfirstapi.model

import com.raag.myfirstapi.data.Countries
import retrofit2.Call
import retrofit2.http.GET


interface MainCountriesApi {

    @GET ("all")
    fun getCountry(): Call<List<Countries>>

}