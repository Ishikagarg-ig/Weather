package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.Custom.CustomDialog;
import com.example.volley.Custom.OnCitySelecListner;
import com.example.volley.Schema.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.StringReader;

public class secondActivity extends AppCompatActivity{

    ImageView image;
    TextView  temp,tempunit,city,weather,humidity,pressure;
    CustomDialog dialog;

    String[] cities={"Patna",
            "Agartala",
            "Kohima",
            "Chandigarh",
            "Ranchi",
            "Thiruvananthapuram",
            "Mumbai",
            "Bhubaneswar",
            "Panaji",
            "Gandhinagar",
            "Dispur",
            "Amaravati",
            "Dehradun",
            "Shimla",
            "Bhopal",
            "Jaipur",
            "Aizawl",
            "Srinagar",
            "Kolkata",
            "Raipur",
            "Chennai",
            "Lucknow",
            "Gangtok",
            "Itanagar",
            "Bangalore",
            "Shillong",
            "Imphal"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        image = findViewById(R.id.weatherImage);
        temp = findViewById(R.id.temp);
        tempunit = findViewById(R.id.tempunit);
        weather = findViewById(R.id.wea);
        humidity = findViewById(R.id.hum);
        pressure = findViewById(R.id.press);
        city = findViewById(R.id.city);

        // dialog=new Dialog(this);
        //dialog.setContentView(R.layout.dialog);
        //dialog.setCancelable(true);

        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog = new CustomDialog(this, R.layout.dialog, cities, new OnCitySelecListner() {
            @Override
            public void OnSelected(int positon) {

                dialog.dismiss();
                fechdata(cities[positon]);

            }
        });

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

        public void fechdata(String citys){

            String URL = "http://api.openweathermap.org/data/2.5/weather?q="+citys+",india&APPID=7ae56d3da4de535777848c376418289c";
            final Gson gson = new GsonBuilder().create();

            StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Data d = gson.fromJson(response, Data.class);

                    Toast.makeText(secondActivity.this, "" + d.getCoord().getLat(), Toast.LENGTH_SHORT).show();

                    temp.setText("" + (int) (d.getMain().getTemp() - 273));

                    humidity.setText("" + d.getMain().getHumidity());

                    String we = d.getWeather().get(0).getMain();

                    weather.setText(we);

                    city.setText("" + d.getName());

                    pressure.setText("" + d.getMain().getPressure() + " p");


                    if (we.equals("Haze")) {
                        image.setImageResource(R.drawable.haze);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }

            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);

        }

}

