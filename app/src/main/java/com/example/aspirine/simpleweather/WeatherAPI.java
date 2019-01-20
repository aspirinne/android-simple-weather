package com.example.aspirine.simpleweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    @GET("/data/2.5/weather")
    Call<PostModel> getData(
            @Query("type") String type,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("APPID") String APPID,
            @Query("q") String city
            );
}

//'provider': 'http://api.openweathermap.org/data/2.5/weather',
//        'api_key': 'c3d107b5a0441a124a4686347c8d53da'

//parametres = {
//        'type': 'like',
//        'units': 'metric',
//        'lang': 'ru',
//        'APPID': config.WEATHER['api_key'],
//    }
//
//    if type(city) is int:
//        parametres.update({'id': city})
//    else:
//        parametres.update({'q': city})
//
//    res = requests.get(
//        config.WEATHER['provider'],
//        params=parametres
//    )
//
//    data = res.json()


//package ru.nknalog.myBooker;
//
//import retrofit2.Call;
//import retrofit2.http.Body;
//import retrofit2.http.GET;
//import retrofit2.http.POST;
//
//public interface NoterApi {
//
////    Product getProduct(long productId);
//    @GET("/projects/1/clients/2")
//    Call<ProjectClient> getClient();
//
//    //    https://myserver1.com/v1/registration
//    @POST("/device/gcm")
//    Call<RegistrationResponse> saveSubscriber(@Body RegistrationBody registrationBody);
//}