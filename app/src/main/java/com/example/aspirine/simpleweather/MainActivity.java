package com.example.aspirine.simpleweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Файл настроек
        SharedPreferences myPreferences = getApplicationContext().getSharedPreferences("WEATHER_SETTINGS", MODE_PRIVATE);
        String current_city = myPreferences.getString("CITY", "unknown");

        if (!"unknown".equals(current_city)) {
            getCurrentWeather(current_city);
        }
        else {

            Intent intent = new Intent(this, CityEditActivity.class);
            startActivity(intent);
            current_city = myPreferences.getString("CITY", "unknown");
            getCurrentWeather(current_city);
        }
    }

    public void refreshWeather(View view) {
        getCurrentWeather("Kazan, RU");
    }

    public void getCurrentWeather(String city) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherAPI api = retrofit.create(WeatherAPI.class);

        // Suda vyebat' parametry
        api.getData(
                "like",
                "metric",
                "ru",
                "c3d107b5a0441a124a4686347c8d53da",
                city
        ).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                Log.d("myLogs", "Response" + response.raw());

                PostModel data = response.body();

                String description = data.getWeather().get(0).getDescription();
                String grad = data.getMain().getTemp().toString();
                String prognoz = "Описание: " + description + "\n"
                        + "Температура: " + grad + "\u00b0";

                // Set the weather
                TextView textView = findViewById(R.id.Gradus);
                textView.setText(prognoz);
            }
            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                //Произошла ошибка
                Log.d("myLogs", "response onFailure");
                Log.d("myLogs", "Throwable: " + t.toString());
            }
        });
    }
}
