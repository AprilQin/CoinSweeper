package com.example.ak.coinseeker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.AsyncTask;

import static android.R.attr.button;


public class Main_menu extends AppCompatActivity {
    int rows = 0;
    int cols = 0;
    int coins = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1: //option button
                if (resultCode == Activity.RESULT_OK){
                    Toast.makeText(Main_menu.this, "saving game configuration...", Toast.LENGTH_SHORT).show();
                    rows = data.getIntExtra("number of rows", 0);
                    cols = data.getIntExtra("number of columns", 0);
                    coins = data.getIntExtra("number of coins", 0);
                    setupPlayScreen(rows, cols, coins);
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //load data from file
        SharedPreferences load = getSharedPreferences("user_input", Context.MODE_PRIVATE);
        rows = load.getInt("INPUT_ROWS",0);
        cols = load.getInt("INPUT_COLS",0);
        coins = load.getInt("INPUT_COINS",0);

        setupOptionScreen();
        setupHelpScreen();
        setupPlayScreen(rows,cols,coins);

        ImageView mario = (ImageView) findViewById(R.id.mario);
        ImageView coin = (ImageView) findViewById(R.id.coin);
        mario.startAnimation(AnimationUtils.loadAnimation(Main_menu.this, R.anim.slide_left_to_right));
        coin.startAnimation(AnimationUtils.loadAnimation(Main_menu.this, R.anim.animation));

    }


    private void setupHelpScreen() {
        Button help = (Button) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_menu.this, Help_screen.class));
            }
        });
    }

    private void setupOptionScreen() {
        Button option = (Button) findViewById(R.id.configuration);
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Main_menu.this,Choose_configuration.class ), 1);
            }
        });
    }

    private void setupPlayScreen(final int rows, final int cols, final int coins) {
        Button play = (Button) findViewById(R.id.play_game);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(Main_menu.this, GameActivity.class);
                game.putExtra("number of rows", rows);
                game.putExtra("number of columns", cols);
                game.putExtra("number of coins", coins);
                if(rows == 0 && cols == 0 && coins == 0) //first time running app
                    Toast.makeText(Main_menu.this, "Please setup game configuration in option first", Toast.LENGTH_LONG).show();
                startActivity(game);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences user_input_pref = getSharedPreferences("user_input", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = user_input_pref.edit();
        editor.putInt("INPUT_ROWS",rows);
        editor.putInt("INPUT_COLS",cols);
        editor.putInt("INPUT_COINS",coins);
        editor.commit();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        set_button_image(R.id.play_game);
        set_button_image(R.id.configuration);
        set_button_image(R.id.help);
    }

    private void set_button_image(int ButtonID) {
        Button set_button = (Button) findViewById(ButtonID);
        int button_width = set_button.getWidth();
        int button_height = set_button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wing_block);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap,button_width, button_height, true);
        Resources resource = getResources();
        set_button.setBackground(new BitmapDrawable(resource,scaledBitmap));
    }


}
