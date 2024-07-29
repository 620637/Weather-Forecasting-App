package com.example.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.API.Constant
import com.example.weather.API.NetworkResponse
import com.example.weather.API.RetrofitInstance
import com.example.weather.API.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherAPI = RetrofitInstance.weatherAPI
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
     val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city : String){
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response =  weatherAPI.getWeather(Constant.apikey,city)
          try{
              if(response.isSuccessful){
                response.body()?.let {
                    _weatherResult.value = NetworkResponse.Success(it)
                }
              }else{
                  _weatherResult.value = NetworkResponse.Error("Failed to Load data")

              }
          }
          catch (e:Exception){
              _weatherResult.value = NetworkResponse.Error("Failed to Load data")
          }

        }


    }
}