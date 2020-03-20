package com.nvk.cleanmyscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchFullScreen(View v){
        String color_mode = "white";
        switch(v.getId()) {
            case R.id.fab_white:
                color_mode = "white";
                break;
            case R.id.fab_black:
                color_mode = "black";
                break;
            case R.id.fab_red:
                color_mode = "red";
                break;
            case R.id.fab_green:
                color_mode = "green";
                break;
            case R.id.fab_blue:
                color_mode = "blue";
                break;
            case R.id.fab_rgb:
                color_mode = "rgb";
                break;
            case R.id.fab_rgb2:
                color_mode = "rgb2";
                break;
        }

        Intent intent = new Intent(MainActivity.this, FullscreenActivity.class);
        intent.putExtra("color_mode", color_mode);
        startActivity(intent);

    }

}
