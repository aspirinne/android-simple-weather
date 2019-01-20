package com.example.aspirine.simpleweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CityEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_edit);
    }

    public void setCity(View view) {

        EditText editText = (EditText) findViewById(R.id.city_editText);
        String new_city = editText.getText().toString();

        SharedPreferences myPreferences = getApplicationContext().getSharedPreferences("WEATHER_SETTINGS", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putString("CITY", new_city);
        myEditor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
