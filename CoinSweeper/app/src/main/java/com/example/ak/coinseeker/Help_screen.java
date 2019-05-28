package com.example.ak.coinseeker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Help_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        TextView authors = (TextView) findViewById(R.id.authors_link);
        authors.setMovementMethod(LinkMovementMethod.getInstance());

        TextView brick = (TextView) findViewById(R.id.brick);
        brick.setMovementMethod(LinkMovementMethod.getInstance());

        TextView coin = (TextView) findViewById(R.id.coin);
        coin.setMovementMethod(LinkMovementMethod.getInstance());

        TextView coin_second = (TextView) findViewById(R.id.coin_second);
        coin_second.setMovementMethod(LinkMovementMethod.getInstance());

        TextView fly_mario = (TextView) findViewById(R.id.fly_mario);
        fly_mario.setMovementMethod(LinkMovementMethod.getInstance());

        TextView mario_background = (TextView) findViewById(R.id.mario_background);
        mario_background.setMovementMethod(LinkMovementMethod.getInstance());

        TextView mario_background_welcome = (TextView) findViewById(R.id.mario_welcome);
        mario_background_welcome.setMovementMethod(LinkMovementMethod.getInstance());

        TextView moving_background = (TextView) findViewById(R.id.moving_background);
        moving_background.setMovementMethod(LinkMovementMethod.getInstance());

        TextView wing_block = (TextView) findViewById(R.id.wing_block);
        wing_block.setMovementMethod(LinkMovementMethod.getInstance());

        TextView brick_sound = (TextView) findViewById(R.id.brick_sound);
        brick_sound.setMovementMethod(LinkMovementMethod.getInstance());

        TextView coin_sound = (TextView) findViewById(R.id.coin_sound);
        coin_sound.setMovementMethod(LinkMovementMethod.getInstance());

        TextView win_sound = (TextView) findViewById(R.id.win_sound);
        win_sound.setMovementMethod(LinkMovementMethod.getInstance());

    }




}
